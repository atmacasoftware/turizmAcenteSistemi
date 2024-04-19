package view;

import business.OtelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Otel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class RoomSaveView extends Layout {
    private JPanel container;
    private JTextField fld_otel;
    private JLabel lbl_pension;
    private JComboBox cmb_pension;
    private JLabel lbl_seasom;
    private JComboBox cmb_season;
    private JLabel lbl_type;
    private JComboBox cmb_type;
    private JFormattedTextField fld_stock;
    private JLabel lbl_stock;
    private JFormattedTextField fld_adultPrice;
    private JLabel lbl_adultPrice;
    private JLabel lbl_childPrice;
    private JFormattedTextField fld_childPrice;
    private JLabel lbl_bedCapacity;
    private JFormattedTextField fld_bedCapacity;
    private JFormattedTextField fld_squareMeter;
    private JLabel lbl_squareMeter;
    private JCheckBox chck_television;
    private JCheckBox chck_miniBar;
    private JCheckBox chck_gameConsole;
    private JCheckBox chck_cashBox;
    private JCheckBox chck_projection;
    private JButton btn_save;
    private JButton btn_cancel;
    private JLabel lbl_otel;
    private Otel otel;
    private OtelManager otelManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private Room room;
    private RoomManager roomManager;

    public RoomSaveView(Otel otel, Room room) {
        if (room != null){
            int seasonID = room.getSeasonID();
            int pensionID = room.getPensionID();

            this.fld_adultPrice.setText(String.valueOf(room.getAdultPrice()));
            this.fld_childPrice.setText(String.valueOf(room.getChildPrice()));
            this.fld_stock.setText(String.valueOf(room.getStock()));
            this.fld_squareMeter.setText(String.valueOf(room.getSquareMeter()));
            this.fld_bedCapacity.setText(String.valueOf(room.getBedCapacity()));

            if(room.isTelevision()){
                this.chck_television.setSelected(true);
            }

            if(room.isProjection()){
                this.chck_projection.setSelected(true);
            }

            if(room.isCashBox()){
                this.chck_cashBox.setSelected(true);
            }

            if(room.isGameConsole()){
                this.chck_gameConsole.setSelected(true);
            }

        }
        this.otel = otel;
        this.otelManager = new OtelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitilaze(900, 600, "Oda Ekle");

        this.fld_otel.setText(otel.getName());

        for (Pension pension : pensionManager.getAllOtelPension(otel.getOtel_id())) {
            cmb_pension.addItem(pension.getComboItem());
        }

        for (Season season : seasonManager.getAllOtelSeason(otel.getOtel_id())) {
            cmb_season.addItem(season.getComboItem());
        }


        btn_cancel.addActionListener(e -> {
            dispose();
        });

        btn_save.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_adultPrice) || Helper.isFieldEmpty(fld_childPrice) || Helper.isFieldEmpty(fld_squareMeter) || Helper.isFieldEmpty(fld_childPrice) || Helper.isFieldEmpty(fld_squareMeter) || Helper.isFieldEmpty(fld_stock)) {
                Helper.showMsg("fill");
            } else {
                try {
                    boolean isSuccess;
                    ComboItem selectedPension = (ComboItem) cmb_pension.getSelectedItem();
                    ComboItem selectedSeason = (ComboItem) cmb_season.getSelectedItem();
                    String selectedType = (String) cmb_type.getSelectedItem();

                    if (room == null) {
                        isSuccess = roomManager.save(new Room(otel.getOtel_id(), selectedPension.getKey(), selectedSeason.getKey(), selectedType, Integer.parseInt(fld_stock.getText()), Double.parseDouble(fld_adultPrice.getText()), Double.parseDouble(fld_childPrice.getText()), Integer.parseInt(fld_bedCapacity.getText()), Integer.parseInt(fld_squareMeter.getText()), chck_television.isSelected(), chck_miniBar.isSelected(), chck_gameConsole.isSelected(), chck_cashBox.isSelected(), chck_projection.isSelected()));
                        if (isSuccess) {
                            Helper.showMsg("done");
                        } else {
                            Helper.showMsg("error");
                        }
                    } else{
                        room.setPensionID(selectedPension.getKey());
                        room.setSeasonID(selectedSeason.getKey());
                        room.setType(selectedType);
                        room.setStock(Integer.parseInt(fld_stock.getText()));
                        room.setAdultPrice(Double.parseDouble(fld_adultPrice.getText()));
                        room.setChildPrice(Double.parseDouble(fld_childPrice.getText()));
                        room.setBedCapacity(Integer.parseInt(fld_bedCapacity.getText()));
                        room.setSquareMeter(Integer.parseInt(fld_squareMeter.getText()));
                        room.setTelevision(chck_television.isSelected());
                        room.setMinibar(chck_miniBar.isSelected());
                        room.setGameConsole(chck_gameConsole.isSelected());
                        room.setCashBox(chck_cashBox.isSelected());
                        room.setProjection(chck_projection.isSelected());
                        isSuccess = roomManager.update(room);
                        if (isSuccess) {
                            Helper.showMsg("done");
                        } else {
                            Helper.showMsg("error");
                        }
                    }
                } catch (Exception ex) {
                    Helper.showMsg("input");
                }
            }
        });
    }
}
