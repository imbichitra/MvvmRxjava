package com.bichi.mvvmrxjava.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bichi.mvvmrxjava.databinding.ActivityUserDetailBinding;
import com.bichi.mvvmrxjava.model.User;
import com.bumptech.glide.Glide;

public class UserDetailActivity extends AppCompatActivity {

    ActivityUserDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.toolbar);

        getExtrasFromIntent();
    }

    private void getExtrasFromIntent(){
        User user = (User) getIntent().getSerializableExtra("user");
        setTitle(user.name.title + "." + user.name.first + " " + user.name.last);

        Glide.with(this).load(user.picture.large).into(binding.profileImage);
        binding.refLayout.userName.setText(user.login.username);
        binding.refLayout.phoneNumber.setText(user.phone);
        binding.refLayout.email.setText(user.email);
        binding.refLayout.gender.setText(user.gender);
        binding.refLayout.business.setText(getAddress(user));
    }
    public String getAddress(User user) {
        return user.location.city
                + " "
                + user.location.state;
    }
}