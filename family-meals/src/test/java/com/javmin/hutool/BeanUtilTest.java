package com.javmin.hutool;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Type;

import org.junit.Assert;
import org.junit.Test;

import com.javmin.entity.User;
import com.xiaoleilu.hutool.util.BeanUtil;
import com.xiaoleilu.hutool.util.BeanUtil.CopyOptions;
import com.xiaoleilu.hutool.util.BeanUtil.ValueProvider;

public class BeanUtilTest {

	@Test
	public void BeanTest() throws IntrospectionException{
		PropertyDescriptor[] propertyDescriptors = BeanUtil.getPropertyDescriptors(User.class);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			System.out.println(propertyDescriptor.getDisplayName());
		}
	}
	
	
	@Test
	public void 注入bean(){
		User person = BeanUtil.fillBean(new User(), new ValueProvider<String>(){

		    @Override
		    public boolean containsKey(String key) {
		        //总是存在key
		        return true;
		    }

			@Override
			public Object value(String key, Type valueType) {
				switch (key) {
	            case "userName":
	                return "张三";
	            case "age":
	                return 18;
	        }
	        return null;
			}

		}, CopyOptions.create());
		System.out.println(person.getUserName());
		System.out.println(BeanUtil.beanToMap(person));
		Assert.assertEquals(person.getAge(),Integer.valueOf(18));
		Assert.assertEquals(person.getUserName(), "张三");
	}
	
}
