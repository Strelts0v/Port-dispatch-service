package com.gv.port.gui.swt;

import com.gv.port.ships.ShipManager;
import com.gv.port.start.Main;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ShipAddingWindow {

    protected Shell shlA;

    private Text downloadSupplies;

    private Text unloadSupplies;

    private Text accessTimeInSeconds;

    private Combo priorityCombo;

    public static long accessTime;

    public static int downloadSupplyCount;

    public static int unloadSupplyCount;

    public static int priority;

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();
        createContents();
        shlA.open();
        shlA.layout();
        while (!shlA.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shlA = new Shell();
        shlA.setSize(352, 229);
        shlA.setText("Adding new ship");
        shlA.setLayout(null);

        Label lblInputCountOf = new Label(shlA, SWT.NONE);
        lblInputCountOf.setBounds(10, 13, 186, 15);
        lblInputCountOf.setText("Input count of download supplies:");

        downloadSupplies = new Text(shlA, SWT.BORDER);
        downloadSupplies.setBounds(202, 10, 117, 23);

        Label lblInputCountOf_1 = new Label(shlA, SWT.NONE);
        lblInputCountOf_1.setText("Input count of unload supplies:");
        lblInputCountOf_1.setBounds(10, 44, 186, 15);

        unloadSupplies = new Text(shlA, SWT.BORDER);
        unloadSupplies.setBounds(202, 41, 117, 23);

        priorityCombo = new Combo(shlA, SWT.NONE);
        priorityCombo.setItems(new String[] {"LOW", "MEDIUM", "HIGH"});
        priorityCombo.setBounds(202, 108, 91, 15);
        priorityCombo.setText("Select");

        Label lblSelectPriorityFor = new Label(shlA, SWT.NONE);
        lblSelectPriorityFor.setText("Select priority for ship:");
        lblSelectPriorityFor.setBounds(10, 111, 128, 15);

        Label lblInputAccessTime = new Label(shlA, SWT.NONE);
        lblInputAccessTime.setText("Input access time in seconds:");
        lblInputAccessTime.setBounds(10, 74, 186, 15);

        accessTimeInSeconds = new Text(shlA, SWT.BORDER);
        accessTimeInSeconds.setBounds(202, 70, 117, 23);

        Button btnAdd = new Button(shlA, SWT.NONE);
        btnAdd.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                downloadSupplyCount = Integer.parseInt(downloadSupplies.getText());
                unloadSupplyCount = Integer.parseInt(unloadSupplies.getText());
                priority = priorityCombo.getSelectionIndex() + 1;
                accessTime = Long.parseLong(accessTimeInSeconds.getText());
                ShipManager.getInstance().addNewShip(accessTime,downloadSupplyCount, unloadSupplyCount, priority);
                Main.getPortWindow().updateShipQueue();
                shlA.close();
            }
        });
        btnAdd.setBounds(91, 157, 75, 23);
        btnAdd.setText("Add");

        Button btnBack = new Button(shlA, SWT.NONE);
        btnBack.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shlA.close();
            }
        });
        btnBack.setText("Back");
        btnBack.setBounds(172, 157, 75, 23);
    }
}