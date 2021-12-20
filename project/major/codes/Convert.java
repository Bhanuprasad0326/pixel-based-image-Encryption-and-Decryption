package com;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import java.io.File;
public class Convert {
public static void main(String args[])throws Exception{
	convert("test.csv","test.arff");
}
public static void convert(String s1,String s2){
	try{
		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(s1));
		Instances data = loader.getDataSet();
		
		ArffSaver saver = new ArffSaver();
		saver.setInstances(data);
		saver.setFile(new File(s2));
		//saver.setDestination(new File(s2));
		saver.writeBatch();
		loader.reset();
	}catch(Exception e){
		e.printStackTrace();
	}
  }
}