import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class example extends JFrame {
	 // JDBC URL, username and password of MySQL server
	 private static final String url = "jdbc:mysql://localhost:3306/cities";
	 private static final String user = "root";
	 private static final String password = "Host879";

     // JDBC variables for opening and managing connection
	 private static Connection con;
	 private static Statement stmt;
	 private static ResultSet rs;
    
	 public example() {
		 JFrame frame = new JFrame();
		 frame.setTitle("������ � ���������������������� ������� ������� ������");
		 frame.setSize(600,300);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setLocationRelativeTo(null);
		 frame.getContentPane().setBackground(new Color(204,153,255));
		 frame.setLayout(new GridBagLayout());
		
	
		 JMenuBar menuBar = new JMenuBar();
		 JMenu Sprav = new JMenu("�����������");
		 JMenu gorod = new JMenu("���������� �������");
		 JMenuItem dobav1 = new JMenuItem("��������");
		 dobav1.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent event) {
			 JFrame frame1 = new JFrame("���������� ������ � ���������� �������");
			 frame1.setSize(600,300);
			 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 frame1.setLocationRelativeTo(null);
			 frame1.getContentPane().setBackground(new Color(153,255,204));
			 frame1.setLayout(new GridBagLayout());
				
			 JTextField textf = new JTextField(20);
			 JButton buttonf = new JButton("��������");
			 buttonf.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
					 String query = "insert into cities values (null, '" + textf.getText() + "')";
					    try {
					        // opening database connection to MySQL server
					        con = DriverManager.getConnection(url, user, password);
					        // getting Statement object to execute query
					        stmt = con.createStatement();
					        // executing SELECT query
					        int rs = stmt.executeUpdate(query);
					        if (rs == 0) {
		                         JOptionPane.showMessageDialog(buttonf, "This is alredy exist");
		                     } else {
		                         JOptionPane.showMessageDialog(buttonf, "����� ����� ������� �������� � �������!");
		                     }
					   } catch (Exception exception) {
					        exception.printStackTrace();
					   } finally {
					        //close connection ,stmt and resultset here
					        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
					        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
					        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
					    }
			 }
		 });	
				 frame1.add(textf);
				 frame1.add(buttonf);
				 frame1.setVisible(true);
			 }
		 });
		
		 JMenuItem izmen1 = new JMenuItem("��������");
		 izmen1.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("��������� ������ � ����������� �������");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(255,255,153));
				 frame1.setLayout(new GridBagLayout());
				
				 JComboBox combo = new JComboBox();
				 String query = "select city from cities";
		         try {
		             // opening database connection to MySQL server
		             con = DriverManager.getConnection(url, user, password);

		             // getting Statement object to execute query
		             stmt = con.createStatement();

		             // executing SELECT query
		             rs = stmt.executeQuery(query);

		             while (rs.next()) {
		            	 combo.addItem(rs.getString("city"));
		             }

		         } catch (Exception exception) {
		             exception.printStackTrace();
		         } finally {
		             //close connection ,stmt and resultset here
		             try { con.close(); } catch(SQLException se) { /*can't do anything */ }
		             try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		             try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		         }
				 JTextField textf2 = new JTextField(20);
				 JButton buttonf = new JButton("��������");
				 buttonf.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent event) {
						 String query = "update cities set city ='" + textf2.getText() + "' where city = '" + combo.getSelectedItem() + "' limit 1";
						    try {
						        // opening database connection to MySQL server
						        con = DriverManager.getConnection(url, user, password);
						        // getting Statement object to execute query
						        stmt = con.createStatement();
						        // executing SELECT query
						        int rs = stmt.executeUpdate(query);
						        if (rs == 0) {
			                         JOptionPane.showMessageDialog(buttonf, "�����, ������� �� ������ ��������, �� ��� ������");
			                     } else {
			                         JOptionPane.showMessageDialog(buttonf, "������ ����� ������� �������!");
			                     }
						   } catch (Exception exception) {
						        exception.printStackTrace();
						   } finally {
						        //close connection ,stmt and resultset here
						        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
						    }
				 }
			 });	
					 frame1.add(combo);
					 frame1.add(textf2);
					 frame1.add(buttonf);
					 frame1.setVisible(true);
				 }
			 });
		 JMenuItem udal1 = new JMenuItem("�������");
		 udal1.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("�������� ������ �� ����������� �������");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(255,153,153));
				 frame1.setLayout(new GridBagLayout());
					
				 JComboBox combo = new JComboBox();
				 String query = "select city from cities";
		         try {
		             // opening database connection to MySQL server
		             con = DriverManager.getConnection(url, user, password);

		             // getting Statement object to execute query
		             stmt = con.createStatement();

		             // executing SELECT query
		             rs = stmt.executeQuery(query);

		             while (rs.next()) {
		            	 combo.addItem(rs.getString("city"));
		             }

		         } catch (Exception exception) {
		             exception.printStackTrace();
		         } finally {
		             //close connection ,stmt and resultset here
		             try { con.close(); } catch(SQLException se) { /*can't do anything */ }
		             try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		             try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		         }
				 JButton buttonf = new JButton("�������");
				 buttonf.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent event) {
						 String query = "delete from cities where city = '" + combo.getSelectedItem() + "' limit 1";
						    try {
						        // opening database connection to MySQL server
						        con = DriverManager.getConnection(url, user, password);
						        // getting Statement object to execute query
						        stmt = con.createStatement();
						        // executing SELECT query
						        int rs = stmt.executeUpdate(query);
						        if (rs == 0) {
			                         JOptionPane.showMessageDialog(buttonf, "�����, ������� �� ������ �������, �� ��� ������");
			                     } else {
			                         JOptionPane.showMessageDialog(buttonf, "������ ����� ������� ������!");
			                     }
						   } catch (Exception exception) {
						        exception.printStackTrace();
						   } finally {
						        //close connection ,stmt and resultset here
						        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
						    }
				 }
			  });	
					 frame1.add(combo);
					 frame1.add(buttonf);
					 frame1.setVisible(true);
				 }
			 });
		 JMenuItem poisk1 = new JMenuItem("�����");
		 poisk1.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("����� ������ �� ����������� �������");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(153,204,255));
				 frame1.setLayout(new GridBagLayout());
				
				 JTextField textf1 = new JTextField(20);
				 JLabel label = new JLabel();
				 JButton buttonf1 = new JButton("����� �� �������� ������");
				 buttonf1.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent event) {
						 String query = "select * from cities where city = '" + textf1.getText() + "'";
						    try {
						        // opening database connection to MySQL server
						        con = DriverManager.getConnection(url, user, password);
						        // getting Statement object to execute query
						        stmt = con.createStatement();
						        // executing SELECT query
						        rs = stmt.executeQuery(query);
			                    while (rs.next()) {
			                    	 int a = rs.getInt(1);
			                    	 String b = rs.getString(2);
			                    	 label.setText("  Id: " + a + " City: " + b);
			                     }
						   } catch (Exception exception) {
						        exception.printStackTrace();
						   } finally {
						        //close connection ,stmt and resultset here
						        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
						    }
				 }
			 });	
				 JTextField textf2 = new JTextField(20);
				 JLabel label2 = new JLabel();
				 JButton buttonf2 = new JButton("����� �� ����");
				 buttonf2.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent event) {
						 String query = "select * from cities where id = '" + textf2.getText() + "'";
						    try {
						        // opening database connection to MySQL server
						        con = DriverManager.getConnection(url, user, password);
						        // getting Statement object to execute query
						        stmt = con.createStatement();
						        // executing SELECT query
						        rs = stmt.executeQuery(query);
			                    while (rs.next()) {
			                    	 int a = rs.getInt(1);
			                    	 String b = rs.getString(2);
			                    	 label2.setText("  Id: " + a + " City: " + b);
			                     }
						   } catch (Exception exception) {
						        exception.printStackTrace();
						   } finally {
						        //close connection ,stmt and resultset here
						        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
						    }
				 }
			 });	
					
					 frame1.add(textf1);
					 frame1.add(buttonf1);
					 frame1.add(label);
					 frame1.add(textf2);
					 frame1.add(buttonf2);
					 frame1.add(label2);
					 frame1.setVisible(true);
				 }
			 });
		
		
		 JMenu oblast = new JMenu("���������� ��������");
		 JMenuItem dobav2 = new JMenuItem("��������");
		 dobav2.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("���������� ������ � ���������� ��������");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(153,255,204));
				 frame1.setLayout(new GridBagLayout());
					
				 JTextField textf = new JTextField(20);
				 JButton buttonf = new JButton("��������");
				 buttonf.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent event) {
						 String query = "insert into regions values (null, '" + textf.getText() + "')";
						    try {
						        // opening database connection to MySQL server
						        con = DriverManager.getConnection(url, user, password);
						        // getting Statement object to execute query
						        stmt = con.createStatement();
						        // executing SELECT query
						        int rs = stmt.executeUpdate(query);
						        if (rs == 0) {
			                         JOptionPane.showMessageDialog(buttonf, "This is alredy exist");
			                     } else {
			                         JOptionPane.showMessageDialog(buttonf, "����� ������� ������� ��������� � �������!");
			                     }
						   } catch (Exception exception) {
						        exception.printStackTrace();
						   } finally {
						        //close connection ,stmt and resultset here
						        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
						    }
				 }
			 });	
					 frame1.add(textf);
					 frame1.add(buttonf);
					 frame1.setVisible(true);
				 }
			 });
		 JMenuItem izmen2 = new JMenuItem("��������");
		 izmen2.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("��������� ������ � ����������� ��������");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(255,255,153));
				 frame1.setLayout(new GridBagLayout());
					
				 JComboBox combo = new JComboBox();
				 String query = "select region from regions";
		         try {
		             // opening database connection to MySQL server
		             con = DriverManager.getConnection(url, user, password);

		             // getting Statement object to execute query
		             stmt = con.createStatement();

		             // executing SELECT query
		             rs = stmt.executeQuery(query);

		             while (rs.next()) {
		            	 combo.addItem(rs.getString("region"));
		             }

		         } catch (Exception exception) {
		             exception.printStackTrace();
		         } finally {
		             //close connection ,stmt and resultset here
		             try { con.close(); } catch(SQLException se) { /*can't do anything */ }
		             try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		             try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		         }
				 JTextField textf2 = new JTextField(20);
				 JButton buttonf = new JButton("��������");
				 buttonf.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent event) {
						 String query = "update regions set region ='" + textf2.getText() + "' where region = '" + combo.getSelectedItem() + "' limit 1";
						    try {
						        // opening database connection to MySQL server
						        con = DriverManager.getConnection(url, user, password);
						        // getting Statement object to execute query
						        stmt = con.createStatement();
						        // executing SELECT query
						        int rs = stmt.executeUpdate(query);
						        if (rs == 0) {
			                         JOptionPane.showMessageDialog(buttonf, "�������, ������� �� ������ ��������, �� ���� �������");
			                     } else {
			                         JOptionPane.showMessageDialog(buttonf, "������ ������� ������� ��������!");
			                     }
						   } catch (Exception exception) {
						        exception.printStackTrace();
						   } finally {
						        //close connection ,stmt and resultset here
						        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
						    }
				 }
			 });	
					 frame1.add(combo);
					 frame1.add(textf2);
					 frame1.add(buttonf);
					 frame1.setVisible(true);
				 }
			 });
		 JMenuItem udal2 = new JMenuItem("�������");
		 udal2.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("�������� ������ �� ����������� ��������");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(255,153,153));
				 frame1.setLayout(new GridBagLayout());
					
				 JComboBox combo = new JComboBox();
				 String query = "select region from regions";
		         try {
		             // opening database connection to MySQL server
		             con = DriverManager.getConnection(url, user, password);

		             // getting Statement object to execute query
		             stmt = con.createStatement();

		             // executing SELECT query
		             rs = stmt.executeQuery(query);

		             while (rs.next()) {
		            	 combo.addItem(rs.getString("region"));
		             }

		         } catch (Exception exception) {
		             exception.printStackTrace();
		         } finally {
		             //close connection ,stmt and resultset here
		             try { con.close(); } catch(SQLException se) { /*can't do anything */ }
		             try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		             try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		         }
				 JButton buttonf = new JButton("�������");
				 buttonf.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent event) {
						 String query = "delete from regions where region = '" + combo.getSelectedItem() + "' limit 1";
						    try {
						        // opening database connection to MySQL server
						        con = DriverManager.getConnection(url, user, password);
						        // getting Statement object to execute query
						        stmt = con.createStatement();
						        // executing SELECT query
						        int rs = stmt.executeUpdate(query);
						        if (rs == 0) {
			                         JOptionPane.showMessageDialog(buttonf, "�������, ������� �� ������ �������, �� ���� �������");
			                     } else {
			                         JOptionPane.showMessageDialog(buttonf, "������ ������� ������� �������!");
			                     }
						   } catch (Exception exception) {
						        exception.printStackTrace();
						   } finally {
						        //close connection ,stmt and resultset here
						        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
						    }
				 }
			 });	
					 frame1.add(combo);
					 frame1.add(buttonf);
					 frame1.setVisible(true);
				 }
			 });
		 JMenuItem poisk2 = new JMenuItem("�����");
		 poisk2.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("����� ������ �� ����������� ��������");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(153,204,255));
				 frame1.setLayout(new GridBagLayout());
				
				 JTextField textf1 = new JTextField(20);
				 JLabel label = new JLabel();
				 JButton buttonf1 = new JButton("����� �� �������� �������");
				 buttonf1.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent event) {
						 String query = "select * from regions where region = '" + textf1.getText() + "'";
						    try {
						        // opening database connection to MySQL server
						        con = DriverManager.getConnection(url, user, password);
						        // getting Statement object to execute query
						        stmt = con.createStatement();
						        // executing SELECT query
						        rs = stmt.executeQuery(query);
			                    while (rs.next()) {
			                    	 int a = rs.getInt(1);
			                    	 String b = rs.getString(2);
			                    	 label.setText("  Id: " + a + " Region: " + b);
			                     }
						   } catch (Exception exception) {
						        exception.printStackTrace();
						   } finally {
						        //close connection ,stmt and resultset here
						        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
						    }
				 }
			 });	
				 JTextField textf2 = new JTextField(20);
				 JLabel label2 = new JLabel();
				 JButton buttonf2 = new JButton("����� �� ����");
				 buttonf2.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent event) {
						 String query = "select * from regions where id = '" + textf2.getText() + "'";
						    try {
						        // opening database connection to MySQL server
						        con = DriverManager.getConnection(url, user, password);
						        // getting Statement object to execute query
						        stmt = con.createStatement();
						        // executing SELECT query
						        rs = stmt.executeQuery(query);
			                    while (rs.next()) {
			                    	 int a = rs.getInt(1);
			                    	 String b = rs.getString(2);
			                    	 label2.setText("  Id: " + a + " Region: " + b);
			                     }
						   } catch (Exception exception) {
						        exception.printStackTrace();
						   } finally {
						        //close connection ,stmt and resultset here
						        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
						    }
				 }
			 });	
					
					 frame1.add(textf1);
					 frame1.add(buttonf1);
					 frame1.add(label);
					 frame1.add(textf2);
					 frame1.add(buttonf2);
					 frame1.add(label2);
					 frame1.setVisible(true);
				 }
			 });
		
		
		 Sprav.add(gorod);
		 gorod.add(dobav1);
		 gorod.add(izmen1);
		 gorod.add(udal1);
		 gorod.add(poisk1);
		 Sprav.add(oblast);
		 oblast.add(dobav2);
		 oblast.add(izmen2);
		 oblast.add(udal2);
		 oblast.add(poisk2);

		 JMenu Operat = new JMenu("����������� ������");
		 JMenuItem vvestid = new JMenuItem("������ ������");
		 vvestid.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("���������� ����������� ������");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(153,255,204));
				 frame1.setLayout(new FlowLayout());
					
				 JTextField textf1 = new JTextField(20);
				 JTextField textf2 = new JTextField(20);
				 JTextField textf3 = new JTextField(20);
				 JTextField textf4 = new JTextField(20);
				 JTextField textf5 = new JTextField(20);
				 JTextField textf6 = new JTextField(20);
				 JButton buttonf = new JButton("��������");
				 buttonf.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent event) {
						 String query = "insert into data_on_cities values (null, '" + textf1.getText() +  "','" + textf2.getText()+ "','" + textf3.getText() + "','" + textf4.getText() + "','" + textf5.getText() + "','" + textf6.getText() + "')";
						    try {
						        // opening database connection to MySQL server
						        con = DriverManager.getConnection(url, user, password);
						        // getting Statement object to execute query
						        stmt = con.createStatement();
						        // executing SELECT query
						        int rs = stmt.executeUpdate(query);
						        if (rs == 0) {
			                         JOptionPane.showMessageDialog(buttonf, "This is alredy exist");
			                     } else {
			                         JOptionPane.showMessageDialog(buttonf, "����� ������ ������� ��������� � �������!");
			                     }
						   } catch (Exception exception) {
						        exception.printStackTrace();
						   } finally {
						        //close connection ,stmt and resultset here
						        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
						        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
						    }
				 }
			 });	
					 frame1.add(textf1);
					 frame1.add(textf2);
					 frame1.add(textf3);
					 frame1.add(textf4);
					 frame1.add(textf5);
					 frame1.add(textf6);
					 frame1.add(buttonf);
					 frame1.setVisible(true);
				 }
			 });
		 
		 JMenuItem izmend = new JMenuItem("�������� ������");
		 izmend.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("��������� ����������� ������");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(153,255,204));
				 frame1.setLayout(new FlowLayout());
				 
				 JButton button1 = new JButton("�������� ��� �������");
				 button1.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent event) {
						 JFrame frame1 = new JFrame("��������� ������ �� ���� �������");
						 frame1.setSize(600,300);
						 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						 frame1.setLocationRelativeTo(null);
						 frame1.getContentPane().setBackground(new Color(153,255,204));
						 frame1.setLayout(new FlowLayout());
						 
						 JComboBox combo = new JComboBox();
						 String query = "select region_id from data_on_cities";
				         try {
				             // opening database connection to MySQL server
				             con = DriverManager.getConnection(url, user, password);
				             // getting Statement object to execute query
				             stmt = con.createStatement();
				             // executing SELECT query
				             rs = stmt.executeQuery(query);
				             while (rs.next()) {
				            	 combo.addItem(rs.getString("region_id"));
				             }
				         } catch (Exception exception) {
				             exception.printStackTrace();
				         } finally {
				             //close connection ,stmt and resultset here
				             try { con.close(); } catch(SQLException se) { /*can't do anything */ }
				             try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
				             try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
				         }
						 JTextField textf2 = new JTextField(20);
						 JButton buttonf = new JButton("��������");
						 buttonf.addActionListener(new ActionListener(){
						 public void actionPerformed(ActionEvent event) {
								 String query = "update data_on_cities set region_id ='" + textf2.getText() + "' where region_id = '" + combo.getSelectedItem() + "' limit 1";
								    try {
								        // opening database connection to MySQL server
								        con = DriverManager.getConnection(url, user, password);
								        // getting Statement object to execute query
								        stmt = con.createStatement();
								        // executing SELECT query
								        int rs = stmt.executeUpdate(query);
								        if (rs == 0) {
					                         JOptionPane.showMessageDialog(buttonf, "��� �������, ������� �� ������ ��������, �� ��� ������");
					                     } else {
					                         JOptionPane.showMessageDialog(buttonf, "������ ��� ������� ��� ������� �������!");
					                     }
								   } catch (Exception exception) {
								        exception.printStackTrace();
								   } finally {
								        //close connection ,stmt and resultset here
								        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
								        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
								        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
								    }
						 }
					 });
						 
						 frame1.add(combo);
						 frame1.add(textf2);
						 frame1.add(buttonf);
						 frame1.setVisible(true);
					 }
				 });
				 
				 JButton button2 = new JButton("�������� ��� ������");
				 button2.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent event) {
						 JFrame frame1 = new JFrame("��������� ������ �� ���� ������");
						 frame1.setSize(600,300);
						 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						 frame1.setLocationRelativeTo(null);
						 frame1.getContentPane().setBackground(new Color(153,255,204));
						 frame1.setLayout(new FlowLayout());
						 
						 JComboBox combo = new JComboBox();
						 String query = "select city_id from data_on_cities";
				         try {
				             // opening database connection to MySQL server
				             con = DriverManager.getConnection(url, user, password);
				             // getting Statement object to execute query
				             stmt = con.createStatement();
				             // executing SELECT query
				             rs = stmt.executeQuery(query);
				             while (rs.next()) {
				            	 combo.addItem(rs.getString("city_id"));
				             }
				         } catch (Exception exception) {
				             exception.printStackTrace();
				         } finally {
				             //close connection ,stmt and resultset here
				             try { con.close(); } catch(SQLException se) { /*can't do anything */ }
				             try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
				             try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
				         }
						 JTextField textf2 = new JTextField(20);
						 JButton buttonf = new JButton("��������");
						 buttonf.addActionListener(new ActionListener(){
						 public void actionPerformed(ActionEvent event) {
								 String query = "update data_on_cities set city_id ='" + textf2.getText() + "' where city_id = '" + combo.getSelectedItem() + "' limit 1";
								    try {
								        // opening database connection to MySQL server
								        con = DriverManager.getConnection(url, user, password);
								        // getting Statement object to execute query
								        stmt = con.createStatement();
								        // executing SELECT query
								        int rs = stmt.executeUpdate(query);
								        if (rs == 0) {
					                         JOptionPane.showMessageDialog(buttonf, "��� ������, ������� �� ������ ��������, �� ��� ������");
					                     } else {
					                         JOptionPane.showMessageDialog(buttonf, "������ ��� ������ ��� ������� �������!");
					                     }
								   } catch (Exception exception) {
								        exception.printStackTrace();
								   } finally {
								        //close connection ,stmt and resultset here
								        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
								        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
								        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
								    }
						 }
					 });
						 frame1.add(combo);
						 frame1.add(textf2);
						 frame1.add(buttonf);
						 frame1.setVisible(true);
					 }
				 });
				 
				 JButton button3 = new JButton("�������� ����������� ���������");
				 button3.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent event) {
						 JFrame frame1 = new JFrame("��������� ������ �� ����������� ���������");
						 frame1.setSize(600,300);
						 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						 frame1.setLocationRelativeTo(null);
						 frame1.getContentPane().setBackground(new Color(153,255,204));
						 frame1.setLayout(new FlowLayout());
						 
						 JComboBox combo = new JComboBox();
						 String query = "select population from data_on_cities";
				         try {
				             // opening database connection to MySQL server
				             con = DriverManager.getConnection(url, user, password);
				             // getting Statement object to execute query
				             stmt = con.createStatement();
				             // executing SELECT query
				             rs = stmt.executeQuery(query);
				             while (rs.next()) {
				            	 combo.addItem(rs.getString("population"));
				             }
				         } catch (Exception exception) {
				             exception.printStackTrace();
				         } finally {
				             //close connection ,stmt and resultset here
				             try { con.close(); } catch(SQLException se) { /*can't do anything */ }
				             try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
				             try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
				         }
						 JTextField textf2 = new JTextField(20);
						 JButton buttonf = new JButton("��������");
						 buttonf.addActionListener(new ActionListener(){
						 public void actionPerformed(ActionEvent event) {
								 String query = "update data_on_cities set population ='" + textf2.getText() + "' where population = '" + combo.getSelectedItem() + "' limit 1";
								    try {
								        // opening database connection to MySQL server
								        con = DriverManager.getConnection(url, user, password);
								        // getting Statement object to execute query
								        stmt = con.createStatement();
								        // executing SELECT query
								        int rs = stmt.executeUpdate(query);
								        if (rs == 0) {
					                         JOptionPane.showMessageDialog(buttonf, "������");
					                     } else {
					                         JOptionPane.showMessageDialog(buttonf, "����������� ��������� ���� ������� ��������!");
					                     }
								   } catch (Exception exception) {
								        exception.printStackTrace();
								   } finally {
								        //close connection ,stmt and resultset here
								        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
								        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
								        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
								    }
						 }
					 });
						 frame1.add(combo);
						 frame1.add(textf2);
						 frame1.add(buttonf);
						 frame1.setVisible(true);
					 }
				 });
				 
				 JButton button4 = new JButton("�������� ������� ������");
				 button4.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent event) {
						 JFrame frame1 = new JFrame("��������� ������ �� ������� ������");
						 frame1.setSize(600,300);
						 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						 frame1.setLocationRelativeTo(null);
						 frame1.getContentPane().setBackground(new Color(153,255,204));
						 frame1.setLayout(new FlowLayout());
						 
						 JComboBox combo = new JComboBox();
						 String query = "select area from data_on_cities";
				         try {
				             // opening database connection to MySQL server
				             con = DriverManager.getConnection(url, user, password);
				             // getting Statement object to execute query
				             stmt = con.createStatement();
				             // executing SELECT query
				             rs = stmt.executeQuery(query);
				             while (rs.next()) {
				            	 combo.addItem(rs.getString("area"));
				             }
				         } catch (Exception exception) {
				             exception.printStackTrace();
				         } finally {
				             //close connection ,stmt and resultset here
				             try { con.close(); } catch(SQLException se) { /*can't do anything */ }
				             try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
				             try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
				         }
						 JTextField textf2 = new JTextField(20);
						 JButton buttonf = new JButton("��������");
						 buttonf.addActionListener(new ActionListener(){
						 public void actionPerformed(ActionEvent event) {
								 String query = "update data_on_cities set area ='" + textf2.getText() + "' where area = '" + combo.getSelectedItem() + "' limit 1";
								    try {
								        // opening database connection to MySQL server
								        con = DriverManager.getConnection(url, user, password);
								        // getting Statement object to execute query
								        stmt = con.createStatement();
								        // executing SELECT query
								        int rs = stmt.executeUpdate(query);
								        if (rs == 0) {
					                         JOptionPane.showMessageDialog(buttonf, "������");
					                     } else {
					                         JOptionPane.showMessageDialog(buttonf, "������� ������ ���� ������� ��������!");
					                     }
								   } catch (Exception exception) {
								        exception.printStackTrace();
								   } finally {
								        //close connection ,stmt and resultset here
								        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
								        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
								        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
								    }
						 }
					 });
						 frame1.add(combo);
						 frame1.add(textf2);
						 frame1.add(buttonf);
						 frame1.setVisible(true);
					 }
				 });
				 
				 JButton button5 = new JButton("�������� ���������� �������������");
				 button5.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent event) {
						 JFrame frame1 = new JFrame("��������� ������ �� ���������� �������������");
						 frame1.setSize(600,300);
						 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						 frame1.setLocationRelativeTo(null);
						 frame1.getContentPane().setBackground(new Color(153,255,204));
						 frame1.setLayout(new FlowLayout());
						 
						 JComboBox combo = new JComboBox();
						 String query = "select universities from data_on_cities";
				         try {
				             // opening database connection to MySQL server
				             con = DriverManager.getConnection(url, user, password);
				             // getting Statement object to execute query
				             stmt = con.createStatement();
				             // executing SELECT query
				             rs = stmt.executeQuery(query);
				             while (rs.next()) {
				            	 combo.addItem(rs.getString("universities"));
				             }
				         } catch (Exception exception) {
				             exception.printStackTrace();
				         } finally {
				             //close connection ,stmt and resultset here
				             try { con.close(); } catch(SQLException se) { /*can't do anything */ }
				             try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
				             try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
				         }
						 JTextField textf2 = new JTextField(20);
						 JButton buttonf = new JButton("��������");
						 buttonf.addActionListener(new ActionListener(){
						 public void actionPerformed(ActionEvent event) {
								 String query = "update data_on_cities set universities ='" + textf2.getText() + "' where universities = '" + combo.getSelectedItem() + "' limit 1";
								    try {
								        // opening database connection to MySQL server
								        con = DriverManager.getConnection(url, user, password);
								        // getting Statement object to execute query
								        stmt = con.createStatement();
								        // executing SELECT query
								        int rs = stmt.executeUpdate(query);
								        if (rs == 0) {
					                         JOptionPane.showMessageDialog(buttonf, "������");
					                     } else {
					                         JOptionPane.showMessageDialog(buttonf, "���������� ������������� ���� ������� ��������!");
					                     }
								   } catch (Exception exception) {
								        exception.printStackTrace();
								   } finally {
								        //close connection ,stmt and resultset here
								        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
								        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
								        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
								    }
						 }
					 });
						 frame1.add(combo);
						 frame1.add(textf2);
						 frame1.add(buttonf);
						 frame1.setVisible(true);
					 }
				 });
				 
				 JButton button6 = new JButton("�������� �������� ���������������������");
				 button6.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent event) {
						 JFrame frame1 = new JFrame("��������� ������ �� �������� ���������������������");
						 frame1.setSize(600,300);
						 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						 frame1.setLocationRelativeTo(null);
						 frame1.getContentPane().setBackground(new Color(153,255,204));
						 frame1.setLayout(new FlowLayout());
						 
						 JComboBox combo = new JComboBox();
						 String query = "select sightseeing from data_on_cities";
				         try {
				             // opening database connection to MySQL server
				             con = DriverManager.getConnection(url, user, password);
				             // getting Statement object to execute query
				             stmt = con.createStatement();
				             // executing SELECT query
				             rs = stmt.executeQuery(query);
				             while (rs.next()) {
				            	 combo.addItem(rs.getString("sightseeing"));
				             }
				         } catch (Exception exception) {
				             exception.printStackTrace();
				         } finally {
				             //close connection ,stmt and resultset here
				             try { con.close(); } catch(SQLException se) { /*can't do anything */ }
				             try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
				             try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
				         }
						 JTextField textf2 = new JTextField(20);
						 JButton buttonf = new JButton("��������");
						 buttonf.addActionListener(new ActionListener(){
						 public void actionPerformed(ActionEvent event) {
								 String query = "update data_on_cities set sightseeing ='" + textf2.getText() + "' where sightseeing = '" + combo.getSelectedItem() + "' limit 1";
								    try {
								        // opening database connection to MySQL server
								        con = DriverManager.getConnection(url, user, password);
								        // getting Statement object to execute query
								        stmt = con.createStatement();
								        // executing SELECT query
								        int rs = stmt.executeUpdate(query);
								        if (rs == 0) {
					                         JOptionPane.showMessageDialog(buttonf, "������");
					                     } else {
					                         JOptionPane.showMessageDialog(buttonf, "������ ��������������������� ���� ������� ��������!");
					                     }
								   } catch (Exception exception) {
								        exception.printStackTrace();
								   } finally {
								        //close connection ,stmt and resultset here
								        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
								        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
								        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
								    }
						 }
					 });
						 frame1.add(combo);
						 frame1.add(textf2);
						 frame1.add(buttonf);
						 frame1.setVisible(true);
					 }
				 });
				 
				 frame1.add(button1);
				 frame1.add(button2);
				 frame1.add(button3);
				 frame1.add(button4);
				 frame1.add(button5);
				 frame1.add(button6);
				 frame1.setVisible(true);
			 }
		 });
		 
		 JMenuItem udald = new JMenuItem("������� ������");
		 JMenuItem naiti = new JMenuItem("����� ������");
		 Operat.add(vvestid);
		 Operat.add(izmend);
		 Operat.add(udald);
		 Operat.add(naiti);

		 JMenu Otchet = new JMenu("������");
		 JMenuItem otch1 = new JMenuItem("����� 1");
		 JMenuItem otch2 = new JMenuItem("����� 2");
		 JMenuItem otch3 = new JMenuItem("����� 3");
		 Otchet.add(otch1);
		 Otchet.add(otch2);
		 Otchet.add(otch3);

		 JMenuItem Exit = new JMenuItem("�����");	
		 Exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			 }
		 });

		 frame.setJMenuBar(menuBar);
		 menuBar.add(Sprav);
		 menuBar.add(Operat);
		 menuBar.add(Otchet);
		 menuBar.add(Exit);
		 frame.setVisible(true);

	 }

	
	 public static void main(String args[]) {
		 new example();
//         String query = "select count(*) from cities";
//         try {
//             // opening database connection to MySQL server
//             con = DriverManager.getConnection(url, user, password);
//
//             // getting Statement object to execute query
//             stmt = con.createStatement();
//
//             // executing SELECT query
//             rs = stmt.executeQuery(query);
//
//             while (rs.next()) {
//                 int count = rs.getInt(1);
//                 System.out.println("Total number of books in the table : " + count);
//             }
//
//         } catch (Exception exception) {
//             exception.printStackTrace();
//         } finally {
//             //close connection ,stmt and resultset here
//             try { con.close(); } catch(SQLException se) { /*can't do anything */ }
//             try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
//             try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
//        }
     }
}