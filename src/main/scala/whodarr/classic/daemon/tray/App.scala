package whodarr.classic.daemon.tray

import dorkbox.desktop.Desktop
import dorkbox.systemTray
import dorkbox.systemTray.{ Menu, MenuItem, Separator, SystemTray }

import scala.util.Try

class App:
  def run =
    val systemTray = SystemTray.get
    systemTray.setTooltip("Classic Whodarr")

    buildMenu(systemTray.getMenu, systemTray)

  def buildMenu(menu: Menu, systemTray: SystemTray) =
    menu.add(new Separator)

    val menuItemAbout =
      new MenuItem("Github Page", _ => Try(Desktop.browseURL("https://github.com/krestenlaust/classic-whodarr")))
    menu.add(menuItemAbout)

    val menuItemExit = new MenuItem("Exit Service", _ => systemTray.shutdown())
    menu.add(menuItemExit)
