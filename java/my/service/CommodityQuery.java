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

@WebServlet("/CommodityQuery")
public class CommodityQuery extends RestServlet {

	/**
	 * total : number of records
	 * page : current page; 
	 * pageSize : number of items on each page, default 10; 
	 * pageCount : number of page;
	 */

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		int page = FastJSON.intValue(json, "page", 1);
		int userid = FastJSON.intValue(json, "userid", 0);
		
		int total = 0;
		String where = "where status=1";
		if(userid == 0) {
			total = getTotal(where); 
		}
		else {
			where = "where userID=" + userid;
			total = getTotal(where);
		}

		
		int pageSize = 10; 
		int pageCount = total / pageSize; 
		if (total % pageSize != 0)
			pageCount += 1;

		// revise page range
		if (page < 0)
			page = 0;
		if (page > pageCount)
			page = pageCount;
		

		String order = " ORDER BY COMID DESC ";
		String limit = String.format(" LIMIT %d,%d ", pageSize * (page - 1), pageSize);

		String sql = "select * from CommodityInfo " + where + order + limit;
		System.out.println("Query: " + sql);

		List<Commodity> rows = AfSimpleDB.query(sql, Commodity.class);

		JSONObject jdata = new JSONObject();
		jdata.put("total", total);
		jdata.put("page", page);
		jdata.put("pageSize", pageSize);
		jdata.put("pageCount", pageCount);
		JSONArray items = new JSONArray();
		jdata.put("items", items);
		
		for(Commodity r : rows )
		{
			JSONObject j1 = (JSONObject)JSON.toJSON(r);
			// create real URL for the picture and send it back to the front-end
			if(r.iconUrl.length()>0)
			{
				String url = request.getContextPath() + "/data/" + r.iconUrl;
				j1.put("icon", r.iconUrl);
				j1.put("iconUrl", url);
			}
			items.add( j1 );
		}
		
		return jdata;
	}

	private int getTotal(String where) throws Exception {
		String sql = " select count(comID) from CommodityInfo " + where;
		String[] row = AfSimpleDB.get(sql);
		if (row != null) {
			return Integer.valueOf(row[0]);
		}
		return 0;
	}

}
