/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.everlast.qtt.manager.model;

import java.util.ArrayList;

/**
 *
 * @author xuguobiao
 */
public class StadiumModel {

    private String title = "";
    private String image = "";
    private String detailUrl = "";

    private String stadiumResourceId = "";
    private String stadiumCode = "";
    private String site = "";
    private String period = "";

//    private String sportCode = "";

    private String cgtype = "";
    private String cgCode = "";
    private String cgId = "";
    private String terminal = "";

    private ArrayList<ItemModel> sportCodeList = new ArrayList<ItemModel>();
    private ArrayList<ItemModel> dateList = new ArrayList<ItemModel>();
    private ArrayList<ItemModel> timeList = new ArrayList<ItemModel>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getStadiumResourceId() {
        return stadiumResourceId;
    }

    public void setStadiumResourceId(String stadiumResourceId) {
        this.stadiumResourceId = stadiumResourceId;
    }

    public String getStadiumCode() {
        return stadiumCode;
    }

    public void setStadiumCode(String stadiumCode) {
        this.stadiumCode = stadiumCode;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public ArrayList<ItemModel> getSportCodeList() {
        return sportCodeList;
    }

//    public String getSportCode() {
//        return sportCode;
//    }
//
//    public void setSportCode(String sportCode) {
//        this.sportCode = sportCode;
//    }
    public void setSportCodeList(ArrayList<ItemModel> sportCodeList) {
        this.sportCodeList = sportCodeList;
    }

    public ArrayList<ItemModel> getDateList() {
        return dateList;
    }

    public void setDateList(ArrayList<ItemModel> dateList) {
        this.dateList = dateList;
    }

    public ArrayList<ItemModel> getTimeList() {
        return timeList;
    }

    public void setTimeList(ArrayList<ItemModel> timeList) {
        this.timeList = timeList;
    }

    public String getCgtype() {
        return cgtype;
    }

    public void setCgtype(String cgtype) {
        this.cgtype = cgtype;
    }

    public String getCgCode() {
        return cgCode;
    }

    public void setCgCode(String cgCode) {
        this.cgCode = cgCode;
    }

    public String getCgId() {
        return cgId;
    }

    public void setCgId(String cgId) {
        this.cgId = cgId;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public static String[] getTitleArray(ArrayList<StadiumModel> list) {
        String[] titles = new String[0];
        if (list != null && list.size() > 0) {
            titles = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                titles[i] = list.get(i).getTitle();
            }
        }
        return titles;
    }

}
