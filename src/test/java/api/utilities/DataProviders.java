package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	
	@DataProvider(name="Data")
	public String [][] getData() throws IOException
	{
		String path="/Users/shubham_infinity/Desktop/test1/PetStoreAutomation/testData/Userdata.xlsx";//taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);
				
		String logindata[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
		
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0
			}
		}
	return logindata;//returning two dimension array
				
	}
	
	
	//DataProvider 2
	//type 1
//	@DataProvider(name="UserNames")
//	public String[] getUserNames() throws IOException
//	{
//		String path="/Users/shubham_infinity/Desktop/test1/PetStoreAutomation/testData/Userdata.xlsx";//taking xl file from testData
//	    ExcelUtility x1 = new ExcelUtility(path);
//
//	    int rownum = x1.getRowCount("Sheet1");
//	    String apidata[] = new String[rownum];
//
//	    for (int i = 1; i <= rownum; i++) {
//	        apidata[i - 1] = x1.getCellData("Sheet1", i, 1);
//	    }
//
//	    return apidata;
//	}
	
	//type 2
	@DataProvider(name = "UserNames")
	public Object[][] getUserNameData() throws IOException {

		String path="/Users/shubham_infinity/Desktop/test1/PetStoreAutomation/testData/Userdata.xlsx";//taking xl file from testData

	    ExcelUtility xlutil = new ExcelUtility(path);

	    String sheetName = "Sheet1";

	    int totalRows = xlutil.getRowCount(sheetName);
	    int userNameColumnIndex = 1; // UserName column

	    Object[][] usernames = new Object[totalRows][1];

	    for (int i = 1; i <= totalRows; i++) {
	        usernames[i - 1][0] = xlutil.getCellData(sheetName, i, userNameColumnIndex);
	    }

	    return usernames;
	}
	
	//DataProvider 3
	
	//DataProvider 4
}
