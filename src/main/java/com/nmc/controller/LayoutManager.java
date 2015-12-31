/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import com.nmc.model.Playlist;
import java.io.Serializable;
import java.util.HashMap;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Poleschuk Ivan
 */
@Named
@SessionScoped
public class LayoutManager implements Serializable{

    private Playlist currentPlaylist;
    private static HashMap<String, Playlist> layout_map = new HashMap();

    static {

        layout_map.put("Editor", new Playlist("Editor"));
        layout_map.put("Images", new Playlist("Images"));
        layout_map.put("Videos", new Playlist("Videos"));
        layout_map.put("Items", new Playlist("Items"));
    }

    public LayoutManager() {
        currentPlaylist = layout_map.get("Editor");
    }

    public void setCurrentPlaylist(String type) {
        currentPlaylist = layout_map.get(type);
    }

    public String getUrl() {
        return currentPlaylist.getUrl();
    }

    public String getHeader() {
        return currentPlaylist.getHeader();
    }

}
