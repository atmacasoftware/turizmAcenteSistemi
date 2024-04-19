package view;

import business.FeaturesManager;
import business.OtelManager;
import core.Helper;
import entity.Otel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class OtelFeatureView extends Layout{
    private JLabel lbl_title;
    private JButton btn_add;
    private JTable tbl_feature;
    private JLabel lbl_otelName;
    private JPanel container;
    private Otel otel;
    private OtelManager otelManager;
    private DefaultTableModel feature_model;
    private Object[] columnNames;
    private FeaturesManager featuresManager;
    private JPopupMenu featurePopMenu;

    public OtelFeatureView(Otel otel){
        this.otel = otel;
        this.otelManager = new OtelManager();
        this.featuresManager = new FeaturesManager();
        this.add(container);
        this.guiInitilaze(500,500, "Otel Özellikleri");

        this.lbl_otelName.setText(otel.getName());

        btn_add.addActionListener(e -> {
            OtelFeatureSaveView otelFeatureSaveView = new OtelFeatureSaveView(otel);
            otelFeatureSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    otelFeatureTableRefresh(null);
                }
            });
        });

        otelFeatureTableRefresh(null);
        popMenusFeature();

    }

    public void otelFeatureTableRefresh(ArrayList<Object[]> otelFeatureListObject){
        columnNames = new Object[]{"Ozellik ID","Özellik Adı"};
        if(otelFeatureListObject == null){
            otelFeatureListObject = featuresManager.getForTableOtelFeature(columnNames.length, featuresManager.getAllOtelFeature(otel.getOtel_id()));
        }

        createTable(feature_model, tbl_feature, columnNames, otelFeatureListObject);
    }

    public void popMenusFeature(){
        this.featurePopMenu = new JPopupMenu();

        tableMouseSelect(tbl_feature);


        featurePopMenu.add("Sil").addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Silmek istediğinize emin misiniz ?", "Onaylama", JOptionPane.YES_NO_OPTION) == 0) {
                otelManager.deleteFeature(getSelectedRow(tbl_feature, 0), otel.getOtel_id());
                Helper.showMsg("done");
                otelFeatureTableRefresh(null);
            } else {
                Helper.showMsg("");
            }
        });

        tbl_feature.setComponentPopupMenu(featurePopMenu);

    }



}
