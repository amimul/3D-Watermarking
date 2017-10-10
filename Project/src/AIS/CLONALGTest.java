package AIS;

import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JOptionPane;

import weka.classifiers.Evaluation;
import weka.core.Instances;


public class CLONALGTest
{
    public static void main(String[] args)
    {
		try
		{
                    double cp=0,icp=0;
                    DecimalFormat df=new DecimalFormat("#.##");
			// prepare dataset
            Instances dataset = new Instances(new FileReader("D:\\vrushal\\BE project\\horse input files\\labeled_horse.arff"));
            dataset.setClassIndex(dataset.numAttributes() - 1);
            CLONALG algorithm = new CLONALG();  
            algorithm.buildClassifier(dataset);
        	// evaulate
            Evaluation evaluation = new Evaluation(dataset);
            evaluation.crossValidateModel(algorithm, dataset, 7, new Random(1));            
            // print algorithm details
            System.out.println(algorithm.toString());
            // print stats
            cp=Double.parseDouble(df.format((evaluation.correct()/evaluation.numInstances())))*100;
            icp=Double.parseDouble(df.format((evaluation.incorrect()/evaluation.numInstances())))*100;
            
            //System.out.println("Correctly Classified Instances  :\t"+evaluation.correct()+" \t"+cp+" %");
            //System.out.println("Incorrectly Classified Instances:\t"+evaluation.incorrect()+"\t"+icp+" %");
            //System.out.println("Total number of Instances       :\t"+evaluation.numInstances());
                    //System.out.println(evaluation.);
            //System.out.println("fMeasure	"+evaluation.fMeasure(1)+" \nPrecision	"+evaluation.precision(1)+"\nRecall		"+evaluation.recall(1));
            JOptionPane.showMessageDialog(null,"Correctly Classified Instances  :\t"+(int)evaluation.correct()+"  \t"+cp+" %"+"\n"+"Incorrectly Classified Instances:\t\t"+(int)evaluation.incorrect()+"  \t"+icp+" %"+"\n"+ "Total number of Instances       :\t" + evaluation.numInstances()+"\nfMeasure==>"+"\t"+evaluation.fMeasure(1)+" \nPrecision==>"+"\t"+evaluation.precision(1)+"\nRecall==>"+"\t"+evaluation.recall(1));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}
