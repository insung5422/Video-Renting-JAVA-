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

public class MaintainAdmin {
	public ArrayList<Admin> adminList = new ArrayList<Admin>();
	private Admin currentAdmin;

	public void load() throws Exception {
		FileInputStream file = new FileInputStream("./src/files/data.xlsx");

		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet admins = csv.getSheet("admins");

		int rows = 0;

		Iterator<Row> iterator = admins.iterator();
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
			Admin admin = new Admin();
			XSSFRow row = admins.getRow(r);

			admin.setName(row.getCell(0).getStringCellValue());
			admin.setEmail(row.getCell(1).getStringCellValue());
			admin.setPassword(row.getCell(2).getStringCellValue());
			adminList.add(admin);
		}
		file.close();
	}
	
	public void update() throws IOException {
		FileInputStream file = new FileInputStream("./src/files/data.xlsx");

		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet admins = csv.getSheet("admins");

		int rows = adminList.size();
		int cols = 3;

		for (int r = 1; r <= rows; r++) {
			XSSFRow row = admins.createRow(r);

			for (int c = 0; c < cols; c++) {
				XSSFCell cell = row.createCell(c);
				String value = "";

				switch (c) {
				case 0:
					value = adminList.get(r - 1).getName();
					break;
				case 1:
					value = adminList.get(r - 1).getEmail();
					break;
				case 2:
					value = adminList.get(r - 1).getPassword();
					break;
				}
				cell.setCellValue(value);
				
			}
		}

		FileOutputStream outFile = new FileOutputStream("./src/files/data.xlsx");
		csv.write(outFile);

		outFile.close();
	}
	
	public boolean removeAdmin(String adminName) throws Exception {
		load(); // load data

		Admin delAdmin = null;
		for (Admin a : adminList) {
			if (a.getName().equals(adminName)) {
				delAdmin = a;
			}
		}
		if(delAdmin == null) {
			return false;
		}
		adminList.remove(delAdmin);// delete from userList

		update();// update with new userList

		// removing last line from excel;
		FileInputStream file = new FileInputStream("./src/files/data.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet admins = csv.getSheet("admins");

		int rows = 0;

		Iterator<Row> iterator = admins.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			XSSFRow row = (XSSFRow) iterator.next();

			if (row.getCell(0).getStringCellValue().equals("") && row.getCell(1).getStringCellValue().equals("")) {
				rows = i - 1;
				break;
			}
			i++;
		}
		rows = i - 1;

		removeRow(admins, rows);

		FileOutputStream outFile = new FileOutputStream("./src/files/data.xlsx");
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
	
	public boolean addAdmin(String name, String email, String pass) throws Exception {
		MaintainEmployee emp = new MaintainEmployee();
		emp.load();
		load();

		for (Employee e : emp.employeeList) {
			if (e.getName().equals(name) && e.getEmail().equals(email)) {
				Admin newAdmin = new Admin(name, email, pass);
				adminList.add(newAdmin);
				update();
				return true;
			}
		}
		return false;
	}
	
	public boolean login(String email, String password) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Admin ad : adminList) {
			if (email.equals(ad.getEmail()) && password.equals(ad.getPassword())) {
				this.currentAdmin = ad;
				return true;
			}
		}
		return false;
	}
	
	public Admin getAdmin() {
		return currentAdmin;
	}
	
	public static void main(String[] args) throws Exception {
		MaintainAdmin ma = new MaintainAdmin();
		ma.load();
		
		for(Admin a: ma.adminList) {
			System.out.println(a);
		}
	}

}
