package whodarr.classic.organizer.webarchive.tests

import net.lingala.zip4j.ZipFile
import org.scalatest.Outcome
import org.scalatest.flatspec.FixtureAnyFlatSpec
import os.Path

import java.nio.file.Paths

class WebArchiveOrganizerIntegrationTests extends FixtureAnyFlatSpec {
  val resourceRoot: Path = os.Path(java.nio.file.Paths.get(getClass.getResource("/").toURI))
  val fixtureRoot: Path  = resourceRoot / "fixtures"
  val fixtureName        = "doctorwho-s10"
  val fixtureCorrectName = "doctorwho-s10_correct"

  override protected def withFixture(test: OneArgTest): Outcome =
    val fixtureSource        = fixtureRoot / f"${fixtureName}.zip"
    val fixtureCorrectSource = fixtureRoot / f"${fixtureCorrectName}.zip"

    val fixturePath        = resourceRoot / "tempFixture" / fixtureName
    val fixtureCorrectPath = resourceRoot / "tempCorrectFixture" / fixtureCorrectName

    // Unpack fixtures
    new ZipFile(fixtureSource.toString).extractAll(fixturePath.toString)
    new ZipFile(fixtureCorrectSource.toString).extractAll(fixtureCorrectPath.toString)

    try test((fixturePath, fixtureCorrectPath))
    finally
      os.remove.all(fixturePath)
      os.remove.all(fixtureCorrectPath)

  override protected type FixtureParam = (os.Path, os.Path)

  it should "have moved serial no. 66" in { case (rootFolder, organizedRootFolder) =>
    ???
  }
}
