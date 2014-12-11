package com.survey.mvc.model.analysis.data;

/**
 * Created by Belkin on 09.12.2014.
 */
public class Answer {
    private long id;
    private String text;
    private long idCform;
    private int code;

    public Answer() {
    }

    public Answer(long id, String text) {
        this();
        this.id = id;
        this.text = text;
    }

    public Answer(long id, String text, int code) {
        this(id, text);
        this.code = code;
    }

    public Answer(long id, String text, int code, long idCform) {
        this(id, text, code);
        this.idCform = idCform;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getIdCform() {
        return idCform;
    }
}
