/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LineItemPanel.java
 *
 * Created on Feb 23, 2012, 10:21:43 PM
 */
package invoicingsystem;

import com.components.custom.IComponent;
import com.components.custom.IContainer;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.uiEty;
import org.components.parent.controls.PxTable;

/**
 *
 * @author nnjj
 */
public abstract class LineItemPanel extends javax.swing.JDialog implements IContainer {

    protected Object lineitem;
    protected PxTable jt;

    public LineItemPanel(JFrame jf) {
        super(jf, true);
      
        init();
    }

    /** Creates new form LineItemPanel */
    public LineItemPanel() {
        super();
        init();

    }

    private void init() {
       focus = new ArrayList<IComponent>();
        setModal(true);

        this.setModalityType(ModalityType.MODELESS);
        setFocusableWindowState(false);
        setUndecorated(true);
//        setLocationRelativeTo(fr);
//        initComponents();
        getRootPane().setOpaque(false);
//        AWTUtilities.setWindowOpacity(this, 0.8f);
        setFocusableWindowState(true);
        setSize(900, 100);
        setPreferredSize(new Dimension(900, 100));
        initComponents();

    }

    public void showLineItemPanel() {


        int x = (int) jt.getLocationOnScreen().x;
        int y = (int) jt.getLocationOnScreen().y;
        int sr = jt.getSelectedRow();
        int hight = (sr + 1) * jt.getRowHeight();
        LineItemPanel.this.setLocation(x, y + hight);
//                lineItemDialogPanel.setFocusable(false);
//                LineItemPanel.this.showWithoutFocus();
//                    LineItemPanel.this.
        selectEty();


//        setFocusableWindowState(false);
        this.setVisible(true);
//        setFocusableWindowState(true);
//        setFocus();
    }

    public void setTable(PxTable tbl) {
        this.jt = tbl;

        ///press esc to close 
//Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener(){
//public void eventDispatched(AWTEvent event) {
//if(((KeyEvent)event).getKeyCode()==KeyEvent.VK_ESCAPE && MyDialog.this.isFocused()){
//MyDialog.this.setVisible(false);
//};
//}}, AWTEvent.KEY_EVENT_M
//
//Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
//
//            public void eventDispatched(AWTEvent event) {
//                if (((KeyEvent) event).getKeyCode() == KeyEvent.VK_ESCAPE && LineItemPanel.this.isShowing()) {
//                    LineItemPanel.this.setVisible(false);
//                    ((KeyEvent) event).consume();
//                    System.out.println("line item escaped called");
//                };
//            }
//        }, AWTEvent.KEY_EVENT_MASK);
        uiEty.setKeyAction(this.getRootPane(), new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                LineItemPanel.this.setVisible(false);
//                ((KeyEvent)e).consume();
            }
        }, KeyEvent.VK_ESCAPE);
        uiEty.setKeyAction(this.getRootPane(), new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                TableUtil.selectNextRow(jt, KeyEvent.VK_UP);
            }
        }, KeyEvent.VK_UP);

        uiEty.setKeyAction(this.getRootPane(), new AbstractAction() {

            public void actionPerformed(ActionEvent e) {

                TableUtil.selectNextRow(jt, KeyEvent.VK_DOWN);
            }
        }, KeyEvent.VK_DOWN);


        jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {
                    showLineItemPanel();
                }
            }
        });
        jt.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
//                for (Component component : LineItemPanel.this.getComponents()) {
//                    if(component.isFocusOwner()){
//                        LineItemPanel.this.setVisible(false);
//                    }
//                }
            }
        });
        addWindowFocusListener(new WindowAdapter() {

            @Override
            public void windowLostFocus(WindowEvent e) {
                LineItemPanel.this.setVisible(false);
            }
        });
        jt.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                showLineItemPanel();
            }
        });
//        addw
//        cTextField1.addKeyListener(new KeyAdapter() {
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                System.out.println(e);
//            }
//        });
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(251, 237, 163));
        getContentPane().setLayout(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setComponent(JComponent jc, int x, int y, int width, int hieht) {
        jc.setBounds(x, y, width, hieht);
        this.getContentPane().add(jc);
        pack();
    }

    public JTextField getTextField() {
        return textField;
    }

    public void dispatchEventx(KeyEvent event) {
        textField.requestFocus();

        System.out.println(" text 111||| " + textField.getText());
        System.out.println("selected text 111||| " + textField.getSelectedText());
        KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(textField, event);
        System.out.println("selected text 222||| " + textField.getSelectedText());

//        cTextField1.select(cTextField1.getText().length()-1, cTextField1.getText().length()-1);
//        cTextField1.setCaretPosition(cTextField1.getText().length()-1);
//        event.consume();
    }

    public void setTextField(JTextField cTextField1) {
        this.textField = cTextField1;
    }
    protected JTextField textField;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void selectEty() {
    }

    @Override
    public void gotoNextComponent() {
        //get current focused compnentt
        Component jc=KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();

        //find from compnent list
        int x=0;
        for (IComponent com : focus) {
            if(  com==jc){
            if(x>=0 && x< focus.size()-1){
                IComponent yx=focus.get(x+1);
            ((Component)yx).requestFocus();
            return;
            }
            }
            x++;
        }

    }


    public void addToFocus(IComponent com){
    focus.add(com);
    com.setContainer(this);

    }

    private List<IComponent> focus;

    @Override
    public void callBackAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear(){
    

    }
    }
