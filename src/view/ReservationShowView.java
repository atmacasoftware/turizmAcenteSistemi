package view;

import business.FeaturesManager;
import business.PensionManager;
import business.ReservationManager;
import business.RoomManager;
import entity.Features;
import entity.Pension;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.ArrayList;

public class ReservationShowView extends Layout{
    private JPanel container;
    private JLabel lbl_otelName;
    private JLabel lbl_email;
    private JLabel lbl_phone;
    private JLabel lbl_star;
    private JLabel lbl_city;
    private JLabel lbl_region;
    private JLabel lbl_address;
    private JList list_feature;
    private JList list_pension;
    private JFormattedTextField fld_customerPhone;
    private JTextField fld_customerName;
    private JTextField fld_customerEmail;
    private JTextField fld_customerIdentity;
    private JFormattedTextField fld_inDate;
    private JFormattedTextField fld_outDate;
    private JTextField fld_adulCount;
    private JTextField fld_childCount;
    private JTextField fld_totalPrice;
    private JCheckBox chck_television;
    private JCheckBox chck_minibar;
    private JCheckBox chchk_gameconsole;
    private JCheckBox chck_cashBox;
    private JCheckBox chck_protection;
    private JTextField fld_roomType;
    private JTextField fld_bedCapacity;
    private JTextField fld_squareMeter;
    private ReservationManager reservationManager;
    private Reservation reservation;
    private Room room;
    private RoomManager roomManager;
    private FeaturesManager featuresManager;
    private PensionManager pensionManager;

    public ReservationShowView(Room room, Reservation reservation) {
        this.room = room;
        this.reservation = reservation;
        this.reservationManager = new ReservationManager();
        this.roomManager = new RoomManager();
        this.featuresManager = new FeaturesManager();
        this.pensionManager = new PensionManager();
        this.add(container);
        this.guiInitilaze(1200, 750, String.valueOf(room.getOtelById().getName()));

        //Otel özelliklieri
        this.lbl_otelName.setText(room.getOtelById().getName());
        this.lbl_star.setText(String.valueOf(room.getOtelById().getStar()));
        this.lbl_email.setText(room.getOtelById().getEmail());
        this.lbl_phone.setText(room.getOtelById().getPhone());
        this.lbl_address.setText(room.getOtelById().getAddress());
        this.lbl_city.setText(room.getOtelById().getCity());
        this.lbl_region.setText(room.getOtelById().getRegion());

        //Oda Özellikleri
        this.fld_roomType.setText(room.getType());
        this.fld_bedCapacity.setText(String.valueOf(room.getBedCapacity()));
        this.fld_squareMeter.setText(String.valueOf(room.getSquareMeter()));

        if(room.isTelevision()){
            this.chck_television.setSelected(true);
        }

        if(room.isMinibar()){
            this.chck_minibar.setSelected(true);
        }

        if(room.isGameConsole()){
            this.chchk_gameconsole.setSelected(true);
        }

        if(room.isProjection()){
            this.chck_protection.setSelected(true);
        }

        if(room.isCashBox()){
            this.chck_cashBox.setSelected(true);
        }

        //Müşteri Bilgileri
        this.fld_customerName.setText(reservation.getCustomerFullName());
        this.fld_customerEmail.setText(reservation.getCustomerEmail());
        this.fld_customerPhone.setText(reservation.getCustomerPhone());
        this.fld_customerIdentity.setText(reservation.getCustomerIdendity());
        this.fld_adulCount.setText(String.valueOf(reservation.getAdulCount()));
        this.fld_childCount.setText(String.valueOf(reservation.getChildCount()));
        this.fld_totalPrice.setText(String.valueOf(reservation.getTotalPrice()));
        this.fld_inDate.setText(String.valueOf(reservation.getIn_date()));
        this.fld_outDate.setText(String.valueOf(reservation.getOut_date()));

        DefaultListModel featureList = new DefaultListModel();
        ArrayList<Features> items = featuresManager.getAllOtelFeature(room.getOtelById().getOtel_id());
        for (Features f : items) {
            featureList.addElement(f.getFeature());
        }
        this.list_feature.setModel(featureList);

        DefaultListModel pensionList = new DefaultListModel();
        ArrayList<Pension> pensionItems = pensionManager.getAllOtelPension(room.getOtelById().getOtel_id());
        for (Pension p : pensionItems) {
            pensionList.addElement(p.getPensiyon_name());
        }
        this.list_pension.setModel(pensionList);


    }

    private void createUIComponents() {
        try{
            this.fld_customerPhone = new JFormattedTextField(new MaskFormatter("0(###) ### ## ##"));
        }catch (ParseException es){
            es.printStackTrace();
        }
    }
}
