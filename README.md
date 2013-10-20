# ColorMine

MIT Licensed .Net library that makes converting between color spaces and comparing colors easy.


## Getting Started

ColorMine is available as a nuget package so you can install by searching for "ColorMine" in the "Manage Nuget Packages" menu, or run the following command in the Package Manager Console:

*PM> Install-Package ColorMine*

## Color Conversions

You can convert between any supported color spaces via generic methods like so:


```c#
var myRgb = new Rgb { R = 149, G = 13, B = 12 }
var myCmy = myRgb.To<Cmy>();
```


```c#
var myXyz = new Xyz { X = .44, Y = .7, Z = .99 }
var myLab = myXyz.To<Lab>();
```

```c#
var myYxy = new Xyz { Y1 = .1124, X = .22, Y2 = .14 }
var myHsl = myYxy.To<Hsl>();
```

Cmyk conversion also supports profiles
```c#
var myCmyk = myRgb
    .WithProfile("~/JapanWebCoated.icc")
    .To<Cmyk>();
var myHunterLab = myCmyk
    .WithProfile("~/JapanWebCoated.icc")
    .To<HunterLab>();
```

Online example at http://colormine.org/color-converter


## Delta-E

Delta-E calculations take and compare colors in any of the supported formats.

### [CIE76](http://colormine.org/delta-e-calculator/)
```c#
double deltaE = myRgb.Compare(myCmy,new Cie1976Comparison());
```

### [CMC l:C](http://colormine.org/delta-e-calculator/cmc)
```c#
double deltaE = myXyz.Compare(myLab,new CmcComparison(lightness: 2, chroma: 1));
```

### [CIE94](http://colormine.org/delta-e-calculator/cie94)
```c#
double deltaE = myYxy.Compare(myHsl,new Cie94(Cie94Comparison.Application.GraphicArts));
```

### [CIE2000](http://colormine.org/delta-e-calculator/cie2000)
```c#
double deltaE = myHunterLab.Compare(myLuv, new CieDe2000());
```

Note: Delta-e calculations are [quasimetric](http://en.wikipedia.org/wiki/Quasimetric#Quasimetrics), the result of comparing color a to b isn't always equal to comparing color b to a...but it will probably be pretty close!

***

## Currently Supported Color Spaces
* CMY
* CMYK
* HSL
* HSB
* HSV
* CIE L*AB
* Hunter LAB
* LCH
* LUV
* RGB
* XYZ
* YXY

## Currently Supported Comparisons
* CIE76
* CMC l:c
* CIE94
* CIE2000
