package com.nmc.connection;

/**
 * Declaration of imports
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.codec.binary.Base64;

import com.nmc.utils.LoadUtilities;
/*
 *
 * @(#)NMCClass.java 1.0 18/02/2014
 * 
 * Copyright 2014 NoMoreCards Inc. All Rights Reserved
 */

/**
 * this Class implements the connection ssl
 *
 * @author Jose Viscaya
 *
 */
public class ConnectionHttps implements Serializable {

    private static final long serialVersionUID = 8438596628615332038L;
    private static InputStream trustedcertificate;
    private static KeyStore kstrustedcertificate;
    private static TrustManagerFactory tmf;
    private static SSLContext context;
    private static SSLSocketFactory sslSocketFactory;
    private static URL url;
    private static URLConnection conexion;
    private static InputStream is;
    private static BufferedReader br;
    private static Date date = new Date();
    private static LoadUtilities ld = new LoadUtilities();
    private static Properties propeties = new Properties();

    static {
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
            public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                propeties = ld.loadProperties();
                if (hostname.equals(propeties.get("host"))) {
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Method that connect to server ssl by post
     *
     * @param Url
     * @param input
     * @return
     */
    public static String doPost(String Url, String input) {
        input = new String(Base64.encodeBase64(input.getBytes()));
        propeties = ld.loadProperties();
        try {
            trustedcertificate = ld.loadCertificate();
            kstrustedcertificate = KeyStore.getInstance(KeyStore.getDefaultType());
            kstrustedcertificate.load(trustedcertificate, propeties.getProperty("key").toCharArray());
            trustedcertificate.close();
            tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(kstrustedcertificate);

            context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);
            sslSocketFactory = context.getSocketFactory();

            url = new URL(Url);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setSSLSocketFactory(sslSocketFactory);
            con.setRequestMethod("POST");
            con.setRequestProperty("content-type", "text/plain; charset=utf-8");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(input);
            wr.flush();
            wr.close();
            String response = "";
            System.out.println(con.getResponseCode());
            if (con.getResponseCode() == 201) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response += inputLine;
                }
                in.close();
            }
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "{\"192\":\"\",\"11\":\"" + date + "\",\"122.17\":true,\"122.18\":\"\",\"RESULT\":[{\"101\":\"\",\"39\":\"9999\",\"44\":\"Unknown\",\"RESULT\":[{}]}]}";
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"192\":\"\",\"11\":\"" + date + "\",\"122.17\":true,\"122.18\":\"\",\"RESULT\":[{\"101\":\"\",\"39\":\"9999\",\"44\":\"Unknown\",\"RESULT\":[{}]}]}";
        } catch (KeyStoreException e) {
            e.printStackTrace();
            return "{\"192\":\"\",\"11\":\"" + date + "\",\"122.17\":true,\"122.18\":\"\",\"RESULT\":[{\"101\":\"\",\"39\":\"9999\",\"44\":\"Unknown\",\"RESULT\":[{}]}]}";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "{\"192\":\"\",\"11\":\"" + date + "\",\"122.17\":true,\"122.18\":\"\",\"RESULT\":[{\"101\":\"\",\"39\":\"9999\",\"44\":\"Unknown\",\"RESULT\":[{}]}]}";
        } catch (CertificateException e) {
            e.printStackTrace();
            return "{\"192\":\"\",\"11\":\"" + date + "\",\"122.17\":true,\"122.18\":\"\",\"RESULT\":[{\"101\":\"\",\"39\":\"9999\",\"44\":\"Unknown\",\"RESULT\":[{}]}]}";
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "{\"192\":\"\",\"11\":\"" + date + "\",\"122.17\":true,\"122.18\":\"\",\"RESULT\":[{\"101\":\"\",\"39\":\"9999\",\"44\":\"Unknown\",\"RESULT\":[{}]}]}";
        } catch(Exception e) {
            e.printStackTrace();
            return "{\"192\":\"\",\"11\":\"" + date + "\",\"122.17\":true,\"122.18\":\"\",\"RESULT\":[{\"101\":\"\",\"39\":\"9999\",\"44\":\"Unknown\",\"RESULT\":[{}]}]}";
        }
    }

}
