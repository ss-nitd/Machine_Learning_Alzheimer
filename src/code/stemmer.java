package code;

import weka.core.stemmers.LovinsStemmer;
import weka.core.stemmers.Stemming;



class stemmer 
{
	public String stem(String in)
	{
		try
		{
			String out="C:/Users/sxs1653/Desktop/after_stem"+Math.random()+".txt";
			String[] arg={"-i",in,"-o",out};
			Stemming.useStemmer(new LovinsStemmer(), arg);
			return out;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ("Exception");
		}
	}
}
