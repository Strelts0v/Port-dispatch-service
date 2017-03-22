package com.gv.port.gui.swt.logging;

import com.gv.port.gui.swt.PortWindow;
import com.gv.port.logging.PortLogger;
import com.gv.port.portStructure.Port;
import com.gv.port.start.Main;
import org.eclipse.swt.widgets.Display;

/**
 * designed as thread, that with specified period will check @see PortLogger class
 * and print log info to the graphical user interface.
 */
public class BackgroundLogger implements Runnable{

    private static int MAX_SHIP_COUNT_IN_DOCKS = 3;

    /**
     * Thread get access to swt gui elements in async mode and update log info
     * according port business logic
     */
    public void run() {
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                PortWindow window = Main.getPortWindow();
                String[] logInfo = PortLogger.getLastLogInfo();
                for (int j = 0; j < logInfo.length; j++) {
                    PortLogger.getLogger().info(logInfo[j]);
                    window.logInfo.append(logInfo[j] + "\n");
                    window.logInfo.redraw();
                }
                Main.getPortWindow().supplyCount.setText(
                        new Integer(Port.getInstance().getStorage().getSupplyCount()).toString());
                Main.getPortWindow().lblShipInDock_1.setText("ship id #" + PortLogger.getShipIdInFirstDock());
                Main.getPortWindow().lblShipInDock_2.setText("ship id #" + PortLogger.getShipIdInSecondDock());
                Main.getPortWindow().lblShipInDock_3.setText("ship id #" + PortLogger.getShipIdInThirdDock());
                int count = Main.getPortWindow().shipList.getItemCount();
                for(int i = 0; i < count && i < MAX_SHIP_COUNT_IN_DOCKS; i++) {
                    Main.getPortWindow().shipList.remove(0);
                    Main.getPortWindow().shipList.redraw();
                }
            }
        });
    }
}
