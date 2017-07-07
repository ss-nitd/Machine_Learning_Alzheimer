/*Author- Soumi Sarkar
 * Function of this snippet
 * Takes a text file
 * Converts it to String
 * Removes the punctuation
 * Removes special character
 * Converts the entire text to lower case
 * Removes digits
 * Converts the string to csv
 * Converts csv to arff using weka
 * stemming
 * tokenistion 
 * counts frequency of each word
 * removal of stop words
 * Find TF
 * Find TF-IDF
 * Clustering
 * POS tagger*/
package code;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JFileChooser;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;


//import weka.core.converters.AbstractFileLoader;

public class clean_text
	{
		
	public static void main(String[] args)throws Exception
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Select File to open");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.setMultiSelectionEnabled(true);
	
	
	
			int returnValue = fileChooser.showOpenDialog(null);
	
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File[] file_array = fileChooser.getSelectedFiles();
	        //int nn=file_array.length;
	        for(File f:file_array)
	        {
			FileWriter writer;
			
			//text file to string
			@SuppressWarnings("resource")
			String line = new Scanner(f).useDelimiter("\\Z").next();//feeding of the text files f in file_array
			// string manipulation
		    line = line.replaceAll("[^\\x00-\\x7f]+", "");
			line = line.replaceAll("[0-9]+","");
			line = line.replaceAll("Chapter+","");
			line = line.replaceAll("Page+","");
			line = line.replaceAll(",","");
			line = line.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
			line = line.toLowerCase();
			//System.out.println(line);
			
			String filen="C:/Users/sxs1653/Desktop/cleaned_unstemmed"+Math.random()+".txt";//text before stemming
			//String after_stem="C:/Users/sxs1653/Desktop/after_stem"+Math.random()+".txt";//text after stemming
			
			try {
				
				File fileff = new File(filen);//before stemming "C:/Users/sxs1653/Desktop/test1.txt"
				FileWriter fileWriter = new FileWriter(fileff);
				fileWriter.write(line);
				//fileWriter.write("a test");
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			//stemming_______________________________________________________________________________
			
			
			stemmer st = new stemmer();
			String after_stem = st.stem(filen);
			
			
			//end of stemming______________________________________________________________
			
			//  tokenization_________________________________________________________________________
			
			clean_text tm=new clean_text();
			
			BufferedReader fileRead = new BufferedReader(new FileReader(filen));	//feeding the text before stemming
			String currentMessage;
			@SuppressWarnings("unchecked")
			HashMap<String, Integer>[] bagofwords=new HashMap[10];
			int count=0;
			WordTypeCount wtc;
			
			while((currentMessage = fileRead.readLine()) != null)
			{
				wtc=new WordTypeCount();
				bagofwords[count]=new HashMap<String, Integer>();
			
				bagofwords[count]=wtc.createMap(currentMessage);
					count++;
			}
			fileRead.close();
			
			
			// Display tokens and their count
			System.out.println("Bag Of Words\n\n");
			tm.DisplayBagOfWords(bagofwords);
			//end of tokenisation_____________________________________________________________
			
			
			// Removal of stop words________________________________________________________
			
						BufferedReader 	stopWFile;
						String currentStopW;
						
						for(int i=0;i<1;i++)
						{
							stopWFile = new BufferedReader(new FileReader("C:/Users/sxs1653/Desktop/stopwords.txt"));
							while((currentStopW = stopWFile.readLine()) != null)
							{
								if(bagofwords[i].containsKey(currentStopW))
								{
									bagofwords[i].remove(currentStopW);
								}
							}
							stopWFile.close();
						}
						
						System.out.println("Bag Of Words After removal stop words\n\n");
						tm.DisplayBagOfWords(bagofwords);
						
						// End of removal of stop words_____________________________________________
						
						// find all terms
						
						Set< String > temp;
						Set< String > terms=new TreeSet<String>();
						
						
						
						for(int i=0;i<1;i++)
						{
							 temp= new TreeSet<String>(bagofwords[i].keySet());
							 terms.addAll(temp);
						}
						
						System.out.println("\n");
						
						int totTerms=terms.size();
						System.out.println("Terms Size :"+totTerms);
						
						// end of step___________________________________
						
						
						
						
						// find TF(d,t)_____________________________________
						
						double[][] TF=new double[1][totTerms];
						String word;
						
						//totTerms=50;
						//noOfDocs=nn;
						
						for(int i=0;i<1;i++)
						{
							Iterator<String> itr=terms.iterator();
							word=itr.next();
							
							for(int j=0;j<totTerms;j++)
							{
								if(!bagofwords[i].containsKey(word))
								{
								
									TF[i][j]=0;
								}
								else
								{
								
									
									TF[i][j]=1 + Math.log10(1 + Math.log10(bagofwords[i].get(word).doubleValue()));
									
								}
							//System.out.println(TF[i][j]);
								if(itr.hasNext())
									itr.next();
							}
						}
						// end of step
						
						
			//___________________________________________________________________________________________________________
			
			
			File nfile=new File(filen);
			//string to csv
			Scanner scan = new Scanner(line);
			//Scanner scan1 = new Scanner(line);
			String new_file="C:/Users/sxs1653/Desktop/CSV"+Math.random()+".csv";
			File file2 = new File(new_file);
			nfile.createNewFile();
			
			writer = new FileWriter(file2);
			//CSVWriter csvWrite = new CSVWriter(new FileWriter("C:/Users/sxs1653/Desktop/CSV.csv"));

			
			while (scan.hasNext())
			{
			   String csv = scan.nextLine().replace(" ", ",");
	           writer.append(csv);
	           writer.append("\n");
	           writer.flush();
			}
			

			
			
			
			

			//csv to arff conversion
			String new_arff="C:/Users/sxs1653/Desktop/new_arff"+Math.random()+".arff";
			CSVLoader loader=new CSVLoader();
			//loader.setNoHeaderRowPresent(true);
			loader.setSource(new File(new_file));
			String[] options = new String[1]; 
			options[0] = "-H";
			 loader.setOptions(options);
			Instances data=loader.getDataSet(); //get instances object
			//save ARFF
			ArffSaver saver=new ArffSaver();
			saver.setInstances(data);//set the dataset we want to convert
			saver.setFile(new File(new_arff));
			saver.writeBatch();
			
			
			//Clustering________________________________________________
			//String train=new_arff;
			//String test=new_arff;
			//clustering cls=new clustering();
			//cls.clus(train,test);// We can change the training and the testing data if we want
	        
			//End of clustering____________________________________________________________
			
			
			
			
			/*// find IDF(term) and IF * IDF__________________________________
			
			int[] dt=new int[totTerms];
			
			Iterator<String> itr=terms.iterator();
			
			for(int i=0;i<totTerms;i++)
			{
				word=itr.next();
				for(int j=0;j<1;j++)
				{
					if(bagofwords[j].containsKey(word))
					{
						dt[i]=dt[i]+1;
					}
				}
			//	System.out.println("DT = "+ dt[i]);
			}
			
			double[] IDF=new double[totTerms];
			
			for(int i=0;i<totTerms;i++)
			{
				if(dt[i]!=0)
				{
					IDF[i]=Math.log10((1+1)/dt[i]);//no of docs 1
				//	System.out.println("IDF = "+IDF[i]);
				}
			}
			
			BufferedWriter fileWrite=null;
			
			fileWrite=new BufferedWriter(new FileWriter("C:/Users/sxs1653/Desktop/IFIDF.arff"));
			fileWrite.write("@RELATION IFIDF");
			fileWrite.newLine();
			
			for(int j=0;j<totTerms;j++)
			{
				fileWrite.write("@ATTRIBUTE t"+j+" real");
				fileWrite.newLine();
			}
			fileWrite.write("@ATTRIBUTE class { ham, spam}");
			fileWrite.newLine();
			
			fileWrite.write("@DATA");
			
			fileWrite.newLine();
			
			
			fileWrite.close();
			
			Double[][] TFIDF=new Double[1][totTerms]; 
			String[] classLabel=new String[1];
			
			try
			{
				fileWrite=new BufferedWriter(new FileWriter("C:/Users/sxs1653/Desktop/IFIDF.arff",true));
				for(int i=0;i<1;i++)
				{	
					for(int j=0;j<totTerms;j++)
					{
						TFIDF[i][j]=TF[i][j] * IDF[j];
					//	System.out.println("TF * IDF = "+TFIDF[i][j]);
						fileWrite.write(new String(TFIDF[i][j].toString())+",");
					}
					fileWrite.write(classLabel[i]);
					fileWrite.newLine();
				}
				fileWrite.close();
			}
			catch(Exception e){}
			*/
			
			
			//Filtering attributes__________________________
			
			
			//end of filtering___________________________________
			
			
			
			//Parts of speech tagging and frequency________________________________________________
			//POS_count poscount=new POS_count();
			//poscount.tag(train);
			// Parts of speech tagging end_____________________________________
			
			writer.close();
			scan.close();
			System.out.println("Close");
	        }// end of for loop for the file array
		}
			
	}

	public void DisplayBagOfWords(HashMap<String, Integer>[] bagofwords)
	{
		//noOfDocs=10;
		for(int i=0;i<1;i++) //i= Serial number of Doc
		{
		
		  Set< String > keys = bagofwords[i].keySet(); // getting the keys in a set for this particular document

	      // sort keys
	      TreeSet< String > sortedKeys = new TreeSet< String >( keys );
	      //Java TreeSet class implements the Set interface that uses a tree for storage.
	      //It inherits AbstractSet class and implements NavigableSet interface. The objects of TreeSet class are stored in ascending order.
	      System.out.println( "\nMap contains:\nKey\t\tValue\n" );

	      // generate output for each key in map
	      for ( String key : sortedKeys )
	         System.out.printf( "%-10s%10s\n", key, bagofwords[i].get( key ) );
	      
		  System.out.println( "\nDocument NO:\t\t# of Keys" );
		  System.out.println("---------------------------------");
	      System.out.println((i+1)+"\t\t\t"+bagofwords[i].size()); 
	      //System.out.println("----------------------------------------"); 
		}
	}
}

