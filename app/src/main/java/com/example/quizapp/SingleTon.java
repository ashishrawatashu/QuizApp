package com.example.quizapp;

import com.example.quizapp.questionModel.Question;

import java.util.ArrayList;
import java.util.List;

public class SingleTon {
    private static SingleTon singleTon;
    public int quizLevel, scoreEachQuestion;
    public long timeInQuiz;;
    public String fileName;
    public static SingleTon getInstance() {
        if (singleTon == null)
            singleTon = new SingleTon();
        return singleTon;
    }

    public ArrayList<Persons> personsList = new ArrayList<>();

    public ArrayList<Question> questionArrayList = new ArrayList<>();


    public ArrayList<Question> getQuestionArrayList() {
        return questionArrayList;
    }

    public void setQuestionArrayList(ArrayList<Question> questionArrayList) {
        this.questionArrayList = questionArrayList;
    }

    public static SingleTon getSingleTon() {
        return singleTon;
    }

    public static void setSingleTon(SingleTon singleTon) {
        SingleTon.singleTon = singleTon;
    }

    public ArrayList<Persons> getPersonLists() {
        return personsList;
    }

    public void setPersonLists(ArrayList<Persons> personLists) {
        this.personsList = personLists;
    }


}
