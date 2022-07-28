package my.service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import af.servlet.util.FastJSON;
import af.sql.c3p0.AfSimpleDB;

@WebServlet("/CommodityClear")
public class CommodityClear extends RestServlet {

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		int comID = FastJSON.intValue(json, "id", 0);
		if(comID == 0) {
			throw new Exception("didn't find commodity which comID = " + comID + "!");
		}
		String sql = "delete from CommodityInfo where comID=" + comID;
		AfSimpleDB.execute(sql);
		return "Deleted!";
	}

}
