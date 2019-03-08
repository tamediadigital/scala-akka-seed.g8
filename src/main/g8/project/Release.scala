import com.typesafe.sbt.SbtNativePackager._
import sbt.Keys._
import sbt._
import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleaseStateTransformations._
import sbtrelease._

object Release {


  lazy val setReleaseVersion: ReleaseStep = setVersionOnly(_._1)
  lazy val setNextVersion: ReleaseStep = setVersionOnly(_._2)
  lazy val settings = Seq(
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies, // : ReleaseStep
      inquireVersions, // : ReleaseStep
      //runTest,                      // : ReleaseStep
      setReleaseVersion, // : ReleaseStep
      commitReleaseVersion, // : ReleaseStep, performs the initial git checks
      tagRelease, // : ReleaseStep
      //      ReleaseStep(releaseStepTask(publish in Universal)),
      setNextVersion, // : ReleaseStep
      commitNextVersion, // : ReleaseStep
      pushChanges // : ReleaseStep, also checks that an upstream branch is properly configured
    )
  )

  def setVersionOnly(selectVersion: Versions => String): ReleaseStep = { st: State =>
    val vs = st.get(ReleaseKeys.versions).getOrElse(sys.error("No versions are set! Was this release part executed before inquireVersions?"))
    val selected = selectVersion(vs)
    st.log.info("Setting version to '%s'." format selected)
    val useGlobal = Project.extract(st).get(releaseUseGlobalVersion)
    writeVersion(st, selected)
    reapply(Seq(
      if (useGlobal) version in ThisBuild := selected
      else version := selected
    ), st)
  }

  releaseTagComment := s"Releasing ${(version in ThisBuild).value} [ci skip]"
  releaseCommitMessage := s"Setting version to ${(version in ThisBuild).value} [ci skip]"

  private def writeVersion(st: State, versionString: String) {
    val file = Project.extract(st).get(releaseVersionFile)
    val lines = IO.readLines(file)
    val nextVersion = versionString.replaceAll("-SNAPSHOT", "")
    val firstLine = s"""git.baseVersion := "$nextVersion""""
    IO.writeLines(file, firstLine :: lines.tail)
  }
}