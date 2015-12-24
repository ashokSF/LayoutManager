/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.controller;

import com.nmc.connection.DataServiceFactory;
import com.nmc.model.Image;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
public class ImageController {

    public static final String IMAGEIMAGE_PATH = "http://100.43.205.74:4224/ServerImage/images/";
    private static final Logger LOG = Logger.getLogger(ImageController.class);

    private Image image;

    private ArrayList<Image> image_list;

    /**
     * Creates a new instance of ImageController
     */
    public ImageController() {
        //image_list = DataServiceFactory.getAdvertisingImage();
    }

    public void upload(FileUploadEvent event) {
    
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

    public void setImage_list(ArrayList<Image> image_list) {
        this.image_list = image_list;
    }

    public String getImageUrlBase() {
        return IMAGEIMAGE_PATH;
    }

}
