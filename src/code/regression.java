package code;


import java.io.*;
import java.io.FileReader;
import weka.core.*;
import weka.core.Instances;
import weka.classifiers.functions.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LinearRegression;


class regression
{
	public static void main(String args[]) throws Exception
	{
		DataSource source=new DataSource("C:/Users/sxs1653/Desktop/features.arff");
		Instances traindata=source.getDataSet();
		//set class index to the last attribute
		traindata.setClassIndex(traindata.numAttributes()-1);;
		
		//build model
		SMOreg lr=new SMOreg();
		lr.buildClassifier(traindata);
		//output model
		System.out.println(lr);
		
		//load new dataset
		
		
	}
}