package view;

import business.OtelManager;
import business.SeasonManager;
import core.Helper;
import entity.Otel;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OtelSeasonSaveView extends Layout {
    private JPanel container;
    private JLabel lbl_otelName;
    private JTextField fld_otelName;
    private JLabel lbl_startSeason;
    private JButton btn_save;
    private JButton btn_cancel;
    private JTextField fld_startSeason;
    private JLabel lbl_endSeason;
    private JTextField fld_endSeason;
    private JLabel lbl_title;
    private Otel otel;
    private OtelManager otelManager;
    private Season season;
    private SeasonManager seasonManager;

    public OtelSeasonSaveView(Otel otel, Season season) {
        if(season != null){
            String startFull = season.getStart_season().toString();
            String[] startSplit = startFull.split("-");
            String endFull = season.getEnd_season().toString();
            String[] endSplit = endFull.split("-");
            this.fld_startSeason.setText(startSplit[2]+"/"+startSplit[1]+"/"+startSplit[0]);
            this.fld_endSeason.setText(endSplit[2]+"/"+endSplit[1]+"/"+endSplit[0]);
        }

        this.otel = otel;
        this.otelManager = new OtelManager();
        this.seasonManager = new SeasonManager();
        this.add(container);
        this.guiInitilaze(500, 500, "Dönem Ekle / Güncelle");
        this.fld_otelName.setText(otel.getName());

        btn_save.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_startSeason) || Helper.isFieldEmpty(fld_endSeason)) {
                Helper.showMsg("fill");
            } else {
                boolean isSuccess;
                if (season == null) {
                    isSuccess = seasonManager.save(new Season(otel.getOtel_id(), LocalDate.parse(fld_startSeason.getText(), DateTimeFormatter.ofPattern("dd/MM/yyy")), LocalDate.parse(fld_endSeason.getText(), DateTimeFormatter.ofPattern("dd/MM/yyy"))), otel);
                    if (isSuccess) {
                        Helper.showMsg("done");
                    }else{
                        Helper.showMsg("error");
                    }
                }else{
                    season.setStart_season(LocalDate.parse(fld_startSeason.getText(), DateTimeFormatter.ofPattern("dd/MM/yyy")));
                    season.setEnd_season(LocalDate.parse(fld_endSeason.getText(), DateTimeFormatter.ofPattern("dd/MM/yyy")));
                    isSuccess = seasonManager.update(season);
                    if (isSuccess) {
                        Helper.showMsg("done");
                    }else{
                        Helper.showMsg("error");
                    }
                }
            }
        });

        btn_cancel.addActionListener(e -> {
            dispose();
        });
    }

    private void createUIComponents() {
        try {
            this.fld_startSeason = new JFormattedTextField(new MaskFormatter("##/##/####"));
            this.fld_endSeason = new JFormattedTextField(new MaskFormatter("##/##/####"));
            if (season == null) {
                this.fld_startSeason.setText("01/01/2024");
                this.fld_endSeason.setText("01/01/2024");
            }else{
                this.fld_startSeason.setText(season.getStart_season().toString());
                this.fld_endSeason.setText(season.getEnd_season().toString());
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
