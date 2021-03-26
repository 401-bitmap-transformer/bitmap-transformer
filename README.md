# bitmap-transformer

Bitmap transformer is a command line program that performs some basic image transformations on a bitmap input file.

# Usage

We can run the program from the command line via gradlew.

```
./gradlew run --args '[inputFile] [outputFile] [transformationName]'
```

# Currently supported transformations

- convertToGrayscale: converts an image to grayScale
- blurImage: blurs an image
- mirrorImage: flips an image from left to right

