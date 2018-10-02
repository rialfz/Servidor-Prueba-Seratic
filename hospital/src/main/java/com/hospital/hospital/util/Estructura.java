/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.util;

import java.util.ArrayList;

/**
 *
 * @author RicardoZ
 */
public class Estructura {
    
    private boolean state;
    private String message;
    private Integer responseCode;
   // private int total;
    private ArrayList<Data> data;
    //private Data data;

    
    public Estructura(boolean estate, String message, ArrayList<Data> data) {
        this.state = estate;
        this.message = message;
        this.data = data;
    }

    public Estructura(boolean state, String message, Integer responseCode, ArrayList<Data> data) {
        this.state = state;
        this.message = message;
        this.responseCode = responseCode;
        this.data = data;
    }
    
    
    
    public Estructura()
    {
        
    }
    
    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;

  
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }



    

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }
    
}
