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
public class Playlist implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String url;
    
    private String name;
    
    private String header;
    
    public Playlist(String type) {
        name = type;
        switch (type) {
            case "Images":
                url = "playlists/image.xhtml";
                header = "Image playlist";
                break;
            case "Videos":
                url = "playlists/video.xhtml";
                header = "Video playlist";
                break;
            case "Items":
                url = "playlists/item.xhtml";
                header = "Item playlist";
                break;
             case "Editor":
                url = "playlists/editor.xhtml";
                header = "Choose playlist to edit";
                break;               
        }       
    }

    public String getUrl() {
        return url;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
    
    
    
}
