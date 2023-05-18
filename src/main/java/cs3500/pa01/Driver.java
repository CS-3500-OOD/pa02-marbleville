package cs3500.pa01;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - input path, sort order (n, c, m), output path
   */
  public static void main(String[] args) {
    if (args.length <= 2) {
      System.out.println(helpMessage());
    } else {
      try {
        StudyGuideGenerator sgg =
            new StudyGuideGenerator(args[0], SortOrder.getSortOrder(args[1]), args[2]);
        sgg.generateStudyGuide();
      } catch (Exception e) {
        System.out.println("Invalid Input");
      }
    }
  }

  /**
   * Returns a help message for the user
   *
   * @return a help message for the user
   */
  public static String helpMessage() {
    return "Please enter an \n"
        + "[input path], \n"
        + "[sort order (n - name, c - date created, m - date modified)], and an \n"
        + "[output path]";
  }
}