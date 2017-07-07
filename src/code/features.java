package code;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import javax.swing.JFileChooser;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

class features 
{
	public static void main(String[] args)throws IOException
	{
		features fea=new features();
		int i=0,j=0;
		int mm=0;
		double array[][];
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select File to open");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(true);
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) 
		{
				File[] file_array = fileChooser.getSelectedFiles();
		        int nn=file_array.length;
		        mm=nn;
		        array=new double[nn][50];//nn docs and 5 features
		        
		        //invoking the stanford tagger
		        MaxentTagger tagger = new MaxentTagger("C:/Program Files/stanford-postagger-full-2016-10-31/models/english-left3words-distsim.tagger");
		        
		        for(int g=0;g<nn;g++)
		        {
		        	@SuppressWarnings("resource")
		        	//text file to string
		        		FileWriter writer;
		        		String line;
		        		String pathname= file_array[g].toString();
		        		File file1 = new File(pathname);
		        		StringBuilder fileContents = new StringBuilder((int)file1.length());
		        		Scanner scanner = new Scanner(file1);
		        		String lineSeparator = System.getProperty("line.separator");
		        		 try {
		        		        while(scanner.hasNextLine()) 
		        		        {
		        		            fileContents.append(scanner.nextLine() + lineSeparator);
		        		        }
		        		        line=fileContents.toString();
		        		 	 }
		        		 finally 
		        		 	{
		        		        scanner.close();
		        		    }
		        		 
		        		 
		        		 	//Count the number of sentences 
		        		 	String tagged1 = tagger.tagString(line);
						    final String[] tokens1 = tagged1.split(" ");
						    List arrlist1 = new ArrayList();
						    for (final String token : tokens1) 
						      {
						          final int lastUnderscoreIndex1 = token.lastIndexOf("_");
						          final String realToken1 = token.substring(lastUnderscoreIndex1 + 1);
						          arrlist1.add(realToken1);
							  }
						    int sent = Collections.frequency(arrlist1, ".");
							
		        		 
		        		 
						//String line = new Scanner(f).useDelimiter("\\Z").next();//feeding of the text files f in file_array
						
						// string manipulation
					    line = line.replaceAll("[^\\x00-\\x7f]+", "");
						line = line.replaceAll("[0-9]+","");
						line = line.replaceAll("Chapter+","");
						line = line.replaceAll("Page+","");
						line = line.replaceAll(",","");
						line = line.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
						line = line.toLowerCase();
						//System.out.println(line);
						//Counting total number of words in a document
						final String[] wo = line.split(" ");
					    int cc=wo.length;
					   
						
						
						//invoking the stanford tagger
						
					    String tagged = tagger.tagString(line);
					    final String[] tokens = tagged.split(" ");
					    List arrlist = new ArrayList();
					    for (final String token : tokens) 
					      {
					          final int lastUnderscoreIndex = token.lastIndexOf("_");
					          final String realToken = token.substring(lastUnderscoreIndex + 1);
					          arrlist.add(realToken);
						  }
						
					    
					  /* String filen="C:/Users/sxs1653/Desktop/cleaned_unstemmed"+Math.random()+".txt";//text before stemming
						//code to write a string to a file where line is the string
						try
						{
							
							File fileff = new File(filen);
							FileWriter fileWriter = new FileWriter(fileff);
							fileWriter.write(line);
							//fileWriter.write("a test");
							fileWriter.flush();
							fileWriter.close();
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}*/
						
						
						j=0;
						//cc is the total word count
						//Features calculation
						double uni=fea.uni_word(line);
						array[i][j++]=(uni/cc)*100; // number of unique words
						double no=fea.noun(arrlist);
						array[i][j++]=(no/cc)*100;//nouns
						double pro=fea.pronoun(arrlist);
						array[i][j++]=(pro/cc)*100;//pronouns
						double co=fea.conjun(arrlist);
						array[i][j++]=(co/cc)*100;//conjunction
						double in=fea.indef(line);
						array[i][j++]=(in/cc)*100;//indefinite terms
						double re=fea.reduce(arrlist);
						array[i][j++]=(re/cc)*100;//reduced sent
						double intr=fea.inter(arrlist);
						array[i][j++]=(intr/cc)*100;//interjections
						array[i][j++]=cc;//total word count
						double sub=fea.subo(arrlist);
						array[i][j++]=(sub/cc)*100;// adjectives
						double verb=fea.verbs(arrlist);
						array[i][j++]=(verb/cc)*100;//total verb count
						array[i][j++]=sent;//total sentence count
						array[i][j++]=(sent/cc)*100;//normalised sentence count
						array[i][j++]=(no/verb); //noun to verb ratio
						double adverb=fea.adverbs(arrlist);
						array[i][j++]=(adverb/cc)*100;//adverbs
						array[i][j++]=uni/cc; //TTR(V/N)
						array[i][j++]=Math.pow(cc,Math.pow(uni, -0.165));//Brunet's index
						int once=fea.word_once(line);// number of words occuring once
						array[i][j++]=(100*Math.log(cc))/(1-(once/uni));//Honore's Statistic
						double word_mean=fea.word_length(line);
						array[i][j++]=word_mean*100;//Mean length of words
						array[i][j++]=((no+sub+verb+adverb)/cc)*100;//Lexical density
						double funct=fea.func(arrlist);
						array[i][j++]=(funct/cc)*100;//Function words
						i++;
						j=0;
												
				}//end of the file for loop
		        for(int k=0;k<nn;k++)
		        {
		        	for(int m=0;m<5;m++)
		        		System.out.print(array[k][m]+" ");
		        	System.out.println();
		        }
		        
				
				try
				{
					
					BufferedWriter fileWriter=null;
					fileWriter=new BufferedWriter(new FileWriter("C:/Users/sxs1653/Desktop/feature.arff"));
					fileWriter.write("@RELATION features");
					fileWriter.newLine();
					fileWriter.newLine();
					//fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE UNIQUE_WORD"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE NOUNS"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE PRONOUN"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE CONJUNCTIONS"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE INDEFINITE_TERMS"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE REDUCED_SENT"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE INTERJECTIONS"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE TOTAL_WORD_COUNT"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE ADJECTIVES"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE VERB"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE NO_OF_SENTENCES"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE NORMALISED_SENTENCES"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE NOUN_TO_VERB"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE ADVERB"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE TTR"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE BRUNETT'S_INDEX"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE HONORE'S_STATISTIC"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE MEAN_WORD_LENGTH"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE LEXICAL_DENSITY"+" real");
					fileWriter.newLine();
					fileWriter.write("@ATTRIBUTE FUNCTION_WORDS"+" real");
					fileWriter.newLine();
					fileWriter.newLine();
					//fileWriter.newLine();
					fileWriter.write("@DATA");
					fileWriter.newLine();
					for(int k=0;k<mm;k++)
			        {
			        	for(int m=0;m<20;m++)
			        		fileWriter.write(new String(Double.toString(array[k][m]))+",");
			        	//fileWriter.write("?");
			        	fileWriter.newLine();
			        }
					fileWriter.flush();
					fileWriter.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
		       
				
		}//end of if
		
	}
	
	
	
	
	private Map< String, Integer > map;
	int uni_word(String str)
	{
		int uni=0;
		map = new HashMap< String, Integer >(); 
		String del=" :/.!,";
		StringTokenizer tokenizer = new StringTokenizer(str,del);
		 while ( tokenizer.hasMoreTokens() ) 
		   {
		      String word = tokenizer.nextToken().toLowerCase(); 
		      if ( map.containsKey( word ) ) // is word in map
		      {
		         int count = map.get( word ); // get current count
		         map.put( word, count + 1 );  // increment count
		      } // end if
		      else
		      {
		         map.put( word, 1 );// add new word with a count of 1 to map
		         uni++;
		      }
		    } // end while
		 return uni;
	}
	
	private Map< String, Integer > map1;
	int word_once(String str)
	{
		int re=0;
		map1=new HashMap<String, Integer>();
		String del=" :/.!,";
		StringTokenizer tokens=new StringTokenizer(str,del);
		while(tokens.hasMoreTokens())
		{
			String word=tokens.nextToken();
			if(map1.containsKey(word))
			{
				int count=map1.get(word);
				map1.put(word, count+1);
			}
			else
				map1.put(word, 1);
		}
		while(tokens.hasMoreTokens())	
		{
			String wo=tokens.nextToken();
			if(map1.get(wo)==1)
			{
				re++;
			}
		}
		return re;
	}
	
	
	double word_length(String str)
	{
		double len=0,words=0;
		String del=" :/.!,";
		StringTokenizer st=new StringTokenizer(str,del);
		while(st.hasMoreTokens())
		{
			String word=st.nextToken();
		    len+=word.length();
		    words++;
		}
		return (len/words);
	}
	
	
	int noun(List<String> arr)
	{
		int noun = Collections.frequency(arr, "NN");
		int noun2 = Collections.frequency(arr, "NNS");
		int res=noun+noun2;
		return res;
	}
	
	int verbs(List<String> arr)
	{
		int v1 = Collections.frequency(arr, "VB");
		int v2= Collections.frequency(arr, "VBD");
		int v3 = Collections.frequency(arr, "VBG");
		int v4 = Collections.frequency(arr, "VBN");
		int v5 = Collections.frequency(arr, "VBP");
		int v6 = Collections.frequency(arr, "VBZ");
		int res=v1+v2+v3+v4+v5+v6;
		return res;
	}
	
	int pronoun(List<String> arr)
	{
		int per_pro = Collections.frequency(arr, "PRP");
		int pos_pro = Collections.frequency(arr, "PRP$");
		return (per_pro);
	}
	
	
	int conjun(List<String> arr)
	{
		int conj = Collections.frequency(arr, "CC");
		return conj;
	}
	
	int adverbs(List<String> arr)
	{
		int conj1 = Collections.frequency(arr, "RB");
		int conj2 = Collections.frequency(arr, "RBR");
		int conj3 = Collections.frequency(arr, "RBS");
		return conj1+conj2+conj3;
	}
	
	int func(List<String> arr)
	{
		int conj1 = Collections.frequency(arr, "CC");
		int conj2 = Collections.frequency(arr, "DT");
		int conj3 = Collections.frequency(arr, "IN");
		return conj1+conj2+conj3;
	}
	
	int inter(List<String> arr)
	{
		int conj = Collections.frequency(arr, "UH");
		return conj;
	}
	
	int subo(List<String> arr)
	{
		int conj = Collections.frequency(arr, "JJ");
		return conj;
	}
	
	int indef(String str)
	{
		int indf=0;
		List<String> con=Arrays.asList("all","someone","something","anyone","anybody","everyone","each","nobody","anything","some","few","many","everything",
										"nothing","everybody","somebody","all","stuff");
		String del=" :/.!,";
		StringTokenizer tokenizer = new StringTokenizer(str,del);
		while ( tokenizer.hasMoreTokens() ) 
		   {
		      String word = tokenizer.nextToken().toLowerCase(); 
		      if(con.contains(word))
		    	   indf++;
		     
		   }
		return indf;
	}
	int reduce(List<String> arr)
	{
		int conj = Collections.frequency(arr, "VBG");
		int conj2 = Collections.frequency(arr, "VBN");
		int res=conj+conj2;
		return res;
	}
}
