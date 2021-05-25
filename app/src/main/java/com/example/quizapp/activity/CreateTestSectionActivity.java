package com.example.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.Methods;
import com.example.quizapp.SingleTon;
import com.example.quizapp.adapterClicks.OnQuestionRadioButtonClick;
import com.example.quizapp.adapterClicks.OnRadioButtonClick;
import com.example.quizapp.adapters.QuestionListAdapter;
import com.example.quizapp.R;
import com.example.quizapp.adapters.QuestionSelectionAdapter;
import com.example.quizapp.questionModel.Question;
import com.google.android.material.card.MaterialCardView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class CreateTestSectionActivity extends AppCompatActivity implements View.OnClickListener, OnRadioButtonClick, OnQuestionRadioButtonClick {
    Dialog successfully_dialog;

    Methods methods;
    QuestionListAdapter questionListAdapter;
    RecyclerView question_list_RV;
    TextView top_timer_TV, timer_center_TV;
    MaterialCardView timer_CV, back_arrow_CV;
    Button continue_BT, save_BT;
    CountDownTimer countDownTimer1, countDownTimer2;
    //    int totalQuestion;
    ArrayList<Question> finalQuestionForExam = new ArrayList<>();
    ArrayList<Question> shuffleAllTheQuestionList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    ConstraintLayout toolbar, information_CL;

    String stringToFile;

    TextView date_TV, totalTime_TV, total_marks_TV, level_TV;
    String totalString = "";
    File file;
    QuestionSelectionAdapter questionSelectionAdapter;
    private static final String FILE_NAME = "example.txt";
    String date, finalDataFromAllquetsion;
    int totalScore = 00;
    boolean isEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_dashboard);
        methods = new Methods(this);
        sharedPreferences = getSharedPreferences("DataBase", 0);

        SingleTon.getInstance().quizLevel = Integer.parseInt(sharedPreferences.getString("quizLevel", ""));
        SingleTon.getInstance().scoreEachQuestion = Integer.parseInt(sharedPreferences.getString("scoreEachQuestion", ""));
        SingleTon.getInstance().timeInQuiz = Long.parseLong(sharedPreferences.getString("timeInQuiz", ""));
//        totalQuestion = Integer.parseInt(sharedPreferences.getString("totalQuestion", ""));

        Log.e("DataBase", "quizLevel " + SingleTon.getInstance().quizLevel + " scoreEachQuestion " + SingleTon.getInstance().scoreEachQuestion + " timeInQuiz " + SingleTon.getInstance().timeInQuiz);

        question_list_RV = findViewById(R.id.question_list_RV);
        timer_center_TV = findViewById(R.id.timer_center_TV);
        top_timer_TV = findViewById(R.id.top_timer_TV);
        timer_CV = findViewById(R.id.timer_CV);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        continue_BT = findViewById(R.id.continue_BT);
        date_TV = findViewById(R.id.date_TV);
        totalTime_TV = findViewById(R.id.totalTime_TV);
        total_marks_TV = findViewById(R.id.total_marks_TV);
        level_TV = findViewById(R.id.level_TV);
        level_TV = findViewById(R.id.level_TV);
        save_BT = findViewById(R.id.save_BT);
        continue_BT.setOnClickListener(this);
        back_arrow_CV.setOnClickListener(this);
        save_BT.setOnClickListener(this);

        shuffleAllTheQuestionList = SingleTon.getInstance().questionArrayList;
        Collections.shuffle(shuffleAllTheQuestionList);

        for (int i = 0; i < SingleTon.getInstance().questionArrayList.size(); i++) {
            if (SingleTon.getInstance().questionArrayList.get(i).getQuestionLevel() == SingleTon.getInstance().quizLevel) {
                Question question = new Question(shuffleAllTheQuestionList.get(i).getQuestion(), shuffleAllTheQuestionList.get(i).getAnswer(), shuffleAllTheQuestionList.get(i).getMcqWithOptionFour(), shuffleAllTheQuestionList.get(i).getQuestionLevel(), shuffleAllTheQuestionList.get(i).getOption());
                finalQuestionForExam.add(question);
            }
        }


        Collections.shuffle(finalQuestionForExam);
        Log.e("size", String.valueOf(finalQuestionForExam.size()));

        String time = String.valueOf(SingleTon.getInstance().timeInQuiz);
        date_TV.setText(methods.getDate2());
        level_TV.setText("" + SingleTon.getInstance().quizLevel);
        total_marks_TV.setText(finalQuestionForExam.size() * SingleTon.getInstance().scoreEachQuestion + "");
        totalTime_TV.setText(time.substring(0, time.length() - 3));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        question_list_RV.setLayoutManager(linearLayoutManager);
//        questionListAdapter = new QuestionListAdapter(this, finalQuestionForExam, this);
//        question_list_RV.setAdapter(questionListAdapter);
//        questionListAdapter.notifyDataSetChanged();

        questionSelectionAdapter = new QuestionSelectionAdapter(this, finalQuestionForExam, this);
        question_list_RV.setAdapter(questionSelectionAdapter);
        questionSelectionAdapter.notifyDataSetChanged();
        for (int position = 0; position < finalQuestionForExam.size(); position++) {
            totalString = totalString + "\n" + (position + 1) + ". " + finalQuestionForExam.get(position).getQuestion() + "\n" +
                    "1." + finalQuestionForExam.get(position).getMcqWithOptionFour().getAnswerOne() + "\n" +
                    "2." + finalQuestionForExam.get(position).getMcqWithOptionFour().getAnswerTwo() + "\n" +
                    "3." + finalQuestionForExam.get(position).getMcqWithOptionFour().getAnswerThree() + "\n" +
                    "4." + finalQuestionForExam.get(position).getMcqWithOptionFour().getAnswerFour() + "\n";
//                    "Answer: " + finalQuestionForExam.get(position).getAnswer() + "\n";
        }


//        countDownTimer1 = new CountDownTimer(5000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                timer_center_TV.setText("" + millisUntilFinished / 1000);
//                //here you can have your logic to set text to edittext
//            }
//
//            public void onFinish() {
//                timer_center_TV.setVisibility(View.GONE);
//                question_list_RV.setVisibility(View.VISIBLE);
//                timer_CV.setVisibility(View.VISIBLE);
//                setTimer();
//            }
//
//        }.start();


    }

    private void setTimer() {
        countDownTimer2 = new CountDownTimer(SingleTon.getInstance().timeInQuiz, 1000) {

            public void onTick(long millisUntilFinished) {
                top_timer_TV.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                for (int i = 0; i < finalQuestionForExam.size(); i++) {
                    if (finalQuestionForExam.get(i).isAnswer()) {
                        totalScore = totalScore + SingleTon.getInstance().scoreEachQuestion;
                    }
                }

                showScoreDialog(totalScore + "/" + finalQuestionForExam.size() * SingleTon.getInstance().scoreEachQuestion);

                continue_BT.setVisibility(View.VISIBLE);
                stringToFile = "Date : " + methods.getDate2() + "\n" + "Score : " + totalScore + "/" + finalQuestionForExam.size() * SingleTon.getInstance().scoreEachQuestion + "\n" + "Level : " + SingleTon.getInstance().quizLevel + "\n" + totalString;
                Log.e("STRING", stringToFile);
                writeToFile(stringToFile);
                Log.e("SCORE", "" + totalScore);
            }

        }.start();
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
        try {
            successfully_dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void writeToFile(String data) {
        try {
            date = DateFormat.format("MM-dd-yyyyy-h-mmssaa", System.currentTimeMillis()).toString();
            File root = new File(Environment.getExternalStorageDirectory(), "QuizApp");
            if (!root.exists()) {
                root.mkdirs(); // this will create folder.
            }
            Log.e("File", root.getAbsolutePath());
            File file = new File(root, date + ".txt"); // file path to save
            FileWriter writer = new FileWriter(file);
            writer.append(data);
            writer.flush();
            writer.close();
            SingleTon.getInstance().fileName = date + ".txt";
            Log.e("FileNMAe", SingleTon.getInstance().fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String readFromFile(Context context) {
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrow_CV:
                onBackPressed();
                break;

            case R.id.continue_BT:
                getAllDataForTextFile();
                if (isEmpty) {
                    Toast.makeText(this, "Please select questions !", Toast.LENGTH_SHORT).show();

                } else {
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_SUBJECT, "Question");
                    email.putExtra(Intent.EXTRA_TEXT, finalDataFromAllquetsion);
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                    Log.e("READ", readFromFile(this));
                }

                break;

            case R.id.save_BT:
                getAllDataForTextFile();
                writeToFile(finalDataFromAllquetsion);
                Log.e("SCORE", "" + totalScore);
                if (isEmpty) {
                    Toast.makeText(this, "Please select questions !", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "File saved successfully", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void getAllDataForTextFile() {
        ArrayList<Question> selectedQuestions = new ArrayList<>();

        for (int i = 0; i < finalQuestionForExam.size(); i++) {

            if (finalQuestionForExam.get(i).isSaveInFile()) {
                Question question = new Question(finalQuestionForExam.get(i).getQuestion(), finalQuestionForExam.get(i).getAnswer(), finalQuestionForExam.get(i).getMcqWithOptionFour(), finalQuestionForExam.get(i).getQuestionLevel(), finalQuestionForExam.get(i).getOption());
                selectedQuestions.add(question);
            }
        }

        String finalString = " ";
        for (int position = 0; position < selectedQuestions.size(); position++) {
            finalString = finalString + "\n" + (position + 1) + ". " + selectedQuestions.get(position).getQuestion() + "\n" +
                    "1." + selectedQuestions.get(position).getMcqWithOptionFour().getAnswerOne() + "\n" +
                    "2." + selectedQuestions.get(position).getMcqWithOptionFour().getAnswerTwo() + "\n" +
                    "3." + selectedQuestions.get(position).getMcqWithOptionFour().getAnswerThree() + "\n" +
                    "4." + selectedQuestions.get(position).getMcqWithOptionFour().getAnswerFour() + "\n";
        }
        finalDataFromAllquetsion = "Date : " + methods.getDate2() + "\n" + "Score : " + totalScore + "/" + selectedQuestions.size() * SingleTon.getInstance().scoreEachQuestion + "\n" + "Level : " + SingleTon.getInstance().quizLevel + "\n" + finalString;
        Log.e("STRING", finalDataFromAllquetsion);
        if (selectedQuestions.isEmpty()) {
            isEmpty = true;
        }else {
            isEmpty = false;
        }

    }

    @Override
    public void onOptionOneClick(int position, String answer) {
        if (finalQuestionForExam.get(position).getAnswer().equals(answer)) {
            finalQuestionForExam.get(position).setAnswer(true);
        } else {
            finalQuestionForExam.get(position).setAnswer(false);
        }
    }

    @Override
    public void onOnOptionTwoClick(int position, String answer) {
        if (finalQuestionForExam.get(position).getAnswer().equals(answer)) {
            finalQuestionForExam.get(position).setAnswer(true);
        } else {
            finalQuestionForExam.get(position).setAnswer(false);
        }
    }

    @Override
    public void onOnOptionThreeClick(int position, String answer) {
        if (finalQuestionForExam.get(position).getAnswer().equals(answer)) {
            finalQuestionForExam.get(position).setAnswer(true);
        } else {
            finalQuestionForExam.get(position).setAnswer(false);
        }
    }

    @Override
    public void onOnOptionFourClick(int position, String answer) {
        if (finalQuestionForExam.get(position).getAnswer().equals(answer)) {
            finalQuestionForExam.get(position).setAnswer(true);
        } else {
            finalQuestionForExam.get(position).setAnswer(false);
        }
    }

    @Override
    public void onRadioButtonClickClick(int position) {
        if (finalQuestionForExam.get(position).isSaveInFile()) {
            finalQuestionForExam.get(position).setSaveInFile(false);
        } else {
            finalQuestionForExam.get(position).setSaveInFile(true);
        }
    }
}