package cs3500.pa01;

/**
 * Represents a way to sort MarkDowns by date modified.
 */
public class SortByModified implements SortByX {
  /**
   * Orders m1 and m2 based on date modified.
   *
   * @param m1 the first object to be compared.
   * @param m2 the second object to be compared.
   * @return integer representing the order of the two MarkDowns
   */
  public int compare(MarkDown m1, MarkDown m2) {
    // Reverse standard compareTo order to get descending order of modified date
    return -1 * m1.getLastModified().compareTo(m2.getLastModified());
  }
}

