package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudyGuideGeneratorTest {
  StudyGuideGenerator sggName;
  StudyGuideGenerator sggCreated;
  StudyGuideGenerator sggModified;

  @BeforeEach
  public void setUp() {
    sggName = new StudyGuideGenerator("./src/test/resources",
        SortOrder.NAME,
        "./src/test/sggName.md");
    sggCreated = new StudyGuideGenerator("./src/test/resources",
        SortOrder.CREATED,
        "./src/test/sggCreated.md");
    sggModified = new StudyGuideGenerator("./src/test/resources",
        SortOrder.LASTMODIFIED,
        "./src/test/sggModified.md");
  }

  @Test
  public void testGenerateStudyGuide() {
    sggName.generateStudyGuide();
    sggCreated.generateStudyGuide();
    sggModified.generateStudyGuide();
    File modified = new File("src/test/sggModified.md");
    assertTrue(modified.exists());
    assertTrue(modified.delete());

    File name = new File("src/test/sggName.md");
    assertTrue(name.exists());
    assertTrue(name.delete());

    File created = new File("src/test/sggCreated.md");
    assertTrue(created.exists());
    assertTrue(created.delete());

    File questions = new File("src/test/questions.sr");
    assertTrue(questions.delete());
  }

  @Test
  public void testNameText() {
    sggName.generateStudyGuide();
    boolean exists = true;
    try {
      File name = new File("src/test/sggName.md");
      Scanner scanner = new Scanner(name);
      scanner.close();
      assertTrue(name.delete());
    } catch (IOException e) {
      e.printStackTrace();
      exists = false;
    }
    assertTrue(exists);
  }

  @Test
  public void testCreatedText() {
    sggCreated.generateStudyGuide();
    boolean exists = true;
    try {
      File name = new File("src/test/sggCreated.md");
      Scanner scanner = new Scanner(name);
      scanner.close();
      assertTrue(name.delete());
    } catch (IOException e) {
      e.printStackTrace();
      exists = false;
    }
    assertTrue(exists);

  }

  @Test
  public void testModifiedText() {
    sggModified.generateStudyGuide();
    boolean exists = true;
    try {
      File name = new File("src/test/sggModified.md");
      Scanner scanner = new Scanner(name);
      scanner.close();
      assertTrue(name.delete());
    } catch (IOException e) {
      exists = false;
    }
    assertTrue(exists);
  }
}
