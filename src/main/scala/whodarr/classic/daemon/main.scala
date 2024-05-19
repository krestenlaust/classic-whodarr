package whodarr.classic.daemon

import com.typesafe.config.ConfigFactory

@main
def main(): Unit =
  val config = ConfigFactory.load("daemon.conf")
