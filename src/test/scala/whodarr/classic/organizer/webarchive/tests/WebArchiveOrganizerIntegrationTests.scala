package whodarr.classic.organizer.webarchive.tests

import net.lingala.zip4j.ZipFile
import org.scalatest.Outcome
import org.scalatest.flatspec.FixtureAnyFlatSpec
import os.Path
import whodarr.classic.organizer.StoryReorganizer
import whodarr.classic.util.{ FileUtility, MoveFileOperation }

import java.nio.file.Paths

class WebArchiveOrganizerIntegrationTests extends FixtureAnyFlatSpec {
  val resourceRoot: Path  = os.Path(java.nio.file.Paths.get(getClass.getResource("/").toURI))
  val fixtureRoot: Path   = resourceRoot / "fixtures"
  val fixtureName         = "doctorwho-s10"
  val fixtureExpectedName = "doctorwho-s10_correct"

  override protected def withFixture(test: OneArgTest): Outcome =
    val fixtureSource        = fixtureRoot / f"$fixtureName.zip"
    val fixtureCorrectSource = fixtureRoot / f"$fixtureExpectedName.zip"

    val fixturePath        = resourceRoot / fixtureName
    val fixtureCorrectPath = resourceRoot / fixtureExpectedName

    // Unpack fixtures
    new ZipFile(fixtureSource.toString).extractAll(fixturePath.toString)
    new ZipFile(fixtureCorrectSource.toString).extractAll(fixtureCorrectPath.toString)

    try test((fixturePath, fixtureCorrectPath))
    finally
      os.remove.all(fixturePath)
      os.remove.all(fixtureCorrectPath)

  override protected type FixtureParam = (os.Path, os.Path)

  it should "move story no. 66" in { case (actualFixture, expectedFixture) =>
    val actualStoryPath =
      actualFixture / "doctorwho-s10" / "season 10 doctor 3" / "Doctor Who - S10E02 (066) - Carnival of Monsters - Parts 1-4"
    val expectedStoryPath =
      expectedFixture / "doctorwho-s10" / "season 10 doctor 3" / "Doctor Who - S10E02 (066) - Carnival of Monsters - Parts 1-4"

    val reorganizer = StoryReorganizer.reorganizeStory(actualStoryPath, 4, 66)
    val reorganized = reorganizer.reorganized(Some(actualStoryPath / os.up))

    FileUtility.massFileOperation(reorganized, MoveFileOperation())

    // Assert that all files have been moved out of the folder and bonus files are left
    assertFoldersIdenticalShallow(actualStoryPath, expectedStoryPath)

    // Assert that the moved files have been renamed correctly
    assertFolderContainedInFolderShallow(actualStoryPath / os.up, expectedStoryPath / os.up)
  }

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
    val subsetFilenames = os.walk(dirSubset, maxDepth = 1).map(p => p.last)

    subsetFilenames.foreach(name => assert(supersetFilenames.contains(name)))
}
