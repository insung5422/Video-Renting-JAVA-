package videoco;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.JComboBox;



public class Person {
	public User currentUser;
	public Admin currentAdmin;

	private JFrame frame;
	private JPanel currentPanel;
	private JTextField loginUser;
	private JPasswordField loginPass;
	private JTextField regiName;
	private JTextField regiUser;
	private JTextField regiPass;
	private JTextField regiEmail;
	private JTextField adminLogin_email;
	private JPasswordField adminLogin_password;
	private JTextField profile_newName;
	private JTextField profile_newEmail;
	private JTextField profile_newPass;
	private JTextField addMtitle;
	private JTextField addMcategory;
	private JTextField addMstock;
	private JTextField addMactor;
	private JTextField addMdirector;
	private JTextField addMrdate;
	private JTextField removeMtitle;
	private JTextField updateMtitle;
	private JTextField updateMdirector;
	private JTextField updateMrdate;
	private JTextField updateMactor;
	private JTextField updateMtitleOriginal;
	private JTextField mNameofEmp;
	private JTextField mEmailofEmp;
	private JTextField mDelNameofEmp;
	private JTextField mPassofEmp;
	private JTable table;
	private JTextField searchTitleInput;
	private String[] types = {"Crime", "Action", "Comedy", "Drama"};
	private JTable tableTent;
	private JTextField movieWantRemove;
	private JTextField customerPayment_address;
	private JTextField customerPayment_payment;
	private JTable tableUser;
	private JTextField manageOriginalUsername;
	private JTextField manageCName;
	private JTextField manageCUsername;
	private JTextField manageCOstatus;
	private JTextField manageCEmail;
	private JTextField manageCOID;
	private JTextField manageCPass;
	private JTextField manageDelUser;
	private JTextField viewOrder_title;
	private JTable table_order;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Person window = new Person();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Person() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		TableData td = new TableData();
		TableDataTent tdt = new TableDataTent();
		TableDataUser tdu = new TableDataUser();
		TableDataOrder tdo = new TableDataOrder();
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 750);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ImagePanel loginPanel = new ImagePanel(new ImageIcon("./src/videoco/image.jpg").getImage());
		//ImagePanel loginPanel = new ImagePanel(new ImageIcon("./src/files/image2.jpg").getImage());
		
		
		//String cImage = "./src/videoco/image.jpg";
		//String aImage = "./src/videoco/image.jpg2";
		
		String cImage = "/Users/danielsong/eclipse-workspace/eecs3311-project/src/files/image.jpg";
		String aImage = "/Users/danielsong/eclipse-workspace/eecs3311-project/src/files/image2.jpg";

		//String cImage = "./src/files/image.jpg";
		//String aImage = "./src/files/image2.jpg";


		ImagePanel welcomePanel = new ImagePanel(new ImageIcon(cImage).getImage());
		currentPanel = welcomePanel;

		ImagePanel customerViewOrderPanel = new ImagePanel(new ImageIcon(cImage).getImage());
		frame.getContentPane().add(customerViewOrderPanel);
		customerViewOrderPanel.setVisible(false);
		customerViewOrderPanel.setLayout(null);

		ImagePanel customerMainPanel = new ImagePanel(new ImageIcon(cImage).getImage());
		frame.getContentPane().add(customerMainPanel);
		customerMainPanel.setVisible(false);
		customerMainPanel.setLayout(null);


		JButton btnNewButton_4_2_1_3_1_1 = new JButton("Back to Main");
		btnNewButton_4_2_1_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				customerMainPanel.setVisible(true);
				currentPanel = customerMainPanel;
			}
		});
		btnNewButton_4_2_1_3_1_1.setBounds(21, 34, 195, 29);
		customerViewOrderPanel.add(btnNewButton_4_2_1_3_1_1);

		JLabel lblNewLabel_7_1 = new JLabel("View Order");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		lblNewLabel_7_1.setBounds(262, 60, 331, 66);
		customerViewOrderPanel.add(lblNewLabel_7_1);

		JPanel orderTable = new JPanel();
		orderTable.setBounds(173, 372, 488, 175);
		customerViewOrderPanel.add(orderTable);

		JLabel customerOrder_username = new JLabel("U");
		customerOrder_username.setHorizontalAlignment(SwingConstants.LEFT);
		customerOrder_username.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		customerOrder_username.setBounds(438, 118, 98, 42);
		customerViewOrderPanel.add(customerOrder_username);

		table_order = new JTable(tdo);
		table_order.setBounds(6, 6, 476, 163);
		table_order.setRowHeight(30);
		table_order.setFont(new Font("Sansserif", Font.PLAIN, 15));
		table_order.setPreferredScrollableViewportSize(new Dimension(488, 166));
		orderTable.add(new JScrollPane(table_order));
		orderTable.setOpaque(false);

		JTableHeader header_order = table_order.getTableHeader();
		header_order.setBackground(new Color(92, 179, 255));
		header_order.setForeground(new Color(255, 255, 255));
		header_order.setFont(new Font("Sansserif", Font.BOLD, 18));


		JLabel customerPayment_usernamevar_1 = new JLabel("User: ");
		customerPayment_usernamevar_1.setHorizontalAlignment(SwingConstants.CENTER);
		customerPayment_usernamevar_1.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		customerPayment_usernamevar_1.setBounds(345, 118, 113, 42);
		customerViewOrderPanel.add(customerPayment_usernamevar_1);


		JLabel lblNewLabel_17 = new JLabel("Type Movie Title Want to Cancel:");
		lblNewLabel_17.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_17.setBounds(286, 231, 292, 29);
		customerViewOrderPanel.add(lblNewLabel_17);

		viewOrder_title = new JTextField();
		viewOrder_title.setBounds(266, 259, 300, 29);
		customerViewOrderPanel.add(viewOrder_title);
		viewOrder_title.setColumns(10);

		JButton btnNewButton_13 = new JButton("Remove Order");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String movie = viewOrder_title.getText();
				MaintainOrder mr = new MaintainOrder();

				try {
					if(mr.removeOrder(movie)) {
						JOptionPane.showMessageDialog(null, "Movie Deleted");
						tdo.refresh();

						MaintainMovie mm = new MaintainMovie();
						mm.increaseStock(movie);
						td.refresh();
					}else {
						JOptionPane.showMessageDialog(null, "Movie Does not Exist in Order");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		btnNewButton_13.setBounds(578, 260, 117, 29);
		customerViewOrderPanel.add(btnNewButton_13);

		JLabel lblNewLabel_18 = new JLabel("My Orders: ");
		lblNewLabel_18.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_18.setBounds(180, 348, 117, 29);
		customerViewOrderPanel.add(lblNewLabel_18);


		ImagePanel customerPaymentPanel = new ImagePanel(new ImageIcon(cImage).getImage());
		frame.getContentPane().add(customerPaymentPanel);
		customerPaymentPanel.setVisible(false);
		customerPaymentPanel.setLayout(null);

		ImagePanel customerOrderPanel = new ImagePanel(new ImageIcon(cImage).getImage());
		frame.getContentPane().add(customerOrderPanel);
		customerOrderPanel.setVisible(false);
		customerOrderPanel.setLayout(null);


		JButton btnNewButton_4_2_1_3_1 = new JButton("Back to View Order");
		btnNewButton_4_2_1_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				customerOrderPanel.setVisible(true);
				currentPanel = customerOrderPanel;
			}
		});
		btnNewButton_4_2_1_3_1.setBounds(21, 34, 195, 29);
		customerPaymentPanel.add(btnNewButton_4_2_1_3_1);

		JLabel customerPayment_usernamevar = new JLabel("User: ");
		customerPayment_usernamevar.setHorizontalAlignment(SwingConstants.CENTER);
		customerPayment_usernamevar.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		customerPayment_usernamevar.setBounds(294, 105, 113, 42);
		customerPaymentPanel.add(customerPayment_usernamevar);

		JLabel customerPayment_pointVar = new JLabel("Loyalty Points:");
		customerPayment_pointVar.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		customerPayment_pointVar.setBounds(321, 234, 136, 40);
		customerPaymentPanel.add(customerPayment_pointVar);

		JLabel customerPayment_point = new JLabel("p");
		customerPayment_point.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		customerPayment_point.setBounds(458, 234, 75, 40);
		customerPaymentPanel.add(customerPayment_point);

		JLabel customerPayment_username = new JLabel("User: ");
		customerPayment_username.setHorizontalAlignment(SwingConstants.CENTER);
		customerPayment_username.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		customerPayment_username.setBounds(344, 105, 172, 42);
		customerPaymentPanel.add(customerPayment_username);

		JLabel customerPayment_movies = new JLabel("M");
		customerPayment_movies.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		customerPayment_movies.setBounds(478, 182, 75, 40);
		customerPaymentPanel.add(customerPayment_movies);


		JButton btnNewButton_11 = new JButton("Pay With Points");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = customerPayment_username.getText();
				String point = customerPayment_point.getText();
				String movie = customerPayment_movies.getText();


				if(Integer.valueOf(point) / Integer.valueOf(movie) >= 10) {
					MaintainUser u = new MaintainUser();
					u.decreasePoint(username);
					MaintainTentative mt = new MaintainTentative();
					MaintainOrder mo = new MaintainOrder();
					try {
						mt.load();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					Order or = null;
					for(Movie m : mt.movieList) {
						or = new Order(username, m.getTitle());
						mo.addOrder(or);
						MaintainMovie mm = new MaintainMovie();
						mm.decreaseStock(m.getTitle());
					}
					tdo.refresh();
					td.refresh();

					try {
						mt.clean(Integer.valueOf(movie));
						tdt.refresh();
					} catch (IOException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Used Points for Payment!");

					currentPanel.setVisible(false);
					customerViewOrderPanel.setVisible(true);
					currentPanel = customerViewOrderPanel;
				}else {
					JOptionPane.showMessageDialog(null, "Not Enough Point");	
				}
			}
		});
		btnNewButton_11.setBounds(294, 272, 217, 29);
		customerPaymentPanel.add(btnNewButton_11);

		JLabel lblNewLabel_14 = new JLabel("Shipping Address:");
		lblNewLabel_14.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_14.setBounds(60, 322, 182, 42);
		customerPaymentPanel.add(lblNewLabel_14);

		JLabel lblNewLabel_14_1 = new JLabel("Payment:");
		lblNewLabel_14_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_14_1.setBounds(60, 457, 182, 42);
		customerPaymentPanel.add(lblNewLabel_14_1);

		customerPayment_address = new JTextField();
		customerPayment_address.setBounds(56, 363, 592, 47);
		customerPaymentPanel.add(customerPayment_address);
		customerPayment_address.setColumns(10);

		customerPayment_payment = new JTextField();
		customerPayment_payment.setColumns(10);
		customerPayment_payment.setBounds(56, 492, 592, 47);
		customerPaymentPanel.add(customerPayment_payment);



		JButton btnNewButton_11_1 = new JButton("Pay With Card");
		btnNewButton_11_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String add = customerPayment_address.getText();
				String pay = customerPayment_payment.getText();
				String username = customerPayment_username.getText();
				int movie = Integer.valueOf(customerPayment_movies.getText());


				if(add.equals("invalid") || pay.equals("invalid")) {
					JOptionPane.showMessageDialog(null, "Invalid Shipping or payment");
				}else {
					MaintainTentative mt = new MaintainTentative();
					MaintainUser u = new MaintainUser();
					MaintainOrder mo = new MaintainOrder();


					u.addPoint(username, movie);
					try {
						mt.load();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					Order or = null;
					for(Movie m : mt.movieList) {
						or = new Order(username, m.getTitle());
						mo.addOrder(or);
						MaintainMovie mm = new MaintainMovie();
						mm.decreaseStock(m.getTitle());
					}
					tdo.refresh();
					td.refresh();

					try {
						mt.clean(movie);
						tdt.refresh();
					} catch (IOException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Payment Success");
					currentPanel.setVisible(false);
					customerViewOrderPanel.setVisible(true);
					currentPanel = customerViewOrderPanel;
				}
				customerPayment_address.setText("");
				customerPayment_payment.setText("");
			}
		});
		btnNewButton_11_1.setBounds(278, 563, 217, 29);
		customerPaymentPanel.add(btnNewButton_11_1);

		JLabel customerPayment__1 = new JLabel("# of Movie(s) Renting:");
		customerPayment__1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		customerPayment__1.setBounds(283, 182, 233, 40);
		customerPaymentPanel.add(customerPayment__1);

		ImagePanel adminMainPanel = new ImagePanel(new ImageIcon(aImage).getImage());
		frame.getContentPane().add(adminMainPanel);
		adminMainPanel.setVisible(false);
		adminMainPanel.setLayout(null);



		JButton btnNewButton_4_2_1_3 = new JButton("Back to Movie List");
		btnNewButton_4_2_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				customerMainPanel.setVisible(true);
				currentPanel = customerMainPanel;
			}
		});
		btnNewButton_4_2_1_3.setBounds(21, 34, 195, 29);
		customerOrderPanel.add(btnNewButton_4_2_1_3);

		JPanel movieTableTent = new JPanel();
		movieTableTent.setBounds(21, 370, 811, 310);
		customerOrderPanel.add(movieTableTent);


		tableTent = new JTable(tdt);
		tableTent.setBounds(6, 6, 799, 298);
		tableTent.setRowHeight(30);
		tableTent.setPreferredScrollableViewportSize(new Dimension(811, 295));
		movieTableTent.add(new JScrollPane(tableTent));
		movieTableTent.setOpaque(false);

		JLabel customerTentative_username = new JLabel("User: ");
		customerTentative_username.setHorizontalAlignment(SwingConstants.CENTER);
		customerTentative_username.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		customerTentative_username.setBounds(278, 93, 281, 42);
		customerOrderPanel.add(customerTentative_username);

		JLabel lblNewLabel_13 = new JLabel("Type Movie Title Want to Remove: ");
		lblNewLabel_13.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_13.setBounds(60, 329, 306, 29);
		customerOrderPanel.add(lblNewLabel_13);

		movieWantRemove = new JTextField();
		movieWantRemove.setBounds(336, 331, 306, 29);
		customerOrderPanel.add(movieWantRemove);
		movieWantRemove.setColumns(10);

		JButton btnNewButton_10 = new JButton("Remove");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String movie = movieWantRemove.getText();
				MaintainTentative mt = new MaintainTentative();
				try {
					if(mt.removeMovie(movie)) {
						tdt.refresh();
						movieWantRemove.setText("");
						int num = Integer.valueOf(customerPayment_movies.getText());
						customerPayment_movies.setText(String.valueOf(num-1));
					}else {
						JOptionPane.showMessageDialog(null, "Movie Does Not Exist");	
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_10.setBounds(649, 331, 117, 29);
		customerOrderPanel.add(btnNewButton_10);

		JButton btnNewButton_4_2_1_3_2 = new JButton("Go to Payment");
		btnNewButton_4_2_1_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				currentPanel.setVisible(false);
				customerPaymentPanel.setVisible(true);
				currentPanel = customerPaymentPanel;
			}
		});
		btnNewButton_4_2_1_3_2.setBounds(623, 34, 195, 29);
		customerOrderPanel.add(btnNewButton_4_2_1_3_2);

		JTableHeader headerTen = tableTent.getTableHeader();

		headerTen.setBackground(new Color(92, 179, 255));
		headerTen.setForeground(new Color(255, 255, 255));
		headerTen.setFont(new Font("Sansserif", Font.BOLD, 15));



		ImagePanel editProfilePanel = new ImagePanel(new ImageIcon(cImage).getImage());
		frame.getContentPane().add(editProfilePanel);
		editProfilePanel.setVisible(false);
		editProfilePanel.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("Welcome");
		lblNewLabel_7.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		lblNewLabel_7.setBounds(332, 62, 198, 66);
		customerMainPanel.add(lblNewLabel_7);

		JButton btnNewButton_6 = new JButton("EDIT PROFILE");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				editProfilePanel.setVisible(true);
				currentPanel = editProfilePanel;
			}
		});
		btnNewButton_6.setBounds(696, 34, 117, 29);
		customerMainPanel.add(btnNewButton_6);

		JButton btnNewButton_4_2_1 = new JButton("Sign Out");
		btnNewButton_4_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				welcomePanel.setVisible(true);
				currentPanel = welcomePanel;

			}
		});
		btnNewButton_4_2_1.setBounds(21, 34, 117, 29);
		customerMainPanel.add(btnNewButton_4_2_1);


		JLabel customerMain_username = new JLabel("User: ");
		customerMain_username.setHorizontalAlignment(SwingConstants.CENTER);
		customerMain_username.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		customerMain_username.setBounds(279, 126, 281, 42);
		customerMainPanel.add(customerMain_username);

		JPanel movieTable = new JPanel();
		movieTable.setBounds(22, 464, 811, 310);
		customerMainPanel.add(movieTable);


		table = new JTable(td);
		table.setBounds(6, 6, 799, 298);
		table.setRowHeight(25);
		table.setPreferredScrollableViewportSize(new Dimension(799, 280));
		movieTable.add(new JScrollPane(table));
		table.setOpaque(false);

		JLabel lblNewLabel_12 = new JLabel("Movies:");
		lblNewLabel_12.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_12.setBounds(24, 447, 94, 16);
		customerMainPanel.add(lblNewLabel_12);

		JLabel lblNewLabel_12_1 = new JLabel("Search By Title:");
		lblNewLabel_12_1.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel_12_1.setBounds(81, 237, 254, 36);
		customerMainPanel.add(lblNewLabel_12_1);

		JLabel lblNewLabel_12_1_1 = new JLabel("Search By Category:");
		lblNewLabel_12_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel_12_1_1.setBounds(54, 326, 254, 36);
		customerMainPanel.add(lblNewLabel_12_1_1);

		searchTitleInput = new JTextField();
		searchTitleInput.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		searchTitleInput.setBounds(267, 241, 217, 36);
		customerMainPanel.add(searchTitleInput);
		searchTitleInput.setColumns(10);

		JButton Search_titleBttn = new JButton("Search");
		Search_titleBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String search = searchTitleInput.getText();
				MaintainMovie m = new MaintainMovie();
				if(!m.searchMovie(search)) {
					JOptionPane.showMessageDialog(null, "No Matching Movie");
					return;
				}

				TableRowSorter<AbstractTableModel> trs = new TableRowSorter<>(td);
				table.setRowSorter(trs);
				trs.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		Search_titleBttn.setBounds(496, 246, 117, 29);
		customerMainPanel.add(Search_titleBttn);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBox = new JComboBox(types);
		comboBox.setBounds(309, 336, 175, 26);
		customerMainPanel.add(comboBox);

		JButton Search_typeBttn = new JButton("Search");
		Search_typeBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search =  comboBox.getSelectedItem().toString();
				TableRowSorter<AbstractTableModel> trs = new TableRowSorter<>(td);
				table.setRowSorter(trs);
				trs.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		Search_typeBttn.setBounds(496, 335, 117, 29);
		customerMainPanel.add(Search_typeBttn);

		JButton btnNewButton_10_2 = new JButton("View All");
		btnNewButton_10_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = "";
				TableRowSorter<AbstractTableModel> trs = new TableRowSorter<>(td);
				table.setRowSorter(trs);
				trs.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		btnNewButton_10_2.setBounds(496, 405, 117, 29);
		customerMainPanel.add(btnNewButton_10_2);

		JButton btnNewButton_4_2_1_2 = new JButton("View Order");
		btnNewButton_4_2_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				customerOrderPanel.setVisible(true);
				currentPanel = customerOrderPanel;
			}
		});
		btnNewButton_4_2_1_2.setBounds(696, 110, 117, 29);
		customerMainPanel.add(btnNewButton_4_2_1_2);

		JButton btnNewButton_4_2_1_2_1 = new JButton("Add Movie");
		btnNewButton_4_2_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String search = searchTitleInput.getText();
				MaintainMovie mm = new MaintainMovie();
				if(!mm.movieInstock(search)) {
					JOptionPane.showMessageDialog(null, "Sorry Movie Out of Stock");
					return;
				}

				mm.setMoviebyTitle(search);
				Movie movie = mm.getMovie();

				MaintainTentative mt = new MaintainTentative();
				if(mt.addMovie(movie)) {
					JOptionPane.showMessageDialog(null, "Movie Added");	
					tdt.refresh();
					int num = mt.movieList.size();
					customerPayment_movies.setText(String.valueOf(num));
					currentPanel.setVisible(false);
					customerOrderPanel.setVisible(true);
					currentPanel = customerOrderPanel;

					searchTitleInput.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "Movie Already Exist");	
				}
			}
		});
		btnNewButton_4_2_1_2_1.setBounds(625, 246, 117, 29);
		customerMainPanel.add(btnNewButton_4_2_1_2_1);

		JTableHeader header = table.getTableHeader();

		header.setBackground(new Color(92, 179, 255));
		header.setForeground(new Color(255, 255, 255));
		header.setFont(new Font("Sansserif", Font.BOLD, 15));


		ImagePanel adminManageAdminPanel = new ImagePanel(new ImageIcon(aImage).getImage());
		frame.getContentPane().add(adminManageAdminPanel);
		adminManageAdminPanel.setVisible(false);
		adminManageAdminPanel.setLayout(null);

		JButton btnNewButton_4_2_1_1_3 = new JButton("Back to Main");
		btnNewButton_4_2_1_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				adminMainPanel.setVisible(true);
				currentPanel = adminMainPanel;
			}
		});
		btnNewButton_4_2_1_1_3.setBounds(21, 34, 117, 29);
		adminManageAdminPanel.add(btnNewButton_4_2_1_1_3);

		JLabel adminMain_admin_1_1 = new JLabel("Manage Admins");
		adminMain_admin_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		adminMain_admin_1_1.setForeground(Color.WHITE);
		adminMain_admin_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		adminMain_admin_1_1.setBounds(281, 59, 281, 42);
		adminManageAdminPanel.add(adminMain_admin_1_1);

		JLabel adminMain_admin_1_2 = new JLabel("Add New Admin:");
		adminMain_admin_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		adminMain_admin_1_2.setForeground(Color.WHITE);
		adminMain_admin_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		adminMain_admin_1_2.setBounds(43, 135, 281, 42);
		adminManageAdminPanel.add(adminMain_admin_1_2);

		mNameofEmp = new JTextField();
		mNameofEmp.setBounds(104, 205, 200, 26);
		adminManageAdminPanel.add(mNameofEmp);
		mNameofEmp.setColumns(10);

		JLabel adminMain_admin_1_2_1 = new JLabel("Name of Employee:");
		adminMain_admin_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		adminMain_admin_1_2_1.setForeground(Color.WHITE);
		adminMain_admin_1_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		adminMain_admin_1_2_1.setBounds(112, 175, 164, 42);
		adminManageAdminPanel.add(adminMain_admin_1_2_1);

		JLabel adminMain_admin_1_2_1_1 = new JLabel("Email of Employee:");
		adminMain_admin_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		adminMain_admin_1_2_1_1.setForeground(Color.WHITE);
		adminMain_admin_1_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		adminMain_admin_1_2_1_1.setBounds(364, 175, 164, 42);
		adminManageAdminPanel.add(adminMain_admin_1_2_1_1);

		mEmailofEmp = new JTextField();
		mEmailofEmp.setColumns(10);
		mEmailofEmp.setBounds(345, 205, 200, 26);
		adminManageAdminPanel.add(mEmailofEmp);

		JButton btnNewButton_9 = new JButton("Add Admin");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = mNameofEmp.getText();
				String email = mEmailofEmp.getText();
				String pass = mPassofEmp.getText();


				MaintainAdmin ma = new MaintainAdmin();
				try {
					if(ma.addAdmin(name, email, pass)) {
						JOptionPane.showMessageDialog(null, "Admin Added");	
					}else {
						JOptionPane.showMessageDialog(null, "Employee Does not Exist");	
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				mNameofEmp.setText("");
				mEmailofEmp.setText("");
				mPassofEmp.setText("");
			}
		});
		btnNewButton_9.setBounds(574, 237, 158, 29);
		adminManageAdminPanel.add(btnNewButton_9);

		JLabel adminMain_admin_1_2_2 = new JLabel("Remove Existing Admin:");
		adminMain_admin_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		adminMain_admin_1_2_2.setForeground(Color.WHITE);
		adminMain_admin_1_2_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		adminMain_admin_1_2_2.setBounds(58, 369, 281, 42);
		adminManageAdminPanel.add(adminMain_admin_1_2_2);

		JLabel adminMain_admin_1_2_1_2 = new JLabel("Name of Employee:");
		adminMain_admin_1_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		adminMain_admin_1_2_1_2.setForeground(Color.WHITE);
		adminMain_admin_1_2_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		adminMain_admin_1_2_1_2.setBounds(351, 402, 164, 42);
		adminManageAdminPanel.add(adminMain_admin_1_2_1_2);

		mDelNameofEmp = new JTextField();
		mDelNameofEmp.setColumns(10);
		mDelNameofEmp.setBounds(334, 433, 200, 26);
		adminManageAdminPanel.add(mDelNameofEmp);

		JButton btnNewButton_9_1 = new JButton("Remove Admin");
		btnNewButton_9_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String delName = mDelNameofEmp.getText();

				MaintainAdmin ma = new MaintainAdmin();
				try {
					if(ma.removeAdmin(delName)) {
						JOptionPane.showMessageDialog(null, "Admin Removed");
					}else {
						JOptionPane.showMessageDialog(null, "Admin Does not Exist");
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				mDelNameofEmp.setText("");
			}
		});
		btnNewButton_9_1.setBounds(574, 433, 158, 29);
		adminManageAdminPanel.add(btnNewButton_9_1);

		JLabel adminMain_admin_1_2_1_1_1 = new JLabel("New Password Set:");
		adminMain_admin_1_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		adminMain_admin_1_2_1_1_1.setForeground(Color.WHITE);
		adminMain_admin_1_2_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		adminMain_admin_1_2_1_1_1.setBounds(364, 243, 164, 42);
		adminManageAdminPanel.add(adminMain_admin_1_2_1_1_1);

		mPassofEmp = new JTextField();
		mPassofEmp.setColumns(10);
		mPassofEmp.setBounds(345, 274, 200, 26);
		adminManageAdminPanel.add(mPassofEmp);

		ImagePanel adminManageMoviePanel = new ImagePanel(new ImageIcon(aImage).getImage());
		frame.getContentPane().add(adminManageMoviePanel);
		adminManageMoviePanel.setVisible(false);
		adminManageMoviePanel.setLayout(null);

		ImagePanel adminManageCustomerPanel = new ImagePanel(new ImageIcon(aImage).getImage());
		frame.getContentPane().add(adminManageCustomerPanel);
		adminManageCustomerPanel.setVisible(false);
		adminManageCustomerPanel.setLayout(null);

		JButton btnNewButton_4_2_1_1_2 = new JButton("Back to Main");
		btnNewButton_4_2_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				adminMainPanel.setVisible(true);
				currentPanel = adminMainPanel;
			}
		});
		btnNewButton_4_2_1_1_2.setBounds(21, 34, 117, 29);
		adminManageCustomerPanel.add(btnNewButton_4_2_1_1_2);

		JPanel userTable = new JPanel();
		userTable.setBounds(22, 530, 809, 246);
		adminManageCustomerPanel.add(userTable);

		//
		tableUser = new JTable(tdu);
		tableUser.setBounds(6, 6, 797, 234);
		tableUser.setRowHeight(30);
		tableUser.setFont(new Font("Sansserif", Font.PLAIN, 14));
		tableUser.setPreferredScrollableViewportSize(new Dimension(797, 227));
		userTable.add(new JScrollPane(tableUser));
		userTable.setOpaque(false);

		JTableHeader headerUser = tableUser.getTableHeader();
		headerUser.setBackground(new Color(92, 179, 255));
		headerUser.setForeground(new Color(255, 255, 255));
		headerUser.setFont(new Font("Sansserif", Font.BOLD, 15));
		//

		JLabel lblNewLabel_15 = new JLabel("Manage Customer Info");
		lblNewLabel_15.setForeground(Color.WHITE);
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.setFont(new Font("Lucida Grande", Font.PLAIN, 33));
		lblNewLabel_15.setBounds(201, 23, 435, 54);
		adminManageCustomerPanel.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel("Update Customer Info:");
		lblNewLabel_16.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_16.setForeground(Color.WHITE);
		lblNewLabel_16.setBounds(35, 103, 229, 29);
		adminManageCustomerPanel.add(lblNewLabel_16);

		JLabel lblNewLabel_16_1 = new JLabel("Original Username:");
		lblNewLabel_16_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_16_1.setForeground(Color.WHITE);
		lblNewLabel_16_1.setBounds(51, 161, 158, 29);
		adminManageCustomerPanel.add(lblNewLabel_16_1);

		manageOriginalUsername = new JTextField();
		manageOriginalUsername.setBounds(48, 184, 145, 36);
		adminManageCustomerPanel.add(manageOriginalUsername);
		manageOriginalUsername.setColumns(10);

		JLabel lblNewLabel_16_1_1 = new JLabel("Name:");
		lblNewLabel_16_1_1.setForeground(Color.WHITE);
		lblNewLabel_16_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_16_1_1.setBounds(57, 258, 94, 29);
		adminManageCustomerPanel.add(lblNewLabel_16_1_1);

		JLabel lblNewLabel_16_1_2 = new JLabel("Username:");
		lblNewLabel_16_1_2.setForeground(Color.WHITE);
		lblNewLabel_16_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_16_1_2.setBounds(262, 161, 117, 29);
		adminManageCustomerPanel.add(lblNewLabel_16_1_2);

		JLabel lblNewLabel_16_1_3 = new JLabel("Order Status:");
		lblNewLabel_16_1_3.setForeground(Color.WHITE);
		lblNewLabel_16_1_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_16_1_3.setBounds(262, 258, 117, 29);
		adminManageCustomerPanel.add(lblNewLabel_16_1_3);

		JLabel lblNewLabel_16_1_4 = new JLabel("Email:");
		lblNewLabel_16_1_4.setForeground(Color.WHITE);
		lblNewLabel_16_1_4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_16_1_4.setBounds(470, 161, 94, 29);
		adminManageCustomerPanel.add(lblNewLabel_16_1_4);

		JLabel lblNewLabel_16_1_5 = new JLabel("Order ID: ");
		lblNewLabel_16_1_5.setForeground(Color.WHITE);
		lblNewLabel_16_1_5.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_16_1_5.setBounds(470, 258, 94, 29);
		adminManageCustomerPanel.add(lblNewLabel_16_1_5);

		manageCName = new JTextField();
		manageCName.setColumns(10);
		manageCName.setBounds(48, 281, 145, 36);
		adminManageCustomerPanel.add(manageCName);

		manageCUsername = new JTextField();
		manageCUsername.setColumns(10);
		manageCUsername.setBounds(255, 184, 145, 36);
		adminManageCustomerPanel.add(manageCUsername);

		manageCOstatus = new JTextField();
		manageCOstatus.setColumns(10);
		manageCOstatus.setBounds(255, 281, 145, 36);
		adminManageCustomerPanel.add(manageCOstatus);

		manageCEmail = new JTextField();
		manageCEmail.setColumns(10);
		manageCEmail.setBounds(462, 184, 145, 36);
		adminManageCustomerPanel.add(manageCEmail);

		manageCOID = new JTextField();
		manageCOID.setColumns(10);
		manageCOID.setBounds(462, 281, 145, 36);
		adminManageCustomerPanel.add(manageCOID);

		JLabel lblNewLabel_16_1_4_1 = new JLabel("Password:");
		lblNewLabel_16_1_4_1.setForeground(Color.WHITE);
		lblNewLabel_16_1_4_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_16_1_4_1.setBounds(673, 161, 94, 29);
		adminManageCustomerPanel.add(lblNewLabel_16_1_4_1);

		manageCPass = new JTextField();
		manageCPass.setColumns(10);
		manageCPass.setBounds(666, 184, 145, 36);
		adminManageCustomerPanel.add(manageCPass);

		JButton btnNewButton_12 = new JButton("Edit Info");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String oUserName = manageOriginalUsername.getText();
				String userName = manageCUsername.getText();
				String email = manageCEmail.getText();
				String pass = manageCPass.getText();
				String name = manageCName.getText();
				String oStatus = manageCOstatus.getText();
				String OID= manageCOID.getText();

				if(oUserName.equals("")) {
					JOptionPane.showMessageDialog(null, "Original Username Must Required");
					manageOriginalUsername.setText("");
					manageCUsername.setText("");
					manageCEmail.setText("");
					manageCPass.setText("");
					manageCName.setText("");
					manageCOstatus.setText("");
					manageCOID.setText("");
					return;
				}
				MaintainUser user = new MaintainUser();
				if(user.updateInfo(oUserName, userName, email, pass,name, oStatus, OID)) {
					JOptionPane.showMessageDialog(null, "Update Success");
					tdu.refresh();
				}else {
					JOptionPane.showMessageDialog(null, "Input Required");
				}
				manageOriginalUsername.setText("");
				manageCUsername.setText("");
				manageCEmail.setText("");
				manageCPass.setText("");
				manageCName.setText("");
				manageCOstatus.setText("");
				manageCOID.setText("");
			}
		});
		btnNewButton_12.setBounds(666, 285, 117, 29);
		adminManageCustomerPanel.add(btnNewButton_12);

		JLabel lblNewLabel_16_1_6 = new JLabel("Customers:");
		lblNewLabel_16_1_6.setForeground(Color.WHITE);
		lblNewLabel_16_1_6.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_16_1_6.setBounds(21, 501, 158, 29);
		adminManageCustomerPanel.add(lblNewLabel_16_1_6);

		JLabel lblNewLabel_16_1_7 = new JLabel("Enter Customer's Username to Delete:");
		lblNewLabel_16_1_7.setForeground(Color.WHITE);
		lblNewLabel_16_1_7.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_16_1_7.setBounds(51, 405, 349, 29);
		adminManageCustomerPanel.add(lblNewLabel_16_1_7);

		manageDelUser = new JTextField();
		manageDelUser.setColumns(10);
		manageDelUser.setBounds(336, 406, 271, 29);
		adminManageCustomerPanel.add(manageDelUser);

		JButton btnNewButton_12_1 = new JButton("Remove Customer");
		btnNewButton_12_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = manageDelUser.getText();
				MaintainUser mu = new MaintainUser();

				try {
					if(mu.removeCus(user)) {
						manageDelUser.setText("");
						tdu.refresh();
						JOptionPane.showMessageDialog(null, "User Deleted");	
					}else {
						JOptionPane.showMessageDialog(null, "User Does not Exist");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_12_1.setBounds(645, 407, 145, 29);
		adminManageCustomerPanel.add(btnNewButton_12_1);


		ImagePanel adminLoginPanel = new ImagePanel(new ImageIcon(aImage).getImage());
		frame.getContentPane().add(adminLoginPanel, BorderLayout.NORTH);
		adminLoginPanel.setVisible(false);
		adminLoginPanel.setLayout(null);

		JButton btnNewButton_4_2_1_1_1 = new JButton("Back to Main");
		btnNewButton_4_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				adminMainPanel.setVisible(true);
				currentPanel = adminMainPanel;
			}
		});
		btnNewButton_4_2_1_1_1.setBounds(21, 34, 117, 29);
		adminManageMoviePanel.add(btnNewButton_4_2_1_1_1);

		JLabel adminMain_admin_1 = new JLabel("Manage Movie");
		adminMain_admin_1.setHorizontalAlignment(SwingConstants.CENTER);
		adminMain_admin_1.setForeground(Color.WHITE);
		adminMain_admin_1.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		adminMain_admin_1.setBounds(276, 22, 281, 42);
		adminManageMoviePanel.add(adminMain_admin_1);

		JLabel lblNewLabel_10_1_1 = new JLabel("Enter Title of the Movie to Delete: ");
		lblNewLabel_10_1_1.setForeground(Color.WHITE);
		lblNewLabel_10_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_10_1_1.setBounds(113, 346, 320, 36);
		adminManageMoviePanel.add(lblNewLabel_10_1_1);

		JLabel lblNewLabel_11 = new JLabel("Title:");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setForeground(Color.WHITE);
		lblNewLabel_11.setBounds(123, 98, 117, 16);
		adminManageMoviePanel.add(lblNewLabel_11);

		addMtitle = new JTextField();
		addMtitle.setBounds(118, 121, 130, 26);
		adminManageMoviePanel.add(addMtitle);
		addMtitle.setColumns(10);

		JLabel lblNewLabel_11_1 = new JLabel("Category:");
		lblNewLabel_11_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11_1.setForeground(Color.WHITE);
		lblNewLabel_11_1.setBounds(153, 159, 61, 16);
		adminManageMoviePanel.add(lblNewLabel_11_1);

		addMcategory = new JTextField();
		addMcategory.setColumns(10);
		addMcategory.setBounds(118, 187, 130, 26);
		adminManageMoviePanel.add(addMcategory);

		JLabel lblNewLabel_11_1_1 = new JLabel("Stock:");
		lblNewLabel_11_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11_1_1.setForeground(Color.WHITE);
		lblNewLabel_11_1_1.setBounds(153, 225, 61, 16);
		adminManageMoviePanel.add(lblNewLabel_11_1_1);

		addMstock = new JTextField();
		addMstock.setColumns(10);
		addMstock.setBounds(118, 253, 130, 26);
		adminManageMoviePanel.add(addMstock);

		JLabel lblNewLabel_11_2 = new JLabel("Actors:");
		lblNewLabel_11_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11_2.setForeground(Color.WHITE);
		lblNewLabel_11_2.setBounds(336, 98, 61, 16);
		adminManageMoviePanel.add(lblNewLabel_11_2);

		addMactor = new JTextField();
		addMactor.setColumns(10);
		addMactor.setBounds(303, 121, 130, 26);
		adminManageMoviePanel.add(addMactor);

		JLabel lblNewLabel_11_1_2 = new JLabel("Directors");
		lblNewLabel_11_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11_1_2.setForeground(Color.WHITE);
		lblNewLabel_11_1_2.setBounds(336, 159, 61, 16);
		adminManageMoviePanel.add(lblNewLabel_11_1_2);

		addMdirector = new JTextField();
		addMdirector.setColumns(10);
		addMdirector.setBounds(303, 187, 130, 26);
		adminManageMoviePanel.add(addMdirector);

		JLabel lblNewLabel_11_1_1_1 = new JLabel("Release Year:");
		lblNewLabel_11_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_11_1_1_1.setBounds(314, 225, 98, 16);
		adminManageMoviePanel.add(lblNewLabel_11_1_1_1);

		addMrdate = new JTextField();
		addMrdate.setColumns(10);
		addMrdate.setBounds(303, 253, 130, 26);
		adminManageMoviePanel.add(addMrdate);

		JLabel lblNewLabel_11_2_1 = new JLabel("Description:");
		lblNewLabel_11_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11_2_1.setForeground(Color.WHITE);
		lblNewLabel_11_2_1.setBounds(520, 98, 98, 16);
		adminManageMoviePanel.add(lblNewLabel_11_2_1);

		JTextArea addMdes = new JTextArea();
		addMdes.setBounds(477, 126, 189, 153);
		adminManageMoviePanel.add(addMdes);

		JButton btnNewButton_8 = new JButton("Add Movie");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = addMtitle.getText();
				String category = addMcategory.getText();
				String stock = addMstock.getText();
				String actor = addMactor.getText();
				String director = addMdirector.getText();
				String rdate = addMrdate.getText();
				String des = addMdes.getText();

				MaintainMovie m = new MaintainMovie();
				Movie movie = new Movie(title, category, stock, actor, director, rdate, des);
				if(m.addMovie(movie)) {
					JOptionPane.showMessageDialog(null, "Movie Added");	
				}else {
					JOptionPane.showMessageDialog(null, "Movie Already Exist");	
				}
				addMtitle.setText("");
				addMcategory.setText("");
				addMstock.setText("");
				addMactor.setText("");
				addMdirector.setText("");
				addMrdate.setText("");
				addMdes.setText("");
			}
		});
		btnNewButton_8.setBounds(689, 187, 117, 29);
		adminManageMoviePanel.add(btnNewButton_8);

		JButton btnNewButton_8_1 = new JButton("Remove Movie");
		btnNewButton_8_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MaintainMovie m = new MaintainMovie();
				try {
					if(m.removeMovie(removeMtitle.getText())) {
						JOptionPane.showMessageDialog(null, "Movie Deleted");
					}else {
						JOptionPane.showMessageDialog(null, "Movie Does not Exist");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				removeMtitle.setText("");
			}
		});
		btnNewButton_8_1.setBounds(689, 353, 117, 29);
		adminManageMoviePanel.add(btnNewButton_8_1);

		JLabel lblNewLabel_11_3 = new JLabel("Add Movie:");
		lblNewLabel_11_3.setForeground(Color.WHITE);
		lblNewLabel_11_3.setBounds(26, 98, 85, 16);
		adminManageMoviePanel.add(lblNewLabel_11_3);

		removeMtitle = new JTextField();
		removeMtitle.setBounds(413, 352, 264, 29);
		adminManageMoviePanel.add(removeMtitle);
		removeMtitle.setColumns(10);

		JLabel lblNewLabel_11_3_1 = new JLabel("Edit Movie:");
		lblNewLabel_11_3_1.setForeground(Color.WHITE);
		lblNewLabel_11_3_1.setBounds(283, 442, 85, 16);
		adminManageMoviePanel.add(lblNewLabel_11_3_1);

		updateMtitle = new JTextField();
		updateMtitle.setColumns(10);
		updateMtitle.setBounds(372, 572, 130, 26);
		adminManageMoviePanel.add(updateMtitle);

		JLabel lblNewLabel_11_4_1 = new JLabel("Actor:");
		lblNewLabel_11_4_1.setForeground(Color.WHITE);
		lblNewLabel_11_4_1.setBounds(372, 614, 61, 16);
		adminManageMoviePanel.add(lblNewLabel_11_4_1);

		JLabel lblNewLabel_11_4_1_1 = new JLabel("Director:");
		lblNewLabel_11_4_1_1.setForeground(Color.WHITE);
		lblNewLabel_11_4_1_1.setBounds(372, 671, 61, 16);
		adminManageMoviePanel.add(lblNewLabel_11_4_1_1);

		updateMdirector = new JTextField();
		updateMdirector.setColumns(10);
		updateMdirector.setBounds(368, 684, 158, 26);
		adminManageMoviePanel.add(updateMdirector);

		JLabel lblNewLabel_11_4_2 = new JLabel("Release Year:");
		lblNewLabel_11_4_2.setForeground(Color.WHITE);
		lblNewLabel_11_4_2.setBounds(538, 512, 115, 16);
		adminManageMoviePanel.add(lblNewLabel_11_4_2);

		updateMrdate = new JTextField();
		updateMrdate.setColumns(10);
		updateMrdate.setBounds(536, 526, 130, 26);
		adminManageMoviePanel.add(updateMrdate);

		JLabel lblNewLabel_11_2_1_1 = new JLabel("Description:");
		lblNewLabel_11_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_11_2_1_1.setBounds(536, 577, 82, 16);
		adminManageMoviePanel.add(lblNewLabel_11_2_1_1);

		JTextArea updateMdes = new JTextArea();
		updateMdes.setBounds(538, 595, 158, 115);
		adminManageMoviePanel.add(updateMdes);

		JButton btnNewButton_8_1_1 = new JButton("Update Movie");
		btnNewButton_8_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String movieTitle = updateMtitleOriginal.getText();
				String title = updateMtitle.getText();
				String actor = updateMactor.getText();
				String director = updateMdirector.getText();
				String rdate = updateMrdate.getText();
				String des = updateMdes.getText();

				MaintainMovie m = new MaintainMovie();
				if(m.updateInfo(movieTitle, title, actor, director, rdate, des)) {
					JOptionPane.showMessageDialog(null, "Movie Updated");
				}else {
					if(movieTitle.equals("")) {
						JOptionPane.showMessageDialog(null, "Title is required");
					}else if(title.equals("") && actor.equals("") && director.equals("") && rdate.equals("") && des.equals("")){
						JOptionPane.showMessageDialog(null, "Fill Something to Update");
					}else {
						JOptionPane.showMessageDialog(null, "Update Fail\nNo Movie Found");

					}
				}
				updateMtitleOriginal.setText("");
				updateMtitle.setText("");
				updateMactor.setText("");
				updateMdirector.setText("");
				updateMrdate.setText("");
				updateMdes.setText("");
			}
		});
		btnNewButton_8_1_1.setBounds(708, 609, 117, 29);
		adminManageMoviePanel.add(btnNewButton_8_1_1);

		JLabel lblNewLabel_11_5 = new JLabel("Title(required):");
		lblNewLabel_11_5.setForeground(Color.WHITE);
		lblNewLabel_11_5.setBounds(372, 493, 117, 16);
		adminManageMoviePanel.add(lblNewLabel_11_5);

		JLabel lblNewLabel_11_5_1 = new JLabel("New Title:");
		lblNewLabel_11_5_1.setForeground(Color.WHITE);
		lblNewLabel_11_5_1.setBounds(372, 559, 117, 16);
		adminManageMoviePanel.add(lblNewLabel_11_5_1);

		updateMactor = new JTextField();
		updateMactor.setColumns(10);
		updateMactor.setBounds(372, 630, 130, 26);
		adminManageMoviePanel.add(updateMactor);

		updateMtitleOriginal = new JTextField();
		updateMtitleOriginal.setColumns(10);
		updateMtitleOriginal.setBounds(372, 507, 130, 26);
		adminManageMoviePanel.add(updateMtitleOriginal);


		JButton btnNewButton_4_1_1 = new JButton("Back to Main");
		btnNewButton_4_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPanel.setVisible(false);
				adminLoginPanel.setVisible(true);
				currentPanel = adminLoginPanel;
			}
		});
		btnNewButton_4_1_1.setBounds(21, 34, 117, 29);
		adminMainPanel.add(btnNewButton_4_1_1);

		JLabel lblNewLabel_10 = new JLabel("Manage Movies:");
		lblNewLabel_10.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setBounds(203, 204, 221, 36);
		adminMainPanel.add(lblNewLabel_10);

		JLabel adminMain_admin = new JLabel("Admin: ");
		adminMain_admin.setForeground(Color.WHITE);
		adminMain_admin.setHorizontalAlignment(SwingConstants.CENTER);
		adminMain_admin.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		adminMain_admin.setBounds(279, 126, 281, 42);
		adminMainPanel.add(adminMain_admin);

		JLabel lblNewLabel_10_1 = new JLabel("Manage Customer Info:");
		lblNewLabel_10_1.setForeground(Color.WHITE);
		lblNewLabel_10_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_10_1.setBounds(172, 286, 221, 36);
		adminMainPanel.add(lblNewLabel_10_1);

		JLabel lblNewLabel_10_2 = new JLabel("Manage Admin");
		lblNewLabel_10_2.setForeground(Color.WHITE);
		lblNewLabel_10_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_10_2.setBounds(203, 371, 221, 36);
		adminMainPanel.add(lblNewLabel_10_2);

		JButton btnNewButton_7 = new JButton("Click to Edit");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				adminManageMoviePanel.setVisible(true);
				currentPanel = adminManageMoviePanel;
			}
		});
		btnNewButton_7.setBounds(424, 211, 117, 29);
		adminMainPanel.add(btnNewButton_7);

		JButton btnNewButton_7_1 = new JButton("Click to Edit");
		btnNewButton_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				adminManageCustomerPanel.setVisible(true);
				currentPanel = adminManageCustomerPanel;
			}
		});
		btnNewButton_7_1.setBounds(424, 293, 117, 29);
		adminMainPanel.add(btnNewButton_7_1);

		JButton btnNewButton_7_2 = new JButton("Click to Edit");
		btnNewButton_7_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				adminManageAdminPanel.setVisible(true);
				currentPanel = adminManageAdminPanel;
			}
		});
		btnNewButton_7_2.setBounds(424, 378, 117, 29);
		adminMainPanel.add(btnNewButton_7_2);


		JButton btnNewButton_4_2 = new JButton("Back to Main");
		btnNewButton_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				welcomePanel.setVisible(true);
				currentPanel = welcomePanel;
			}
		});
		btnNewButton_4_2.setBounds(21, 34, 117, 29);
		adminLoginPanel.add(btnNewButton_4_2);

		JLabel lblNewLabel_6 = new JLabel("Admin Login");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Lucida Grande", Font.PLAIN, 38));
		lblNewLabel_6.setBounds(298, 121, 319, 102);
		adminLoginPanel.add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel("Email:");
		lblNewLabel_6_1.setForeground(Color.WHITE);
		lblNewLabel_6_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_6_1.setBounds(257, 280, 86, 46);
		adminLoginPanel.add(lblNewLabel_6_1);

		JLabel lblNewLabel_6_1_1 = new JLabel("Password:");
		lblNewLabel_6_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_6_1_1.setBounds(227, 357, 125, 59);
		adminLoginPanel.add(lblNewLabel_6_1_1);

		adminLogin_email = new JTextField();
		adminLogin_email.setBounds(355, 291, 210, 29);
		adminLoginPanel.add(adminLogin_email);
		adminLogin_email.setColumns(10);

		adminLogin_password = new JPasswordField();
		adminLogin_password.setBounds(355, 374, 210, 29);
		adminLoginPanel.add(adminLogin_password);

		JButton btnNewButton_5 = new JButton("Login");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = adminLogin_email.getText();
				String password = String.valueOf(adminLogin_password.getPassword());

				MaintainAdmin admin = new MaintainAdmin();
				if(admin.login(email, password)) {
					JOptionPane.showMessageDialog(null, "Login Success");	
					currentPanel.setVisible(false);
					adminMainPanel.setVisible(true);
					currentPanel = adminMainPanel;
					adminMain_admin.setText("Admin: " + admin.getAdmin().getName());

				}else {
					JOptionPane.showMessageDialog(null, "Login Fail");	
				}
				adminLogin_email.setText("");
				adminLogin_password.setText("");
			}
		});
		btnNewButton_5.setBounds(355, 453, 117, 29);
		adminLoginPanel.add(btnNewButton_5);
		adminLoginPanel.setVisible(false);

		frame.setSize(welcomePanel.getDim());
		frame.setPreferredSize(welcomePanel.getDim());



		JLabel lblNewLabel_8 = new JLabel("PROFILE");
		lblNewLabel_8.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		lblNewLabel_8.setBounds(325, 42, 258, 74);
		editProfilePanel.add(lblNewLabel_8);


		JLabel lblNewLabel_9 = new JLabel("New Name: ");
		lblNewLabel_9.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_9.setBounds(174, 265, 173, 40);
		editProfilePanel.add(lblNewLabel_9);

		JLabel lblNewLabel_9_1 = new JLabel("New Email: ");
		lblNewLabel_9_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_9_1.setBounds(174, 331, 173, 40);
		editProfilePanel.add(lblNewLabel_9_1);

		JLabel lblNewLabel_9_2 = new JLabel("New Password: ");
		lblNewLabel_9_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_9_2.setBounds(174, 397, 145, 40);
		editProfilePanel.add(lblNewLabel_9_2);

		profile_newName = new JTextField();
		profile_newName.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		profile_newName.setBounds(310, 269, 251, 31);
		editProfilePanel.add(profile_newName);
		profile_newName.setColumns(10);

		profile_newEmail = new JTextField();
		profile_newEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		profile_newEmail.setColumns(10);
		profile_newEmail.setBounds(310, 335, 251, 31);
		editProfilePanel.add(profile_newEmail);

		profile_newPass = new JTextField();
		profile_newPass.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		profile_newPass.setColumns(10);
		profile_newPass.setBounds(310, 401, 251, 31);
		editProfilePanel.add(profile_newPass);

		JLabel profile_user = new JLabel("username");
		profile_user.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		profile_user.setBounds(410, 132, 173, 40);
		editProfilePanel.add(profile_user);

		JButton btnClickToEdit = new JButton("Click to Edit");
		btnClickToEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = profile_user.getText();
				String newName = profile_newName.getText();
				String newEmail = profile_newEmail.getText();
				String newPass = profile_newPass.getText();
				MaintainUser mUser = new MaintainUser();

				if(mUser.updateInfo(userName, newName, newEmail, newPass)) {
					JOptionPane.showMessageDialog(null, "New Profile Set!");
					profile_newName.setText("");
					profile_newEmail.setText("");
					profile_newPass.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "No Input\nCant edit Profile");
					profile_newName.setText("");
					profile_newEmail.setText("");
					profile_newPass.setText("");
				}
			}
		});
		btnClickToEdit.setBounds(310, 457, 154, 29);
		editProfilePanel.add(btnClickToEdit);

		JLabel lblNewLabel_9_3 = new JLabel("Loyalty Points:");
		lblNewLabel_9_3.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_9_3.setBounds(277, 184, 173, 40);
		editProfilePanel.add(lblNewLabel_9_3);

		JLabel profile_point = new JLabel("point");
		profile_point.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		profile_point.setBounds(410, 184, 173, 40);
		editProfilePanel.add(profile_point);

		JLabel lblNewLabel_9_4 = new JLabel("Current User:");
		lblNewLabel_9_4.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_9_4.setBounds(273, 132, 125, 40);
		editProfilePanel.add(lblNewLabel_9_4);

		JButton btnNewButton_4_2_1_1 = new JButton("Back to Main");
		btnNewButton_4_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profile_newName.setText("");
				profile_newEmail.setText("");
				profile_newPass.setText("");

				currentPanel.setVisible(false);
				customerMainPanel.setVisible(true);
				currentPanel = customerMainPanel;
			}
		});
		btnNewButton_4_2_1_1.setBounds(21, 34, 117, 29);
		editProfilePanel.add(btnNewButton_4_2_1_1);


		ImagePanel loginPanel = new ImagePanel(new ImageIcon(cImage).getImage());
		frame.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		lblNewLabel_2.setBounds(350, 126, 125, 63);
		loginPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Username:");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(224, 272, 112, 41);
		loginPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Password:");
		lblNewLabel_3_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(224, 364, 112, 41);
		loginPanel.add(lblNewLabel_3_1);

		loginUser = new JTextField();
		loginUser.setBounds(344, 278, 217, 32);
		loginPanel.add(loginUser);
		loginUser.setColumns(10);

		loginPass = new JPasswordField();
		loginPass.setBounds(344, 370, 217, 32);
		loginPanel.add(loginPass);

		JButton btnNewButton_2 = new JButton("Login");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MaintainUser user = new MaintainUser();
				//Login(loginUser.getText(), String.valueOf(loginPass.getPassword()));
				if(user.login(loginUser.getText(), String.valueOf(loginPass.getPassword()))) {
					currentUser = user.getUser();

					JOptionPane.showMessageDialog(null, "Login Success");
					//Editing next pages
					customerMain_username.setText("User: " + currentUser.getUsername());
					profile_point.setText(currentUser.getPoint());
					profile_user.setText(currentUser.getUsername());
					customerTentative_username.setText("User: " + currentUser.getUsername());
					customerPayment_point.setText(currentUser.getPoint());
					customerPayment_username.setText(currentUser.getUsername());
					customerOrder_username.setText(currentUser.getUsername());


					String order_name = customerOrder_username.getText();
					TableRowSorter<AbstractTableModel> trsOrder = new TableRowSorter<>(tdo);
					table_order.setRowSorter(trsOrder);
					trsOrder.setRowFilter(RowFilter.regexFilter(order_name));
					tdo.refresh();

					currentPanel.setVisible(false);
					customerMainPanel.setVisible(true);
					currentPanel = customerMainPanel;
				}else {
					JOptionPane.showMessageDialog(null, "Login Error\nInvalid Credentials");
				}
				loginUser.setText("");
				loginPass.setText("");

			}
		});
		btnNewButton_2.setBounds(346, 448, 117, 29);
		loginPanel.add(btnNewButton_2);

		JButton btnNewButton_4 = new JButton("Back to Main");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				welcomePanel.setVisible(true);
				currentPanel = welcomePanel;
			}
		});
		btnNewButton_4.setBounds(21, 33, 117, 29);
		loginPanel.add(btnNewButton_4);
		loginPanel.setVisible(false);
		ImagePanel registerPanel = new ImagePanel(new ImageIcon(cImage).getImage());
		frame.getContentPane().add(registerPanel);
		frame.getContentPane().add(registerPanel);

		JLabel lblNewLabel_4 = new JLabel("Enter information for Register");
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel_4.setBounds(215, 110, 517, 91);
		registerPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Name: ");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(263, 251, 148, 37);
		registerPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("Username:");
		lblNewLabel_5_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_5_1.setBounds(229, 327, 148, 37);
		registerPanel.add(lblNewLabel_5_1);

		JLabel lblNewLabel_5_2 = new JLabel("Password:");
		lblNewLabel_5_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_5_2.setBounds(229, 405, 148, 37);
		registerPanel.add(lblNewLabel_5_2);

		JLabel lblNewLabel_5_3 = new JLabel("Email:");
		lblNewLabel_5_3.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_5_3.setBounds(263, 475, 148, 37);
		registerPanel.add(lblNewLabel_5_3);

		regiName = new JTextField();
		regiName.setBounds(354, 253, 198, 37);
		registerPanel.add(regiName);
		regiName.setColumns(10);

		regiUser = new JTextField();
		regiUser.setColumns(10);
		regiUser.setBounds(354, 329, 198, 37);
		registerPanel.add(regiUser);

		regiPass = new JTextField();
		regiPass.setColumns(10);
		regiPass.setBounds(354, 407, 198, 37);
		registerPanel.add(regiPass);

		regiEmail = new JTextField();
		regiEmail.setColumns(10);
		regiEmail.setBounds(354, 477, 198, 37);
		registerPanel.add(regiEmail);

		JButton btnNewButton_3 = new JButton("Register");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MaintainUser user = new MaintainUser();
				User newUser = new User(regiName.getText(), regiUser.getText(), 
						regiEmail.getText(), regiPass.getText(), "0", "null", "null");
				try {
					if(user.addCus(newUser)) {
						JOptionPane.showMessageDialog(null, "Register Success");
						customerMain_username.setText("User: " + newUser.getName());
						currentPanel.setVisible(false);
						loginPanel.setVisible(true);
						currentPanel = loginPanel;
						regiName.setText("");
						regiUser.setText("");
						regiEmail.setText("");
						regiPass.setText("");
					}else {
						JOptionPane.showMessageDialog(null, "Existing Customer\n"
								+ "Please Try with new Email or Username");
						regiName.setText("");
						regiUser.setText("");
						regiEmail.setText("");
						regiPass.setText("");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(354, 545, 117, 29);
		registerPanel.add(btnNewButton_3);

		JButton btnNewButton_4_1 = new JButton("Back to Main");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				welcomePanel.setVisible(true);
				currentPanel = welcomePanel;
			}
		});
		btnNewButton_4_1.setBounds(21, 34, 117, 29);
		registerPanel.add(btnNewButton_4_1);
		registerPanel.setVisible(false);
		frame.getContentPane().add(welcomePanel);



		JLabel lblNewLabel = new JLabel("Welcome To VideoCo");
		lblNewLabel.setFont(new Font("Kohinoor Gujarati", Font.PLAIN, 60));
		lblNewLabel.setBounds(128, 106, 662, 85);
		welcomePanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Login As Customer:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(214, 265, 178, 41);
		welcomePanel.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				loginPanel.setVisible(true);
				currentPanel = loginPanel;
			}
		});
		btnNewButton.setBounds(413, 274, 117, 29);
		welcomePanel.add(btnNewButton);

		JLabel lblNewLabel_1_1 = new JLabel("Register As Customer:");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(214, 354, 230, 41);
		welcomePanel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Login As Admin:");
		lblNewLabel_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(214, 443, 178, 41);
		welcomePanel.add(lblNewLabel_1_2);

		JButton btnNewButton_1 = new JButton("Sign in");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				registerPanel.setVisible(true);
				currentPanel = registerPanel;
			}
		});
		btnNewButton_1.setBounds(413, 363, 117, 29);
		welcomePanel.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Login");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentPanel.setVisible(false);
				adminLoginPanel.setVisible(true);
				currentPanel = adminLoginPanel;
			}
		});
		btnNewButton_1_1.setBounds(413, 452, 117, 29);
		welcomePanel.add(btnNewButton_1_1);

		frame.pack();
	}
}
