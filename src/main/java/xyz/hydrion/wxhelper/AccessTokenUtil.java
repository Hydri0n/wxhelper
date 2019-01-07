package xyz.hydrion.wxhelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

public class AccessTokenUtil {

    private static String accessToken = "";

    public static void update(String appId, String secret, String grantType){
        String url = "https://api.weixin.qq.com/cgi-bin/token?";
        System.out.println("开始更新access_token");
        url = url + "grant_type=" + grantType + "&appid=" + appId + "&secret=" + secret;
        Map<String,Object> result = getMapFromApi(url);
        accessToken = (String) result.get("access_token");
        if (accessToken != null)
            System.out.println("AccessToken获取成功：" + accessToken);
        else if (result.get("errcode") != null)
            System.out.println("AccessToken获取失败:" + result.get("errmsg"));
    }

    public static String getAccessToken() {
        return accessToken;
    }

    @SuppressWarnings("unchecked")
    private static Map<String,Object> getMapFromApi(String api){
        Map<String,Object> map = new HashMap<String, Object>();
        OkHttpClient httpClient = new OkHttpClient();
        Call call = httpClient.newCall(new Request.Builder().url(api).build());
        try {
            String response = call.execute().body().string();
            map.putAll((Map) JSON.parse(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
