package com.example.libraryapplicationcoursework.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapplicationcoursework.MainActivity;
import com.example.libraryapplicationcoursework.R;
import com.example.libraryapplicationcoursework.database.AppDatabase;
import com.example.libraryapplicationcoursework.entity.User;
import com.example.libraryapplicationcoursework.helper.GeneralData;
import com.example.libraryapplicationcoursework.helper.LoggedUser;

public class LoginActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private TextView tv_wrong;
    private LoggedUser loggedUser;

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppDatabase database = AppDatabase.getInstance(this);
        database.userDAO().deleteAllUsers();
        database.userDAO().insertUser("Marsel Charginov","marschar","12200202",5000);

        init();
        view = getWindow().getDecorView();
        view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int i) {
                if(i==0){
                    view.setSystemUiVisibility(hideSystemBars());
                }
            }
        });
    }

    private int hideSystemBars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            view.setSystemUiVisibility(hideSystemBars()
            );
        }
    }

    private void init(){

        et_password = findViewById(R.id.passwordSignIn);
        et_username = findViewById(R.id.usernameSignIn);
        tv_wrong = findViewById(R.id.wrong);

    }

    public void switchToRegistration(View view){
        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }

    public void checkData(View view){
        AppDatabase database = AppDatabase.getInstance(this.getApplicationContext());

        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        User user = database.userDAO().getUserByUsernameAndPassword(username,password);
        if(user!=null){
            loggedUser = new LoggedUser();
            loggedUser.setFullname(user.getFullName());
            loggedUser.setUsername(user.getUsername());
            loggedUser.setPassword(user.getPassword());
            loggedUser.setWallet(user.getWallet());
            loggedUser.setId(user.getId());
            GeneralData.setLoggedUser(loggedUser);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            tv_wrong.setVisibility(View.VISIBLE);
        }
    }

}