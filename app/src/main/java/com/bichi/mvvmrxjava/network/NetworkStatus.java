package com.bichi.mvvmrxjava.network;

import com.bichi.mvvmrxjava.utils.Status;

public class NetworkStatus {
    Status status;
    String message;

    NetworkStatus(Status status,String message){
        this.status = status;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public static NetworkStatus LOADING(){
        return new NetworkStatus(Status.LOADING,"Loading");
    }

    public static NetworkStatus LOADED(){
        return new NetworkStatus(Status.LOADED,"Loaded");
    }

    public static NetworkStatus FAIL(){
        return new NetworkStatus(Status.FAIL,"Error");
    }
}
