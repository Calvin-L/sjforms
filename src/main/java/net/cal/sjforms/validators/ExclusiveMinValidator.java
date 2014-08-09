package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

public class ExclusiveMinValidator<T, U extends Comparable<T>> implements Validator<T, T> {

  private final U min;

  public ExclusiveMinValidator(U min) {
    this.min = min;
  }

  @Override
  public T validate(String fieldName, T value) throws ValidationException {
    if (value == null) {
      return null;
    }
    if (min.compareTo(value) >= 0) {
      throw new ValidationException(fieldName, value, "must be greater than " + min);
    }
    return value;
  }

}
