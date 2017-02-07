package com.everlast.qtt.manager.controller;

import com.everlast.qtt.manager.common.DebugLog;
import com.everlast.qtt.manager.model.FmLoginModel;
import com.everlast.qtt.manager.common.CommonUtils;
import java.util.Map;

/**
 * @author: XuGuobiao
 * @email: everlastxgb@gmail.com
 *
 * @create_time: 2015-9-1 10:51:15
 *
 */
public class FmController extends BaseController {

    protected static String token = "";

    public FmLoginModel fmLogin(Map<String, String> dataMap) throws Exception {

        dataMap.put("Developer", URLs.FMDeveloper);
        String body = requestBodyString(URLs.FM_LOGIN, HTTP_GET, dataMap);
        DebugLog.log("fmLogin return->" + body);//登录token&账户余额&最大登录客户端个数&最多获取号码数&单个客户端最多获取号码数&折扣
        String[] strs = body.split("&");
        FmLoginModel model = new FmLoginModel();

        model.setToken(strs[0]);
        model.setBalance(CommonUtils.parse2Double(strs[1]));
        model.setMaxLoginNum(CommonUtils.parse2Int(strs[2]));
        model.setMaxGetPhoneTotalNum(CommonUtils.parse2Int(strs[3]));
        model.setMaxGetPhoneSingelNum(CommonUtils.parse2Int(strs[4]));
        model.setDiscount(CommonUtils.parse2Double(strs[5]));
        token = model.getToken();
        return model;
    }

    public String[] fmGetPhone(Map<String, String> dataMap) throws Exception {
        dataMap.put("token", token);
        dataMap.put("ItemId", URLs.FM_QTT_PROID);
        dataMap.put("PhoneType", "0");
        dataMap.put("Area", "广东");

        String body = requestBodyString(URLs.FM_GETPHONE, HTTP_GET, dataMap);
        DebugLog.log("fmLogin return->" + body);

        String[] phones = body.split(";");
        return phones;
    }

    public String fmGetMessage(Map<String, String> dataMap) throws Exception {
        dataMap.put("token", token);

        String body = requestBodyString(URLs.FM_GETMESSAGE, HTTP_GET, dataMap);
        DebugLog.log("fmLogin return->" + body);

        return body;
    }

    public void qttPhoneVerCode(Map<String, String> dataMap) throws Exception {
        String body = requestBodyString(URLs.QTT_PHONE_VERCODE, HTTP_GET, dataMap);
        DebugLog.log("fmLogin return->" + body);
    }

    public void qttLoginAccount(Map<String, String> dataMap) throws Exception {

    }

    public String qttRegisterCheat(Map<String, String> dataMap) throws Exception {
        String mobilePhone = dataMap.get("Phone");
        String firstUrlString = "http://tytapp.quntitong.cn/sportinterNew/androidaccount/phoneVerCode.do?service=hn&os=ios&deviceid=6CBE535D4AA946E8AF1F3D75D1342AB6&imsi=EA3761541F72400DA7F6971DCBF4ADE3&version=2.3&timestamp=1450854866.079797&authstring=c947bbdd0d09d6c00f1dccb8eb8dadac&authstring=71fb8e605f31f84d62b0128b0ab75dd8&timeStamp=1450854866&mobilePhone=";
        firstUrlString += mobilePhone;
        String phoneVerCode = requestBodyString(firstUrlString, HTTP_POST, null);
        int resultCode = CommonUtils.parse2Int(phoneVerCode);
        if (resultCode > 0) {
            String secondUrlString = "http://tytapp.quntitong.cn/sportinterNew/androidaccount/createAnAccount.do?service=hn&os=ios&deviceid=50EC0401295F45B6B2D552EB9EBA0137&imsi=1A43D8EA9FAE45BEBAB9B9015F5D8E2E&version=2.3&timestamp=1450854967.948007&authstring=3f6ec8a45276226b915c867ae4703119&password=4FA1C9D1CD9CCD31D296546332F32AEA&timestamp=1450854967.947858&authstring=47f1f1599a17f0c342d37cecfe0e6af4&idcard=";
            secondUrlString += "&mobilePhone=" + mobilePhone + "&applyName=" + mobilePhone;

            String createAnAccount = requestBodyString(secondUrlString, HTTP_POST, null);
            if (createAnAccount.split(",")[0].contains("\"100\"")) {
                return createAnAccount;
            } else {
                throw new Exception(createAnAccount);
            }
        } else {
            String errString = resultCode == -101 ? "手机号已被注册" : resultCode == -102 ? "号码有问题，仅限广东省内" : "注册失败，code=" + phoneVerCode;
            throw new Exception(errString);
        }
    }

    private String getRandomInt(int wei) {
        String ranInt = "";
        for (int i = 0; i < wei; i++) {
            int digit = (int) (Math.random() * 10f);
            ranInt += digit;
        }

        return ranInt;
    }

}
