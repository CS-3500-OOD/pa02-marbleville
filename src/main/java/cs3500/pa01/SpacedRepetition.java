package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Represents and coordinates a SpacedRepetition study session
 */
public class SpacedRepetition {
  ArrayList<Question> listOfQuestions = new ArrayList<>();
  private int hardCt = 0;

  private int numQuestions = 0;

  private int numQuestionsAnswered = 0;

  private int questionEasyToHard = 0;

  private int questionHardToEasy = 0;

  private String path = "";


  /**
   * Shows welcome screen and prompts user for input file and number of questions
   */
  public void showWelcomeScreen() {
    System.out.print(this.welcomeMessage());

    Scanner input = new Scanner(System.in);

    this.path = input.nextLine();

    this.loadQuestionFile(this.path);

    System.out.println(this.fileLoadedMessage());

    this.numQuestions = Integer.parseInt(input.nextLine());

    if (numQuestions <= this.listOfQuestions.size()) {
      this.showQuestions();
    } else {
      this.errorMessage();
      System.exit(0);
    }
  }

  /**
   * Takes user input for path and loads the question file
   */
  public void loadQuestionFile(String path) {
    QuestionFile qf = new QuestionFile(path.substring(path.lastIndexOf("/") + 1),
        FileTime.from(Instant.now()), FileTime.from(Instant.now()));
    try {
      String file = Files.readString(Path.of(path));
      qf.parseFile(file);
      this.listOfQuestions = qf.getListOfQuestions();
      for (Question q : this.listOfQuestions) {
        if (q.isHard()) {
          this.hardCt++;
        }
      }
    } catch (IOException e) {
      this.errorMessage();
      System.exit(0);
      // maybe call showWelcomeScreen
    }
  }

  /**
   * Iterates through the list of questions and prompts user for input
   */
  public void showQuestions() {
    Scanner input = new Scanner(System.in);
    int currentInput = -1;
    ArrayList<Question> studyQuestions = this.getQuestionList(this.numQuestions);
    for (Question q : studyQuestions) {
      this.numQuestionsAnswered++;
      System.out.println(q.getQuestion());
      System.out.println(this.questionMessage());
      while (currentInput != 1 && currentInput != 2) {
        currentInput = input.nextInt();
        switch (currentInput) {
          case 1:
            if (q.isHard()) {
              this.questionHardToEasy++;
              this.hardCt--;
            }
            q.setHard(false);
            break;
          case 2:
            if (!q.isHard()) {
              this.questionEasyToHard++;
              this.hardCt++;
            }
            q.setHard(true);
            break;
          case 3:
            System.out.println(q.getAnswer());
            break;
          case 4:
            this.exitStudySession(this.path);
            System.exit(0);
            break;
          default:
            this.errorMessage();
            break;
        }
        currentInput = -1;
      }
    }
    this.exitStudySession(this.path);
  }

  /**
   * Returns the message with controls for each question
   *
   * @return welcome message
   */
  public String questionMessage() {
    return "1. Set easy 2. Set hard 3. Show answer 4. Exit";
  }

  /**
   * Returns a list of questions to be studied of a specified length with hard questions
   * coming first
   *
   * @return a list of questions to be studied
   */
  public ArrayList<Question> getQuestionList(int num) {
    if (num > this.listOfQuestions.size()) {
      throw new IllegalArgumentException("Not enough questions in file.");
    }
    ArrayList<Question> hardList = new ArrayList<>();
    ArrayList<Question> easyList = new ArrayList<>();
    for (Question q : this.listOfQuestions) {
      if (q.isHard()) {
        hardList.add(q);
      } else {
        easyList.add(q);
      }
    }
    Collections.shuffle(hardList);
    Collections.shuffle(easyList);
    hardList.addAll(easyList);

    // populate a new arraylist with the first num questions from the shuffled list
    ArrayList<Question> studyList = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      studyList.add(hardList.get(i));
    }
    return studyList;
  }

  /**
   * Exits the study session and writes the updated question file and show session stats
   */
  public void exitStudySession(String path) {
    System.out.println(showStats());
    QuestionFile qf = new QuestionFile("questions.sr", FileTime.from(Instant.now()),
        FileTime.from(Instant.now()));
    for (Question q : this.listOfQuestions) {
      qf.addQuestion(q);
    }
    Writer w = new Writer(path);
    w.writeFile(qf);
  }

  /**
   * Returns the stats of the study session
   *
   * @return stats of the study session
   */
  public String showStats() {
    return "----------------------------"
        + "\nNumber of questions answered: " + this.numQuestionsAnswered
        + "\nNumber of questions Easy -> Hard: " + this.questionEasyToHard
        + "\nNumber of questions Hard -> Easy: " + this.questionHardToEasy
        + "\nNumber of hard questions in file: " + this.hardCt
        + "\nNumber of east questions in file: " + (this.listOfQuestions.size() - this.hardCt);
  }

  /**
   * Returns the list of questions
   *
   * @return list of questions
   */
  public ArrayList<Question> getListOfQuestions() {
    return this.listOfQuestions;
  }

  /**
   * Returns the number of hard questions
   *
   * @return number of hard questions
   */
  public int getHardCt() {
    return this.hardCt;
  }

  /**
   * Returns an error message
   *
   * @return error message
   */
  public String errorMessage() {
    return "Invalid input. Please try again.";
  }

  /**
   * Returns a welcome message
   *
   * @return welcome message
   */
  public String welcomeMessage() {
    return "Welcome to Spaced Repetition!\nPlease input the path to your question file: ";
  }

  /**
   * Returns a message indicating that the file has been loaded
   *
   * @return message indicating that the file has been loaded
   */
  public String fileLoadedMessage() {
    return "\nYour question file has been loaded..."
        + "\nHow many question would you like to study out of "
        + this.listOfQuestions.size() + "?: ";
  }
}
