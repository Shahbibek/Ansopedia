package com.example.ansopedia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ansopedia.QuizActivity;
import com.example.ansopedia.R;
import com.example.ansopedia.databinding.ActivityQuizBinding;
import com.example.ansopedia.models.QuizModels;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private Context context;
    private ArrayList<QuizModels> quizModelsArrayList;
    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_option,parent,false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        QuizModels quizModels = quizModelsArrayList.get(position);
        holder.options.setText(quizModels.getMap().get("content"));

    }

    @Override
    public int getItemCount() {
        return quizModelsArrayList.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder{

        private TextView options;
        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            options = itemView.findViewById(R.id.options);
        }
    }
}
