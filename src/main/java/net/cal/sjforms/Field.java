package net.cal.sjforms;

import net.cal.sjforms.validators.*;

import java.util.Arrays;

/**
 * A field is responsible for parsing a string into a validated object. This class
 * contains many convenience methods for quickly constructing fields that perform
 * complex validation.
 *
 * <p>Example: create a field for an optional string parameter called "input":
 * <pre>
 *   Field&lt;String&gt; INPUT_FIELD = Field.stringField("input");
 * </pre></p>
 *
 * <p>Example: create a field for a required int parameter called "limit":
 * <pre>
 *   Field&lt;Integer&gt; LIMIT_FIELD = Field.intField("limit").required();
 * </pre></p>
 *
 * <p>Example: create a field for an optional double parameter called "x" that defaults to 0:
 * <pre>
 *   Field&lt;Double&gt; X_FIELD = Field.doubleField("x").withDefault(0.0);
 * </pre></p>
 *
 * <p>Example: create a field for a required int parameter called "y" that can only be 0, 2, or 4:
 * <pre>
 *   Field&lt;Integer&gt; Y_FIELD = Field.doubleField("y").required().oneOf(0, 2, 4);
 * </pre></p>
 *
 * <p>Example: create a field for an optional {@link java.util.Locale} parameter called "locale"
 * (you can also use this syntax to add arbitrary custom validators):
 * <pre>
 *   Field&lt;Locale&gt; LOCALE_FIELD = Field.named("locale").withValidator(new LocaleValidator());
 * </pre></p>
 *
 * Note that validators are applied in order, and this can sometimes matter. For instance, it is
 * usually a good idea to apply the {@link #withDefault(Object)} validator <em>before</em> the
 * {@link #oneOf(Object[])} validator, otherwise the latter will throw an exception (unless of
 * course <code>null</code> is in the list of approved values).
 *
 * @param <T> the type of validated result this field returns
 */
public abstract class Field<T> {

  private static final Validator<String, Byte> BYTE_VALIDATOR = new ByteValidator();
  private static final Validator<String, Short> SHORT_VALIDATOR = new ShortValidator();
  private static final Validator<String, Integer> INT_VALIDATOR = new IntValidator();
  private static final Validator<String, Long> LONG_VALIDATOR = new LongValidator();
  private static final Validator<String, Double> DOUBLE_VALIDATOR = new DoubleValidator();
  private static final Validator<String, Float> FLOAT_VALIDATOR = new FloatValidator();
  private static final Validator<String, Boolean> BOOLEAN_VALIDATOR = new BooleanValidator();
  private static final Validator<String, Character> CHAR_VALIDATOR = new CharValidator();
  private static final Validator REQUIRED_VALIDATOR = new RequiredValidator();

  public static Field<String> named(final String name) {
    return new Field<String>() {
      @Override
      public String getName() {
        return name;
      }

      @Override
      public String validate(String value) {
        return value;
      }
    };
  }

  public <B> Field<B> withValidator(final Validator<T, B> validator) {
    final Field<T> parent = this;
    return new Field<B>() {
      @Override
      public String getName() {
        return parent.getName();
      }

      @Override
      public B validate(String value) throws ValidationException {
        return validator.validate(getName(), parent.validate(value));
      }
    };
  }

  public static Field<String> stringField(String name) {
    return named(name);
  }

  public static Field<Byte> byteField(String name) {
    return named(name).withValidator(BYTE_VALIDATOR);
  }

  public static Field<Short> shortField(String name) {
    return named(name).withValidator(SHORT_VALIDATOR);
  }

  public static Field<Integer> intField(String name) {
    return named(name).withValidator(INT_VALIDATOR);
  }

  public static Field<Long> longField(String name) {
    return named(name).withValidator(LONG_VALIDATOR);
  }

  public static Field<Double> doubleField(String name) {
    return named(name).withValidator(DOUBLE_VALIDATOR);
  }

  public static Field<Float> floatField(String name) {
    return named(name).withValidator(FLOAT_VALIDATOR);
  }

  public static Field<Boolean> boolField(String name) {
    return named(name).withValidator(BOOLEAN_VALIDATOR);
  }

  public static Field<Character> charField(String name) {
    return named(name).withValidator(CHAR_VALIDATOR);
  }

  @SuppressWarnings("unchecked")
  public Field<T> required() {
    // this is safe because REQUIRED_VALIDATOR does not read the value other than to check whether it is null
    return withValidator((Validator<T,T>)REQUIRED_VALIDATOR);
  }

  public Field<T> withDefault(T defaultValue) {
    return withValidator(new DefaultValueValidator<>(defaultValue));
  }

  @SafeVarargs
  public final Field<T> oneOf(T... values) {
    return oneOf(Arrays.asList(values));
  }

  public Field<T> oneOf(Iterable<T> values) {
    return withValidator(new EnumValidator<>(values));
  }

  public abstract String getName();
  public abstract T validate(String value) throws ValidationException;

}
