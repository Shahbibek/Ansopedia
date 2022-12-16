package com.example.ansopedia;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ansopedia.adapters.CategoryDetailsAdapter;
//import com.example.ansopedia.databinding.ActivityCategoryDetailsBinding;
import com.example.ansopedia.models.CategoriesModel;
import com.example.ansopedia.models.CategoryDetailsModel;
import com.example.ansopedia.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryDetailsActivity extends AppCompatActivity {

    private CategoryDetailsAdapter categoryAdapter;
//    private ArrayList<CategoryDetailsModel> arrayList;
    private ArrayList<CategoryDetailsModel> categoriesList;
    TextView topicsTitle;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private RecyclerView topicLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        topicLists = findViewById(R.id.topiclists);
        topicsTitle = findViewById(R.id.topicsTitle);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        String title = getIntent().getStringExtra("title");
//        String icon = getIntent().getStringExtra("icon");
//        String color = getIntent().getStringExtra("color");
//        String desc = getIntent().getStringExtra("desc");
        String id = getIntent().getStringExtra("id");
        topicsTitle.setText(title);
        initTopics(id);
    }

    private void initTopics(String id) {
        categoriesList = new ArrayList<>();
        categoryAdapter = new CategoryDetailsAdapter(CategoryDetailsActivity.this,categoriesList);

//        categoriesList.add(new CategoryDetailsModel(3, 2, "Sanjay", "This is java Sanjay"));
        getCategories(id);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        topicLists.setLayoutManager(layoutManager);
        topicLists.setAdapter(categoryAdapter);
    }

    private void getCategories(String id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL+"?groupId="+id, new Response.Listener<String>() {
//            @SuppressLint("NotifyDataSetChanged")
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject mainObj = new JSONObject(response);
                    // if(mainObj.getString("status").equals("success")){
                    JSONArray jsonArray = mainObj.getJSONArray("user");
                    for (int i = 0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        CategoryDetailsModel category = new CategoryDetailsModel(
                                object.getInt("id"),
                                object.getInt("parent_id"),
                                object.getString("content"),
                                object.getString("short_desc")
                        );
//                        Toast.makeText(CategoryDetailsActivity.this, object.getString("content"), Toast.LENGTH_SHORT).show();
                        categoriesList.add(category);
                    }
                    categoryAdapter.notifyDataSetChanged();
                    //}
//                    else{
//
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);

    }

}
