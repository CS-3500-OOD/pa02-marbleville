package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class WriterTest {
  /**
   * Tests that the writer can write a file
   */
  @Test
  void testWriteFile() {
    Writer writer = new Writer("src/test/resources/test.md");
    writer.writeFile(
        new MarkDown("test.md",
            FileTime.from(Instant.now()), FileTime.from(Instant.now())));
    File file = new File("src/test/resources/test.md");
    assertTrue(file.exists());
    assertTrue(file.delete());
  }
}
