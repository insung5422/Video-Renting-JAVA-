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

public class MaintainMovie {
	public ArrayList<Movie> movieList = new ArrayList<Movie>();
	private Movie currentMovie;

	public void load() throws Exception {
		FileInputStream file = new FileInputStream("./src/files/data.xlsx");

		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet movies = csv.getSheet("movies");

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
		FileInputStream file = new FileInputStream("./src/files/data.xlsx");

		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet movies = csv.getSheet("movies");

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

		FileOutputStream outFile = new FileOutputStream("./src/files/data.xlsx");
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
		FileInputStream file = new FileInputStream("./src/files/data.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook csv = new XSSFWorkbook(file);
		XSSFSheet users = csv.getSheet("movies");

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
	
	public boolean updateInfo(String movieTitle, String title, String actor, String director, String rDate, String des) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(movieTitle.equals("")) {
			return false;
		}
		String checker = "";
		for(Movie m: movieList) {
			if(m.getTitle().equals(movieTitle)) {
				checker = "check";
				if(title.equals("") && actor.equals("") && director.equals("") && rDate.equals("") && des.equals("")) {
					return false;
				}
				if(!title.equals("")) {
					m.setTitle(title);
				}
				if(!actor.equals("")) {
					m.setActor(actor);
				}
				if(!director.equals("")) {
					m.setDirector(director);
				}
				if(!rDate.equals("")) {
					m.setRdate(rDate);
				}
				if(!des.equals("")) {
					m.setDes(des);
				}
			}
		}
		if(checker.equals("")) {
			return false;
		}
		
		try {
			update();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
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
	
	public Movie getMovie() {
		return currentMovie;
	}
	
	public void setMoviebyTitle(String title) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(Movie m: movieList) {
			if(m.getTitle().equals(title)) {
				this.currentMovie = m;
			}
		}
	}
	
	public boolean movieInstock(String title) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Movie m: movieList) {
			if(m.getTitle().equals(title)) {
				if(m.getStock().equals("0")) {
					return false;
				}else {
					return true;
				}
			}
		}
		return false;		
	}
	
	public void decreaseStock(String title) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Movie m: movieList) {
			if(m.getTitle().equals(title)) {
				int stock = Integer.valueOf(m.getStock());
				stock -= 1;
				m.setStock(String.valueOf(stock));
			}
		}
		try {
			update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void increaseStock(String title) {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Movie m: movieList) {
			if(m.getTitle().equals(title)) {
				int stock = Integer.valueOf(m.getStock());
				stock += 1;
				m.setStock(String.valueOf(stock));
			}
		}
		try {
			update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		MaintainMovie ma = new MaintainMovie();
		ma.load();
		
		for(Movie e : ma.movieList) {
			System.out.println(e);
		}
	}

}
