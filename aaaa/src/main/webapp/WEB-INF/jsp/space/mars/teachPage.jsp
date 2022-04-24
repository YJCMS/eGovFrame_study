<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : egovSampleList.jsp
  * @Description : Sample List 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2009.02.01            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.02.01
  *
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="title.sample" /></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javaScript" language="javascript" defer="defer">
		var property = {keyId : '${keyId}'};
    	$(document).ready(function(){
			console.log("chocho");
			console.log(property.keyId);
			
			initPage();
		});
    	// 기능별로 새분화 하도록 하겠어요...
    	
    	
    	// 페이지열리면 실행될 것
    	function initPage() {
    		console.log("initPage");
    		// 이벤트 등록
    		$("#btnSearch").on("click", function() {
    			// 조회번튼 클릭하면 목록검색 function 호출
    			selectList();	
    		});
    		$("#btnInsert").on("click", function() {
    			// 등록 function 호출
    			insertData();	
    		});
    		$("#btnUpdate").on("click", function() {
    			// 수정 function 호출
    			updateData();	
    		});
    		$("#btnDelete").on("click", function() {
    			// 삭제 function 호출
    			deleteData();	
    		});
    		$("#btnDeleteYn").on("click", function() {
    			// 삭제-상태값변경 function 호출
    			deleteYnData();	
    		});

//     		$("#dataList tbody").on("click", function() {
//     			// 등록 function 호출
//     			console.log("tbody");
//     		});
//     		$("#dataList").on("click", function() {
//     			// 등록 function 호출
//     			console.log("tbody");
//     		});
    		
    		
    		// 화면 open시 목록검색
    		selectList();
		}

    	
    	// 검색조건 셋팅하는  function
    	function setParam(type){
    		var param = {};
    		//값을 가져오는지 확인해볼꼐요  잘가져오네요
    		
    		var _testName = $("#"+type).find('[name="testName"]').val();
    		var _fileName = $("#"+type).find('[name="fileName"]').val();

    		param.testName = _testName;
    		param.fileName = _fileName;
    
    		if( type == "insert") { 
    		 	var _testId = $("#"+type).find('[name="testId"]').val(); 
    			var _testTitle = $("#"+type).find('[name="testTitle"]').val();
        		var _testDate = $("#"+type).find('[name="testDate"]').val();
        	
        		param.testId = _testId;
        		param.testTitle = _testTitle;
        		param.testDate = _testDate;
        		
    		}
    		if( type == "edit") { 
    			var _testId = $("#"+type).find('[name="testId"]').val();
    			var _testTitle = $("#"+type).find('[name="testTitle"]').val();
        		var _testDate = $("#"+type).find('[name="testDate"]').val();
        		
    			param.testId = _testId;
    			param.testTitle = _testTitle;
        		param.testDate = _testDate;
    		}
    		
//     		param = {"lastNm" : _lastNm , "firstNm" : _firstNm};

// 			$.extend(param, {"lastNm" : _lastNm , "firstNm" : _firstNm});
// 			$.extend(param, {"lastNm" : $("#search").find('[name="lastNm"]').val() , "firstNm" : $("#search").find('[name="firstNm"]').val()});
    		
    		//이렇게 리턴을 적어주고 param 지정하면 param object가 반환됨  
    		return param; 
    	}
    	
    	// 목록검색 function
    	function selectList() {
    		console.log("selectList");
    		
    		var url = "/aaaa/mars/selectSampleDataList.do"
    		
  			// 검색조건 셋팅하는  function
			var param = setParam('search');
    		
    		
    		// 목록검색
    		$.ajax({
   			  type: "POST",
   			  url: url,
   			  data: param,
//    			  async: false,
   			  success: function(data){
   				console.log("selectList::success");
   				console.log(data);
   				setDataList(data);
					
   			  },
   			  error: function(error){
   				console.log("selectList::error");
   				  
   			  },
   			  complete: function(com){
   				console.log("selectList::complete");
   	    		$("#dataList tbody tr").on("click", function(evt) {
   	    			//수정,삭제 data 셋팅
   	    			setEditData($(this));
   	    		});
   			  },
   			  beforeSend : function(){
   				console.log("selectList::beforeSend");
   			  } 
   			  
   			});
   
    	}
    	
    	// 목록만들기 function
    	function setDataList(data){
    		var str = '';
    		
    		//tbody 영역 초기화
//     		$("#dataList").find("tbody").empty();
    		var _tbody = $("#dataList").find("tbody");
	   		_tbody.empty();
	   		
    		//data에서  resultList 혹은 resultCnt 확인
    		if( data.resultCnt > 0 ){
    			//목록 1줄씩 추가
    			for ( let item of data.resultList) {
    				str = '<tr data-id="'+item.testId+'"><td style="background-color: gray;">'+item.testTitle+'</td><td style="background-color: gray;">'+item.testName+'</td><td style="background-color: gray;">'+item.testDate+'</td><td style="background-color: gray;">'+item.fileName+'</td></tr>';
    				_tbody.append(str);
				}
    			
    	
    				
    		}else if( data.resultCnt == 0 ){
    			//검색결과 없을 때 추가
    			str = '<tr><td colspan="2" style="background-color: gray;">검색결과가 없습니다.</td></tr>';
    			_tbody.append(str);
    		}
    	}
    	
    	
    	// 등록 function 호출
		function insertData(){
  			// 검색조건 셋팅하는  function
			var param = setParam('insert');
    		
  			
			if( param.fileName == ''){
				alert("파일 이름을 선택하세요");
				return false;
			}
			if( param.testName == '' || param.testName.trim() == ''){
				alert("이름을 입력하세요");
				return false;
			}
			
			if( param.testTitle == '' || param.testTitle.trim() == ''){
				alert("제목을 입력하세요");
				return false;
			}
			
			if( param.testDate == '' || param.testDate.trim() == ''){
				alert("날짜를 입력하세요");
				return false;
			}
			
			var url = "/aaaa/mars/insertData.do"
    		
    		// 목록검색
    		$.ajax({
   			  type: "POST",
   			  url: url,
   			  data: param,
//    			  async: false,
   			  success: function(data){
   				if (data.resultMsg == "success"  ) {
   				// 목록검색 function 실행
   					selectList();
   				}else if (data.resultMsg == "fail"  ){
   					alert("등록에 실패하였습니다. 다시 시도해주세요.");
   				} 

   			  },
   			  error: function(error){
   				console.log("selectList::error");
   				  s
   			  },
   			  complete: function(com){
   				console.log("selectList::complete");
   	    		$("#insert").find('[name="fileName"]').val('');
   	    		$("#insert").find('[name="testName"]').val('');
   	    		$("#insert").find('[name="testDate"]').val('');
   	    		$("#insert").find('[name="testTitle"]').val('');
   	    		
   			  },
   			  beforeSend : function(){
   				console.log("selectList::beforeSend");
   			  } 
   			  
   			});
    	}
    	
    	function setEditData(object){
    		var _testTitle= object.find('td:eq(0)').text();
   			var _testName = object.find('td:eq(1)').text();
   			var _testDate = object.find('td:eq(2)').text();
   			var _fileName = object.find('td:eq(3)').text();
   			var _testId = object.attr("data-id");
   			
				$("#edit").find('[name="testTitle"]').val(_testTitle);
   	    		$("#edit").find('[name="testName"]').val(_testName);
   	    		$("#edit").find('[name="testDate"]').val(_testDate);
   	    		$("#edit").find('[name="fileName"]').val(_fileName);
   	    		$("#edit").find('[name="testId"]').val(_testId);
    	}
    	
    	function updateData(){
    		// 검색조건 셋팅하는  function
			var param = setParam('edit');
    		
			if( param.fileName == ''){
				alert("파일 이름을 선택하세요");
				return false;
			}
			if( param.testName == '' || param.testName.trim() == ''){
				alert("이름을 입력하세요");
				return false;
			}
			if( param.testTitle == '' || param.testTitle.trim() == ''){
				alert("제목을 입력하세요");
				return false;
			}
			if( param.testDate == '' || param.testDate.trim() == ''){
				alert("날짜를 입력하세요");
				return false;
			}
			
			var url = "/aaaa/mars/updateData.do"
			
    		$.ajax({
   			  type: "POST",
   			  url: url,
   			  data: param,
   			  success: function(data){
   				if (data.resultMsg == "success"  ) {
   				// 목록검색 function 실행
   					selectList();
   				}else if (data.resultMsg == "fail"  ){
   					alert("등록에 실패하였습니다. 다시 시도해주세요.");
   				} 

   			  },
   			  error: function(error){
   				console.log("selectList::error");
   				  
   			  },
   			  complete: function(com){
   				console.log("selectList::complete");
   				$("#edit").find('[name="testTitle"]').val('');
   	    		$("#edit").find('[name="testName"]').val('');
   	    		$("#edit").find('[name="testDate"]').val('');
   	    		$("#edit").find('[name="fileName"]').val('');
   	    		$("#edit").find('[name="testId"]').val('');
   			  },
   			  beforeSend : function(){
   				console.log("selectList::beforeSend");
   			  } 
   			  
   			});
    	}
    	
    	function deleteData(){
    		// 검색조건 셋팅하는  function
			var param = setParam('edit');
    		
			var url = "/aaaa/mars/deleteData.do"
			
    		$.ajax({
   			  type: "POST",
   			  url: url,
   			  data: param,
   			  success: function(data){
   				if (data.resultMsg == "success"  ) {
   				// 목록검색 function 실행
   					selectList();
   				}else if (data.resultMsg == "fail"  ){
   					alert("삭제에 실패하였습니다. 다시 시도해주세요.");
   				} 

   			  },
   			  error: function(error){
   				console.log("deleteData::error");
   				  
   			  },
   			  complete: function(com){
   				console.log("deleteData::complete");
   				$("#edit").find('[name="testTitle"]').val('');
   	    		$("#edit").find('[name="testName"]').val('');
   	    		$("#edit").find('[name="testDate"]').val('');
   	    		$("#edit").find('[name="fileName"]').val('');
   	    		$("#edit").find('[name="testId"]').val('');
   			  },
   			  beforeSend : function(){
   				console.log("deleteData::beforeSend");
   			  } 
   			  
   			});
    	}
    	
    	/* function deleteYnData(){
    		// 검색조건 셋팅하는  function
			var param = setParam('edit');
    		
			var url = "/aaaa/mars/deleteYnData.do"
			
    		$.ajax({
   			  type: "POST",
   			  url: url,
   			  data: param,
   			  success: function(data){
   				if (data.resultMsg == "success"  ) {
   				// 목록검색 function 실행
   					selectList();
   				}else if (data.resultMsg == "fail"  ){
   					alert("삭제에 실패하였습니다. 다시 시도해주세요.");
   				} 

   			  },
   			  error: function(error){
   				console.log("deleteYnData::error");
   				  
   			  },
   			  complete: function(com){
   				console.log("deleteYnData::complete");
   				$("#edit").find('[name="testTitle"]').val(_testTitle);
   	    		$("#edit").find('[name="testName"]').val(_testName);
   	    		$("#edit").find('[name="testDate"]').val(_testDate);
   	    		$("#edit").find('[name="fileName"]').val(_fileName);
   	    		$("#edit").find('[name="dataId"]').val(_dataId);
   			  },
   			  beforeSend : function(){
   				console.log("deleteYnData::beforeSend");
   			  } 
   			  
   			});
    	} */
    	
    </script>
</head>

<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
	<div style="width: 100%;height: 50px;">
		<span style="font-weight: bold;color: red;">teachPage.jsp 화면 -- mybatis 활용( Mapper interface 사용 방식 )</span>
	</div>
	<br/>
	
	<div id="search" style="width: 100%; height: 100px;border: 1px solid blue;">
		<p>검색조건</p>
		<br/>
		파일이름 : <select name="fileName" style="width: 150px;">
				<option value="">-전체-</option>
				<option value="a">a</option>
				<option value="b">b</option>
				<option value="c">c</option>
			</select>
		이름 : <input name="testName" type="text"/>
		<button id="btnSearch">조회</button>
	</div>
	
	
    <div style="width: 100%; height: 400px;border: 1px solid red;">
   		<p>검색결과목록</p>
		<br/>
    	<table id="dataList" style="width: 500px;border: 1px solid gray;">
    		<colgroup>
    			<col style="width: 100px;"/>
    			<col style="width: auto;"/>
    		</colgroup>
    		<thead>
    			<tr>
    				<th>제목</th>
    				<th>이름</th>
    				<th>날짜</th>
    				<th>파일이름</th>
    				
    			</tr>
    		</thead>
    		<tbody>
    			<tr>
    				<td style="background-color: gray;">data 제목</td>
    				<td style="background-color: gray;">data 이름</td>
    				<td style="background-color: gray;">data 날짜</td>
    				<td style="background-color: green;">data 파일이름</td>
    			</tr>
    		</tbody>
    	</table>
	</div>
	
	<div id="insert" style="width: 100%; height: 100px;border: 1px solid green;">
		<p>등록</p>
		<br/>
		
		제목 : <input name="testTitle" type="text"/>
		이름 : <input name="testName" type="text"/>
		날짜 : <input name="testDate" type="text"/>
		파일이름 : <select name="fileName" style="width: 150px;">
				<option value="">-전체-</option>
				<option value="a">a</option>
				<option value="b">b</option>
				<option value="c">c</option>
			</select>
			
		<button id="btnInsert">등록</button>
	</div>
	
	<div id="edit" style="width: 100%; height: 100px;border: 1px solid yellow;">
		<p>수정/삭제</p>
		<br/>
		
		제목 : <input name="testTitle" type="text"/>
		이름 : <input name="testName" type="text"/>
		날짜 : <input name="testDate" type="text"/>
		파일이름 : <select name="fileName" style="width: 150px;">
				<option value="">-전체-</option>
				<option value="a">a</option>
				<option value="b">b</option>
				<option value="c">c</option>
			</select>
				<input name="testId" type="hidden"/>
		
		<button id="btnUpdate">수정</button>
		<button id="btnDelete">삭제</button>
		<button id="btnDeleteYn">삭제-상태값 변경</button>
	</div>
</body>
</html>
