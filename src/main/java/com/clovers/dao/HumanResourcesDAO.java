package com.clovers.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clovers.dto.AnnaulRestDTO;
import com.clovers.dto.MemberDTO;
import com.clovers.dto.WorkConditionDTO;

@Repository
public class HumanResourcesDAO {
	// 인사 DAO

	@Autowired
	private SqlSession db;

//	public String selectByIdGetName(String id) {
//		return db.selectOne("HumanResources.selectByIdGetName",id);
//	}

	public MemberDTO selectById(String id) {
		return db.selectOne("HumanResources.selectById", id);
	}

	public String reChangePw(String id) {
		return db.selectOne("HumanResources.getPw", id);
	}

	public int update(Map<String, String> param) {
		return db.update("HumanResources.update", param);
	}

	public int updateNoImg(Map<String, String> param) {
		return db.update("HumanResources.updateNoImg", param);
	}

	public int updateNoImag(Map<String, String> param) {
		return db.update("HumanResources.updateNoImag", param);
	}

	// 사용자 지각 정보 불러오기
	public int selectLateInfo(String id) {
		return db.selectOne("HumanResources.selectLateInfo", id);
	}

	// 사용자 조기퇴근 정보 불러오기
	public int selectEarlyLeaveInfo(String id) {
		return db.selectOne("HumanResources.selectEarlyLeaveInfo", id);
	}

	// 사용자 퇴근 미체크 정보 불러오기
	public int selectNotCheckedLeaveInfo(String id) {
		return db.selectOne("HumanResources.selectNotCheckedLeaveInfo", id);
	}

	// 이번달 공휴일 정보 불러오기
	public List<Map<String, Date>> selectHoliDays() {
		return db.selectList("HumanResources.selectHoliDays");
	}

	// 이번달 근무일 정보 불러오기
	public List<Map<String, Timestamp>> selectWorkingDaysThisMonth(String id) {
		return db.selectList("HumanResources.selectWorkingDaysThisMonth", id);
	}

	// 사용자 근무 규칙 정보 불러오기
	public Map<String, Object> selectEmployeeWorkRule(String id) {
		return db.selectOne("HumanResources.selectEmployeeWorkRule", id);
	}

	// 출근전인지 확인
	public Map<String, Object> selectAttendStatus(String id) {
		return db.selectOne("HumanResources.selectAttendStatus", id);
	}

	// 출근 기록 남기기
	public int insertAttendingWork(Map<String, Object> user) {
		return db.insert("HumanResources.insertAttendingWork", user);
	}

	// 퇴근 기록 남기기
	public int updateLeavingWork(String id) {
		return db.update("HumanResources.updateLeavingWork", id);
	}

	// 업무 상태 기록 남기기
	public int insertWorkCondition(Map<String, Object> data) {
		db.insert("HumanResources.insertWorkCondition", data);
		return Integer.parseInt(data.get("id").toString());
	}

	// 업무 상태 종료시간 업데이트
	public int updateWorkCondtionEndTime(int attend_status_id) {
		return db.update("HumanResources.updateWorkCondtionEndTime", attend_status_id);
	}

	// 입력한 업무 상태 확인
	public WorkConditionDTO selectWorkCondition(int id) {
		return db.selectOne("HumanResources.selectWorkCondition", id);
	}

	// 업무 상태 리스트 불러오기
	public List<WorkConditionDTO> selectWorkConditionsList(String id) {
		return db.selectList("HumanResources.selectWorkConditionsList", id);
	}

	// 휴가 사유 구분 불러오기
	public List<String> selectRestReasonType() {
		return db.selectList("HumanResources.selectRestReasonType");
	}

	// 해당 년도 휴가 총 개수 불러오기
	public Map<String, Object> selectYearTotalAnnaul(Map<String, Object> data) {
		Map<String, Object> result = db.selectOne("HumanResources.selectYearTotalAnnaul", data);
		return (result != null) ? result : (new HashMap<>());
	}

	// 해당 년도 휴가 사용 개수 불러오기
	public int selectUsedAnnaul(Map<String, Object> data) {
		Integer result = db.selectOne("HumanResources.selectUsedAnnaul", data);
		System.out.println(result);
		return (result != null) ? result.intValue() : 0;
	}

	// 해당 년도 휴가 생성 상세 정보 불러오기
	public List<AnnaulRestDTO> selectYearDetailAnnaul(Map<String, Object> data) {
		return db.selectList("HumanResources.selectYearDetailAnnaul", data);
	}

	// 사용자의 휴가 신청 상세 내역 확인하기
	public List<Map<String, Object>> selectAnnaulAppDetails(String id) {
		return db.selectList("HumanResources.selectAnnaulAppDetails", id);
	}

	// 사용자의 최근 1년치 신청 상세 내역 확인하기
	public List<Map<String, Object>> selectAnnaulAppDetailsForYear(String id) {
		return db.selectList("HumanResources.selectAnnaulAppDetailsForYear", id);
	}

	// 사용자의 최근 1달치 신청 상세 내역 확인하기
	public List<Map<String, Object>> selectAnnaulAppDetailsForMonth(String id) {
		return db.selectList("HumanResources.selectAnnaulAppDetailsForMonth", id);
	}

	// 사용자의 최근 1주일 신청 상세 내역 확인하기
	public List<Map<String, Object>> selectAnnaulAppDetailsForWeek(String id) {
		return db.selectList("HumanResources.selectAnnaulAppDetailsForWeek", id);
	}

	// 임직원 정보 전부 불러오기
	public List<MemberDTO> employeeSelectAll() {
		return db.selectList("HumanResources.employeeSelectAll");
	}
}
