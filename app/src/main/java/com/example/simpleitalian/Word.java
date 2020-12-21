package com.example.simpleitalian;

public class Word {
    private long id;
    private String italian;
    private String russian;
    private String transcription;
    private int image;
    private int speech;
    private boolean known;

    public Word(long id, String italian, String transcription, String russian, int speech, int image, boolean known) {
        this.id = id;
        this.italian = italian;
        this.russian = russian;
        this.transcription = transcription;
        this.speech = speech;
        this.image = image;
        this.known = known;
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

    public void setImage(int image) {
        this.image = image;
    }
    public int getImage() {
        return image;
    }

    public void setSpeech(int speech) {
        this.speech = speech;
    }
    public int getSpeech() {
        return speech;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }
    public boolean getKnown() {
        return known;
    }

    public void print() {
        System.out.println("Word: (id: " + id + "; italian: " + italian + "; russian: " + russian +
                "; transcription: " + transcription + "; image: " + image + "; speech: " + speech +
                "; known: " + known + ");");
    }
}