package com.example.abnn965.totalmemberinvolvement;

/**
 * Created by ABVN237 on 9/5/2016.
 */
public class ViewTargetClass {
    String name;
    String surname;
    int daysIn;
    int daysAbsent;

    public ViewTargetClass(String name,int daysIn,int daysAbsent){
        this.name=name;
        this.surname=surname;
        this.daysIn=daysIn;
        this.daysAbsent=daysAbsent;
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getDaysIn(){
        String daysIn = String.valueOf(this.daysIn);
        return this.daysIn+" out of (7)";
    }
    public String getDaysAbsent(){
        return this.daysAbsent+" out of (7)";
    }
}
