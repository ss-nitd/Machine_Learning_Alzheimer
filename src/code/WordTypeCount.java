package code;

//Program counts the number of occurrences of each word in a string
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

public class WordTypeCount
{
private Map< String, Integer > map;


// create map from user input
public HashMap<String, Integer> createMap(String input) 
{
	   map = new HashMap< String, Integer >(); // create HashMap
   // create StringTokenizer for input
	   String del=" :/.!,";
   StringTokenizer tokenizer = new StringTokenizer(input,del);

   // processing input text 
   while ( tokenizer.hasMoreTokens() ) // while more input 
   {
      String word = tokenizer.nextToken().toLowerCase(); // get word
               
      // if the map contains the word
      if ( map.containsKey( word ) ) // is word in map
      {
         int count = map.get( word ); // get current count
         map.put( word, count + 1 );  // increment count
      } // end if
      else 
         map.put( word, 1 ); // add new word with a count of 1 to map
    } // end while
   
   return (HashMap<String, Integer>) map;
   
} // end method createMap
} // end class WordTypeCount
