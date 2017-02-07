package com.everlast.qtt.manager.controller;

import com.everlast.qtt.manager.common.DebugLog;
import com.everlast.qtt.manager.model.ItemModel;
import com.everlast.qtt.manager.model.OrderModel;
import com.everlast.qtt.manager.model.SportListAreaListModel;
import com.everlast.qtt.manager.model.StadiumModel;
import java.util.ArrayList;
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
public class ApiController3 {

    public enum REQUEST_WHAT {

        Login_Init, VerifyCode, Login, Get_SportTypeList_AreaList, Stadium_List, Stadium_Select, Stadium_TimeSection, Stadium_Query, Select_Suer, Submit_Order,
    }

    private QttQiangController qttQiangController;

    public ApiController3() {
        qttQiangController = new QttQiangController();
    }

    public void startLoginInit(ResultCallback<Boolean> resultCallback) {
        commonControl(REQUEST_WHAT.Login_Init, null, resultCallback);
    }

    public void getVerifyCode(ResultCallback<byte[]> resultCallback) {
        commonControl(REQUEST_WHAT.VerifyCode, null, resultCallback);
    }

    public void login(String phone, String pwd, String code, ResultCallback<Boolean> resultCallback) {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("mobilephone", phone);
        dataMap.put("password", pwd);
        dataMap.put("verifyCodes", code);
        commonControl(REQUEST_WHAT.Login, dataMap, resultCallback);
    }

    public void getSportTypeListAreaList(ResultCallback<SportListAreaListModel> resultCallback) {
        commonControl(REQUEST_WHAT.Get_SportTypeList_AreaList, null, resultCallback);
    }

    public void getStadiumList(String sportCode, String area, String searchContent, ResultCallback<ArrayList<StadiumModel>> resultCallback) {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("sortType", "0");
        dataMap.put("area", area);
        dataMap.put("sportCode", sportCode);
        dataMap.put("searchContent", searchContent);
        dataMap.put("pageIndex", "1");
        dataMap.put("pageSize", "20");
        dataMap.put("totalRecord", "");
        dataMap.put("isUserForm", "false");

        commonControl(REQUEST_WHAT.Stadium_List, dataMap, resultCallback);
    }

    public void selectStadium(String stadiumResourceId, ResultCallback<StadiumModel> resultCallback) {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("stadiumResourceId", stadiumResourceId);
        commonControl(REQUEST_WHAT.Stadium_Select, dataMap, resultCallback);
    }

    public void requestTimeSection(StadiumModel stadiumModel, String sportCode, String subscribeDate, ResultCallback<ArrayList<ItemModel>> resultCallback) {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("stadiumResourceId", stadiumModel.getStadiumResourceId());
        dataMap.put("stadiumCode", stadiumModel.getStadiumCode());
        dataMap.put("site", stadiumModel.getSite());
        dataMap.put("period", stadiumModel.getPeriod());
        dataMap.put("sportCode", sportCode);
        dataMap.put("subscribeDate", subscribeDate);
        commonControl(REQUEST_WHAT.Stadium_TimeSection, dataMap, resultCallback);
    }

    public void query(StadiumModel stadiumModel, String sportCode, String subscribeDate, String time, ResultCallback<ArrayList<OrderModel>> resultCallback) {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("stadiumResourceId", stadiumModel.getStadiumResourceId());
        dataMap.put("stadiumCode", stadiumModel.getStadiumCode());
        dataMap.put("site", stadiumModel.getSite());
        dataMap.put("period", stadiumModel.getPeriod());
        dataMap.put("sportCode", sportCode);
        dataMap.put("subscribeDate", subscribeDate);
        dataMap.put("time", time);
        commonControl(REQUEST_WHAT.Stadium_Query, dataMap, resultCallback);
    }

    public void submitOrder(StadiumModel stadiumModel, OrderModel orderModel, ResultCallback<Boolean> resultCallback) {
        Map<String, String> dataMap = new HashMap<String, String>();

        String fieIds_storeIds = orderModel.getStadiumFieId() + "_" + orderModel.getStoreId();
        dataMap.put("cgId", stadiumModel.getCgId());
        dataMap.put("cgtype", stadiumModel.getCgtype());
        dataMap.put("cgCode", stadiumModel.getCgCode());
        dataMap.put("terminal", stadiumModel.getTerminal());

        dataMap.put("fieIds_storeIds", fieIds_storeIds);
        dataMap.put("storeIds", orderModel.getStoreId());
        dataMap.put("priceIds", "");

        dataMap.put("pay", "0");
        dataMap.put("payment", "1");
        dataMap.put("pricetemp2", "S");
        dataMap.put("pricestoreIds", orderModel.getStoreId() + "_");

        dataMap.put("pricepriceIds", orderModel.getPrice() + "_");
        dataMap.put("num" + orderModel.getStoreId(), "1");
        dataMap.put("sub2total", orderModel.getPrice());
        dataMap.put("ordertotal", orderModel.getPrice());
        dataMap.put("agreeContext", "Y");
        commonControl(REQUEST_WHAT.Submit_Order, dataMap, resultCallback);
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
                    DebugLog.log("Exception->" + err);
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
            case Login_Init:
                result = qttQiangController.startLoginInit();
                break;
            case VerifyCode:
                result = qttQiangController.getVerifyCode();
                break;
            case Login:
                result = qttQiangController.login(dataMap);
                break;
            case Get_SportTypeList_AreaList:
                result = qttQiangController.getSportTypeListAreaList();
                break;
            case Stadium_List:
                result = qttQiangController.getStadiumList(dataMap);
                break;
            case Stadium_Select:
                result = qttQiangController.selectStadium(dataMap);
                break;
            case Stadium_TimeSection:
                result = qttQiangController.requestTimeSections(dataMap);
                break;
            case Stadium_Query:
                result = qttQiangController.query(dataMap);
                break;
            case Select_Suer:
                result = qttQiangController.selectSuer(dataMap);
                break;
            case Submit_Order:
                result = qttQiangController.submitOrder(dataMap);
                break;
            default:
                break;
        }
        return result;
    }

}
