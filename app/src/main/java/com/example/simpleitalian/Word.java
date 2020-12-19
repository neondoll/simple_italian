package com.example.simpleitalian;

public class Word {
    private long id;
    private String italian;
    private String russian;
    private String transcription;
    private String image;
    private String speech;
    private Boolean known;

    public Word(int id, String italian, String transcription, String russian, String speech, String image) {
        this.id = id;
        this.italian = italian;
        this.russian = russian;
        this.transcription = transcription;
        this.speech = speech;
        this.image = image;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setItalian(String italian) {
        this.italian = italian;
    }

    public String getItalian() {
        return italian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getRussian() {
        return russian;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public String getSpeech() {
        return speech;
    }

    public void setKnown(Boolean known) {
        this.known = known;
    }

    public Boolean getKnown() {
        return known;
    }
}