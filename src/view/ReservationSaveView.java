package view;

import business.FeaturesManager;
import business.OtelManager;
import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Features;
import entity.Otel;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReservationSaveView extends Layout {
    private JPanel container;
    private JTextField fld_otelName;
    private JLabel lbl_otelName;
    private JLabel lbl_city;
    private JTextField fld_city;
    private JTextField fld_address;
    private JTextField fld_star;
    private JLabel lbl_star;
    private JTextField fld_roomType;
    private JLabel lbl_roomType;
    private JTextField fld_pensionType;
    private JLabel lbl_pensionType;
    private JLabel lbl_startDate;
    private JFormattedTextField fld_startDate;
    private JFormattedTextField fld_endDate;
    private JLabel lbl_endDate;
    private JLabel lbl_bedCapacity;
    private JTextField fld_bedCapacity;
    private JTextField fld_squareMeter;
    private JLabel lbl_squareMeter;
    private JCheckBox chck_television;
    private JCheckBox chck_minibar;
    private JCheckBox chck_gameConsole;
    private JCheckBox chch_cashBox;
    private JCheckBox chck_projection;
    private JLabel lbl_adultCount;
    private JTextField fld_adultCount;
    private JTextField fld_childCount;
    private JTextField fld_customerName;
    private JLabel lbl_customerName;
    private JTextField fld_customerEmail;
    private JLabel lbl_customerEmail;
    private JTextField fld_customerIdentity;
    private JLabel lbl_customerIdentity;
    private JFormattedTextField fld_customerPhone;
    private JTextField fld_totalPrice;
    private JLabel lbl_totalPrice;
    private JButton btn_calcPrice;
    private JButton btn_save;
    private JButton iptalEtButton;
    private JLabel lbl_status;
    private JComboBox cmb_status;
    private JLabel lbl_enterDate;
    private JFormattedTextField fld_enterDate;
    private JLabel lbl_outDate;
    private JFormattedTextField fld_outDate;
    private JList list_feature;
    private Room room;
    private RoomManager roomManager;
    private Reservation reservation;
    private ReservationManager reservationManager;
    private FeaturesManager featuresManager;


    ReservationSaveView(Room room, Reservation reservation, String currentDate) {
        if(reservation != null){
            String in_date = String.valueOf(reservation.getIn_date());
            String[] inDateSplit = in_date.split("-");
            String out_date = String.valueOf(reservation.getOut_date());
            String[] outDateSplit = out_date.split("-");
            String inDate = inDateSplit[2] + "/" + inDateSplit[1] + "/" + inDateSplit[0];
            String outDate = outDateSplit[2] + "/" + outDateSplit[1] + "/" + outDateSplit[0];
            this.fld_adultCount.setText(String.valueOf(reservation.getAdulCount()));
            this.fld_childCount.setText(String.valueOf(reservation.getChildCount()));
            this.fld_totalPrice.setText(String.valueOf(reservation.getTotalPrice()));
            this.fld_customerName.setText(String.valueOf(reservation.getCustomerFullName()));
            this.fld_customerEmail.setText(String.valueOf(reservation.getCustomerEmail()));
            this.fld_customerPhone.setText(String.valueOf(reservation.getCustomerPhone()));
            this.fld_customerIdentity.setText(String.valueOf(reservation.getCustomerIdendity()));
            this.fld_enterDate.setText(inDate);
            this.fld_outDate.setText(outDate);
        }
        this.room = room;
        this.reservationManager = new ReservationManager();
        this.featuresManager = new FeaturesManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitilaze(1200, 750, "Rezervasyon Ekle");

        this.fld_otelName.setText(room.getOtelById().getName());
        this.fld_city.setText(room.getOtelById().getCity());
        this.fld_address.setText(room.getOtelById().getAddress());
        this.fld_star.setText(room.getOtelById().getStar());

        this.fld_roomType.setText(room.getType());
        this.fld_pensionType.setText(room.getPensionById().getPensiyon_name());
        this.fld_bedCapacity.setText(String.valueOf(room.getBedCapacity()));
        this.fld_squareMeter.setText(String.valueOf(room.getSquareMeter()));
        this.fld_startDate.setText(room.getSeasonById().getStart_season().toString());
        this.fld_endDate.setText(room.getSeasonById().getEnd_season().toString());

        if (room.isTelevision()) {
            this.chck_television.setSelected(true);
        }

        if (room.isProjection()) {
            this.chck_projection.setSelected(true);
        }

        if (room.isMinibar()) {
            this.chck_minibar.setSelected(true);
        }

        if (room.isCashBox()) {
            this.chch_cashBox.setSelected(true);
        }

        if (room.isGameConsole()) {
            this.chck_gameConsole.setSelected(true);
        }


        DefaultListModel featureList = new DefaultListModel();
        ArrayList<Features> items = featuresManager.getAllOtelFeature(room.getOtelID());
        for (Features f : items) {
            featureList.addElement(f.getFeature());
        }
        this.list_feature.setModel(featureList);

        btn_calcPrice.addActionListener(e -> {
            String startFull = this.fld_enterDate.getText();
            String[] startSplit = startFull.split("/");
            String endFull = this.fld_outDate.getText();
            String[] endSplit = endFull.split("/");
            String startDate = startSplit[2] + "-" + startSplit[1] + "-" + startSplit[0];
            String endDate = endSplit[2] + "-" + endSplit[1] + "-" + endSplit[0];

            boolean dateControl = roomManager.isBetweenValue(room.getRoomID(), startDate, endDate);
            if (!dateControl) {
                Helper.showMsg("dateError");
            } else {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime date1 = LocalDate.parse(this.fld_enterDate.getText(), dtf).atStartOfDay();
                LocalDateTime date2 = LocalDate.parse(this.fld_outDate.getText(), dtf).atStartOfDay();

                if (date1.compareTo(date2) < 0) {
                    long dayCount = Duration.between(date1, date2).toDays();
                    Double calcPrice;

                    try {
                        calcPrice = (room.getAdultPrice() * dayCount * Integer.parseInt(this.fld_adultCount.getText())) + (room.getChildPrice() * dayCount * Integer.parseInt(this.fld_childCount.getText()));
                        this.fld_totalPrice.setText(String.valueOf(calcPrice));
                        if (!this.fld_totalPrice.getText().isEmpty()) {
                            this.btn_save.setEnabled(true);
                        }
                    } catch (Exception es) {
                        Helper.showMsg("inputError");
                    }
                } else{
                    Helper.showMsg("dateMinusError");
                }
            }
        });


        btn_save.addActionListener(e -> {
            if(Helper.isFieldEmpty(this.fld_adultCount) || Helper.isFieldEmpty(this.fld_childCount) || Helper.isFieldEmpty(this.fld_customerName) || Helper.isFieldEmpty(this.fld_customerEmail) || Helper.isFieldEmpty(this.fld_customerIdentity) || Helper.isFieldEmpty(this.fld_customerPhone) || Helper.isFieldEmpty(this.fld_totalPrice)) {
                Helper.showMsg("fill");
            } else {
                String selectedStatus = (String) cmb_status.getSelectedItem();
                boolean isSuccess;

                String c_in_date = this.fld_enterDate.getText();
                String[] cInDateSplit = c_in_date.split("/");
                String c_out_date = this.fld_outDate.getText();
                String[] cOutDateSplit = c_out_date.split("/");
                String cInDate = cInDateSplit[2] + "-" + cInDateSplit[1] + "-" + cInDateSplit[0];
                String cOutDate = cOutDateSplit[2] + "-" + cOutDateSplit[1] + "-" + cOutDateSplit[0];

                if(reservation == null){
                    isSuccess = reservationManager.save(new Reservation(room.getRoomID(), this.fld_customerName.getText(), this.fld_customerEmail.getText(), this.fld_customerPhone.getText(), this.fld_customerIdentity.getText(), Integer.parseInt(this.fld_adultCount.getText()), Integer.parseInt(this.fld_childCount.getText()), Double.parseDouble(this.fld_totalPrice.getText()), selectedStatus, LocalDate.parse(currentDate), LocalDate.parse(currentDate), LocalDate.parse(cInDate), LocalDate.parse(cOutDate)));
                    if(isSuccess){
                        Helper.showMsg("done");
                        //Oda stok sayısını düşürme işlemi
                        int currentStock = room.getStock();
                        room.setStock(currentStock - 1);
                        roomManager.update(room);

                        //Rezervasyon başarılı ise rezervasyon özeti görüntüleme ekranına gidiş
                        ReservationShowView reservationShowView = new ReservationShowView(room, new Reservation(room.getRoomID(), this.fld_customerName.getText(), this.fld_customerEmail.getText(), this.fld_customerPhone.getText(), this.fld_customerIdentity.getText(), Integer.parseInt(this.fld_adultCount.getText()), Integer.parseInt(this.fld_childCount.getText()), Double.parseDouble(this.fld_totalPrice.getText()), selectedStatus, LocalDate.parse(currentDate), LocalDate.parse(currentDate), LocalDate.parse(cInDate), LocalDate.parse(cOutDate)));
                        dispose();
                    }else{
                        Helper.showMsg("error");
                    }
                }else{
                    reservation.setRoom_id(room.getRoomID());
                    reservation.setCustomerFullName(this.fld_customerName.getText());
                    reservation.setCustomerEmail(this.fld_customerEmail.getText());
                    reservation.setCustomerPhone(this.fld_customerPhone.getText());
                    reservation.setCustomerIdendity(this.fld_customerIdentity.getText());
                    reservation.setAdulCount(Integer.parseInt(this.fld_adultCount.getText()));
                    reservation.setChildCount(Integer.parseInt(this.fld_childCount.getText()));
                    reservation.setTotalPrice(Double.parseDouble(this.fld_totalPrice.getText()));
                    reservation.setUpdated_at(LocalDate.parse(currentDate));
                    reservation.setStatus(selectedStatus);
                    reservation.setIn_date(LocalDate.parse(cInDate));
                    reservation.setOut_date(LocalDate.parse(cOutDate));
                    isSuccess = reservationManager.update(reservation);
                    if(isSuccess){
                        Helper.showMsg("done");
                    }else{
                        Helper.showMsg("error");
                    }
                }
            }
        });
    }

    private void createUIComponents() {
        try {
            this.fld_enterDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
            this.fld_outDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
            this.fld_customerPhone = new JFormattedTextField(new MaskFormatter("0(###) ### ## ##"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
