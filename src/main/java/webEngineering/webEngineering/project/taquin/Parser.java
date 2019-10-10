package webEngineering.webEngineering.project.taquin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Parser {

	private String[] args;

	public Parser(String[] args) {

		this.args = args;
	}

	public NPuzzle parse() {

		int[][] table = null;
		int dimension = 0;
		
		String fileName = args[0];
		String line = null;

		try {	

			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int lineCount = 0;

			while ((line = bufferedReader.readLine()) != null) {

				// separate on #
				String[] splittedLine= line.split("#");
				String firstPart = splittedLine[0].trim();

				// check the first part of the string is compose only of numbers and spaces;
				if (firstPart.length() > 0 && firstPart.charAt(0) != '#' ) {
					if (!firstPart.matches("[0-9 ]{3,}")) {
						throw new IllegalArgumentException("caracter are not only numbers");
					}
					else {
						int[] arrayOfNumber = Arrays.asList(firstPart.split(" ")).stream().mapToInt(Integer::parseInt).toArray();
						if (dimension == 0) {
							dimension = arrayOfNumber.length;
							table = new int[dimension][dimension];
						} else if (lineCount > dimension || arrayOfNumber.length != dimension)
							throw new IllegalArgumentException("the table is not a square");
						table[lineCount++] = arrayOfNumber;
					}
				}
			}
			bufferedReader.close();
			if (dimension != lineCount)
				throw new IllegalArgumentException("the table is not a square");
		} catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file '" + 
							fileName + "'");                
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading file '" 
							+ fileName + "'");
		}
		NPuzzle puzzle = new NPuzzle(dimension);
		puzzle.setBoard(table);
		return puzzle;
	}
}
