/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polo.surixondemo.model;
/**
 *
 * @author Иван
 */
public class Category {
    private String name;
    
    public Category() {
        
    }
    
    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
