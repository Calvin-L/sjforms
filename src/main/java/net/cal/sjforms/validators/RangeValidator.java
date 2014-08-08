package net.cal.sjforms.validators;

import net.cal.sjforms.ValidationException;
import net.cal.sjforms.Validator;

/**
 * A complex validator that lets you specify a minimum (inclusive or exclusive),
 * a maximum (inclusive or exclusive), and a clamping mode (clamped or throw-exception)
 * to limit a value to a particular range. It can be used with any type that
 * implements {@link java.lang.Comparable} (e.g. {@link java.lang.Integer},
 * {@link java.lang.String}, etc).
 */
public class RangeValidator<T extends Comparable<T>> implements Validator<T, T> {

  public static enum Cap {
    INCLUSIVE,
    EXCLUSIVE
  }

  public static enum Clamp {
    CLAMP_IF_OUT_OF_BOUNDS,
    FAIL_IF_OUT_OF_BOUNDS
  }

  private final T min;
  private final Cap minCap;
  private final T max;
  private final Cap maxCap;
  private final Clamp clamp;

  public RangeValidator(T min, Cap minCap, T max, Cap maxCap, Clamp clamp) {
    this.min = min;
    this.minCap = minCap;
    this.max = max;
    this.maxCap = maxCap;
    this.clamp = clamp;
  }

  @Override
  public T validate(String fieldName, T value) throws ValidationException {
    if (value == null) {
      return null;
    }
    if (minCap == Cap.INCLUSIVE && value.compareTo(min) < 0) {
      return tryReturnClamped(fieldName, min);
    } else if (minCap == Cap.EXCLUSIVE && value.compareTo(min) <= 0) {
      return tryReturnClamped(fieldName, min);
    }
    if (maxCap == Cap.INCLUSIVE && value.compareTo(max) > 0) {
      return tryReturnClamped(fieldName, max);
    } else if (maxCap == Cap.EXCLUSIVE && value.compareTo(max) >= 0) {
      return tryReturnClamped(fieldName, max);
    }
    return value;
  }

  private T tryReturnClamped(String fieldName, T value) throws ValidationException {
    if (clamp == Clamp.FAIL_IF_OUT_OF_BOUNDS) {
      fail(fieldName, value);
    }
    return value;
  }

  private void fail(String fieldName, T value) throws ValidationException {
    throw new ValidationException(fieldName, value, "not in bounds "
            + min + " (" + (minCap == Cap.INCLUSIVE ? "inclusive" : "exclusive") + ") to "
            + max + " (" + (maxCap == Cap.INCLUSIVE ? "inclusive" : "exclusive") + ")");
  }

}
