package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;

import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class MainModel {
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request, HttpServletResponse response) {
		List<MusicVO> list1 = MusicDAO.musicMainData(1);
		// Top100
		List<MusicVO> list2 = MusicDAO.musicMainData(2);
		// 가요
		List<MusicVO> list3 = MusicDAO.musicMainData(3);
		// POP
		List<MusicVO> list4 = MusicDAO.musicMainData(4);
		// OST
		
		request.setAttribute("list1", list1);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		request.setAttribute("list4", list4);
		
		request.setAttribute("main_jsp", "home.jsp");
		return "../main/main.jsp";
	}
}
