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

public class MaintainTentative {
	public ArrayList<Movie> movieList = new ArrayList<Movie>();

	public void load() throws Exception {
		FileInputStream file = new FileInputStream("./src/files/orders.xlsx");

		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet movies = csv.getSheet("tentative");

		int rows = 0;

		Iterator<Row> iterator = movies.iterator();
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
			Movie movie = new Movie();
			XSSFRow row = movies.getRow(r);

			movie.setTitle(row.getCell(0).getStringCellValue());
			movie.setCategory(row.getCell(1).getStringCellValue());
			movie.setStock(row.getCell(2).getStringCellValue());
			movie.setActor(row.getCell(3).getStringCellValue());
			movie.setDirector(row.getCell(4).getStringCellValue());
			movie.setRdate(row.getCell(5).getStringCellValue());
			movie.setDes(row.getCell(6).getStringCellValue());
			
			
			movieList.add(movie);
		}
		file.close();
	}
	
	public void update() throws IOException {
		FileInputStream file = new FileInputStream("./src/files/orders.xlsx");

		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet movies = csv.getSheet("tentative");

		int rows = movieList.size();
		int cols = 7;

		for (int r = 1; r <= rows; r++) {
			XSSFRow row = movies.createRow(r);

			for (int c = 0; c < cols; c++) {
				XSSFCell cell = row.createCell(c);
				String value = "";

				switch (c) {
				case 0:
					value = movieList.get(r - 1).getTitle();
					break;
				case 1:
					value = movieList.get(r - 1).getCategory();
					break;
				case 2:
					value = movieList.get(r - 1).getStock();
					break;
				case 3:
					value = movieList.get(r - 1).getActor();
					break;
				case 4:
					value = movieList.get(r - 1).getDirector();
					break;
				case 5:
					value = movieList.get(r - 1).getRdate();
					break;
				case 6:
					value = movieList.get(r - 1).getDes();
					break;
				}
				cell.setCellValue(value);

			}
		}

		FileOutputStream outFile = new FileOutputStream("./src/files/orders.xlsx");
		csv.write(outFile);

		outFile.close();
	}

	public boolean addMovie(Movie newMovie) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Movie m : movieList) {
			if (m.getTitle().equals(newMovie.getTitle())) {
				return false;
			}
		}
		movieList.add(newMovie);
		try {
			update();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean removeMovie(String movieTitle) throws IOException {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		} // load data

		Movie delMovie = null;
		for (Movie m : movieList) {
			if (m.getTitle().equals(movieTitle)) {
				delMovie = m;
			}
		}
		if(delMovie == null) {
			return false;
		}
		movieList.remove(delMovie);// delete from userList

		try {
			update();
		} catch (IOException e) {
			e.printStackTrace();
		}// update with new userList

		// removing last line from excel;
		FileInputStream file = new FileInputStream("./src/files/orders.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet users = csv.getSheet("tentative");

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
			rows = i - 1;
		}

		removeRow(users, rows);

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
	
	
	public boolean searchMovie(String title) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Movie m : movieList) {
			if(m.getTitle().equals(title)) {
				return true;
			}
		}
		return false;
	}
	
	public void clean(int num) throws IOException {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		} // load data

		movieList.clear();// delete from userList

		try {
			update();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int ii = 0;
		while(ii < num) {
			FileInputStream file = new FileInputStream("./src/files/orders.xlsx");
			@SuppressWarnings("resource")
			XSSFWorkbook csv = new XSSFWorkbook(file);
			XSSFSheet users = csv.getSheet("tentative");

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
				rows = i - 1;
			}

			removeRow(users, rows);

			FileOutputStream outFile = new FileOutputStream("./src/files/orders.xlsx");
			csv.write(outFile);
			outFile.close();
			ii++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		MaintainTentative mo = new MaintainTentative();
		//mo.load();
		//Movie mm = new Movie("test", "aaaction", "3", "idk", "se", "2018", "hola");
		//mo.addMovie(mm);
		
		//System.out.println(mo.updateInfo("", "Free guy1", "Ryan Reynolds1", "Shawn Levy1", "20211", "FreeGuy description1"));
		
		mo.removeMovie("Free Guy");
		//mo.clean(2);
		for (Movie m : mo.movieList) {
			System.out.println(m.toString());
		}
	}
}
