/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.connection;

import com.nmc.model.Image;
import com.nmc.utils.OperationEnum;
import com.nmc.utils.json.KeyFinder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    //Images response
    public static final String RS_IMAGE_ID = "120.86";
    public static final String RS_IMAGE_NAME = "121.170";

    private static final Logger LOG = Logger.getLogger(DataServiceFactory.class);

    public static void getAdvertisingItem() {

        HashMap<String, String> params = new HashMap();
        HashMap<String, String> filters = new HashMap();

        params.put(FN_USER_ID, "0001202A000000001054");

        String input = buildJsonToServer(OperationEnum.GET_ADVERTISING_ITEM.toString(), params, filters);

        String out = ConnectionHttps.doPost(defaultServer, input);

    }

    public static ArrayList<Image> getAdvertisingImage() {

        HashMap<String, String> params = new HashMap();
        HashMap<String, String> filters = new HashMap();
        
        ArrayList<Image> image_list = new ArrayList();

        filters.put(FN_USER_ID, "0001202A000000001054");
        filters.put("operator", "eq");

        String input = buildJsonToServer(OperationEnum.GET_ADVERTISING_IMAGE.toString(), params, filters);

        String jsonResponse = ConnectionHttps.doPost(defaultServer, input);
        JSONParser parser = new JSONParser();
        
        JSONArray result_array = null;

        try {
            JSONObject obj = (JSONObject) parser.parse(jsonResponse);
            JSONArray result = (JSONArray) obj.get(FN_RESULT_NAME);
            for (Object nest : result) {
                result_array =  (JSONArray)((JSONObject) nest).get(FN_RESULT_NAME);
            }

            for (Object img : result_array) {
                Image image = new Image();
                image.setId((String)((JSONObject) img).get(RS_IMAGE_ID));
                image.setName((String)((JSONObject) img).get(RS_IMAGE_NAME));
                image_list.add(image);
            }

        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        
        return image_list;

    }

    public static void getAdvertisingVideo() {

        HashMap<String, String> params = new HashMap();
        HashMap<String, String> filters = new HashMap();

        filters.put(FN_USER_ID, "0001202A000000001054");
        filters.put("operator", "eq");

        String input = buildJsonToServer(OperationEnum.GET_ADVERTISING_VIDEO.toString(), params, filters);

        String out = ConnectionHttps.doPost(defaultServer, input);
    }

    private static String buildJsonToServer(String operation, HashMap<String, String> params, HashMap<String, String> filters) {
        StringBuilder str = new StringBuilder("{ ");
        str.append("\"").append(FN_SECURITY_TOKEN_NAME).append("\":");
        str.append("\"").append(FN_SECURITY_TOKEN_VALUE).append("\",");

        str.append("\"").append(FN_TIME_STAMP_NAME).append("\":");
        str.append("\"").append(getNowTime()).append("\",");

        str.append("\"").append(FN_LANGUAGE_NAME).append("\":");
        str.append("\"").append("es").append("\",");

        str.append("\"").append(FN_OPLIST_NAME).append("\":[");

        str = buildJsonOperation(str, operation, params, filters);

        str.append("]}");
        return str.toString();
    }

    private static StringBuilder buildJsonOperation(StringBuilder str, String operation, HashMap<String, String> params, HashMap<String, String> filters) {
        // str.append(operationToJSON(operationData));
        str.append("{");
        str.append("\"").append(FN_OPERATION_NAME).append("\":");
        str.append("\"").append(operation).append("\",");

        //Operation PARAMS
        str.append("\"").append(FN_PARAMS_NAME).append("\":");
        str.append("{");

        Iterator<Map.Entry<String, String>> param = params.entrySet().iterator();
        while (param.hasNext()) {
            Map.Entry<String, String> entry = param.next();
            str.append("\"").append((String) entry.getKey()).append("\":");
            str.append("\"").append((String) entry.getValue()).append("\"");
            if (param.hasNext()) {
                str.append(",");
            }
        }
        str.append("}");

        //Operation FILTERS
        Iterator<Map.Entry<String, String>> filter = filters.entrySet().iterator();
        if (filter.hasNext()) {

            str.append(",");
            str.append("\"").append(FN_FILTER_NAME).append("\":[{");

            while (filter.hasNext()) {
                Map.Entry<String, String> entry = filter.next();
                str.append("\"").append((String) entry.getKey()).append("\":");
                str.append("\"").append((String) entry.getValue()).append("\"");
                if (filter.hasNext()) {
                    str.append(",");
                }
            }

            str.append("}]");

        }

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
