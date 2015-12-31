/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.model;

import java.io.Serializable;

/**
 *
 * @author Poleschuk Ivan
 */
public class LayoutComponent{
    private String componentId;
    private String dimX;
    private String dimY;
    private String height;
    private String width;

    public LayoutComponent(String componentId, String dimX, String dimY, String height, String width) {
        this.componentId = componentId;
        this.dimX = dimX;
        this.dimY = dimY;
        this.height = height;
        this.width = width;
    }
    

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getDimX() {
        return dimX;
    }

    public void setDimX(String dimX) {
        this.dimX = dimX;
    }

    public String getDimY() {
        return dimY;
    }

    public void setDimY(String dimY) {
        this.dimY = dimY;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
    
    
    
}
