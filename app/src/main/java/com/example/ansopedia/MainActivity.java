package com.example.ansopedia;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.ansopedia.adapters.CategoryAdapter;
import com.example.ansopedia.adapters.TabsAdapter;
import com.example.ansopedia.databinding.ActivityMainBinding;
import com.example.ansopedia.models.CategoriesModel;
import com.example.ansopedia.models.TabsModel;
import com.example.ansopedia.utils.Constants;
import com.google.android.material.navigation.NavigationView;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CategoryAdapter categoryAdapter;
    private ArrayList<CategoriesModel> categorieslist;

    private TabsAdapter tabsAdapter;
    private ArrayList<TabsModel> tabsModelArrayList;



    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerlayout,binding.toolbar,R.string.navigation_open,R.string.navigation_close);

        binding.drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

        initSlider();
        intitCategories();
        intitSubjects();
    }

    private void intitSubjects() {
        tabsModelArrayList = new ArrayList<>();
        tabsAdapter = new TabsAdapter(getApplicationContext(),tabsModelArrayList);

        tabsModelArrayList.add(new TabsModel("Programming",1));
        tabsModelArrayList.add(new TabsModel("Puzzles",2));
        tabsModelArrayList.add(new TabsModel("Aptitude",3));
        tabsModelArrayList.add(new TabsModel("GK",4));
        tabsModelArrayList.add(new TabsModel("Interview",5));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.subjects.setLayoutManager(layoutManager);
        binding.subjects.setAdapter(tabsAdapter);
    }

    private void initSlider() {
        binding.carousel.addData(new CarouselItem("https://images.unsplash.com/photo-1605379399642-870262d3d051?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1206&q=80","Try Now"));
        binding.carousel.addData(new CarouselItem("https://images.unsplash.com/photo-1607252650355-f7fd0460ccdb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80","Try Now"));
        binding.carousel.addData(new CarouselItem("https://images.unsplash.com/photo-1526379095098-d400fd0bf935?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1332&q=80","Try Now"));
        binding.carousel.addData(new CarouselItem("https://images.unsplash.com/photo-1526379095098-d400fd0bf935?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1332&q=80","Try Now"));
        binding.carousel.addData(new CarouselItem("https://images.unsplash.com/photo-1526379095098-d400fd0bf935?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1332&q=80","Try Now"));

    }

    private void intitCategories() {
        categorieslist = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this,categorieslist);

        getCategories();
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);
    }

    private void getCategories() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject mainObj = new JSONObject(response);
                    // if(mainObj.getString("status").equals("success")){
                    JSONArray jsonArray = mainObj.getJSONArray("categories");
                    for (int i = 0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        CategoriesModel category = new CategoriesModel(
                                object.getString("name"),
                                Constants.CATEGORIES_IMAGE_URL+ object.getString("icon"),
                                object.getString("color"),
                                object.getString("brief"),
                                object.getInt("id")
                        );
                        categorieslist.add(category);
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