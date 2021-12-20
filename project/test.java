package com;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
public class test{
	static int A[][];
	static int B[][];
	static int square[][];
	static ArrayList<String> data = new ArrayList<String>();
	
public static void readSquare(){
	try{
		BufferedReader br = new BufferedReader(new FileReader("square.txt"));
		String line = null;
		while((line = br.readLine()) != null){
			line = line.trim();
			if(line.length() > 0){
				data.add(line);
			}
		}
		br.close();
		String arr[] = data.get(0).split("\\s+");
		int cols = arr.length;
		int rows = data.size();
		A = new int[rows][cols];
		B = new int[rows][cols];
		square = new int[rows][cols];
		for(int i=0;i<data.size();i++){
			String array[] = data.get(i).split("\\s+");
			for(int j=0;j<array.length;j++){
				A[i][j] = Integer.parseInt(array[j]);
			}
		}
		int end = cols-1;
		int start = (cols/2) - 1 ;
		boolean flag = false;
		for(int i=0;i<A.length;i++){
			for(int j=0;j<A[i].length;j+=2){
				if(i == 0){
					B[i][j] = A[i][end];
					B[i][j+1] = A[i][start];
				} if(i == 1) { 
					B[i][j] = A[i][start];
					B[i][j+1] = A[i][end];
				}
				if(!flag && i > 1 && (i % 2) == 0){
					B[i][j] = A[i][end];
					B[i][j+1] = A[i][start];
				}
				if(!flag && i > 1 && (i % 2) == 1){
					B[i][j] = A[i][start];
					B[i][j+1] = A[i][end];
				}
				if(flag && i > 1 && (i % 2) == 0){
					B[i][j] = A[i][start];
					B[i][j+1] = A[i][end];
				}
				if(flag && i > 1 && (i % 2) == 1){
					B[i][j] = A[i][end];
					B[i][j+1] = A[i][start];
				}
			}
			end = end - 1;
			start = start - 1;
			if(start == -1){
				start = (cols/2) - 1;
				end = cols - 1;
				flag = true;
			}
		}
		int n = 4 ;
		for(int i=0;i<B.length;i++){
			for(int j=0;j<B[i].length;j++){
				int value = A[i][j] + n * B[i][j] - n;
				//value = A[i][j] ^ value;
				square[i][j] = value;
			}
		}
		for(int i=0;i<square.length;i++){
			for(int j=0;j<square[i].length;j++){
				System.out.print(square[i][j]+" ");
			}
			System.out.println();
		}


	}catch(Exception e){
		e.printStackTrace();
	}
}
public static void encrypt()throws Exception{
	BufferedImage bi=ImageIO.read(new File("Water.jpg"));
	BufferedImage image= new BufferedImage(bi.getWidth(),bi.getHeight(),BufferedImage.TYPE_INT_ARGB);
	for(int x=0;x<bi.getWidth();x++){
		for(int y=0;y<bi.getHeight();y++){
			Color c = new Color(bi.getRGB(x, y));
			int red = c.getRed();
			int green = c.getGreen();
			int blue = c.getBlue();
			String redc[] = getBinary(red);
			String greenc[] = getBinary(green);
			String bluec[] = getBinary(blue);
			int p1 = square[Integer.parseInt(redc[0],2)][Integer.parseInt(redc[1],2)];
			int p2 = square[Integer.parseInt(greenc[0],2)][Integer.parseInt(greenc[1],2)];
			int p3 = square[Integer.parseInt(bluec[0],2)][Integer.parseInt(bluec[1],2)];
			square[Integer.parseInt(redc[0],2)][Integer.parseInt(redc[1],2)] = red;
			square[Integer.parseInt(greenc[0],2)][Integer.parseInt(greenc[1],2)] = green;
			square[Integer.parseInt(bluec[0],2)][Integer.parseInt(bluec[1],2)] = blue;
			image.setRGB(x,y,new Color(p1,p2,0).getRGB());
		}
	}
	File outputfile = new File("image.jpg");
	ImageIO.write(image, "jpg", outputfile);
}
public static void decrypt()throws Exception{
	BufferedImage bi=ImageIO.read(new File("image.jpg"));
	BufferedImage image= new BufferedImage(bi.getWidth(),bi.getHeight(),BufferedImage.TYPE_INT_RGB);
	for(int x=0;x<bi.getWidth();x++){
		for(int y=0;y<bi.getHeight();y++){
			Color c = new Color(bi.getRGB(x, y));
			int red = c.getRed();
			int green = c.getGreen();
			int blue = c.getBlue();
			String redc[] = getBinary(red);
			String greenc[] = getBinary(green);
			String bluec[] = getBinary(blue);
			int p1 = square[Integer.parseInt(redc[0],2)][Integer.parseInt(redc[1],2)];
			int p2 = square[Integer.parseInt(greenc[0],2)][Integer.parseInt(greenc[1],2)];
			int p3 = square[Integer.parseInt(bluec[0],2)][Integer.parseInt(bluec[1],2)];
			image.setRGB(x,y,new Color(p1,p2,p3).getRGB());
		}
	}
	File outputfile = new File("decrypt.jpg");
	ImageIO.write(image, "jpg", outputfile);
}
public static String[] getBinary(int value){
	String binary = Integer.toBinaryString(value);
	StringBuilder sb = new StringBuilder();
	int length = 8 - binary.length();
	for(int j=0;j<length;j++){
		sb.append("0");
	}
	sb.append(binary);
	String arr[] = new String[2];
	binary = sb.toString();
	arr[0] = binary.substring(0,4);
	arr[1] = binary.substring(4,binary.length());
	return arr;
}

public static void main(String args[])throws Exception{
	readSquare();
	encrypt();
	decrypt();
}
}