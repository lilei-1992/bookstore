package com.yd.utils;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class HttpClientUtil {

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * get请求
     *
     * @param url        请求路径
     * @param parameters 请求参数
     * @param headers    请求头
     * @return 响应体
     */
    public static String doGet(String url, Map<String, String> parameters, Map<String, String> headers) {
        HttpGet httpGet;
        if (Objects.nonNull(parameters) && parameters.size() != 0) {
            httpGet = new HttpGet(getUriBuilder(url, parameters));
        } else {
            httpGet = new HttpGet(url);
        }
        if (Objects.nonNull(headers) && headers.size() != 0) {
            headers.forEach(httpGet::setHeader);
        }
        return getContent(httpGet);
    }

    /**
     * post请求
     *
     * @param url        请求路径
     * @param parameters 请求参数
     * @param headers    请求头
     * @param body       请求体
     * @return 响应体
     */
    public static String doPost(String url, Map<String, String> parameters, Map<String, String> headers, String body) {
        HttpPost httpPost;
        if (Objects.nonNull(parameters) && parameters.size() != 0) {
            httpPost = new HttpPost(getUriBuilder(url, parameters));
        } else {
            httpPost = new HttpPost(url);
        }
        if (Objects.nonNull(headers) && headers.size() != 0) {
            headers.forEach(httpPost::setHeader);
        }
        try {
            httpPost.setEntity(new StringEntity(body));
            return getContent(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载
     *
     * @param url     请求路径
     * @param path    下载保存路径
     * @param headers 请求头
     * @return 保存的路径包含文件名
     */
    public static String download(String url, String path, Map<String, String> headers) {
        String suffix = url.substring(url.lastIndexOf("."));
        if (!path.endsWith("/") && !path.endsWith("\"")) {
            path = path + "/";
        }
        String savePath = path + UUID.randomUUID().toString().replace("-", "") + suffix;
        File file = createFile(savePath);
        HttpGet httpGet = new HttpGet(url);
        if (Objects.nonNull(headers) && headers.size() != 0) {
            headers.forEach(httpGet::setHeader);
        }
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            byte[] bytes = IOUtils.toByteArray(response.getEntity().getContent());
            FileUtils.writeByteArrayToFile(file, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return savePath;
    }

    /**
     * 上传
     *
     * @param url  请求路径
     * @param file 上传的资源
     * @return 响应体
     */
    public static String upload(String url, File file) {
        HttpPost httpPost = new HttpPost(url);
        FileBody fileBody = new FileBody(file);
        HttpEntity entity = MultipartEntityBuilder.create().addPart("file", fileBody).build();
        httpPost.setEntity(entity);
        return getContent(httpPost);
    }

    private static URI getUriBuilder(String url, Map<String, String> parameters) {
        URIBuilder uriBuilder;
        try {
            uriBuilder = new URIBuilder(url);
            parameters.forEach(uriBuilder::addParameter);
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getContent(HttpRequestBase httpRequestBase) {
        String content = null;
        try (CloseableHttpResponse response = httpClient.execute(httpRequestBase)) {
            content = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private static File createFile(String savePath) {
        File file = new File(savePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
