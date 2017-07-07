package code;
import java.io.*;
import java.lang.Math;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.swing.*;
//import edu.stanford.nlp.tagger.maxent.MaxentTagger;

class pdf_to_text 
{
	public static void main(String args[]) throws IOException, ClassNotFoundException
		{

	      //Loading an existing document
	      //File file = new File("F:/PROJECTS/PSU/Corpus/Agatha Christae/The mirror cracked from side to side-1962.pdf");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select File to open");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(true);



		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file[] = fileChooser.getSelectedFiles();
        int n= file.length;
        //System.out.println(n);
        for(int j=0;j<n;j++)
        {
		PDDocument document = PDDocument.load(file[j]);

	      //Instantiate PDFTextStripper class
	      PDFTextStripper pdfStripper = new PDFTextStripper();

	      //Retrieving text from PDF document
	      String text = pdfStripper.getText(document);
	      //System.out.println(text);
	      text = text.replaceAll("[^\\x00-\\x7f]+", "");
	      text = text.replaceAll("[0-9]+","");
	      text = text.replaceAll("Chapter+","");
	      text = text.replaceAll("Page+","");
	     
	      try {
	    	    BufferedWriter out = new BufferedWriter(new FileWriter("F:/PROJECTS/PSU/text"+Math.random()+".txt"));
	    	    //out.createNewFile();
	    	    out.write(text); 
	    	    out.write("End of file");
	    	    out.close();
	    	   }
	      catch (IOException e)
	    	{
	    	    System.out.println("Exception ");

	    	}
	      //Closing the document
	      document.close();
	      //break;
	     
	   }
	}
}
}

