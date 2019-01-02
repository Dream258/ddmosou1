package com.xxx.common.util;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;

public class WeChatUtils {

    /** 获取微信登录信息 */
    public static Map weChat(String code){
        Map<String, Object> map = new HashMap<>();
        try {
            Map map1 = getAccessToken(code);
            String token = map1.get("access_token").toString();
            String openid = map1.get("openid").toString();
            Map map2 = testToken(token,openid);
            if("ok".equals(map2.get("errmsg").toString())){
                map = userinfo(token,openid);
            }else{
                Map map3 = refreshToken(map1.get("refresh_token").toString());
                token = map3.get("access_token").toString();
                openid = map3.get("openid").toString();
                map = userinfo(token,openid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /** 获取微信AccessToken */
    public static Map getAccessToken(String code){
        Map<String, Object> map = new HashMap<>();
        try {
            StringBuilder url = new StringBuilder();
            url.append("https://api.weixin.qq.com/sns/oauth2/access_token");
            url.append("?grant_type=authorization_code");
            url.append("&appid="+DictionaryUtils.WE_CHAT_APP_ID);
            url.append("&secret="+DictionaryUtils.WE_CHAT_APP_SECRET);
            url.append("&code="+code);
            String result = HttpClientUtils.get(url.toString(),"UTF-8");
            map = JSON.parseObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /** 刷新微信AccessToken */
    public static Map refreshToken(String token){
        Map<String, Object> map = new HashMap<>();
        try {
            StringBuilder url = new StringBuilder();
            url.append("https://api.weixin.qq.com/sns/oauth2/refresh_token");
            url.append("?grant_type=refresh_token");
            url.append("&appid="+DictionaryUtils.WE_CHAT_APP_ID);
            url.append("&refresh_token="+token);
            String result = HttpClientUtils.get(url.toString(),"UTF-8");
            map = JSON.parseObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /** 检验微信AccessToken */
    public static Map testToken(String token,String openid){
        Map<String, Object> map = new HashMap<>();
        try {
            StringBuilder url = new StringBuilder();
            url.append("https://api.weixin.qq.com/sns/auth");
            url.append("?openid="+openid);
            url.append("&access_token="+token);
            String result = HttpClientUtils.get(url.toString(),"UTF-8");
            map = JSON.parseObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /** 获取微信用户信息 */
    public static Map userinfo(String token,String openid){
        Map<String, Object> map = new HashMap<>();
        try {
            StringBuilder url = new StringBuilder();
            url.append("https://api.weixin.qq.com/sns/userinfo");
            url.append("?openid="+openid);
            url.append("&access_token="+token);
            String result = HttpClientUtils.get(url.toString(),"UTF-8");
            map = JSON.parseObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
