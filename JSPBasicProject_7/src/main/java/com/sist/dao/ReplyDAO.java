package com.sist.dao;

import java.util.*;
import com.sist.commons.*;
import com.sist.vo.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ReplyDAO {
	private static SqlSessionFactory ssf;
	
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	/*
	 *   <insert id="replyInsert" parameterType="com.sist.vo.ReplyVO">
    		 INSERT INTO reply(rno, fno, id, name, msg)
    		 VALUES(re_rno_seq.nextval, #{fno}, #{id}, #{name}, #{msg})
    	 <!-- 
    	 #{start} map = map.get("start")
    	 ========= map의 key
    	 #{id}
    	 ========= vo = vo.getId()
     	 -->
  		 </insert>
  		 <select id="replyListData" resultType="com.sist.vo.ReplyVO" parameterType="int">
    		 SELECT rno, fno, id, name, msg, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday
    		 FROM reply
    		 WHERE fno=#{fno}
  		 </select>
	 */
	public static void replyInsert(ReplyVO vo) {
		SqlSession session = ssf.openSession(true);
		session.insert("replyInsert", vo);
		//session.commit();
		session.close();
	}
	/*
	 *    selectOne()
	 * 	  ReplyVo vo = new ReplyVO();
	 * 	  vo.setId(rs.getString("id"))
	 *    ...
	 *    
	 *    selectList()
	 *    while(rs.next()) {
	 *    	ReplyVO vo = new ReplyVO();
	 *      vo.setId(rs.getString("id"))
	 *      vo.set ...
	 *    }
	 */
	public static List<ReplyVO> replyListData(int fno) {
		SqlSession session = ssf.openSession();
		List<ReplyVO> list = session.selectList("replyListData", fno);
		session.close();
		return list;
	}
	
	/*
	 *   <delete id="replyDelete" parameterType="int">
    		 DELETE FROM reply
    		 WHERE rno=#{rno}
  		 </delete>
  		 
  		 JSP => 링크 : 어떤 데이터 전송
  		 DAO 제작
  		 JSP에서 처리 => 이동 (response.sendRedirect())
	 */
	public static void replyDelete(int rno) {
		SqlSession session = ssf.openSession();
		session.delete("replyDelete", rno);
		session.commit();
		session.close();
	}
	
}
