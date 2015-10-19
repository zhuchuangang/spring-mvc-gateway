package com.szss.gateway.test;

import com.google.gson.Gson;
import com.szss.commons.security.utils.Constants;
import com.szss.commons.security.utils.RestTestUtils;
import com.szss.commons.security.utils.SortCollection;
import com.szss.gateway.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/9.
 */
public class RestGatewayTest {
    private RestTemplate restTemplate = new RestTemplate();
    private Gson gson = new Gson();

    @Test
    public void test1() throws Exception {
        String resource = "/gateway";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add(Constants.REQUEST_HTTP_METHOD_PARAMETER_NAME, "POST");
        params.add(Constants.REQUEST_METHOD_PARAMETER_NAME, "test.user.login");
        params.add(Constants.REQUEST_URI_PARAMETER_NAME, resource);
        params.add(Constants.API_KEY_PARAMETER_NAME, RestTestUtils.APIKEY);
        params.add(Constants.REQUEST_TIMESTAMP_PARAMETER_NAME, RestTestUtils.timestamp());
        String sourceStr = SortCollection.sort(RestTestUtils.APPSECRET, params);
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        String sign = encoder.encodePassword(sourceStr, "");
        params.add(Constants.SECURE_HASH_PARAMETER_NAME, sign);
        Map<String,Object> p=new HashMap<String, Object>();
        params.add("name", "zhu");
        params.add("password", "123456");
        params.add("manager.name", "gang");
        params.add("manager.password", "654321");
        String url = UriComponentsBuilder.fromHttpUrl(RestTestUtils.IP + resource).build().toUriString();
        ResponseEntity responseEntity = restTemplate.postForEntity(url, params, String.class);
        System.out.print(responseEntity.getBody().toString());
    }

    @Test
    public void test2() throws Exception {
        String resource = "/gateway";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add(Constants.REQUEST_HTTP_METHOD_PARAMETER_NAME, "POST");
        params.add(Constants.REQUEST_METHOD_PARAMETER_NAME, "test.user.json");
        params.add(Constants.REQUEST_URI_PARAMETER_NAME, resource);
        params.add(Constants.API_KEY_PARAMETER_NAME, RestTestUtils.APIKEY);
        params.add(Constants.REQUEST_TIMESTAMP_PARAMETER_NAME, RestTestUtils.timestamp());
        String sourceStr = SortCollection.sort(RestTestUtils.APPSECRET, params);
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        String sign = encoder.encodePassword(sourceStr, "");
        params.add(Constants.SECURE_HASH_PARAMETER_NAME, sign);
        Map<String,Object> p=new HashMap<String, Object>();
        User user=new User();
        user.setName("zhu");
        user.setPassword("123456");
        User manager=new User();
        manager.setName("gang");
        manager.setPassword("654321");
        user.setManager(manager);
        String userjson=new Gson().toJson(user);
        params.add("data",userjson);
        String url = UriComponentsBuilder.fromHttpUrl(RestTestUtils.IP + resource).build().toUriString();
        ResponseEntity responseEntity = restTemplate.postForEntity(url, params, String.class);
        System.out.print(responseEntity.getBody().toString());
    }
}
