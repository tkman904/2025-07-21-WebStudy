package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;

@Controller
public class MemberModel {
	@RequestMapping("member/post.do")
	public String member_post(HttpServletRequest request, HttpServletResponse response) {
		// 요청값 받기
		String dong = request.getParameter("dong");
		
		// 요청 처리 
		List<ZipcodeVO> list = MemberDAO.postFindData(dong);
		
		// 출력할 결과값 전송 => JSON / request
		request.setAttribute("list", list);
		return "../member/post.jsp";
	}
}
