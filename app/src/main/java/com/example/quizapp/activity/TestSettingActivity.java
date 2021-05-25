package com.example.quizapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.SingleTon;
import com.google.android.material.card.MaterialCardView;

public class TestSettingActivity extends AppCompatActivity implements View.OnClickListener {
    Button continue_BT;
    //radio button for question one
    RadioButton radio_score_five_BT_for_question_one, radio_score_ten_BT_for_question_one, radio_score_fifteen_BT_for_question_one;

    //radio button for question two
    RadioButton radio_time_five_BT_for_question_two, radio_time_twenty_BT_for_question_two, radio_time_thirty_BT_for_question_two;

    //radio button for question three
    RadioButton radio_five_BT_for_question_three, radio_ten_BT_for_question_three, radio_fifteen_BT_for_question_three;


    //radio button for question four
    RadioButton level_one_RB, level_two_RB, level_three_RB, level_four_RB, level_five_RB;


    String scoreEachQuestion = "", timeInQuiz = "", totalQuestion = "", quizLevel = "";


    MaterialCardView back_arrow_CV;

    Dialog successfully_dialog;
    SharedPreferences sharedPreferences;
    String from;


    TextView question4_TV;
    RadioGroup question4_RG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Intent intent = getIntent();
        from = intent.getStringExtra("From");
        sharedPreferences = getSharedPreferences("DataBase", 0);

        SingleTon.getInstance().quizLevel = Integer.parseInt(sharedPreferences.getString("quizLevel", ""));
        SingleTon.getInstance().scoreEachQuestion = Integer.parseInt(sharedPreferences.getString("scoreEachQuestion", ""));
        SingleTon.getInstance().timeInQuiz = Long.parseLong(sharedPreferences.getString("timeInQuiz", ""));


        //find view by id question one
        radio_score_five_BT_for_question_one = findViewById(R.id.radio_score_five_BT_for_question_one);
        radio_score_ten_BT_for_question_one = findViewById(R.id.radio_score_ten_BT_for_question_one);
        radio_score_fifteen_BT_for_question_one = findViewById(R.id.radio_score_fifteen_BT_for_question_one);
        //onclick question one
        radio_score_five_BT_for_question_one.setOnClickListener(this);
        radio_score_ten_BT_for_question_one.setOnClickListener(this);
        radio_score_fifteen_BT_for_question_one.setOnClickListener(this);

        //find view by id question two
        radio_time_five_BT_for_question_two = findViewById(R.id.radio_time_five_BT_for_question_two);
        radio_time_twenty_BT_for_question_two = findViewById(R.id.radio_time_twenty_BT_for_question_two);
        radio_time_thirty_BT_for_question_two = findViewById(R.id.radio_time_thirty_BT_for_question_two);
        //onclick question one
        radio_time_five_BT_for_question_two.setOnClickListener(this);
        radio_time_twenty_BT_for_question_two.setOnClickListener(this);
        radio_time_thirty_BT_for_question_two.setOnClickListener(this);


        //find view by id question three
        radio_five_BT_for_question_three = findViewById(R.id.radio_five_BT_for_question_three);
        radio_ten_BT_for_question_three = findViewById(R.id.radio_ten_BT_for_question_three);
        radio_fifteen_BT_for_question_three = findViewById(R.id.radio_fifteen_BT_for_question_three);
        //onclick question three
        radio_five_BT_for_question_three.setOnClickListener(this);
        radio_ten_BT_for_question_three.setOnClickListener(this);
        radio_fifteen_BT_for_question_three.setOnClickListener(this);

        //find view by id question four
        level_one_RB = findViewById(R.id.level_one_RB);
        level_two_RB = findViewById(R.id.level_two_RB);
        level_three_RB = findViewById(R.id.level_three_RB);
        level_four_RB = findViewById(R.id.level_four_RB);
        level_five_RB = findViewById(R.id.level_five_RB);

        question4_TV = findViewById(R.id.question4_TV);
        question4_RG = findViewById(R.id.question4_RG);

        //onclick question four
        level_one_RB.setOnClickListener(this);
        level_two_RB.setOnClickListener(this);
        level_three_RB.setOnClickListener(this);
        level_four_RB.setOnClickListener(this);
        level_five_RB.setOnClickListener(this);
        //find view by id Button and onclick of button
        continue_BT = findViewById(R.id.continue_BT);
        continue_BT.setOnClickListener(this);


        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        back_arrow_CV.setOnClickListener(this);

        if (from.equals("TestSettingCard")) {
            question4_TV.setVisibility(View.VISIBLE);
            question4_RG.setVisibility(View.VISIBLE);
        } else {
            question4_TV.setVisibility(View.GONE);
            question4_RG.setVisibility(View.GONE);
            continue_BT.setText("Continue");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.radio_score_five_BT_for_question_one:
                scoreEachQuestion = "5";

                break;

            case R.id.radio_score_ten_BT_for_question_one:
                scoreEachQuestion = "10";


                break;

            case R.id.radio_score_fifteen_BT_for_question_one:
                scoreEachQuestion = "15";
                break;


            case R.id.radio_time_five_BT_for_question_two:
                timeInQuiz = "5000";

                break;

            case R.id.radio_time_twenty_BT_for_question_two:
                timeInQuiz = "20000";

                break;

            case R.id.radio_time_thirty_BT_for_question_two:
                timeInQuiz = "30000";
                break;

            case R.id.radio_five_BT_for_question_three:
                totalQuestion = "5";

                break;

            case R.id.radio_ten_BT_for_question_three:
                totalQuestion = "10";


                break;

            case R.id.radio_fifteen_BT_for_question_three:
                totalQuestion = "15";
                break;

            case R.id.level_one_RB:
                quizLevel = "1";

                break;

            case R.id.level_two_RB:

                quizLevel = "2";

                break;

            case R.id.level_three_RB:

                quizLevel = "3";

                break;

            case R.id.level_four_RB:
                quizLevel = "4";


                break;

            case R.id.level_five_RB:

                quizLevel = "5";

                break;

            case R.id.continue_BT:
                if (scoreEachQuestion.equals("")) {

                    Toast.makeText(this, "How much score each question ?", Toast.LENGTH_SHORT).show();
                } else if (timeInQuiz.equals("")) {
                    Toast.makeText(this, "Time taken by each question (Sec)", Toast.LENGTH_SHORT).show();

                } else if (from.equals("TestSettingCard") && quizLevel.equals("")) {
                    Toast.makeText(this, "Select level ", Toast.LENGTH_SHORT).show();
                }

                   /*else if (totalQuestion.equals("")){
                    Toast.makeText(this, "How many questions ?", Toast.LENGTH_SHORT).show();
                }*/
                else {
                    if (from.equals("TestSettingCard")) {
                        SharedPreferences.Editor editor;
                        SharedPreferences sharedPreferences = TestSettingActivity.this.getSharedPreferences("DataBase", 0);
                        editor = sharedPreferences.edit();
                        editor.putString("timeInQuiz", timeInQuiz);
                        editor.putString("quizLevel", quizLevel);
                        editor.putString("scoreEachQuestion", scoreEachQuestion);
//                    editor.putString("totalQuestion", totalQuestion);
                        editor.apply();
                        finish();
                    }else {
                        SharedPreferences.Editor editor;
                        SharedPreferences sharedPreferences = TestSettingActivity.this.getSharedPreferences("DataBase", 0);
                        editor = sharedPreferences.edit();
                        editor.putString("timeInQuiz", timeInQuiz);
                        editor.putString("scoreEachQuestion", scoreEachQuestion);
                        startActivity(new Intent(this,CreateTestSectionActivity.class));

                    }

                }
                break;

            case R.id.back_arrow_CV:
                onBackPressed();
                break;


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String score = data.getStringExtra("Score");
                Log.e("SCORE", score);

                showScoreDialog(score);
            }
        }
    }

    private void showScoreDialog(String score) {
        successfully_dialog = new Dialog(this);
        successfully_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        successfully_dialog.setCancelable(false);
        successfully_dialog.setContentView(R.layout.total_score_dialog);
        successfully_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView done_TV = successfully_dialog.findViewById(R.id.done_TV);
        TextView total_score_TV = successfully_dialog.findViewById(R.id.total_score_TV);

        total_score_TV.setText(score);
        successfully_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        done_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                successfully_dialog.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = successfully_dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        successfully_dialog.show();

    }
}