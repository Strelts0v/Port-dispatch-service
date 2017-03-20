package com.gv.port.start;

import com.gv.port.gui.swt.PortWindow;

public class Main {

    public static PortWindow window;

    /**
     * Launch the application.
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        try {
            window = new PortWindow();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PortWindow getPortWindow(){
        return window;
    }
}
