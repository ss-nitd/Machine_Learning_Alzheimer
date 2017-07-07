package code;



import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

class clustering 
{
	void clus(String dataset, String train)
	{
	try
	{
	DataSource source=new DataSource(dataset);
	Instances data1=source.getDataSet(); 
	SimpleKMeans model=new SimpleKMeans();
	model.setNumClusters(2);
	model.buildClusterer(data1);;
	System.out.println(model);
	
	ClusterEvaluation clseval=new ClusterEvaluation();
	//String dataset1=dataset;
	DataSource source1=new DataSource(train);
	Instances data11=source1.getDataSet();
	clseval.setClusterer(model);
	clseval.evaluateClusterer(data11);;
	System.out.println("No of clusters: "+clseval.getNumClusters());
	}
	catch(Exception e)
	{
		System.out.println("Exception");
	}
	}

}
