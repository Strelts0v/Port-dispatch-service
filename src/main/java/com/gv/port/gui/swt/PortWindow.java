package com.gv.port.gui.swt;

import com.gv.port.gui.swt.logging.BackgroundLogger;
import com.gv.port.logging.PortLogger;
import com.gv.port.portStructure.Port;
import com.gv.port.ships.Ship;
import com.gv.port.ships.ShipManager;
import com.gv.port.start.Main;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PortWindow {

    protected Shell shell;

    public Display display;

    public Button btnAddShip;

    public Button btnDeleteShip;

    public Button btnStartPort;

    public Label lblDock_1;

    public Label lblDock_2;

    public Label lblDock_3;

    public StyledText logInfo;

    public Label lblShipsQueue;

    public List shipList;

    public Label lblLogInformation;

    public Label lblStorageState;

    public Label lblSupplyCount;

    public Label supplyCount;

    public Label lblShipInDock_1;

    public Label lblShipInDock_2;

    public Label lblShipInDock_3;

    private final static int LOGGING_FREQUENCY = 4;

    /**
     * Open the window.
     */
    public void open() {
        display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        shell.setModified(true);
        shell.setSize(831, 563);
        shell.setMinimumSize(831, 563);
        shell.setBackground(SWTResourceManager.getColor(152, 251, 152));
        shell.setLayout(new FormLayout());

        btnAddShip = new Button(shell, SWT.NONE);
        btnAddShip.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                //add ship
            }
        });
        FormData fd_btnAddShip = new FormData();
        fd_btnAddShip.left = new FormAttachment(0, 10);
        btnAddShip.setLayoutData(fd_btnAddShip);
        btnAddShip.setText("Add ship");

        btnDeleteShip = new Button(shell, SWT.NONE);
        fd_btnAddShip.bottom = new FormAttachment(100, -314);
        btnDeleteShip.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                //delete ship
            }
        });
        FormData fd_btnDeleteShip = new FormData();
        fd_btnDeleteShip.top = new FormAttachment(btnAddShip, 6);
        fd_btnDeleteShip.left = new FormAttachment(0, 10);
        btnDeleteShip.setLayoutData(fd_btnDeleteShip);
        btnDeleteShip.setText("Delete ship");

        lblDock_1 = new Label(shell, SWT.NONE);
        lblDock_1.setBackground(SWTResourceManager.getColor(255, 218, 185));
        lblDock_1.setAlignment(SWT.CENTER);
        FormData fd_lblDock_1 = new FormData();
        fd_lblDock_1.bottom = new FormAttachment(100, -493);
        fd_lblDock_1.top = new FormAttachment(0, 10);
        lblDock_1.setLayoutData(fd_lblDock_1);
        lblDock_1.setText("Dock #1");

        lblDock_2 = new Label(shell, SWT.NONE);
        fd_lblDock_1.right = new FormAttachment(100, -473);
        lblDock_2.setBackground(SWTResourceManager.getColor(240, 230, 140));
        lblDock_2.setText("Dock #2");
        lblDock_2.setAlignment(SWT.CENTER);
        FormData fd_lblDock_2 = new FormData();
        fd_lblDock_2.left = new FormAttachment(lblDock_1, 19);
        fd_lblDock_2.top = new FormAttachment(0, 10);
        lblDock_2.setLayoutData(fd_lblDock_2);

        lblDock_3 = new Label(shell, SWT.NONE);
        fd_lblDock_2.right = new FormAttachment(lblDock_3, -20);
        lblDock_3.setBackground(SWTResourceManager.getColor(224, 255, 255));
        lblDock_3.setText("Dock #3");
        lblDock_3.setAlignment(SWT.CENTER);
        FormData fd_lblDock_3 = new FormData();
        fd_lblDock_3.top = new FormAttachment(0, 10);
        fd_lblDock_3.right = new FormAttachment(100, -84);
        fd_lblDock_3.left = new FormAttachment(0, 556);
        lblDock_3.setLayoutData(fd_lblDock_3);

        logInfo = new StyledText(shell, SWT.BORDER);
        fd_lblDock_1.left = new FormAttachment(logInfo, 0, SWT.LEFT);
        fd_btnDeleteShip.right = new FormAttachment(logInfo, -21);
        fd_btnAddShip.right = new FormAttachment(logInfo, -21);
        FormData fd_logInfo = new FormData();
        fd_logInfo.bottom = new FormAttachment(100, -10);
        fd_logInfo.left = new FormAttachment(0, 167);
        logInfo.setLayoutData(fd_logInfo);

        lblShipsQueue = new Label(shell, SWT.NONE);
        lblShipsQueue.setFont(SWTResourceManager.getFont("Segoe Print", 12, SWT.BOLD));
        lblShipsQueue.setBackground(SWTResourceManager.getColor(152, 251, 152));
        fd_logInfo.right = new FormAttachment(100, -226);
        FormData fd_lblShipsQueue = new FormData();
        fd_lblShipsQueue.left = new FormAttachment(0, 595);
        fd_lblShipsQueue.right = new FormAttachment(100, -103);
        lblShipsQueue.setLayoutData(fd_lblShipsQueue);
        lblShipsQueue.setText("Ships queue:");

        shipList = new List(shell, SWT.BORDER);
        fd_lblShipsQueue.bottom = new FormAttachment(shipList, -3);
        FormData fd_shipList = new FormData();
        fd_shipList.bottom = new FormAttachment(100, -10);
        fd_shipList.top = new FormAttachment(0, 145);
        fd_shipList.right = new FormAttachment(100, -28);
        fd_shipList.left = new FormAttachment(logInfo, 6);
        shipList.setLayoutData(fd_shipList);

        lblLogInformation = new Label(shell, SWT.NONE);
        fd_logInfo.top = new FormAttachment(lblLogInformation, 6);
        lblLogInformation.setFont(SWTResourceManager.getFont("Segoe Print", 12, SWT.BOLD));
        lblLogInformation.setBackground(SWTResourceManager.getColor(152, 251, 152));
        FormData fd_lblLogInformation = new FormData();
        fd_lblLogInformation.bottom = new FormAttachment(100, -386);
        fd_lblLogInformation.left = new FormAttachment(logInfo, 0, SWT.LEFT);
        lblLogInformation.setLayoutData(fd_lblLogInformation);
        lblLogInformation.setText("Log information:");

        lblStorageState = new Label(shell, SWT.NONE);
        lblStorageState.setBackground(SWTResourceManager.getColor(152, 251, 152));
        lblStorageState.setFont(SWTResourceManager.getFont("Rockwell", 12, SWT.NORMAL));
        FormData fd_lblStorageState = new FormData();
        fd_lblStorageState.left = new FormAttachment(0, 10);
        fd_lblStorageState.top = new FormAttachment(lblShipsQueue, 0, SWT.TOP);
        lblStorageState.setLayoutData(fd_lblStorageState);
        lblStorageState.setText("Storage state");

        lblSupplyCount = new Label(shell, SWT.NONE);
        fd_btnAddShip.top = new FormAttachment(lblSupplyCount, 26);
        lblSupplyCount.setFont(SWTResourceManager.getFont("@Yu Gothic Medium", 9, SWT.NORMAL));
        lblSupplyCount.setBackground(SWTResourceManager.getColor(152, 251, 152));
        FormData fd_lblSupplyCount = new FormData();
        fd_lblSupplyCount.left = new FormAttachment(0, 10);
        fd_lblSupplyCount.bottom = new FormAttachment(100, -365);
        fd_lblSupplyCount.top = new FormAttachment(lblStorageState, 10);
        lblSupplyCount.setLayoutData(fd_lblSupplyCount);
        lblSupplyCount.setText("Supply count:");

        supplyCount = new Label(shell, SWT.NONE);
        fd_lblSupplyCount.right = new FormAttachment(supplyCount, -21);
        supplyCount.setBackground(SWTResourceManager.getColor(152, 251, 152));
        supplyCount.setText("#");
        FormData fd_supplyCount = new FormData();
        fd_supplyCount.right = new FormAttachment(logInfo, -21);
        fd_supplyCount.left = new FormAttachment(0, 109);
        fd_supplyCount.top = new FormAttachment(0, 145);
        supplyCount.setLayoutData(fd_supplyCount);

        lblShipInDock_1 = new Label(shell, SWT.NONE);
        lblShipInDock_1.setBackground(SWTResourceManager.getColor(255, 250, 240));
        lblShipInDock_1.setAlignment(SWT.CENTER);
        FormData fd_lblShipInDock_1 = new FormData();
        fd_lblShipInDock_1.left = new FormAttachment(0, 167);
        fd_lblShipInDock_1.top = new FormAttachment(lblDock_1, 6);
        lblShipInDock_1.setLayoutData(fd_lblShipInDock_1);
        lblShipInDock_1.setText("Empty");

        lblShipInDock_2 = new Label(shell, SWT.NONE);
        fd_lblShipInDock_1.right = new FormAttachment(lblShipInDock_2, -19);
        fd_lblDock_2.bottom = new FormAttachment(lblShipInDock_2, -6);
        lblShipInDock_2.setBackground(SWTResourceManager.getColor(255, 250, 240));
        lblShipInDock_2.setText("Empty");
        lblShipInDock_2.setAlignment(SWT.CENTER);
        FormData fd_lblShipInDock_2 = new FormData();
        fd_lblShipInDock_2.right = new FormAttachment(lblDock_2, 0, SWT.RIGHT);
        fd_lblShipInDock_2.top = new FormAttachment(0, 38);
        fd_lblShipInDock_2.left = new FormAttachment(0, 361);
        lblShipInDock_2.setLayoutData(fd_lblShipInDock_2);

        lblShipInDock_3 = new Label(shell, SWT.NONE);
        fd_lblDock_3.bottom = new FormAttachment(lblShipInDock_3, -6);
        lblShipInDock_3.setBackground(SWTResourceManager.getColor(255, 250, 240));
        lblShipInDock_3.setText("Empty");
        lblShipInDock_3.setAlignment(SWT.CENTER);
        FormData fd_lblShipInDock_3 = new FormData();
        fd_lblShipInDock_3.right = new FormAttachment(lblDock_3, 0, SWT.RIGHT);
        fd_lblShipInDock_3.left = new FormAttachment(lblDock_3, 0, SWT.LEFT);
        fd_lblShipInDock_3.bottom = new FormAttachment(lblShipsQueue, -61);
        fd_lblShipInDock_3.top = new FormAttachment(0, 38);
        lblShipInDock_3.setLayoutData(fd_lblShipInDock_3);

        btnStartPort = new Button(shell, SWT.NONE);
        btnStartPort.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                ShipManager.getInstance().lunchShipsInQueue();
                ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
                service.scheduleAtFixedRate(new BackgroundLogger(), 0, LOGGING_FREQUENCY, TimeUnit.SECONDS);
            }
        });

        fd_btnDeleteShip.bottom = new FormAttachment(btnStartPort, -6);
        btnStartPort.setText("Start port");
        FormData fd_btnStartPort = new FormData();
        fd_btnStartPort.top = new FormAttachment(0, 250);
        fd_btnStartPort.left = new FormAttachment(btnAddShip, 0, SWT.LEFT);
        fd_btnStartPort.right = new FormAttachment(btnAddShip, 0, SWT.RIGHT);
        btnStartPort.setLayoutData(fd_btnStartPort);

        initializeContent();
    }

    private void initializeContent(){
        ShipManager.getInstance().initializeShipsQueue();
        Queue<Ship> shipQueue =  ShipManager.getInstance().getShipsQueue();
        for(Ship ship : shipQueue){
            shipList.add("Ship #" + ship.getShipId() + ". Priority: " + ship.getPriorityText());
        }
        supplyCount.setText(new Integer(Port.getInstance().getStorage().getSupplyCount()).toString());
    }
}