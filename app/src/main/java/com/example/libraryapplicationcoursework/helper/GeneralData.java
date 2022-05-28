package com.example.libraryapplicationcoursework.helper;

public class GeneralData {

    private static LoggedUser loggedUser;

    public static void setLoggedUser(LoggedUser user){
        loggedUser = user;
    }

    public static LoggedUser getLoggedUser(){
        return loggedUser;
    }


}
