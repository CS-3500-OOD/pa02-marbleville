package cs3500.pa01;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Scanner;

/**
 * A class that reads in a file system and creates a list of MarkDown files.
 */
public class MarkDownReader extends FileSystemReader<MarkDown> {
  private SortByX sortBy;

  public MarkDownReader(SortOrder sortBy) {
    this.sortBy = sortBy.getSortBy();
  }


  @Override
  public FileVisitResult visitFile(Path filePath, BasicFileAttributes attr) {
    if (attr.isRegularFile() && filePath.toString().endsWith(".md")) {
      this.readFile(filePath, attr);
    }
    return FileVisitResult.CONTINUE;
  }

  /**
   * Reads in a file and creates a MarkDown file to add to the list of files.
   *
   * @param filePath the path of the file
   * @param attr     the attributes of the file
   */
  public void readFile(Path filePath, BasicFileAttributes attr) {
    String[] pathArr = filePath.toString().split("/");
    String fileName = pathArr[pathArr.length - 1];
    MarkDown md = new MarkDown(fileName, attr.creationTime(), attr.lastModifiedTime());
    StringBuilder file = new StringBuilder();
    try {
      Scanner scanner = new Scanner(filePath);

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine() + "\n";
        file.append(line);
      }
      scanner.close();
    } catch (IOException e) {
      System.out.println(errorMessage(e));
    }
    md.parseFile(file.toString());
    this.addFile(md);
  }

  /**
   * Converts the list of MarkDown files into a single MarkDown file.
   *
   * @return a single MarkDown file
   */
  public MarkDown toSingleMarkDown() {
    this.getFiles().sort(sortBy);
    MarkDown md =
        new MarkDown("studyGuide",
            FileTime.from(Instant.now()),
            FileTime.from(Instant.now()));
    for (MarkDown file : this.getFiles()) {
      for (MarkDownUnit unit : file.getUnits()) {
        md.addMarkDownUnit(unit);
      }
    }
    return md;
  }
}
