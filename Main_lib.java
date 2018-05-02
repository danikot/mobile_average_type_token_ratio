package main;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.WordTokenFactory;

public class Main_lib {

  public static void main(String[] args) throws IOException {
    for (String arg : args) {
      // option #2: By token
      PTBTokenizer ptbt = new PTBTokenizer(new FileReader(arg),
              new WordTokenFactory(), null);
      ArrayList<String> GoetheTokens=new ArrayList();
      while (ptbt.hasNext()){String xxx=ptbt.next().toString(); if(Character.isLetter(xxx.toCharArray()[0])){GoetheTokens.add(xxx);}}
      
      
		ArrayList<String> GoetheTypes=new ArrayList<String>(new HashSet<String>(GoetheTokens));
		Collections.sort(GoetheTypes);
		Map<String,Integer> GoetheTypesTokensMap=new HashMap<String,Integer>();			
		for (String Type:GoetheTypes)
		{GoetheTypesTokensMap.put(Type, Collections.frequency(GoetheTokens,Type));}
		Map<String,Integer> GoetheTypesTokensMapCapitalInitial=new HashMap<String,Integer>();		
		for (String key:GoetheTypesTokensMap.keySet()){if (Character.isUpperCase(key.toCharArray()[0]))
			{GoetheTypesTokensMapCapitalInitial.put(key, GoetheTypesTokensMap.get(key));}}
		PrintWriter IwanFjodorow = new PrintWriter(arg + "_20_lib.txt");
		GoetheTypesTokensMap.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed()).limit(20).forEach(IwanFjodorow::println);
		IwanFjodorow.close();
		IwanFjodorow = new PrintWriter(arg + "_20CAP_lib.txt");
		GoetheTypesTokensMapCapitalInitial.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed()).limit(20).forEach(IwanFjodorow::println);
		IwanFjodorow.close();
		IwanFjodorow = new PrintWriter(arg + "_Types_lib.txt");
		GoetheTypes.stream().forEach(IwanFjodorow::println);
		IwanFjodorow.close();
		int word=0;
		int windows=0;
		float sumMATTR=0;
		while ((word + 300)<GoetheTokens.size()){word=windows+300;
		HashSet<String> SubSet=new HashSet<String>(GoetheTokens.subList(windows, word));
		sumMATTR=sumMATTR + ((float)(SubSet.size())/300);windows++;}
		if(windows==0){windows++;
		HashSet<String> SubSet=new HashSet<String>(GoetheTokens);
		sumMATTR=sumMATTR + ((float)(SubSet.size())/(GoetheTokens.size()%300));}
		float MATTR=(float)(sumMATTR)/windows;
		IwanFjodorow = new PrintWriter(arg + "_(MA)TTR_lib.txt");
		System.out.println("in " + arg + ":");
		System.out.println("Type/Token Ratio: "+ Float.toString((float)GoetheTypes.size()/GoetheTokens.size()));
		IwanFjodorow.println("Type/Token Ratio: "+ Float.toString((float)GoetheTypes.size()/GoetheTokens.size()));
		System.out.println("Moving-Average Type/Token Ratio with Window size 300: "+ Float.toString(MATTR));
		IwanFjodorow.println("Moving-Average Type/Token Ratio with Window size 300: "+ Float.toString(MATTR));
		IwanFjodorow.close();

    	 }
    }
  }
