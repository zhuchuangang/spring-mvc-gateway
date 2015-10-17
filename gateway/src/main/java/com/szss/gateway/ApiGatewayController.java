package com.szss.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by zcg on 15/10/17.
 */
@RestController
@RequestMapping("/gateway")
public class ApiGatewayController {
    @RequestMapping(params = "method=test.user.login",
            method = RequestMethod.POST,
            headers = "accept=application/xml, application/json",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String test(@ModelAttribute("data")String data) {
        System.out.println(data);
        return "success";
    }
}
