package com.example.abnn965.totalmemberinvolvement;

/**
 * Created by Vusi Ngwenya on 8/25/2016.
 */

//Class to get target information
public class TargetClass {
    int id;
    String email;
    String name;
    String surname;
    String mobile;
    String address;
    public TargetClass(String name,String surname, String email,String address){
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.address=address;
    }
    public String getEmail(){
        return this.email;
    }
    public String getName(){
        return this.name+" "+this.surname;
    }
    public String getSurname(){
        return this.surname;
    }

    public String getAddress(){
        return this.address;
    }
}
