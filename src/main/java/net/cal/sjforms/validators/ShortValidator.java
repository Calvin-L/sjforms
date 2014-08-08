package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

/**
 * Applies {@link Short#parseShort(String)} to the input. Throws a
 * {@link net.cal.sjforms.ValidationException} when the parse fails.
 */
public class ShortValidator implements Validator<String, Short> {
  @Override
  public Short validate(String fieldName, String value) throws ValidationException {
    if (value == null) {
      return null;
    }
    try {
      return Short.parseShort(value);
    } catch (NumberFormatException e) {
      throw new ValidationException(fieldName, value, "not a valid short", e);
    }
  }
}
