/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.controller;

import com.nmc.connection.DataServiceFactory;
import com.nmc.model.Image;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import org.apache.log4j.Logger;



@ManagedBean
@RequestScoped
public class ImageController {
    
    public static final String IMAGEIMAGE_PATH = "http://100.43.205.74:4152/ServerImage/images/";
    private static final Logger LOG = Logger.getLogger(ImageController.class);
    
    private Image image;
        
    private List<Image> image_list;

    /**
     * Creates a new instance of ImageController
     */
    public ImageController() {
        DataServiceFactory.getAdvertisingImage();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Image> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<Image> image_list) {
        this.image_list = image_list;
    }   
    
    
}
