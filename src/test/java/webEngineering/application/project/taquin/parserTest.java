package webEngineering.application.project.taquinv2;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webEngineering.application.project.taquinv2.utils.ExpectedSolutionCalculator;

import javax.naming.NotContextException;
import java.io.IOException;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;


public class parserTest {

    Logger logger = (Logger) LoggerFactory.getLogger(Parser.class);

    ClassLoader classLoader = getClass().getClassLoader();

    @Test(expected = IllegalArgumentException.class)
    public void checkLineWithNotOnlyNumber() throws IllegalArgumentException, IOException {

        String[] params = {classLoader.getResource("testNPuzzle/notOnlyNumbers.txt").getFile()};
        Parser parser = new Parser(params);
        try {
            parser.parse();
        } catch (IllegalArgumentException e) {
            logger.info("success: checkLineWithNoNumber - " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.info("wrong exception: checkLineWithNoNumber - " + e.getMessage());
        }
    }

    @Test(expected = IOException.class)
    public void  checkWrongFileName() throws IOException{

        String[] params = {"no name"};
        Parser parser = new Parser(params);
        try {
            parser.parse();
        } catch (IOException e) {
            logger.info("success: checkWrongFileName - " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.info("wrong exception: checkWrongFileName - " + e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNoInput() throws IOException {

        String[] params = new String[2];
        Parser parser = new Parser(params);
        try {
            parser.parse();
        } catch (IllegalArgumentException e) {
            logger.info("success: checkNoInput - " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.info("wrong exception: checkNoInput - " + e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNoDimension() throws Exception {

        String[] params = {classLoader.getResource("testNPuzzle/noDimension.txt").getFile()};
        Parser parser = new Parser(params);
        try {
            parser.parse();
        } catch (IllegalArgumentException e) {
            logger.info("success: checkNoDimension - " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.info("wrong exception: checkNoDimension - " + e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNoGrid() throws Exception{

        String[] params = {classLoader.getResource("testNPuzzle/noGrid.txt").getFile()};
        Parser parser = new Parser(params);
        try {
            parser.parse();
            throw new NotContextException();
        } catch (IllegalArgumentException e) {
            logger.info("success: checkNoGrid - " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("wrong exception: checkNoGrid - " + e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkLineAreNotGoodAccoringToDimension() throws Exception{

        String[] params = {classLoader.getResource("testNPuzzle/LineAreNotGoodAccoringToDimension.txt").getFile()};
        Parser parser = new Parser(params);
        try {
            parser.parse();
            throw new NotContextException();
        } catch (IllegalArgumentException e) {
            logger.info("success: checkLineAreNotGoodAccoringToDimension - " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("wrong exception: checkLineAreNotGoodAccoringToDimension - " + e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkLineAreNotGoodAccoringToDimension2() throws Exception{

        String[] params = {classLoader.getResource("testNPuzzle/LineAreNotGoodAccoringToDimension2.txt").getFile()};
        Parser parser = new Parser(params);
        try {
            parser.parse();
            throw new NotContextException();
        } catch (IllegalArgumentException e) {
            logger.info("success: checkLineAreNotGoodAccoringToDimension2 - " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("wrong exception: checkLineAreNotGoodAccoringToDimension2 - " + e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNumberIsToBig() throws Exception{

        String[] params = {classLoader.getResource("testNPuzzle/numberIsTooBig.txt").getFile()};
        Parser parser = new Parser(params);
        try {
            parser.parse();
            throw new NotContextException();
        } catch (IllegalArgumentException e) {
            logger.info("success: numberIsTooBig - " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("wrong exception: numberIsTooBig - " + e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkEmptyFile() throws Exception{

        String[] params = {classLoader.getResource("testNPuzzle/empty.txt").getFile()};
        Parser parser = new Parser(params);
        try {
            parser.parse();
            throw new NotContextException();
        } catch (IllegalArgumentException e) {
            logger.info("success: checkEmptyFile - " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("wrong exception: checkEmptyFile - " + e.getMessage());
            throw e;
        }
    }



    // success tests

    @Test
    public void createEasyOne() throws Exception{

        String[] params = {classLoader.getResource("testNPuzzle/easyOne.txt").getFile()};
        Parser parser = new Parser(params);
        NPuzzle puzzle = null;
        try {
            puzzle = parser.parse();
        } catch (Exception e) {
            throw e;
        }
        assertEquals(3, puzzle.getDimension());
        assertEquals(Arrays.deepEquals(puzzle.getExpectedBoard(), ExpectedSolutionCalculator.CLASSIC.getSolution(3)), true);
    }
}
