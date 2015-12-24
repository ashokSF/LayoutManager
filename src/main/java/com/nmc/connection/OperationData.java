/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.connection;

import java.util.HashMap;
import org.json.simple.JSONArray;

/**
 *
 * @author Иван
 */
public class OperationData {

    private HashMap<String, String> params = new HashMap();
    private HashMap<String, String> filters = new HashMap();

    private String operation;

    private JSONArray results;

    public OperationData(String operation) {
        this.operation = operation;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public HashMap<String, String> getFilters() {
        return filters;
    }

    public void setParam(String key, String value) {
        this.params.put(key, value);
    }

    public void setFilter(String key, String value) {
        this.filters.put(key, value);
    }

    public String getOperation() {
        return operation;
    }

    public JSONArray getResults() {
        return results;
    }

    public void setResults(JSONArray results) {
        this.results = results;
    }

}
