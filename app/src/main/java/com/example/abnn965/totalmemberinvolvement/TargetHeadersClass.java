package com.example.abnn965.totalmemberinvolvement;

/**
 * Created by ABVN237 on 9/5/2016.
 */
public class TargetHeadersClass {

    String nameHeader;
    String daysInHeader;
    String daysAbsentHeader;

    public TargetHeadersClass(String nameHeader,String daysInHeader,String daysAbsentHeader){
        this.nameHeader=nameHeader;
        this.daysInHeader=daysInHeader;
        this.daysAbsentHeader=daysAbsentHeader;
    }
    public String getNameHeader(){
        return this.nameHeader;
    }
    public String getDaysInHeader(){
        return this.daysInHeader;
    }
    public String getDaysAbsentHeader(){
        return this.daysAbsentHeader;
    }
}
