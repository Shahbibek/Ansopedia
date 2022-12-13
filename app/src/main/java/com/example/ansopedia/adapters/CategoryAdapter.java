package com.example.ansopedia.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ansopedia.CategoryDetailsActivity;
import com.example.ansopedia.R;
import com.example.ansopedia.databinding.ItemCategoriesBinding;
import com.example.ansopedia.models.CategoriesModel;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private Context context;
    private ArrayList<CategoriesModel> arrayList;

    public CategoryAdapter(Context context, ArrayList<CategoriesModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categories,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        CategoriesModel categoriesModel = arrayList.get(position);
        Glide.with(context)
                .load(categoriesModel.getIcon())
                .into(holder.binding.caticon);
        holder.binding.catcard.setBackgroundColor(Color.parseColor(categoriesModel.getColor()));
        holder.itemView.setOnClickListener(c->{
            Intent intent = new Intent(context, CategoryDetailsActivity.class);
            intent.putExtra("name",categoriesModel.getName());
            intent.putExtra("icon",categoriesModel.getIcon());
            intent.putExtra("color",categoriesModel.getColor());
            intent.putExtra("desc",categoriesModel.getDesc());
            intent.putExtra("id",categoriesModel.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{
        ItemCategoriesBinding binding;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCategoriesBinding.bind(itemView);
        }
    }
}
