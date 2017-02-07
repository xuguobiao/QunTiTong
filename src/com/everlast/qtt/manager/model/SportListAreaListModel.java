/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.everlast.qtt.manager.model;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class SportListAreaListModel {

    private ArrayList<ItemModel> sportList = new ArrayList<ItemModel>();
    private ArrayList<ItemModel> areaList = new ArrayList<ItemModel>();

    public ArrayList<ItemModel> getSportList() {
        return sportList;
    }

    public void setSportList(ArrayList<ItemModel> sportList) {
        this.sportList = sportList;
    }

    public ArrayList<ItemModel> getAreaList() {
        return areaList;
    }

    public void setAreaList(ArrayList<ItemModel> areaList) {
        this.areaList = areaList;
    }

}
