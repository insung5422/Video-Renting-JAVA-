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
public class TableDataOrder extends AbstractTableModel{
	private ArrayList<Order> orderList;
	private String[] headers = {"Username", "Movie"};
	public TableDataOrder() throws IOException {
		updateList();
	}
	
	public String getColumnName(int cell) {
		return headers[cell];
	}
	
	public void updateList() throws IOException {
		orderList = new ArrayList<>();
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
			orderList.add(order);
		}
		file.close();;
	}
	
	@Override
	public int getRowCount() {

		return orderList.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int row, int cell) {
		switch(cell) {
		case 0:
			return orderList.get(row).getName();
		case 1:
			return orderList.get(row).getMovie();
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
