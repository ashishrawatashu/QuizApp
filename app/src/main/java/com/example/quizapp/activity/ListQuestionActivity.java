package com.example.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quizapp.Methods;
import com.example.quizapp.SingleTon;

import com.example.quizapp.adapterClicks.QuestionClick;
import com.example.quizapp.adapters.AllQuestionAdapter;
import com.example.quizapp.R;
import com.example.quizapp.questionModel.Question;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class ListQuestionActivity extends AppCompatActivity implements View.OnClickListener, QuestionClick {

    Methods methods;
    AllQuestionAdapter allQuestionAdapter;
    RecyclerView question_list_RV;
    MaterialCardView back_arrow_CV;
    ArrayList<Question> shuffleAllTheQuestionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_question);

        methods = new Methods(this);

        question_list_RV = findViewById(R.id.question_list_RV);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);

        back_arrow_CV.setOnClickListener(this);
//
//        shuffleAllTheQuestionList = SingleTon.getInstance().questionArrayList;
//        Collections.shuffle(shuffleAllTheQuestionList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        question_list_RV.setLayoutManager(linearLayoutManager);
        allQuestionAdapter = new AllQuestionAdapter(this, SingleTon.getInstance().questionArrayList, this);
        question_list_RV.setAdapter(allQuestionAdapter);
        allQuestionAdapter.notifyDataSetChanged();

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        question_list_RV.setLayoutManager(linearLayoutManager);
//        allQuestionAdapter = new AllQuestionAdapter(this, shuffleAllTheQuestionList, this);
//        question_list_RV.setAdapter(allQuestionAdapter);
//        allQuestionAdapter.notifyDataSetChanged();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrow_CV:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent updateQuestionIntent = new Intent(this,AddQuestionActivity.class);
        updateQuestionIntent.putExtra("From","ListQuestionActivity");
        updateQuestionIntent.putExtra("Position",position);
        startActivity(updateQuestionIntent);

    }

    @Override
    public void onItemDelete(int position) {
            androidx.appcompat.app.AlertDialog.Builder confirmationDialog = new androidx.appcompat.app.AlertDialog.Builder(this);
            confirmationDialog.setTitle("Confirmation!!");
            confirmationDialog.setMessage("Are you want to delete this question ?");
            confirmationDialog.setCancelable(false);

            confirmationDialog.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SingleTon.getInstance().getQuestionArrayList().remove(position);
                            SharedPreferences prefs = getSharedPreferences("DataBase",0);
                            SharedPreferences.Editor editor = prefs.edit();
                            Gson gson1 = new Gson();
                            String json1 = gson1.toJson(SingleTon.getInstance().questionArrayList);
                            editor.putString("QuestionList", json1);
                            Log.e("QuestionList",json1);
                            editor.apply();
                            Toast.makeText(ListQuestionActivity.this, "Question deleted successfully ", Toast.LENGTH_SHORT).show();
                            allQuestionAdapter.notifyDataSetChanged();
                        }
                    });
            confirmationDialog.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            androidx.appcompat.app.AlertDialog alert11 = confirmationDialog.create();
            alert11.show();
    }
}