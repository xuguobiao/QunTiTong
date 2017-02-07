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
public class ItemModel {

    private String id = "";
    private String title = "";

    public ItemModel() {

    }

    public ItemModel(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static String[] getTitleArray(ArrayList<ItemModel> list) {
        String[] titles = new String[0];
        if (list != null && list.size() > 0) {
            titles = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                titles[i] = list.get(i).getTitle();
            }
        }
        return titles;
    }

    public static String getIdByTitle(ArrayList<ItemModel> modelList, String title) {
        for (ItemModel model : modelList) {
            if (model.getTitle().equals(title)) {
                return model.getId();
            }
        }
        return "";
    }

}
