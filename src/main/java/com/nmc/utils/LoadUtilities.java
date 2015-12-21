package com.nmc.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/*
 *
 * @(#)NMCClass.java 1.0 18/02/2014
 * 
 * Copyright 2014 NoMoreCards Inc. All Rights Reserved
 */


/**
 *
 * @author Jose Viscaya
 *
 */
public class LoadUtilities implements Serializable {

    private static final long serialVersionUID = -341264746820330585L;
    private static final String filenameWin = "C:\\NMC_Resources\\certificates\\server.keystore";
    private static final String filenameLinux = "/root/NMC_Resources/certificates/server.keystore";
    private static final String filenPropertiesW = "C:\\NMC_Resources\\properties\\keyStore.properties";
    private static final String filenPropertiesL = "/root/NMC_Resources/properties/keyStore.properties";


    /**
     *
     * @return Key Store
     */
    public InputStream loadCertificate() {
    	InputStream is = null;
        if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
            try {
                is = new FileInputStream(filenameWin);
                return is;
            } catch (FileNotFoundException e) {
                return is;
            }
        } else {
            try {
                is = new FileInputStream(filenameLinux);
                return is;
            } catch (FileNotFoundException e) {
                return is;
            }
        }
    }

    /**
     *
     * @return Key store
     */
    public Properties loadProperties() {
        Properties propeties = new Properties();
        InputStream is = null;
        try {
            if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
                try {
                    is = new FileInputStream(filenPropertiesW);
                } catch (FileNotFoundException e) {

                }
            } else {
                try {
                    is = new FileInputStream(filenPropertiesL);
                } catch (FileNotFoundException e) {
                    
                }
            }
            propeties.load(is);
        } catch (IOException e) {
            return propeties;
        }
        return propeties;
    }

}
