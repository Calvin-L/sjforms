package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

public class InclusiveMinValidator<T, U extends Comparable<T>> implements Validator<T, T> {

  private final U min;

  public InclusiveMinValidator(U min) {
    this.min = min;
  }

  @Override
  public T validate(String fieldName, T value) throws ValidationException {
    if (value == null) {
      return null;
    }
    if (min.compareTo(value) > 0) {
      throw new ValidationException(fieldName, value, "must be at least " + min);
    }
    return value;
  }

}
