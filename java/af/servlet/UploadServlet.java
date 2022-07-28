package af.servlet;

import java.io.File;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import af.servlet.util.FastJSON;
import af.servlet.util.WebUtil;

/**
 * need JAR package:
 *   commos-fileupload .jar
 *   commons-io.jar
 * 
 * afanihao.cn
 */

public abstract class UploadServlet extends HttpServlet
{	 
	protected File tmpDir; 
	
	protected long sizeLimit = 1000000*100;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(tmpDir==null) throw new RuntimeException("Upload: NO tmpDir!");
		
		JSONObject jresp = new JSONObject(true);
		try
		{			
			jresp.put("error", 0);
			jresp.put("reason", "OK");
			
			Object data = parseUpload(request, response);

			if (data != null)
			{
				if (data instanceof JSONObject) 
					jresp = (JSONObject) data;
				else
					jresp.put("data", data);
			}

		} catch (Exception e)
		{
			jresp.put("error", -1);
			jresp.put("reason", e.getMessage());
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.write( FastJSON.niceFormat( jresp));
		out.close();
	}

	private Object parseUpload(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart)
			throw new Exception("请求编码必须为: multipart/form-data !");

		request.setCharacterEncoding("UTF-8");

		ServletFileUpload upload = new ServletFileUpload(); 
		FileItemIterator iter = upload.getItemIterator(request);
		
		while (iter.hasNext())
		{
			FileItemStream item = iter.next();
			String fieldName = item.getFieldName();
			InputStream inputStream = item.openStream();
			if (item.isFormField())
			{
				String fieldValue = Streams.asString(inputStream, "UTF-8");
				System.out.println("普通表单字段:" + fieldName + "=" + fieldValue);
			} else
			{
				UploadInfo info = new UploadInfo();
				info.realName = item.getName(); 
				info.suffix = WebUtil.getSuffix(info.realName); 
				info.tmpFileName = WebUtil.createTmpName(info.suffix);
				info.tmpFile = new File(tmpDir, info.tmpFileName); 
				info.fileSize = 0; 

				// System.out.println("Start upload file: " + info.realName + " >> " + info.tmpFile);

				info.tmpFile.getParentFile().mkdirs();
				FileOutputStream outputStream = new FileOutputStream(info.tmpFile);
				try
				{
					info.fileSize = saveUpload(inputStream, outputStream, sizeLimit);
				} finally
				{
					try{
						outputStream.close();
					} catch (Exception e)
					{
					}					
				}

				info.request = request;
				info.response = response;
				return handleFile( info );
			}
		}
		
		throw new Exception("There is no file data in the request!");
	}

	private long saveUpload(InputStream in, OutputStream out, long limit) throws Exception
	{
		long count = 0;
		byte[] buf = new byte[8192];
		while (true)
		{
			int n = in.read(buf);
			if (n < 0)
				break;
			if (n == 0)
				continue;
			out.write(buf, 0, n);

			count += n;
			if(count > limit)
				throw new Exception("Exceed file size limit > " + limit);
		}
		return count;
	}
	
	protected abstract Object handleFile(UploadInfo info) throws Exception;


	
}
