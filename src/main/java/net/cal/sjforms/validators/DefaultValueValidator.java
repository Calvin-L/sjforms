package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

/**
 * Returns the given default value if the input is null.
 * @param <T>
 */
public class DefaultValueValidator<T> implements Validator<T,T> {

  private final T defaultValue;

  public DefaultValueValidator(T defaultValue) {
    this.defaultValue = defaultValue;
  }

  @Override
  public T validate(String fieldName, T value) throws ValidationException {
    return value == null ? defaultValue : value;
  }

}
