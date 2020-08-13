package com.bichi.mvvmrxjava.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.bichi.mvvmrxjava.model.UserResponse;
import com.bichi.mvvmrxjava.network.NetworkStatus;
import com.bichi.mvvmrxjava.network.UserService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.bichi.mvvmrxjava.network.NetworkStatus.FAIL;
import static com.bichi.mvvmrxjava.network.NetworkStatus.LOADED;
import static com.bichi.mvvmrxjava.network.NetworkStatus.LOADING;

public class UserViewModel extends AndroidViewModel {

    public static final String TAG = UserViewModel.class.getSimpleName();

    CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<NetworkStatus> networkStatus = new MutableLiveData<>();
    public MutableLiveData<UserResponse> userData = new MutableLiveData<>();
    private UserService api;

    public UserViewModel(@NonNull Application application, UserService api) {
        super(application);
        this.api = api;
    }

    public void fetchUsersList(){
        networkStatus.postValue(LOADING());
        api.fetchUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(UserResponse userResponse) {
                        userData.postValue(userResponse);
                        networkStatus.postValue(LOADED());
                        Log.d(TAG, "onNext: "+userResponse.getPeopleList().size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        networkStatus.postValue(FAIL());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });


    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
