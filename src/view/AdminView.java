package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.time.LocalDate;

public class AdminView extends Layout {
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTable tbl_users;
    private Object[] columnNames;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JButton btn_add;
    private JButton btn_filter;
    private JComboBox cmb_usertype;
    private JLabel lbl_currentDate;
    private JButton btn_writeFile;
    private User user;
    private UserManager userManager;
    private DefaultTableModel user_model;
    private JPopupMenu userPopMenu;
    private LocalDate today;

    public AdminView(User user) {
        this.add(container);
        this.userManager = new UserManager();
        this.user = user;
        guiInitilaze(1000, 750, "Kullanıcı Yönetimi");
        // Bugünkü zaman
        today = LocalDate.now();

        if (user == null) {
            Helper.showMsg("notFound");
            dispose();
        }

        this.lbl_welcome.setText("Hoşgeldiniz" + " " + this.user.getFirst_name() + " " + this.user.getLast_name());
        this.lbl_currentDate.setText("Tarih : " + String.valueOf(today));

        // Kullanıcı Listesi
        userTableRefresh(null);
        popMenusUser();

        this.cmb_usertype.setModel(new DefaultComboBoxModel(User.Role.values()));

        // Yeni kullanıcı ekleme
        btn_add.addActionListener(e -> {
            UserSaveView userSaveView = new UserSaveView(null, String.valueOf(today));
            userSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    userTableRefresh(null);
                }
            });

        });

        // Kullanıcı rolüne göre filtremele yapma
        btn_filter.addActionListener(e -> {
            ArrayList<User> userListTableSearch = userManager.filterTable((User.Role) cmb_usertype.getSelectedItem());
            ArrayList<Object[]> userListObject = userManager.getForTable(columnNames.length, userListTableSearch);
            userTableRefresh(userListObject);
        });

        // Çıkış işleminden sonra yeniden login ekranına yönlendirme
        btn_logout.addActionListener(e -> {
            LoginView loginView = new LoginView();
            dispose();
        });

    }

    private void userTableRefresh(ArrayList<Object[]> userListObject) {
        columnNames = new Object[]{"ID", "Ad", "Soyad", "E-Posta", "Telefon Numarası", "Kullanıcı Tipi", "Oluşturuma Tarihi", "Güncellenme Tarihi"};
        if(userListObject == null) {
            userListObject = userManager.getForTable(columnNames.length, userManager.getAllUsers());
        }

        createTable(user_model, tbl_users, columnNames, userListObject);
    }

    public void popMenusUser() {
        // User tablosu içi pop menü
        this.userPopMenu = new JPopupMenu();

        // Mouse seçim işlemi
        tableMouseSelect(tbl_users);

        userPopMenu.add("Güncelle").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserSaveView userSaveView = new UserSaveView(userManager.getById(getSelectedRow(tbl_users, 0)),String.valueOf(today));
                userSaveView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        userTableRefresh(null);
                    }
                });
            }
        });

        userPopMenu.add("Sil").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Silmek istediğinize emin misiniz ?", "Onaylama", JOptionPane.YES_NO_OPTION) == 0) {
                    userManager.delete(getSelectedRow(tbl_users, 0));
                    Helper.showMsg("done");
                    userTableRefresh(null);
                } else {
                    Helper.showMsg("");
                }
            }
        });

        tbl_users.setComponentPopupMenu(userPopMenu);

    }

}
