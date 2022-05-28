package com.example.libraryapplicationcoursework.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapplicationcoursework.R;
import com.example.libraryapplicationcoursework.entity.Book;
import com.example.libraryapplicationcoursework.ui.bookInformation.BookInformationFragment;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.MyViewHolder> {

    private Context context;
    private List<Book> books;

    public BookListAdapter() {
    }

    public BookListAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
        notifyDataSetChanged();
    }

    public void setBooks(List<Book> books){
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_title.setText(books.get(position).getTitle());
        holder.iv_book.setImageResource(books.get(position).getImage());

        holder.bookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new BookInformationFragment();
                Bundle args = new Bundle();
                args.putString("Title",books.get(position).getTitle());
                args.putString("Author",books.get(position).getAuthor());
                args.putString("Genre",books.get(position).getGenre());
                args.putString("Description",books.get(position).getDescription());
                args.putInt("Price",books.get(position).getPrice());
                args.putInt("Image",books.get(position).getImage());
                myFragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, myFragment).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        ImageView iv_book;
        CardView bookItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.bookTitleItem);
            iv_book = (ImageView) itemView.findViewById(R.id.bookImageTitle);
            bookItem = (CardView) itemView.findViewById(R.id.bookItem);
        }
    }

}
