package com.example.ansopedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ansopedia.Model.SharedPrefManager;
import com.example.ansopedia.Model.UserModel;

public class MyProfileActivity extends AppCompatActivity {
    private TextView tvUserName, tvUserEmail;
    private Button logoutButton;
    private LinearLayout leadershipBoard, badges, notes, editProfile, llEditSetting;
    UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        intiViews();
        userModel = SharedPrefManager.getInstance(this).getUser();
        tvUserName.setText(userModel.getUserName());
        tvUserEmail.setText(userModel.getEmail());

        logoutButton.setOnClickListener(new View.OnClickListener() {
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
        leadershipBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyProfileActivity.this,LeaderboardActivity.class));
            }
        });
        badges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyProfileActivity.this, BadgeActivity.class));
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyProfileActivity.this, EditProfileActivity.class));
            }
        });
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyProfileActivity.this, "Notes Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
        

    }

    private void intiViews() {
        tvUserName = findViewById(R.id.userName);
        tvUserEmail = findViewById(R.id.userEmail);
        logoutButton = findViewById(R.id.logoutButton);
        llEditSetting = findViewById(R.id.llEditSetting);
        leadershipBoard = findViewById(R.id.leadershipBoard);
        badges = findViewById(R.id.badges);
        notes = findViewById(R.id.notes);
        editProfile = findViewById(R.id.editProfile);
    }
}