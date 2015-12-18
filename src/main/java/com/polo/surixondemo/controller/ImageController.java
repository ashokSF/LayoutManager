/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polo.surixondemo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.primefaces.extensions.event.ImageAreaSelectEvent;
import org.primefaces.extensions.event.ResizeEvent;
import org.primefaces.extensions.event.RotateEvent;
import org.primefaces.model.CroppedImage;
import sun.misc.BASE64Decoder;

@ManagedBean
@SessionScoped
public class ImageController implements Serializable {

    private static final long serialVersionUID = 20111020L;

    private String imageUrl;

    private CroppedImage croppedImage;

    private String newImageName;

    private byte[] bytes;

    private String dataURL;

    private String colorInline;

    public ImageController() {
        imageUrl = "/resources/images/bridge.jpg";
    }

    public void setDataURL(String dataURL) {
        this.dataURL = dataURL;
    }

    public String getDataURL() {
        return dataURL;
    }

    public void submit() throws IOException {

        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("imgEditor:dataURL");
        System.out.println("Submit" + getDataURL());
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) external.getContext();
        String filename = servletContext.getRealPath("cloud.png");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decodedBytes = decoder.decodeBuffer(dataURL.split("^data:image/(png|jpg);base64,")[1]);
        BufferedImage imag = ImageIO.read(new ByteArrayInputStream(decodedBytes));
        ImageIO.write(imag, "jpg", new File(filename));
    }

    public void selectEndListener(final ImageAreaSelectEvent e) {
        final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area selected",
                "X1: " + e.getX1()
                + ", X2: " + e.getX2()
                + ", Y1: " + e.getY1()
                + ", Y2: " + e.getY2()
                + ", Image width: " + e.getImgWidth()
                + ", Image height: " + e.getImgHeight());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void rotateListener(final RotateEvent e) {
        final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Image rotated",
                "Degree:" + e.getDegree());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void resizeListener(final ResizeEvent e) {
        final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Image resized",
                "Width:" + e.getWidth() + ", Height: " + e.getHeight());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    private String getRandomImageName() {
        int i = (int) (Math.random() * 100000);

        return String.valueOf(i);
    }

    public void crop() {
        if (croppedImage == null) {
            return;
        }

        setNewImageName(getRandomImageName());
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources"
                + File.separator + "images" + File.separator + "crop" + File.separator + getNewImageName() + ".jpg";

        System.out.println(newFileName);

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
            imageOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cropping failed."));
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Cropping finished."));
    }

    public String getNewImageName() {
        return newImageName;
    }

    public void setNewImageName(String newImageName) {
        this.newImageName = newImageName;
    }

    public String getColorInline() {
        return colorInline;
    }

    public void setColorInline(String colorInline) {
        this.colorInline = colorInline;
    }

}
