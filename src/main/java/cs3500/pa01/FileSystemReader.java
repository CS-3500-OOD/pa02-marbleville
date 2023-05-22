package cs3500.pa01;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Abstract class to read and process the file system
 */
public abstract class FileSystemReader<T> implements FileVisitor<Path> {
  private ArrayList<T> files = new ArrayList<>();

  /**
   * Adds a given file to the list of files.
   *
   * @param file to be added
   */
  public void addFile(T file) {
    this.files.add(file);
  }

  @Override
  public abstract FileVisitResult visitFile(Path filePath, BasicFileAttributes attr);

  public abstract void readFile(Path filePath, BasicFileAttributes attr);

  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exec) {
    return FileVisitResult.CONTINUE;
  }

  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
      throws IOException {
    return FileVisitResult.CONTINUE;
  }

  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    System.err.println(errorMessage(exc));
    return FileVisitResult.CONTINUE;
  }

  /**
   * Returns the list of files.
   *
   * @return list of files
   */
  public ArrayList<T> getFiles() {
    return this.files;
  }

  /**
   * Returns an error message.
   *
   * @param err the error
   * @return the error message
   */
  public static String errorMessage(IOException err) {
    return err.toString();
  }
}
