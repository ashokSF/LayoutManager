/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.connection;

import com.nmc.model.Image;
import com.nmc.model.Item;
import com.nmc.model.Video;
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

    //Video response
    public static final String RS_VIDEO_FILE_ID = "125.50";
    public static final String RS_VIDEO_TITLE = "48.6";
    public static final String RS_VIDEO_FO_NAME = "121.15";
    public static final String RS_VIDEO_THUMB= "47.42";
    
    //Items response
    public static final String RS_ITEM_PRODUCT_ID = "114.144";
    public static final String RS_ITEM_PRODUCT_TYPE_ID = "114.112"; 

    private static final Logger LOG = Logger.getLogger(DataServiceFactory.class);

    public static ArrayList<Item> getAdvertisingItem() {

        ArrayList<Item> item_list = new ArrayList();

        OperationData operationData = new OperationData(OperationEnum.GET_ADVERTISING_VIDEO.toString());
        operationData.setParam(FN_USER_ID, "0001202A000000001054");

        System.out.println(JSONArray.toJSONString(sendOperation(operationData)));

        return item_list;

    }

    public static ArrayList<Image> getAdvertisingImage() {

        ArrayList<Image> image_list = new ArrayList();

        OperationData operationData = new OperationData(OperationEnum.GET_ADVERTISING_IMAGE.toString());
        operationData.setFilter(FN_USER_ID, "0001202A000000001054");
        operationData.setFilter("operator", "eq");

        for (Object img : sendOperation(operationData)) {
            Image image = new Image();
            image.setId((String) ((JSONObject) img).get(RS_IMAGE_ID));
            image.setName((String) ((JSONObject) img).get(RS_IMAGE_NAME));
            image_list.add(image);
        }

        return image_list;

    }

    public static ArrayList<Video> getAdvertisingVideo() {

        ArrayList<Video> video_list = new ArrayList();

        OperationData operationData = new OperationData(OperationEnum.GET_ADVERTISING_VIDEO.toString());
        operationData.setFilter(FN_USER_ID, "0001202A000000001054");
        operationData.setFilter("operator", "eq");

        System.out.println(JSONArray.toJSONString(sendOperation(operationData)));

        return video_list;
    }

    private static JSONArray sendOperation(OperationData operationData) {

        String jsonRequest = buildJsonToServer(operationData.getOperation(), operationData.getParams(), operationData.getFilters());

        String jsonResponse = ConnectionHttps.doPost(defaultServer, jsonRequest);

        JSONParser parser = new JSONParser();

        JSONArray result_array = null;

        try {
            JSONObject obj = (JSONObject) parser.parse(jsonResponse);
            JSONArray result = (JSONArray) obj.get(FN_RESULT_NAME);
            for (Object nest : result) {
                result_array = (JSONArray) ((JSONObject) nest).get(FN_RESULT_NAME);
            }

            operationData.setResults(result_array);

        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        return operationData.getResults();
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
