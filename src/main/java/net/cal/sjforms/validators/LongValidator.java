package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

/**
 * Applies {@link java.lang.Long#parseLong(String)} to the input. Throws a
 * {@link net.cal.sjforms.ValidationException} when the parse fails.
 */
public class LongValidator implements Validator<String, Long> {
  @Override
  public Long validate(String fieldName, String value) throws ValidationException {
    if (value == null) {
      return null;
    }
    try {
      return Long.parseLong(value);
    } catch (NumberFormatException e) {
      throw new ValidationException(fieldName, value, "not a valid long", e);
    }
  }
}
