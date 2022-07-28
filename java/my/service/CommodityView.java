package my.service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import af.servlet.util.FastJSON;
import af.sql.c3p0.AfSimpleDB;
import my.db.Commodity;
import my.db.Like;

@WebServlet("/CommodityView")
public class CommodityView extends RestServlet {

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		int id = FastJSON.intValue(json, "id", 0);
		int userid = FastJSON.intValue(json, "userid", 0);

		String sql = "select * from CommodityInfo where comID=" + id;
		Commodity c = (Commodity) AfSimpleDB.get(sql, Commodity.class);
		if (c == null)
			throw new Exception("Record doesn't exist, comId = " + id);
		JSONObject jdata = (JSONObject)JSON.toJSON(c);
		if(c.iconUrl.length()>0)
		{
			String url = request.getContextPath() + "/data/" + c.iconUrl;
			jdata.put("iconUrl", url);
		}
		
		String sql2 = "select * from LikeInfo where comID=" + id + " AND userID=" + userid;
		Like l = (Like) AfSimpleDB.get(sql2, Like.class);
		if(l != null) {
			jdata.put("isStar", true);
		}
		return jdata;
	}

}
