package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

public class DeleteModel {
	public void execute(HttpServletRequest request) {
		// request에 값을 담는다
		request.setAttribute("msg", "게시판 데이터 삭제");
	}
}
