package dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class file_input {
	
	private static XSSFWorkbook wk;
	private static XSSFSheet sh;
	private static XSSFRow rows;
	public static void main(String[] args) throws IOException{
		//(Using try with resources to close the stream[AutoCloseable])
//		File f=new File("G:\\Array_Names.xlsx");
//		try{
//		f.renameTo(new File("./Flipkart_Data.xlsx"));
//		}catch(Exception e){
//			System.out.println("File doesnot exist");
//		}
//		try(FileInputStream fis=new FileInputStream(f)){
//	
//			wk=new XSSFWorkbook();
//			sh=wk.getSheet("Sheet");
//			sh.getRow(1).getCell(1);
//		}catch(IOException e)
//		{
//			System.out.println("Exception is thrown");
//		}
//		finally{
//			FileInputStream fis=new FileInputStream("G:\\Selenium_2018\\FlipkartTest\\Flipkart_Data.xlsx");
//			Iterator<Row> row_iterator=sh.iterator();
//			while(row_iterator.hasNext()){
//				XSSFRow row=(XSSFRow) row_iterator.next();
//				Iterator<Cell>cellIterator=row.cellIterator();
//		         while (cellIterator.hasNext()) {
//		             Cell cell = cellIterator.next();
//		             
//		             switch (cell.getCellType()) {
//		                case Cell.CELL_TYPE_NUMERIC:
//		                   System.out.print(cell.getNumericCellValue() + " \t\t ");
//		                   break;
//		                
//		                case Cell.CELL_TYPE_STRING:
//		                   System.out.print(
//		                   cell.getStringCellValue() + " \t\t ");
//		                   break;
//		             }
////		  
//			}
//			}
		
		
			
//		}


		
		// TODO Auto-generated method stub
		
		
//		file_input fin=new file_input();
//		fin.read_file();
		try(FileInputStream fis=new FileInputStream(new File("G:\\Selenium_2018\\Webapplications\\"
				+ "src\\test\\java\\dataprovider\\Flipkart_data.xlsx"))){
		ArrayList<String>headers=new ArrayList<>();
		HashMap<String,Object>hm=new HashMap<>();
		ArrayList<HashMap<String,Object>> data=new ArrayList<HashMap<String,Object>>();
		Object cell_value =null;
		wk=new XSSFWorkbook(fis);
		sh=wk.getSheet("Sheet1");
		Iterator<Row> row_iterator=sh.iterator();
		while(row_iterator.hasNext()){
			XSSFRow row=(XSSFRow) row_iterator.next();
			int i =0;
			Iterator<Cell>cellIterator=row.cellIterator();
	         while (cellIterator.hasNext()) {
	             Cell cell = cellIterator.next();
	 			if (row.getRowNum()==0)
	 				
	 				headers.add(cell.getStringCellValue());
	 	
	 			else{
	 				
	 			
	             switch (cell.getCellType()) {
	                case Cell.CELL_TYPE_NUMERIC:
	                	 cell_value= new BigDecimal(cell.getNumericCellValue()).longValue();
	                	 
	                  break;
	                
	                case Cell.CELL_TYPE_STRING:
//	                   System.out.print(
//	                   cell.getStringCellValue() + " \t\t ");
	                   cell_value=cell.getStringCellValue().toString();
	                   break;
	                 
	                case Cell.CELL_TYPE_BLANK:
	                	cell.setCellValue("PASS");
	                	break;
	                	
	             
	         }
	             
	 			
	 			//System.out.println(headers.get(i)+ "  : "+cell_value);
	 			hm.put(headers.get(i), cell_value);
	 			
	 			i+=1;
	 			}
	         }
	         //System.out.println(hm);
	         data.add(hm);

	      
//	         System.out.println(data);
//	         hm.clear();
	         
	}
		  
	
		//System.out.println(data);
		for(HashMap <String,Object>m:data){
			//System.out.println(m.get("ID"));
			if(m.get("ID").toString().equals(String.valueOf(4)))
			System.out.println(m.get("USERNAME"));
		}
	
	}
		

	   	  
	         try(FileOutputStream fout=new FileOutputStream(new File("G:\\Selenium_2018\\Webapplications\\"
				+ "src\\test\\java\\dataprovider\\Flipkart_data.xlsx"))){
	         	
	         	wk.write(fout);}	
		}
	}

