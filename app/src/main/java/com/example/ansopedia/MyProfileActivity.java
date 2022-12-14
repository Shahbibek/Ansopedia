package com.example.ansopedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ansopedia.Model.SharedPrefManager;
import com.example.ansopedia.Model.UserModel;

public class MyProfileActivity extends AppCompatActivity {
    private TextView tvUserName, tvUserEmail;
    private Button btnLogout;
    private LinearLayout llEditSetting;
    UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        intiViews();
        userModel = SharedPrefManager.getInstance(this).getUser();
        tvUserName.setText(userModel.getUserName());
        tvUserEmail.setText(userModel.getEmail());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(MyProfileActivity.this).logout();
            }
        });

        llEditSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyProfileActivity.this, EditProfileActivity.class));
                finish();
            }
        });
    }

    private void intiViews() {
        tvUserName = findViewById(R.id.userName);
        tvUserEmail = findViewById(R.id.userEmail);
        btnLogout = findViewById(R.id.logoutButton);
        llEditSetting = findViewById(R.id.llEditSetting);
    }
}