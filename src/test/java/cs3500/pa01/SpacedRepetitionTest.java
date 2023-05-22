package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpacedRepetitionTest {

  SpacedRepetition spacedRepetition;

  @BeforeEach
  public void setUp() {
    spacedRepetition = new SpacedRepetition();
  }

  /**
   * Tests the showWelcomeScreen method
   */
  @Test
  public void testShowWelcomeScreen() {
    String input = String.format("src/test/resources/questions.sr%s2", System.lineSeparator());
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    spacedRepetition.showWelcomeScreen();
    assertEquals(1, spacedRepetition.getHardCt());
    assertEquals(2, spacedRepetition.getListOfQuestions().size());
  }

  /**
   * Tests the showQuestions method
   */
  @Test
  public void testShowQuestions() {
    // load file and set state
    spacedRepetition.loadQuestionFile("src/test/resources/questions.sr");
    spacedRepetition.setNumQuestions(2);

    // simulate a study session where user sets all questions to hard
    spacedRepetition.setPath("src/test/resources/questions(1).sr");
    String input = String.format("2%s2", System.lineSeparator());
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    spacedRepetition.showQuestions();
    assertEquals(2, spacedRepetition.getHardCt());

    // simulate a study session where user sets all questions to easy
    spacedRepetition.setPath("src/test/resources/questions(1).sr");
    String input2 = String.format("1%s1", System.lineSeparator());
    ByteArrayInputStream in2 = new ByteArrayInputStream(input2.getBytes());
    System.setIn(in2);
    spacedRepetition.showQuestions();
    assertEquals(0, spacedRepetition.getHardCt());

    // simulate session where user asks for the answer
    spacedRepetition.setPath("src/test/resources/questions(1).sr");
    String input3 = String.format("3%s1%s3%s2", System.lineSeparator(), System.lineSeparator(),
        System.lineSeparator());
    ByteArrayInputStream in3 = new ByteArrayInputStream(input3.getBytes());
    System.setIn(in3);

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    spacedRepetition.showQuestions();
    assertTrue(
        outContent.toString().contains("Lansing"));

    //tests exiting program
    spacedRepetition.setPath("src/test/resources/questions(2).sr");
    String input4 = String.format("1%s1", System.lineSeparator());
    ByteArrayInputStream in4 = new ByteArrayInputStream(input4.getBytes());
    System.setIn(in4);
    spacedRepetition.showQuestions();
    File questions2 = new File("src/test/resources/questions(2).sr");
    assertTrue(questions2.delete());

    // delete test file
    File questions = new File("src/test/resources/questions(1).sr");
    assertTrue(questions.delete());
  }

  /**
   * Tests the setNumQuestions and getNumQuestions method
   */
  @Test
  public void testSetNumQuestions() {
    spacedRepetition.setNumQuestions(2);
    assertEquals(2, spacedRepetition.getNumQuestions());
  }

  /**
   * Tests the showStats method
   */
  @Test
  public void testShowStats() {
    spacedRepetition.loadQuestionFile("src/test/resources/questions.sr");
    assertEquals("----------------------------"
            + "\nNumber of questions answered: 0"
            + "\nNumber of questions Easy -> Hard: 0"
            + "\nNumber of questions Hard -> Easy: 0"
            + "\nNumber of hard questions in file: 1"
            + "\nNumber of east questions in file: 1",
        spacedRepetition.showStats());
  }

  /**
   * Tests the loadQuestionFile method
   */
  @Test
  public void testLoadQuestionFile() {
    spacedRepetition.loadQuestionFile("src/test/resources/questions.sr");
    assertEquals(1, spacedRepetition.getHardCt());
    assertEquals(2, spacedRepetition.getListOfQuestions().size());
  }

  /**
   * Tests the getQuestionList method
   */
  @Test
  public void testGetQuestionList() {
    spacedRepetition.loadQuestionFile("src/test/resources/questions.sr");
    assertEquals(2, spacedRepetition.getQuestionList(2).size());
    assertTrue(spacedRepetition.getQuestionList(2).get(0).isHard());
    assertThrows(IllegalArgumentException.class, () -> spacedRepetition.getQuestionList(3));
  }

  /**
   * Tests the getListOfQuestions method
   */
  @Test
  public void testGetListOfQuestions() {
    spacedRepetition.loadQuestionFile("src/test/resources/questions.sr");
    assertEquals(2, spacedRepetition.getListOfQuestions().size());
    assertEquals(
        "[What is the capital of Massachusetts?:::Boston:::false, What is the capital of "
            + "Michigan?:::Lansing:::true]",
        spacedRepetition.getListOfQuestions().toString());
  }

  /**
   * Tests the getHardCt method
   */
  @Test
  public void testGetHardCt() {
    spacedRepetition.loadQuestionFile("src/test/resources/questions.sr");
    assertEquals(1, spacedRepetition.getHardCt());
  }

  /**
   * Tests the exitStudySession method
   */
  @Test
  public void testExitStudySession() {
    spacedRepetition.loadQuestionFile("src/test/resources/questions.sr");
    spacedRepetition.exitStudySession("src/test/resources/questions.sr");
    File file = new File("src/test/resources/questions.sr");
    assertTrue(file.exists());
  }

  /**
   * Tests the question message method
   */
  @Test
  public void testQuestionMessage() {
    assertEquals("1. Set easy 2. Set hard 3. Show answer 4. Exit",
        spacedRepetition.questionMessage());
  }

  /**
   * Tests the errorMessage method
   */
  @Test
  public void testErrorMessage() {
    assertEquals("Invalid input. Please try again.", spacedRepetition.errorMessage());
  }

  /**
   * Tests the welcomeMessage method
   */
  @Test
  public void testWelcomeMessage() {
    assertEquals("Welcome to Spaced Repetition!\nPlease input the path to your question "
            + "file: ",
        spacedRepetition.welcomeMessage());
  }

  /**
   * Tests the fileLoadedMessage method
   */
  @Test
  public void testFileLoadedMessage() {
    spacedRepetition.loadQuestionFile("src/test/resources/questions.sr");
    assertEquals("\nYour question file has been loaded..."
        + "\nHow many question would you like to study out of "
        + 2 + "?: ", spacedRepetition.fileLoadedMessage());
  }
}