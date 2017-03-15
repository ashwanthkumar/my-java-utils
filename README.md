[![Build Status](https://snap-ci.com/ashwanthkumar/my-java-utils/branch/master/build_image)](https://snap-ci.com/ashwanthkumar/my-java-utils/branch/master)

# my-java-utils
Set of Java utils that I take along with my OSS java projects. Most of them are influenced from [Scala](http://scala-lang.org/) world and [Guava](https://github.com/google/guava) library. Library has zero external dependencies. If you find some implementations can be better, please do let me know.

## Features
### List
- Lists#map
- Lists#filter
- Lists#foldL
- Lists#find
- Lists#mkString
- Lists#Nil
- Lists#concat
- Lists#flatten
- Lists#take
- Lists#takeWhile

### Set
- Sets#copy
- Sets#isEmpty
- Sets#nonEmpty

### Iterable
- Iterables#exists
- Iterables#forall
- Iterables#head
- Iterables#headOption
- Iterables#isEmpty
- Iterables#nonEmpty
- Iterables#toMap
- Iterables#foreach

### Maps
- Maps#of
- Maps#builder
- Maps#empty
- Maps#getOrElse

### Lang
- Option / Some / None
- Tuple2 / Tuple3
- Function
- Predicate

### Functions
- Functions#identity
- Functions#STDOUT
- Functions#STDERR

### Predicates
- Predicates#True
- Predicates#False

### StringUtils
- StringUtils#isNotEmpty
- StringUtils#isEmpty
- StringUtils#startsWith
- StringUtils#endsWith
- StringUtils#size
- StringUtils#trim
- StringUtils#isBlank
- StringUtils#isNotBlank

### IO
- IO#fromFile
- IO#linesFromFile
- IO#linesFromInputStream

### Monodic Parsers
```java
Parser<String> parser = Literal("input");
ParserResult<String> result = parser.parse("input is awesome"); // returns Success<String>
ParserResult<String> result = parser.parse("output is awesome"); // returns Failure<String>
```

Inspired from Scala's [Parser Combinator](https://github.com/scala/scala-parser-combinators) and JS [Parsimmon](https://github.com/jneen/parsimmon).
Take a look at [TemplateParser](https://github.com/ashwanthkumar/my-java-utils/blob/master/src/main/java/in/ashwanthkumar/utils/template/TemplateParser.java), it's implemented using this parser package.

### Parsers
- Parsers#Literal
- Parsers#Character
- Parsers#Integer
- Parsers#Double
- Parsers#Regex
- Parsers#Sequence

### Parser
- Parser#map
- Parser#thenR
- Parser#thenL
- Parser#then
- Parser#or
- Parser#debug
- Parser#skip

### TemplateParser
Supports `#{variable}` and `#{variable?default}` type syntax based rendering. Inspired from [sbt-editsource](http://software.clapper.org/sbt-editsource/#variables) plugin.

## Dependencies

For Maven,
```xml
<dependency>
  <groupId>in.ashwanthkumar</groupId>
  <artifactId>my-java-utils</artifactId>
  <version>0.1.0</version>
</dependency>
```

For SBT,
```
libraryDependencies += "in.ashwanthkumar" % "my-java-utils" % "0.1.0"
```

## License
http://www.apache.org/licenses/LICENSE-2.0
