package view;

import business.RoomManager;
import core.Helper;
import entity.Otel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class OtelRoomView extends Layout {
    private JPanel container;
    private JButton btn_add;
    private JTable tbl_room;
    private JLabel lbl_otelName;
    private Otel otel;
    private DefaultTableModel model;
    private Object[] columnNames;
    private RoomManager roomManager;
    private JPopupMenu roomPopMenu;

    public OtelRoomView(Otel otel) {
        this.otel = otel;
        this.roomManager = new RoomManager();
        this.add(container);
        String title = otel.getName() + " " + "Oteline Ait Odalar";
        this.guiInitilaze(750, 500, title);

        this.lbl_otelName.setText(otel.getName());

        btn_add.addActionListener(e -> {
            RoomSaveView roomSaveView = new RoomSaveView(otel, null);
            roomSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    otelRoomTableRefresh(null);
                }
            });
        });

        otelRoomTableRefresh(null);
        popMenusRoom();
    }

    public void otelRoomTableRefresh(ArrayList<Object[]> otelRoomListObject) {
        columnNames = new Object[]{"Room ID", "Otel ID", "Pansiyon ID", "Sezon ID", "Tip", "Stok", "Yetişkin Fiyat", "Çocuk Fiyat", "Yatak Kapasitesi", "Oda Alanı", "Televizyon", "Minibar", "Oyun Konsolu", "Para Kasası", "Projeksiyon"};
        if (otelRoomListObject == null) {
            otelRoomListObject = roomManager.getForTable(columnNames.length, roomManager.getAllOtelRoom(otel.getOtel_id()));
        }

        createTable(model, tbl_room, columnNames, otelRoomListObject);
    }

    public void popMenusRoom() {
        this.roomPopMenu = new JPopupMenu();

        tableMouseSelect(tbl_room);

        roomPopMenu.add("Güncelle").addActionListener(e -> {
            RoomSaveView roomSaveView = new RoomSaveView(otel, roomManager.getById(getSelectedRow(tbl_room, 0)));
            roomSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    otelRoomTableRefresh(null);
                }
            });
        });

        roomPopMenu.add("Sil").addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Silmek istediğinize emin misiniz ?", "Onaylama", JOptionPane.YES_NO_OPTION) == 0) {
                roomManager.delete(getSelectedRow(tbl_room, 0));
                Helper.showMsg("done");
                otelRoomTableRefresh(null);
            } else {
                Helper.showMsg("");
            }
        });

        tbl_room.setComponentPopupMenu(roomPopMenu);

    }

}
