package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

/**
 * Only allows non-null values
 * @param <T>
 */
public class RequiredValidator<T> implements Validator<T,T> {
  @Override
  public T validate(String fieldName, T value) throws ValidationException {
    if (value == null) {
      throw new ValidationException(fieldName, "missing value");
    }
    return value;
  }
}
