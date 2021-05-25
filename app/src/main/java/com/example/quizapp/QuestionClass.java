package com.example.quizapp;

import com.example.quizapp.questionModel.McqWithOptionFour;
import com.example.quizapp.questionModel.Question;
import com.example.quizapp.questionModel.QuestionList;

import java.util.ArrayList;

public class QuestionClass {
    ArrayList<Question> questionListArrayList = new ArrayList<>();
    public ArrayList<Question>  getQuestion(){
        McqWithOptionFour mcqWithOptionFour1 = new McqWithOptionFour("China", "India", "Pakistan", "Nigeria");
        Question question1 = new Question("Which country recorded the highest number of child births on the New Year’s Day?", "India", mcqWithOptionFour1,1,2);
        questionListArrayList.add(question1);

        McqWithOptionFour mcqWithOptionFour2 = new McqWithOptionFour("China", "India", "Brazil", "Nigeria");
        Question question2 = new Question("Which country is to set up its new Research base ‘Comandante Ferraz Antarctic Station’ in Antarctica?", "Brazil",  mcqWithOptionFour2,2,3);
        questionListArrayList.add(question2);


        McqWithOptionFour mcqWithOptionFour3 = new McqWithOptionFour("January 24", "January 25", "January 26", "January 27");
        Question question3 = new Question("When is the International Customs Day (ICD) celebrated annually across the world?", "January 26",  mcqWithOptionFour3,3,3);
        questionListArrayList.add(question3);


        McqWithOptionFour mcqWithOptionFour4 = new McqWithOptionFour("Maldives", "India", "Brazil", "Nigeria");
        Question question4 = new Question("Which country has recently re-joined the Commonwealth as its 54th member country?", "Maldives", mcqWithOptionFour4,4,1);
        questionListArrayList.add(question4);


        McqWithOptionFour mcqWithOptionFour5 = new McqWithOptionFour("Maldives", "Sri Lanka", "Brazil", "China");
        Question question5 = new Question("Union Cabinet of India approved Alliance Air to start flight operations between India and which country, by waiving 20-aircraft rule?, ","Sri Lanka", mcqWithOptionFour5,5,3);
        questionListArrayList.add(question5);


        QuestionList questionList = new QuestionList();
        questionList.setQuestions(questionListArrayList);

        return questionListArrayList;
    }



}
