package net.cal.sjforms;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Thrown when a {@link net.cal.sjforms.Form} fails to validate. Includes a map from
 * {@link net.cal.sjforms.Field} to {@link net.cal.sjforms.ValidationException} with
 * an entry for each failure.
 */
public class FormValidationException extends Exception {

  private final Map<Field, ValidationException> failures;

  public FormValidationException(Map<Field, ValidationException> failures) {
    super("Form failed validation with " + failures.size() + " problems");
    this.failures = new HashMap<>(failures);
  }

  /**
   * Get a map from fields to associated failures. A field will have an entry in the
   * map if and only if it experienced a validation failure.
   * @return a map from fields to associated failures
   */
  public Map<Field, ValidationException> getFailures() {
    return Collections.unmodifiableMap(failures);
  }

}
