package com.example.libraryapplicationcoursework.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.libraryapplicationcoursework.R;
import com.example.libraryapplicationcoursework.adapter.BoughtBookListAdapter;
import com.example.libraryapplicationcoursework.adapter.FavoriteBookListAdapter;
import com.example.libraryapplicationcoursework.database.AppDatabase;
import com.example.libraryapplicationcoursework.entity.BoughtBook;
import com.example.libraryapplicationcoursework.helper.GeneralData;

import java.util.List;

public class BoughtFragment extends Fragment {

    private BoughtBookListAdapter adapter;
    private RecyclerView rv_books;

    private List<BoughtBook> books;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bought,container,false);

        AppDatabase database = AppDatabase.getInstance(getContext());
        books = database.bookDAO().getAllBoughtBooksByUsername(
                GeneralData.getLoggedUser().getUsername());

        rv_books = view.findViewById(R.id.recyclerViewBought);
        rv_books.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new BoughtBookListAdapter(getContext(),books);
        rv_books.setAdapter(adapter);

        return view;
    }
}