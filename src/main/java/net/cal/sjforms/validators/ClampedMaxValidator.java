package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

public class ClampedMaxValidator<T extends Object & Comparable<T>> implements Validator<T, T> {

  private final T max;

  public ClampedMaxValidator(T max) {
    this.max = max;
  }

  @Override
  public T validate(String fieldName, T value) throws ValidationException {
    if (value == null) {
      return null;
    }
    if (max.compareTo(value) > 0) {
      return max;
    }
    return value;
  }

}
