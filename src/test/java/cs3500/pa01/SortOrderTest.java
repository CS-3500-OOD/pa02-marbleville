package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SortOrderTest {

  /**
   * Tests the getSortBy method for SortOrder
   */
  @Test
  public void testGetSortBy() {
    assertTrue(SortOrder.NAME.getSortBy() instanceof SortByName);
    assertTrue(SortOrder.CREATED.getSortBy() instanceof SortByCreated);
    assertTrue(SortOrder.LASTMODIFIED.getSortBy() instanceof SortByModified);
    assertThrows(IllegalArgumentException.class, () -> SortOrder.TEST.getSortBy());
  }

  /**
   * Tests the getSortOrder method for SortOrder
   */
  @Test
  public void testGetSortOrder() {
    assertEquals(SortOrder.NAME, SortOrder.getSortOrder("n"));
    assertEquals(SortOrder.CREATED, SortOrder.getSortOrder("c"));
    assertEquals(SortOrder.LASTMODIFIED, SortOrder.getSortOrder("m"));
    assertThrows(IllegalArgumentException.class, () -> SortOrder.getSortOrder("INVALID"));
  }
}
