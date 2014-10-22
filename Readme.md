# Jessy #

Java console Chessprogram

Hands on experience is the best way for me to learn.

Did this project just to get more familiar with Java.
My goal was to put to use what I learned about the language. Classes, Exceptions and such.

I never intended to build a complete chess program.

So you can put pieces on the board and move them around.

![Example](https://raw.github.com/jubalh/jessy/master/example.gif)

## Bootstrapping ##

Previously you needed to have [Gradle](http://www.gradle.org/) installed.

We added the Gradle wrapper now, so you don't need it anymore. It is already distributed with this sourcecode. In the following text we will assume that you use the gradle wrapper `gradlew` if that is not the case, you just have to substitute that command with `gradle`.

Get it: `git clone https://github.com/jubalh/jessy`
`cd flux; git submodule init; git submodule update`

Change directory: `cd jessy`

Compile: `gradlew installApp`

Run: `./build/install/jessy/bin/jessy` 

To create an Eclipse project type `gradlew eclipse` and add it to your workspace.

## Use ##

Start it.

Type `Pd2-Pd4`  to move pawn from D2 to D4.

Abbreviations of figures:
__P__ for pawn

__N__ for knight

__B__ for bishop

__R__ for rook

__Q__ for Queen

__K__ for King

As you see you need to type the letter in upper-case.
Field letter in lower-case.
