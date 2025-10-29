package com.sist.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
import com.sist.commons.*;

public class MusicDAO {
	private static SqlSessionFactory ssf;
	
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	/*
	 *     <select id="musicListData" resultType="MusicVO" parameterType="hashmap">
     *       SELECT no, title, singer, poster, num
     *       FROM (SELECT no, title, singer, poster, rownum as num
     *       FROM (SELECT no, title, singer, poster
     *       FROM genie_music ORDER BY no))
     *       WHERE num BETWEEN #{start} AND #{end}
     *     </select>
	 */
	public static List<MusicVO> musicListData(Map map) {
		SqlSession session = ssf.openSession();
		List<MusicVO> list = session.selectList("musicListData", map);
		session.close();
		return list;
	}
	
	public static int musicTotalPage() {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("musicTotalPage");
		session.close();
		return total;
	}
	
	public static MusicVO musicDetailData(int no) {
		SqlSession session = ssf.openSession();
		MusicVO vo = session.selectOne("musicDetailData", no);
		session.close();
		return vo;
	}
	
	/*
	 *   <!-- 장르별 -->
  <select id="musicTypeListData" resultType="MusicVO" parameterType="hashmap">
    SELECT no, cno, title, poster
    FROM genie_music
    WHERE type LIKE '%'||#{cno}||'%'
    ORDER BY no ASC
    OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
  </select>
  <select id="musicTypeTotalPage" resultType="int" parameterType="string">
    SELECT CEIL(COUNT(*)/12.0)
    FROM genie_music
    WHERE type LIKE '%'||#{cno}||'%'
  </select> 
	 */
	public static List<MusicVO> musicTypeListData(Map map) {
		List<MusicVO> list = null;
		try {
			SqlSession session = ssf.openSession();
			list = session.selectList("musicTypeListData", map);
			session.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public static int musicTypeTotalPage(int cno) {
		int total = 0;
		try {
			SqlSession session = ssf.openSession();
			total = session.selectOne("musicTypeTotalPage", cno);
			session.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return total;
	}
	
	/*
	 *   <select id="musicFindListData" resultType="MusicVO" parameterType="hashmap">
	       SELECT no, title, poster, singer
	       FROM genie_music
	       WHERE ${column} LIKE '%'||#{ss}||'%'
	     </select>
	 */
	public static List<MusicVO> musicFindListData(Map map) {
		List<MusicVO> list = null;
		try { 
			SqlSession session = ssf.openSession();
			list = session.selectList("musicFindListData", map);
			session.close(); 
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
