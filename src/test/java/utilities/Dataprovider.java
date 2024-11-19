package utilities;

import org.testng.annotations.DataProvider;
import java.io.IOException;

public class Dataprovider{
	//Data Provider 1
	@DataProvider(name="LoginData")
	public String[][] getAllData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx"; //taking xl file from testdata
		ExcelUtility xl=new ExcelUtility(path); // creating an object for XLUtility
		
		int totalrow=xl.getRowCount("Sheet1");
		int totalcols=xl.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrow][totalcols]; // created for two dimensional array which can store
		
		for(int i=1;i<totalrow;i++) //1 // read the data from xl storing in two dimensional array
		{
			for(int j=0;j<totalcols;j++) // 0  i is rows j is col
			{
				logindata[i-1][j]=xl.getCellDate("Sheet1", i, j); // 1,0
			}
		}
		return logindata; // returning 2 dimensional array
		
		}

	// Data provider 2
	
	@DataProvider(name="UserNames")
	public String[] getUserName() throws IOException
	{
		String path=System.getProperty("user.dir")+"/Userdata.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		
		int rownum=xl.getRowCount("Sheet1");
		
		
		String[]logindata=new String[rownum];
		
		for(int i=1;i<rownum;i++)
		{
			logindata[i-1]=xl.getCellDate("Sheet1", i,1);
			
		}
		return logindata;
		
		}
}
