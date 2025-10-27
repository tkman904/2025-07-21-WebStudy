package com.sist.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.*;
import com.sist.vo.*;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	/*
	 *   <select id="foodListData" resultType="FoodVO" parameterType="hashmap">
	 *     SELECT fno, name, poster, num
     *     FROM (SELECT fno, name, poster, rownum as num
     *     FROM (SELECT fno, name, poster
     *     FROM menupan_food ORDER BY fno))
     *     WHERE num BETWEEN #{start} AND #{end}
     *   </select>
	 */
	public static List<FoodVO> foodListData(Map map) {
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodListData", map);
		session.close();
		return list;
	}
	
	/*
	 *    <select id="foodTotalPage" resultType="int">
     *      SELECT CEIL(COUNT(*)/12.0)
     *      FROM menupan_food
     *    </select>
	 */
	public static int foodTotalPage() {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("foodTotalPage");
		session.close();
		return total;
	}
}
