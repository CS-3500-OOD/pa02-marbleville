package cs3500.pa01;

import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a file with a name and dates created and last modified
 */
public abstract class FileType {
  private String name;
  private FileTime created;
  private FileTime lastModified;

  /**
   * Constructs a FileType object
   *
   * @param name         the name of the file
   * @param created      the created date of the file
   * @param lastModified the last modified date of the file
   */
  public FileType(String name, FileTime created, FileTime lastModified) {
    this.name = name;
    this.created = created;
    this.lastModified = lastModified;
  }

  /**
   * Returns the name of this MarkDown
   *
   * @return the name of this MarkDown
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the created date of this MarkDown
   *
   * @return the created date of this MarkDown
   */
  public FileTime getCreated() {
    return this.created;
  }

  /**
   * Returns the last modified date of this MarkDown
   *
   * @return the last modified date of this MarkDown
   */
  public FileTime getLastModified() {
    return this.lastModified;
  }

  /**
   * Returns the string representation of this MarkDown
   *
   * @return the string representation of this MarkDown
   */
  public abstract String toString();

  /**
   * Adds data from a string to this FileType object
   *
   * @param file the string to be parsed
   */
  public abstract void parseFile(String file);

  /**
   * Sets the name of this FileType object
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the last modified date of this FileType object
   */
  public void setCreated(FileTime created) {
    this.created = created;
  }

  /**
   * Sets the last modified date of this FileType object
   */
  public void setLastModified(FileTime lastModified) {
    this.lastModified = lastModified;
  }
}
