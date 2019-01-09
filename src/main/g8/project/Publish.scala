import com.typesafe.sbt.SbtNativePackager._
import sbt.Keys._
import sbt._


object Publish {

  lazy val publishers = Seq(
    publishTo := {
      if (isSnapshot.value) {
        Option(Dependencies.Tamedia.snapshots)
      } else {
        Option(Dependencies.Tamedia.releases)
      }
    }
  )

  lazy val settings = Seq(
    // disable cross version (when publish do it without _<scala-version>)
    crossVersion := CrossVersion.Disabled(),

    // publish project in maven style
    publishMavenStyle := false,

    // don't publish test artifacts
    publishArtifact in Test := false,

    // Remove files from compile phase, because don't want to publish jar, xml,...
    packagedArtifacts := Map.empty,

    // make sure the zip gets made before the publish commands for the added artifacts
    publish := ((publish) dependsOn (packageBin in Universal)).value
  )
}