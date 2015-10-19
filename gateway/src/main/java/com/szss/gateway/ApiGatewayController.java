package com.szss.gateway;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zcg on 15/10/17.
 */
@RestController
@RequestMapping("/gateway")
public class ApiGatewayController {
    @RequestMapping(params = "method=test.user.login",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String test1(@ModelAttribute("user") User user) {
        System.out.println("User name:"+user.getName()+" password:"+user.getPassword()+" manager:"+user.getManager().getName());
        return "true";
    }

    @RequestMapping(params = "method=test.user.json",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String test2(@RequestParam("data") String data) {
        Gson gson=new Gson();
        User user=gson.fromJson(data,User.class);
        System.out.println("User name:"+user.getName()+" password:"+user.getPassword()+" manager:"+user.getManager().getName());
        return "true";
    }
}
