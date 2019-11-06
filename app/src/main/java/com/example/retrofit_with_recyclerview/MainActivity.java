package com.example.retrofit_with_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.retrofit_with_recyclerview.retrofit.PostEndPoint;
import com.example.retrofit_with_recyclerview.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Post> postList;
    private PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initRecyclerView();

        getAllPost();
    }

    private void getAllPost() {

        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        PostEndPoint apiService = retrofit.create(PostEndPoint.class);

        Call<List<Post>> call = apiService.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.code() == 200){
                    List<Post> posts = response.body();
                    postList.addAll(posts);
                    adapter.notifyDataSetChanged();

                }else {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        postList = new ArrayList<>();
        adapter = new PostAdapter(postList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
