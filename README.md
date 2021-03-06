# СLI merge sort

![](https://travis-ci.org/lazyniv/merge_sort.svg?branch=master)

## Overview 

The Merge sort utility for merging several files.

Data in files can be of the two types -- integer or string. Each item in the file must begin on a new line. The utility supports files sorted in ascending and descending order.

The program parameters are set at the start through command line arguments, in order:

1) Sort mode (-a or -d). if nothing is specified, then the default sort mode is ascending.
2) Data type (-i or -s)
3) Name of output file
4) The remaining parameters are the names of the input files

## Requirements

* Java 1.9 or latter

* Apache Commons CLI 1.4

* JUnit 4.12 for run unit tests

Solution was tested on openjdk9 and openjdk11 

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
