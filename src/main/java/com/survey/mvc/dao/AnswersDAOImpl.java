package com.survey.mvc.dao;

import com.survey.mvc.entity.AnswersEntity;
import com.survey.mvc.model.CompleteAnswer;
import com.survey.mvc.model.CompletedFormRow;
import com.survey.mvc.model.analysis.data.AnalysisData;
import com.survey.mvc.model.analysis.data.Answer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class AnswersDAOImpl implements AnswersDAO {

    @Autowired
    private SessionFactory sessionFactory;


    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addAnswer(AnswersEntity answer) {
        getCurrentSession().save(answer);
    }

    @Override
    public AnswersEntity getAnswer(int id) {
        AnswersEntity answer = (AnswersEntity) getCurrentSession().get(AnswersEntity.class, id);
        return answer;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<CompletedFormRow> getAnswersByCompletedForm(int id) {
        return processAnswers(getQuery("all").setInteger("id", id));
    }

    @Override
    public ArrayList<CompletedFormRow> getAnswersByCompletedForm(int id, String type) {
        return processAnswers(getQuery(type).setInteger("id", id).setString("status", type));
    }

    private Query getQuery(String type) {
        String sql = "select new map(comF.idCform as idCform, comF.status as status, ansOp.text as textOption, ans.text as textQ, q.idQuestion as idQuestion, q.order as orderQ) " +
                " from QuestionsEntity q, AnswerOptionsEntity ansOp, AnswersEntity ans, CompletedFormsEntity comF\n" +
                " where q.idForm=:id AND q.idQuestion=ansOp.idQuestion AND ansOp.idOption=ans.idOption And ans.idCform=comF.idCform\n";
        if(!type.equals("all")) {
            sql += " AND comF.status like :status ";
        }


        sql +=  " order by comF.idCform, q.order";

        return getCurrentSession().createQuery(sql);
    }

    private ArrayList<CompletedFormRow> processAnswers(Query q) {
        ArrayList<HashMap<String, String>> result = (ArrayList<HashMap<String, String>>) q.list();

        HashMap<Integer, CompletedFormRow> response = new HashMap<Integer, CompletedFormRow>();
        for(HashMap row :result) {
            Integer idCform = (Integer) row.get("idCform");
            Integer idQuestion = (Integer) row.get("idQuestion");
            CompletedFormRow singleRow = response.get(idCform);
            if(singleRow == null) {
                singleRow = new CompletedFormRow(idCform, (String) row.get("status"));
                response.put(idCform, singleRow);
            }

            int i = 0;
            for(CompleteAnswer ca : singleRow.getAnswers()){
                if(ca.getIdQuestion()== idQuestion){
                    i++;
                }
            }
            if(i==0){
                singleRow.putAnswer(new CompleteAnswer(
                        (Integer) row.get("idQuestion"),
                        (Integer) row.get("orderQ")
                ));
            }

            for(CompleteAnswer ca1 : singleRow.getAnswers()){
                if(ca1.getIdQuestion()==idQuestion){
                    String ans = (String) (row.get("textOption") != null ? row.get("textOption") : row.get("textQ"));
                    ca1.putAnswer(ans);
                }
            }
        }
        return new ArrayList<CompletedFormRow>(response.values());
    }

    @SuppressWarnings("unchecked")
    public ArrayList<CompletedFormRow> getAnswersByIdCompletedForm(int id, int idCompForm) {
        return processAnswers(getQuery().setInteger("id", id).setInteger("idCF", idCompForm));
    }

    @SuppressWarnings("unchecked")
    public ArrayList<HashMap<String, String>> getAnalysisData(int idQuestion) {
        Query q = getCurrentSession().createQuery("SELECT new map(q.idQuestion as id, q.scale as scale, a.idOption as idAnswer, ao.order as aOrder, ao.text as answer,  a.text as answerT) \n" +
                "FROM QuestionsEntity q, AnswerOptionsEntity ao, AnswersEntity a \n" +
                "WHERE ao.idQuestion=:id and q.idQuestion = ao.idQuestion and ao.idOption = a.idOption")
                .setInteger("id", idQuestion);

        return (ArrayList<HashMap<String, String>>) q.list();
    }


    private Query getQuery() {
        String sql = "select new map(comF.idCform as idCform, comF.status as status, ansOp.text as textOption, ans.text as textQ, q.idQuestion as idQuestion, q.order as orderQ) " +
                " from QuestionsEntity q, AnswerOptionsEntity ansOp, AnswersEntity ans, CompletedFormsEntity comF\n" +
                " where q.idForm=:id AND q.idQuestion=ansOp.idQuestion AND ansOp.idOption=ans.idOption And ans.idCform=comF.idCform\n" +
                " AND comF.idCform=:idCF";

        return getCurrentSession().createQuery(sql);
    }
}
