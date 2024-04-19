package view;

import business.OtelManager;
import business.PensionManager;
import core.ComboItem;
import core.Helper;
import entity.Features;
import entity.Otel;
import entity.Pension;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OtelPensionSaveView extends Layout {
    private JLabel lbl_otelName;
    private JTextField fld_otelName;
    private JLabel lbl_pension;
    private JComboBox cmb_pension;
    private JButton btn_save;
    private JButton btn_cancel;
    private JPanel container;
    private Otel otel;
    private OtelManager otelManager;
    private PensionManager pensionManager;

    public OtelPensionSaveView(Otel otel) {
        this.otel = otel;
        this.otelManager = new OtelManager();
        this.pensionManager = new PensionManager();
        this.add(container);
        this.guiInitilaze(500, 500, "Pansiyon Tipi Ekle / Güncelle");

        this.fld_otelName.setText(otel.getName());

        for (Pension pension : pensionManager.getAllPension()) {
            cmb_pension.addItem(pension.getComboItem());
        }

        btn_save.addActionListener(e -> {
            boolean isSuccess;
            ComboItem selectedItem = (ComboItem) cmb_pension.getSelectedItem();

            isSuccess = otelManager.savePension(selectedItem.getKey(), otel.getOtel_id());

            if (isSuccess) {
                Helper.showMsg("done");
            }

        });

        btn_cancel.addActionListener(e -> {
            dispose();
        });
    }

}
