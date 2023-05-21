package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionFileTest {

  QuestionFile qf;

  @BeforeEach
  public void setUp() {
    qf = new QuestionFile("name", FileTime.from(Instant.now()),
        FileTime.from(Instant.now()));
    qf.addQuestion(new Question("question", "answer"));
    qf.addQuestion(new Question("question2", "answer2"));
  }

  /**
   * Tests the toString method for QuestionFile
   */
  @Test
  public void testToString() {
    assertEquals("question:::answer:::false\nquestion2:::answer2:::false\n", qf.toString());
  }

  /**
   * Tests the parseFile method for QuestionFile
   */
  @Test
  public void testParseFile() {
    QuestionFile qf2 = new QuestionFile("name", FileTime.from(Instant.now()),
        FileTime.from(Instant.now()));
    qf2.parseFile("question:::answer:::false\nquestion:::answer2:::false\n");
    assertEquals(2, qf2.getListOfQuestions().size());
    assertEquals("question:::answer:::false\nquestion:::answer2:::false\n",
        qf2.toString());
  }

  /**
   * Tests the addQuestion method for QuestionFile
   */
  @Test
  public void testAddQuestion() {
    Question q3 = new Question("question3", "answer3");
    qf.addQuestion(q3);
    assertEquals(3, qf.getListOfQuestions().size());
    assertEquals(q3, qf.getListOfQuestions().get(2));
  }

  /**
   * Tests the getListOfQuestions method for QuestionFile
   */
  @Test
  public void testGetListOfQuestions() {
    assertEquals(2, qf.getListOfQuestions().size());
    assertEquals("question:::answer:::false", qf.getListOfQuestions().get(0).toString());
    assertEquals("question2:::answer2:::false", qf.getListOfQuestions().get(1).toString());
  }

}