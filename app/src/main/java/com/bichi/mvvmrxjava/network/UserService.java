package com.bichi.mvvmrxjava.network;

import com.bichi.mvvmrxjava.model.UserResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

import static com.bichi.mvvmrxjava.utils.Constants.RANDOM_USER_URL;

public interface UserService {
    @GET(RANDOM_USER_URL)
    Observable<UserResponse> fetchUsers();
}
