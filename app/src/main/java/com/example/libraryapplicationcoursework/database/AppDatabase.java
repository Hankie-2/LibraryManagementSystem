package com.example.libraryapplicationcoursework.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.libraryapplicationcoursework.dao.BookDAO;
import com.example.libraryapplicationcoursework.dao.UserDAO;
import com.example.libraryapplicationcoursework.entity.Book;
import com.example.libraryapplicationcoursework.entity.BoughtBook;
import com.example.libraryapplicationcoursework.entity.FavoriteBook;
import com.example.libraryapplicationcoursework.entity.User;

@Database(entities = {User.class, Book.class, FavoriteBook.class, BoughtBook.class},version = 6,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase database;

    private static final String databaseName = "library";


    public synchronized static AppDatabase getInstance(Context context)
    {
        //Проверка
        if(database == null)
        {
            //Если репозиторий null то инициализируем базу данных
            database = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,databaseName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract UserDAO userDAO();

    public abstract BookDAO bookDAO();

}
