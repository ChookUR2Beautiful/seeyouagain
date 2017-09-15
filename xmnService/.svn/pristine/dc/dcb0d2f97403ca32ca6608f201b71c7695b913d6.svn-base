package com.xmniao.xmn.core.api.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.MongoBaseService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.verification.service.BillService;
import com.xmniao.xmn.core.xmer.entity.MSeller;


/**
 * resetful 服务   get/post 等请求处理
 * @author Administrator
 *
 */
@Controller
public class PersonServiceApi {
	
	@Autowired
	private BillService personService;  
	
	
	
	@Autowired
	private MongoBaseService mongoBaseService;
	
	@Autowired
	private StringRedisTemplate stringredisTemplate; 
	
	@Autowired
	private String ip_number_business;
	
	@Autowired
	private String port_business;
	 
	/**
	 * hello
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
    public Object sayHello(String page) throws UnsupportedEncodingException { 
	
		return null;
    }  
	
	
	@RequestMapping(value = "/tokenIsInvalid", method = RequestMethod.GET)
	@ResponseBody
	public Object tokenIsInvalid(){
		return new BaseResponse(ResponseCode.TOKENERR, "token无效,请重新登录");
	}
    
	/**
	 * 获取person集合
	 * @return
	 */
    @RequestMapping(value="persons",method=RequestMethod.GET)
    @ResponseBody
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })  
    public 	List<Urs> getPerson() {  
    	return null;
    }  
    
    
    
    
    @RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.GET)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })  
    public Urs getPersonByPath(@PathParam("Personid") int Personid) {  
//        Person Person = PersonService.getPerson(Personid);  
//        System.out.println("PersonServicePath = " + Person.toString());  
        return null;  
    }  
  
    /**
     * 新增person
     * @param Person
     * @return
     */
    @POST  
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })  
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})  
    public Urs savePerson(final Urs Person) {  
        System.out.println("enter savePerson");  
//        return PersonService.savePerson(Person);
        return null;
    }  
  
    /**
     * 修改person
     * @param Personid
     * @param Person
     * @return
     */
    @Path("{Personid:[0-9]*}")  
    @PUT  
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })  
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,  
            MediaType.TEXT_XML })  
    public Urs updatePerson(@PathParam("Personid") String Personid, final Urs Person) {  
        if (Person == null) {  
            return null;  
        }  
//        return PersonService.updatePerson(Personid, Person);
        return null;
    }  
  
    
    /**
     * 根据ID删除person
     * @param Personid
     * @return
     */
    @Path("{Personid:[0-9]*}")  
    @DELETE  
    public String deletePerson(@PathParam("Personid") int Personid) {  
  
//        if (PersonService.deletePerson(Personid)) {  
//            return "Deleted Person id = " + Personid;  
//        } else {  
//            return "Deleted Person failed id = " + Personid;  
//        } 
        return null;
    }
  
}
