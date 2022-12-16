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

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
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
import com.example.ansopedia.fragments.AboutUsFragment;
import com.example.ansopedia.models.CategoriesModel;
import com.example.ansopedia.models.TabsModel;
import com.example.ansopedia.utils.Constants;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private CategoryAdapter categoryAdapter;
    private List<CategoriesModel> categorieslist;

    private TabsAdapter tabsAdapter;
    private ArrayList<TabsModel> tabsModelArrayList;


    ActivityMainBinding binding;
    private String baseUrl = "https://api.ansopedia.com/api/content/getCategory.php/";
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

//        binding.drawerlayout.setDrawerListener(toggle);
        binding.drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

//        NavigationView navigationView1 = findViewById(R.id.navigationview);

//        binding.navigationview.setNavigationItemSelectedListener();


        initSlider();
        intitCategories();
        intitSubjects();

        binding.navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navhome:
                        Toast.makeText(getApplicationContext(), "Home clicked...", Toast.LENGTH_LONG).show();
                        binding.drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navprofile:
                        Toast.makeText(getApplicationContext(), "Home clicked...", Toast.LENGTH_LONG).show();
                        binding.drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.course:
                        Toast.makeText(getApplicationContext(), "Home clicked...", Toast.LENGTH_LONG).show();
                        binding.drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:
                        Toast.makeText(getApplicationContext(), "Home clicked...", Toast.LENGTH_LONG).show();
                        binding.drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.share:
                        Toast.makeText(getApplicationContext(), "Home clicked...", Toast.LENGTH_LONG).show();
                        binding.drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.aboutus:
                        Toast.makeText(getApplicationContext(), "Home clicked...", Toast.LENGTH_LONG).show();
                        binding.drawerlayout.closeDrawer(GravityCompat.START);
                        break;

                }
                return true;
            }
        });

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

        getCategories();
        categoryAdapter = new CategoryAdapter(this,categorieslist);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);
    }


//   private void getCategories() {
//         Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
//
//        MyApi api = retrofit.create(MyApi.class);
//        Call<List<CategoriesModel>> call = api.getModels();
//
//
//        call.enqueue(new Callback<List<CategoriesModel>>() {
//            @Override
//            public void onResponse(Call<List<CategoriesModel>> call, Response<List<CategoriesModel>> response) {
//                categorieslist = response.body();
//
//                for (int i = 0; i < categorieslist.size(); i++){
//
//                    CategoriesModel categoriesModel = new CategoriesModel(
//                            categorieslist.get(i).getName(),
//                            categorieslist.get(i).getName(),
//                            categorieslist.get(i).getDesc(),
//                            categorieslist.get(i).getId()
//                    );
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<CategoriesModel>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Not able to fetch data...", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }


    //this is previous code
//    it will work fine but api used is different

    private void getCategories() {
        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//
//                    //}
////                    else{
////
////                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
        StringRequest  request = new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject mainObj = new JSONObject(response);
                    // if(mainObj.getString("status").equals("success")){
                    JSONArray jsonArray = mainObj.getJSONArray("categories");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        CategoriesModel category = new CategoriesModel(
                                object.getString("name"),
                                Constants.CATEGORIES_IMAGE_URL + object.getString("icon"),
                                object.getString("color"),
                                object.getString("brief"),
                                object.getInt("id")
                        );
                        categorieslist.add(category);
                    }
                    categoryAdapter.notifyDataSetChanged();
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




    //    when clicked on back button make sure that activity is not gone to back state if navigation drawer is open
    @Override
    public void onBackPressed() {
        if(binding.drawerlayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerlayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////        item.setChecked(true);
//        binding.framelayout.setVisibility(View.VISIBLE);
//        switch (item.getItemId()){
//            case R.id.navhome:
//                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new AboutUsFragment()).commit();
////                Toast.makeText(this, "clicked on home", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.navprofile:
//                Toast.makeText(this, "clicked on profile", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.course:
//                Toast.makeText(this, "courses", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.logout:
//                Toast.makeText(this, "clicked on logout...", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.share:
//                Toast.makeText(this, "clicked on share...", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                Toast.makeText(this, "wrong option...", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return true;
//    }
}


