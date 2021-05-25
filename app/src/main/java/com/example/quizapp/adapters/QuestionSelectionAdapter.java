package com.example.quizapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.adapterClicks.OnQuestionRadioButtonClick;
import com.example.quizapp.adapterClicks.OnRadioButtonClick;
import com.example.quizapp.adapterClicks.QuestionClick;
import com.example.quizapp.questionModel.Question;

import java.util.ArrayList;

public class QuestionSelectionAdapter extends RecyclerView.Adapter<QuestionSelectionAdapter.MyViewHolder> {
    Context context;
    ArrayList<Question> questionListArrayList;
    OnQuestionRadioButtonClick onRadioButtonClick;

    public QuestionSelectionAdapter(Context context, ArrayList<Question> questionListArrayList, OnQuestionRadioButtonClick onRadioButtonClick) {
        this.context = context;
        this.questionListArrayList = questionListArrayList;
        this.onRadioButtonClick = onRadioButtonClick;
    }

    @NonNull
    @Override
    public QuestionSelectionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_selection_click, parent, false);
        QuestionSelectionAdapter.MyViewHolder vh = new QuestionSelectionAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionSelectionAdapter.MyViewHolder holder, final  int position) {
        holder.question_TV.setText("" + (1 + position) + ".  " + questionListArrayList.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return questionListArrayList.size();

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        RadioButton question_TV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question_TV = itemView.findViewById(R.id.question_TV);

            question_TV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRadioButtonClick != null) {
                        onRadioButtonClick.onRadioButtonClickClick(getAdapterPosition());
                    }
                }
            });

        }
    }
}