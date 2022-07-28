package af.servlet.util;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class FormData extends HashMap<String,String>
{
	
	public static FormData parse(String data, String encoding)
	{
		FormData formData = new FormData();
		
		String[] aaa= data.split("&");
		for(String a : aaa)
		{
			String[] bbb = a.split("=");
			if(bbb.length == 2)
			{
				String key = bbb[0];
				String value = bbb[1];
				if(value==null) continue;
				
				if(encoding != null)
				{
					try {
						value = URLDecoder.decode(value, encoding);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				formData.put(key, value);
			}			
		}
		
		return formData;
	}
	
	/////////////////////////////////
	
	public int getInt(String key, int defValue)
	{
		try {
			String valueStr = this.get(key);
			if(valueStr==null||valueStr.length()==0)
				return defValue;
			return Integer.valueOf(valueStr);
		}catch(Exception e) {
			return defValue;
		}
	}
	
	public long getLong(String key, long defValue)
	{
		try {
			String valueStr = this.get(key);
			if(valueStr==null||valueStr.length()==0)
				return defValue;
			return Long.valueOf(valueStr);
		}catch(Exception e) {
			return defValue;
		}
	}
	
	public boolean getBoolean(String key, boolean defValue)
	{
		try {
			String valueStr = this.get(key);
			if(valueStr==null||valueStr.length()==0)
				return defValue;
			valueStr = valueStr.toLowerCase();
			return valueStr.equals("true")
					|| valueStr.equals("yes")
					|| valueStr.equals("1");
		}catch(Exception e) {
			return defValue;
		}
	}
	
	public String getString(String key, String defValue)
	{
		try {
			String valueStr = this.get(key);
			if(valueStr==null||valueStr.length()==0)
				return defValue;
			return valueStr;
		}catch(Exception e) {
			return defValue;
		}
	}
	
	public double getDouble(String key, double defValue)
	{
		try {
			String valueStr = this.get(key);
			if(valueStr==null||valueStr.length()==0)
				return defValue;
			return Double.valueOf(valueStr);
		}catch(Exception e) {
			return defValue;
		}
	}
}
