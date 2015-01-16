package com.survey.mvc.model.integration;

import com.google.gson.*;
import com.survey.mvc.entity.UsersEntity;
import com.survey.mvc.model.CompleteAnswer;
import com.survey.mvc.model.CompletedFormRow;
import com.survey.mvc.model.integration.model.Form;
import com.survey.mvc.model.integration.model.Questions;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Belkin on 07.12.2014.
 */
public class SurveyMonkey implements ThirdPartySurvey{
    private static final String HOST_NAME = "https://api.surveymonkey.net";
    private static final String URL_SUFIX = "/v2";

    private String apiKey;
    private String secret;
    private String clientId;
    private String token;

    RestTemplate restTemplate = new RestTemplate();


    public SurveyMonkey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public Collection<Form> getForms() {
        JsonObject requestBody = new JsonObject();
        JsonArray fields = new JsonArray();
        fields.add(new JsonPrimitive("title"));
        fields.add(new JsonPrimitive("preview_url"));
        requestBody.add("fields", fields);

        JsonObject o = call("surveys/get_survey_list", requestBody.toString());

        JsonArray surveys = o.getAsJsonObject("data").getAsJsonArray("surveys");
        ArrayList<Form> forms = new ArrayList<Form>();
        for(JsonElement survey : surveys){
            JsonObject row = survey.getAsJsonObject();
            Form form = new Form(
                    row.get("survey_id").getAsInt(),
                    row.get("title").getAsString(),
                    row.get("preview_url").getAsString()
            );
            forms.add(form);
        }
        return forms;
    }

    @Override
    public ArrayList<CompletedFormRow> getAnswers(Form form){
        ArrayList<Long> idRespondents = getIdRespondents(form);

        JsonObject requestBody = new JsonObject();
        JsonArray fields = new JsonArray();
        for(Long r : idRespondents){
            fields.add(new JsonPrimitive(String.valueOf(r)));
        }
        requestBody.add("respondent_ids", fields);
        requestBody.add("survey_id", new JsonPrimitive(String.valueOf(form.getId())));

        JsonObject respons = call("surveys/get_responses", requestBody.toString());
        ArrayList<CompletedFormRow> answers = new ArrayList<CompletedFormRow>();
        JsonArray data = respons.getAsJsonArray("data");
        for(JsonElement respondentsJS : data){
            JsonArray questionsJS = respondentsJS.getAsJsonObject().getAsJsonArray("questions");
            CompletedFormRow completedForm = new CompletedFormRow();
            for(JsonElement q : questionsJS){
                JsonArray answersJS = q.getAsJsonObject().getAsJsonArray("answers");
                long questionId = q.getAsJsonObject().get("question_id").getAsLong();
                Questions formQuestion = form.getQuestions().get(questionId);
                for(JsonElement a : answersJS){
                    String answerText;
                    if(formQuestion != null) {
                        long answerId = a.getAsJsonObject().get("row").getAsLong();
                        answerText = formQuestion.getOptions().get(answerId);
                    } else {
                         answerText = "";
                    }

                    int i = 0;
                    for(CompleteAnswer ca : completedForm.getAnswers()){
                        if(ca.getIdQuestion()== questionId){
                            i++;
                        }
                    }
                    if(i==0){
                        CompleteAnswer complAnsw = new CompleteAnswer(questionId);
                        if(formQuestion != null) {
                            complAnsw.setOrder(formQuestion.getOrder());
                        }
                        completedForm.putAnswer(complAnsw);
                    }

                    for(CompleteAnswer ca1 : completedForm.getAnswers()){
                        if(ca1.getIdQuestion()==questionId) {
                            ca1.putAnswer(answerText);
                        }
                    }
                }
            }
            completedForm.answerSort();
            answers.add(completedForm);
        }
        return answers;
    }

    /**
     * get URl for auth
     * @param user
     * @return
     */
    @Override
    public String getAuthUrl(int user) {
        String base = "https://ru.surveymonkey.com/user/oauth/authorize";
        String redirectUrl = "http://localhost:8080/auth/" + user + "/assign/callback";
        return base + "?" + getAuthParams() + "&redirect_uri="+redirectUrl;
    }

    private Long getIdCollector(Form form){
        JsonObject respons = call("surveys/get_collector_list", "{\"survey_id\": \"" + form.getId() + "\"}");
        JsonArray array = respons.getAsJsonObject("data").getAsJsonArray("collectors");
        return array.get(0).getAsJsonObject().get("collector_id").getAsLong();
    }

    private ArrayList<Long> getIdRespondents(Form form){
        Long idCollector = getIdCollector(form);
        JsonObject respons = call("surveys/get_respondent_list",
                "{\"survey_id\": \"" + form.getId() + "\", \"collector_id\": \"" + idCollector + "\"}");
        JsonArray array = respons.getAsJsonObject("data").getAsJsonArray("respondents");
        ArrayList<Long> answers = new ArrayList<Long>();
        for(JsonElement a : array){
            answers.add(a.getAsJsonObject().get("respondent_id").getAsLong());
        }
        return answers;
    }

    @Override
    public Form getForm(int id) {
        Collection<Form> forms = getForms();

        Form form = null;
        for(Form f : forms){
            if(f.getId()==id){
                form = f;
            }
        }
        if(form==null){
            return null;
        }

        JsonObject response = call("surveys/get_survey_details", "{\"survey_id\": \"" + id + "\"}");
        JsonArray questions = response.getAsJsonObject("data").getAsJsonArray("pages").get(0).getAsJsonObject().getAsJsonArray("questions");

        for(JsonElement q : questions){
            JsonObject row = q.getAsJsonObject();
            Questions question = new Questions(
                    row.get("question_id").getAsInt(),
                    row.get("heading").getAsString()
            );

            question.setOrder(row.get("position").getAsInt());
            JsonArray options = row.getAsJsonArray("answers");
            for(JsonElement o : options){
                JsonObject optionsRow = o.getAsJsonObject();
                question.getOptions().put(optionsRow.get("answer_id").getAsLong(), optionsRow.get("text").getAsString());
            }

            form.getQuestions().put(row.get("question_id").getAsLong(), question);
        }

        return form;
    }

    /**
     * Get parameters for auth in url query string format
     * @return
     */
    private String getAuthParams() {
        return "client_id=" + clientId + "&api_key=" + apiKey + "&response_type=code";
    }

    private JsonObject call(String url, String body){
        ResponseEntity<String> response  = restTemplate.exchange(
                url(url),
                HttpMethod.POST,
                getHttpEntity(body),
                String.class);

        JsonParser parser = new JsonParser();
        return (JsonObject)parser.parse(response.getBody());
    }

    private JsonObject call(String url){
        ResponseEntity<String> response  = restTemplate.exchange(
                url(url),
                HttpMethod.POST,
                getHttpEntity(),
                String.class);

        JsonParser parser = new JsonParser();
        return (JsonObject)parser.parse(response.getBody());
    }

    private String url(String urlPart) {
        return HOST_NAME + URL_SUFIX + "/" + urlPart + "?api_key=" + apiKey;
    }

    private HttpEntity<String> getHttpEntity() {
        return new HttpEntity<String>("parameters", headers());
    }

    private HttpEntity getHttpEntity(String body) {
        return new HttpEntity<String>(body, headers());
    }

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "bearer " + token);
        return headers;
    }
}
