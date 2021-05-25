package com.example.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.SingleTon;
import com.example.quizapp.questionModel.McqWithOptionFour;
import com.example.quizapp.questionModel.Question;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;

public class AddQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    TextView add_question_TV;
    MaterialCardView back_arrow_CV;
    Button continue_BT;
    EditText add_question_ET;
    EditText option_one_ET,option_two_ET ,option_three_ET,option_four_ET;
    RadioButton level_one_RB,level_two_RB,level_three_RB,level_four_RB,level_five_RB;
    RadioButton answer_option_one_RB,answer_option_two_RB,answer_option_three_RB,answer_option_four_RB;
    int questionLevel =0, option=0;
    
    String question, answer="", optionOne, optionTwo, optionThree, optionFour;

    int listPosition;
    String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        add_question_TV = findViewById(R.id.add_question_TV);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);

        continue_BT = findViewById(R.id.continue_BT);

        add_question_ET = findViewById(R.id.add_question_ET);

        option_one_ET = findViewById(R.id.option_one_ET);
        option_two_ET = findViewById(R.id.option_two_ET);
        option_three_ET = findViewById(R.id.option_three_ET);
        option_four_ET = findViewById(R.id.option_four_ET);

        level_one_RB = findViewById(R.id.level_one_RB);
        level_two_RB = findViewById(R.id.level_two_RB);
        level_three_RB = findViewById(R.id.level_three_RB);
        level_four_RB = findViewById(R.id.level_four_RB);
        level_five_RB = findViewById(R.id.level_five_RB);

        answer_option_one_RB = findViewById(R.id.answer_option_one_RB);
        answer_option_two_RB = findViewById(R.id.answer_option_two_RB);
        answer_option_three_RB = findViewById(R.id.answer_option_three_RB);
        answer_option_four_RB = findViewById(R.id.answer_option_four_RB);

        continue_BT.setOnClickListener(this);
        back_arrow_CV.setOnClickListener(this);

        level_one_RB.setOnClickListener(this);
        level_two_RB.setOnClickListener(this);
        level_three_RB.setOnClickListener(this);
        level_four_RB.setOnClickListener(this);
        level_five_RB.setOnClickListener(this);

        answer_option_one_RB.setOnClickListener(this);
        answer_option_two_RB.setOnClickListener(this);
        answer_option_three_RB.setOnClickListener(this);
        answer_option_four_RB.setOnClickListener(this);


        Intent intent = getIntent();
        from = intent.getStringExtra("From");
        if (from.equals("ListQuestionActivity")){
            listPosition = intent.getIntExtra("Position",0);
            add_question_TV.setText("UPDATE QUESTION");
            add_question_ET.setText(SingleTon.getInstance().getQuestionArrayList().get(listPosition).getQuestion());
            option_one_ET.setText(SingleTon.getInstance().getQuestionArrayList().get(listPosition).getMcqWithOptionFour().getAnswerOne());
            option_two_ET.setText(SingleTon.getInstance().getQuestionArrayList().get(listPosition).getMcqWithOptionFour().getAnswerTwo());
            option_three_ET.setText(SingleTon.getInstance().getQuestionArrayList().get(listPosition).getMcqWithOptionFour().getAnswerThree());
            option_four_ET.setText(SingleTon.getInstance().getQuestionArrayList().get(listPosition).getMcqWithOptionFour().getAnswerFour());

            option = SingleTon.getInstance().getQuestionArrayList().get(listPosition).getOption();
            questionLevel = SingleTon.getInstance().getQuestionArrayList().get(listPosition).getQuestionLevel();
            answer = SingleTon.getInstance().getQuestionArrayList().get(listPosition).getAnswer();


            if (SingleTon.getInstance().getQuestionArrayList().get(listPosition).getQuestionLevel()==1){
                level_one_RB.setChecked(true);
            }else if (SingleTon.getInstance().getQuestionArrayList().get(listPosition).getQuestionLevel()==2){
                level_two_RB.setChecked(true);
            }else if (SingleTon.getInstance().getQuestionArrayList().get(listPosition).getQuestionLevel()==3){
                level_three_RB.setChecked(true);
            }else if (SingleTon.getInstance().getQuestionArrayList().get(listPosition).getQuestionLevel()==4){
                level_four_RB.setChecked(true);
            }else if (SingleTon.getInstance().getQuestionArrayList().get(listPosition).getQuestionLevel()==5){
                level_five_RB.setChecked(true);
            }

            if (SingleTon.getInstance().getQuestionArrayList().get(listPosition).getOption()==1){
                answer_option_one_RB.setChecked(true);
            }else if (SingleTon.getInstance().getQuestionArrayList().get(listPosition).getOption()==2){
                answer_option_two_RB.setChecked(true);
            }else if (SingleTon.getInstance().getQuestionArrayList().get(listPosition).getOption()==3){
                answer_option_three_RB.setChecked(true);
            }else if (SingleTon.getInstance().getQuestionArrayList().get(listPosition).getOption()==4){
                answer_option_four_RB.setChecked(true);
            }

        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.back_arrow_CV:
                onBackPressed();
                break;

         
            case R.id.level_one_RB:
                questionLevel = 1;
                break;

            case R.id.level_two_RB:
                questionLevel = 2;

                break;

            case R.id.level_three_RB:
                questionLevel = 3;

                break;

            case R.id.level_four_RB:
                questionLevel = 4;

                break;

            case R.id.level_five_RB:
                questionLevel = 5;
                break;


            case R.id.answer_option_one_RB:
                option =1;
                answer = option_one_ET.getText().toString().trim();
                break;

            case R.id.answer_option_two_RB:
                option =2;
                answer = option_two_ET.getText().toString().trim();
                break;

            case R.id.answer_option_three_RB:
                option =3;
                answer = option_three_ET.getText().toString().trim();
                break;

            case R.id.answer_option_four_RB:
                option =4;
                answer = option_four_ET.getText().toString().trim();
                break;


            case R.id.continue_BT:
                question = add_question_ET.getText().toString();
                
                optionOne = option_one_ET.getText().toString().trim();
                optionTwo = option_two_ET.getText().toString().trim();
                optionThree = option_three_ET.getText().toString().trim();
                optionFour = option_four_ET.getText().toString().trim();
                
                
                if (question.isEmpty()){
                    add_question_ET.setError("Enter question ..!");
                    option_two_ET.setError(null);
                    option_three_ET.setError(null);
                    option_four_ET.setError(null);
                    option_one_ET.setError(null);

                }else if (optionOne.isEmpty()){
                    add_question_ET.setError(null);
                    option_two_ET.setError(null);
                    option_three_ET.setError(null);
                    option_four_ET.setError(null);
                    option_one_ET.setError("Enter option one !");
                }else if (optionTwo.isEmpty()){
                    add_question_ET.setError(null);
                    option_two_ET.setError("Enter option two !");
                    option_three_ET.setError(null);
                    option_four_ET.setError(null);
                    option_one_ET.setError(null);
                }else if (optionThree.isEmpty()){
                    add_question_ET.setError(null);
                    option_two_ET.setError(null);
                    option_three_ET.setError("Enter option three !");
                    option_four_ET.setError(null);
                    option_one_ET.setError(null);
                }else if (optionFour.isEmpty()){
                    add_question_ET.setError(null);
                    option_two_ET.setError(null);
                    option_three_ET.setError(null);
                    option_four_ET.setError("Enter option four !");
                    option_one_ET.setError(null);
                }else if (answer.isEmpty()){
                    add_question_ET.setError(null);
                    option_two_ET.setError(null);
                    option_three_ET.setError(null);
                    option_four_ET.setError(null);
                    option_one_ET.setError(null);
                    Toast.makeText(this, "Select answer", Toast.LENGTH_SHORT).show();
                }else if (questionLevel==0){
                    add_question_ET.setError(null);
                    option_two_ET.setError(null);
                    option_three_ET.setError(null);
                    option_four_ET.setError(null);
                    option_one_ET.setError(null);
                    Toast.makeText(this, "Select question level", Toast.LENGTH_SHORT).show();
                }else {
                    add_question_ET.setError(null);
                    option_two_ET.setError(null);
                    option_three_ET.setError(null);
                    option_four_ET.setError(null);
                    option_one_ET.setError(null);

                    if (from.equals("ListQuestionActivity")){
                        SingleTon.getInstance().questionArrayList.get(listPosition).setAnswer(answer);
                        SingleTon.getInstance().questionArrayList.get(listPosition).setQuestionLevel(questionLevel);
                        SingleTon.getInstance().questionArrayList.get(listPosition).setOption(option);
                        SingleTon.getInstance().questionArrayList.get(listPosition).setQuestion(question);

                        SingleTon.getInstance().questionArrayList.get(listPosition).getMcqWithOptionFour().setAnswerOne(optionOne);
                        SingleTon.getInstance().questionArrayList.get(listPosition).getMcqWithOptionFour().setAnswerTwo(optionTwo);
                        SingleTon.getInstance().questionArrayList.get(listPosition).getMcqWithOptionFour().setAnswerThree(optionThree);
                        SingleTon.getInstance().questionArrayList.get(listPosition).getMcqWithOptionFour().setAnswerFour(optionFour);

                        SharedPreferences prefs = getSharedPreferences("DataBase",0);
                        SharedPreferences.Editor editor = prefs.edit();
                        Gson gson1 = new Gson();
                        String json1 = gson1.toJson(SingleTon.getInstance().questionArrayList);
                        editor.putString("QuestionList", json1);
                        Log.e("QuestionList",json1);
                        editor.apply();
                        Toast.makeText(this, "Question updated successfully ", Toast.LENGTH_SHORT).show();
                        finish();

                    }else {
                        McqWithOptionFour mcqWithOptionFour = new McqWithOptionFour(optionOne, optionTwo, optionThree, optionFour);
                        Question question2 = new Question(question, answer,  mcqWithOptionFour,questionLevel,option);

                        SingleTon.getInstance().questionArrayList.add(question2);
                        SharedPreferences prefs = getSharedPreferences("DataBase",0);
                        SharedPreferences.Editor editor = prefs.edit();
                        Gson gson1 = new Gson();
                        String json1 = gson1.toJson(SingleTon.getInstance().questionArrayList);
                        editor.putString("QuestionList", json1);
                        Log.e("QuestionList",json1);
                        editor.apply();
                        Toast.makeText(this, "Question save successfully ", Toast.LENGTH_SHORT).show();
                        finish();
                    }




                }

                
                
                
                break;

        }
    }
}