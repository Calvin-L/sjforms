package net.cal.sjforms;

/**
 * Thrown when a value passed to a {@link net.cal.sjforms.Validator} fails validation.
 * Includes both the name of the field that failed validation ({@link #getFieldName()})
 * and the value that caused the error ({@link #getValue()}).
 */
public class ValidationException extends Exception {

  private final String fieldName;
  private final Object value;

  public ValidationException(String fieldName, String message) {
    super("Field '" + fieldName + "': " + message);
    this.fieldName = fieldName;
    this.value = null;
  }

  public ValidationException(String fieldName, Object value, String message) {
    super("Value '" + value + "' for field '" + fieldName + "': " + message);
    this.fieldName = fieldName;
    this.value = value;
  }

  public ValidationException(String fieldName, String message, Throwable cause) {
    super("Field '" + fieldName + "': " + message, cause);
    this.fieldName = fieldName;
    this.value = null;
  }

  public ValidationException(String fieldName, Object value, String message, Throwable cause) {
    super("Value '" + value + "' for field '" + fieldName + "': " + message, cause);
    this.fieldName = fieldName;
    this.value = value;
  }

  /**
   * Get the name of the field that caused this exception.
   * @return the name of the field that caused this exception
   */
  public String getFieldName() {
    return fieldName;
  }

  /**
   * Get the illegal value that caused this exception. This will return the most
   * processed form of the value available; clients wishing to recover the original
   * string that caused the problem must keep that information themselves (although
   * in many cases <code>e.getValue().toString()</code> will be close enough).
   * @return the illegal value that caused this exception
   */
  public Object getValue() {
    return value;
  }

}
