package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class FileSystemReaderTest {
  /**
   * Tests the errorMessage method
   */
  @Test
  public void testErrorMessage() {
    IOException err = new IOException("test");
    assertEquals(FileSystemReader.errorMessage(err), err.toString());
  }
}