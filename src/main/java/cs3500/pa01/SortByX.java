package cs3500.pa01;

import java.util.Comparator;

/**
 * Interface for different sort comparisons.
 */
public interface SortByX extends Comparator<MarkDown> {
  /**
   * Compares its two arguments for order.  Returns a positive integer if the first MarkDown
   * comes before the second MarkDown, a negative integer if the first MarkDown comes after,
   * and zero if the MarkDowns are the same.
   *
   * @param m1 the first object to be compared.
   * @param m2 the second object to be compared.
   * @return integer representing the order of the two MarkDowns
   */
  int compare(MarkDown m1, MarkDown m2);
}
