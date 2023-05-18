package cs3500.pa01;

import java.nio.file.attribute.FileTime;
import java.util.ArrayList;

/**
 * Represents a MarkDown file with text and tags
 */
public class MarkDown extends FileType {
  private ArrayList<MarkDownUnit> listOfMarkDown = new ArrayList<>();

  public MarkDown(String name, FileTime created, FileTime lastModified) {
    super(name, created, lastModified);
  }

  /**
   * Parses a string into a MarkDown object
   *
   * @param file the string to be parsed
   */
  public void parseFile(String file) {
    String[] mdArray = file.split("\n");
    for (int i = 0; i < mdArray.length; i++) {
      String line = mdArray[i];
      // If a line contains "[[" but not "]]", then it is a point that spans multiple lines
      // and must be put together
      if (line.contains("[[") && !line.contains("]]")) {
        line = mdArray[i] + mdArray[i + 1];
        i++;
      }
      this.tokenizeLine(line);
    }
  }

  /**
   * Adds a MarkDownUnit to the list of MarkDownUnits of this MarkDown
   *
   * @param mdUnit the MarkDownUnit to be added
   */
  public void addMarkDownUnit(MarkDownUnit mdUnit) {
    this.listOfMarkDown.add(mdUnit);
  }

  /**
   * Tokenizes a line of text into MarkDownUnits and adds them to the list of MarkDownUnits
   *
   * @param line to be tokenized
   */
  public void tokenizeLine(String line) {
    String[] lineArray = line.split("]]");
    for (String s : lineArray) {
      if (s.contains("[[")) {
        s = s.substring(s.indexOf("[["));
        s += "]]";
      }
      if (s.startsWith("#") || s.startsWith("[[")) {
        this.addMarkDownUnit(MarkDownUnit.stringToMarkDownUnit(s));
      }
    }
  }

  /**
   * Returns the string representation of this MarkDown
   *
   * @return the string representation of this MarkDown
   */
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < this.listOfMarkDown.size(); i++) {

      if (i == listOfMarkDown.size() - 1 || listOfMarkDown.get(i + 1).getTag().contains("#")) {
        String unitString = this.listOfMarkDown.get(i).toString() + "\n";
        result.append(unitString);
      } else {
        String unitString = this.listOfMarkDown.get(i).toString();
        result.append(unitString);
      }
    }
    return result.toString();
  }


  /**
   * Returns the list of MarkDownUnits of this MarkDown
   *
   * @return the list of MarkDownUnits of this MarkDown
   */
  public ArrayList<MarkDownUnit> getUnits() {
    return this.listOfMarkDown;
  }
}
