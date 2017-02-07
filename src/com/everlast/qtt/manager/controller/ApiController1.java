package com.everlast.qtt.manager.controller;

import com.everlast.qtt.manager.common.DebugLog;
import com.everlast.qtt.manager.model.FmLoginModel;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author: XuGuobiao
 * @email: everlastxgb@gmail.com
 *
 * Created on 2015-4-7 PM 2:38:04
 *
 */
public class ApiController1 {

    public enum REQUEST_WHAT {

        FM_LOGIN, FM_GETPHONE, FM_GETMESSAGE, QTT_PHONE_VERCODE, QTT_LOGIN_ACCOUNT, QTT_REGITER_CHEAT
    }

    private FmController fmController;

    public ApiController1() {
        fmController = new FmController();
    }

    public void fmLogin(String uName, String pWord, ResultCallback<FmLoginModel> resultCallback) {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("uName", uName);
        dataMap.put("pWord", pWord);
        commonControl(REQUEST_WHAT.FM_LOGIN, dataMap, resultCallback);
    }

    public void fmGetPhone(String count, ResultCallback<String[]> resultCallback) {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("Count", count);
        commonControl(REQUEST_WHAT.FM_GETPHONE, dataMap, resultCallback);
    }

    public void fmGetMessage(String phone, ResultCallback<String> resultCallback) {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("Phone", phone);
        commonControl(REQUEST_WHAT.FM_GETMESSAGE, dataMap, resultCallback);
    }

    public void qttRegisterCheat(String phone, ResultCallback<String> resultCallback) {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("Phone", phone);
        commonControl(REQUEST_WHAT.QTT_REGITER_CHEAT, dataMap, resultCallback);
    }

    private void commonControl(final REQUEST_WHAT what, final Map<String, String> dataMap, final ResultCallback resultCallback) {
        new Thread() {
            public void run() {
                Object resultObject = null;
                boolean success = false;
                String message = "";
                try {
                    resultObject = toRequest(what, dataMap);
                    success = true;
                } catch (Exception e) {
                    String err = e.getMessage() + "";
                    DebugLog.e(err);
                    message = err.equals("") || err.equals("null") ? "获取数据失败" : err;
                    success = false;
                }
                if (resultCallback != null) {
                    resultCallback.callbackInUI(success, resultObject, message);
                }

            }
        }.start();
    }

    private Object toRequest(REQUEST_WHAT what, Map<String, String> dataMap) throws Exception {
        Object result = null;
        switch (what) {
            case FM_LOGIN:
                result = fmController.fmLogin(dataMap);
                break;
            case FM_GETPHONE:
                result = fmController.fmGetPhone(dataMap);
                break;
            case FM_GETMESSAGE:
                result = fmController.fmGetMessage(dataMap);
                break;
            case QTT_REGITER_CHEAT:
                result = fmController.qttRegisterCheat(dataMap);
                break;
            default:
                break;
        }
        return result;
    }

}
