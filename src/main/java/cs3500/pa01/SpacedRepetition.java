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
  int hardCt = 0;

  int numQuestions = 0;


  public void showWelcomeScreen() {
    System.out.println("Welcome to Spaced Repetition!");
    System.out.print("Please input the path to your question file: ");

    Scanner input = new Scanner(System.in);

    String path = input.nextLine();

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
      System.out.println(q.getQuestion());
      while (currentInput != 1 || currentInput != 2) {
        currentInput = input.nextInt();
        switch (currentInput) {
          case 1:
            q.setHard(false);
            break;
          case 2:
            q.setHard(true);
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
  }

  public ArrayList<Question> getQuestionList() {
    ArrayList<Question> newList = new ArrayList<>(this.listOfQuestions);
    Collections.shuffle(newList);
    return new ArrayList<>(newList.subList(0, this.numQuestions));
  }

  public void exitStudySession() {
    this.showStats();
    System.exit(0);
  }

  public void showStats() {
    System.out.println("You got " + this.hardCt + " questions wrong.");
  }
}
