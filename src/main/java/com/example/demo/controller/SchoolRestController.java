package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class SchoolRestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/login")
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    //添加学校
    @RequestMapping("/addschool")
    public String addschool(HttpServletRequest request, Map<String,Object> map)
    {
        String scid = request.getParameter("scid");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String schoolWeb = request.getParameter("schoolWeb");

        String s = restTemplate.postForObject("http://localhost:9000/rest/addschool?scid="
                + scid + "&name=" + name + "&description=" + description + "&schoolWeb=" + schoolWeb,
                null, String.class);

        if("add".equals(s))    //添加成功
        {
            map.put("msg","add");
        }
        else
        {
            map.put("msg1","fault");
        }
        return "login";
    }

    //删除学校
    @RequestMapping("deleteschool")
    public String deleteschool(HttpServletRequest request,Map<String,Object> map)
    {
        String name = request.getParameter("name");

        String s = restTemplate.postForObject("http://localhost:9000/rest/deleteschool?name="
                + name, null, String.class);
        if("delete".equals(s))    //删除成功
        {
            map.put("msg2","delete");
        }
        else
        {
            map.put("msg3","fault");
        }
        return "login";
    }

    //修改学校信息
    @RequestMapping("/updateschool")
    public String updateschool(HttpServletRequest request,Map<String,Object> map)
    {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String schoolWeb = request.getParameter("schoolWeb");

        String s = restTemplate.postForObject("http://localhost:9000/rest/updateschool?name="
                + name + "&description=" + description + "&schoolWeb=" + schoolWeb,
                null, String.class);

        if("update".equals(s))
        {
            map.put("msg4","update");
        }
        else
        {
            map.put("msg5","fault");
        }
        return "login";
    }


    //查询一个学校
    @RequestMapping("/getschool")
    public String getschool(HttpServletRequest request,Map<String,Object> map)
    {
        String name = request.getParameter("name");
        School school = restTemplate.postForObject("http://localhost:9000/rest/getschool?name="
                + name, null, School.class);
        if(school!=null)    //有该学校
        {
            map.put("msg6","get");
            System.out.println(school);
        }
        else
        {
            map.put("msg7","fault");
        }
        return "login";
    }

    //查询所有学校
    @RequestMapping("/getallschool")
    public String getallschool()
    {
        //得到的是json字符串
        List list = restTemplate.postForObject("http://localhost:9000/rest/getallschool",
                null, List.class);
        for(Object o:list)
        {
            JSON json=(JSON)JSON.toJSON(o);   //json字符型转成json对象
            School school = JSON.toJavaObject(json, School.class);   //json对象转成School对象
            System.out.println(school);
        }
        return "login";
    }

}






