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

@WebServlet("/CommoditySearchBox")
public class CommoditySearchBox extends RestServlet {

	/**
	 * total : number of records
	 * page : current page; 
	 * pageSize : number of items on each page, default 10; 
	 * pageCount : number of page;
	 */

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		int searchPage = FastJSON.intValue(json, "searchPage", 1);
		String input = FastJSON.stringValue(json, "input", "");
		
		String where = "where status=1 and title like '%" + input.trim() + "%'";
		int total = getTotal(where);

		
		int pageSize = 10;
		int pageCount = total / pageSize;
		if (total % pageSize != 0)
			pageCount += 1;

		if (searchPage < 0)
			searchPage = 0;
		if (searchPage > pageCount)
			searchPage = pageCount;
		
		String order = " ORDER BY COMID DESC ";
		String limit = String.format(" LIMIT %d,%d ", pageSize * (searchPage - 1), pageSize);

		String sql = "select * from CommodityInfo " + where + order + limit;
		System.out.println("Query: " + sql);

		List<Commodity> rows = AfSimpleDB.query(sql, Commodity.class);

		JSONObject jdata = new JSONObject();
		jdata.put("total", total);
		jdata.put("searchPage", searchPage);
		jdata.put("pageSize", pageSize);
		jdata.put("searchPageCount", pageCount);
		JSONArray items = new JSONArray();
		jdata.put("items", items);
		
		for(Commodity r : rows )
		{
			JSONObject j1 = (JSONObject)JSON.toJSON(r);

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
