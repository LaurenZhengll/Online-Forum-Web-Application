package my.service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import af.servlet.util.FastJSON;
import af.sql.c3p0.AfSimpleDB;

@WebServlet("/StarRemove")
public class StarRemove extends RestServlet {

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		int comID = FastJSON.intValue(json, "id", 0);
		int userID = FastJSON.intValue(json, "userid", 0);
		String sql = "delete from LikeInfo where comID='" + comID + "' AND userID='" + userID + "'";
		AfSimpleDB.execute(sql);
		return "Deleted!";
	}

}
