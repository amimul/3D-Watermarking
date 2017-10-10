package AIS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

import weka.core.Instances;



public class test {
	public static void main(String args[]) throws Exception
	{
            System.out.println("Hello Testtttttttttttttt");
		BufferedReader breader=null;
		breader=new BufferedReader(new FileReader("D:\\vrushal\\BE project\\horse input files\\h_training.arff"));
		Instances train=new Instances(breader);
		train.setClassIndex(train.numAttributes()-1);
		
		breader=new BufferedReader(new FileReader("D:\\vrushal\\BE project\\horse input files\\horse.arff"));
		Instances test=new Instances(breader);
		test.setClassIndex(train.numAttributes()-1);
		
		breader.close();
		
		CLONALG algorithm = new CLONALG();  
        algorithm.buildClassifier(train);
        Instances labeled=new Instances(test);
        
        for(int i=0;i<test.numInstances();i++)
        {
        	double clslabel=algorithm.classifyInstance(test.instance(i));
        	labeled.instance(i).setClassValue(clslabel);
        }
        BufferedWriter writer=new BufferedWriter(new FileWriter("D:\\vrushal\\BE project\\horse input files\\labeled_horse2.arff"));
        writer.write(labeled.toString());
        writer.close();
        JOptionPane.showMessageDialog(null,"ARFF Created SuccessFully...!!");
	}

}
