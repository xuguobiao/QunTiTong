/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.everlast.qtt.manager.controller;

import com.everlast.qtt.manager.common.CommonUtils;
import com.everlast.qtt.manager.common.DebugLog;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author xuguobiao
 */
public class BaseController {

    protected final static int TIMEOUT_CONNECTION = 10000;

    protected final static String HTTP_GET = "GET";
    protected final static String HTTP_POST = "POST";

    protected String userAgent = "";
    protected Map<String, String> cookies = null;

    public void setUserAgent(String ua) {
        userAgent = ua;
    }

    protected Document requestDocument(String url, String httpMethod, Map<String, String> data) throws Exception {
        DebugLog.log("request url->" + url);
        DebugLog.log("httpMethod->" + httpMethod);
        Connection connection = Jsoup.connect(url).timeout(TIMEOUT_CONNECTION).userAgent(userAgent);
        if (data != null && data.size() > 0) {
            connection.data(data);
            DebugLog.log("request data->" + CommonUtils.map2String(data));
        }
        if (cookies != null) {
            DebugLog.log("set Cookies->" + cookies);
            connection.cookies(cookies);
        }
        Document resultDocument = HTTP_POST.equalsIgnoreCase(httpMethod) ? connection.post() : connection.get();

        return resultDocument;
    }

    protected String requestBodyString(String url, String httpMethod, Map<String, String> data) throws Exception {
        DebugLog.log("request url->" + url);
        DebugLog.log("httpMethod->" + httpMethod);
        Connection connection = Jsoup.connect(url).timeout(TIMEOUT_CONNECTION).userAgent(userAgent);
        if (data != null && data.size() > 0) {
            connection.data(data);
            DebugLog.log("request data->" + CommonUtils.map2String(data));
        }
        if (cookies != null) {
            connection.cookies(cookies);
        }
        connection.method(HTTP_POST.equalsIgnoreCase(httpMethod) ? Connection.Method.POST : Connection.Method.GET);
        Connection.Response res = connection.execute();

        String resultBody = res.body();
        DebugLog.log("resultBody->" + resultBody);

        DebugLog.log("resultCookies->" + res.cookies());
        if (res.cookies() != null && !res.cookies().isEmpty()) {
            cookies = res.cookies();
        }
        return resultBody;
    }

    protected byte[] requestImage(String url, String httpMethod, Map<String, String> data) throws Exception {
        DebugLog.log("request url->" + url);
        DebugLog.log("httpMethod->" + httpMethod);
        Connection connection = Jsoup.connect(url).timeout(TIMEOUT_CONNECTION).userAgent(userAgent);
        if (data != null && data.size() > 0) {
            connection.data(data);
            DebugLog.log("request data->" + CommonUtils.map2String(data));
        }
        if (cookies != null) {
            connection.cookies(cookies);
        }
        connection.method(HTTP_POST.equalsIgnoreCase(httpMethod) ? Connection.Method.POST : Connection.Method.GET);
        connection.ignoreContentType(true);
        Connection.Response res = connection.execute();

        byte[] resultBody = res.bodyAsBytes();

        DebugLog.log("resultCookies->" + res.cookies());
        if (res.cookies() != null && !res.cookies().isEmpty()) {
            cookies = res.cookies();
        }
        return resultBody;
    }
}
