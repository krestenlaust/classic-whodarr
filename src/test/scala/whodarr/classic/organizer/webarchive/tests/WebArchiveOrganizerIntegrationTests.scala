package whodarr.classic.organizer.webarchive.tests

import org.scalatest.Outcome
import org.scalatest.flatspec.FixtureAnyFlatSpec
import os.Path

class WebArchiveOrganizerIntegrationTests extends FixtureAnyFlatSpec {

  override protected def withFixture(test: OneArgTest): Outcome =
    // Unpack fixtures
    test(os.pwd)

  override protected type FixtureParam = os.Path

  it should "have moved serial no. 66" in { rootFolder =>
    ???
  }
}
