package com.example.ansopedia.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ansopedia.CategoryDetailsActivity;
import com.example.ansopedia.QuizActivity;
import com.example.ansopedia.R;
import com.example.ansopedia.models.CategoryDetailsModel;

import java.util.ArrayList;

public class CategoryDetailsAdapter extends RecyclerView.Adapter<CategoryDetailsAdapter.CategoryDetailsHolder>{

    private Context context;
    private ArrayList<CategoryDetailsModel> categoryDetailsModelArrayList;

    public CategoryDetailsAdapter(Context context, ArrayList<CategoryDetailsModel> categoryDetailsModelArrayList) {
        this.context = context;
        this.categoryDetailsModelArrayList = categoryDetailsModelArrayList;
    }

    @NonNull
    @Override
    public CategoryDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topics,parent,false);
        return new CategoryDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryDetailsHolder holder, int position) {

        CategoryDetailsModel categoryDetailsModel = categoryDetailsModelArrayList.get(position);
//        holder.bindings.topiclists

        holder.topicText.setText(categoryDetailsModel.getContent());
        holder.itemView.setOnClickListener(c->{
            Intent intent = new Intent(context, QuizActivity.class);
            intent.putExtra("title",categoryDetailsModel.getContent());
            intent.putExtra("id",""+categoryDetailsModel.getId());
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return categoryDetailsModelArrayList.size();
    }

    public class CategoryDetailsHolder extends RecyclerView.ViewHolder{

//        ActivityCategoryDetailsBinding bindings;
        TextView topicText;
        public CategoryDetailsHolder(@NonNull View itemView) {
            super(itemView);
            topicText = (TextView) itemView.findViewById(R.id.topicText);
        }
    }
}
