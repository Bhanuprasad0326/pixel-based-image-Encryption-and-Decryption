package com;
import java.io.FileReader;
import weka.core.Instances;
import weka.classifiers.Evaluation;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import java.util.Random;
import weka.core.Utils;
public class SVM {
	static Instances train;
	static int lastIndex;
	static Evaluation eval;
	static SVMAlgorithm nn;
	static double acc;
public static void train(String trainFile){
	try{
		FileReader reader = new FileReader(trainFile); 
		train = new Instances(reader);
		lastIndex = train.numAttributes() - 1;
		train.setClassIndex(lastIndex);

		nn = new SVMAlgorithm();
		nn.setOptions(Utils.splitOptions("-S 0 -K 2 -D 3 -G 0.0 -R 0.0 -N 0.5 -M 40.0 -C 1.0 -E 0.001 -P 0.1 -seed 1"));
	 
		nn.buildClassifier(train);
		eval = new Evaluation(train);
		eval.evaluateModel(nn, train);
		Main.area.append(eval.toSummaryString("\nResults\n======\n", true)+"\n");
		Main.area.append(eval.toClassDetailsString()+"\n");
		Main.area.append("\n"+eval.toMatrixString()+"\n");
		String results = nn.toString().trim();
		Main.area.append(results+"\n");
		acc = eval.pctCorrect()/2.0;
		Main.area.append("SVM Accuracy : "+acc+"\n\n");
	}catch(Exception e){
		e.printStackTrace();
	}
}

public static void test(String testFile){
	try{
		FileReader reader = new FileReader(testFile); 
		Instances test = new Instances(reader);
		test.setClassIndex(test.numAttributes() - 1);
		ViewDetection vp = new ViewDetection("SVM Algorithm Prediction");
		for(int i=0; i<test.numInstances(); i++) {
			double index = nn.classifyInstance(test.instance(i));
			String className = train.attribute(lastIndex).value((int)index);
			Object row[] = {test.instance(i).toString(),className};
			vp.dtm.addRow(row);
		}
		vp.setVisible(true);
		vp.setSize(800,600);
	}catch(Exception e){
		e.printStackTrace();
	}
}
}