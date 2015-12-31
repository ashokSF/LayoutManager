/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.controller;

import com.nmc.connection.DataServiceFacade;
import com.nmc.model.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Poleschuk Ivan
 */
@Named
@SessionScoped
public class ImageController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    transient Logger LOG;

    private Image image;

    private ArrayList<Image> image_list = new ArrayList();

    private ArrayList<Image> portal_images = new ArrayList();

    private static final String destination = "C:\\AppServ\\www\\ServerImage\\images\\";
    
    private static final String DEFAULT_IMAGE = "icnCamara.png";

    private Image currentEditingImage;

    /**
     * Creates a new instance of ImageController
     */
    public ImageController() {
        
        ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();

        DataServiceFacade.getAdvertisingVideo();
        //portal_images = DataServiceFacade.getAdvertisingImage();
        image_list = DataServiceFacade.getAdvertisingImage();
        for (int i = 1; image_list.size() < 10; i++) {
            Image img = new Image();
            img.setId(i);
            img.setUrl(("/resources/images/").concat(DEFAULT_IMAGE));
            //img.setUrl("C:\\AppServ\\www\\ServerImage\\images\\upload.png");
            img.setAlt("click to add image");
            image_list.add(i - 1, img);
        }
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

    public void processUpOrder(Image img) {
        if (img.getId() > 1) {
            int i = img.getId() - 1;
            //System.out.println(i);
            img.setId(i);
            (image_list.get(i - 1)).setId(i + 1);
            Collections.swap(image_list, i, i - 1);
        }

    }
    
    
    public void processDownOrder(Image img) {
        if (img.getId() < image_list.size()) {
            int i = img.getId() - 1;
            //System.out.println(i);
            img.setId(i + 2);
            (image_list.get(i + 1)).setId(i + 1);
            Collections.swap(image_list, i, i + 1);
        }
    }

    public void upload(FileUploadEvent event) {

        UploadedFile uploadedFile = event.getFile();
        System.out.println(uploadedFile.getFileName());
        // Do what you want with the file        

        try {
            copyFile(uploadedFile.getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        currentEditingImage.setUrl(destination + uploadedFile.getFileName());

    }

    public void saveImage(Image img) {
        System.out.println("new image setting");

    }

    public void copyFile(String fileName, InputStream in) {
        try {
            System.out.println(destination + fileName);
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Image getCurrentEditingImage() {
        return currentEditingImage;
    }

    public void setCurrentEditingImage(Image currentEditingImage) {
        this.currentEditingImage = currentEditingImage;
    }
    
    
    

}
