package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

/**
 * Runs {@link java.lang.Boolean#parseBoolean(String)} on the input.
 */
public class BooleanValidator implements Validator<String, Boolean> {
  @Override
  public Boolean validate(String fieldName, String value) throws ValidationException {
    if (value == null) {
      return null;
    }
    return Boolean.parseBoolean(value);
  }
}
