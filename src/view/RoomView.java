package view;

import business.OtelManager;
import business.RoomManager;
import core.Helper;
import entity.Otel;
import entity.Room;
import entity.Season;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomView extends Layout{
    private JPanel container;
    private JButton btn_back;
    private JTextField fld_searchOtelName;
    private JLabel lbl_searchOtelName;
    private JLabel lbl_searchCity;
    private JTextField fld_searchCity;
    private JLabel lbl_searchRegion;
    private JTextField fld_searchRegion;
    private JButton btn_search;
    private JButton btn_clear;
    private JTable tbl_room;
    private JTextField fld_startDate;
    private JLabel lbl_startDate;
    private JLabel lbl_endDate;
    private JFormattedTextField fld_endDate;
    private RoomManager roomManager;
    private Room room;
    private Otel otel;
    private OtelManager otelManager;
    private DefaultTableModel model;
    private Object[] columnNames;
    private JPopupMenu roomPopMenu;

    public RoomView(User user){
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitilaze(1200, 750, "Oda Yönetimi");

        btn_back.addActionListener(e -> {
            PersonelView personelView = new PersonelView(user);
            dispose();
        });

        roomTableRefresh(null);
        popMenusRoom();

        btn_search.addActionListener(e -> {

            String startFull = fld_startDate.getText();
            String[] startSplit = startFull.split("/");
            String endFull = fld_endDate.getText();
            String[] endSplit = endFull.split("/");
            String startDate = startSplit[2]+"-"+startSplit[1]+"-"+startSplit[0];
            String endDate = endSplit[2]+"-"+endSplit[1]+"-"+endSplit[0];

            ArrayList<Room> roomListTableSearch = roomManager.filterTable(fld_searchOtelName.getText(), fld_searchCity.getText(), fld_searchRegion.getText(), startDate, endDate);
            ArrayList<Object[]> roomListObject = roomManager.getForSortTable(columnNames.length, roomListTableSearch);
            roomTableRefresh(roomListObject);
        });

        btn_clear.addActionListener(e -> {
            this.fld_searchOtelName.setText("");
            this.fld_searchCity.setText("");
            this.fld_searchRegion.setText("");
            this.fld_startDate.setText("");
            this.fld_endDate.setText("");
        });
    }

    public void roomTableRefresh(ArrayList<Object[]> roomListObject){
        columnNames = new Object[]{"Room ID","Otel Adı","Pansiyon","Sezon","Tip", "Şehir", "Bölge","Stok", "Yetişkin Fiyat", "Çocuk Fiyat"};
        if(roomListObject == null){
            roomListObject = roomManager.getForSortTable(columnNames.length, roomManager.getAllRoom());
        }

        createTable(model, tbl_room, columnNames, roomListObject);
    }

    public void popMenusRoom() {
        this.roomPopMenu = new JPopupMenu();

        tableMouseSelect(tbl_room);

        roomPopMenu.add("Yeni Rezervasyon").addActionListener(e -> {
            ReservationSaveView reservationSaveView = new ReservationSaveView(roomManager.getById(getSelectedRow(tbl_room, 0)), null, String.valueOf(getToday()));
            reservationSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    roomTableRefresh(null);
                }
            });
        });

        roomPopMenu.add("Güncelle").addActionListener(e -> {
            RoomSaveView roomSaveView = new RoomSaveView(roomManager.getById(getSelectedRow(tbl_room, 0)).getOtelById(), roomManager.getById(getSelectedRow(tbl_room, 0)));
            roomSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    roomTableRefresh(null);
                }
            });
        });

        roomPopMenu.add("Sil").addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Silmek istediğinize emin misiniz ?", "Onaylama", JOptionPane.YES_NO_OPTION) == 0) {
                roomManager.delete(getSelectedRow(tbl_room, 0));
                Helper.showMsg("done");
                roomTableRefresh(null);
            } else {
                Helper.showMsg("");
            }
        });

        tbl_room.setComponentPopupMenu(roomPopMenu);

    }

    private void createUIComponents() {
        try{
            this.fld_startDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
            this.fld_endDate = new JFormattedTextField(new MaskFormatter("##/##/####"));

        }catch (ParseException e){
            e.printStackTrace();
        }
    }

}
