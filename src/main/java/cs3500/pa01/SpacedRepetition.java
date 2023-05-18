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


  public void showWelcomeScreen() {
    System.out.println("Welcome to Spaced Repetition!");
    System.out.print("Please input the path to your question file: ");

    Scanner input = new Scanner(System.in);

    this.path = input.nextLine();

    QuestionFile qf = new QuestionFile(path.substring(path.lastIndexOf("/") + 1),
        FileTime.from(Instant.now()), FileTime.from(Instant.now()));
    try {
      String file = Files.readString(Path.of(path));
      qf.parseFile(file);
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }

    this.listOfQuestions = qf.getListOfQuestions();
    for (Question q : this.listOfQuestions) {
      if (q.isHard()) {
        this.hardCt++;
      }
    }

    System.out.println("\nYour question file has been loaded...");
    System.out.println("How many question would you like to study out of "
        + this.listOfQuestions.size() + "?");
    if (input.nextInt() <= this.listOfQuestions.size()) {
      this.numQuestions = input.nextInt();
    } else {
      System.out.println("No enough questions in file.");
      System.exit(0);
    }
  }

  public void showQuestions() {
    Scanner input = new Scanner(System.in);
    int currentInput = -1;
    for (Question q : this.getQuestionList()) {
      this.numQuestionsAnswered++;
      System.out.println(q.getQuestion());
      while (currentInput != 1 || currentInput != 2) {
        currentInput = input.nextInt();
        switch (currentInput) {
          case 1:
            q.setHard(false);
            if (q.isHard()) {
              this.questionHardToEasy++;
              this.hardCt--;
            }
            break;
          case 2:
            q.setHard(true);
            if (!q.isHard()) {
              this.questionEasyToHard++;
              this.hardCt++;
            }
            break;
          case 3:
            System.out.println(q.getAnswer());
            break;
          case 4:
            this.exitStudySession();
            break;
          default:
            System.out.println("Invalid input. Please try again.");
            break;
        }
      }
    }
    this.exitStudySession();
  }

  public ArrayList<Question> getQuestionList() {
    ArrayList<Question> newList = new ArrayList<>(this.listOfQuestions);
    Collections.shuffle(newList);
    return new ArrayList<>(newList.subList(0, this.numQuestions));
  }

  public void exitStudySession() {
    this.showStats();
    QuestionFile qf = new QuestionFile("questions.sr", FileTime.from(Instant.now()),
        FileTime.from(Instant.now()));
    for (Question q : this.listOfQuestions) {
      qf.addQuestion(q);
    }
    Writer w = new Writer(path);
    w.writeFile(qf);
    System.exit(0);
  }

  public void showStats() {
    System.out.println("----------------------------");
    System.out.println("Number of questions answered: " + this.numQuestionsAnswered);
    System.out.println("Number of questions Easy -> Hard: " + this.questionEasyToHard);
    System.out.println("Number of questions Hard -> Easy: " + this.questionHardToEasy);
    System.out.println("Number of hard questions in file: " + this.hardCt);
    System.out.println(
        "Number of east questions in file: " + (this.listOfQuestions.size() - this.hardCt));
  }
}
