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

~~An *experimental* GNU Make file is included in the PolyColorMine directory,
simply run `make` (or `make -s` if you like clean output) in that
directory to compile all sources. Support for creating jar files with make is
to be implemented.

Run `make clean` to delete all compiled sources, and the `@sources` file~~

I have abandoned the make file for a bash script.

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
+	Complete makefile