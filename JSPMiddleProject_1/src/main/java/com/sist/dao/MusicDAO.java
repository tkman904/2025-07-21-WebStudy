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
}
