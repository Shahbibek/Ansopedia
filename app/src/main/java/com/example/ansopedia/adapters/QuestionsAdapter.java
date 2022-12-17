package com.example.ansopedia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ansopedia.R;
import com.example.ansopedia.models.CategoryDetailsModel;
import com.example.ansopedia.models.QuestionsModel;

import java.util.ArrayList;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsHolder>{


    private Context context;
    private ArrayList<QuestionsModel> questionsModelArrayList;

    public QuestionsAdapter(Context context, ArrayList<QuestionsModel> questionsModelArrayList) {
        this.context = context;
        this.questionsModelArrayList = questionsModelArrayList;
    }
    @NonNull
    @Override
    public QuestionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topics,parent,false);
        return new QuestionsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsAdapter.QuestionsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return questionsModelArrayList.size();
    }

    public class QuestionsHolder extends RecyclerView.ViewHolder{
        public QuestionsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
