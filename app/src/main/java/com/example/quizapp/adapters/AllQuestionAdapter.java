package com.example.quizapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.adapterClicks.QuestionClick;
import com.example.quizapp.questionModel.Question;

import java.util.ArrayList;

public class AllQuestionAdapter extends RecyclerView.Adapter<AllQuestionAdapter.MyViewHolder> {
    Context context;
    ArrayList<Question> questionListArrayList;
    QuestionClick questionClick;

    public AllQuestionAdapter(Context context, ArrayList<Question> questionListArrayList, QuestionClick questionClick) {
        this.context = context;
        this.questionListArrayList = questionListArrayList;
        this.questionClick = questionClick;
    }

    @NonNull
    @Override
    public AllQuestionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_question_list, parent, false);
        AllQuestionAdapter.MyViewHolder vh = new AllQuestionAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AllQuestionAdapter.MyViewHolder holder, final  int position) {

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
        TextView question_TV;
        ImageView delete_icon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question_TV = itemView.findViewById(R.id.question_TV);
            delete_icon = itemView.findViewById(R.id.delete_icon);

            question_TV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (questionClick != null) {
                        questionClick.onItemClick(getAdapterPosition());
                    }
                }
            });


            delete_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (questionClick != null) {
                        questionClick.onItemDelete(getAdapterPosition());
                    }
                }
            });


        }
    }
}