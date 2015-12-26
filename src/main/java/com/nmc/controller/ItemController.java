/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.controller;

import com.nmc.connection.DataServiceFacade;
import com.nmc.model.Image;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

/**
 *
 * @author Poleschuk Ivan
 */
@Named
@RequestScoped
public class ItemController {

    @Inject
    DataServiceFacade dsf;

    @Inject
    Logger LOG;

}
