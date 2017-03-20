package com.gv.port.gui.swt.logging;

import com.gv.port.gui.swt.PortWindow;
import com.gv.port.logging.PortLogger;
import com.gv.port.portStructure.Port;
import com.gv.port.start.Main;
import org.eclipse.swt.widgets.Display;

public class BackgroundLogger implements Runnable{

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
                Main.getPortWindow().lblShipInDock_1.setText(PortLogger.getShipIdInFirstDock());

                Main.getPortWindow().lblShipInDock_2.setText((PortLogger.getShipIdInSecondDock()));

                Main.getPortWindow().lblShipInDock_3.setText(PortLogger.getShipIdInThirdDock());
                int count = Main.getPortWindow().shipList.getItemCount();
                while(--count >= 0) {
                    Main.getPortWindow().shipList.remove(0);
                    Main.getPortWindow().shipList.redraw();
                }

            }
        });
    }
}
