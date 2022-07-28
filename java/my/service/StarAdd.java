package my.service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import af.servlet.util.FastJSON;
import af.sql.c3p0.AfSimpleDB;
import my.db.Like;

@WebServlet("/StarAdd")
public class StarAdd extends RestServlet {

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		int comID = FastJSON.intValue(json, "id", 0);
		int userID = FastJSON.intValue(json, "userid", 0);
		if(comID == 0 || userID == 0) {
			throw new Exception("Didn't find comID and/or userID");
		}
		Like l = new Like();
		l.comID = comID;
		l.userID = userID;
		AfSimpleDB.insert(l);
		return l;
	}

}
