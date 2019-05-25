name := "spark-examples"

version := "0.1"

scalaVersion := "2.12.8"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies ++= Seq("org.apache.spark" %% "spark-core" % "2.4.3", "org.apache.spark" %% "spark-sql" % "2.4.3")
