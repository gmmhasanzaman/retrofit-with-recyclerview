package com.example.retrofit_with_recyclerview.retrofit;

import com.example.retrofit_with_recyclerview.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostEndPoint {

    @GET("posts")
    Call<List<Post>> getPosts();
}
