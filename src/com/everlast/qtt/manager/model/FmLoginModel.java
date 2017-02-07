/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.everlast.qtt.manager.model;

/**
 *
 * @author xuguobiao
 */
public class FmLoginModel {

    private String uName = "";
    private String pWord = "";
    //
    private String token = "";
    private double balance;
    private int maxLoginNum;
    private int maxGetPhoneTotalNum;
    private int maxGetPhoneSingelNum;
    private double discount;

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getpWord() {
        return pWord;
    }

    public void setpWord(String pWord) {
        this.pWord = pWord;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getMaxLoginNum() {
        return maxLoginNum;
    }

    public void setMaxLoginNum(int maxLoginNum) {
        this.maxLoginNum = maxLoginNum;
    }

    public int getMaxGetPhoneTotalNum() {
        return maxGetPhoneTotalNum;
    }

    public void setMaxGetPhoneTotalNum(int maxGetPhoneTotalNum) {
        this.maxGetPhoneTotalNum = maxGetPhoneTotalNum;
    }

    public int getMaxGetPhoneSingelNum() {
        return maxGetPhoneSingelNum;
    }

    public void setMaxGetPhoneSingelNum(int maxGetPhoneSingelNum) {
        this.maxGetPhoneSingelNum = maxGetPhoneSingelNum;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

}
