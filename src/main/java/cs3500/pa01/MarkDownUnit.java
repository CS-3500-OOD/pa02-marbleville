package cs3500.pa01;

/**
 * Represents a unit of text and its tag in a MarkDown
 */
public class MarkDownUnit {
  private String text;
  private String tag;

  /**
   * Constructs a MarkDownUnit with the given text and tag
   *
   * @param text the text of this MarkDownUnit
   * @param tag  the tag of this MarkDownUnit
   */
  public MarkDownUnit(String text, String tag) {
    this.text = text;
    if (tag.startsWith("#")) {
      this.tag = tag;
    } else if (tag.startsWith("[[")) {
      this.tag = "[[";
    } else {
      throw new IllegalArgumentException("Invalid tag");
    }
  }

  /**
   * Returns the text of this MarkDownUnit
   *
   * @return the text of this MarkDownUnit
   */
  public String getText() {
    return this.text;
  }

  /**
   * Returns the tag of this MarkDownUnit
   *
   * @return the tag of this MarkDownUnit
   */
  public String getTag() {
    return this.tag;
  }

  /**
   * Parses the given string into a MarkDownUnit
   * Assumes only one tag per input string
   *
   * @param s the string to be parsed
   * @return MarkDownUnit with associated text and tags
   */
  public static MarkDownUnit stringToMarkDownUnit(String s) {
    String text = "";
    String tag = "";

    if (s.startsWith("#")) {
      String[] lineArray = s.split(" ");
      tag = lineArray[0];
      StringBuilder sb = new StringBuilder();
      for (String str : lineArray) {
        if (!str.equals(tag)) {
          sb.append(str);
          sb.append(" ");
        }
      }
      text = sb.toString().trim();
    } else if (s.startsWith("[[")) {
      tag = "[[";
      text = s.substring(s.indexOf("[[") + 2, s.indexOf("]]"));
    } else {
      throw new IllegalArgumentException("Invalid input string");
    }

    return new MarkDownUnit(text, tag);
  }

  /**
   * Returns the string representation of this MarkDownUnit
   *
   * @return string representation of this MarkDownUnit
   */
  public String toString() {
    if (tag.startsWith("#")) {
      return tag + " " + text + "\n";
    } else {
      return "- " + text + "\n";
    }
  }
}
