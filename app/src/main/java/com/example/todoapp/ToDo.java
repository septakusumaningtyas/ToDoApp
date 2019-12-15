package com.example.todoapp;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class ToDo {
    String titledoes,datedoes,descdoes;
    String keydoes;

    public ToDo(String s, String toString, String string){

    }

    public ToDo(String titledoes, String datedoes, String descdoes, String keydoes) {
        this.titledoes = titledoes;
        this.datedoes = datedoes;
        this.descdoes = descdoes;
        this.keydoes = keydoes;
    }

    public String getKeydoes() {
        return keydoes;
    }

    public void setKeydoes(String keydoes) {
        this.keydoes = keydoes;
    }

    public String getTitledoes() {
        return titledoes;
    }

    public void setTitledoes(String titledoes) {
        this.titledoes = titledoes;
    }

    public String getDatedoes() {
        return datedoes;
    }

    public void setDatedoes(String datedoes) {
        this.datedoes = datedoes;
    }

    public String getDescdoes() {
        return descdoes;
    }

    public void setDescdoes(String descdoes) {
        this.descdoes = descdoes;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("titledoes",  titledoes);
        result.put("datedoes", datedoes);
        result.put("descdoes", descdoes);
        return result;
    }
}
