package com.example.libraryapplicationcoursework.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.libraryapplicationcoursework.entity.User;

@Dao
public interface UserDAO {

    @Query("DELETE FROM users")
    void deleteAllUsers();

    @Query("INSERT INTO users(name,username,password,wallet) VALUES(:fullname,:username,:password,:wallet)")
    void insertUser(String fullname,String username,String password,int wallet);

    @Query("SELECT * FROM users WHERE username =:username AND password =:password")
    User getUserByUsernameAndPassword(String username,String password);

    @Query("UPDATE users SET wallet = wallet - :money WHERE username =:username")
    void subtractMoneyByUsername(String username,int money);

    @Query("SELECT * FROM users WHERE username = :username")
    User ifUsernameIsTaken(String username);

    @Query("UPDATE users SET wallet = wallet - :money WHERE username =:username ")
    void subtractMoney(String username,int money);

    @Query("SELECT * FROM users WHERE username = :username AND wallet > :money")
    User checkUsersWallet(String username,int money);
}
