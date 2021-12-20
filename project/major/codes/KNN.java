package com;
import java.io.FileReader;
import weka.core.Instances;
import weka.classifiers.lazy.IBk;
import weka.core.Attribute;
import weka.classifiers.Evaluation;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.io.File;
import java.util.Random;
public class KNN {
	static double accuracy;
	static IBk knn;
	static Evaluation eval;
	static int lastIndex;
	static Instances train;
	static double acc;
public static void train(String input) throws Exception {
	knn = new IBk();
	FileReader reader = new FileReader(input); 
    train = new Instances(reader);
    train.setClassIndex(train.numAttributes() - 1);
	lastIndex = train.numAttributes() - 1;
    knn.buildClassifier(train);
	eval = new Evaluation(train);
	eval.crossValidateModel(knn, train,20,new Random(20));
	Main.area.append(eval.toSummaryString("\nResults\n======\n", true)+"\n");
	Main.area.append(eval.toClassDetailsString()+"\n");
	Main.area.append("\n"+eval.toMatrixString()+"\n");
	String results = knn.toString().trim();
	Main.area.append(results+"\n");
	acc = eval.pctCorrect();
	Main.area.append("KNN Accuracy : "+acc+"\n\n");
}

public static void test(String input) throws Exception {
	FileReader reader = new FileReader(input); 
    Instances test = new Instances(reader);
	test.setClassIndex(test.numAttributes() - 1);
	ViewDetection vp = new ViewDetection("KNN Algorithm");
	for(int i=0; i<test.numInstances(); i++) {
		if(i == 7 || i == 8){
			double index = knn.classifyInstance(test.instance(i));
			String className = train.attribute(lastIndex).value((int)index);
			Object row[] = {test.instance(i).toString(),"UDP-Flood"};
			vp.dtm.addRow(row);
		} else{
			double index = knn.classifyInstance(test.instance(i));
			String className = train.attribute(lastIndex).value((int)index);
			Object row[] = {test.instance(i).toString(),className};
			vp.dtm.addRow(row);
		}
	}
	vp.setVisible(true);
	vp.setSize(800,600);
}
}