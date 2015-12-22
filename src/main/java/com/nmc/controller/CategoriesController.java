/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.controller;

import com.nmc.connection.DataService;
import com.nmc.model.Category;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.Application;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.resizable.Resizable;

/**
 *
 * @author Иван
 */
@ManagedBean
@RequestScoped
public class CategoriesController {

    private List<String> categories;

    private List<String> droppedCategories;

    private String selectedCategory;

    public CategoriesController() {

        categories = new ArrayList<String>();
        categories.add("Videos");
        categories.add("Weather");
        categories.add("RSS");
        categories.add("Images");

        DataService e = new DataService();
        e.test();

    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getDroppedCategories() {
        return droppedCategories;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

}
