package view;

import business.FeaturesManager;
import business.OtelManager;
import core.ComboItem;
import core.Helper;
import entity.Features;
import entity.Otel;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OtelFeatureSaveView extends Layout {
    private JPanel container;
    private JTextField fld_otelName;
    private JLabel lbl_otelName;
    private JLabel lbl_feature;
    private JComboBox cmb_feature;
    private JButton btn_save;
    private JButton btn_cancel;
    private Otel otel;
    private OtelManager otelManager;
    private FeaturesManager featuresManager;

    public OtelFeatureSaveView(Otel otel) {
        this.otel = otel;
        this.otelManager = new OtelManager();
        this.featuresManager = new FeaturesManager();
        this.add(container);
        this.guiInitilaze(500, 500, "Otel Özellik Ekle / Güncelle");

        this.fld_otelName.setText(otel.getName());

        for (Features feature : featuresManager.getAllFeature()) {
            cmb_feature.addItem(feature.getComboItem());
        }


        btn_save.addActionListener(e -> {
            boolean isSuccess;
            ComboItem selectedItem = (ComboItem) cmb_feature.getSelectedItem();

            isSuccess = otelManager.saveFeature(selectedItem.getKey(), otel.getOtel_id());

            if (isSuccess) {
                Helper.showMsg("done");
            }

        });

        btn_cancel.addActionListener(e -> {
            dispose();
        });
    }
}
