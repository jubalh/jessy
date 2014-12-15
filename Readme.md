# Jessy #

Got something for me?!

Indeed, this is **jessy**, *a totally kafkaeque chess game*.
This is the most awesome fuck you ever played, yo.


*Idiot*: I don't like chess.


What? Come on! Man, you're smart.


Okay folks, now for real.
Hands on experience is the best way to learn.
So I built this. Just to play around a little with that Java shit they tought us at uni. I know cool folks use C, but what do these guys know..

One more thing: this game is full of bugs an' shit. No, this is not bad. It's intended. Like I said dude: *this thing is kafkaesque*!!

Anyways, if you find any bugs: remove them! Make a pull request! We want to ship clean meth, no room for impurities here.

![Example](https://raw.github.com/jubalh/jessy/master/example.gif)

## Bootstrapping ##

Previously you needed to have [Gradle](http://www.gradle.org/) installed.

We added the Gradle wrapper now, so you don't need it anymore. It is already distributed with this sourcecode. In the following text we will assume that you use the gradle wrapper `./gradlew` if that is not the case, you just have to substitute that command with `gradle`.

Get it: `git clone https://github.com/jubalh/jessy`
`cd flux; git submodule init; git submodule update`

Change directory: `cd jessy`

Compile: `./gradlew installApp`

Run: `./build/install/jessy/bin/jessy` 

To create an Eclipse project type `./gradlew eclipse` and add it to your workspace.

## Use ##

Start it.

Jessy supports command completion, so press <tab> to see a list of all commands.

To start a game type `start` or `start againstComputer` depending against whom you want to play.

To move a figure just type the origin immediately followed by the destination. Like this: `a2a3`.

Now play and have fun!
