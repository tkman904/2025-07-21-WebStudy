package com.sist.dao;
/*
 *   1. 목록 ------
 *   			 | sendRedirect
 *   2. 글쓰기 -----
 *   3. 상세보기 ---
 *   			 |
 *   4. 수정 ------
 *   5. 삭제 ----------- 목록
 */
import java.util.*;
import java.io.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.BoardVO;

public class BoardDAO {
	private static SqlSessionFactory ssf;
	
	static {
		// XML을 파싱 => 저장된 데이터 읽기
		try {
			// XMl 파일 읽기
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
			// map에 저장 => key=id value=SQL
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	/*
	 *   <select id="boardListData" resultType="BoardVO" parameterType="hashmap">
    		SELECT no, subject, name, 
    	   		TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num
    		FROM (SELECT no, subject, name, regdate, hit, rownum as num
    		FROM (SELECT no, subject, name, regdate, hit
    		FROM jspBoard ORDER BY no DESC))
    		WHERE num BETWEEN #{start} AND #{end}
  		 </select>
	 */
	public static List<BoardVO> boardListData(Map map) {
		SqlSession session = ssf.openSession();
		// getConnection()
		List<BoardVO> list = session.selectList("boardListData", map);
		session.close();
		return list;
	}
	
	/*
	 *   <select id="boardTotalPage" resultType="int">
    		SELECT CEIL(COUNT(*)/10.0) FROM jspBoard
  		 </select>
	 */
	public static int boardTotalPage() {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("boardTotalPage");
		session.close();
		return total;
	}
	
	/*
	 *   <insert id="boardInsert" parameterType="BoardVO">
    		<selectKey keyProperty="no" resultType="int" order="BEFORE">
      		SELECT NVL(MAX(no)+1, 1) as no FROM jspBoard
    		</selectKey>
    		INSERT INTO jspBoard(no, name, subject, content, pwd)
    		VALUES(#{no}, #{name}, #{subject}, #{content}, #{pwd})
         </insert>
	 */
	public static void boardInsert(BoardVO vo) {
		SqlSession session = ssf.openSession(true);
		session.insert("boardInsert", vo);
		//session.commit();
		session.close();
	}
	
	/*
	 *   <update id="hitIncrement" parameterType="int">
    		UPDATE jspBoard SET
    		hit=hit+1
    		WHERE no=#{no}
  		 </update>
  		 <select id="boardDetailData" resultType="BoardVO" parameterType="int">
    		SELECT no, name, subject, content, hit,
    			TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday
    		FROM jspBoard
    		WHERE no=#{no}
  		 </select>
	 */
	public static BoardVO boardDetailData(int no) {
		SqlSession session = ssf.openSession();
		// 조회수 증가
		session.update("hitIncrement", no);
		session.commit();
		BoardVO vo = session.selectOne("boardDetailData", no);
		session.close();
		return vo;
	}
	
	/*
	 *   <select id="boardUpdateData" resultType="BoardVO" parameterType="int">
    		SELECT no, name, subject, content
    		FROM jspBoard
    		WHERE no=#{no}
  		 </select>
	 */
	public static BoardVO boardUpdateData(int no) {
		SqlSession session = ssf.openSession();
		BoardVO vo = session.selectOne("boardUpdateData", no);
		session.close();
		return vo;
	}
	
	/*
	 *   <select id="boardGetPassword" resultType="string" parameterType="int">
    		SELECT pwd FROM jspBoard
    		WHERE no=#{no}
  		 </select>
  		 
  		 <update id="boardUpdate" parameterType="BoardVO">
    		UPDATE jspBoard SET
    		name=#{name}, subject=#{subject}, content=#{content}
    		WHERE no=#{no}
  		 </update>
	 */
	public static boolean boardUpdate(BoardVO vo) {
		boolean bCheck = false;
		SqlSession session = ssf.openSession(true);
		
		// 1. 비밀번호
		String db_pwd = session.selectOne("boardGetPassword", vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			// 수정
			bCheck = true;
			session.update("boardUpdate", vo);
		}
		session.close();
		return bCheck;
	}
	
	/*
	 *   <select id="boardGetPassword" resultType="string" parameterType="int">
    		SELECT pwd FROM jspBoard
    		WHERE no=#{no}
  		 </select>
  		 
	     <delete id="boardDelete" parameterType="int">
    		DELETE FROM jspBoard
    		WHERE no=#{no}
  		 </delete>>
	 */
	public static boolean boardDelete(int no, String pwd) {
		boolean bCheck = false;
		SqlSession session = ssf.openSession(true);
		String db_pwd=session.selectOne("boardGetPassword", no);
		if(db_pwd.equals(pwd)) {
			bCheck = true;
			session.delete("boardDelete", no);
		}
		session.close();
		return bCheck;
	}
}
