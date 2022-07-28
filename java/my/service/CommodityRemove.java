package my.service;

import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import af.servlet.util.FastJSON;
import af.sql.c3p0.AfSimpleDB;
import af.sql.util.AfSqlUpdate;
import af.sql.util.AfSqlWhere;

@WebServlet("/CommodityRemove")
public class CommodityRemove extends RestServlet {

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		int comID = FastJSON.intValue(json, "id", 0);
		AfSqlUpdate update = new AfSqlUpdate("CommodityInfo");
		update.add2("status", false);
		update.add2("deleteTime", new Date().toString());

		AfSqlWhere where = new AfSqlWhere();
		where.add2("comID", comID);

		String sql = update + " " + where;
		AfSimpleDB.execute(sql);

		return "";
	}

}
