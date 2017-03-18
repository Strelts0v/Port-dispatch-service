package com.gv.portDispatchService.gui.start;

import com.gv.portDispatchService.gui.swt.PortWindow;

public class Main {
    /**
     * Launch the application.
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        try {
            PortWindow window = new PortWindow();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
