package com.tcs.nanodegree.myappportfolio.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Jinal Tandel on 11/19/2015.
 */
public class Utility {

    public static String getResponseString(URL url) throws IOException {
        URLConnection urlConnection;
        String response = "";
        urlConnection = url.openConnection();
        urlConnection.setConnectTimeout(20000);
        urlConnection.setReadTimeout(15000);
        urlConnection.setUseCaches(false);
        InputStream in = urlConnection.getInputStream();
        StringBuilder sb = new StringBuilder();
        byte[] buff = new byte[1024];
        int count;
        while ((count = in.read(buff)) > 0) {
            sb.append(new String(buff, 0, count));

        }
        response = sb.toString();
        return response;

    }
}
