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
import com.example.libraryapplicationcoursework.entity.BoughtBook;

import java.util.List;

public class BoughtBookListAdapter extends RecyclerView.Adapter<BoughtBookListAdapter.MyViewHolder> {

    private Context context;
    private List<BoughtBook> books;

    public BoughtBookListAdapter() {
    }

    public BoughtBookListAdapter(Context context, List<BoughtBook> books) {
        this.context = context;
        this.books = books;
        notifyDataSetChanged();
    }

    public void setBooks(List<BoughtBook> books){
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_buy,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.iv_book.setImageResource(books.get(position).getImage());
        holder.tv_title.setText(books.get(position).getTitle());
        holder.tv_date.setText(books.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_date;
        ImageView iv_book;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date = (TextView) itemView.findViewById(R.id.bookDateBuy);
            tv_title = (TextView) itemView.findViewById(R.id.bookTitleBuy);
            iv_book = (ImageView) itemView.findViewById(R.id.bookImageFav);
        }
    }
}
