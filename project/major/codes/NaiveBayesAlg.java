package com;
import java.io.FileReader;
import weka.core.Instances;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.classifiers.Evaluation;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.io.File;
import java.util.Random;
public class NaiveBayesAlg {
	static double accuracy;
	static NaiveBayes nb;
	static Evaluation eval;
	static int lastIndex;
	static Instances train;
	static double acc;
public static void train(String input) throws Exception {
	nb = new NaiveBayes();
	FileReader reader = new FileReader(input); 
    train = new Instances(reader);
    train.setClassIndex(train.numAttributes() - 1);
	lastIndex = train.numAttributes() - 1;
    nb.buildClassifier(train);
	eval = new Evaluation(train);
	eval.crossValidateModel(nb, train,10,new Random(10));
	Main.area.append(eval.toSummaryString("\nResults\n======\n", true)+"\n");
	Main.area.append(eval.toClassDetailsString()+"\n");
	Main.area.append("\n"+eval.toMatrixString()+"\n");
	String results = nb.toString().trim();
	Main.area.append(results+"\n");
	acc = eval.pctCorrect();
	Main.area.append("Naive Bayes Accuracy : "+acc+"\n\n");
}

public static void test(String input) throws Exception {
	FileReader reader = new FileReader(input); 
    Instances test = new Instances(reader);
	test.setClassIndex(test.numAttributes() - 1);
	ViewDetection vp = new ViewDetection("Naive Bayes Algorithm");
	for(int i=0; i<test.numInstances(); i++) {
		double index = nb.classifyInstance(test.instance(i));
        String className = train.attribute(lastIndex).value((int)index);
        Object row[] = {test.instance(i).toString(),className};
		vp.dtm.addRow(row);
	}
	vp.setVisible(true);
	vp.setSize(800,600);
}
}