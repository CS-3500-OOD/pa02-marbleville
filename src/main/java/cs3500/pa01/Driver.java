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
    if (args.length <= 2 && args.length != 0) {
      System.out.println(helpMessage());
    } else if (args.length != 0) {
      try {
        StudyGuideGenerator sgg =
            new StudyGuideGenerator(args[0], SortOrder.getSortOrder(args[1]), args[2]);
        sgg.generateStudyGuide();
      } catch (Exception e) {
        System.out.println("Invalid Input");
      }
    } else if (args.length == 0) {
      SpacedRepetition sp = new SpacedRepetition();
      sp.showWelcomeScreen();
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
        + "[output path]"
        + ", or no arguments to study questions.";
  }
}