package my.db; 

import af.sql.annotation.AFCOLUMNS; 
import af.sql.annotation.AFTABLE; 

@AFTABLE(name="LikeInfo")  
@AFCOLUMNS() 
public class Like 
{ 
 
	public Integer userID ; 
	public Integer comID ;
	
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getComID() {
		return comID;
	}
	public void setComID(Integer comID) {
		this.comID = comID;
	}
}
