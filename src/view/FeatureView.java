package view;

import business.FeaturesManager;
import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class FeatureView extends Layout{
    private JPanel container;
    private JButton btn_add;
    private JTable tbl_features;
    private DefaultTableModel feature_model;
    private Object[] columnNames;
    private FeaturesManager featuresManager;
    private LocalDate today;
    private JPopupMenu featurePopMenu;

    public FeatureView(){
        this.add(container);
        featuresManager = new FeaturesManager();
        this.guiInitilaze(500,500, "Otel Özellikleri");
        today = LocalDate.now();

        // Özellik Listesi
        featureTableRefresh(null);
        popMenusFeature();

        btn_add.addActionListener(e -> {
            FeatureSaveView featureSaveView = new FeatureSaveView(null, String.valueOf(today));
            featureSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    featureTableRefresh(null);
                }
            });
        });
    }

    private void featureTableRefresh(ArrayList<Object[]> featureListObject) {
        columnNames = new Object[]{"ID", "Özellik", "Oluşturuma Tarihi", "Güncellenme Tarihi"};
        if(featureListObject == null) {
            featureListObject = featuresManager.getForTable(columnNames.length, featuresManager.getAllFeature());
        }

        createTable(feature_model, tbl_features, columnNames, featureListObject);
    }

    //Özellik ekranı pop menü
    public void popMenusFeature(){
        this.featurePopMenu = new JPopupMenu();

        tableMouseSelect(tbl_features);

        featurePopMenu.add("Güncelle").addActionListener(e -> {
            FeatureSaveView featureSaveView = new FeatureSaveView(featuresManager.getById(getSelectedRow(tbl_features, 0)),String.valueOf(today));
            featureSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    featureTableRefresh(null);
                }
            });
        });

        featurePopMenu.add("Sil").addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Silmek istediğinize emin misiniz ?", "Onaylama", JOptionPane.YES_NO_OPTION) == 0) {
                featuresManager.delete(getSelectedRow(tbl_features, 0));
                Helper.showMsg("done");
                featureTableRefresh(null);
            } else {
                Helper.showMsg("");
            }
        });

        tbl_features.setComponentPopupMenu(featurePopMenu);

    }

}
