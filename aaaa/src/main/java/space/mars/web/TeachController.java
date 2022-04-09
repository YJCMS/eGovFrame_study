/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package space.mars.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import space.mars.service.TeachService;

import javax.annotation.Resource;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
@RequestMapping("/mars")
public class TeachController {

	/** EgovSampleService */
	@Resource(name = "teachService")
	private TeachService teachService;

//	화면 이동
	@RequestMapping(value = "/goTeach.do")
	public ModelAndView goTeach(ModelAndView model, HttpServletRequest request) throws Exception {
		
		System.out.println("/goTeach.do");
		
		model.addObject("keyId", "99999"); //설정값 넘기기
		
		model.setViewName("mars/teachPage");
		return model;
	}
	

	/**
	 * AJAX기능을 jQuery를 이용하여 탭형식으로 나타낸다.
	 * @param tabName 각 탭명
	 * @param request
	 * @return 메인페이지
	 * @throws Exception
	 */
	@Resource MappingJackson2JsonView ajaxMainView;
	@RequestMapping(value = "/selectSampleDataList.do")
	protected ModelAndView selectSampleDataList(HttpServletRequest request) throws Exception {
		//화면에 넘길 결과
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		//데이터 조회시 param 값
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		//첫번째 방법
		String fileName = request.getParameter("fileName");
		String testName = request.getParameter("testName");
		String testTitle = request.getParameter("testTitle");
		String testDate = request.getParameter("testDate");
		
		paramMap.put("fileName", fileName);
		paramMap.put("testName", testName);
		paramMap.put("testTitle", testTitle);
		paramMap.put("testDate", testDate);
		
		System.out.println("###### paramMap ######");
		System.out.println(paramMap);
		System.out.println("###### paramMap ######");
		
		List<?> resultList = teachService.selectSampleDataList(paramMap);
		
		resultMap.put("resultList", resultList);
		resultMap.put("resultMsg", "success");
		resultMap.put("resultCnt", resultList.size());

		return new ModelAndView(ajaxMainView, resultMap);
	}
	
	/**
	 * AJAX기능을 jQuery를 이용하여 탭형식으로 나타낸다.
	 * @param tabName 각 탭명
	 * @param request
	 * @return 메인페이지
	 * @throws Exception
	 */
//	@Resource MappingJackson2JsonView ajaxMainView1;
	@RequestMapping(value = "/selectSampleDataList1.do")
	protected ModelAndView selectSampleDataList1(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		//화면에 넘길 결과
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		//데이터 조회시 param 값
		//두번째 방법
		System.out.println("###### param ######");
		System.out.println(paramMap);
		System.out.println("###### param ######");
		
		List<?> resultList = teachService.selectSampleDataList(paramMap);
		
		
		resultMap.put("resultList", resultList);
		resultMap.put("resultMsg", "success");
		resultMap.put("resultCnt", resultList.size());

		return new ModelAndView(ajaxMainView, resultMap);
	}

	
	/**
	 * AJAX기능을 jQuery를 이용하여 탭형식으로 나타낸다.
	 * @param tabName 각 탭명
	 * @param request
	 * @return 메인페이지
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertData.do")
	protected ModelAndView insertData(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		String str = "success"; 
		//화면에 넘길 결과
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		//데이터 조회시 param 값
		System.out.println("###### param ######");
		System.out.println(paramMap);
		System.out.println("###### param ######");
		
		try {
			teachService.insertData(paramMap);
		} catch (Exception e) {
			str = "fail";
		} finally {
			resultMap.put("resultMsg", str);
		}
		
		return new ModelAndView(ajaxMainView, resultMap);
	}
	
	
	/**
	 * AJAX기능을 jQuery를 이용하여 탭형식으로 나타낸다.
	 * @param tabName 각 탭명
	 * @param request
	 * @return 메인페이지
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateData.do")
	protected ModelAndView updateData(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		String str = "success";
		//화면에 넘길 결과
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		//데이터 조회시 param 값
		System.out.println("###### param ######");
		System.out.println(paramMap);
		System.out.println("###### param ######");
		
		try {
			int cnt = teachService.updateData(paramMap);
			resultMap.put("resultCnt", cnt);
		} catch (Exception e) {
			str = "fail";
		} finally {
			resultMap.put("resultMsg", str);
		}
		
		return new ModelAndView(ajaxMainView, resultMap);
	}
	
	/**
	 * AJAX기능을 jQuery를 이용하여 탭형식으로 나타낸다.
	 * @param tabName 각 탭명
	 * @param request
	 * @return 메인페이지
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteData.do")
	protected ModelAndView deleteData(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		String str = "success";
		//화면에 넘길 결과
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		//데이터 조회시 param 값
		System.out.println("###### param ######");
		System.out.println(paramMap);
		System.out.println("###### param ######");
		
		try {
			int cnt = teachService.deleteData(paramMap);
			resultMap.put("resultCnt", cnt);
		} catch (Exception e) {
			str = "fail";
		} finally {
			resultMap.put("resultMsg", str);
		}
		
		return new ModelAndView(ajaxMainView, resultMap);
	}
	
	/**
	 * AJAX기능을 jQuery를 이용하여 탭형식으로 나타낸다.
	 * @param tabName 각 탭명
	 * @param request
	 * @return 메인페이지
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteYnData.do")
	protected ModelAndView deleteYnData(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		String str = "success";
		//화면에 넘길 결과
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		//데이터 조회시 param 값
		System.out.println("###### param ######");
		System.out.println(paramMap);
		System.out.println("###### param ######");
		
		try {
			int cnt = teachService.deleteYnData(paramMap);
			resultMap.put("resultCnt", cnt);
		} catch (Exception e) {
			str = "fail";
		} finally {
			resultMap.put("resultMsg", str);
		}
		
		return new ModelAndView(ajaxMainView, resultMap);
	}


}
