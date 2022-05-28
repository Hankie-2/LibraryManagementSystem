package com.example.libraryapplicationcoursework.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.libraryapplicationcoursework.R;
import com.example.libraryapplicationcoursework.adapter.FavoriteBookListAdapter;
import com.example.libraryapplicationcoursework.database.AppDatabase;
import com.example.libraryapplicationcoursework.entity.FavoriteBook;
import com.example.libraryapplicationcoursework.helper.GeneralData;

import java.util.List;

public class FavoriteFragment extends Fragment {

    private RecyclerView rv_books;
    private FavoriteBookListAdapter adapter;

    private List<FavoriteBook> books;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite,container,false);

        AppDatabase database = AppDatabase.getInstance(getContext());
        books = database.bookDAO().getAllFavoriteBooksByUsername(GeneralData.getLoggedUser().getUsername());

        rv_books = view.findViewById(R.id.recyclerViewFavorite);
        rv_books.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new FavoriteBookListAdapter(getContext(),books);
        rv_books.setAdapter(adapter);
        return view;
    }
}