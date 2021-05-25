package com.example.quizapp.questionModel;

import java.util.List;

public class Question {
    private String question;
    private String answer;
    private boolean isAnswer;
    private int questionLevel;
    private int option;
    private boolean saveInFile;

    private McqWithOptionFour mcqWithOptionFour;


    public Question(String question, String answer, McqWithOptionFour mcqWithOptionFour,int questionLevel,int option) {
        this.question = question;
        this.answer = answer;
        this.mcqWithOptionFour = mcqWithOptionFour;
        this.questionLevel = questionLevel;
        this.option = option;
    }


    public boolean isSaveInFile() {
        return saveInFile;
    }

    public void setSaveInFile(boolean saveInFile) {
        this.saveInFile = saveInFile;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(int questionLevel) {
        this.questionLevel = questionLevel;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public McqWithOptionFour getMcqWithOptionFour() {
        return mcqWithOptionFour;
    }

    public void setMcqWithOptionFour(McqWithOptionFour mcqWithOptionFour) {
        this.mcqWithOptionFour = mcqWithOptionFour;
    }
}
