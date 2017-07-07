package code;

import weka.filters.*;
import weka.core.*;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Remove;
import java.io.*;

class attr_fil 
{
	public void attr_filt(String arff_file)
	{
		try
		{
			String filt="C:/Users/sxs1653/Desktop/filt_attr"+Math.random()+".arff";
			DataSource source=new DataSource(arff_file);
			Instances dataset=source.getDataSet();
			String opts[]=new String[] {"-R","2"};
			Remove remove=new Remove();
			remove.setOptions(opts);
			remove.setInputFormat(dataset);
			Instances newdata=Filter.useFilter(dataset, remove);
			
			//ARFF saver
			ArffSaver saver=new ArffSaver();
			saver.setInstances(newdata);
			saver.setFile(new File(filt));;
			saver.writeBatch();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	public static void main(String[] args)
	{
		attr_fil af=new attr_fil();
		af.attr_filt("C:/Users/sxs1653/Desktop/new_arff0.16462419382321436.arff");
	}
}
