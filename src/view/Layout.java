package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Layout extends JFrame {

    public Layout(){

    }

    public void guiInitilaze(int width, int height, String title){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(width,height);
        setTitle(title);
        setResizable(false);
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void createTable(DefaultTableModel model, JTable table, Object[] column, ArrayList<Object[]> rows){
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(column);
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);

        model.setRowCount(0);

        if(rows == null){
            rows = new ArrayList<>();
        }

        for(Object[] i : rows){
            model.addRow(i);
        }
    }

    public int getSelectedRow(JTable table, int column){
        int selectId = (int) table.getValueAt(table.getSelectedRow(),column);
        return selectId;
    }

    public void tableMouseSelect(JTable table){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectrow = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selectrow,selectrow);
            }
        });
    }

    public LocalDate getToday(){
        LocalDate today = LocalDate.now();
        return today;
    }


}
