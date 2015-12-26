/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.controller;

import com.nmc.connection.DataServiceFacade;
import com.nmc.model.Category;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.resizable.Resizable;

/*
 *
 * @author Poleschuk Ivan
 */


@Named
@RequestScoped
public class CategoriesController {
    
    @Inject
    Logger LOG;

    private List<String> categories;

    private List<String> droppedCategories;

    private String selectedCategory;

    public CategoriesController() {
        
        categories = new ArrayList();
        categories.add("Videos");
        categories.add("Items");
        categories.add("Images");

   
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
