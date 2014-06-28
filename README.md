space-jazz
==========

A space invaders clone written in Java without any external libraries. This is a school projects that focuses to make everything as object oriented as possible. It uses Swing for drawing and a proper gameloop without any framerate caps. Since we were fairly familiar with Microsoft's XNA before starting this project, many aspects of this project may look really close to XNA. We plan to create all content by ourselves using Gimp or Photoshop for graphics and Famitracker for sound.

####Folders
```
space-jazz                              Main project workspace
space-jazz\src                          Source code
space-jazz\content                      Game content like graphics or sound
space-jazz\content\content-source\      Project files from Gimp, Photoshop (graphics) and Famitracker (sound)
```

####Compiling
If you want to compile and run this game (using Eclipse), clone it and set your Eclipse workspace to the first space-jazz folder (the one next to READNME.md). You may need to import the project using File -> Import -> Java Project. 

~~Note: we are using hardcoded "\" as directory separators which means that the game may have problems loading content on non-Windows systems. This will be fixed in the future.~~
The game is tested and works on Windows, OS X and Linux (Xubuntu). The fullscreen options is automatically disabled on operating systems other than Windows since it doesn't work propertly. On Linux, it's recommended to use Oracle's proprietary Java version for mutch better performance compared to OpenJDK.

####Binaries
A compiled version is now available to download from Mega:
[Link](https://mega.co.nz/#!dooHmYpS!swQ4YYiG3mYPzo1u3v2wjxoKPgfiB5EM1Cr80fH86xo) (7z 14,5MB) 
Unpack all files into any chosen directory and run space-jazz.jar. Make sure the application gets write permissions for the directory it is running in!
