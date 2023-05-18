package cs3500.pa01;

import java.nio.file.attribute.FileTime;
import java.util.ArrayList;

public class QuestionFile extends FileType {

  ArrayList<Question> listOfQuestions = new ArrayList<>();

  /**
   * Constructs a QuestionFile object.
   *
   * @param name         the name of the file
   * @param created      the created date of the file
   * @param lastModified the last modified date of the file
   */
  public QuestionFile(String name, FileTime created, FileTime lastModified) {
    super(name, created, lastModified);
  }

  /**
   * Returns the string representation of this QuestionFile
   *
   * @return the string representation of this QuestionFile
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Question q : this.listOfQuestions) {
      sb.append(q.toString());
    }
    return sb.toString();
  }

  /**
   * Parses a string into a QuestionFile object
   *
   * @param file the string to be parsed
   */
  @Override
  public void parseFile(String file) {
    String[] questionArray = file.split("\n");
    for (String s : questionArray) {
      this.listOfQuestions.add(Question.parseLineFromSpacedRepetition(s));
    }
  }

  /**
   * Adds a Question to the list of Questions of this QuestionFile
   *
   * @param q the Question to be added
   */
  public void addQuestion(Question q) {
    this.listOfQuestions.add(q);
  }

  /**
   * Returns the list of Questions of this QuestionFile
   *
   * @return the list of Questions of this QuestionFile
   */
  public ArrayList<Question> getListOfQuestions() {
    return this.listOfQuestions;
  }
}
