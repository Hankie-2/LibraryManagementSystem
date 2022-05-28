package com.example.libraryapplicationcoursework.entity;

import android.provider.ContactsContract;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "bought")
public class BoughtBook {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "image")
    private int image;

    public BoughtBook(String title, String username, String date,int image) {
        this.title = title;
        this.username = username;
        this.date = date;
        this.image = image;
    }

    public BoughtBook(String title, String date, int image) {
        this.title = title;
        this.date = date;
        this.image = image;
    }

    public BoughtBook() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
