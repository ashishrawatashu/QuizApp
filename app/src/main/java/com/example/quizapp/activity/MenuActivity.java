package com.example.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.Methods;
import com.example.quizapp.Persons;
import com.example.quizapp.QuestionClass;
import com.example.quizapp.R;
import com.example.quizapp.SingleTon;
import com.example.quizapp.questionModel.Question;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    QuestionClass questionClass = new QuestionClass();

    MaterialCardView back_arrow_CV, add_question_MCV,create_test_section_MCV,test_setting_MCV,list_question_MCV;
    SharedPreferences sharedPreferences;
    Methods methods;
    TextView logout_TV;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        methods = new Methods(this);
        sharedPreferences = this.getSharedPreferences("DataBase", 0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("QuestionList", null);
        Type type = new TypeToken<ArrayList<Question>>() {}.getType();
        ArrayList<Question> questionArrayList = gson.fromJson(json, type);
        if (questionArrayList==null){
            Log.e("Check", "Check5");
            SingleTon.getInstance().questionArrayList = questionClass.getQuestion();
            SharedPreferences prefs = getSharedPreferences("DataBase",0);
            SharedPreferences.Editor editor = prefs.edit();
            Gson gson1 = new Gson();
            String json1 = gson1.toJson(SingleTon.getInstance().questionArrayList);
            editor.putString("QuestionList", json1);
            Log.e("QuestionList",json1);
            editor.apply();
        }else {
            Log.e("Check6", "Check6");
            SingleTon.getInstance().questionArrayList = questionArrayList;
        }
        Log.e("ArrayList", methods.getRequestJson(SingleTon.getInstance().getQuestionArrayList()));
        Log.e("SIZE", methods.getRequestJson(SingleTon.getInstance().getQuestionArrayList().size()));

        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        add_question_MCV = findViewById(R.id.add_question_MCV);
        create_test_section_MCV = findViewById(R.id.create_test_section_MCV);
        test_setting_MCV = findViewById(R.id.test_setting_MCV);
        list_question_MCV = findViewById(R.id.list_question_MCV);
        logout_TV = findViewById(R.id.logout_TV);

        logout_TV.setOnClickListener(this);
        back_arrow_CV.setOnClickListener(this);
        add_question_MCV.setOnClickListener(this);
        create_test_section_MCV.setOnClickListener(this);
        test_setting_MCV.setOnClickListener(this);
        list_question_MCV.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.logout_TV:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("isLoggedIn","false");
                editor.apply();
                startActivity(new Intent(this,SignInActivity.class));
                break;
            case R.id.back_arrow_CV:
                finishAffinity();
                break;

            case R.id.add_question_MCV:
                Intent addQuestionIntent = new Intent(this,AddQuestionActivity.class);
                addQuestionIntent.putExtra("From","MenuActivity");
                addQuestionIntent.putExtra("Position","");
                startActivity(addQuestionIntent);
                break;

            case R.id.create_test_section_MCV:
                Intent testSettingActivity = new Intent(this,TestSettingActivity.class);
                testSettingActivity.putExtra("From","CreateCard");
                startActivity(testSettingActivity);
                break;

            case R.id.test_setting_MCV:
                Intent testSettingActivity1= new Intent(this,TestSettingActivity.class);
                testSettingActivity1.putExtra("From","TestSettingCard");
                startActivity(testSettingActivity1);
                break;

            case R.id.list_question_MCV:
                startActivity(new Intent(this,ListQuestionActivity.class));
                break;
                
                
        }
    }
}