package my.service;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import af.servlet.util.FastJSON;
import af.sql.c3p0.AfSimpleDB;
import af.sql.util.AfSqlWhere;
import my.db.Commodity;
import my.db.Like;

@WebServlet("/StarQuery")
public class StarQuery extends RestServlet {

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		int page = FastJSON.intValue(json, "page", 1);
		int userid = FastJSON.intValue(json, "userid", 0);
		
		if(userid == 0) {
			throw new Exception("Didn't find userID");
		}
		
		String where = "where userID=" + userid;
		
		int total = getTotal(where);
		int pageSize = 10; 
		int pageCount = total / pageSize;
		if (total % pageSize != 0)
			pageCount += 1;

		
		if (page < 0)
			page = 0;
		if (page > pageCount)
			page = pageCount;

		String order = " ORDER BY COMID DESC ";
		String limit = String.format(" LIMIT %d,%d ", pageSize * (page - 1), pageSize);

		String sql = "select * from LikeInfo " + where + order + limit;
		System.out.println("Query: " + sql);

		// *******Update to directly get the comID*********
		List<Like> rows = AfSimpleDB.query(sql, Like.class);
		JSONObject jdata = new JSONObject();
		jdata.put("total", total);
		jdata.put("page", page);
		jdata.put("pageSize", pageSize);
		jdata.put("pageCount", pageCount);
		JSONArray items = new JSONArray();
		jdata.put("items", items);
		
		for(Like l : rows )
		{
			String sql_com = "select * from CommodityInfo where comID=" + l.comID;
			Commodity c = (Commodity) AfSimpleDB.get(sql_com, Commodity.class);
			JSONObject j1 = (JSONObject)JSON.toJSON(c);
			if(c.iconUrl.length()>0)
			{
				String url = request.getContextPath() + "/data/" + c.iconUrl;
				j1.put("icon", c.iconUrl);
				j1.put("iconUrl", url);
			}
			items.add( j1 );
		}
		
		return jdata;
	}

	private int getTotal(String where) throws Exception {
		String sql = " select count(comID) from LikeInfo " + where;
		String[] row = AfSimpleDB.get(sql);
		if (row != null) {
			return Integer.valueOf(row[0]);
		}
		return 0;
	}

}
