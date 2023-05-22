package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionTest {
  Question question;

  @BeforeEach
  void setUp() {
    question = new Question("question", "answer");
  }

  /**
   * Tests the getQuestion method
   */
  @Test
  public void testGetQuestion() {
    assertEquals("question", question.getQuestion());
  }

  /**
   * Tests the getAnswer method
   */
  @Test
  public void testGetAnswer() {
    assertEquals("answer", question.getAnswer());
  }

  /**
   * Tests the isHard and setHard method
   */
  @Test
  public void testIsHard() {
    assertFalse(question.isHard());
    question.setHard(true);
    assertTrue(question.isHard());
  }

  /**
   * Tests the toString method
   */
  @Test
  public void testToString() {
    assertEquals("question:::answer:::false", question.toString());
  }

  /**
   * Tests the parseLineFromMarkDown method
   */
  @Test
  public void testParseLineFromMarkDown() {
    String line = "question:::answer";
    Question q2 = Question.parseLineFromMarkDown(line);
    assertEquals("question", q2.getQuestion());
    assertEquals("answer", q2.getAnswer());
    assertFalse(q2.isHard());
    assertThrows(IllegalArgumentException.class,
        () -> Question.parseLineFromMarkDown("question:answer"));
  }

  /**
   * Tests the parseLineFromSpacedRepetition method
   */
  @Test
  public void testParseLineFromSpacedRepetition() {
    String line = "question:::answer:::true";
    Question q2 = Question.parseLineFromSpacedRepetition(line);
    assertEquals("question", q2.getQuestion());
    assertEquals("answer", q2.getAnswer());
    assertTrue(q2.isHard());
    assertThrows(IllegalArgumentException.class,
        () -> Question.parseLineFromSpacedRepetition("question:answer"));
  }
}