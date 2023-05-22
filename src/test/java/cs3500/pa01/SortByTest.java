package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortByTest {
  SortByX sortByCreated;
  SortByX sortByName;
  SortByX sortByModified;

  @BeforeEach
  public void setUp() {
    sortByCreated = new SortByCreated();
    sortByName = new SortByName();
    sortByModified = new SortByModified();
  }

  /**
   * Tests the compare method for SortByCreated
   */
  @Test
  public void testCompareSortByCreated() {
    long l1 = 10000000L;
    long l2 = 10000001L;
    MarkDown m1 = new MarkDown("name", FileTime.from(Instant.ofEpochMilli(l1)),
        FileTime.from(Instant.ofEpochMilli(l1)));
    MarkDown m2 = new MarkDown("name", FileTime.from(Instant.ofEpochMilli(l2)),
        FileTime.from(Instant.ofEpochMilli(l2)));
    MarkDown m3 = new MarkDown("name", FileTime.from(Instant.ofEpochMilli(l1)),
        FileTime.from(Instant.ofEpochMilli(l1)));
    assertEquals(0, sortByCreated.compare(m1, m3));
    assertEquals(-1, sortByCreated.compare(m1, m2));
    assertEquals(1, sortByCreated.compare(m2, m1));
  }

  /**
   * Tests the compare method for SortByModified
   */
  @Test
  public void testCompareSortByModified() {
    long l1 = 10000000L;
    long l2 = 10000001L;
    MarkDown m1 = new MarkDown("name", FileTime.from(Instant.ofEpochMilli(l1)),
        FileTime.from(Instant.ofEpochMilli(l1)));
    MarkDown m2 = new MarkDown("name", FileTime.from(Instant.ofEpochMilli(l2)),
        FileTime.from(Instant.ofEpochMilli(l2)));
    MarkDown m3 = new MarkDown("name", FileTime.from(Instant.ofEpochMilli(l1)),
        FileTime.from(Instant.ofEpochMilli(l1)));
    assertEquals(0, sortByModified.compare(m1, m3));
    assertEquals(-1, sortByModified.compare(m2, m1));
    assertEquals(1, sortByModified.compare(m1, m2));
  }

  /**
   * Tests the compare method for SortByName
   */
  @Test
  public void testCompareSortByName() {
    long l1 = 10000000L;
    long l2 = 10000001L;
    MarkDown m1 = new MarkDown("allen", FileTime.from(Instant.ofEpochMilli(l1)),
        FileTime.from(Instant.ofEpochMilli(l1)));
    MarkDown m2 = new MarkDown("name", FileTime.from(Instant.ofEpochMilli(l2)),
        FileTime.from(Instant.ofEpochMilli(l2)));
    MarkDown m3 = new MarkDown("name", FileTime.from(Instant.ofEpochMilli(l1)),
        FileTime.from(Instant.ofEpochMilli(l1)));
    assertEquals(0, sortByName.compare(m2, m3));
    assertEquals(-13, sortByName.compare(m1, m2));
    assertEquals(13, sortByName.compare(m2, m1));
  }
}
