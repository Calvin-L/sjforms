package net.cal.sjforms;

import java.util.Arrays;

public class Basics {

  private static final Field<Integer> LIMIT_FIELD = Field.intField("limit").withDefault(10).clampMin(0).clampMax(100);

  public static void main(String[] args) {

    for (String input : Arrays.asList("0", "50", "-10", "1000", "x", null)) {
      String output;
      try {
        Integer out = LIMIT_FIELD.validate(input);
        output = out == null ? null : out.toString();
      } catch (ValidationException e) {
        output = "ERROR: " + e.getMessage();
      }
      System.out.println("Validation for '" + input + "' = " + output);
    }

  }

}
