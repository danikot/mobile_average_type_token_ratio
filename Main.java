package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
//import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.List;
import java.util.Map;
//import java.util.Set;

public class Main { public static void main(String[] args) {
try{

//Dateinamen sind als Argumente einzugeben
//in etwa: *.txt
	
	for(String Filename:args){
		
//Das Folgende soll Tokens einlesen. Alle Chars im String "Signs"(z.29) gilten als Wortbruch. 

			String Zeile;
			String tmp=""; 
			String signs="!@#$%^&*()_?.,<>;:\'\"|-  \n~\t=}{«»"	+ "";
			ArrayList<String> GoetheTokens=new ArrayList<String>();
			
			//for (char Zeichen : signs.toCharArray()){System.out.print(Zeichen + "\n");}
					BufferedReader GoetheBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(Filename))));
			while((Zeile=GoetheBufferedReader.readLine()) != null) {
				loop1: 
			    for(int n=0; n<Zeile.length();n++){
				for(char Zeichen : signs.toCharArray()){
				if(Zeichen == Zeile.charAt(n)){ 
				if(tmp!=""){GoetheTokens.add(tmp); /*System.out.print(tmp + "\n")*/; tmp="";}continue loop1;}} 
				tmp=tmp + Zeile.charAt(n);}
				GoetheTokens.add(tmp);/* System.out.print(tmp)*/; tmp="";}
			GoetheBufferedReader.close();
				/*{for(char sign : signs.toCharArray())
				 {if(Zeichen == sign){if(tmp!=""){GoetheTokens.add(tmp); System.out.print(tmp); tmp="";}}
				  else {tmp=tmp+Zeichen;}}*/
			
//Die Typeliste wird durch eine Umwandlung der eingelesenen Tokenliste in einen Set erstellt. 
			
				ArrayList<String> GoetheTypes=new ArrayList<String>(new HashSet<String>(GoetheTokens));
				Collections.sort(GoetheTypes);
				GoetheTypes.remove(0);
				
//Hier wird die Häufigkeit auf Types gemappt.
				
				Map<String,Integer> GoetheTypesTokensMap=new HashMap<String,Integer>();			
				for (String Type:GoetheTypes)
				{GoetheTypesTokensMap.put(Type, Collections.frequency(GoetheTokens,Type));}

//Nun wird daraus eine Map nur mit Wörtern die mit Großbuchstaben anfangen erstellt.

				Map<String,Integer> GoetheTypesTokensMapCapitalInitial=new HashMap<String,Integer>();		
				for (String key:GoetheTypesTokensMap.keySet()){if (Character.isUpperCase(key.toCharArray()[0]))
					{GoetheTypesTokensMapCapitalInitial.put(key, GoetheTypesTokensMap.get(key));}}

//Hier wird alles in der Aufgaben 1 gefragte in jeweils eine eigene Datei gestreamt.
//Dateinamen sind relativ zum Namen der eingegebenen Datei.
				
				PrintWriter Gutenberg = new PrintWriter(Filename + "_20.txt");
				GoetheTypesTokensMap.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed()).limit(20).forEach(Gutenberg::println);
				Gutenberg.close();
				Gutenberg = new PrintWriter(Filename + "_20CAP.txt");
				GoetheTypesTokensMapCapitalInitial.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed()).limit(20).forEach(Gutenberg::println);
				Gutenberg.close();
				Gutenberg = new PrintWriter(Filename + "_Types.txt");
				GoetheTypes.stream().forEach(Gutenberg::println);
				Gutenberg.close();
				
//Das MATTR Fenster rückt Wort für Wort vor und beinhaltet 300 Wörter.

				int word=0;
				int lastword=0;
				int windows=0;
				float sumMATTR=0;
				while ((word + 300)<GoetheTokens.size()){
				word=windows+300;
				HashSet<String> SubSet=new HashSet<String>(GoetheTokens.subList(windows, word));
				sumMATTR=sumMATTR + ((float)(SubSet.size())/300);
				windows++;}
				windows++;
				HashSet<String> SubSet=new HashSet<String>(GoetheTokens.subList(lastword, GoetheTokens.size()));
				sumMATTR=sumMATTR + ((float)(SubSet.size())/(GoetheTokens.size()%300));
				float MATTR=(float)(sumMATTR)/windows;
				
//nun wird sowohl MATTR als auch TTR sowohl in die Konsole als auch in eine Datei eingegeben.

				Gutenberg = new PrintWriter(Filename + "_(MA)TTR.txt");
				System.out.println("in " + Filename + ":");
				System.out.println("Type/Token Ratio: "+ Float.toString((float)GoetheTypes.size()/GoetheTokens.size()));
				Gutenberg.println("Type/Token Ratio: "+ Float.toString((float)GoetheTypes.size()/GoetheTokens.size()));
				System.out.println("Moving-Average Type/Token Ratio with Window size 300: "+ Float.toString(MATTR));
				Gutenberg.println("Moving-Average Type/Token Ratio with Window size 300: "+ Float.toString(MATTR));
				Gutenberg.close();
	}
	}catch(Exception e){
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}

}
