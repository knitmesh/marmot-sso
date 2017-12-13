package com.sohu.util;



import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
    private final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public String doPost(String url, Map<String,Object> map, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String,Object> elem = (Map.Entry<String,Object>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), (String) elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage()+"   url="+url);
        }finally{
            httpPost.releaseConnection();
        }
        return result;
    }

    public String doGet(String url) {
        HttpGet httpGet = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            httpGet = new HttpGet();
            httpGet.setURI(new URI(url));
            HttpResponse reponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = reponse.getEntity();
            if (httpEntity == null) {
                logger.info("HttpClientUtil doGet 发生异常");
                return null;
            } else {
                String value = EntityUtils.toString(httpEntity);
                return value;
            }
        } catch (Exception e) {
            logger.error(e.getMessage()+"url="+url);
        }finally{
            httpGet.releaseConnection();
        }
        return null;
    }

}
