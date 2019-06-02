package com.example.demo.Util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by forget on 2019/3/5.
 */
public class HttpUtil {

    /**
     * s使用java原生HttpClient调用外部API
     */
    public void dopost() throws MalformedURLException {
        URL url = new URL("");
        // HttpClientParams params=new HttpClientParams();

        // HttpClient httpCient = new HttpClient();
    }

    public static String getJson(String url) throws Exception {
        //创建默认的httpClient实例.
        CloseableHttpClient httpclient = null;
        //接收响应结果
        CloseableHttpResponse response = null;
        String resStr = null;
        try {
            //创建httppost
            httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            response = httpclient.execute(httpGet);
            //解析返结果
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                resStr = EntityUtils.toString(entity, "UTF-8");
            } else {
                System.out.println("响应内容不存在");
            }
            return resStr;
        } catch (Exception e) {
            throw e;
        } finally {
            httpclient.close();
        }
    }

    public static String getRedirectInfo(String uri) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext httpContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(uri);
        try {
            //将HttpContext对象作为参数传给execute()方法,则HttpClient会把请求响应交互过程中的状态信息存储在HttpContext中
            HttpResponse response = httpClient.execute(httpGet, httpContext);
            //获取重定向之后的主机地址信息,即"http://127.0.0.1:8088"
            HttpHost targetHost = (HttpHost) httpContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
            //获取实际的请求对象的URI,即重定向之后的"/blog/admin/login.jsp"
            HttpUriRequest realRequest = (HttpUriRequest) httpContext.getAttribute(ExecutionContext.HTTP_REQUEST);
            String url = targetHost + realRequest.getURI().toString();
            if (response.getStatusLine().getStatusCode() == 404) {
                url = url.substring(0, url.length() - 14);
                return url;
            }
            return null;
        } catch (Exception e) {
            System.out.println("发生异常，连接地址" + uri);
            return null;
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }

    public static CloseableHttpResponse getHttpEntity(String url) throws Exception {
        //创建默认的httpClient实例.
        CloseableHttpClient httpclient = null;
        //接收响应结果
        CloseableHttpResponse response = null;
        String resStr = null;
        try {
            //创建httppost
            //      Header header = new BasicHeader("Range", "bytes=" + startIndex + "-" + endIndex);
            httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            response = httpclient.execute(httpGet);
            //解析返结果
            //  HttpEntity entity = response.getEntity();
            return response;
        } catch (Exception e) {
            throw e;
        } finally {
            httpclient.close();
//            response.close();
        }
    }

    public static String postJson(String url, String JsonParam) throws IOException {
        //创建默认的httpClient实例.
        CloseableHttpClient httpclient = null;
        //接收响应结果
        CloseableHttpResponse response = null;
        String resStr = null;
        try {
            //创建httppost
            httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
            //参数
            StringEntity se = new StringEntity(JsonParam);
            se.setContentEncoding("UTF-8");
            se.setContentType("application/json");//发送json需要设置contentType
            httpPost.setEntity(se);
            response = httpclient.execute(httpPost);
            //解析返结果
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                resStr = EntityUtils.toString(entity, "UTF-8");
                System.out.println(resStr);
            } else {
                System.out.println("响应内容不存在");
            }
            return resStr;
        } catch (Exception e) {
            throw e;
        } finally {
            httpclient.close();
            response.close();
        }
    }

    /*   public static boolean WriteData(String jsonstr){
           JSONObject json= JSON.parseObject(jsonstr);
           json=JSON.parseObject(json.get("data").toString());
           JSONArray jsonArray=JSON.parseArray(json.get("songs").toString());
           for(int i=0;i<jsonArray.size();i++)
           {
               json=(JSONObject) jsonArray.get(i);
               if (MService==null)System.out.println("MService IS  NULL");
               Music music=new Music();
               music.setMusicName(json.getString("name"));
               music.setMusicImg(json.getString("pic"));
               music.setSinger(json.getString("singer"));
               music.setLanguage("中文");
               music.setMusicAddress(json.getString("url"));
               music.setTime(parseTime(json.getInteger("time")));
               MService.InsertMusic(music);
           }
               return true;
       }*/
    public static String parseTime(int s) {
        String str = "0" + s / 60 + ":";
        if ((s / 60) < 10 && (s / 60) > 0) {
            str = "0" + (s / 60) + ":";
        } else if ((s / 60) < 1) {
            str = "00:";
        } else {
            str = s / 60 + ":";
        }

        if ((s % 60) < 10) {
            str = str + "0" + (s % 60);
        } else {
            str = str + (s % 60);
        }
        return str;
    }
}
