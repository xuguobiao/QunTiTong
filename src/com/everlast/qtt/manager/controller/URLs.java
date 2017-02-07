package com.everlast.qtt.manager.controller;

public class URLs {

    /**
     * ********** 飞码验证平台 *******************
     */
    public static final String FMDeveloper = "LcC9VpqXJnytDX8%2bC9S3tA%3d%3d";
    public static final String FM_QTT_PROID = "10820";

    public static final String FM_SERVER_URL = "http://xapi.83r.com";
    public static final String FM_LOGIN = FM_SERVER_URL + "/User/login";
    public static final String FM_GETPHONE = FM_SERVER_URL + "/User/getPhone";

    public static final String FM_GETMESSAGE = FM_SERVER_URL + "/User/getMessage";
    
    public static final String QTT_CN_SERVER = "http://www.quntitong.cn";
    public static final String QTT_PHONE_VERCODE = QTT_CN_SERVER + "/sport/account/phoneVerCode.do";
    public static final String QTT_LOGIN_ACCOUNT = QTT_CN_SERVER + "/sport/account/loginAccount.do";

    /**
     * ********** 群体通 *******************
     */
    public static final String QTT_SERVER = "http://www.quntitong.cn";

    public static final String QTT_INIT_LOGIN = QTT_SERVER + "/sport/login.jsp";
    public static final String QTT_VERIFYCODE = QTT_SERVER + "/sport/image.jsp";

    public static final String QTT_LOGIN = QTT_SERVER + "/sport/login/login.do";

    public static final String QTT_STADIUM_LIST = QTT_SERVER + "/sport/stadium/list.do";
    public static final String QTT_STADIUM_QUERY = QTT_SERVER + "/sport/stadium/query.do";
    public static final String QTT_STADIUM_SELECT = QTT_SERVER + "/sport/stadium/selectStadium.do";
    public static final String QTT_STADIUM_DETAIL = QTT_SERVER + "/sport/stadium/detail.do?resourceId=";

    public static final String QTT_selectSuer = QTT_SERVER + "/sport/stadium/selectSuer.do";
    public static final String QTT_submitOrder = QTT_SERVER + "/sport/order/submitOrder.do";
}
