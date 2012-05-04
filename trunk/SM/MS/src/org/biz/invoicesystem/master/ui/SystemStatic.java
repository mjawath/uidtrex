package org.biz.invoicesystem.master.ui;

import org.components.util.Sessions;
import org.components.windows.MainWindow;

public abstract class SystemStatic {

    public final static String ITEM_IMAGE_PATH = "images//item//";
    public final static int GRID_LIST_SIZE = 50;

    public abstract void keyListeners();

    public abstract Object uiToEntity(Object i);

    public abstract void entity2Ui(Object i);

    public static MainWindow getMainWindow() {
        MainWindow jf = (MainWindow) Sessions.getObj("mainui");
        return jf;
    }
}