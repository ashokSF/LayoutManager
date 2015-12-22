/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.connection;

import com.nmc.utils.OperationEnum;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author jose
 */
public class DataServiceFactory {

    private static String defaultServer = "https://100.43.205.74:8095/NmcServerS/nmc-server/post/";

    public static final String FN_SECURITY_TOKEN_NAME = "192";
    public static final String FN_SECURITY_TOKEN_VALUE = "8f09eaddb545ff7c94b3c7106eede716";
    public static final String FN_TIME_STAMP_NAME = "11";
    public static final String FN_LANGUAGE_NAME = "122.45";
    public static final String FN_OPLIST_NAME = "OPTLST";
    public static final String FN_OPERATION_NAME = "101";
    public static final String FN_USER_ID = "53";
    public static final String FN_PARAMS_NAME = "PARAM";
    public static final String FN_FILTER_NAME = "FILTER";
    public static final String FN_ORDER_NAME = "ORDER";
    public static final String FN_GLOBAL_ERROR_FLAG_NAME = "122.17";
    public static final String FN_RESULT_NAME = "RESULT";
    public static final String FN_RESULT_CODE_NAME = "39";
    public static final String FN_RESULT_MESSAGE_NAME = "44";
    public static final String FN_EXPECTED_NAME = "EXPECTED";
    public static final String FN_EXPECTED_ALL_NAME = "ALL";
    public static final String FN_OPERATOR_NAME = "operator";
    public static final String FN_NOT_NAME = "NOT";
    public static final String FN_REQUEST_COUNT = "COUNT";
    public static final String FN_COUNT = "127.41";
    public static final String FN_LIMITS = "LIMITS";
    public static final String FN_ORDER_DESC = "DESC";
    public static final String FN_ORDER_ASC = "ASC";
    public static final String SERVER_DATE_FORMAT = "MM-dd-yyyy KK:mm:ss.SSS Z";

    public void test() {
        String input = buildJsonToServer();
        
        System.out.println(input);
        String out = ConnectionHttps.doPost(defaultServer, input);

        System.out.println("Response " + out);
    }

    private String buildJsonToServer() {
        StringBuilder str = new StringBuilder("{ ");
        str.append("\"").append(FN_SECURITY_TOKEN_NAME).append("\":");
        str.append("\"").append(FN_SECURITY_TOKEN_VALUE).append("\",");

        str.append("\"").append(FN_TIME_STAMP_NAME).append("\":");
        str.append("\"").append(getNowTime()).append("\",");

        str.append("\"").append(FN_LANGUAGE_NAME).append("\":");
        str.append("\"").append("es").append("\",");

        str.append("\"").append(FN_OPLIST_NAME).append("\":[");
        
        str = buildJsonOperation(str);

        str.append("]}");
        return str.toString();
    }
    
    private StringBuilder buildJsonOperation(StringBuilder str) {
        // str.append(operationToJSON(operationData));
        str.append("{");
        str.append("\"").append(FN_OPERATION_NAME).append("\":");
        str.append("\"").append(OperationEnum.GET_ADVERTISING_ITEM).append("\",");    
        
        
        //Operation PARAMS
        str.append("\"").append(FN_PARAMS_NAME).append("\":");
        str.append("{");
        str.append("\"").append(FN_USER_ID).append("\":");
        str.append("\"").append("0001202A000000001054").append("\"");           
        str.append("}");     
        
        
        str.append("}"); //Operation end     
        
        return str;
    }

    private static String getNowTime() {
        // UTC is always 0000, if other zone is used the patter should changed
        SimpleDateFormat dateFormat = new SimpleDateFormat(SERVER_DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(new Date());
    }
}
