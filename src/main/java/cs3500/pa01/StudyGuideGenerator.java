package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Coordinates the generation of a study guide
 */
public class StudyGuideGenerator {
  private MarkDown studyGuide;
  private MarkDownReader markDownReader;
  private Path inputPath;
  private Writer markDownWriter;

  private Writer questionsWriter;

  /**
   * Constructs a StudyGuideGenerator
   *
   * @param inputPath  the path to the input directory
   * @param sortBy     the sort order
   * @param outputPath the path to the output file
   */
  public StudyGuideGenerator(String inputPath, SortOrder sortBy, String outputPath) {
    this.markDownReader = new MarkDownReader(sortBy);
    this.inputPath = Path.of(inputPath);
    this.markDownWriter = new Writer(outputPath);
    String questionPath = outputPath.substring(0, outputPath.lastIndexOf("/") + 1)
        + "questions.md";
    this.questionsWriter = new Writer(questionPath);
  }

  /**
   * Generates and writes a study guide
   */
  public void generateStudyGuide() {
    try {
      Files.walkFileTree(inputPath, markDownReader);
      studyGuide = markDownReader.toSingleMarkDown();
      this.markDownWriter.writeFile(this.studyGuide);
      this.questionsWriter.writeFile(this.studyGuide.getQuestionFile());
    } catch (IOException e) {
      System.out.println(FileSystemReader.errorMessage(e));
      e.printStackTrace();
    }
  }
}
