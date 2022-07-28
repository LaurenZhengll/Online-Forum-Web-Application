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

@WebServlet("/UserView")
public class UserView extends RestServlet {

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		int userid = FastJSON.intValue(json, "userid", 0);

		String sql = "select * from UserInfo where userID=" + userid;
		User u = (User) AfSimpleDB.get(sql, User.class);
		if (u == null)
			throw new Exception("User nonexist! userId = " + userid);
		JSONObject jdata = (JSONObject)JSON.toJSON(u);
		String url = "";
		if(u.avatar != null)
		{
			url = request.getContextPath() + "/avatar/" + u.avatar;
			jdata.put("avatarUrl", url);
		}
		return jdata;
	}

}
