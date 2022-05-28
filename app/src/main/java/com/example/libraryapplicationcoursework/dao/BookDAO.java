package com.example.libraryapplicationcoursework.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.libraryapplicationcoursework.entity.Book;
import com.example.libraryapplicationcoursework.entity.BoughtBook;
import com.example.libraryapplicationcoursework.entity.FavoriteBook;
import com.example.libraryapplicationcoursework.entity.User;

import java.util.List;

@Dao
public interface BookDAO {

    @Query("SELECT * FROM books")
    List<Book> getAllBooks();

    @Query("DELETE FROM books")
    void deleteAllBooks();

    @Insert
    void addBook(Book book);

    @Query("SELECT * FROM books ORDER BY RANDOM() LIMIT 1")
    Book randomBook();

    @Query("SELECT * FROM favorite WHERE username = :username")
    List<FavoriteBook> getAllFavoriteBooksByUsername(String username);

    @Query("INSERT INTO favorite(title,image,username) VALUES(:title,:image,:username)")
    void addFavoriteBook(String title,int image,String username);

    @Insert
    void buyBook(BoughtBook book);

    @Query("SELECT * FROM bought WHERE username = :username")
    List<BoughtBook> getAllBoughtBooksByUsername(String username);

}
