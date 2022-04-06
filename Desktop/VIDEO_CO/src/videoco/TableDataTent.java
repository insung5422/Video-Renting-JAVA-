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
public class TableDataTent extends AbstractTableModel{

	private ArrayList<Movie> movieList;
	private String[] headers = {"Title", "Category", "Stock", "Actors", "Directors", "Release-Date", "Description"};
	public TableDataTent() throws IOException {
		updateList();
	}
	
	public String getColumnName(int cell) {
		return headers[cell];
	}

	public void updateList() throws IOException {
		movieList = new ArrayList<>();

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

	@Override
	public int getRowCount() {

		return movieList.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public Object getValueAt(int row, int cell) {
		switch(cell) {
		case 0:
			return movieList.get(row).getTitle();
		case 1:
			return movieList.get(row).getCategory();
		case 2:
			return movieList.get(row).getStock();
		case 3:
			return movieList.get(row).getActor();
		case 4:
			return movieList.get(row).getDirector();
		case 5:
			return movieList.get(row).getRdate();
		case 6:
			return movieList.get(row).getDes();
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
