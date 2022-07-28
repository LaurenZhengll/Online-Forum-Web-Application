package my.service;

import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import af.sql.c3p0.AfSimpleDB;
import my.db.User;

@WebServlet("/UserNew")
public class UserNew extends RestServlet {

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		User u = JSON.toJavaObject(json, User.class);
		
		String sql = "select * from UserInfo where email='" + u.email + "'";
		User user = (User) AfSimpleDB.get(sql, User.class);
		
		if(user == null) {
			AfSimpleDB.insert(u);
		}
		else {
			return "Email already exist!";
		}

		return u;
	}
	
}
