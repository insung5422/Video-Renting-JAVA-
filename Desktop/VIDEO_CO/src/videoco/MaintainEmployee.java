package videoco;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MaintainEmployee {
	public ArrayList<Employee> employeeList = new ArrayList<Employee>();

	public void load() throws Exception {
		FileInputStream file = new FileInputStream("./src/files/data.xlsx");

		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet employees = csv.getSheet("employees");

		int rows = 0;

		Iterator<Row> iterator = employees.iterator();
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
			Employee employee = new Employee();
			XSSFRow row = employees.getRow(r);

			employee.setName(row.getCell(0).getStringCellValue());
			employee.setEmail(row.getCell(1).getStringCellValue());
			employeeList.add(employee);
		}
		file.close();
	}
	
	public static void main(String[] args) throws Exception {
		MaintainEmployee ma = new MaintainEmployee();
		ma.load();
		
		for(Employee e : ma.employeeList) {
			System.out.println(e);
		}
	}
	

}
