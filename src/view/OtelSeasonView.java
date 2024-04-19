package view;

import business.OtelManager;
import business.SeasonManager;
import core.Helper;
import entity.Otel;
import entity.Season;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class OtelSeasonView extends Layout {
    private JPanel container;
    private JLabel lbl_title;
    private JLabel lbl_otelName;
    private JButton btn_add;
    private JTable tbl_season;
    private DefaultTableModel model;
    private Object[] columnNames;
    private SeasonManager seasonManager;
    private JPopupMenu seasonPopMenu;
    private Otel otel;
    private OtelManager otelManager;

    public OtelSeasonView(Otel otel) {
        this.otel = otel;
        this.seasonManager = new SeasonManager();
        this.add(container);
        this.guiInitilaze(500, 500, "Otel'in Dönemleri");

        this.lbl_otelName.setText(otel.getName());

        btn_add.addActionListener(e -> {
            OtelSeasonSaveView otelSeasonSaveView = new OtelSeasonSaveView(otel, null);
            otelSeasonSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    otelSeasonTableRefresh(null);
                }
            });
        });

        otelSeasonTableRefresh(null);
        popMenusSeason();
    }

    public void otelSeasonTableRefresh(ArrayList<Object[]> otelSeasonListObject){
        columnNames = new Object[]{"Dönem ID","Dönem Başlangıç", "Dönem Bitiş"};
        if(otelSeasonListObject == null){
            otelSeasonListObject = seasonManager.getForTableOtelSeason(columnNames.length, seasonManager.getByOtelAllSeason(otel.getOtel_id()));
        }

        createTable(model, tbl_season, columnNames, otelSeasonListObject);
    }

    public void popMenusSeason(){
        this.seasonPopMenu = new JPopupMenu();

        tableMouseSelect(tbl_season);

        seasonPopMenu.add("Güncelle").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OtelSeasonSaveView otelSeasonSaveView = new OtelSeasonSaveView(otel,seasonManager.getById(getSelectedRow(tbl_season, 0)));
                otelSeasonSaveView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        otelSeasonTableRefresh(null);
                    }
                });
            }
        });

        seasonPopMenu.add("Sil").addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Silmek istediğinize emin misiniz ?", "Onaylama", JOptionPane.YES_NO_OPTION) == 0) {
                seasonManager.delete(getSelectedRow(tbl_season, 0));
                Helper.showMsg("done");
                otelSeasonTableRefresh(null);
            } else {
                Helper.showMsg("");
            }
        });

        tbl_season.setComponentPopupMenu(seasonPopMenu);

    }


}
