ColorMine
=========

MIT Licensed .Net library intended to converting between color spaces and comparing colors easy.


```c#
var myRgb = new Rgb { R = 149, G = 13, B = 12 }
var myCmy = myRgb.To<Cmy>();
```

Online example at http://colormine.org/color-converter

You can also calculate delta-e, it's currently a bit awkward but undergoing rapid development. More algorithms coming soon!

CIE76 http://colormine.org/delta-e-calculator/
```c#
double deltaE = myRgb.Compare(myCmy,new Cie1976Comparison());
```

CMC l:C http://colormine.org/delta-e-calculator/cmc
```c#
double deltaE = myRgb.Compare(myCmy,new CmcComparison(lightness: 2, chroma: 1));
```

***

## Currently Supported Color Spaces
* CMY
* CMYK
* HSL
* HSV
* LAB
* LCH
* LUV
* RGB
* XYZ
* YXY

## Currently Supported Comparisons
* CIE76
* CMC l:c
* CIE94

Note: Comparisons support all color spaces
