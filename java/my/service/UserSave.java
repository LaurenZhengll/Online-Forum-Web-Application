package my.service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import af.sql.c3p0.AfSimpleDB;
import af.sql.util.AfSqlUpdate;
import af.sql.util.AfSqlWhere;
import my.db.User;

@WebServlet("/UserSave")
public class UserSave extends RestServlet {

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		User r = JSON.toJavaObject(json, User.class);

		AfSqlUpdate update = new AfSqlUpdate("UserInfo");
		update.add2("userName", r.userName);
		update.add2("password", r.password);
		update.add2("phone", r.phone);
		update.add2("email", r.email);

		AfSqlWhere where = new AfSqlWhere();
		where.add2("userID", r.userID);

		String sql = update + " " + where;
		AfSimpleDB.execute(sql);

		return "";
	}

}
