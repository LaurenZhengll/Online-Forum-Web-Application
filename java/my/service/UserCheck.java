package my.service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import af.servlet.util.FastJSON;
import af.sql.c3p0.AfSimpleDB;
import my.db.User;

@WebServlet("/UserCheck")
public class UserCheck extends RestServlet {

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		int userID = FastJSON.intValue(json, "userID", 0);
		String email = FastJSON.stringValue(json, "email", "");
		String password = FastJSON.stringValue(json, "password", "");
		
		String sql = "";
		if(userID == 0) {
			sql = "select * from UserInfo where email='" + email + "' AND password='" + password + "'";
			
		}
		else {
			sql = "select * from UserInfo where userID='" + userID + "' AND password='" + password + "'";
			
		}
		User u = (User) AfSimpleDB.get(sql, User.class);
		
		if (u == null)
			throw new Exception("User does not exist ");
		JSONObject jdata = (JSONObject) JSON.toJSON(u);
		return jdata;
		

	}

}
