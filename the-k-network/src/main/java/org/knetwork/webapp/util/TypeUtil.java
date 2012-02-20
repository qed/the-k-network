package org.knetwork.webapp.util;


import java.util.Scanner;

/**
 * The Class TypeUtil provides utility methods that determine whether Strings represent data of
 * certain types such as double, bigDecimal and integer without using exceptions.
 */
public class TypeUtil {
	
  /**
   * Checks if is double.
   *
   * @param  in the in
   * @return true, if is double
   */
  public static boolean isDouble(String in) {
    Scanner scanner = new Scanner(in);

    return scanner.hasNextDouble();
  }

  /**
   * Checks if the string is a Long
   * @param in
   * @return
   */
  public static boolean isLong(String in) {
    Scanner scanner = new Scanner(in);

    return scanner.hasNextLong();
  }

  /**
   * checks if the String can be a BigDecimal or not.
   *
   * @param  in
   * @return
   */
  public static boolean isBigDecimal(String in) {
    Scanner scanner = new Scanner(in);

    return scanner.hasNextBigDecimal();
  }

  /**
   * Checks if is Integer.
   *
   * @param  in the in
   * @return true, if is double
   */

  public static boolean isInteger(String in) {
    Scanner scanner = new Scanner(in);

    return scanner.hasNextInt();
  }
}
