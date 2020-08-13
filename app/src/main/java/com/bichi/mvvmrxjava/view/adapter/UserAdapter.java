package com.bichi.mvvmrxjava.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bichi.mvvmrxjava.databinding.ItemUserBinding;
import com.bichi.mvvmrxjava.model.User;
import com.bichi.mvvmrxjava.view.UserDetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder>{

    private List<User> userList;
    Context context;

    public UserAdapter(Context context) {
        this.context = context;
        this.userList = new ArrayList<>();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding view = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterViewHolder holder, int position) {
        holder.bindUser(userList.get(position));
        holder.itemView.itemPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("user",userList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemUserBinding itemView;

        public UserAdapterViewHolder(@NonNull ItemUserBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

        public void bindUser(User user) {
            Glide.with(itemView.getRoot()).load(user.picture.large).into(itemView.imagePeople);
            itemView.labelName.setText(user.name.first+" "+user.name.last);
            itemView.labelPhone.setText(user.phone);
            itemView.labelMail.setText(user.email);
        }
    }

}
