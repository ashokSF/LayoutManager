/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.utils;

/**
 *
 * @author Poleschuk Ivan
 */
public enum OperationEnum {

    GET_ADVERTISING_ITEM("010100289"),
    GET_ADVERTISING_IMAGE("010100310"),
    GET_ADVERTISING_VIDEO("010100311");

    private final String operationServerCode;

    private OperationEnum(String operationServerCode) {
        this.operationServerCode = operationServerCode;
    }

    public String getOperationServerCode() {
        return operationServerCode;
    }

    @Override
    public String toString() {
        return operationServerCode;
    }
    
    
    
}
