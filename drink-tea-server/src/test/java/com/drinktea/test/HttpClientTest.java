package com.drinktea.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class HttpClientTest {
    /**
     * 通过httpclient发送get请求
     */
    @Test
    public void testGet() throws IOException {
        // 创建request对象
        HttpGet httpGet = new HttpGet("http://localhost:8080/user/shop/status");
        try (
            // 创建client对象
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 发送请求
            CloseableHttpResponse response = httpClient.execute(httpGet)) {
                // 获得服务器返回的状态码
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("服务器返回的状态码为：" + statusCode);
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                System.out.println("服务端返回的数据为" + body);

        }
    }

    /**
     * 通过httpclient发送post请求
     */
    @Test
    public void testPost() throws Exception {
        HttpPost httpPost = new HttpPost("http://localhost:8080/admin/employee/login");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "admin");
        jsonObject.put("password", "123456");
        StringEntity requestEntity = new StringEntity(jsonObject.toString());
        // 指定请求编码方式
        requestEntity.setContentEncoding("UTF-8");
        // 数据格式
        requestEntity.setContentType("application/json");
        httpPost.setEntity(requestEntity);
        try (
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("服务器返回的状态码为：" + statusCode);
            HttpEntity responseEntity = response.getEntity();
            String body = EntityUtils.toString(responseEntity);
            System.out.println("服务端返回的数据为" + body);
        }
    }
}
