package com;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.io.File;
import java.util.ArrayList;
import java.awt.Cursor;
import javax.swing.JFileChooser;
import org.jvnet.substance.SubstanceLookAndFeel;
import java.awt.Cursor;
import org.jfree.ui.RefineryUtilities;
public class Main extends JFrame implements Runnable{
	static JTextArea area;
	JScrollPane jsp;
	JPanel p1,p2,p3;
	JLabel l1;
	Font f1,f2;
	JButton b1,b2,b3,b4,b5,b6;
	Thread thread;
	String title = "An Intelligent Software Defined Network Controller For Preventing Distributed<br/>Denial Of Service Attack";
	File train,test;
	JFileChooser chooser,chooser1;

public Main(){
	super("DDOS Detection Detection");
	p1 = new JPanel();
	p1.setBackground(new Color(255,255,170));
	p1.setPreferredSize(new Dimension(600,80));
	l1 = new JLabel("<HTML><BODY><CENTER>"+title.toUpperCase()+"</center></body></html>");
	l1.setForeground(Color.black);
	l1.setFont(new Font("Times New Roman",Font.BOLD,18));
	p1.add(l1);

	chooser = new JFileChooser(new File("dataset"));
	

	p2 = new JPanel();
	p2.setPreferredSize(new Dimension(420,150));
	f2 = new Font("Times New Roman",Font.BOLD,16);
	
	b1 = new JButton("Upload DDOS Train Dataset");
	b1.setFont(f2);
	p2.add(b1);
	b1.setPreferredSize(new Dimension(400,40));
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			area.setText("");
			int option = chooser.showOpenDialog(Main.this);
			if(option == chooser.APPROVE_OPTION){
				train = chooser.getSelectedFile();
				Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
				setCursor(hourglassCursor);
				Convert.convert(train.getPath(),"com/train.arff");
				area.append(train.getName()+" DDOS train dataset loaded\n");
				Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(normalCursor);
			}
		}
	});

	b2 = new JButton("Run SVM Algorithm");
	b2.setFont(f2);
	b2.setPreferredSize(new Dimension(400,40));
	p2.add(b2);
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			int option = chooser.showOpenDialog(Main.this);
			if(option == chooser.APPROVE_OPTION){
				test = chooser.getSelectedFile();
				Convert.convert(test.getPath(),"com/test.arff");
				area.append(test.getName()+" test dataset loaded\n");
				Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
				setCursor(hourglassCursor);
				try{
					SVM.train("com/train.arff");
					SVM.test("com/test.arff");
				}catch(Exception e){
					e.printStackTrace();
				}
				Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(normalCursor);
			}
			
		}
	});

	b3 = new JButton("Run KNN Algorithm");
	b3.setFont(f2);
	b3.setPreferredSize(new Dimension(400,40));
	p2.add(b3);
	b3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			int option = chooser.showOpenDialog(Main.this);
			if(option == chooser.APPROVE_OPTION){
				test = chooser.getSelectedFile();
				area.append(test.getName()+" test dataset loaded\n");
				Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
				setCursor(hourglassCursor);
				try{
					KNN.train("com/train.arff");
					KNN.test("com/test.arff");
				}catch(Exception e){
					e.printStackTrace();
				}
				Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(normalCursor);
			}
		}
	});

	b4 = new JButton("Run Naive Bayes Algorithm");
	b4.setFont(f2);
	b4.setPreferredSize(new Dimension(400,40));
	p2.add(b4);
	b4.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			int option = chooser.showOpenDialog(Main.this);
			if(option == chooser.APPROVE_OPTION){
				test = chooser.getSelectedFile();
				area.append(test.getName()+" test dataset loaded\n");
				Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
				setCursor(hourglassCursor);
				try{
					NaiveBayesAlg.train("com/train.arff");
					NaiveBayesAlg.test("com/test.arff");
				}catch(Exception e){
					e.printStackTrace();
				}
				Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(normalCursor);
			}
		}
	});

	b5 = new JButton("Accuracy Graph");
	b5.setFont(f2);
	b5.setPreferredSize(new Dimension(400,40));
	p2.add(b5);
	b5.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Chart chart1 = new Chart("Accuracy Graph");
			chart1.pack();
			RefineryUtilities.centerFrameOnScreen(chart1);
			chart1.setVisible(true);
		}
	});

	b6 = new JButton("Exit");
	b6.setFont(f2);
	b6.setPreferredSize(new Dimension(400,40));
	p2.add(b6);
	b6.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			System.exit(0);
		}
	});



	
	p3 = new JPanel();
	p3.setLayout(new BorderLayout());
	area = new JTextArea();
	area.setFont(f2);
	area.setEditable(false);
	area.setLineWrap(true);
	jsp = new JScrollPane(area);
	jsp.getViewport().setBackground(Color.white);
	
	p3.add(jsp,BorderLayout.CENTER);
	
	//p3.setPreferredSize(new Dimension(600,600));

	getContentPane().add(p1,BorderLayout.NORTH);
	getContentPane().add(p2,BorderLayout.SOUTH);
	getContentPane().add(p3,BorderLayout.CENTER);
	thread = new Thread(this);
	thread.start();
}
public void run(){
	while(true){
		try{
			l1.setVerticalAlignment(JLabel.TOP);
			thread.sleep(500);
			l1.setHorizontalAlignment(JLabel.RIGHT);
			thread.sleep(500);
			l1.setHorizontalAlignment(JLabel.LEFT);
			thread.sleep(500);
			l1.setHorizontalAlignment(JLabel.CENTER);
			thread.sleep(500);
			l1.setVerticalAlignment(JLabel.BOTTOM);
			thread.sleep(500);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
public static void main(String a[])throws Exception{
	SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceBinaryWatermark");
	SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceInvertedTheme");
	SubstanceLookAndFeel.setCurrentGradientPainter("org.jvnet.substance.painter.SpecularGradientPainter");
	SubstanceLookAndFeel.setCurrentButtonShaper("org.jvnet.substance.button.ClassicButtonShaper");
	UIManager.setLookAndFeel(new SubstanceLookAndFeel());
	Main main = new Main();
	main.setVisible(true);
	main.setExtendedState(JFrame.MAXIMIZED_BOTH);
}
}
