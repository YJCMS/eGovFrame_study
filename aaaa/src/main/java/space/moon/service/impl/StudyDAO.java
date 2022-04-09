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
package space.moon.service.impl;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : SampleDAO.java
 * @Description : Sample DAO Class
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

@Repository("studyDAO")
public class StudyDAO extends EgovAbstractDAO {

	/**
	 * 목록을 조회한다.
	 * @param paramMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
	public List<?> selectSampleDataList(HashMap<String, Object> paramMap) throws Exception {	
		return list("StudyDAO.selectSampleDataList", paramMap);
	}
	
	
	
	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertData(HashMap<String, Object> paramMap) throws Exception {
		return (String) insert("StudyDAO.insertData", paramMap);
	}

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	public int updateData(HashMap<String, Object> paramMap) throws Exception {
		int cnt = update("StudyDAO.updateData", paramMap);
		return cnt;
	}
	

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	public int deleteData(HashMap<String, Object> paramMap) throws Exception {
		int cnt = delete("StudyDAO.deleteData", paramMap);
		return cnt;
	}
	
	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	public int deleteYnData(HashMap<String, Object> paramMap) throws Exception {
		int cnt = update("StudyDAO.deleteYnData", paramMap);
		return cnt;
	}

}
