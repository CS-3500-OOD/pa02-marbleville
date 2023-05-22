package cs3500.pa01;

/**
 * Represents a question in a Spaced Repetition System.
 */
public class Question {
  private String question;
  private String answer;
  private boolean isHard = false;

  /**
   * Constructs a Question object.
   *
   * @param question the question
   * @param answer   the answer
   */
  public Question(String question, String answer) {
    this.question = question;
    this.answer = answer;
  }

  /**
   * Returns the question of this Question object.
   *
   * @return the question of this Question object
   */
  public String getQuestion() {
    return this.question;
  }

  /**
   * Returns the answer of this Question object.
   *
   * @return the answer of this Question object
   */
  public String getAnswer() {
    return this.answer;
  }

  /**
   * Returns whether this Question object is hard.
   *
   * @return whether this Question object is hard
   */
  public boolean isHard() {
    return this.isHard;
  }

  /**
   * Sets this Question object to be hard.
   */
  public void setHard(boolean isHard) {
    this.isHard = isHard;
  }

  /**
   * Returns a string representation of the data stored by this Question object.
   *
   * @return a string representation of the data stored by this Question object
   */
  public String toString() {
    return this.question + ":::" + this.answer + ":::" + this.isHard;
  }

  /**
   * Parses a line of text from a MarkDown file into a Question object.
   *
   * @param line of text to be parsed
   * @return a Question object with the data from the given line of text
   */
  public static Question parseLineFromMarkDown(String line) {
    if (!line.contains(":::")) {
      throw new IllegalArgumentException("Invalid line");
    }

    String[] lineArr = line.split(":::");
    String question = lineArr[0];
    String answer = lineArr[1];
    return new Question(question, answer);
  }

  /**
   * Parses a line of text from a SpacedRepetition file into a Question object.
   *
   * @param line of text to be parsed
   * @return a Question object with the data from the given line of text
   */
  public static Question parseLineFromSpacedRepetition(String line) {
    if (!line.contains(":::")) {
      throw new IllegalArgumentException("Invalid line");
    }
    String[] lineArr = line.split(":::");
    String question = lineArr[0];
    String answer = lineArr[1];
    boolean isHard = Boolean.parseBoolean(lineArr[2]);
    Question q = new Question(question, answer);
    q.setHard(isHard);
    return q;
  }
}
