# Lexicographical Conventions

This file attempts to describe the visual style to which this project will
conform. It is not exhaustive, and I have likely made mistakes. Consequently,
this file will be updated along with all the others.

## Naming

+	Names of classes shall be written in "Class Case" (wherein the first
	letter of each word is capitalized)
+	Names of variables and methods shall be written in "Camel Case" (wherein
	the first letter of each word aside from the first word is capitalized)
+	Names of constants shall be written in Upper Case, words being separated
	by underscores
+	Main classes (and therefore files) shall begin with the prefix "Poly"-
+	Names shall aim to be non-arbitrary, self descriptive, and concise.

## Block Structure

+	Curly brackets shall be in their own lines, unless the block they contain
	can be compressed to one line (the last character of the line must not
	pass column 80)
+	The contents of a multi-line block must be indented using one tab
+	The opening paren for the arguments of a block must be separated from the
	block designator by one space
+	Modifiers to the block designator must each be separated by one space

## List Structure

+	Items in a list shall be separated by one comma followed by either one
	space. This applies to arguments, array declarations, etc.
+	Lists with language specific delimitation characters (i.e. for loops)
	shall be separated by that delimeter character and one space

## General Structure

+	Lines shall extend no farther than column 80. Those that do shall replace
	the space nearest to column 80 with a new line. This shall be repeated
	until no characters extend beyond there.
+	When invoking methods rather than defining them, no space shall be used
	between its designator and the opening paren of its argument list.