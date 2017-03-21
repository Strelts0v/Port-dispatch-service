package com.gv.port.start;

import com.gv.port.gui.swt.PortWindow;

/**
 * defines entry point of application
 */
public class Main {

    /** object of main gui window */
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

    /**
     * @return main gui window - PortWindow object
     */
    public static PortWindow getPortWindow(){
        return window;
    }
}
