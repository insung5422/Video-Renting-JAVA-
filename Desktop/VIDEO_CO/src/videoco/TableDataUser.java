package videoco;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("serial")
public class TableDataUser extends AbstractTableModel{
	private ArrayList<User> userList;
	private String[] headers = {"Name", "Username", "Email", "Password", "Point", "Order-Status", "Order ID"};
	public TableDataUser() throws IOException {
		updateList();
	}
	
	public String getColumnName(int cell) {
		return headers[cell];
	}

	public void updateList() throws IOException {
		userList = new ArrayList<>();

		FileInputStream file = new FileInputStream("./src/files/data.xlsx");

		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet users = csv.getSheet("users");

		int rows = 0;

		Iterator<Row> iterator = users.iterator();
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
			User user = new User();
			XSSFRow row = users.getRow(r);

			user.setName(row.getCell(0).getStringCellValue());
			user.setUsername(row.getCell(1).getStringCellValue());
			user.setEmail(row.getCell(2).getStringCellValue());
			user.setPassword(row.getCell(3).getStringCellValue());
			user.setPoint(row.getCell(4).getStringCellValue());
			user.setOrderstatus(row.getCell(5).getStringCellValue());
			user.setOrderID(row.getCell(6).getStringCellValue());


			userList.add(user);
		}
		file.close();
	}

	@Override
	public int getRowCount() {

		return userList.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public Object getValueAt(int row, int cell) {
		switch(cell) {
		case 0:
			return userList.get(row).getName();
		case 1:
			return userList.get(row).getUsername();
		case 2:
			return userList.get(row).getEmail();
		case 3:
			return userList.get(row).getPassword();
		case 4:
			return userList.get(row).getPoint();
		case 5:
			return userList.get(row).getOrderstatus();
		case 6:
			return userList.get(row).getOrderID();
		}
		return null;
	}
	public void refresh() {
		try {
			updateList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.fireTableDataChanged();
		
	}
}
