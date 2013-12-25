/* basic project info */
name := "jerkson"

organization := "com.github.tkawachi"

version := "0.6.4"

licenses := Seq(
  ("MIT", url("http://codahale.com/mit.txt"))
)

homepage := Some(url("https://github.com/tkawachi/jerkson"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/tkawachi/jerkson"),
    "scm:git:https://github.com/tkawachi/jerkson.git",
    Some("scm:git:git@github.com:tkawachi/jerkson.git")
  )
)

/* scala versions and options */
scalaVersion := "2.10.2"

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  // "-deprecation",
  "-unchecked",
  "-encoding", "UTF-8",
  "-optimise"
)

scalacOptions ++= Seq(
  "-Yclosure-elim",
  "-Yinline"
)

scalacOptions += "-target:jvm-1.6"

// These language flags will be used only for 2.10.x.
// Uncomment those you need, or if you hate SIP-18, all of them.
scalacOptions <++= scalaVersion map { sv =>
  if (sv startsWith "2.10") List(
    // "-Xverify",
    // "-Ywarn-all",
    "-feature",
    "-language:postfixOps",
    "-language:reflectiveCalls",
    "-language:implicitConversions",
    "-language:higherKinds",
    "-language:existentials"
  )
  else Nil
}

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

/* dependencies */
libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-core" % "2.0.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.0.2"
)

libraryDependencies <+= scalaVersion {
  "org.scala-lang" % "scala-reflect" % _
}

resolvers ++= Seq(
  "Local Maven" at Path.userHome.asFile.toURI.toURL + ".m2/repository"
)


/* testing */
parallelExecution in Test := false

/* sbt behavior */
logLevel in compile := Level.Warn

traceLevel := 5

offline := false

/* publishing */
seq(bintrayPublishSettings:_*)
