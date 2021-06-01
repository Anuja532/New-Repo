package genericfunctionslib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel {

  public static List<Map<String, String>> getTestData() throws IOException {
	  File file = new File(".\\src\\testdata\\TestData.xlsx");
	  FileInputStream fis = new FileInputStream(file);

	  XSSFWorkbook wb = new XSSFWorkbook(fis);
	  XSSFSheet sheet = wb.getSheetAt(0);
	  wb.close();
	  int lastRowNum = sheet.getLastRowNum() ;
	  int lastCellNum = sheet.getRow(0).getLastCellNum();

	  List<Map<String, String>> dataElements = new ArrayList<Map<String, String>>();

	  // [{testCase:1, userName:user1, password:pass1, expected:beh1}, 
	  //  {testCase:2, userName:user2, password:pass2, expected:beh2}]
	  for (int i = 1; i <= lastRowNum; i++) {
		  Map<String, String> dataElement = new HashMap<String, String>();
		  for (int j = 0; j < lastCellNum; j++) {
			  if (sheet.getRow(i).getCell(j) != null) {
				  
				  dataElement.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i).getCell(j).toString());	    			
			  } else {
				  dataElement.put(sheet.getRow(0).getCell(j).toString(), "");	    				    			
			  }
		  }
		  dataElements.add(dataElement);
	  }
	  return dataElements;
  }

}


