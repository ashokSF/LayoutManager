/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Poleschuk Ivan
 */


@Named
@RequestScoped
public class LayoutManager {
    
    private String playlist;
    
    private String playListName;
    
    
    @PostConstruct
    public void init() {
        setPlaylist("playlists/image.xhtml");
        setPlayListName("Image playlist");
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }
    
    
       
    
}
