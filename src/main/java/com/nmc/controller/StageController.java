/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.controller;

import com.nmc.connection.DataServiceFacade;
import com.nmc.model.CampaignLayout;
import com.nmc.model.LayoutComponent;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Poleschuk Ivan
 */
@Named
@RequestScoped
public class StageController {

    @Inject
    transient Logger LOG;

    private String jsonModel;

    private String layoutWidth;
    private String layoutLength;

    private CampaignLayout layout;

    public StageController() {
        try {
            layout = DataServiceFacade.getAdvertisingLayout();
            //System.out.println(layout.getStageWidth());
            
            
            //recalculate to gridster dashboard            
            if (layout != null) {
                
                JSONArray jsonArray = new JSONArray();
                
                for (LayoutComponent lc: layout.getComponentList()) {
                    Long size_x = Long.parseLong(lc.getWidth()) / 140;
                    Long size_y = Long.parseLong(lc.getHeight()) / 80;
                    Long col = (Long.parseLong(lc.getDimX()) / 140 + 1);
                    Long row = (Long.parseLong(lc.getDimY()) / 80 + 1);
                    
                    JSONObject obj = new JSONObject();
                    obj.put("id", lc.getComponentId());
                    obj.put("size_x", size_x);
                    obj.put("size_y", size_y);
                    obj.put("col", col);
                    obj.put("row", row);
                    
                    jsonArray.add(obj);
                    
                }
                
                this.jsonModel = jsonArray.toJSONString();
                System.out.println(this.jsonModel);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getJsonModel() {
        return jsonModel;
    }

    public void setJsonModel(String jsonModel) {
        this.jsonModel = jsonModel;
    }

    public String getLayoutWidth() {
        return layoutWidth;
    }

    public void setLayoutWidth(String layoutWidth) {
        this.layoutWidth = layoutWidth;
    }

    public String getLayoutLength() {
        return layoutLength;
    }

    public void setLayoutLength(String layoutHeight) {
        this.layoutLength = layoutHeight;
    }

    public CampaignLayout getLayout() {
        return layout;
    }

    public void setLayout(CampaignLayout layout) {
        this.layout = layout;
    }

    public void saveStage() {
        System.out.println(jsonModel);

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) parser.parse(jsonModel);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        layout.setStageLength(layoutLength);
        layout.setStageWidth(layoutWidth);
        layout.setCc(jsonArray.size());
        layout.setComponentList(new ArrayList<LayoutComponent>());

        for (Object obj : jsonArray) {

            Long col = (Long) ((JSONObject) obj).get("col");
            col = (col - 1) * 140;
            Long row = (Long) ((JSONObject) obj).get("row");
            row = (row - 1) * 80;

            LayoutComponent lc = new LayoutComponent(
                    (String) ((JSONObject) obj).get("id"),
                    String.valueOf(col),
                    String.valueOf(row),
                    String.valueOf((Long) ((JSONObject) obj).get("size_y") * 80),
                    String.valueOf((Long) ((JSONObject) obj).get("size_x") * 140)
            );
            layout.addComponent(lc);
        }

        DataServiceFacade.addAdvertisingLayout(layout);
    }

}
