package util;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Test {



	public static void testing(String[] args) {
		System.out.println("studTest");

		ArrayList<String> arguments = new ArrayList<String>();
		File reference = new File("");
		String st;
		String ClassMetrics = new String("");
		ArrayList<String> listForFile = new ArrayList<String>();
		ArrayList<String> listForReference = new ArrayList<String>();

		Collections.addAll(arguments, args);
		arguments.remove(0);
		for (String arg : arguments) {

			File file = new File(arg);
			if (!file.exists()) {
				System.out.println("file not found");
				return;
			}

			try{

				ClassMetrics = file.getName();
				System.out.print("Start test "+ClassMetrics+"... ");
				reference = new File("src/main/resources/defaultOutputFiles/" + ClassMetrics);
				if(!reference.exists()){
					System.out.println("This metric is not found");
					return;
				}

				BufferedReader read = new BufferedReader(new FileReader(file));
				while((st = read.readLine()) != null){

/*if(st.matches(".*METRICS CLASS*")){
ClassMetrics = st.substring(st.indexOf("picard.analysis")+"picard.analysis.".length());
reference = new File("src/main/resources/defaultOutputFiles/" + ClassMetrics + ".txt");
}*/

					filter(st,listForFile);
				}
				read.close();

				read = new BufferedReader(new FileReader(reference));
				while((st = read.readLine()) != null)
					filter(st,listForReference);
				read.close();

				if(listForFile.equals(listForReference))
					System.out.println("checked");
				else
					System.out.println("unchecked");

			}
			catch(Exception e){e.printStackTrace();}

		}
	}
	static private void filter(String st, ArrayList list){
		if(!st.startsWith("#") && st.length()>0){
			st=st.replace(" ", "");
			st=st.replace(" ", "");
			list.add(st);
//System.out.println(st);
		}
	}
}
