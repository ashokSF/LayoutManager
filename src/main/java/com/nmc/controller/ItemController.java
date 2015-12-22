/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.controller;

import com.nmc.connection.DataServiceFactory;
import com.nmc.model.Image;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import org.apache.log4j.Logger;

/**
 *
 * @author Иван
 */
@ManagedBean
@RequestScoped
public class ItemController {

    @Inject
    DataServiceFactory dsf;

    private static final Logger LOG = Logger.getLogger(ItemController.class);
    

    
    
    
    

}
