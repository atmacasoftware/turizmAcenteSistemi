package view;

import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Reservation;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ReservationView extends Layout {
    private JPanel container;
    private JButton btn_back;
    private JButton btn_search;
    private JTable tbl_reservation;
    private JTextField fld_customerName;
    private JLabel lbl_customerName;
    private JLabel lbl_customerEmail;
    private JTextField fld_customerEmail;
    private JTextField fld_customerIdentity;
    private JLabel lbl_status;
    private JLabel lbl_customerIdentity;
    private JComboBox cmb_status;
    private DefaultTableModel model;
    private Object[] columnNames;
    private JPopupMenu reservationPopMenu;
    private ReservationManager reservationManager;
    private RoomManager roomManager;

    public ReservationView(User user) {
        this.reservationManager = new ReservationManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitilaze(1200, 750, "Rezervasyon İşlemleri");

        btn_back.addActionListener(e -> {
            PersonelView personelView = new PersonelView(user);
            dispose();
        });

        reservationTableRefresh(null);
        popMenusReservation();

        btn_search.addActionListener(e -> {
            String selectedStatus = (String) cmb_status.getSelectedItem();
            ArrayList<Reservation> reservationListTableSearch = reservationManager.filterTable(fld_customerName.getText(), fld_customerEmail.getText(), fld_customerIdentity.getText(), selectedStatus);
            ArrayList<Object[]> reservationListObject = reservationManager.getForTable(columnNames.length, reservationListTableSearch);
            reservationTableRefresh(reservationListObject);
        });
    }

    public void reservationTableRefresh(ArrayList<Object[]> roomListObject) {
        columnNames = new Object[]{"ID", "Otel Adı", "Oda Tipi", "Müşteri Adı", "Müşteri E-Posta", "Müşteri Telefon","Müşteri Kimlik No", "Yetişkin - Çocuk Sayısı",  "Toplam Tutar", "Giriş - Çıkış Tarihi", "Rezervasyon Durumu", "Oluşturulma Tarihi", "Güncellenme Tarihi"};
        if (roomListObject == null) {
            roomListObject = reservationManager.getForTable(columnNames.length, reservationManager.getAllreservation());
        }

        createTable(model, tbl_reservation, columnNames, roomListObject);
    }

    public void popMenusReservation() {
        this.reservationPopMenu = new JPopupMenu();

        tableMouseSelect(tbl_reservation);

        reservationPopMenu.add("Görüntüle").addActionListener(e -> {
            ReservationShowView reservationShowView = new ReservationShowView(reservationManager.getById(getSelectedRow(tbl_reservation, 0)).getRoom(),reservationManager.getById(getSelectedRow(tbl_reservation, 0)));
            reservationShowView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    reservationTableRefresh(null);
                }
            });
        });

        reservationPopMenu.add("Güncelle").addActionListener(e -> {
            ReservationSaveView reservationSaveView = new ReservationSaveView(reservationManager.getById(getSelectedRow(tbl_reservation, 0)).getRoom(),reservationManager.getById(getSelectedRow(tbl_reservation, 0)), String.valueOf(getToday()));
            reservationSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    reservationTableRefresh(null);
                }
            });
        });

        reservationPopMenu.add("Sil").addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Silmek istediğinize emin misiniz ?", "Onaylama", JOptionPane.YES_NO_OPTION) == 0) {
                //Rezervasyonun silinmesi durumunda oda stoğunu 1 arttırma işlemi
                Room room = reservationManager.getById(getSelectedRow(tbl_reservation, 0)).getRoom();
                int currentStock = room.getStock();
                room.setStock(currentStock + 1);
                roomManager.update(room);

                reservationManager.delete(getSelectedRow(tbl_reservation, 0));
                Helper.showMsg("done");
                reservationTableRefresh(null);
            } else {
                Helper.showMsg("");
            }
        });

        tbl_reservation.setComponentPopupMenu(reservationPopMenu);

    }

}
