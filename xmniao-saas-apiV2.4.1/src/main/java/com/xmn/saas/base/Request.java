package com.xmn.saas.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class Request implements Serializable {
	private static final long serialVersionUID = 1L;


	public boolean doValidate(BindingResult result) throws Exception {
		if (result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}

			new Response(ResponseCode.FAILURE, errors.toString()).write();

			return false;
		}

		return true;
	}

}
