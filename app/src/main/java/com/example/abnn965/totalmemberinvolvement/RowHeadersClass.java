package com.example.abnn965.totalmemberinvolvement;

/**
 * Created by ABVN237 on 8/30/2016.
 */
//The class for getting data for populating the table headers
public class RowHeadersClass {
    String idHeader;
    String nameHeader;
    String emailHeader;
    String mobileHeader;
    String addressHeader;
    String actionsHeader;

    public RowHeadersClass(String nameHeader,String emailHeader,String addressHeader,String actionsHeader){
        this.nameHeader=nameHeader;
        this.emailHeader=emailHeader;
        this.addressHeader=addressHeader;
        this.actionsHeader=actionsHeader;
    }
    public String getNameHeader(){
        return this.nameHeader;
    }
    public String getEmailHeader(){
        return this.emailHeader;
    }
    public String getAddressHeader(){
        return this.addressHeader;
    }
    public String getActionsHeader(){
        return this.actionsHeader;
    }
}
