package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpacedRepetitionTest {

  SpacedRepetition spacedRepetition;

  @BeforeEach
  public void setUp() {
    spacedRepetition = new SpacedRepetition();
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