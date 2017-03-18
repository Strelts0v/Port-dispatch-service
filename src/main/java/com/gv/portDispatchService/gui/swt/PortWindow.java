package com.gv.portDispatchService.gui.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class PortWindow {

    protected Shell shell;

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();
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
    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        shell.setModified(true);
        shell.setLayout(new FormLayout());

        Button btnNewButton = new Button(shell, SWT.NONE);
        btnNewButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            }
        });
        FormData fd_btnNewButton = new FormData();
        fd_btnNewButton.left = new FormAttachment(0, 10);
        btnNewButton.setLayoutData(fd_btnNewButton);
        btnNewButton.setText("Add ship");

        Button btnNewButton_1 = new Button(shell, SWT.NONE);
        fd_btnNewButton.bottom = new FormAttachment(100, -318);
        btnNewButton_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            }
        });
        FormData fd_btnNewButton_1 = new FormData();
        fd_btnNewButton_1.top = new FormAttachment(btnNewButton, 6);
        fd_btnNewButton_1.left = new FormAttachment(0, 10);
        fd_btnNewButton_1.bottom = new FormAttachment(100, -287);
        btnNewButton_1.setLayoutData(fd_btnNewButton_1);
        btnNewButton_1.setText("Delete ship");

        Label lblDock_1 = new Label(shell, SWT.NONE);
        lblDock_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
        lblDock_1.setAlignment(SWT.CENTER);
        FormData fd_lblDock_1 = new FormData();
        fd_lblDock_1.left = new FormAttachment(0, 194);
        fd_lblDock_1.bottom = new FormAttachment(100, -493);
        fd_lblDock_1.top = new FormAttachment(0, 10);
        lblDock_1.setLayoutData(fd_lblDock_1);
        lblDock_1.setText("Dock #1");

        Label lblDock_2 = new Label(shell, SWT.NONE);
        fd_lblDock_1.right = new FormAttachment(lblDock_2, -19);
        lblDock_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
        lblDock_2.setText("Dock #2");
        lblDock_2.setAlignment(SWT.CENTER);
        FormData fd_lblDock_2 = new FormData();
        fd_lblDock_2.left = new FormAttachment(0, 361);
        fd_lblDock_2.bottom = new FormAttachment(100, -493);
        fd_lblDock_2.top = new FormAttachment(0, 10);
        lblDock_2.setLayoutData(fd_lblDock_2);

        Label lblDock_3 = new Label(shell, SWT.NONE);
        fd_lblDock_2.right = new FormAttachment(lblDock_3, -19);
        lblDock_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
        lblDock_3.setText("Dock #3");
        lblDock_3.setAlignment(SWT.CENTER);
        FormData fd_lblDock_3 = new FormData();
        fd_lblDock_3.bottom = new FormAttachment(100, -493);
        fd_lblDock_3.top = new FormAttachment(0, 10);
        fd_lblDock_3.right = new FormAttachment(100, -117);
        fd_lblDock_3.left = new FormAttachment(0, 539);
        lblDock_3.setLayoutData(fd_lblDock_3);

        StyledText styledText = new StyledText(shell, SWT.BORDER);
        styledText.setText("logInfo");
        fd_btnNewButton_1.right = new FormAttachment(styledText, -48);
        fd_btnNewButton.right = new FormAttachment(styledText, -48);
        FormData fd_styledText = new FormData();
        fd_styledText.top = new FormAttachment(lblDock_1, 113);
        fd_styledText.bottom = new FormAttachment(100, -10);
        fd_styledText.left = new FormAttachment(0, 194);
        styledText.setLayoutData(fd_styledText);

        Label lblNewLabel = new Label(shell, SWT.NONE);
        fd_styledText.right = new FormAttachment(100, -226);
        FormData fd_lblNewLabel = new FormData();
        fd_lblNewLabel.left = new FormAttachment(0, 595);
        fd_lblNewLabel.right = new FormAttachment(100, -103);
        lblNewLabel.setLayoutData(fd_lblNewLabel);
        lblNewLabel.setText("Ships queue:");

        List list_1 = new List(shell, SWT.BORDER);
        fd_lblNewLabel.bottom = new FormAttachment(list_1, -3);
        FormData fd_list_1 = new FormData();
        fd_list_1.top = new FormAttachment(styledText, 0, SWT.TOP);
        fd_list_1.bottom = new FormAttachment(styledText, 0, SWT.BOTTOM);
        fd_list_1.right = new FormAttachment(styledText, 198, SWT.RIGHT);
        fd_list_1.left = new FormAttachment(styledText, 6);
        list_1.setLayoutData(fd_list_1);

        Label lblLogInformation = new Label(shell, SWT.NONE);
        FormData fd_lblLogInformation = new FormData();
        fd_lblLogInformation.top = new FormAttachment(lblNewLabel, 0, SWT.TOP);
        fd_lblLogInformation.left = new FormAttachment(lblDock_1, 0, SWT.LEFT);
        lblLogInformation.setLayoutData(fd_lblLogInformation);
        lblLogInformation.setText("Log information:");

        Label lblStorageState = new Label(shell, SWT.NONE);
        FormData fd_lblStorageState = new FormData();
        fd_lblStorageState.left = new FormAttachment(0, 10);
        fd_lblStorageState.top = new FormAttachment(lblNewLabel, 0, SWT.TOP);
        lblStorageState.setLayoutData(fd_lblStorageState);
        lblStorageState.setText("Storage state");

        Label lblSupplyCount = new Label(shell, SWT.NONE);
        fd_btnNewButton.top = new FormAttachment(lblSupplyCount, 22);
        FormData fd_lblSupplyCount = new FormData();
        fd_lblSupplyCount.top = new FormAttachment(styledText, 0, SWT.TOP);
        fd_lblSupplyCount.left = new FormAttachment(0, 10);
        lblSupplyCount.setLayoutData(fd_lblSupplyCount);
        lblSupplyCount.setText("Supply count:");

        Label lblSupplycount = new Label(shell, SWT.NONE);
        lblSupplycount.setText("SupplyCount");
        FormData fd_lblSupplycount = new FormData();
        fd_lblSupplycount.top = new FormAttachment(styledText, 0, SWT.TOP);
        fd_lblSupplycount.left = new FormAttachment(lblSupplyCount, 6);
        lblSupplycount.setLayoutData(fd_lblSupplycount);

    }
}