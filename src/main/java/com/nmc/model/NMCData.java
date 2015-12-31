/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.model;

/**
 *
 * @author Poleschuk Ivan
 */
public class NMCData {
    
    private String merchantId;
    private String campaignId;
    private String imagePath;
    private String videoPath;
    
    private static NMCData instance;
    
    private NMCData() {
        
    }
    
    {
       merchantId = "0001202A000000000858";
       campaignId = "00000000034";
       imagePath  = "http://100.43.205.74:4224/ServerImage/images/";
       
    }
    
    public static NMCData getInstance() {
        if (instance == null) {
            instance = new NMCData();
        }
        return instance;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
    
    
            
}
