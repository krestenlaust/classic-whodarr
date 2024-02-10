package whodarr.classic.daemon.tray

import dorkbox.systemTray
import dorkbox.systemTray.{Menu, MenuItem, Separator, SystemTray}

class App:
  def run =
    val systemTray = SystemTray.get
    systemTray.setTooltip("Classic Whodarr")

    buildMenu(systemTray.getMenu, systemTray)

  def buildMenu(menu: Menu, systemTray: SystemTray) =
    menu.add(new Separator)

    val menuItemExit = new MenuItem("Exit Service", _ => systemTray.shutdown())
    menu.add(menuItemExit)
