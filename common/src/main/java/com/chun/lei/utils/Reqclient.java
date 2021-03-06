package com.chun.lei.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chun.lei.model.QrMsg;
import com.chun.lei.sysconfig.WxConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created by lcl on 2020/4/28 0028
 */
public class Reqclient {

    public static String getWxOpenId(String code,String type){
        String reqUrl;
        if("xb".equals(type)){
            reqUrl = WxConfig.XB_WX_OPENID_URL.replace("CODE",code);
        }else {
            reqUrl = WxConfig.WX_OPENID_URL.replace("CODE",code);
        }
        return getReq(reqUrl);
    }

    //"access_token":"25_bg1bH8Vj3ajLu1X7ynv5OdP8nXvv2CV12t7_9HZ2ZkJgmmXUb7zgrvZfcxaFC2O2Dpu2jK3tc3lUNdyqWRJI4LscPxCHPfkVuHtcC2kGCL-XxPAIcRKqO3oH-DsgpDjL_SRUuet_EEBjdf0-CKIjAFARZX","expires_in":7200}
    public static String getWxAuth(){
        String reqUrl = WxConfig.WX_ACCESS_TOKEN_URL;
        String resu = getReq(reqUrl);
        String newtoken = JSONObject.parseObject(resu).getString("access_token");
        System.out.println(newtoken);
        return newtoken;
    }


    /**
     * 发送GET请求参数直接拼接
     */
    public static String getReq(String url){
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get);
            if(response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
                if(response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //参数为Map的get请求
    public String getMap(String url, Map<String,String> map) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> pairs = new ArrayList();
        for(Map.Entry<String,String> entry : map.entrySet()){
            pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            builder.setParameters(pairs);
            HttpGet get = new HttpGet(builder.build());
            response = httpClient.execute(get);
            if(response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
            }
            return result;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 发送Map的post请求
     * */
    public static String postMap(String url,Map<String,String> map) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for(Map.Entry<String,String> entry : map.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }
        CloseableHttpResponse response = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
            response = httpClient.execute(post);
            if(response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
                if(response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * Post发送joson
     * */
    public static String postJson(String url,String jsonString) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            post.setEntity(new ByteArrayEntity(jsonString.getBytes("UTF-8")));
            response = httpClient.execute(post);
            if(response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
                if(response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 依赖工具来
     * */
    private static String entityToString(HttpEntity entity) throws IOException {
        String result = null;
        if(entity != null) {
            long lenth = entity.getContentLength();
            if(lenth != -1 && lenth < 2048) {
                result = EntityUtils.toString(entity,"UTF-8");
            }else {
                InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
                CharArrayBuffer buffer = new CharArrayBuffer(2048);
                char[] tmp = new char[1024];
                int l;
                while((l = reader1.read(tmp)) != -1){
                    buffer.append(tmp, 0, l);
                }
                result = buffer.toString();
            }
        }
        return result;
    }

    /**
     * 依赖工具来
     * */
    public static Boolean getQrCode(String wxUrl, String fileName, QrMsg qrMsg){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(wxUrl);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(JSON.toJSONString(qrMsg), "UTF-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        InputStream inputStream;
        FileOutputStream out = null;
        try {
            response = httpClient.execute(httpPost);
//            System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                inputStream = response.getEntity().getContent();
                File file=new File("/usr/local/qr/wpzg/"+fileName);
                if (!file.exists()){
                    file.createNewFile();
                }
                out=new FileOutputStream(file);
                int len = 0;
                byte[] buf = new byte[512];
                while ((len = inputStream.read(buf, 0, 512)) != -1) {
                    out.write(buf, 0, len);
                }
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.close();
                httpClient.close();
            } catch (IOException e1) {
                e1.printStackTrace();
                return false;
            }
            return false;
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 发送GET请求参数直接拼接
     */
    public static Boolean testUrl(String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000).setConnectionRequestTimeout(8000)
                .setSocketTimeout(10000).build();
        get.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get);
            if(response != null && response.getStatusLine().getStatusCode() == 200) {
                return true;
            }else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }finally {
            try {
                httpClient.close();
                if(response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
