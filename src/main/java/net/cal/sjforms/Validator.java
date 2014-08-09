package net.cal.sjforms;

/**
 * A validator takes an In as input and may produce a validated Out as output.
 * If validation fails, it can throw a {@link net.cal.sjforms.ValidationException}.
 *
 *
 * <p>Instances of this class can act as validators (if they emit the exact input object
 * but throw validation exceptions under some conditions -- see e.g.
 * {@link net.cal.sjforms.validators.RequiredValidator}) or as transformers (if they emit a different
 * object from the given input -- see e.g. {@link net.cal.sjforms.validators.DoubleValidator}).
 * Some complex validators do a little of each (although that is generally not the case with the
 * builtin validators).</p>
 * @param <In> the input type
 * @param <Out> the output type
 */
public interface Validator<In, Out> {

  /**
   * Parse the given input value and produce a valid output object.
   * @param fieldName
   * @param value
   * @return the parsed object. NOTE: excluding rare cases (e.g. {@link net.cal.sjforms.validators.RequiredValidator}
   *         and {@link net.cal.sjforms.validators.DefaultValueValidator}), validators return null when the input
   *         value is null. In this way, you can attach arbitrary validators to optional fields.
   * @throws ValidationException when the input value is malformed.
   */
  Out validate(String fieldName, In value) throws ValidationException;

}
