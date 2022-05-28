package com.example.libraryapplicationcoursework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapplicationcoursework.R;
import com.example.libraryapplicationcoursework.entity.Book;
import com.example.libraryapplicationcoursework.entity.FavoriteBook;

import java.util.List;

public class FavoriteBookListAdapter extends RecyclerView.Adapter<FavoriteBookListAdapter.MyViewHolder> {

    private Context context;
    private List<FavoriteBook> favoriteBooks;

    public FavoriteBookListAdapter() {
    }

    public FavoriteBookListAdapter(Context context, List<FavoriteBook> favoriteBooks) {
        this.context = context;
        this.favoriteBooks = favoriteBooks;
        notifyDataSetChanged();
    }

    public void setBooks(List<FavoriteBook> books){
        favoriteBooks = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteBookListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteBookListAdapter.MyViewHolder holder, int position) {
        holder.tv_title.setText(favoriteBooks.get(position).getTitle());
        holder.iv_book.setImageResource(favoriteBooks.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return favoriteBooks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_book;
        TextView tv_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_book = (ImageView) itemView.findViewById(R.id.bookImageFav);
            tv_title = (TextView) itemView.findViewById(R.id.bookTitleFav);
        }
    }
}
