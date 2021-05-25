package com.example.quizapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.adapterClicks.OnRadioButtonClick;
import com.example.quizapp.questionModel.Question;

import java.util.ArrayList;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.MyViewHolder> {
    Context context;
    ArrayList<Question> questionListArrayList;
    OnRadioButtonClick onRadioButtonClick;

    public QuestionListAdapter(Context context, ArrayList<Question> questionListArrayList,OnRadioButtonClick onRadioButtonClick) {
        this.context = context;
        this.questionListArrayList = questionListArrayList;
        this.onRadioButtonClick = onRadioButtonClick;

    }

    @NonNull
    @Override
    public QuestionListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list_layout, parent, false);
        QuestionListAdapter.MyViewHolder vh = new QuestionListAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionListAdapter.MyViewHolder holder, final int position) {

        holder.question_TV.setText("" + (1 + position) + ".  " + questionListArrayList.get(position).getQuestion());
        holder.option_one.setText(questionListArrayList.get(position).getMcqWithOptionFour().getAnswerOne());
        holder.option_two.setText(questionListArrayList.get(position).getMcqWithOptionFour().getAnswerTwo());
        holder.option_three.setText(questionListArrayList.get(position).getMcqWithOptionFour().getAnswerThree());
        holder.option_four.setText(questionListArrayList.get(position).getMcqWithOptionFour().getAnswerFour());


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
        RadioButton option_one, option_two, option_three, option_four;
        RadioGroup radio_group;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question_TV = itemView.findViewById(R.id.question_TV);
            option_one = itemView.findViewById(R.id.option_one);
            option_two = itemView.findViewById(R.id.option_two);
            option_three = itemView.findViewById(R.id.option_three);
            option_four = itemView.findViewById(R.id.option_four);
            radio_group = itemView.findViewById(R.id.radio_group);

            option_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRadioButtonClick != null) {
                            onRadioButtonClick.onOptionOneClick(getAdapterPosition(), questionListArrayList.get(getAdapterPosition()).getMcqWithOptionFour().getAnswerOne());
                    }
                }
            });

            option_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRadioButtonClick != null) {
                            onRadioButtonClick.onOnOptionTwoClick(getAdapterPosition(), questionListArrayList.get(getAdapterPosition()).getMcqWithOptionFour().getAnswerTwo());
                    }
                }
            });

            option_three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRadioButtonClick != null) {
                            onRadioButtonClick.onOnOptionThreeClick(getAdapterPosition(), questionListArrayList.get(getAdapterPosition()).getMcqWithOptionFour().getAnswerThree());
                    }
                }
            });

            option_four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRadioButtonClick != null) {
                        onRadioButtonClick.onOnOptionFourClick(getAdapterPosition(), questionListArrayList.get(getAdapterPosition()).getMcqWithOptionFour().getAnswerFour());
                    }
                }
            });


        }
    }
}