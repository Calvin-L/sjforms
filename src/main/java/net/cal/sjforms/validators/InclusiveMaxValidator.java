package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

public class InclusiveMaxValidator<T, U extends Comparable<T>> implements Validator<T, T> {

  private final U max;

  public InclusiveMaxValidator(U max) {
    this.max = max;
  }

  @Override
  public T validate(String fieldName, T value) throws ValidationException {
    if (value == null) {
      return null;
    }
    if (max.compareTo(value) < 0) {
      throw new ValidationException(fieldName, value, "must be at most " + max);
    }
    return value;
  }

}
