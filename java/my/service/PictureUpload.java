package my.service;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import af.servlet.UploadInfo;
import af.servlet.UploadServlet;
import af.servlet.util.FormData;
import af.sql.c3p0.AfSimpleDB;
import af.sql.util.AfSqlUpdate;

@WebServlet("/PictureUpload")
public class PictureUpload extends UploadServlet {

	@Override
	public void init() throws ServletException {
		// create folder to save the pictures
		this.tmpDir = new File(this.getServletContext().getRealPath("/data"));
		this.tmpDir.mkdir();
	}

	@Override
	protected Object handleFile(UploadInfo info) throws Exception {
		String queryString = info.request.getQueryString();
		FormData query = FormData.parse(queryString, "UTF-8");
		
		int id = query.getInt("id", 0);
		
		AfSqlUpdate update = new AfSqlUpdate("CommodityInfo");
		update.add2("iconUrl", info.tmpFileName);
		String sql = update + " WHERE comID=" + id;
		
		AfSimpleDB.execute(sql);
		
		System.out.println("Picture update >> id=" + id + ", file:" + info.tmpFileName);
		
		String url = info.request.getContextPath() + "/data/" + info.tmpFileName;
		return url;
	}

}
