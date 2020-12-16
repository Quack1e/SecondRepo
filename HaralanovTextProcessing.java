/*
 * Martin Haralanov
 * Pre Prog 2
 * 6/2/2020
 */
import java.util.*;
import java.io.*;

public class HaralanovTextProcessing {
	public static void main(String[] args) {
		
		Scanner inFile = null;
		FileWriter outFile = null;
		try {
			inFile = new Scanner(new File("Sentences.txt"));
		} catch(FileNotFoundException e) {
			System.out.println("That file cannot be located");
			System.exit(-1);
		}
		
		int listLength = 0;
		while (inFile.hasNextLine()) {
			inFile.nextLine();
			listLength++;
		}
		inFile.close();
	
		char character;
		char prevCharacter = ' ';
		int i = 0;
		int [] textStats = new int[] {0, 1, 0, listLength};
		String [] outputs = new String[] {"The number of letters is: \t", "The number of words is: \t", "The number of sentences is: \t", "The number of lines is: \t" };
		
		try {
			FileReader read = new FileReader(new File ("Sentences.txt"));
			BufferedReader Buffread = new BufferedReader(read);
			try {
				while ( (i = Buffread.read()) != -1) {
					character = (char) i;
					
					if((character == ' ' && Character.isLetter(prevCharacter)) || (character == ',' && Character.isLetter(prevCharacter))) {
						textStats[1]++;
					}
					if(character == '.' && Character.isLetter(prevCharacter)) {
						textStats[1]++;
						textStats[2]++;
					}
					if (Character.isLetter(character)) {
						textStats[0]++;
					}
					prevCharacter = character;
				}
			} catch(IOException e) {
				System.out.println("Character cannot be read");
				System.exit(-1);
			}
			
		} catch(FileNotFoundException e) {
			System.out.println("That file cannot be located");
			System.exit(-1);
		}
		double [] avgStats = new double[] {((double)textStats[0]/textStats[1]),((double)textStats[1]/textStats[2]),((double)textStats[1]/textStats[3])};
		String [] avgOutputs = new String[] {"The average number of letters per word is: \t", "The average number of words per sentance is: \t", "The average number of words per line is: \t"};
		try {
			outFile = new FileWriter(new File("report.txt"));
		} catch(IOException e) {
			System.out.println("That file cannot be written to");
			System.exit(-1);
		}
		try {
			for(int j = 0; j < 4; j++) {
				outFile.write(outputs[j]+" "+textStats[j]  + "\r\n");
			}
			for(int j = 0; j < 3; j++) {
				outFile.write(avgOutputs[j]+" "+ avgStats[j]  + "\r\n");
			}
			outFile.close();
		} catch (IOException e) {
			System.out.println("Error: file writing");
			System.exit(-1);
		}
		
		
	}
	

}
