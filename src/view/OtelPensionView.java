package view;

import business.OtelManager;
import business.PensionManager;
import core.Helper;
import entity.Otel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class OtelPensionView extends Layout{
    private JPanel container;
    private JLabel lbl_title;
    private JButton btn_add;
    private JLabel lbl_otelName;
    private JTable tbl_feature;
    private Otel otel;
    private OtelManager otelManager;
    private PensionManager pensionManager;
    private JPopupMenu pensionPopMenu;
    private DefaultTableModel pension_model;
    private Object[] columnNames;

    public OtelPensionView(Otel otel){
        this.otel = otel;
        this.otelManager = new OtelManager();
        this.pensionManager = new PensionManager();
        this.add(container);
        this.guiInitilaze(500,500, "Otel Özellikleri");

        this.lbl_otelName.setText(otel.getName());

        btn_add.addActionListener(e -> {
            OtelPensionSaveView otelPensionSaveView = new OtelPensionSaveView(otel);
            otelPensionSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    otelPensionTableRefresh(null);
                }
            });
        });

        otelPensionTableRefresh(null);
        popMenusPension();

    }

    public void otelPensionTableRefresh(ArrayList<Object[]> otelPensionListObject){
        columnNames = new Object[]{"Pansiyon ID","Pansiyon Tipi"};
        if(otelPensionListObject == null){
            otelPensionListObject = pensionManager.getForTableOtelPension(columnNames.length, pensionManager.getAllOtelPension(otel.getOtel_id()));
        }

        createTable(pension_model, tbl_feature, columnNames, otelPensionListObject);
    }

    public void popMenusPension(){
        this.pensionPopMenu = new JPopupMenu();

        tableMouseSelect(tbl_feature);


        pensionPopMenu.add("Sil").addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Silmek istediğinize emin misiniz ?", "Onaylama", JOptionPane.YES_NO_OPTION) == 0) {
                otelManager.deletePension(getSelectedRow(tbl_feature, 0), otel.getOtel_id());
                Helper.showMsg("done");
                otelPensionTableRefresh(null);
            } else {
                Helper.showMsg("");
            }
        });

        tbl_feature.setComponentPopupMenu(pensionPopMenu);

    }
}
