package com.example.libraryapplicationcoursework.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapplicationcoursework.R;
import com.example.libraryapplicationcoursework.database.AppDatabase;
import com.example.libraryapplicationcoursework.entity.User;

public class RegistrationActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private EditText et_fullname;
    private TextView tv_wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();
    }

    private void init(){
        et_fullname = findViewById(R.id.fullnameRegister);
        et_password = findViewById(R.id.passwordRegister);
        et_username = findViewById(R.id.usernameRegister);
        tv_wrong = findViewById(R.id.wrongRegister);
    }

    public void registerUser(View view){
        AppDatabase database = AppDatabase.getInstance(this);

        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        String fullname = et_fullname.getText().toString();
        int money = 0;

        User ifExist = database.userDAO().ifUsernameIsTaken(username);


        if(username.length()==0 || password.length() == 0 || fullname.length() == 0 ||username.length()<6 || password.length()<6)
        {
            Toast.makeText(this, "Пароль и логин должны состоять от 7 символов!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(ifExist==null){
                database.userDAO().insertUser(fullname,username,password,money);
                finish();
                Toast.makeText(this, "Вы успешно зарегистрировались!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Такой логин занят!", Toast.LENGTH_SHORT).show();
            }
        }

    }



}