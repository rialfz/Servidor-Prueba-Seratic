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
public class Data<T> 
{
    private int total;
    //private T rows;
    private ArrayList<T> rows;


    public Data(int total, ArrayList<T> rows) {
        this.total = total;
        this.rows = rows;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    
    public ArrayList<T> getRows()
    {
        return rows;
        
    }

    public void setRows(ArrayList<T> rows) {
        this.rows = rows;
    }

    public Data() {
    }
    
}
