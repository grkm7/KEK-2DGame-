package com.info.oyuncalismasi;

public class Skorlar {
    private String skor_id;
    private String name;
    private int skor;

    public Skorlar() {
    }

    public Skorlar(String skor_id, String name, int skor) {
        this.skor_id = skor_id;
        this.name = name;
        this.skor = skor;
    }

    public String getSkor_id() {
        return skor_id;
    }

    public void setSkor_id(String skor_id) {
        this.skor_id = skor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }
}
