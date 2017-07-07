package code;
import java.io.*;
import java.util.*;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
//import java.nio.charset.StandardCharsets;


class POS_count 
	{
	void tag(String filenn) throws IOException, ClassNotFoundException
	{
		

      //Loading an existing document
      
     //to read a text file to a string
	  @SuppressWarnings("resource")
      String text = new Scanner(new File(filenn)).useDelimiter("\\Z").next();
      MaxentTagger tagger = new MaxentTagger("C:/Program Files/stanford-postagger-full-2016-10-31/models/english-left3words-distsim.tagger");
      String tagged = tagger.tagString(text);
      
      
      final String[] tokens = tagged.split(" ");
      List arrlist = new ArrayList();
      
      for (final String token : tokens) 
      {
          final int lastUnderscoreIndex = token.lastIndexOf("_");
          final String realToken = token.substring(lastUnderscoreIndex + 1);
          arrlist.add(realToken);
	  }
      int noun = Collections.frequency(arrlist, "NN");
      int con = Collections.frequency(arrlist, "CC");
      int cd = Collections.frequency(arrlist, "CD");
      int det = Collections.frequency(arrlist, "DT");
      int ex = Collections.frequency(arrlist, "EX");
      int fw= Collections.frequency(arrlist, "FW");
      int in = Collections.frequency(arrlist, "IN");
      int adj = Collections.frequency(arrlist, "JJ");
      int adj_com = Collections.frequency(arrlist, "JJR");
      int adj_super = Collections.frequency(arrlist, "JJS");
      int list_item = Collections.frequency(arrlist, "LS");
      int modal = Collections.frequency(arrlist, "MD");
      int noun_plural = Collections.frequency(arrlist, "NNS");
      int proper_noun = Collections.frequency(arrlist, "NNP");
      int proper_noun_plu = Collections.frequency(arrlist, "NNPS");
      int predeterminer = Collections.frequency(arrlist, "PDT");
      int poss_ending = Collections.frequency(arrlist, "POS");
      int per_pro = Collections.frequency(arrlist, "PRP");
      int pos_pro = Collections.frequency(arrlist, "PRP$");
      int adverb = Collections.frequency(arrlist, "RB");
      int adv_com = Collections.frequency(arrlist, "RBR");
      int adv_super = Collections.frequency(arrlist, "RBS");
      int particle = Collections.frequency(arrlist, "RP");
      int sym = Collections.frequency(arrlist, "SYM");
      int to = Collections.frequency(arrlist, "TO");
      int interjection = Collections.frequency(arrlist, "UH");
      int verb_base_from = Collections.frequency(arrlist, "VB");
      int verb_past_tense = Collections.frequency(arrlist, "VBD");
      int verb_gerund = Collections.frequency(arrlist, "VBG");
      int verb_past_part = Collections.frequency(arrlist, "VBN");
      int verb_non_3sg_pres = Collections.frequency(arrlist, "VBP");
      int verb_3sg_pres = Collections.frequency(arrlist, "VBZ");
      int wh_determiner = Collections.frequency(arrlist, "WDT");
      int wh_pronoun = Collections.frequency(arrlist, "WP");
      int possessive_wh = Collections.frequency(arrlist, "WP$");
      int wh_adverb = Collections.frequency(arrlist, "WRB");      
      
      System.out.println("Frequency of 'nouns' is: "+noun);
      System.out.println("Frequency of 'conjunctions' is: "+con);
      System.out.println("Frequency of 'cardinal number' is: "+cd);
      System.out.println("Frequency of 'determiner' is: "+det);
      System.out.println("Frequency of 'existential there' is: "+ex);
      System.out.println("Frequency of 'foreign words' is: "+fw);
      System.out.println("Frequency of 'preposition' is: "+in);
      System.out.println("Frequency of 'adjective' is: "+adj);
      System.out.println("Frequency of 'existential there' is: "+ex);
      System.out.println("Frequency of 'comparative adjective' is: "+adj_com);
      System.out.println("Frequency of 'superlative adjective' is: "+adj_super);
      System.out.println("Frequency of 'list item' is: "+list_item);
      System.out.println("Frequency of 'modal' is: "+modal);
      System.out.println("Frequency of 'noun plural' is: "+noun_plural);
      System.out.println("Frequency of 'proper noun' is: "+proper_noun);
      System.out.println("Frequency of 'proper noun plural' is: "+proper_noun_plu);
      System.out.println("Frequency of 'predeterminer' is: "+predeterminer);
      System.out.println("Frequency of 'possessive ending' is: "+poss_ending);
      System.out.println("Frequency of 'personal pronoun' is: "+per_pro);
      System.out.println("Frequency of 'possessive pronoun' is: "+pos_pro);
      System.out.println("Frequency of 'adverb' is: "+adverb);
      System.out.println("Frequency of 'comparative adverb' is: "+adv_com);
      System.out.println("Frequency of 'superlative adverb' is: "+adv_super);
      System.out.println("Frequency of 'particle' is: "+particle);
      System.out.println("Frequency of 'symbol' is: "+sym);
      System.out.println("Frequency of 'to' is: "+to);
      System.out.println("Frequency of 'interjection' is: "+interjection);
      System.out.println("Frequency of 'verb base from' is: "+verb_base_from);
      System.out.println("Frequency of 'verb past tense' is: "+verb_past_tense);
      System.out.println("Frequency of 'verb gerund' is: "+verb_gerund);
      System.out.println("Frequency of 'verb past participle' is: "+verb_past_part);
      System.out.println("Frequency of 'verb non 3sg pres' is: "+verb_non_3sg_pres);
      System.out.println("Frequency of 'verb_3sg_pres' is: "+verb_3sg_pres);
      System.out.println("Frequency of 'wh determiner' is: "+wh_determiner);
      System.out.println("Frequency of 'wh pronoun' is: "+wh_pronoun);
      System.out.println("Frequency of 'possessive wh' is: "+possessive_wh);
      System.out.println("Frequency of 'wh adverb' is: "+wh_adverb);
      //text.close();
    }
}
