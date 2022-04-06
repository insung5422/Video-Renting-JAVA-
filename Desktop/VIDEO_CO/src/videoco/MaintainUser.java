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

public class MaintainUser {
	public ArrayList<User> userList = new ArrayList<User>();
	private User currentUser;

	public void load() throws Exception {
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

	public void update() throws IOException {
		FileInputStream file = new FileInputStream("./src/files/data.xlsx");

		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet users = csv.getSheet("users");

		int rows = userList.size();
		int cols = 7;

		for (int r = 1; r <= rows; r++) {
			XSSFRow row = users.createRow(r);

			for (int c = 0; c < cols; c++) {
				XSSFCell cell = row.createCell(c);
				String value = "";

				switch (c) {
				case 0:
					value = userList.get(r - 1).getName();
					break;
				case 1:
					value = userList.get(r - 1).getUsername();
					break;
				case 2:
					value = userList.get(r - 1).getEmail();
					break;
				case 3:
					value = userList.get(r - 1).getPassword();
					break;
				case 4:
					value = userList.get(r - 1).getPoint();
					break;
				case 5:
					value = userList.get(r - 1).getOrderstatus();
					break;
				case 6:
					value = userList.get(r - 1).getOrderID();
					break;
				}
				cell.setCellValue(value);

			}
		}

		FileOutputStream outFile = new FileOutputStream("./src/files/data.xlsx");
		csv.write(outFile);

		outFile.close();
	}

	public boolean addCus(User newUser) throws Exception {
		load();
		for (User u : userList) {
			if (u.getUsername().equals(newUser.getUsername())||
					u.getEmail().equals(newUser.getEmail())) {
				return false;
			}
		}
		userList.add(newUser);
		update();
		return true;
	}

	public boolean removeCus(String username) throws Exception {
		load(); // load data

		User delUser = null;
		for (User u : userList) {
			if (u.getUsername().equals(username)) {
				delUser = u;
			}
		}
		
		if(delUser == null) {
			return false;
		}
		userList.remove(delUser);// delete from userList

		update();// update with new userList

		// removing last line from excel;
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
		rows = i - 1;

		removeRow(users, rows);

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

	public boolean login(String username, String password) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (User u : userList) {
			if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
				this.currentUser = u;
				return true;
			}
		}
		return false;
	}

	public User getUser() {
		return currentUser;
	}
	
	public void getUserbyUsername(String username) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(User u: userList) {
			if(u.getUsername().equals(username)) {
				this.currentUser = u;
			}
		}
	}
	
	public boolean updateInfo(String username, String name, String email, String pass) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(User u: userList) {
			if(u.getUsername().equals(username)) {
				if(name.equals("") && email.equals("") && pass.equals("")) {
					return false;
				}
				if(!name.equals("")) {
					u.setName(name);
				}
				if(!email.equals("")) {
					u.setEmail(email);
				}
				if(!pass.equals("")) {
					u.setPassword(pass);
				}
			}
		}
		
		try {
			update();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean updateInfo(String username, String newUsername, String email, String pass,
			String name, String oStatus, String oID) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boolean check = false;
		for(User u: userList) {
			if(u.getUsername().equals(username)) {
				check = true;
				if(name.equals("") && email.equals("") && pass.equals("") &&
						newUsername.equals("") && oStatus.equals("") && oID.equals("")) {
					return false;
				}
				if(!name.equals("")) {
					u.setName(name);
				}
				if(!email.equals("")) {
					u.setEmail(email);
				}
				if(!pass.equals("")) {
					u.setPassword(pass);
				}
				if(!newUsername.equals("")) {
					u.setUsername(newUsername);
				}
				if(!oStatus.equals("")) {
					u.setOrderstatus(oStatus);
				}
				if(!oID.equals("")) {
					u.setOrderID(oID);
				}
			}
		}
		if(check == false) {
			return false;
		}
		
		try {
			update();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void decreasePoint(String username) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(User u: userList) {
			if(u.getUsername().equals(username)) {
				int newPoint = (Integer.valueOf(u.getPoint()) - 10);
				u.setPoint(String.valueOf(newPoint));
			}
		}
		try {
			update();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addPoint(String username, int num) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(User u: userList) {
			if(u.getUsername().equals(username)) {
				int newPoint = (Integer.valueOf(u.getPoint()) + num);
				u.setPoint(String.valueOf(newPoint));
			}
		}
		try {
			update();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	public static void main(String[] args) throws Exception {
		MaintainUser maintain = new MaintainUser();

		 //User newUser = new User("testing", "newCus", "testing@yorku.ca", "0000", "0",
		 //"null", "null");
		maintain.load();
		// maintain.userList.add(newUser);
		//maintain.updateInfo("jimmy1", "", "", "");

		 //maintain.addCus(newUser);
		//maintain.removeCus("daniel3");

		//for (User u : maintain.userList) {
			//System.out.println(u.toString());
		//}
		
		//maintain.updateName("jimmy1", "Jimmy");
		//System.out.println(maintain.userList.size());
		//maintain.updateEmail("jimmy1", "jimmy1@yorku.ca");
		//System.out.println(maintain.userList.size());
		//maintain.updatePass("jimmy1", "J1234567");
		//maintain.update();
		
		
		
		for (User u : maintain.userList) {
			System.out.println(u.toString());
		}

	}
}
