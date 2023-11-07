package com.clovers.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clovers.dto.DeptTaskDTO;
import com.clovers.dto.JobDTO;
import com.clovers.dto.MemberDTO;
import com.clovers.services.OfficeService;

@Controller
@RestController
@RequestMapping("/office")
public class OfficeController {
	// 오피스 관리 컨트롤러
	@Autowired
	private OfficeService oservice;
	
	// 부서 명 불러오기
	@GetMapping("/detpTask")
	public ResponseEntity<List<DeptTaskDTO>> selectDeptTaskAll(){
		List<DeptTaskDTO> list = oservice.selectDeptTaskAll();
		return ResponseEntity.ok(list);
	}
	
	// 직급 명 불러오기
	@GetMapping("/job")
	public ResponseEntity<List<JobDTO>> selectPositionAll(){
		List<JobDTO> list = oservice.selectPositionAll();
		return ResponseEntity.ok(list);
	}
	
	// 사용자 수 불러오기
	@GetMapping("/empCount")
	public ResponseEntity<Integer> selectEmpCount(){
		int count= oservice.selectEmpCount();
		return ResponseEntity.ok(count);
	}
	
	// 사용자 리스트 불러오기
	@GetMapping("/userList")
	public ResponseEntity<List<Map<String, String>>> selectUserList(){
		List<Map<String, String>> list = oservice.selectUserList();
		return ResponseEntity.ok(list);
	}
	
	// 사용자 등록하기
	@PostMapping("/userInsert")
	public ResponseEntity<Integer> insertUser(@RequestBody MemberDTO dto){
		int result = oservice.insertUser(dto);
		return ResponseEntity.ok(result);
	}
	
	// 사용자 삭제하기
	@PostMapping("/userDelete")
	public ResponseEntity<Integer> deleteUser(@RequestBody List<String> userID){
		int result = oservice.deleteUser(userID);
		return ResponseEntity.ok(result);
	}
	
	// 사용자 직위 수정하기
	@PostMapping("/updateUserJob")
	public ResponseEntity<Integer> updateUserJob(@RequestBody List<MemberDTO> dtoList){
		oservice.updateUserJob(dtoList);
		return ResponseEntity.ok().build();
	}
	
	// 사용자 소속 조직 수정하기
	@PostMapping("/updateUserDeptTask")
	public ResponseEntity<Integer> updateUserDeptTask(@RequestBody List<MemberDTO> dtoList){
		for(MemberDTO dto:dtoList) {
			System.out.println(dto.toString());
		}
		oservice.updateUserDeptTask(dtoList);
		return ResponseEntity.ok().build();
	}
}
