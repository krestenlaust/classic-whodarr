package whodarr.classic.organizer.webarchive.tests

import net.lingala.zip4j.ZipFile
import org.scalatest.Outcome
import org.scalatest.flatspec.FixtureAnyFlatSpec
import os.Path
import whodarr.classic.organizer.webarchive.StoryReorganizer
import whodarr.classic.util.{ FileUtility, MoveFileOperation }

import java.nio.file.Paths

class OrganizerIntegrationTests extends FixtureAnyFlatSpec {
  val resourceRoot: Path     = os.Path(java.nio.file.Paths.get(getClass.getResource("/").toURI))
  val fixtureRoot: Path      = resourceRoot / "fixtures"
  val resourceTempRoot: Path = resourceRoot / "temp"
  val fixtureName            = "doctorwho-s10"
  val fixtureExpectedName    = "doctorwho-s10_correct"

  override protected def withFixture(test: OneArgTest): Outcome =
    val fixtureSource        = fixtureRoot / f"$fixtureName.zip"
    val fixtureCorrectSource = fixtureRoot / f"$fixtureExpectedName.zip"

    val fixturePath        = resourceTempRoot / fixtureName
    val fixtureCorrectPath = resourceTempRoot / fixtureExpectedName

    // Make sure temp is removed
    os.remove.all(resourceTempRoot)
    os.makeDir(resourceTempRoot)

    // Unpack fixtures
    new ZipFile(fixtureSource.toString).extractAll(fixturePath.toString)
    new ZipFile(fixtureCorrectSource.toString).extractAll(fixtureCorrectPath.toString)

    try test((fixturePath, fixtureCorrectPath))
    finally os.remove.all(resourceTempRoot)

  override protected type FixtureParam = (os.Path, os.Path)

  it should "move story no. 66" in { case (actualFixture, expectedFixture) =>
    val storyFolderName   = "Doctor Who - S10E02 (066) - Carnival of Monsters - Parts 1-4"
    val actualStoryPath   = actualFixture / "doctorwho-s10" / "season 10 doctor 3" / storyFolderName
    val expectedStoryPath = expectedFixture / "doctorwho-s10" / "season 10 doctor 3" / storyFolderName

    val reorganizer = StoryReorganizer.reorganizeStory(actualStoryPath, 4, 66)
    val reorganized = reorganizer.reorganized(Some(actualStoryPath / os.up))

    FileUtility.massFileOperation(reorganized, MoveFileOperation())

    assertStoryMoved(actualFixture, expectedFixture, storyFolderName)
  }

  it should "move all stories in season 10" in { case (actualFixture, expectedFixture) =>
    val actualSeasonPath   = actualFixture / "doctorwho-s10" / "season 10 doctor 3"
    val expectedSeasonPath = expectedFixture / "doctorwho-s10" / "season 10 doctor 3"

    // Perform magic...

    // Assert
    assertStoryMoved(actualFixture, expectedFixture, "Doctor Who - S10E01 (065) - The Three Doctors - Parts 1-4")
    assertStoryMoved(actualFixture, expectedFixture, "Doctor Who - S10E02 (066) - Carnival of Monsters - Parts 1-4")
    assertStoryMoved(actualFixture, expectedFixture, "Doctor Who - S10E03 (067) - Frontier in Space - Parts 1-6")
    assertStoryMoved(actualFixture, expectedFixture, "Doctor Who - S10E04 (068) - Planet of the Daleks - Parts 1-6")
    assertStoryMoved(actualFixture, expectedFixture, "Doctor Who - S10E05 (069) - The Green Death - Parts 1-6")
  }

  def assertStoryMoved(actualFixture: Path, expectedFixture: Path, storyFolderName: String): Unit =
    val actualStoryPath   = actualFixture / "doctorwho-s10" / "season 10 doctor 3" / storyFolderName
    val expectedStoryPath = expectedFixture / "doctorwho-s10" / "season 10 doctor 3" / storyFolderName

    // Assert that all files have been moved out of the folder and bonus files are left
    assertFoldersIdenticalShallow(actualStoryPath, expectedStoryPath)

    // Assert that the moved files have been renamed correctly
    assertFolderContainedInFolderShallow(actualStoryPath / os.up, expectedStoryPath / os.up)

  /** Assert that both folders are exactly identical.
    * @param dir1 A directory to compare to another
    * @param dir2 The other directory to compare to the first one.
    */
  def assertFoldersIdenticalShallow(dir1: Path, dir2: Path): Unit =
    os.walk(dir1, maxDepth = 1).zip(os.walk(dir2, maxDepth = 1)).foreach { case (elem1, elem2) =>
      assert(elem1.last === elem2.last)
    }

  /** Asserts that all elements inside dirSubset exist inside dirSuperset as well
    * @param dirSubset The folder with minimum elements that needs to exist.
    * @param dirSuperset A folder to compare against to have at least the same elements.
    */
  def assertFolderContainedInFolderShallow(dirSubset: Path, dirSuperset: Path): Unit =
    val supersetFilenames = os.walk(dirSuperset, maxDepth = 1).map(p => p.last)
    val subsetFilenames   = os.walk(dirSubset, maxDepth = 1).map(p => p.last)

    subsetFilenames.foreach(name => assert(supersetFilenames.contains(name)))
}
