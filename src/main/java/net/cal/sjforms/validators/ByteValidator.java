package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

/**
 * Applies {@link java.lang.Double#parseDouble(String)} to the input. Throws a
 * {@link net.cal.sjforms.ValidationException} when the parse fails.
 */
public class ByteValidator implements Validator<String, Byte> {
  @Override
  public Byte validate(String fieldName, String value) throws ValidationException {
    if (value == null) {
      return null;
    }
    try {
      return Byte.parseByte(value);
    } catch (NumberFormatException e) {
      throw new ValidationException(fieldName, value, "not a valid byte", e);
    }
  }
}
