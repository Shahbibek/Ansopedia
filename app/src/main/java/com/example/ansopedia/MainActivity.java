package com.example.ansopedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.ansopedia.Model.SharedPrefManager;
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

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerlayout,binding.toolbar,R.string.navigation_open,R.string.navigation_close);

        binding.drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navhome) {
                    Toast.makeText(MainActivity.this, "Home is Clicked", Toast.LENGTH_SHORT).show();
                    binding.drawerlayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.navprofile) {
                    startActivity(new Intent(MainActivity.this, MyProfileActivity.class));
                    binding.drawerlayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.course) {
                    Toast.makeText(MainActivity.this, "Course is Clicked", Toast.LENGTH_SHORT).show();
                    binding.drawerlayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.logout) {
                    Toast.makeText(MainActivity.this, "Logout is Clicked", Toast.LENGTH_SHORT).show();
                    binding.drawerlayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.share) {
                    Toast.makeText(MainActivity.this, "Share is Clicked", Toast.LENGTH_SHORT).show();
                    binding.drawerlayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.aboutus) {
                    startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                    binding.drawerlayout.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });
        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });
        String id = "2";       //To change this value can change the content in the recycler view
        initSlider();
        intitCategories(id);
        intitSubjects();
    }

    private void intitSubjects() {
        tabsModelArrayList = new ArrayList<>();
        tabsAdapter = new TabsAdapter(getApplicationContext(),tabsModelArrayList);

//        tabsModelArrayList.add(new TabsModel(1, "Programming"));
        getTabsContent();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.subjects.setLayoutManager(layoutManager);
        binding.subjects.setAdapter(tabsAdapter);
    }

    private void initSlider() {
        binding.carousel.addData(new CarouselItem("https://ansopedia.com/wp-content/uploads/2022/12/banner_1.png","Try Now"));
        binding.carousel.addData(new CarouselItem("https://ansopedia.com/wp-content/uploads/2022/12/banner_2.png","Try Now"));
    }

    private void intitCategories(String id) {
        categorieslist = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this,categorieslist);
//        categorieslist.add(new CategoriesModel(3, 2, "Sanjay", "This is java Sanjay", "https://spng.pngfind.com/pngs/s/74-744402_java-logo-png-transparent-svg-vector-freebie-supply.png","#f6f6f6"));
        getCategories(id);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);
    }

    private void getCategories(String id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL+"?groupId="+id, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject mainObj = new JSONObject(response);
                    // if(mainObj.getString("status").equals("success")){
                    JSONArray jsonArray = mainObj.getJSONArray("user");
                    for (int i = 0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        CategoriesModel category = new CategoriesModel(
                                object.getInt("id"),
                                object.getInt("parent_id"),
                                object.getString("content"),
                                object.getString("short_desc"),
                                object.getString("image_url"),
                                object.getString("color")
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

    private void getTabsContent() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL+"?groupId=0", new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject mainObj = new JSONObject(response);
                    // if(mainObj.getString("status").equals("success")){
                    JSONArray jsonArray = mainObj.getJSONArray("user");
                    for (int i = 0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        TabsModel category = new TabsModel(
                                object.getInt("id"),
                                object.getString("content")
                        );
                        tabsModelArrayList.add(category);
                    }
                    tabsAdapter.notifyDataSetChanged();
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