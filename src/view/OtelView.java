package view;

import business.OtelManager;
import core.Helper;
import entity.User;
import entity.Otel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class OtelView extends Layout {
    private JPanel container;
    private JButton btn_back;
    private JButton btn_otelFeature;
    private JButton btn_pensionType;
    private JButton btn_addOtel;
    private JTable tbl_otel;
    private DefaultTableModel otel_model;
    private Object[] columnNames;
    private Otel otel;
    private OtelManager otelManager;
    private JPopupMenu otelPopMenu;

    public OtelView(User user) {
        this.add(container);
        this.otelManager = new OtelManager();
        this.guiInitilaze(1200, 750, "Otel Yönetimi");

        btn_back.addActionListener(e -> {
            PersonelView personelView = new PersonelView(user);
            dispose();
        });

        //Otel özellikleri ekranına geçi
        btn_otelFeature.addActionListener(e -> {
            FeatureView featureView = new FeatureView();
        });

        //Pansiyon Tipleri ekranına geçiş
        btn_pensionType.addActionListener(e -> {
            PensionView pensionView = new PensionView();
        });

        // Otel Ekleme
        btn_addOtel.addActionListener(e -> {
            OtelSaveView otelSaveView = new OtelSaveView(null, String.valueOf(getToday()));
            otelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    otelTableRefresh(null);
                }
            });

        });

        // Otelleri listeleme
        otelTableRefresh(null);
        popupMenusOtel();

    }

    private void otelTableRefresh(ArrayList<Object[]> otelListObject) {
        columnNames = new Object[]{"ID", "Otel Adı", "E-Posta", "Telefon Numarası", "Yıldız", "Şehir", "Bölge", "Adres", "Oluşturulma Tarihi", "Güncellenme Tarihi"};
        if (otelListObject == null) {
            otelListObject = otelManager.getForTable(columnNames.length, otelManager.getAllOtel());
        }

        createTable(otel_model, tbl_otel, columnNames, otelListObject);
    }

    public void popupMenusOtel(){
        this.otelPopMenu = new JPopupMenu();

        tableMouseSelect(tbl_otel);

        //Otele pansiyon tipi ekleme işlemi
        otelPopMenu.add("Pansiyon Tipi Ekle / Görüntüle").addActionListener(e -> {
            OtelPensionView otelPensionView = new OtelPensionView(otelManager.getById(getSelectedRow(tbl_otel, 0)));
        });

        //Otele özellik ekleme işlemi
        otelPopMenu.add("Özellik Ekle / Görüntüle").addActionListener(e -> {
            OtelFeatureView otelFeatureView = new OtelFeatureView(otelManager.getById(getSelectedRow(tbl_otel, 0)));
        });

        //Otele dönem ekleme işlemi
        otelPopMenu.add("Dönem Ekle / Görüntüle").addActionListener(e -> {
            OtelSeasonView otelSeasonView = new OtelSeasonView(otelManager.getById(getSelectedRow(tbl_otel, 0)));
        });

        //Otele oda ekleme işlemi
        otelPopMenu.add("Oda Ekle / Görüntüle").addActionListener(e -> {
            OtelRoomView otelRoomView = new OtelRoomView(otelManager.getById(getSelectedRow(tbl_otel, 0)));
        });

        //Oteli tüm özellikleri ile görüntüleme
        otelPopMenu.add("Oteli Görüntüle").addActionListener(e -> {
            OtelShowView otelShowView = new OtelShowView(otelManager.getById(getSelectedRow(tbl_otel, 0)));
        });

        //Oteli güncelleme
        otelPopMenu.add("Güncelle").addActionListener(e -> {
            OtelSaveView otelSaveView = new OtelSaveView(otelManager.getById(getSelectedRow(tbl_otel, 0)),String.valueOf(getToday()));
            otelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    otelTableRefresh(null);
                }
            });
        });

        //Oteli silme
        otelPopMenu.add("Sil").addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Silmek istediğinize emin misiniz ?", "Onaylama", JOptionPane.YES_NO_OPTION) == 0) {
                otelManager.delete(getSelectedRow(tbl_otel, 0));
                Helper.showMsg("done");
                otelTableRefresh(null);
            } else {
                Helper.showMsg("");
            }
        });

        tbl_otel.setComponentPopupMenu(otelPopMenu);
    }


}
