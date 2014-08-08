package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

/**
 * Applies {@link Float#parseFloat(String)} to the input. Throws a
 * {@link net.cal.sjforms.ValidationException} when the parse fails.
 */
public class FloatValidator implements Validator<String, Float> {
  @Override
  public Float validate(String fieldName, String value) throws ValidationException {
    if (value == null) {
      return null;
    }
    try {
      return Float.parseFloat(value);
    } catch (NumberFormatException e) {
      throw new ValidationException(fieldName, value, "not a valid float", e);
    }
  }
}
