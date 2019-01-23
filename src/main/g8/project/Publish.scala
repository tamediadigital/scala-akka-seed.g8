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

    // make sure the zip gets made before the publish commands for the added artifacts
    publish := ((publish) dependsOn (packageBin in Universal)).value
  )
}