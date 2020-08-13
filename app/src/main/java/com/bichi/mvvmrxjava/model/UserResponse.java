package com.bichi.mvvmrxjava.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("results") private List<User> userList;

    public List<User> getPeopleList () { return userList;}

    public void setPeopleList(List<User> mUserList) { this.userList = mUserList ;}
}
