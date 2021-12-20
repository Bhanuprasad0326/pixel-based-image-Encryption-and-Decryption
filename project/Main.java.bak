package com;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Main extends JFrame{	
	JPanel p1,p2,p3;
	JLabel l1,l2;
	Font f1,f2;
	JButton b1,b2,b3,b4,b5;
	JFileChooser chooser;
	File file;
public Main(){
	setTitle("Pixel Based Image Encryption");
	f1 = new Font("Times New Roman",Font.BOLD,16);
    p1 = new JPanel();
    l1 = new JLabel("<html><body><center><br/>Pixel Based Image Encryption Using Magic Square".toUpperCase());
	l1.setFont(this.f1);
    l1.setForeground(Color.white);
    p1.add(l1);
    p1.setBackground(new Color(204, 110, 155));

    f2 = new Font("Times New Roman", 1, 14);
        
	chooser = new JFileChooser(new File("images"));

	p3 = new JPanel();
	b1 = new JButton("Upload Image");
	b1.setFont(f2);
	p3.add(b1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			int option = chooser.showOpenDialog(Main.this);
			if(option == chooser.APPROVE_OPTION){
				file = chooser.getSelectedFile();
				String name = file.getName().toLowerCase();
				if(name.endsWith("jpg") || name.endsWith("gif") || name.endsWith("png")){
					ImageIcon icon = new ImageIcon(file.getPath());
					icon.getImage().flush();
					l2.setIcon(icon);
					JOptionPane.showMessageDialog(Main.this,"Image loaded");
				}else{
					JOptionPane.showMessageDialog(Main.this,"Only jpg,gif and png are allowed");
				}
			}	
		}
	});

	b2 = new JButton("Generate Square");
	b2.setFont(f2);
	p3.add(b2);
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Square.readSquare();
			ViewSquare vs = new ViewSquare();
			for(int i=0;i<Square.cols;i++){
				vs.dtm.addColumn(i+"");
			}
			Object head1[] = {"Matrix A"};
			vs.dtm.addRow(head1);
			for(int i=0;i<Square.A.length;i++){
				Object row[] = new Object[Square.cols];
				for(int j=0;j<Square.A[i].length;j++){
					row[j] = Square.A[i][j];
				}
				vs.dtm.addRow(row);
			}
			Object head2[] = {"Matrix B"};
			vs.dtm.addRow(head2);
			for(int i=0;i<Square.B.length;i++){
				Object row[] = new Object[Square.cols];
				for(int j=0;j<Square.B[i].length;j++){
					row[j] = Square.B[i][j];
				}
				vs.dtm.addRow(row);
			}
			Object head3[] = {"Square Matrix"};
			vs.dtm.addRow(head3);
			for(int i=0;i<Square.square.length;i++){
				Object row[] = new Object[Square.cols];
				for(int j=0;j<Square.square[i].length;j++){
					row[j] = Square.square[i][j];
				}
				vs.dtm.addRow(row);
			}
			vs.setVisible(true);
			vs.setSize(600,400);
		}
	});

	b3 = new JButton("Encrypt Image");
	b3.setFont(f2);
	p3.add(b3);
	b3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			try{
				Square.encrypt(file);
				JOptionPane.showMessageDialog(Main.this,"Image Encrypted & save in encrypt file");
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	});

	b4 = new JButton("Map Square & Decrypt Image");
	b4.setFont(f2);
	p3.add(b4);
	b4.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			try{
				Square.decrypt();
				JOptionPane.showMessageDialog(Main.this,"Image Decrypted & save in decrypt file");
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	});

	b5 = new JButton("Exit");
	b5.setFont(f2);
	p3.add(b5);
	b5.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			System.exit(0);
		}
	});

	p2 = new JPanel();
	l2 = new JLabel(new ImageIcon("images/Sunset.jpg"));
	p2.add(l2);
	p2.setPreferredSize(new Dimension(800,600));
	
    getContentPane().add(p1, "North");
    getContentPane().add(p3, "Center");
	getContentPane().add(p2, "South");
}


public static void main(String a[])throws Exception	{
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	Main cs = new Main();
	cs.setVisible(true);
	cs.setExtendedState(JFrame.MAXIMIZED_BOTH);
}
}