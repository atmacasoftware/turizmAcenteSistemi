package view;

import business.PensionManager;
import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class PensionView extends Layout{
    private JPanel container;
    private JButton btn_add;
    private JTable tbl_pension;
    private JLabel lbl_title;
    private DefaultTableModel pension_model;
    private Object[] columnNames;
    private PensionManager pensionManager;
    private JPopupMenu pensionPopMenu;

    public PensionView() {
        this.add(container);
        pensionManager = new PensionManager();
        this.guiInitilaze(500,500, "Pansiyon Tipleri");

        //Pansiyon Listesi
        pensionTableRefresh(null);
        popMenusPension();

        btn_add.addActionListener(e -> {
            PensionSaveView pensionSaveView = new PensionSaveView(null, String.valueOf(getToday()));
            pensionSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    pensionTableRefresh(null);
                }
            });
        });
    }

    private void pensionTableRefresh(ArrayList<Object[]> pensionListObject){
        columnNames = new Object[]{"ID","Pansiyon Tipi", "Oluşturulma Tarihi", "Güncellenme Tarihi"};
        if(pensionListObject == null){
            pensionListObject = pensionManager.getForTable(columnNames.length, pensionManager.getAllPension());
        }

        createTable(pension_model, tbl_pension, columnNames, pensionListObject);
    }

    public void popMenusPension(){
        this.pensionPopMenu = new JPopupMenu();

        tableMouseSelect(tbl_pension);

        pensionPopMenu.add("Güncelle").addActionListener(e -> {
            PensionSaveView pensionSaveView = new PensionSaveView(pensionManager.getById(getSelectedRow(tbl_pension, 0)),String.valueOf(getToday()));
            pensionSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    pensionTableRefresh(null);
                }
            });
        });

        pensionPopMenu.add("Sil").addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Silmek istediğinize emin misiniz ?", "Onaylama", JOptionPane.YES_NO_OPTION) == 0) {
                pensionManager.delete(getSelectedRow(tbl_pension, 0));
                Helper.showMsg("done");
                pensionTableRefresh(null);
            } else {
                Helper.showMsg("");
            }
        });

        tbl_pension.setComponentPopupMenu(pensionPopMenu);
    }

}
