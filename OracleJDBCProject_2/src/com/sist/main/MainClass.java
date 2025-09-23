package com.sist.main;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

public class MainClass {

	public static void main(String[] args) {
		// <% %> => JSTL <c : if> <c : forEach>
		EmpDAO dao = EmpDAO.newInstance();
		List<EmpVO> list = dao.empListData();
		for(EmpVO vo : list) {
			/*
			 * 	<%=
			 * 		vo.getEmpno()+" "
			 * 		+vo.getEname()+" "
			 * 		+vo.getHiredate().toString()+" "
			 * 		+vo.getSal()+" "
			 * 		+vo.getJob()+" "
			 * 		+vo.getDvo().getDname()+" "
			 * 		+vo.getDvo().getLoc()+" "
			 * 		+vo.getSvo().getGrade());
			 *  %>
			 *  <br>
			 */
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getHiredate().toString()+" "
					+vo.getSal()+" "
					+vo.getJob()+" "
					+vo.getDvo().getDname()+" "
					+vo.getDvo().getLoc()+" "
					+vo.getSvo().getGrade());
		}
	}

}
