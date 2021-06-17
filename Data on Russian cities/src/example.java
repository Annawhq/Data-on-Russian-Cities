import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class example extends JFrame {
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
		JMenu gorod = new JMenu("Справочник городов");
		JMenuItem dobav1 = new JMenuItem("Добавить");
		JMenuItem izmen1 = new JMenuItem("Изменить");
		JMenuItem udal1 = new JMenuItem("Удалить");
		JMenuItem poisk1 = new JMenuItem("Поиск");
		JMenu oblast = new JMenu("Справочник областей");
		JMenuItem dobav2 = new JMenuItem("Добавить");
		JMenuItem izmen2 = new JMenuItem("Изменить");
		JMenuItem udal2 = new JMenuItem("Удалить");
		JMenuItem poisk2 = new JMenuItem("Поиск");
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
		JMenuItem vvestid = new JMenuItem("Ввести данные");
		JMenuItem izmend = new JMenuItem("Изменить данные");
		JMenuItem udald = new JMenuItem("Удалить данные");
		JMenuItem naiti = new JMenuItem("Найти");
		Operat.add(vvestid);
		Operat.add(izmend);
		Operat.add(udald);
		Operat.add(naiti);
		
		
		JMenu Otchet = new JMenu("Отчеты");
		JMenuItem otch1 = new JMenuItem("Отчет 1");
		JMenuItem otch2 = new JMenuItem("Отчет 2");
		JMenuItem otch3 = new JMenuItem("Отчет 3");
		Otchet.add(otch1);
		Otchet.add(otch2);
		Otchet.add(otch3);
		
		JMenu Exit = new JMenu("Выход");
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
	
	
	public static void main(String[] args) {
		new example();
	}
}