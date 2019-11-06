package com.example.retrofit_with_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> postList;
    private Context context;

    public PostAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_post_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Post post = postList.get(position);
        holder.titleTV.setText(post.getTitle());
        holder.bodyTV.setText(post.getBody());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTV, bodyTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.titleTV);
            bodyTV  = itemView.findViewById(R.id.bodyTV);
        }
    }
}
