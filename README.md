# СLI merge sort

![](https://travis-ci.org/lazyniv/merge_sort.svg?branch=master)

## Requirements

Java 1.9 or latter

## Build and run
### If you have gradle:
```
$ gradle build
$ java -jar build/libs/merge_sort-1.0.jar [OPTION]... [OUTPUT_FILE]   [INPUT_FILE]...
```
### Otherwise

for Unix like system

```
$ ./gradlew build
$ java -jar build/libs/merge_sort-1.0.jar [OPTION]... [OUTPUT_FILE]   [INPUT_FILE]...

```

for Windows

```
$ gradlew.bat build
$ java -jar build/libs/merge_sort-1.0.jar [OPTION]... [OUTPUT_FILE]   [INPUT_FILE]...
```

## Examples

Let we have two ascending sorted integer files `in1.txt`, `in2.txt` and we would like to merge them into `out.txt`
we should type:
```
$ java -jar build/libs/merge_sort-1.0.jar -i out.txt in1.txt in2.txt
```

To display a help message, you should type:
```
$ java -jar build/libs/merge_sort-1.0.jar -h
```
