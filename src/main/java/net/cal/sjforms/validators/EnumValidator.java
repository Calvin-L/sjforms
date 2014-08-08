package net.cal.sjforms.validators;

import com.google.common.collect.Sets;
import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

import java.util.Set;

/**
 * Ensures the output is one of the given values. If not, it throws a
 * {@link net.cal.sjforms.ValidationException}.
 * @param <T>
 */
public class EnumValidator<T> implements Validator<T,T> {

  public final Set<T> legalValues;

  public EnumValidator(Iterable<T> legalValues) {
    this.legalValues = Sets.newHashSet(legalValues);
  }

  @Override
  public T validate(String fieldName, T value) throws ValidationException {
    if (value == null) {
      return null;
    }
    if (!legalValues.contains(value)) {
      throw new ValidationException(fieldName, value, "must be one of " + legalValues);
    }
    return value;
  }

}
