package cs3500.pa01;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class QuestionFileReader extends FileSystemReader<QuestionFile> {
  @Override
  public FileVisitResult visitFile(Path filePath, BasicFileAttributes attr) {
    if (attr.isRegularFile() && filePath.toString().endsWith(".sr")) {
      this.readFile(filePath, attr);
    }
    return FileVisitResult.CONTINUE;
  }

  @Override
  public void readFile(Path filePath, BasicFileAttributes attr) {
    String[] pathArr = filePath.toString().split("/");
    String fileName = pathArr[pathArr.length - 1];
    QuestionFile qu = new QuestionFile(fileName, attr.creationTime(), attr.lastModifiedTime());
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
    qu.parseFile(file.toString());
    this.addFile(qu);
  }
}
