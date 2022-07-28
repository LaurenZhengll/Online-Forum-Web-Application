package my.service;

import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import af.sql.c3p0.AfSimpleDB;
import my.db.Commodity;

@WebServlet("/CommodityNew")
public class CommodityNew extends RestServlet {

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json)
			throws Exception {
		Commodity c = new Commodity();
		c.title = "N/A";
		c.description = "";
		c.tag = "";
		c.price = 0;
		c.userID = 1;
		c.iconUrl = "";
		c.state = 0;
		c.status = true;
		c.stock = 0;
		c.createTime = new Date();
		c.deleteTime = null;
		
		AfSimpleDB.insert(c);

		return c;
	}

}
