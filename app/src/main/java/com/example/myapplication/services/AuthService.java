package com.example.myapplication.services;

import com.example.myapplication.model.LoginWrapper;
import com.example.myapplication.model.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth")
    Call<Token> login(@Body LoginWrapper loginWrapper);
}
