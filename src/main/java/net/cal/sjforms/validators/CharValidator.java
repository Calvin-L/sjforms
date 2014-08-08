package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

/**
 * Extracts the first character of the input string. Throws a
 * {@link net.cal.sjforms.ValidationException} when the input
 * is not exactly one character long.
 */
public class CharValidator implements Validator<String, Character> {
  @Override
  public Character validate(String fieldName, String value) throws ValidationException {
    if (value == null) {
      return null;
    }
    if (value.length() != 1) {
      throw new ValidationException(fieldName, value, "not a single character");
    }
    return value.charAt(0);
  }
}
