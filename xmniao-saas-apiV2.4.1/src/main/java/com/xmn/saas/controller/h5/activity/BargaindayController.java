package com.xmn.saas.controller.h5.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmn.saas.service.activity.BargaindayService;

@Controller(value = "h5-activity-bargainday-controller")
@RequestMapping(value = "h5/activity/bargainday")
public class BargaindayController extends AbstractActiviryController{
	
	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(FreetryController.class);
	
	@Autowired
	private BargaindayService bargaindayService;

}
