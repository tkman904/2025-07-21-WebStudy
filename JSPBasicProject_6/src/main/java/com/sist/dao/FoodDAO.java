package com.sist.dao;

import java.util.*;
import java.io.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.FoodVO;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	
	static {
		// XML에 등록된 데이터를 읽어서 Map에 저장하는 과정
		// XML 파싱
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// SQL문장 실행 결과값
	/*
	 *   <select id="foodListData" resultType="FoodVO" parameterType="hashmap">
     *     SELECT fno, name, poster, address, num
     *     FROM (SELECT fno, name, poster, address, rownum as num
     *     FROM (SELECT fno, name, poster, address
     *     FROM menupan_food ORDER BY fno ASC))
     *     WHERE num BETWEEN #{start} AND #{end}
     *   </select>
     *   
     *   <select id="foodTotalPage" resultType="int">
     *     SELECT CEIL(COUNT(*)/12.0) FROM menupan_food
     *   </select>
	 */
	public static List<FoodVO> foodListData(Map map) {
		List<FoodVO> list = new ArrayList<FoodVO>();
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("foodListData", map);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static int foodTotalPage() {
		int total = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			total = session.selectOne("foodTotalPage");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	/*
	 *   <select id="foodDetailData" resultType="FoodVO" parameterType="int">
     *     SELECT * FROM menupan_food
     *     WHERE fno=#{fno}
  	 *   </select>
	 */
	public static FoodVO foodDetailData(int fno) {
		SqlSession session = ssf.openSession();
		FoodVO vo = session.selectOne("foodDetailData", fno);
		session.close();
		return vo;
	}
}
