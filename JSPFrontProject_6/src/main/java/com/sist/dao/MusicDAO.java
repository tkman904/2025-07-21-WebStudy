package com.sist.dao;

import java.util.*;
import java.io.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.MusicVO;

public class MusicDAO {
	private static SqlSessionFactory ssf;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/*
	 *   <select id="musicMainData" resultType="com.sist.vo.MusicVO" parameterType="int">
     *     SELECT no, poster, title, rownum
     *     FROM (SELECT no, poster, title
     *     FROM genie_music WHERE cno=#{cno} ORDER BY no ASC)
     *     WHERE rownum&lt;=4
     *   </select>
	 */
	public static List<MusicVO> musicMainData(int cno) {
		SqlSession session = ssf.openSession();
		List<MusicVO> list = session.selectList("musicMainData", cno);
		session.close();
		return list;
	}
}
