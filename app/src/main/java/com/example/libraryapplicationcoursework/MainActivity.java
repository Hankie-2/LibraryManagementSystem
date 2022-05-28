package com.example.libraryapplicationcoursework;

import android.os.Bundle;
import android.view.View;

import com.example.libraryapplicationcoursework.database.AppDatabase;
import com.example.libraryapplicationcoursework.entity.User;
import com.example.libraryapplicationcoursework.helper.GeneralData;
import com.example.libraryapplicationcoursework.helper.LoggedUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.libraryapplicationcoursework.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = getWindow().getDecorView();
        view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int i) {
                if(i==0){
                    view.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private int hideSystemBars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }


    void refreshData(){
        AppDatabase database = AppDatabase.getInstance(this);
        LoggedUser loggedUser = new LoggedUser();
        User user = database.userDAO().getUserByUsernameAndPassword(
                GeneralData.getLoggedUser().getUsername(),
                GeneralData.getLoggedUser().getPassword());
        loggedUser.setFullname(user.getFullName());
        loggedUser.setUsername(user.getUsername());
        loggedUser.setPassword(user.getPassword());
        loggedUser.setWallet(user.getWallet());
        loggedUser.setId(user.getId());
        GeneralData.setLoggedUser(loggedUser);

        database.bookDAO().getAllBoughtBooksByUsername(GeneralData.getLoggedUser().getUsername());
        database.bookDAO().getAllFavoriteBooksByUsername(GeneralData.getLoggedUser().getUsername());

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            view.setSystemUiVisibility(hideSystemBars()
            );
        }
    }

}