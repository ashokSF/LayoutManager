/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Poleschuk Ivan
 */
public class CampaignLayout{
    
    private String campaignId;

    public CampaignLayout(String campaignId, String stageLength, String stageWidth, Integer cc) {
        this.campaignId = campaignId;
        this.stageLength = stageLength;
        this.stageWidth = stageWidth;
        this.cc = cc;
    }
    
    private String stageLength;
    private String stageWidth;
    private Integer cc;
    
    private ArrayList<LayoutComponent> componentList = new ArrayList();

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getStageLength() {
        return stageLength;
    }

    public void setStageLength(String stageLength) {
        this.stageLength = stageLength;
    }

    public String getStageWidth() {
        return stageWidth;
    }

    public void setStageWidth(String stageWidth) {
        this.stageWidth = stageWidth;
    }

    public Integer getCc() {
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }

    public ArrayList<LayoutComponent> getComponentList() {
        return componentList;
    }

    public void setComponentList(ArrayList<LayoutComponent> componentList) {
        this.componentList = componentList;
    }
    
    public void addComponent(LayoutComponent component) {
        componentList.add(component);
    }
    
    
    
}
