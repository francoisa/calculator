# calculator

calculator is a java program that evaluates arithmetic expressions one line at a time. It's main purpose is:

* to demonstrate how an antlr4 grammar can be used in a program 
* to create a simple interface to a java program for a future jni project

## Building the Calculator

The build artifact is a jar called calc-***&lt;version&gt;*** where ***&lt;version&gt;*** is set by a property in the ant build.xml

The current version is 1.0

```
ant
```

ant builds **calc-1.0.jar** under the **targets** directory.

### Dependencies

You will need the antlr4 tool build this project. The jar file [antlr4-4.5.3-complete.jar](http://www.antlr.org/download/antlr-4.5.3-complete.jar) needs to be under the lib directory.

## Running the Calculator

You can run the calculator with no parameters in which case it will keep asking for input and remember previous values until you enter ***quit***.
```
java -cp lib\antlr-runtime-4.5.3.jar;target\calc-1.0.jar net.jmf.calc.Calc
```

You can also pass a file paramter with multiple expressions (one per line). In this case, the program will parse and process each line.
```
java -cp lib\antlr-runtime-4.5.3.jar;target\calc-1.0.jar net.jmf.calc.Calc t.expr
```

## Use as a Library
The jar calc-1.0.jar has a public static function which allows it to be used as a library if the antlr4 runtime is available on the classpath.  It was compiled with [antlr4-runtime-4.5.3.jar](http://www.antlr.org/download/antlr-runtime-4.5.3.jar)

```
String net.jmf.calc.Calc.parse(String str);
```
