# SJForms (Simple Java Forms)

This is a small declarative-style library for validating untrusted input in Java. It is useful for checking HTTP request parameters, command-line parameters, etc.

Usage is fairly straightforward. First declare the structure of your form (usually as static class members):

    Field<String> INPUT_FIELD = Field.stringField("input");
    Field<Integer> LIMIT_FIELD = Field.intField("limit").required();
    Form FORM = new Form(INPUT_FIELD, LIMIT_FIELD);
 
Next Load some input and validate it:
 
    // somehow acquire a Map<String, String> (from field names to input values) called inputMap
 
    ParseResult parseResult;
    try {
      parseResult = FORM.parse(inputMap);
    } catch (ValidationException e) {
      System.err.println("Input validation failed with message: " + e.getMessage());
      return;
    }

    String input = parseResult.getParsedValue(INPUT_FIELD);
    int limit = parseResult.getParsedValue(LIMIT_FIELD);

If the `Form`/`ParseResult` API is too heavy-handed for you, you can just use the `Field` objects to validate data directly:

    // somehow acquire a String called limitString
    
    int limit;
    try {
      limit = LIMIT_FIELD.validate(limitString);
    } catch (ValidationException e) {
      System.err.println("Input validation failed with message: " + e.getMessage());
      return;
    }

The library is totally threadsafe (i.e. the same `Form`s, `Field`s, and other classes can be used from multiple threads concurrently).

## Building

This project uses the [Gradle](http://www.gradle.org/) build system. It was made using Gradle 2.0, but the build files are very simple and earlier/later versions of Gradle should work just fine. To build:

    $ gradle build

This will generate jar files in build/libs and examples/build/libs. You can also generate javadoc with Gradle:

    $ gradle javadoc
