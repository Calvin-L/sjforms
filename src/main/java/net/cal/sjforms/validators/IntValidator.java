package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

/**
 * Applies {@link java.lang.Integer#parseInt(String)} to the input. Throws a
 * {@link net.cal.sjforms.ValidationException} when the parse fails.
 */
public class IntValidator implements Validator<String, Integer> {
  @Override
  public Integer validate(String fieldName, String value) throws ValidationException {
    if (value == null) {
      return null;
    }
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      throw new ValidationException(fieldName, value, "not a valid int", e);
    }
  }
}
