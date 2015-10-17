package com.szss.gateway.test;

import com.google.gson.Gson;
import com.szss.commons.security.utils.Constants;
import com.szss.commons.security.utils.RestTestUtils;
import com.szss.commons.security.utils.SortCollection;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/9.
 */
public class RestGatewayTest {
    private RestTemplate restTemplate = new RestTemplate();
    private Gson gson = new Gson();

    @Test
    public void test() throws Exception {
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

        Map<String, String> p = new HashMap<String, String>();
        p.put("data", "{123}");
        String url = UriComponentsBuilder
                .fromHttpUrl(RestTestUtils.IP + resource).queryParams(params).build().toUriString();
        ResponseEntity responseEntity = restTemplate.postForEntity(url, p, String.class);
        System.out.print(responseEntity.getBody().toString());
    }

}
