package com.example.ansopedia;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.ansopedia.adapters.CategoryDetailsAdapter;
import com.example.ansopedia.databinding.ActivityCategoryDetailsBinding;
import com.example.ansopedia.models.CategoryDetailsModel;

import java.util.ArrayList;

public class CategoryDetailsActivity extends AppCompatActivity {

    private CategoryDetailsAdapter adapter;
    private ArrayList<CategoryDetailsModel> arrayList;
    ActivityCategoryDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerlayout,binding.toolbar,R.string.navigation_open,R.string.navigation_close);

        binding.drawerlayout.addDrawerListener(toggle);
        toggle.syncState();


        String name = getIntent().getStringExtra("name");
        String icon = getIntent().getStringExtra("icon");
        String color = getIntent().getStringExtra("color");
        String desc = getIntent().getStringExtra("desc");
        String id = getIntent().getStringExtra("id");

        initTopics();
    }

    private void initTopics() {
        arrayList = new ArrayList<>();
        adapter = new CategoryDetailsAdapter(CategoryDetailsActivity.this,arrayList);

        arrayList.add(new CategoryDetailsModel("Variable",1));
        arrayList.add(new CategoryDetailsModel("Data Type",2));
        arrayList.add(new CategoryDetailsModel("Function",3));
        arrayList.add(new CategoryDetailsModel("Operator",4));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        binding.topiclists.setLayoutManager(layoutManager);
        binding.topiclists.setAdapter(adapter);
    }

}
