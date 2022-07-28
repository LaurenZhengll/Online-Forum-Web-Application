package my.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import af.sql.c3p0.AfSimpleDB;
import af.sql.util.AfSqlUpdate;
import af.sql.util.AfSqlWhere;
import my.db.Commodity;

@WebServlet("/CommoditySave")
public class CommoditySave extends RestServlet {

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		Commodity r = JSON.toJavaObject(json, Commodity.class);

		AfSqlUpdate update = new AfSqlUpdate("CommodityInfo");
		update.add2("title", r.title);
		update.add2("description", r.description);
		update.add2("price", r.price + "");
		update.add2("stock", r.stock);
		if(r.userID != null) {
			update.add2("userID", r.userID);
		}
		

		AfSqlWhere where = new AfSqlWhere();
		where.add2("comID", r.comID);

		String sql = update + " " + where;
		AfSimpleDB.execute(sql);

		return "";
	}

}
