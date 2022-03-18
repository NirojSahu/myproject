package Scenarios3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

public class dataprovider_summation {
	
	@DataProvider(name="dp_Sum")
	public static Iterator<Object[]> getsummationdata() throws IOException
	{
		ExcelReadWrite xl= new ExcelReadWrite("D:\\Sum1.xls");
		HSSFSheet Sheet1 = xl.Setsheet("Sheet1");
		
		int RowCount = xl.getrowcount(Sheet1);
		System.out.println(RowCount);
		
		List<Object[]> arr_list= new ArrayList<Object[]>();
		
		
		
		for(int i=1;i<=RowCount;i++)
		{
			Map<String,String> dMap= new HashMap<String,String>();
			
			dMap.put(xl.Readvalue(Sheet1, 0, "Num1"), (xl.Readvalue(Sheet1, i, "Num1")));
			dMap.put(xl.Readvalue(Sheet1, 0, "Num2"), (xl.Readvalue(Sheet1, i, "Num2")));
			dMap.put(xl.Readvalue(Sheet1, 0, "Exp_Result"), (xl.Readvalue(Sheet1, i, "Exp_Result")));
			
			Object[] Obj= new Object[1];
			Obj[0]=dMap;
			
			System.out.println(Obj[0]);
			
			arr_list.add(Obj);
			
		}
		
		
		return arr_list.iterator();
		
	}

}
