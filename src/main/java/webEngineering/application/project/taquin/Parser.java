package webEngineering.application.project.taquin;

import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import webEngineering.application.project.taquin.heuristic.*;
import webEngineering.application.project.taquin.utils.ExpectedSolutionCalculator;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {

    public String[] args;

    public int[][] table;

    private int dimension;

    private Heuristic heuristic;

    int heuristicDefinition;

    Map<String, Boolean> options;

    public Parser(String[] args) {
        this.args = args;
    }


    private NPuzzle parse(String file) throws IOException, InvalidFileNameException, IllegalArgumentException {

        if (args.length == 0) throw new IllegalArgumentException("no input...");
        
        if (file == null || file.isEmpty()) throw new IllegalArgumentException("name of file is empty or null");
        File files = new File(file);
        BufferedReader bufferedReader;
        FileReader reader = new FileReader(files);
        bufferedReader = new BufferedReader(reader);

        dimension = retrieveDimensionFromInput(bufferedReader);
        table = new int[dimension][];
        String line;
        int i = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (i == dimension)
                throw new IllegalArgumentException("there are to much line compared to the dimension");
            int[] listOfInt = isLineValid(line);
            if (listOfInt != null)
                table[i++] = listOfInt;
        }
        checkTableIFullAndCompleted();
        checkNoDuplicateNumber();

        bufferedReader.close();
        setOptions();
        if (!SolvabilityChecker.isSolvable(table))
            throw new IllegalArgumentException("the board is not solvable");
        NPuzzle puzzle = new NPuzzle(table, ExpectedSolutionCalculator.CLASSIC.getSolution(dimension), dimension, heuristic);
        puzzle.setOptions(options);
        return puzzle;

    }

    private void setOptions() {

        State state =new State(ExpectedSolutionCalculator.CLASSIC.getSolution(dimension));
        String option = args[heuristicDefinition];
        if (option.endsWith("euclidian"))
            heuristic = new EuclidianDist();
        else if (option.endsWith("manhathan"))
            heuristic = new Manhathan();
        else if (option.endsWith("misplaced_tiles"))
            heuristic = new MisplacedTile();
        else
            heuristic = new OptimizedManhattan(state.getInternPosition());
    }

    private int getOptions() {
        options = new HashMap<>();

        int i;
        i = 0;
        String option;

        while (i < args.length) {
            option = args[i];
            if (option.equals("--greedy") || option.equals("-g"))
                options.put("greedy", true);
            else if (option.equals("--uniform") || option.equals("-u"))
                options.put("uniform", true);
            else if (option.equals("--server") || option.equals("-s"))
                options.put("server", true);
            else if (option.startsWith("--heuristic:") || option.startsWith("-h")) {
                heuristicDefinition = i;
            }
            else
                break;
            i++;
        }

        return i;
    }

    private void checkNoDuplicateNumber() {

        boolean[] checkExist = new boolean[dimension * dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if(table[i][j] > dimension * dimension)
                    throw new IllegalArgumentException("this number cant exist on this grid..." + table[i][j]);
                if (!checkExist[table[i][j]])
                    checkExist[table[i][j]] = true;
                else
                    throw new IllegalArgumentException("there are duplicates on the board");
            }
        }
    }

    private void checkTableIFullAndCompleted() throws IllegalArgumentException {
        for (int[] array :
                table) {
            if (array == null || array.length != dimension)
                throw new IllegalArgumentException("one line is not as the dimension described it");
        }
        if (table.length != dimension)
            throw new IllegalArgumentException("the count of lines is not the same as the dimension described it");
    }

    private int retrieveDimensionFromInput(BufferedReader bufferedReader) throws IOException, IllegalArgumentException {

        String line;
        while ((line = bufferedReader.readLine()) != null && isComment(line));
        if (line == null)
            throw new IllegalArgumentException("this file is missing a part...");
        String[] parts = line.split("#");
        if (((parts.length > 1 && parts[1].charAt(0) == '#') || parts.length == 1) &&  parts[0].matches("[0-9]+"))
            return Integer.parseInt(parts[0]);
        else
            throw new IllegalArgumentException("Dimension is not valid: line :" + line);
    }

    public NPuzzle parse() throws IOException {
        if (args.length == 0)
            throw new IllegalArgumentException("no files selected");
        int countOption = getOptions();
        if (countOption >= args.length)
            throw new IllegalArgumentException("no files selected");
       return parse(args[countOption]);
    }

    private int[] isLineValid(String line) {
        if (isComment(line))
            return null;
        else {
            String[] separatedLine = line.split("#");
            for (String s: separatedLine) {
                s.trim();
            }
            try {
                int[] tableOfNumber = Stream.of((separatedLine[0].split("[ ]+")))
                        .map(String :: trim).filter(item -> !item.isEmpty()).mapToInt(Integer::parseInt).toArray();

                if (tableOfNumber.length != dimension) throw new IllegalArgumentException("dimension is not respected for the line : " + line);
                return tableOfNumber;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("line not composed only of number: " + line);
            }
        }
    }

    private boolean isComment(String line) {
        return (line.isEmpty() || line.charAt(0) == '#');
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
