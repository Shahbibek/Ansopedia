package com.example.ansopedia;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ansopedia.adapters.CategoryAdapter;
import com.example.ansopedia.adapters.QuestionsAdapter;
import com.example.ansopedia.models.CategoriesModel;
import com.example.ansopedia.models.QuestionsModel;
import com.example.ansopedia.models.QuizModels;
import com.example.ansopedia.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    TextView topicName;
    private QuestionsAdapter questionsAdapter;
    private ArrayList<QuestionsModel> questionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        drawerLayout = findViewById(R.id.quizdrawerlayout);
        toolbar = findViewById(R.id.quiztoolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        String title = getIntent().getStringExtra("title");
        String id = getIntent().getStringExtra("id");

        topicName = findViewById(R.id.topic_name);
        topicName.setText(title);
//        String id = "5";
        inItQuestions(id);

    }


    private void inItQuestions(String id) {
        questionList = new ArrayList<>();
        questionsAdapter = new QuestionsAdapter(this,questionList);
//        categorieslist.add(new CategoriesModel(3, 2, "Sanjay", "This is java Sanjay", "https://spng.pngfind.com/pngs/s/74-744402_java-logo-png-transparent-svg-vector-freebie-supply.png","#f6f6f6"));
        getQuestions(id);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
//        binding.categoriesList.setLayoutManager(layoutManager);
//        binding.categoriesList.setAdapter(categoryAdapter);
    }

    private void getQuestions(String id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_QUESTIONS+"?groupId="+id, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject mainObj = new JSONObject(response);
                    // if(mainObj.getString("status").equals("success")){
                    JSONArray jsonArray = mainObj.getJSONArray("questions");
                    for (int i = 0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        QuestionsModel question = new QuestionsModel(
                                object.getInt("id"),
                                object.getInt("type"),
                                object.getInt("level"),
                                object.getInt("score"),
                                object.getString("description"),
                                object.getString("content")
                        );
                        questionList.add(question);
                    }
                    questionsAdapter.notifyDataSetChanged();
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