package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MarkDownUnitTest {

  /**
   * Tests the constructor for MarkDownUnit
   */
  @Test
  public void testConstructor() {
    MarkDownUnit mdu = new MarkDownUnit("text", "#");
    assertEquals("text", mdu.getText());
    assertEquals("#", mdu.getTag());
    assertThrows(IllegalArgumentException.class,
        () -> new MarkDownUnit("text", "invalid tag"));
  }

  /**
   * Tests the stringToMarkDownUnit method
   */
  @Test
  public void testStringToMarkDownUnit() {
    MarkDownUnit mdu = MarkDownUnit.stringToMarkDownUnit("# text");
    assertEquals("text", mdu.getText());
    assertEquals("#", mdu.getTag());
    assertThrows(IllegalArgumentException.class,
        () -> MarkDownUnit.stringToMarkDownUnit("invalid tag"));
  }

  /**
   * Tests the toString method
   */
  @Test
  public void testToString() {
    MarkDownUnit mdu = new MarkDownUnit("text", "#");
    assertEquals("# text\n", mdu.toString());
    MarkDownUnit mdu2 = new MarkDownUnit("text", "[[");
    assertEquals("- text\n", mdu2.toString());
    MarkDownUnit mdu3 = new MarkDownUnit("", "#");
    assertEquals("# \n", mdu3.toString());
  }

  /**
   * Tests th getTag method
   */
  @Test
  public void testGetTag() {
    MarkDownUnit mdu = new MarkDownUnit("text", "#");
    assertEquals("#", mdu.getTag());
  }

  /**
   * Tests the getText method
   */
  @Test
  public void testGetText() {
    MarkDownUnit mdu = new MarkDownUnit("text", "#");
    assertEquals("text", mdu.getText());
  }
}