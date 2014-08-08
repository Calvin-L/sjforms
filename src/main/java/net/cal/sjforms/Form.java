package net.cal.sjforms;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * A form is constructed out of {@link net.cal.sjforms.Field}s and implements
 * {@link #parse(java.util.Map)} to convert a map of key-value pairs into a
 * validated {@link ParseResult}.
 *
 * <p>Example:
 * <pre>
 *   Field&lt;String&gt; INPUT_FIELD = Field.stringField("input");
 *   Field&lt;Integer&gt; LIMIT_FIELD = Field.intField("limit").required();
 *   Form FORM = new Form(INPUT_FIELD, LIMIT_FIELD);
 *
 *   // ...
 *
 *   ParseResult parseResult;
 *   try {
 *     parseResult = FORM.parse(inputMap);
 *   } catch (ValidationException e) {
 *     logger.error("Input validation failed with message: " + e.getMessage(), e);
 *     return;
 *   }
 *   String input = parseResult.getParsedValue(INPUT_FIELD);
 *   int limit = parseResult.getParsedValue(LIMIT_FIELD);
 * </pre></p>
 *
 * For more examples of constructing fields, see the docs on
 * {@link net.cal.sjforms.Field}.
 */
public class Form {

  private final List<Field> fields;

  /**
   * Array form of {@link #Form(Iterable)}.
   * @param fields the fields to use
   */
  public Form(Field... fields) {
    this(Arrays.asList(fields));
  }

  /**
   * Construct a form with the given fields.
   * @param fields the fields to use
   */
  public Form(Iterable<Field> fields) {
    this.fields = Lists.newArrayList(fields);
  }

  /**
   * Get the fields of this form.
   * @return this form's fields, in order
   */
  public List<Field> getFields() {
    return Collections.unmodifiableList(fields);
  }

  /**
   * Array form of {@link #extend(Iterable)}.
   * @param fields the extra fields
   * @return a new form with all the fields from this one plus the ones given
   */
  public Form extend(Field... fields) {
    return extend(Arrays.asList(fields));
  }

  /**
   * Construct a new form with additional fields.
   * @param fields the extra fields
   * @return a new form with all the fields from this one plus the ones given
   */
  public Form extend(Iterable<Field> fields) {
    return new Form(Iterables.concat(this.fields, fields));
  }

  /**
   * Parse some form inputs. Stops immediately if any field fails to validate.
   * @param values the raw values to parse
   * @return a {@link net.cal.sjforms.ParseResult} with parsed values. Each entry in the
   *         map is parsed by the corresponding field. (If there are multiple fields with
   *         the same name on this form, only the first one is used.) Any entries which do
   *         not have corresponding fields will not be included in the final result.
   * @throws ValidationException if any validator on any field fails. Note that fields are
   *         checked in the same order that they were provided during construction of this
   *         form (i.e. the same order returned by {@link #getFields()}).
   * @see #parseAll(java.util.Map)
   */
  public ParseResult parse(Map<String, String> values) throws ValidationException {
    Map<Field, Object> results = new HashMap<>();
    for (Field field : fields) {
      String value = values.get(field.getName());
      Object result = field.validate(value);
      if (result != null) {
        results.put(field, result);
      }
    }
    return new ParseResult(results);
  }

  /**
   * Parse some form inputs, but do not stop immediately if a field fails to validate.
   * @param values the raw values to parse
   * @return a {@link net.cal.sjforms.ParseResult} with parsed values. Each entry in the
   *         map is parsed by the corresponding field. (If there are multiple fields with
   *         the same name on this form, only the first one is used.) Any entries which do
   *         not have corresponding fields will not be included in the final result.
   * @throws FormValidationException if any validator on any field fails. The thrown
   *         exception contains all of the individual validation failures.
   * @see #parse(java.util.Map)
   */
  public ParseResult parseAll(Map<String, String> values) throws FormValidationException {
    Map<Field, Object> results = new HashMap<>(fields.size());
    Map<Field, ValidationException> failures = new HashMap<>(fields.size());
    for (Field field : fields) {
      String value = values.get(field.getName());
      Object result = null;
      try {
        result = field.validate(value);
      } catch (ValidationException e) {
        failures.put(field, e);
      }
      if (result != null) {
        results.put(field, result);
      }
    }
    if (!failures.isEmpty()) {
      throw new FormValidationException(failures);
    }
    return new ParseResult(results);
  }

}
