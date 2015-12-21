/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmc.connection;

/**
 *
 * @author jose
 */
public class Example {
    private static String defaultServer = "https://100.43.205.74:8095/NmcServerS/nmc-server/post/";
    
    public void test(){
        String input = "{\"192\":\"95d646f51a150173a774061559511532\",\"11\":\"09-16-2014 08:01:13.851 -0500\",\"122.45\":\"es\",\"120.38\":\"45353534534543\", \"120.39\":\"45353534534543\",\"57\":\"35323a35343a30303a31323a33343a3536\", \"OPTLST\":[{\"101\":\"010100368\",\"PARAM\":{\"53\":\"0001202A000000001124\",\"122.126\":\"BOG DONGLE 2\"}}]}";
        String out = ConnectionHttps.doPost(defaultServer,input);
	System.out.println("Response "+out);
    }
}
