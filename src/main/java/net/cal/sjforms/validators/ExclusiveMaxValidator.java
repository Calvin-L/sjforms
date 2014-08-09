package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

public class ExclusiveMaxValidator<T, U extends Comparable<T>> implements Validator<T, T> {

  private final U max;

  public ExclusiveMaxValidator(U max) {
    this.max = max;
  }

  @Override
  public T validate(String fieldName, T value) throws ValidationException {
    if (value == null) {
      return null;
    }
    if (max.compareTo(value) <= 0) {
      throw new ValidationException(fieldName, value, "must be less than " + max);
    }
    return value;
  }

}
