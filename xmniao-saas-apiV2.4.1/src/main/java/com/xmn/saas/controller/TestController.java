package com.xmn.saas.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.Response;
import com.xmn.saas.entity.test.Activety;
import com.xmn.saas.entity.test.User;
import com.xmn.saas.service.test.TestService;

@Controller
@RequestMapping( "/test" )
public class TestController {

    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping( value = "/view0" )
    public void view0() throws Exception {
        Response response = new Response(200, "JJ");

        response.write();
    }

    @ResponseBody
    @RequestMapping( value = "/view1" )
    public void view1() throws Exception {
        Activety activety = testService.get(1L);

        Response response = new Response(200, "JJ", activety);

        response.write();


    }

    @SuppressWarnings( "serial" )
    @ResponseBody
    @RequestMapping( value = "/view2" )
    public void view2() throws Exception {
        Activety activety = testService.get(1L);

        Response response = new Response(200, "", activety);

        response.write(new HashMap<Class<?>, String[]>() {
            {
                put(Activety.class, new String[] { "activetyName" , "user" });
                put(User.class, new String[] { "name" , "loginDate" });
            }
        });


    }

    @SuppressWarnings( "serial" )
    @ResponseBody
    @RequestMapping( value = "/view3" )
    public void view3() throws Exception {
        Activety activety = testService.get(1L);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("activety", activety);

        Response response = new Response(200, "", result);

        response.write(new HashMap<Class<?>, String[]>() {
            {
                put(Activety.class, new String[] { "activetyName" , "user" });
                put(User.class, new String[] { "name" , "loginDate" });
            }
        }, "yyyy-MM-dd HH:mm:ss");


    }


    @SuppressWarnings( "serial" )
    @ResponseBody
    @RequestMapping( value = "/view4" )
    public void view4() throws Exception {
        Activety activety = testService.get(1L);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("code", 1);
        result.put("msg", "成功！！！");

        Response response = new Response(200, "", result);
        response.write();
        return;


    }

}
