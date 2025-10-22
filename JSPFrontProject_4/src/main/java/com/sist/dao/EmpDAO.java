package com.sist.dao;

import java.util.*;
import java.io.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/*
 *   1. 초기화
 *      생성자, 인스턴스 블록
 *      static 블록
 *      
 *      static블록 => 인스턴스블록 => 생성자
 *       |           ----------------
 *       |           | 인스턴스 변수 (생성자)
 *       | static변수 초기화 : 생성자를 호출할 필요가 없다
 *   2. 상속 예외 조건
 */

import com.sist.vo.EmpVO;
public class EmpDAO {
	private static SqlSessionFactory ssf;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
			// XML 파싱 => 태그 / 속상에 저장된 데이터를 읽어온다
			// MyBatis에서 지정한 태그, 속성만 사용이 가능
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// 기능
	/*
	 *   <select id="empListData" resultType="com.sist.vo.EmpVO">
     *     SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, sal, deptno
     *     FROM emp
     *   </select>
     */   
	public static List<EmpVO> empListData() {
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empListData");
		session.close();
		return list;
	}
	
	/*   <select id="empDetailData" resultType="com.sist.vo.EmpVO" parameterType="int">
     *     SELECT empno, ename, job, mgr, sal, comm, deptno, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday
     *     FROM emp
     *     WHERE empno=#{empno}
     *   </select>
	 */
	public static EmpVO empDetailData(int empno) {
		SqlSession session = ssf.openSession();
		EmpVO vo = session.selectOne("empDetailData", empno);
		session.close();
		return vo;
	}
}
