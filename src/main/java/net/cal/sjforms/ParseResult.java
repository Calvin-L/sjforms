package net.cal.sjforms;

import java.util.HashMap;
import java.util.Map;

/**
 * The result of using a {@link net.cal.sjforms.ValidationException} to parse some inputs.
 */
public class ParseResult {

  private final Map<Field, Object> values;

  /**
   * Construct a ParseResult.
   * @param values A heterogeneous map from Field&lt;T&gt; to T. The caller
   *               MUST guarantee that fields are paired with values of the
   *               correct types.
   */
  public ParseResult(Map<Field, Object> values) {
    this.values = new HashMap<>(values);
  }

  /**
   * Get a parsed value from this ParseResult.
   * @param field the field to get a value for
   * @param <T> the type of value
   * @return The parsed value (if it exists in this result). This returns
   *         null when the field is optional and no value was provided for
   *         its parameter, and when the field is not a part of the form
   *         that produced this result.
   */
  @SuppressWarnings("unchecked")
  public <T> T getParsedValue(Field<T> field) {
    // this cast is safe provided that the constructor's condition was met
    return (T)values.get(field);
  }

}
