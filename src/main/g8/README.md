# Scala seed project

This is basic Scala project that can be used for as quick start point for other Scala based projects.

It contains simple service that communicate with one Actor and returns simple string message to the client.

Use ./run-local.sh to start it.


You can use: 

1. `sbt publish` in order to build and publish your snapshot version
2. `sbt 'release with-defailts''` in order to release your code - this will automatically increment version in 
`version.sbt`, tag your code and push all changes to your git repository.
