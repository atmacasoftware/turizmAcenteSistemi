package view;

import business.OtelManager;
import business.SeasonManager;
import core.Helper;
import entity.Season;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class SeasonView extends Layout{
    private JPanel container;
    private JButton btn_search;
    private JButton btn_back;
    private JTable tbl_season;
    private JTextField fld_searchOtelName;
    private JTextField fld_searchCity;
    private JLabel lbl_searchCity;
    private JLabel lbl_searchOtelName;
    private JTextField fld_searchRegion;
    private JLabel lbl_searchRegion;
    private JButton btn_clear;
    private DefaultTableModel model;
    private Object[] columnNames;
    private SeasonManager seasonManager;
    private JPopupMenu seasonPopMenu;
    private OtelManager otelManager;

    public SeasonView(User user){
        this.seasonManager = new SeasonManager();
        this.otelManager = new OtelManager();
        this.add(container);
        this.guiInitilaze(1200, 750, "Dönem Yönetimi");

        btn_back.addActionListener(e -> {
            PersonelView personelView = new PersonelView(user);
            dispose();
        });

        otelSeasonTableRefresh(null);
        popMenusSeason();


        btn_search.addActionListener(e -> {
            ArrayList<Season> seasonListTableSearch = seasonManager.filterTable(fld_searchOtelName.getText(), fld_searchCity.getText(), fld_searchRegion.getText());
            ArrayList<Object[]> seasonListObject = seasonManager.getForTable(columnNames.length, seasonListTableSearch);
            otelSeasonTableRefresh(seasonListObject);
        });

        btn_clear.addActionListener(e -> {
            this.fld_searchOtelName.setText("");
            this.fld_searchCity.setText("");
            this.fld_searchRegion.setText("");
        });
    }

    public void otelSeasonTableRefresh(ArrayList<Object[]> otelSeasonListObject){
        columnNames = new Object[]{"Dönem ID","Otel ID","Otel Adı","Şehir","Bölge","Dönem Başlangıç", "Dönem Bitiş"};
        if(otelSeasonListObject == null){
            otelSeasonListObject = seasonManager.getForTable(columnNames.length, seasonManager.getAllSeasonAndOtel());
        }

        createTable(model, tbl_season, columnNames, otelSeasonListObject);
    }

    public void popMenusSeason(){
        this.seasonPopMenu = new JPopupMenu();

        tableMouseSelect(tbl_season);

        seasonPopMenu.add("Güncelle").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OtelSeasonSaveView otelSeasonSaveView = new OtelSeasonSaveView(otelManager.getById(getSelectedRow(tbl_season,1)),seasonManager.getById(getSelectedRow(tbl_season, 0)));
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
