package af.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public abstract class RestServlet extends HttpServlet
{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		this.processRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		this.processRequest(req, resp);
	}
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// 1 get content
		JSONObject json = null;
		try{
			String requestBody = requestBody(req, "UTF-8");
			json = JSON.parseObject(requestBody);
		} catch (Exception e)
		{
			json = new JSONObject();
		}

		// 2 handle data
		JSONObject result = new JSONObject(true);
		result.put("error", 0);
		result.put("reason", "OK");

		try
		{
			// handle request
			Object data = this.handle(req, resp, json);
			if (data != null)
			{
				if (data instanceof JSON) 
					result.put("data", data);
				else
					result.put("data", JSON.toJSON(data));
			}

		} catch (Exception e)
		{
			String reason = e.getMessage();
			if (reason == null)
				reason = e.getClass().getName();
			result.put("error", -1);
			result.put("reason", e.getMessage());
		}
		
		
		// 3 return response
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.getWriter().write( JSON.toJSONString(result, SerializerFeature.PrettyFormat) );
		
	}

	// to do
	protected abstract Object handle(HttpServletRequest request
			, HttpServletResponse response
			, JSONObject json)throws Exception;
	
	
	
	private String requestBody(HttpServletRequest req, String charset) throws IOException
	{
		ByteArrayOutputStream result = new ByteArrayOutputStream(1024*16);  
		
		InputStream inputStream = req.getInputStream();
        byte[] data = new byte[1024];  
        while (true)
        {
        	int n = inputStream.read(data);
        	if(n <= 0) break;
        	
        	result.write(data, 0, n);     	
        	if(result.size() > 1024*1024) 
        		break;
        }  
        
        return result.toString(charset);
	}


}
