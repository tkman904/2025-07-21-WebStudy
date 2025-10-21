package com.sist.dao;

import java.util.*;
import java.io.*;

import com.sist.vo.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	
	static {
		try {
			// XML읽기
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static List<FoodVO> foodListData(Map map) {
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodListData", map);
		session.close();
		return list;
	}
	
	public static int foodTotalPage() {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("foodTotalPage");
		session.close();
		return total;
	}
}
