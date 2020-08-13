package com.bichi.mvvmrxjava.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bichi.mvvmrxjava.databinding.ActivityUsersBinding;
import com.bichi.mvvmrxjava.network.ApiFactory;
import com.bichi.mvvmrxjava.network.UserService;
import com.bichi.mvvmrxjava.utils.Status;
import com.bichi.mvvmrxjava.view.adapter.UserAdapter;
import com.bichi.mvvmrxjava.viewModel.MyViewModelFactory;
import com.bichi.mvvmrxjava.viewModel.UserViewModel;

public class UsersActivity extends AppCompatActivity {
    public static final String TAG = UsersActivity.class.getSimpleName();
    UserViewModel userViewModel;
    ActivityUsersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUsersBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        UserService api = ApiFactory.create();
        MyViewModelFactory factory = new MyViewModelFactory(this.getApplication(),api);
        userViewModel = new ViewModelProvider(this,factory).get(UserViewModel.class);

        UserAdapter adapter = new UserAdapter(this);
        binding.listUser.setAdapter(adapter);
        binding.listUser.setLayoutManager(new LinearLayoutManager(this));

        userViewModel.fetchUsersList();

        userViewModel.networkStatus.observe(this, networkStatus -> {
            if (networkStatus.getStatus() == Status.LOADING) {
                binding.progressLayout.setVisibility(View.VISIBLE);
                binding.labelStatus.setText(networkStatus.getMessage());
                Log.d(TAG, "onChanged: " + networkStatus.getStatus() + " " + networkStatus.getMessage());
            }else {
                binding.progressLayout.setVisibility(View.GONE);
            }
        });

        userViewModel.userData.observe(this, userResponse -> {
            adapter.setUserList(userResponse.getPeopleList());
        });
    }
}