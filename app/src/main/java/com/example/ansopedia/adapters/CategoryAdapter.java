package com.example.ansopedia.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ansopedia.CategoryDetailsActivity;
import com.example.ansopedia.R;
import com.example.ansopedia.databinding.ItemCategoriesBinding;
import com.example.ansopedia.models.CategoriesModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private Context context;
    private List<CategoriesModel> arrayList;

    public CategoryAdapter(Context context, List<CategoriesModel> arrayList) {
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
//        Glide.with(context)
//                .load(categoriesModel.getImage_url())
//                .into(holder.binding.caticon);



        // App Crash due to this line


        holder.txtCategoryTitle.setText(arrayList.get(position).getContent());
//        holder.txtProductPrice.setText(""+productModelArrayList.get(position).getPrice());
        // Picasso.get().load(productModelArrayList.get(position).getImage()).resize(100,100).into(holder.imageProduct);
        Picasso.get().load(arrayList.get(position).getImage_url()).resize(100,100).into(holder.catIcon);
//        Picasso.get().load(arrayList.get(position).getColor()).into(holder.catIcon);
        if(categoriesModel.getColor().isEmpty()){

        }else{
            holder.catCard.setBackgroundColor(Color.parseColor(categoriesModel.getColor()));
        }

        holder.itemView.setOnClickListener(c->{
            Intent intent = new Intent(context, CategoryDetailsActivity.class);
            intent.putExtra("title",categoriesModel.getContent());
//            intent.putExtra("icon",categoriesModel.getImage_url());
//            intent.putExtra("color",categoriesModel.getColor());
//            intent.putExtra("desc",categoriesModel.getDesc());
            intent.putExtra("id",""+categoriesModel.getId());
//            Toast.makeText(context, ""+categoriesModel.getId(), Toast.LENGTH_SHORT).show();
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{
        TextView txtCategoryTitle;
        ImageView catIcon;
        ConstraintLayout catCard;


        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            txtCategoryTitle = itemView.findViewById(R.id.txtCategoryTitle);
            catIcon = itemView.findViewById(R.id.caticon);
            catCard = itemView.findViewById(R.id.catcard);

        }
    }
}
