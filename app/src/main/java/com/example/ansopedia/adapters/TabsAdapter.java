package com.example.ansopedia.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ansopedia.CategoryDetailsActivity;
import com.example.ansopedia.MainActivity;
import com.example.ansopedia.R;
import com.example.ansopedia.databinding.ItemTabsBinding;
import com.example.ansopedia.models.TabsModel;

import java.util.ArrayList;

public class TabsAdapter extends RecyclerView.Adapter<TabsAdapter.TabsViewHolder>{

    private Context context;
    private ArrayList<TabsModel> tabsModelArrayList;

    public TabsAdapter(Context context, ArrayList<TabsModel> tabsModelArrayList) {
        this.context = context;
        this.tabsModelArrayList = tabsModelArrayList;
    }

    @NonNull
    @Override
    public TabsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tabs,parent,false);
        return new TabsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabsViewHolder holder, int position) {
        TabsModel tabsModel = tabsModelArrayList.get(position);
        holder.binding.tabText.setText(tabsModel.getContent());
        holder.itemView.setOnClickListener(c->{
            holder.binding.tabText.setTextColor(Color.parseColor("#00FF00"));

        });
    }

    @Override
    public int getItemCount() {
        return tabsModelArrayList.size();
    }




    public class TabsViewHolder extends RecyclerView.ViewHolder{

        ItemTabsBinding binding;
        public TabsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemTabsBinding.bind(itemView);
        }
    }
}
