package com.bichi.mvvmrxjava.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bichi.mvvmrxjava.network.UserService;

public class MyViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private UserService api;

    public MyViewModelFactory(Application application,UserService api){
        this.application = application;
        this.api = api;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserViewModel(application,api);
    }
}
