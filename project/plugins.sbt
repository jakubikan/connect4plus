resolvers += "db4omaverepositories" at "http://source.db4o.com/maven"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Scala sbt" at "http://repo.scala-sbt.org/scalasbt/plugins-releases"


addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.4.0")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.2")

addSbtPlugin("info.schleichardt" % "sbt-sonar" % "0.1.0-SNAPSHOT")

addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.1.4")



