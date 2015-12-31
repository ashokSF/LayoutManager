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
public class Video implements Serializable{
    
    private String id;
    
    private String name;
    
    private String videoFile;
    
    private String thumb;
    
    private String playListOrder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPlayListOrder() {
        return playListOrder;
    }

    public void setPlayListOrder(String playListOrder) {
        this.playListOrder = playListOrder;
    }
    
    
    
    
}
