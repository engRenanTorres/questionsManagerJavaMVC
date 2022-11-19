package br.com.engrenantorres.questionmanager.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckStrings {

  public static Boolean isEmail(String string) {
    Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
    Matcher mat = pattern.matcher(string);

    if (!mat.matches()) {
      return false;
    }
    return true;
  }

}
