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
package space.moon.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import space.moon.service.StudyService;

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
 * @  ?????????      ?????????              ????????????
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           ????????????
 *
 * @author ????????????????????? ???????????? ?????????
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
@RequestMapping("/moon")
public class StudyController {

	/** EgovSampleService */
	@Resource(name = "studyService")
	private StudyService studyService;

//	?????? ??????
	@RequestMapping(value = "/goStudy.do")
	public ModelAndView goStudy(ModelAndView model, HttpServletRequest request) throws Exception {
		
		System.out.println("/goStudy.do");
		
		model.addObject("keyId", "99999"); //????????? ?????????
		
		model.setViewName("moon/studyPage");
		return model;
	}
	
	/**
	 * ??? ????????? ????????????. (pageing)
	 * @param searchVO - ????????? ????????? ?????? SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
//	@ResponseBody
//	@RequestMapping(value = "/selectSampleDataList.do")
//	@RequestMapping(value = "/selectSampleDataList.do", method = RequestMethod.GET)
//	public ResponseEntity<JsonString> selectSampleDataList(@RequestParam HashMap<String, Object> param, ModelMap model) throws Exception {
//	public Map<String, Object> selectSampleDataList(@RequestParam HashMap<String, Object> param) throws Exception {		
//		Map<String, Object> map = new HashMap<String, Object>();
		
//		List<?> sampleList = studyService.selectSampleDataList(param);
		
		
//		map.put("resultMsg", "chocho");
//		/** EgovPropertyService.sample */
//		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
//		searchVO.setPageSize(propertiesService.getInt("pageSize"));
//
//		/** pageing setting */
//		PaginationInfo paginationInfo = new PaginationInfo();
//		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
//		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
//		paginationInfo.setPageSize(searchVO.getPageSize());
//
//		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
//		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
//
//		List<?> sampleList = sampleService.selectSampleList(searchVO);
//		model.addAttribute("resultList", sampleList);
//
//		int totCnt = sampleService.selectSampleListTotCnt(searchVO);
//		paginationInfo.setTotalRecordCount(totCnt);
//		model.addAttribute("paginationInfo", paginationInfo);
//		return map;	
//	}
	
	
	/**
	 * ??? ????????? ????????????. (pageing)
	 * @param searchVO - ????????? ????????? ?????? SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
//	@RequestMapping(value = "/selectSampleDataList.do")
//	@ResponseBody
////	@RequestMapping(value = "/selectSampleDataList.do", method = RequestMethod.GET)
////	public ResponseEntity<JsonString> selectSampleDataList(@RequestParam HashMap<String, Object> param, ModelMap model) throws Exception {
//	public Map<String, Object> selectSampleDataList(@RequestParam HashMap<String, Object> param) throws Exception {		
////	public void selectSampleDataList(@RequestParam HashMap<String, Object> param) throws Exception {		
//		
//		System.out.println("/selectSampleDataList.do");
//		System.out.println(param.toString());
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		
////		List<?> sampleList = studyService.selectSampleDataList(param);
//		
//		
//		map.put("code", "1");
//		map.put("msg", "success");
//		map.put("resultMsg", "chocho");
////		/** EgovPropertyService.sample */
////		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
////		searchVO.setPageSize(propertiesService.getInt("pageSize"));
////
////		/** pageing setting */
////		PaginationInfo paginationInfo = new PaginationInfo();
////		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
////		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
////		paginationInfo.setPageSize(searchVO.getPageSize());
////
////		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
////		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
////		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
////
////		List<?> sampleList = sampleService.selectSampleList(searchVO);
////		model.addAttribute("resultList", sampleList);
////
////		int totCnt = sampleService.selectSampleListTotCnt(searchVO);
////		paginationInfo.setTotalRecordCount(totCnt);
////		model.addAttribute("paginationInfo", paginationInfo);
//		System.out.println(map.toString());
//		return map;	
//	}
	
	

	/**
	 * AJAX????????? jQuery??? ???????????? ??????????????? ????????????.
	 * @param tabName ??? ??????
	 * @param request
	 * @return ???????????????
	 * @throws Exception
	 */
	@Resource MappingJackson2JsonView ajaxMainView;
	@RequestMapping(value = "/selectSampleDataList.do")
	protected ModelAndView selectSampleDataList(HttpServletRequest request) throws Exception {
		//????????? ?????? ??????
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		//????????? ????????? param ???
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		//????????? ??????
		String lastNm = request.getParameter("lastNm");
		String firstNm = request.getParameter("firstNm");
		
		paramMap.put("lastNm", lastNm);
		paramMap.put("firstNm", firstNm);
		
		System.out.println("###### paramMap ######");
		System.out.println(paramMap);
		System.out.println("###### paramMap ######");
		
		List<?> resultList = studyService.selectSampleDataList(paramMap);
		
		resultMap.put("resultList", resultList);
		resultMap.put("resultMsg", "success");
		resultMap.put("resultCnt", resultList.size());

		return new ModelAndView(ajaxMainView, resultMap);
	}
	
	/**
	 * AJAX????????? jQuery??? ???????????? ??????????????? ????????????.
	 * @param tabName ??? ??????
	 * @param request
	 * @return ???????????????
	 * @throws Exception
	 */
//	@Resource MappingJackson2JsonView ajaxMainView1;
	@RequestMapping(value = "/selectSampleDataList1.do")
	protected ModelAndView selectSampleDataList1(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		//????????? ?????? ??????
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		//????????? ????????? param ???
		//????????? ??????
		System.out.println("###### param ######");
		System.out.println(paramMap);
		System.out.println("###### param ######");
		
		List<?> resultList = studyService.selectSampleDataList(paramMap);
		
		
		resultMap.put("resultList", resultList);
		resultMap.put("resultMsg", "success");
		resultMap.put("resultCnt", resultList.size());

		return new ModelAndView(ajaxMainView, resultMap);
	}

	
	/**
	 * AJAX????????? jQuery??? ???????????? ??????????????? ????????????.
	 * @param tabName ??? ??????
	 * @param request
	 * @return ???????????????
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertData.do")
	protected ModelAndView insertData(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		String str = "success"; 
		//????????? ?????? ??????
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		//????????? ????????? param ???
		System.out.println("###### param ######");
		System.out.println(paramMap);
		System.out.println("###### param ######");
		
		try {
			studyService.insertData(paramMap);
		} catch (Exception e) {
			str = "fail";
		} finally {
			resultMap.put("resultMsg", str);
		}
		
		return new ModelAndView(ajaxMainView, resultMap);
	}
	
	
	/**
	 * AJAX????????? jQuery??? ???????????? ??????????????? ????????????.
	 * @param tabName ??? ??????
	 * @param request
	 * @return ???????????????
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateData.do")
	protected ModelAndView updateData(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		String str = "success";
		//????????? ?????? ??????
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		//????????? ????????? param ???
		System.out.println("###### param ######");
		System.out.println(paramMap);
		System.out.println("###### param ######");
		
		try {
			int cnt = studyService.updateData(paramMap);
			resultMap.put("resultCnt", cnt);
		} catch (Exception e) {
			str = "fail";
		} finally {
			resultMap.put("resultMsg", str);
		}
		
		return new ModelAndView(ajaxMainView, resultMap);
	}
	
	/**
	 * AJAX????????? jQuery??? ???????????? ??????????????? ????????????.
	 * @param tabName ??? ??????
	 * @param request
	 * @return ???????????????
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteData.do")
	protected ModelAndView deleteData(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		String str = "success";
		//????????? ?????? ??????
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		//????????? ????????? param ???
		System.out.println("###### param ######");
		System.out.println(paramMap);
		System.out.println("###### param ######");
		
		try {
			int cnt = studyService.deleteData(paramMap);
			resultMap.put("resultCnt", cnt);
		} catch (Exception e) {
			str = "fail";
		} finally {
			resultMap.put("resultMsg", str);
		}
		
		return new ModelAndView(ajaxMainView, resultMap);
	}
	
	/**
	 * AJAX????????? jQuery??? ???????????? ??????????????? ????????????.
	 * @param tabName ??? ??????
	 * @param request
	 * @return ???????????????
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteYnData.do")
	protected ModelAndView deleteYnData(@RequestParam HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		String str = "success";
		//????????? ?????? ??????
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		//????????? ????????? param ???
		System.out.println("###### param ######");
		System.out.println(paramMap);
		System.out.println("###### param ######");
		
		try {
			int cnt = studyService.deleteYnData(paramMap);
			resultMap.put("resultCnt", cnt);
		} catch (Exception e) {
			str = "fail";
		} finally {
			resultMap.put("resultMsg", str);
		}
		
		return new ModelAndView(ajaxMainView, resultMap);
	}


}
