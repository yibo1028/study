package com.yibo.aop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.yibo.aop.annotation.BindWeiXinUserAction.NOT_EXIST_USER;
import com.yibo.aop.annotation.TestAnnotation;
import com.yibo.aop.annotation.TestAnnotationParamter;
import com.yibo.aop.entity.TestBean;

/**
 * 类说明  
 * @author Yibo Liu
 * @version 2017年3月24日
 */
@Controller
@RequestMapping(value="/test")
public class TestControler {
	// http://127.0.0.1:8080/study/test?param=aa&p1=111&p2=222&age=10&userNo=100
	@TestAnnotation
	@RequestMapping(value="")
	@ResponseBody
	public String test(@TestAnnotationParamter(paramName = "testBean", actions = NOT_EXIST_USER) TestBean testBean, @RequestParam(required=false,name="param") String param,
			String str, Integer ccc) {
		return param;		
	}
	
	/*@TestAnnotation(name = "ttt", num = 0)
	@RequestMapping(value="")
	@ResponseBody
	public String test(@TestAnnotationParamter(paramName = "aaa") TestBean testBean, @RequestParam(required=false,name="param") String param) {
		return param;		
	}*/
}
