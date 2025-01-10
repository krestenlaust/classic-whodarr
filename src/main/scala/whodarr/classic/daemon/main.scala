package whodarr.classic.daemon

import com.typesafe.config.ConfigFactory
import whodarr.classic.daemon.tray.App

@main
def main(): Unit =
  val config = ConfigFactory.load("daemon.conf")

  val app = App()
  app.run
