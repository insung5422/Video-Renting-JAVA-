package videoco;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MaintainOrder {
	public ArrayList<Order> tList = new ArrayList<Order>();

	public void load() throws Exception {
		FileInputStream file = new FileInputStream("./src/files/orders.xlsx");

		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet orders = csv.getSheet("orders");

		int rows = 0;

		Iterator<Row> iterator = orders.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			XSSFRow row = (XSSFRow) iterator.next();

			if (row.getCell(0).getStringCellValue().equals("") && row.getCell(1).getStringCellValue().equals("")) {
				rows = i - 1;
				break;
			}
			i++;
		}
		rows = i-1;

		for (int r = 1; r <= rows; r++) {
			Order order = new Order();
			XSSFRow row = orders.getRow(r);

			order.setName(row.getCell(0).getStringCellValue());
			order.setMovie(row.getCell(1).getStringCellValue());
			tList.add(order);
		}
		file.close();
	}
	
	public void update() throws IOException {
		FileInputStream file = new FileInputStream("./src/files/orders.xlsx");

		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet orders = csv.getSheet("orders");

		int rows = tList.size();
		int cols = 2;

		for (int r = 1; r <= rows; r++) {
			XSSFRow row = orders.createRow(r);

			for (int c = 0; c < cols; c++) {
				XSSFCell cell = row.createCell(c);
				String value = "";

				switch (c) {
				case 0:
					value = tList.get(r - 1).getName();
					break;
				case 1:
					value = tList.get(r - 1).getMovie();
					break;
				}
				cell.setCellValue(value);

			}
		}

		FileOutputStream outFile = new FileOutputStream("./src/files/orders.xlsx");
		csv.write(outFile);

		outFile.close();
	}

	public boolean addOrder(Order newTen) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Order t : tList) {
			if (t.getMovie().equals(newTen.getMovie())) {
				return false;
			}
		}
		tList.add(newTen);
		try {
			update();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean removeOrder(String movieTitle) throws IOException {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		} // load data

		Order delTen = null;
		for (Order t : tList) {
			if (t.getMovie().equals(movieTitle)) {
				delTen = t;
			}
		}
		if(delTen == null) {
			return false;
		}
		tList.remove(delTen);// delete from userList

		try {
			update();
		} catch (IOException e) {
			e.printStackTrace();
		}// update with new userList

		// removing last line from excel;
		FileInputStream file = new FileInputStream("./src/files/orders.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet orders = csv.getSheet("orders");

		int rows = 0;

		Iterator<Row> iterator = orders.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			XSSFRow row = (XSSFRow) iterator.next();

			if (row.getCell(0).getStringCellValue().equals("") && row.getCell(1).getStringCellValue().equals("")) {
				rows = i - 1;
				break;
			}
			i++;
			rows = i - 1;
		}

		removeRow(orders, rows);

		FileOutputStream outFile = new FileOutputStream("./src/files/orders.xlsx");
		csv.write(outFile);
		outFile.close();
		return true;
		
	}

	public void removeRow(XSSFSheet sheet, int rowIndex) {
		int lastRowNum = sheet.getLastRowNum();
		if (rowIndex >= 0 && rowIndex < lastRowNum) {
			sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
		}
		if (rowIndex == lastRowNum) {
			Row removingRow = sheet.getRow(rowIndex);
			if (removingRow != null) {
				sheet.removeRow(removingRow);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		MaintainOrder ten = new MaintainOrder();
		ten.load();
		
		//Order tt = new Order("jimmy1", "IronMan");
		//ten.addOrder(tt);
		//ten.removeOrder("IronMan");
		for (Order t : ten.tList) {
			System.out.println(t.toString());
		}
	}
	
}
