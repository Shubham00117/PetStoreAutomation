package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.playload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTest {

	
	@Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testPostUser(String userID,String userName,String fname,String lname,String useremail,String pwd, String ph) 
	{
		User user=new User();
		
		 user.setId(Integer.parseInt(userID));
		    user.setUsername(userName);   
		    user.setFirstname(fname);
		    user.setLastname(lname);
		    user.setEmail(useremail);
		    user.setPassowrd(pwd);
		    user.setPhone(ph);
		
		Response response =	UserEndPoints.createUser(user);
		AssertJUnit.assertEquals(response.statusCode(), 200);
		
		
	}
	
	@Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String username) {
		
	Response response =	UserEndPoints.deleteUser(username);
		AssertJUnit.assertEquals(response.statusCode(), 200);
		
	}
}
