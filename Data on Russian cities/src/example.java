import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		 frame.setTitle("Данные о достопримечательностях крупных городов России");
		 frame.setSize(600,300);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setLocationRelativeTo(null);
		 frame.getContentPane().setBackground(new Color(204,153,255));
		 frame.setLayout(new GridBagLayout());
		
		 JMenuBar menuBar = new JMenuBar();
		 JMenu Sprav = new JMenu("Справочники");
		 Sprav.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
		 Sprav.setPreferredSize(new Dimension(110, 50));
		 JMenu gorod = new JMenu("Справочник городов");
		 gorod.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
		 gorod.setPreferredSize(new Dimension(110, 35));
		 JMenuItem dobav1 = new JMenuItem("Добавить");
		 dobav1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
		 dobav1.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent event) {
			 JFrame frame1 = new JFrame("Добавление данных в справочник городов");
			 frame1.setSize(600,300);
			 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
			 frame1.setLocationRelativeTo(null);
			 frame1.getContentPane().setBackground(new Color(204,153,255));
			 frame1.setLayout(new GridBagLayout());
				
			 JLabel label = new JLabel("Введите город, который хотите добавить: ");
			 label.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
			 JTextField textf = new JTextField(15);
			 JButton buttonf = new JButton("Добавить");
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
		                         JOptionPane.showMessageDialog(buttonf, "Новый город успешно добавлен в таблицу!");
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
			 	 frame1.add(label);
				 frame1.add(textf);
				 frame1.add(buttonf);
				 frame1.setVisible(true);
			 }
		 });
		
		 JMenuItem izmen1 = new JMenuItem("Изменить");
		 izmen1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
		 izmen1.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Изменение данных в справочнике городов");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 frame1.setLayout(new FlowLayout());
				
				 JLabel label1 = new JLabel("Выберите запись, которую хотите изменить: ");
				 label1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
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
		         JLabel label2 = new JLabel("Введите новые данные для названия города: ");
		         label2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
				 JTextField textf2 = new JTextField(15);
				 JButton buttonf = new JButton("Изменить");
				 buttonf.setPreferredSize(new Dimension(120, 40));
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
			                         JOptionPane.showMessageDialog(buttonf, "Город, который вы хотите изменить, не был найден");
			                     } else {
			                         JOptionPane.showMessageDialog(buttonf, "Данный город успешно изменен!");
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
				 	 frame1.add(label1);
					 frame1.add(combo);
					 frame1.add(label2);
					 frame1.add(textf2);
					 frame1.add(buttonf);
					 frame1.setVisible(true);
				 }
			 });
		 JMenuItem udal1 = new JMenuItem("Удалить");
		 udal1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
		 udal1.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Удаление данных из справочника городов");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 frame1.setLayout(new GridBagLayout());
				
				 JLabel label = new JLabel("Выберите город, который хотите удалить: ");
				 label.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
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
				 JButton buttonf = new JButton("Удалить");
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
			                         JOptionPane.showMessageDialog(buttonf, "Город, который вы хотите удалить, не был найден");
			                     } else {
			                         JOptionPane.showMessageDialog(buttonf, "Данный город успешно удален!");
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
				 	 frame1.add(label);
					 frame1.add(combo);
					 frame1.add(buttonf);
					 frame1.setVisible(true);
				 }
			 });
		 JMenuItem poisk1 = new JMenuItem("Поиск");
		 poisk1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
		 poisk1.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Поиск данных из справочника городов");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 frame1.setLayout(new FlowLayout());
				
				 JLabel label1 = new JLabel("Введите в одно из полей информацию - название или же код города: ");
				 label1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,17));
				 JTextField textf1 = new JTextField(20);
				 JLabel label2 = new JLabel();
				 JButton buttonf1 = new JButton("Найти по названию города");
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
			                    	 label2.setText("Результат поиска: "+ "  Код города: " + a + " Название: " + b);
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
				 JButton buttonf2 = new JButton("Найти по коду");
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
			                    	 label2.setText("Результат поиска: "+ "  Код города: " + a + " Название: " + b);
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
					 frame1.add(label1);
					 frame1.add(textf1);
					 frame1.add(buttonf1);
					 frame1.add(textf2);
					 frame1.add(buttonf2);
					 frame1.add(label2);
					 frame1.setVisible(true);
				 }
			 });
		
		
		 JMenu oblast = new JMenu("Справочник областей");
		 oblast.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
		 oblast.setPreferredSize(new Dimension(150, 35));
		 JMenuItem dobav2 = new JMenuItem("Добавить");
		 dobav2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
		 dobav2.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Добавление данных в справочник областей");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 frame1.setLayout(new GridBagLayout());
				
				 JLabel label = new JLabel("Введите область, которую хотите добавить: ");
				 label.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
				 JTextField textf = new JTextField(13);
				 JButton buttonf = new JButton("Добавить");
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
			                         JOptionPane.showMessageDialog(buttonf, "Новая область успешно добавлена в таблицу!");
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
				 	 frame1.add(label);
					 frame1.add(textf);
					 frame1.add(buttonf);
					 frame1.setVisible(true);
				 }
			 });
		 JMenuItem izmen2 = new JMenuItem("Изменить");
		 izmen2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
		 izmen2.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Изменение данных в справочнике областей");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 frame1.setLayout(new FlowLayout());
					
				 JLabel label1 = new JLabel("Выберите запись, которую хотите изменить: ");
				 label1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
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
		         JLabel label2 = new JLabel("Введите новые данные для названия области: ");
		         label2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
				 JTextField textf2 = new JTextField(15);
				 JButton buttonf = new JButton("Изменить");
				 buttonf.setPreferredSize(new Dimension(120, 40));
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
			                         JOptionPane.showMessageDialog(buttonf, "Область, которую вы хотите изменить, не была найдена");
			                     } else {
			                         JOptionPane.showMessageDialog(buttonf, "Данная область успешно изменена!");
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
				 	 frame1.add(label1);
				 	 frame1.add(combo);
				 	 frame1.add(label2);
				 	 frame1.add(textf2);
				 	 frame1.add(buttonf);
					 frame1.setVisible(true);
				 }
			 });
		 JMenuItem udal2 = new JMenuItem("Удалить");
		 udal2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
		 udal2.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Удаление данных из справочника областей");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 frame1.setLayout(new GridBagLayout());
				
				 JLabel label = new JLabel("Выберите область, которую хотите удалить: ");
				 label.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
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
				 JButton buttonf = new JButton("Удалить");
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
			                         JOptionPane.showMessageDialog(buttonf, "Область, которую вы хотите удалить, не была найдена");
			                     } else {
			                         JOptionPane.showMessageDialog(buttonf, "Данная область успешно удалена!");
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
				 	 frame1.add(label);
					 frame1.add(combo);
					 frame1.add(buttonf);
					 frame1.setVisible(true);
				 }
			 });
		 JMenuItem poisk2 = new JMenuItem("Поиск");
		 poisk2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
		 poisk2.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Поиск данных из справочника областей");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 frame1.setLayout(new FlowLayout());
				
				 JLabel label1 = new JLabel("Введите в одно из полей информацию - название или же код области: ");
				 label1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,17));
				 JTextField textf1 = new JTextField(20);
				 JLabel label2 = new JLabel();
				 JButton buttonf1 = new JButton("Найти по названию области");
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
			                    	 label2.setText("Результат поиска: "+ "  Код области: " + a + " Название: " + b);
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
				 JButton buttonf2 = new JButton("Найти по коду");
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
			                    	 label2.setText("Результат поиска: "+ "  Код области: " + a + " Название: " + b);
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
					 frame1.add(label1);
					 frame1.add(textf1);
					 frame1.add(buttonf1);
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

		 JMenu Operat = new JMenu("Оперативные данные");
		 Operat.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
		 Operat.setPreferredSize(new Dimension(170, 50));
		 JMenuItem vvestid = new JMenuItem("Ввести данные");
		 vvestid.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
		 vvestid.setPreferredSize(new Dimension(167, 35));
		 vvestid.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Добавление оперативных данных");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 frame1.setLayout(new GridLayout(7, 2, 0, 5));
				
				 JLabel label1 = new JLabel(" Введите код области: ");
				 label1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
				 JTextField textf1 = new JTextField(20);
				 JLabel label2 = new JLabel(" Введите код города: ");
				 label2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
				 JTextField textf2 = new JTextField(20);
				 JLabel label3 = new JLabel(" Введите численность населения(в млн): ");
				 label3.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
				 JTextField textf3 = new JTextField(20);
				 JLabel label4 = new JLabel(" Введите площадь города(в кв.км.): ");
				 label4.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
				 JTextField textf4 = new JTextField(20);
				 JLabel label5 = new JLabel(" Введите количество университетов: ");
				 label5.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
				 JTextField textf5 = new JTextField(20);
				 JLabel label6 = new JLabel(" Введите достопримечательность: ");
				 label6.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
				 JTextField textf6 = new JTextField(20);
				 JButton buttonf = new JButton("Добавить");
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
			                         JOptionPane.showMessageDialog(buttonf, "Новые данные успешно добавлены в таблицу!");
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
				 	 frame1.add(label1);
					 frame1.add(textf1);
					 frame1.add(label2);
					 frame1.add(textf2);
					 frame1.add(label3);
					 frame1.add(textf3);
					 frame1.add(label4);
					 frame1.add(textf4);
					 frame1.add(label5);
					 frame1.add(textf5);
					 frame1.add(label6);
					 frame1.add(textf6);
					 frame1.add(buttonf);
					 frame1.setVisible(true);
				 }
			 });
		 
		 JMenuItem izmend = new JMenuItem("Изменить данные");
		 izmend.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
		 izmend.setPreferredSize(new Dimension(167, 35));
		 izmend.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Изменение оперативных данных");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 frame1.setLayout(new FlowLayout());
				 
				 JLabel label = new JLabel("Выберите критерий, по которому хотите произвести изменение: ");
				 label.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,17));
				 JButton button1 = new JButton("Изменить код региона");
				 button1.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent event) {
						 JFrame frame1 = new JFrame("Изменение данных по коду региона");
						 frame1.setSize(600,300);
						 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
						 frame1.setLocationRelativeTo(null);
						 frame1.getContentPane().setBackground(new Color(204,153,255));
						 frame1.setLayout(new FlowLayout());
						 
						 JLabel label1 = new JLabel(" Выберите код региона, который хотите изменить: ");
						 label1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
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
				         JLabel label2 = new JLabel(" Введите новые данные для кода региона: ");
				         label2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
						 JTextField textf2 = new JTextField(15);
						 JButton buttonf = new JButton("Изменить");
						 buttonf.setPreferredSize(new Dimension(120, 40));
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
					                         JOptionPane.showMessageDialog(buttonf, "Ошибка");
					                     } else {
					                         JOptionPane.showMessageDialog(buttonf, "Данный код региона был успешно изменен!");
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
						 frame1.add(label1);
						 frame1.add(combo);
						 frame1.add(label2);
						 frame1.add(textf2);
						 frame1.add(buttonf);
						 frame1.setVisible(true);
					 }
				 });
				 
				 JButton button2 = new JButton("Изменить код города");
				 button2.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent event) {
						 JFrame frame1 = new JFrame("Изменение данных по коду города");
						 frame1.setSize(600,300);
						 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
						 frame1.setLocationRelativeTo(null);
						 frame1.getContentPane().setBackground(new Color(204,153,255));
						 frame1.setLayout(new FlowLayout());
						 
						 JLabel label1 = new JLabel(" Выберите код города, который хотите изменить: ");
						 label1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
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
				         JLabel label2 = new JLabel(" Введите новые данные для кода города: ");
				         label2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
						 JTextField textf2 = new JTextField(15);
						 JButton buttonf = new JButton("Изменить");
						 buttonf.setPreferredSize(new Dimension(120, 40));
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
					                         JOptionPane.showMessageDialog(buttonf, "Ошибка");
					                     } else {
					                         JOptionPane.showMessageDialog(buttonf, "Данный код города был успешно изменен!");
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
						 frame1.add(label1);
						 frame1.add(combo);
						 frame1.add(label2);
						 frame1.add(textf2);
						 frame1.add(buttonf);
						 frame1.setVisible(true);
					 }
				 });
				 
				 JButton button3 = new JButton("Изменить численность населения");
				 button3.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent event) {
						 JFrame frame1 = new JFrame("Изменение данных по численности населения");
						 frame1.setSize(600,300);
						 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
						 frame1.setLocationRelativeTo(null);
						 frame1.getContentPane().setBackground(new Color(204,153,255));
						 frame1.setLayout(new FlowLayout());
						 
						 JLabel label1 = new JLabel(" Выберите численность населения, которую хотите изменить: ");
						 label1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
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
				         JLabel label2 = new JLabel("Введите новые данные для численности населения: ");
				         label2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
						 JTextField textf2 = new JTextField(15);
						 JButton buttonf = new JButton("Изменить");
						 buttonf.setPreferredSize(new Dimension(120, 40));
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
					                         JOptionPane.showMessageDialog(buttonf, "Ошибка");
					                     } else {
					                         JOptionPane.showMessageDialog(buttonf, "Численность населения была успешно изменена!");
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
						 frame1.add(label1);
						 frame1.add(combo);
						 frame1.add(label2);
						 frame1.add(textf2);
						 frame1.add(buttonf);
						 frame1.setVisible(true);
					 }
				 });
				 
				 JButton button4 = new JButton("Изменить площадь города");
				 button4.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent event) {
						 JFrame frame1 = new JFrame("Изменение данных по площади города");
						 frame1.setSize(600,300);
						 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
						 frame1.setLocationRelativeTo(null);
						 frame1.getContentPane().setBackground(new Color(204,153,255));
						 frame1.setLayout(new FlowLayout());
						 
						 JLabel label1 = new JLabel(" Выберите площадь города, которую хотите изменить: ");
						 label1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
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
				         JLabel label2 = new JLabel("Введите новые данные для площади города: ");
				         label2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
						 JTextField textf2 = new JTextField(15);
						 JButton buttonf = new JButton("Изменить");
						 buttonf.setPreferredSize(new Dimension(120, 40));
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
					                         JOptionPane.showMessageDialog(buttonf, "Ошибка");
					                     } else {
					                         JOptionPane.showMessageDialog(buttonf, "Площадь города была успешно изменена!");
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
						 frame1.add(label1);
						 frame1.add(combo);
						 frame1.add(label2);
						 frame1.add(textf2);
						 frame1.add(buttonf);
						 frame1.setVisible(true);
					 }
				 });
				 
				 JButton button5 = new JButton("Изменить количество университетов");
				 button5.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent event) {
						 JFrame frame1 = new JFrame("Изменение данных по количеству университетов");
						 frame1.setSize(600,300);
						 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
						 frame1.setLocationRelativeTo(null);
						 frame1.getContentPane().setBackground(new Color(204,153,255));
						 frame1.setLayout(new FlowLayout());
						 
						 JLabel label1 = new JLabel(" Выберите количество университетов, которое хотите изменить: ");
						 label1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
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
				         JLabel label2 = new JLabel("Введите новые данные для количества университетов: ");
				         label2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
						 JTextField textf2 = new JTextField(13);
						 JButton buttonf = new JButton("Изменить");
						 buttonf.setPreferredSize(new Dimension(120, 40));
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
					                         JOptionPane.showMessageDialog(buttonf, "Ошибка");
					                     } else {
					                         JOptionPane.showMessageDialog(buttonf, "Количество университетов было успешно изменено!");
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
						 frame1.add(label1);
						 frame1.add(combo);
						 frame1.add(label2);
						 frame1.add(textf2);
						 frame1.add(buttonf);
						 frame1.setVisible(true);
					 }
				 });
				 
				 JButton button6 = new JButton("Изменить название достопримечательности");
				 button6.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent event) {
						 JFrame frame1 = new JFrame("Изменение данных по названию достопримечательности");
						 frame1.setSize(600,300);
						 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
						 frame1.setLocationRelativeTo(null);
						 frame1.getContentPane().setBackground(new Color(204,153,255));
						 frame1.setLayout(new FlowLayout());
						 
						 JLabel label1 = new JLabel(" Выберите достопримечательность: ");
						 label1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
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
				         JLabel label2 = new JLabel("Введите новые данные для достопримечательности: ");
				         label2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
						 JTextField textf2 = new JTextField(15);
						 JButton buttonf = new JButton("Изменить");
						 buttonf.setPreferredSize(new Dimension(120, 40));
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
					                         JOptionPane.showMessageDialog(buttonf, "Ошибка");
					                     } else {
					                         JOptionPane.showMessageDialog(buttonf, "Данная достопримечательность была успешно изменена!");
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
						 frame1.add(label1);
						 frame1.add(combo);
						 frame1.add(label2);
						 frame1.add(textf2);
						 frame1.add(buttonf);
						 frame1.setVisible(true);
					 }
				 });
				 
				 frame1.add(label);
				 frame1.add(button1);
				 frame1.add(button2);
				 frame1.add(button3);
				 frame1.add(button4);
				 frame1.add(button5);
				 frame1.add(button6);
				 frame1.setVisible(true);
			 }
		 });
		 
		 JMenuItem udald = new JMenuItem("Удалить данные");
		 udald.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
		 udald.setPreferredSize(new Dimension(167, 35));
		 udald.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Удаление данных из главной таблицы");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 frame1.setLayout(new GridBagLayout());
				
				 JLabel label = new JLabel("Выберите запись, которую хотите удалить: ");
				 label.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
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
				 JButton buttonf = new JButton("Удалить");
				 buttonf.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent event) {
						 String query = "delete from data_on_cities where sightseeing = '" + combo.getSelectedItem() + "' limit 1";
						    try {
						        // opening database connection to MySQL server
						        con = DriverManager.getConnection(url, user, password);
						        // getting Statement object to execute query
						        stmt = con.createStatement();
						        // executing SELECT query
						        int rs = stmt.executeUpdate(query);
						        if (rs == 0) {
			                         JOptionPane.showMessageDialog(buttonf, "Ошибка");
			                     } else {
			                         JOptionPane.showMessageDialog(buttonf, "Данная запись о достопримечательности успешно удалена!");
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
				 	 frame1.add(label);
					 frame1.add(combo);
					 frame1.add(buttonf);
					 frame1.setVisible(true);
			 }
		 });
			 
		 JMenuItem naiti = new JMenuItem("Найти данные");
		 naiti.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
		 naiti.setPreferredSize(new Dimension(167, 35));
		 naiti.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Поиск данных из главной таблицы");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 frame1.setLayout(new FlowLayout());
				 
				 JLabel label1 = new JLabel("Введите достопримечательность, информацию о которой хотите найти: ");
				 label1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
				 JTextField textf1 = new JTextField(20);
				 JLabel label2 = new JLabel();
				 JButton buttonf1 = new JButton("Найти");
				 buttonf1.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent event) {
						 String query = "select * from data_on_cities where sightseeing = '" + textf1.getText() + "'";
						    try {
						        // opening database connection to MySQL server
						        con = DriverManager.getConnection(url, user, password);
						        // getting Statement object to execute query
						        stmt = con.createStatement();
						        // executing SELECT query
						        rs = stmt.executeQuery(query);
			                    while (rs.next()) {
			                    	 int a = rs.getInt(2);
			                    	 int v = rs.getInt(3);
			                    	 double c = rs.getDouble(4);
			                    	 int f = rs.getInt(5);
			                    	 int g = rs.getInt(6);
			                    	 String b = rs.getString(7);
			                    	 label2.setText("<html>" + "  Id региона: " + a + " Id города: " + v + " Население: " + c + "<br>" + " Площадь: " + f + " Количество университетов: " + g + " Достопримечательность: " + b + "</html>");
			                    	 label2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
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
				 frame1.add(label1);
				 frame1.add(textf1);
				 frame1.add(buttonf1);
				 frame1.add(label2);
				 frame1.setVisible(true);
			 }
		 });
		 
		 Operat.add(vvestid);
		 Operat.add(izmend);
		 Operat.add(udald);
		 Operat.add(naiti);

		 JMenu Otchet = new JMenu("Отчеты");
		 Otchet.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
		 Otchet.setPreferredSize(new Dimension(80, 50));
		 JMenuItem otch1 = new JMenuItem("Отчет 1");
		 otch1.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Отчет");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 JLabel label = new JLabel("Полный отчет о достопримечательностях");
				 label.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
				 JPanel panel = new JPanel();
				 String SQL = "select data_on_cities.id, regions.region, cities.city, data_on_cities.population, data_on_cities.area, data_on_cities.universities, data_on_cities.sightseeing from data_on_cities left join regions on data_on_cities.region_id = regions.id left join cities on data_on_cities.city_id = cities.id";
				 try {
				 con = DriverManager.getConnection(url, user, password);
				 stmt = con.createStatement();
				 rs = stmt.executeQuery(SQL);
				 String n = "",e = "",g = "",h = "", a = "", j = "", f = "";      

				 DefaultTableModel model;
				 model = new DefaultTableModel(); 
				 JTable jTable1 = new  JTable(model);
				 model.addColumn("id");
				 model.addColumn("region");
				 model.addColumn("city");
				 model.addColumn("population");
				 model.addColumn("area");
				 model.addColumn("universities");
				 model.addColumn("sightseeing");
				 while(rs.next())  
				 {
				     n = rs.getString("id");    
				     e = rs.getString("region");
				     g = rs.getString("city");
				     h = rs.getString("population"); 
				     a = rs.getString("area"); 
				     j = rs.getString("universities"); 
				     f = rs.getString("sightseeing"); 
				     model.addRow(new Object[]{n,e,g,h,a,j,f});
				 }
				 panel.add(label);
				 panel.add(new JScrollPane(jTable1));
				 jTable1.setPreferredScrollableViewportSize(jTable1.getPreferredSize());
				 jTable1.getColumn("id").setMaxWidth(20);
				 jTable1.getColumn("population").setMaxWidth(50);
				 jTable1.getColumn("area").setMaxWidth(50);
				 jTable1.getColumn("universities").setMaxWidth(50);
				 } catch (Exception exception) {
				        exception.printStackTrace();
				   } finally {
				        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
				    }
				 frame1.add(panel);
				 frame1.setVisible(true);
			 }
		 });
		 
		 otch1.setPreferredSize(new Dimension(77, 35));
		 otch1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
		 JMenuItem otch2 = new JMenuItem("Отчет 2");
		 otch2.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Отчет");
				 frame1.setSize(600,335);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 JLabel label = new JLabel("Отчет о количестве достопримечательностей в каждом городе");
				 label.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
				 JPanel panel = new JPanel();
				 String SQL = "select cities.id, city, count(sightseeing) as 'количество достопримечательностей' from cities left join data_on_cities on data_on_cities.city_id = cities.id group by city";
				 try {
				 con = DriverManager.getConnection(url, user, password);
				 stmt = con.createStatement();
				 rs = stmt.executeQuery(SQL);
				 String n = "",e = "",g = "";      

				 DefaultTableModel model;
				 model = new DefaultTableModel(); 
				 JTable jTable1 = new  JTable(model);
				 model.addColumn("id");
				 model.addColumn("city");
				 model.addColumn("quantity");
				 while(rs.next())  
				 {
				     n = rs.getString("id");    
				     e = rs.getString("city");
				     g = rs.getString("количество достопримечательностей");
				     model.addRow(new Object[]{n,e,g});
				 }
				 panel.add(label);
				 panel.add(new JScrollPane(jTable1));
				 jTable1.setPreferredScrollableViewportSize(jTable1.getPreferredSize());
				 jTable1.getColumn("id").setMaxWidth(20);
				 jTable1.getColumn("quantity").setMaxWidth(120);
				 } catch (Exception exception) {
				        exception.printStackTrace();
				   } finally {
				        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
				    }
				 frame1.add(panel);
				 frame1.setVisible(true);
			 }
		 });
		 
		 otch2.setPreferredSize(new Dimension(77, 35));
		 otch2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
		 JMenuItem otch3 = new JMenuItem("Отчет 3");
		 otch3.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Отчет");
				 frame1.setSize(600,335);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 JLabel label = new JLabel("Отчет о площади каждого города");
				 label.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,16));
				 JPanel panel = new JPanel();
				 String SQL = "select cities.id, cities.city, data_on_cities.area from data_on_cities left join cities on data_on_cities.city_id = cities.id group by city";
				 try {
				 con = DriverManager.getConnection(url, user, password);
				 stmt = con.createStatement();
				 rs = stmt.executeQuery(SQL);
				 String n = "",e = "",g = "";      

				 DefaultTableModel model;
				 model = new DefaultTableModel(); 
				 JTable jTable1 = new  JTable(model);
				 jTable1.setPreferredSize(new Dimension(380, 173));
				 model.addColumn("id");
				 model.addColumn("city");
				 model.addColumn("area");
				 while(rs.next())  
				 {   
					 n = rs.getString("id");
				     e = rs.getString("city");
				     g = rs.getString("area");
				     model.addRow(new Object[]{n,e,g});
				 }
				 panel.add(label);
				 panel.add(new JScrollPane(jTable1));
				 jTable1.setPreferredScrollableViewportSize(jTable1.getPreferredSize());
				 jTable1.getColumn("id").setMaxWidth(23);
				 jTable1.getColumn("area").setMaxWidth(120);
				 } catch (Exception exception) {
				        exception.printStackTrace();
				   } finally {
				        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
				    }
				 frame1.add(panel);
				 frame1.setVisible(true);
			 }
		 });
		 otch3.setPreferredSize(new Dimension(77, 35));
		 otch3.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
		 Otchet.add(otch1);
		 Otchet.add(otch2);
		 Otchet.add(otch3);

		 JMenu Prosmotr = new JMenu("Просмотр таблиц");
		 Prosmotr.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
		 Prosmotr.setPreferredSize(new Dimension(140, 50));
		 JMenuItem pros1 = new JMenuItem("Справочник областей");
		 pros1.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Справочник областей");
				 frame1.setSize(600,305);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 JPanel panel = new JPanel();
				 String SQL = "select * from regions";
				 try {
				 con = DriverManager.getConnection(url, user, password);
				 stmt = con.createStatement();
				 rs = stmt.executeQuery(SQL);
				 String n = "",e = "";      

				 DefaultTableModel model;
				 model = new DefaultTableModel(); 
				 JTable jTable1 = new  JTable(model);
				 model.addColumn("id");
				 model.addColumn("region");
				 while(rs.next())  
				 {
				     n = rs.getString("id");    
				     e = rs.getString("region");   
				     model.addRow(new Object[]{n,e});
				 }
				 panel.add(new JScrollPane(jTable1));
				 } catch (Exception exception) {
				        exception.printStackTrace();
				   } finally {
				        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
				    }
				 frame1.add(panel);
				 frame1.setVisible(true);
			 }
		 });
		 pros1.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
		 pros1.setPreferredSize(new Dimension(137, 40));
		 JMenuItem pros2 = new JMenuItem("Справочник городов");
		 pros2.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Справочник городов");
				 frame1.setSize(600,305);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 JPanel panel = new JPanel();
				 String SQL = "select * from cities";
				 try {
				 con = DriverManager.getConnection(url, user, password);
				 stmt = con.createStatement();
				 rs = stmt.executeQuery(SQL);
				 String n = "",e = "";      

				 DefaultTableModel model;
				 model = new DefaultTableModel(); 
				 JTable jTable1 = new  JTable(model);
				 model.addColumn("id");
				 model.addColumn("city");
				 while(rs.next())  
				 {
				     n = rs.getString("id");    
				     e = rs.getString("city");   
				     model.addRow(new Object[]{n,e});
				 }
				 panel.add(new JScrollPane(jTable1));
				 } catch (Exception exception) {
				        exception.printStackTrace();
				   } finally {
				        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
				    }
				 frame1.add(panel);
				 frame1.setVisible(true);
			 }
		 });
		 pros2.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
		 pros2.setPreferredSize(new Dimension(137, 40));
		 JMenuItem pros3 = new JMenuItem("Главная таблица");
		 pros3.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame1 = new JFrame("Главная таблица");
				 frame1.setSize(600,300);
				 frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
				 frame1.setLocationRelativeTo(null);
				 frame1.getContentPane().setBackground(new Color(204,153,255));
				 JPanel panel = new JPanel();
				 String SQL = "select * from data_on_cities";
				 try {
				 con = DriverManager.getConnection(url, user, password);
				 stmt = con.createStatement();
				 rs = stmt.executeQuery(SQL);
				 String n = "",e = "",g = "",h = "", a = "", j = "", f = "";      

				 DefaultTableModel model;
				 model = new DefaultTableModel(); 
				 JTable jTable1 = new  JTable(model);
				 model.addColumn("id");
				 model.addColumn("region_id");
				 model.addColumn("city_id");
				 model.addColumn("population");
				 model.addColumn("area");
				 model.addColumn("universities");
				 model.addColumn("sightseeing");
				 while(rs.next())  
				 {
				     n = rs.getString("id");    
				     e = rs.getString("region_id");
				     g = rs.getString("city_id");
				     h = rs.getString("population"); 
				     a = rs.getString("area"); 
				     j = rs.getString("universities"); 
				     f = rs.getString("sightseeing"); 
				     model.addRow(new Object[]{n,e,g,h,a,j,f});
				 }
				 panel.add(new JScrollPane(jTable1));
				 jTable1.setPreferredScrollableViewportSize(jTable1.getPreferredSize());
				 jTable1.getColumn("id").setMaxWidth(20);
				 jTable1.getColumn("region_id").setMaxWidth(58);
				 jTable1.getColumn("city_id").setMaxWidth(57);
				 jTable1.getColumn("population").setMaxWidth(65);
				 jTable1.getColumn("area").setMaxWidth(40);
				 jTable1.getColumn("universities").setMaxWidth(85);
				 jTable1.getColumn("sightseeing").setMaxWidth(270);
				 } catch (Exception exception) {
				        exception.printStackTrace();
				   } finally {
				        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
				    }
				 frame1.add(panel);
				 frame1.setVisible(true);
			 }
		 });
		 pros3.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,13));
		 pros3.setPreferredSize(new Dimension(137, 40));
		 Prosmotr.add(pros1);
		 Prosmotr.add(pros2);
		 Prosmotr.add(pros3);

		 JMenu Exit = new JMenu("Выход");
		 Exit.setFont(new Font("Bahnschrift Light SemiCondensed",Font.PLAIN,15));
		 Exit.setPreferredSize(new Dimension(80, 50));
		 Exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			 }
		 });
 
		 frame.setJMenuBar(menuBar);
		 menuBar.add(Sprav);
		 menuBar.add(Operat);
		 menuBar.add(Otchet);
		 menuBar.add(Prosmotr);
		 menuBar.add(Exit);
		 frame.setVisible(true);
	 }

	
	 public static void main(String args[]) {
		 new example();
     }
}