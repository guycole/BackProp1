BackProp1
=========

Simple 3 layer backpropagation neural network w/3 demo applications (point classifier, XOR, 0-9 recognition)

Also available at http://backprop1.sourceforge.net/

Update: 12 April 2014 

BackProp1 now uses gradle.

Original BackProp1 on eclipse moved to "original" branch.

"gradle runDemo1" generates training set and trains to solve point classification for the equation "y = -5x+2" (you can view the training set w/gnuplot by using demo1/src/main/script/gnuplot1.dem)

There is no gradle support for demo2, an XOR demo.

A [link](http://www.digiburo.com/grafix/backProp3.png).

<img src="http://www.digiburo.com/grafix/backProp3.png" border="0" align="middle" alt="demo3 screenshot"/>

"gradle runDemo3" is an interactive digit recognizer.  It trains for the digits 0 through 9, and then allows one to change the pattern values then submit for classification.
