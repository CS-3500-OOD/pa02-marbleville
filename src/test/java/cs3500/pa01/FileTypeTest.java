package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileTypeTest {
  FileType ft;
  long l1 = 10000000L;

  @BeforeEach
  public void setUp() {
    long l1 = 10000000L;
    ft = new MarkDown("test.txt",
        FileTime.from(Instant.ofEpochMilli(l1)), FileTime.from(Instant.ofEpochMilli(l1)));
  }

  /**
   * Tests the getName method for FileType
   */
  @Test
  public void testGetName() {
    assertEquals("test.txt", ft.getName());
  }

  /**
   * Tests the getCreated method for FileType
   */
  @Test
  public void testGetCreated() {
    assertEquals(FileTime.from(Instant.ofEpochMilli(l1)), ft.getCreated());
  }

  /**
   * Tests the getLastModified method for FileType
   */
  @Test
  public void testGetLastModified() {
    assertEquals(FileTime.from(Instant.ofEpochMilli(l1)), ft.getLastModified());
  }

  /**
   * Tests the setName method for FileType
   */
  @Test
  public void testSetName() {
    ft.setName("test2.txt");
    assertEquals("test2.txt", ft.getName());
  }

  /**
   * Tests the setCreated method for FileType
   */
  @Test
  public void testSetCreated() {
    ft.setCreated(FileTime.from(Instant.ofEpochMilli(l1 + 1)));
    assertEquals(FileTime.from(Instant.ofEpochMilli(l1 + 1)), ft.getCreated());
  }

  /**
   * Tests the setLastModified method for FileType
   */
  @Test
  public void testSetLastModified() {
    ft.setLastModified(FileTime.from(Instant.ofEpochMilli(l1 + 1)));
    assertEquals(FileTime.from(Instant.ofEpochMilli(l1 + 1)), ft.getLastModified());
  }
}