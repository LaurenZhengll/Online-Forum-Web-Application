package my.service;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import af.servlet.UploadInfo;
import af.servlet.UploadServlet;
import af.servlet.util.FormData;
import af.sql.c3p0.AfSimpleDB;
import af.sql.util.AfSqlUpdate;

@WebServlet("/AvatarUpload")
public class AvatarUpload extends UploadServlet {

	@Override
	public void init() throws ServletException {
		this.tmpDir = new File(this.getServletContext().getRealPath("/avatar"));
		this.tmpDir.mkdir();
	}

	@Override
	protected Object handleFile(UploadInfo info) throws Exception {
		String queryString = info.request.getQueryString();
		FormData query = FormData.parse(queryString, "UTF-8");

		int userid = query.getInt("userid", 0);

		AfSqlUpdate update = new AfSqlUpdate("UserInfo");
		update.add2("avatar", info.tmpFileName);
		String sql = update + " WHERE userID=" + userid;

		AfSimpleDB.execute(sql);

		System.out.println("Avatar Update >> userid=" + userid + ", file:" + info.tmpFileName);

		String url = info.request.getContextPath() + "/avatar/" + info.tmpFileName;
		return url;
	}

}
