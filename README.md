# ColorMine

# CURRENTLY UNDER CONSTRUCTION

ColorMine is an MIT Licensed .Net library that makes converting between color
spaces and comparing colors easy, originally by GitHub user
[THEJoezack](https://github.com/THEjoezack) in C#, forked on 2014-10-10.

[Original Project](https://github.com/THEjoezack/ColorMine)

PolyColorMine is a non-official port of ColorMine to Java by GitHub User
[zpolygon95](https://github.com/zpolygon95). PolyColorMine's creation is due to
the lack of existing libraries for java that intuitively trasform colorspace
coordinates, and perform accurate comparisons thereof.

## Building

A GNU Make file is included in the PolyColorMine directory,
simply run `make` (or `make -s` if you like clean output) in that
directory to compile all sources. The makefile will now generate a jar
file in the PolyColorMine/dist directory suitable for inclusion in other
projects. Run `make test` to compile the test file, which includes the jar
file from dist, and run `make ARGS="[command line arguments]" run to run the
test program.

Run `make clean` to delete all compiled sources, the `@sources` file, and the
output jar file.

## List of Implemented Features

+	Supports RGB
+	Supports Xyz
+	Supports CIE-L*ab
+	Supports CIE94 Comparison

## List of Planned Features

+	Support for Cmy
+	Support for Cmyk
+	Support for CIE-Lch
+	Support for Yxz
+	Support for HunterLab
+	Support for CIE-Luv
+	Support for Hsl
+	Support for Hsv
+	Support for CIE1976 Comparison
+	Support for CeDee2000 Comparison
+	Support for CMC Comparison

## To-Do

+	~~Change names to comply with standard java practice~~
+	~~Format files into a logical package structure~~
+	Document everything
+	~~Complete makefile~~
+	Restructure the PolyColorMine directory