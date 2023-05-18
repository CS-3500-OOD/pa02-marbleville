package cs3500.pa01;

/**
 * Represents a sort by date created comparison.
 */
public class SortByCreated implements SortByX {
  /**
   * Orders m1 and m2 based on date created.
   *
   * @param m1 the first object to be compared.
   * @param m2 the second object to be compared.
   * @return integer representing the order of the two MarkDowns
   */
  public int compare(MarkDown m1, MarkDown m2) {
    return m1.getCreated().compareTo(m2.getCreated());
  }
}
