import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar implements ActionListener {


    JMenu FileMenu;
    JMenu EditMenu;
    JMenu HelpMenu;
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;


    public MyMenuBar() {


        FileMenu = new JMenu("File");
        EditMenu = new JMenu("Edit");
        HelpMenu = new JMenu("Help");

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        loadItem.setMnemonic(KeyEvent.VK_L);//shortcuts

        FileMenu.add(loadItem);
        FileMenu.add(saveItem);
        FileMenu.add(exitItem);

        this.add(FileMenu);
        this.add(EditMenu);
        this.add(HelpMenu);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loadItem){
            System.out.println("load");
        }
        if(e.getSource() == saveItem){
            System.out.println("save");
        }
        if(e.getSource() == exitItem){
            System.out.println("exit");
        }
    }


}
