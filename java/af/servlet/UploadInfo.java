package af.servlet;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Upload information
public class UploadInfo
{
	public String realName ;
	public String suffix ;    
	public String tmpFileName ; // temperate file name in server
	public File tmpFile ;   
	public long fileSize ;  
	
	public HttpServletRequest request;
	public HttpServletResponse response;
}
