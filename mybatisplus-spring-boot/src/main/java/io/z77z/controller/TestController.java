package io.z77z.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;

import io.z77z.entity.BeautifulPictures;
import io.z77z.service.BeautifulPicturesService;

//如果是要返回jsp页面就必须要使用@Controller而不是@RestController
@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	BeautifulPicturesService beautifulPicturesService;
	
	@RequestMapping("/test1")  
    public String view(Model model,Page<BeautifulPictures> page) {
		Page<BeautifulPictures> pageList= beautifulPicturesService.selectPage(page);
		model.addAttribute("user",JSON.toJSONString(pageList.getRecords()));
        return "index";
    }
}
