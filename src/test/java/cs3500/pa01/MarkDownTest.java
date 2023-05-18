package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarkDownTest {

  MarkDown md;

  @BeforeEach
  public void setUp() {
    md = new MarkDown("Test", FileTime.from(Instant.EPOCH),
        FileTime.from(Instant.EPOCH));
  }

  /**
   * Tests the constructor for MarkDown
   */
  @Test
  public void testConstructor() {
    assertEquals("Test", md.getName());
    assertEquals(FileTime.from(Instant.EPOCH), md.getCreated());
    assertEquals(FileTime.from(Instant.EPOCH), md.getLastModified());
  }

  /**
   * Tests the addMDUnit and toString method for MarkDown
   */
  @Test
  public void testToString() {
    assertEquals("", md.toString());
    md.addMarkDownUnit(new MarkDownUnit("Test", "#"));
    assertEquals("# Test\n\n", md.toString());
    md.addMarkDownUnit(new MarkDownUnit("Test1", "[["));
    assertEquals("# Test\n- Test1\n\n", md.toString());
  }

  /**
   * Tests the tokenizeLine method for MarkDown
   */
  @Test
  public void testTokenizeLine() {
    md.tokenizeLine("");
    assertEquals("", md.toString());
    md.tokenizeLine("# Test");
    assertEquals("# Test\n\n", md.toString());
    md.tokenizeLine("[[Test1]]");
    assertEquals("# Test\n- Test1\n\n", md.toString());
    md.tokenizeLine("[[Test2]]");
    assertEquals("# Test\n- Test1\n- Test2\n\n", md.toString());

  }

  /**
   * Tests the parseFile method for MarkDown
   */
  @Test
  public void testParseFile() {
    boolean readSuccess = true;
    try {
      Scanner scanner = new Scanner(Path.of("src/test/resources/arrays.md"));
      StringBuilder file = new StringBuilder();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine() + "\n";
        file.append(line);
      }
      md.parseFile(file.toString());
    } catch (Exception e) {
      readSuccess = false;
    }
    assertEquals("Test", md.getName());
    assertEquals(10, md.getUnits().size());
    assertTrue(readSuccess);
  }
}