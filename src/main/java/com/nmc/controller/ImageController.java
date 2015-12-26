/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.controller;

import com.nmc.connection.DataServiceFacade;
import com.nmc.model.Image;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Poleschuk Ivan
 */

@Named
@RequestScoped
public class ImageController {
    
    @Inject
    Logger LOG;

    private Image image;

    private ArrayList<Image> image_list = new ArrayList();

    /**
     * Creates a new instance of ImageController
     */
    public ImageController() {
        //image_list = DataServiceFacade.getAdvertisingImage();
        for (int i = 1; image_list.size() < 10; i++ ) {
            Image img = new Image();
            img.setId(String.valueOf(i));
            img.setName("/SurixonDemo/resources/images/icnCamara.png");
            image_list.add(img);
        }        
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

    
 
}
