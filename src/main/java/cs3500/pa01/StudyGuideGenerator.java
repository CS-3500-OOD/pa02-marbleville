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
  private Writer fileWriter;

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
    this.fileWriter = new Writer(outputPath);
  }

  /**
   * Generates and writes a study guide
   */
  public void generateStudyGuide() {
    try {
      Files.walkFileTree(inputPath, markDownReader);
      studyGuide = markDownReader.toSingleMarkDown();
      this.fileWriter.writeFile(this.studyGuide);
    } catch (IOException e) {
      System.out.println(FileSystemReader.errorMessage(e));
      e.printStackTrace();
    }
  }
}
