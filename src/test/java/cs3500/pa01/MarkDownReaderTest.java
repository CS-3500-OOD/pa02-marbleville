package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarkDownReaderTest {
  private MarkDownReader mdrName;
  private MarkDownReader mdrCreated;
  private MarkDownReader mdrModified;

  @BeforeEach
  void setUp() {

    mdrName = new MarkDownReader(SortOrder.NAME);
    mdrCreated = new MarkDownReader(SortOrder.CREATED);
    mdrModified = new MarkDownReader(SortOrder.LASTMODIFIED);
  }

  /**
   * Tests that the visitFile and readFile methods work as expected.
   */
  @Test
  void testReadFile() {
    boolean readSuccess = true;
    // Tests an existing file
    try {
      BasicFileAttributes attr = Files.readAttributes(
          Paths.get("src/test/resources/arrays.md"), BasicFileAttributes.class);
      mdrName.readFile(Path.of("src/test/resources/arrays.md"), attr);
    } catch (IOException e) {
      readSuccess = false;
    }
    assertTrue(readSuccess);
    assertEquals("arrays.md", mdrName.getFiles().get(0).getName());
    assertEquals(10, mdrName.getFiles().get(0).getUnits().size());

    // Tests a non-existing file
    boolean readSuccess2 = true;
    try {
      BasicFileAttributes attr = Files.readAttributes(
          Paths.get("src/test/resources/arrays2.md"), BasicFileAttributes.class);
      mdrName.visitFile(Path.of("src/test/resources/arrays2.md"), attr);
    } catch (IOException e) {
      readSuccess2 = false;
    }
    assertFalse(readSuccess2);
  }

  /**
   * Tests that the toSingleMarkDown method sorted by name
   */
  @Test
  public void testToSingleMarkDownName() {
    // Test on empty MDR
    assertEquals("studyGuide", mdrName.toSingleMarkDown().getName());
    assertEquals(0, mdrName.toSingleMarkDown().getUnits().size());

    // Test on non-empty MDR sorted by name
    boolean walkSuccess = true;
    try {
      Files.walkFileTree(Path.of("src/test/resources"), mdrName);
    } catch (IOException e) {
      walkSuccess = false;
    }
    assertEquals("studyGuide", mdrName.toSingleMarkDown().getName());
    assertEquals(17, mdrName.toSingleMarkDown().getUnits().size());
    assertEquals(mdrName.toSingleMarkDown().toString().split("\n")[0],
        mdrName.getFiles().get(0).toString().split("\n")[0]);
    assertTrue(walkSuccess);


  }

  /**
   * Tests that the toSingleMarkDown method sorted by date created
   */
  @Test
  public void testToSingleMarkDownCreated() {
    // Test on non-empty MDR sorted by created
    boolean walkSuccess = true;
    try {
      Files.walkFileTree(Path.of("src/test/resources"), mdrCreated);
      long l1 = 10000000L;
      long l2 = 10000001L;
      mdrCreated.getFiles().get(0).setCreated(FileTime.from(Instant.ofEpochMilli(l1)));
      mdrCreated.getFiles().get(1).setCreated(FileTime.from(Instant.ofEpochMilli(l2)));
    } catch (IOException e) {
      walkSuccess = false;
    }
    assertEquals("studyGuide", mdrCreated.toSingleMarkDown().getName());
    assertEquals(17, mdrCreated.toSingleMarkDown().getUnits().size());
    assertEquals(mdrCreated.toSingleMarkDown().toString().split("\n")[0],
        mdrCreated.getFiles().get(0).toString().split("\n")[0]);
    assertTrue(walkSuccess);
  }

  /**
   * Tests that the toSingleMarkDown method sorted by date modified
   */
  @Test
  public void testToSingleMarkDownModified() {
    // Test on non-empty MDR sorted by created
    boolean walkSuccess = true;
    try {
      Files.walkFileTree(Path.of("src/test/resources"), mdrModified);
      long l1 = 10000000L;
      long l2 = 10000001L;
      mdrModified.getFiles().get(0).setCreated(FileTime.from(Instant.ofEpochMilli(l1)));
      mdrModified.getFiles().get(1).setCreated(FileTime.from(Instant.ofEpochMilli(l2)));
    } catch (IOException e) {
      walkSuccess = false;
    }
    assertEquals("studyGuide", mdrModified.toSingleMarkDown().getName());
    assertEquals(17, mdrModified.toSingleMarkDown().getUnits().size());
    assertEquals(mdrModified.toSingleMarkDown().toString().split("\n")[0],
        mdrModified.getFiles().get(0).toString().split("\n")[0]);
    assertTrue(walkSuccess);
  }
}


