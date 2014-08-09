package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

public class ClampedMinValidator<T extends Object & Comparable<T>> implements Validator<T, T> {

  private final T min;

  public ClampedMinValidator(T min) {
    this.min = min;
  }

  @Override
  public T validate(String fieldName, T value) throws ValidationException {
    if (value == null) {
      return null;
    }
    if (min.compareTo(value) > 0) {
      return min;
    }
    return value;
  }

}
