package com.everlast.qtt.manager.controller;

import com.everlast.qtt.manager.common.DebugLog;
import com.everlast.qtt.manager.model.OrderModel;
import com.everlast.qtt.manager.common.CommonUtils;
import com.everlast.qtt.manager.model.ItemModel;
import com.everlast.qtt.manager.model.SportListAreaListModel;
import com.everlast.qtt.manager.model.StadiumModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author: XuGuobiao
 * @email: everlastxgb@gmail.com
 *
 * @create_time: 2015-9-1 10:51:15
 *
 */
public class QttQiangController extends BaseController {

    public boolean startLoginInit() throws Exception {
        Document doc = requestDocument(URLs.QTT_INIT_LOGIN, HTTP_GET, null);
        boolean success = doc.getElementById("quntitong") != null;
        if (!success) {
            throw new Exception("初始化登录页失败");
        }
        DebugLog.log("startLoginInit->" + doc.toString());
        return success;
    }

    public byte[] getVerifyCode() throws Exception {
        startLoginInit();
        String urlString = URLs.QTT_VERIFYCODE + "?key=" + Math.random();
        byte[] imageBytes = requestImage(urlString, HTTP_GET, null);
        return imageBytes;
    }

    public boolean login(Map<String, String> dataMap) throws Exception {

        String code = dataMap.get("verifyCodes") + "";
        String phone = dataMap.get("mobilephone") + "";

        String urlString = URLs.QTT_LOGIN + "?loginType=undefined&verifyCodes=" + code + "&key=" + Math.random();
        Document document = requestDocument(urlString, HTTP_POST, dataMap);
        String info = document.getElementById("uphone").text();
        if (phone.equals(info.trim())) {
            return true;
        }
        return false;
    }

    public SportListAreaListModel getSportTypeListAreaList() throws Exception {
        SportListAreaListModel saListModel = new SportListAreaListModel();

        ArrayList<ItemModel> sportModelList = new ArrayList<ItemModel>();
        ArrayList<ItemModel> areaModelList = new ArrayList<ItemModel>();

        Document document = requestDocument(URLs.QTT_STADIUM_LIST, HTTP_GET, null);
        Element pre = document.getElementById("cgSearchTr");
        Elements lines = pre.siblingElements();
        Elements sports = lines.get(0).select("td a[onclick]");
        for (Element sport : sports) {
            String clickString = sport.attr("onclick");
            String id = clickString.substring(clickString.indexOf("'") + 1, clickString.indexOf("')"));
            String title = sport.text();
            ItemModel itemModel = new ItemModel(id, title);
            sportModelList.add(itemModel);
        }

        Elements areas = lines.get(1).select("td a[onclick]");
        for (Element area : areas) {
            String clickString = area.attr("onclick");
            String id = clickString.substring(clickString.indexOf("'") + 1, clickString.indexOf("')"));
            String title = area.text();
            ItemModel itemModel = new ItemModel(id, title);
            areaModelList.add(itemModel);
        }

        saListModel.setSportList(sportModelList);
        saListModel.setAreaList(areaModelList);

        return saListModel;
    }

    public ArrayList<StadiumModel> getStadiumList(Map<String, String> dataMap) throws Exception {
        ArrayList<StadiumModel> modelList = new ArrayList<StadiumModel>();

        String urlString = URLs.QTT_STADIUM_LIST + "?key=" + Math.random();
        Document document = requestDocument(urlString, HTTP_POST, dataMap);

        Elements venus = document.select("div.hot_venues_main_list");
        for (Element ve : venus) {
            String href = ve.select("div a").get(0).attr("href");
            String resourceId = href.split("=")[1];
            String name = ve.select("tbody tr").get(0).text();
            StadiumModel model = new StadiumModel();
            model.setStadiumResourceId(resourceId);
            model.setTitle(name);
//            model.setSportCode(dataMap.get("sportCode"));
            modelList.add(model);
        }
        return modelList;
    }

    public StadiumModel selectStadium(Map<String, String> dataMap) throws Exception {
        Document document = requestDocument(URLs.QTT_STADIUM_SELECT, HTTP_GET, dataMap);
        Element theForm = document.getElementById("theForm");
        String stadiumResourceId = theForm.getElementById("stadiumResourceId").val();
        String stadiumCode = theForm.getElementById("stadiumCode").val();
        String site = theForm.getElementById("site").val();
        String period = theForm.getElementById("period").val();

        ArrayList<ItemModel> sportCodeList = new ArrayList<>();
        Elements sportCodes = theForm.select("#sportCode option");
        for (Element sportCode : sportCodes) {
            String id = sportCode.val();
            String title = sportCode.text();
            sportCodeList.add(new ItemModel(id, title));
        }

        ArrayList<ItemModel> dateList = new ArrayList<>();
        Elements dates = theForm.select("#subscribeDate option");
        for (Element date : dates) {
            String id = date.val();
            String title = date.text();
            dateList.add(new ItemModel(id, title));
        }

        Element submitForm = document.getElementById("submitForm");
        String cgtype = submitForm.getElementById("cgtype").val();
        String cgCode = submitForm.getElementById("cgCode").val();
        String cgId = submitForm.getElementById("cgId").val();
        String terminal = submitForm.getElementById("terminal").val();

        StadiumModel model = new StadiumModel();
        model.setStadiumResourceId(stadiumResourceId);
        model.setStadiumCode(stadiumCode);
        model.setSite(site);
        model.setPeriod(period);
        model.setSportCodeList(sportCodeList);
        model.setDateList(dateList);
//        model.setTimeList(timeList);

        model.setCgtype(cgtype);
        model.setCgCode(cgCode);
        model.setCgId(cgId);
        model.setTerminal(terminal);

        //dynamically request to get time section
//        try {
//            Map<String, String> tempMap = new HashMap<String, String>();
//            tempMap.put("stadiumResourceId", model.getStadiumResourceId());
//            tempMap.put("stadiumCode", model.getStadiumCode());
//            tempMap.put("site", model.getSite());
//            tempMap.put("period", model.getPeriod());
//            tempMap.put("sportCode", sportCodeList.get(0).getId());
//            tempMap.put("subscribeDate", dateList.get(1).getId());
//            ArrayList<ItemModel> timeList = requestTimeSections(tempMap);
//            model.setTimeList(timeList);
//        } catch (Exception e) {
//        }
        return model;
    }

    public ArrayList<ItemModel> requestTimeSections(Map<String, String> dataMap) throws Exception {
        ArrayList<ItemModel> timeList = new ArrayList<>();
        String urlString = URLs.QTT_STADIUM_QUERY + "?key=" + Math.random();
        Document timeDocument = requestDocument(urlString, HTTP_POST, dataMap);
        Elements times = timeDocument.select("#onLineStore th");
        times.remove(0);//
        for (Element time : times) {
            String id = time.text().trim();
            String title = id;
            if (id.length() > 4) {
                timeList.add(new ItemModel(id, title));
            }
        }
        return timeList;
    }

    public ArrayList<OrderModel> query(Map<String, String> dataMap) throws Exception {

        String time = dataMap.get("time") + "";

        ArrayList<OrderModel> modelList = new ArrayList<OrderModel>();
        String urlString = URLs.QTT_STADIUM_QUERY + "?key=" + Math.random();
        Document document = requestDocument(urlString, HTTP_POST, dataMap);
        DebugLog.log("query->" + document.toString());

        String[] times = time.split("-");
        String startTime = times[0].trim();
        String endTime = times[1].trim();
        Element table = document.getElementById("onLineStore");

        Elements ths = table.select("th");

        int targetColunm = -1;
        for (Element th : ths) {
            String thText = th.text().trim();
            targetColunm++;
            if (thText.startsWith(startTime) && thText.endsWith(endTime)) {
                break;
            }
        }
        if (targetColunm == -1) {
            return null;
        }
        Elements trs = table.select("tr");
        for (int i = 1; i < trs.size(); i++) {//first line is the header
            Element tr_td = trs.get(i).child(targetColunm);
            Elements td_divs = tr_td.select("div[onclick]");
            if (td_divs != null && td_divs.size() > 0) {//available 
                Element td_div = td_divs.get(0);
                String clickString = td_div.attr("onclick");
                String params = clickString.substring(clickString.indexOf("(") + 1, clickString.lastIndexOf(")")).replace("'", "");
                String[] paramArray = params.split(",");

                OrderModel model = new OrderModel();
                model.setStadiumFieId(paramArray[0]);
                model.setStadiumFieName(paramArray[1]);
                model.setStoreId(paramArray[2]);
                model.setOpenDate(paramArray[3]);
                model.setPrice(paramArray[4]);
                model.setPrice1(paramArray[5]);
                model.setPrice2(paramArray[6]);
                model.setPrice3(paramArray[7]);
                model.setPrice4(paramArray[8]);
                model.setPrice5(paramArray[9]);
                model.setPrice6(paramArray[10]);
                model.setTimeSection(paramArray[11]);
                model.setOrderNum(paramArray[12]);
                model.setSportCode(paramArray[13]);
                model.setAccountNum(paramArray[14]);
                model.setCostmoney(paramArray[15]);

                modelList.add(model);
            }
        }
        return modelList;
    }

    public boolean selectSuer(Map<String, String> dataMap) throws Exception {

        String cgtype = dataMap.get("cgtype");
        String fieIds_storeIds = dataMap.get("fieIds_storeIds") + "";

        String urlString = URLs.QTT_selectSuer + "?cgtype=" + cgtype + "&key=" + Math.random();
        Document document = requestDocument(urlString, HTTP_POST, dataMap);
        DebugLog.log("selectSuer->" + document.toString());
        if (fieIds_storeIds.equals(document.getElementById("fieIds_storeIds").val())) {
            return true;
        }
        return false;
    }

    public boolean submitOrder(Map<String, String> dataMap) throws Exception {

        String urlString = URLs.QTT_submitOrder + "?key=" + Math.random();

        Document document = requestDocument(urlString, HTTP_POST, dataMap);
        DebugLog.log("submitOrder->" + document.toString());
        if (document.getElementById("orderId") != null) {
            return true;
        }
        return false;
    }
    
}
