package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserSaveView extends Layout {
    private JPanel container;
    private JLabel lbl_title;
    private JTextField fld_firstname;
    private JTextField fld_lastname;
    private JTextField fld_email;
    private JTextField fld_mobilephone;
    private JPasswordField fld_password;
    private JRadioButton radio_admin;
    private JRadioButton radio_personel;
    private JButton btn_save;
    private JLabel lbl_firstname;
    private JLabel lbl_lastname;
    private JLabel lbl_email;
    private JLabel lbl_mobilephone;
    private JLabel lbl_password;
    private JButton btn_cancel;
    private UserManager userManager;
    private User user;

    public UserSaveView(User user, String currentDate) {

        userManager = new UserManager();

        if (user != null) {
            fld_firstname.setText(user.getFirst_name());
            fld_lastname.setText(user.getLast_name());
            fld_email.setText(user.getEmail());
            fld_mobilephone.setText(user.getMobile_phone());
            fld_password.setText(user.getPassword());
        }


        this.user = user;

        this.add(container);

        guiInitilaze(500, 500, "Kullanıcı Ekle / Güncelle");

        // Kullanıcı Ekle / Güncelle Ekranı Kapat
        btn_cancel.addActionListener(e -> {
            dispose();
        });

        // Kullanıcı Ekleme veya Güncelleme İşlemi
        btn_save.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_firstname) || Helper.isFieldEmpty(fld_lastname) || Helper.isFieldEmpty(fld_email) || Helper.isFieldEmpty(fld_mobilephone) || Helper.isFieldEmpty(fld_password)) {
                Helper.showMsg("fill");
            } else {

                if (user == null) { // Yeni kullanıcı ekleme
                    userManager.save(new User(fld_firstname.getText(),fld_lastname.getText(),fld_email.getText(),fld_mobilephone.getText(),fld_password.getText(), LocalDate.parse(currentDate), LocalDate.parse(currentDate),
                    this.radio_admin.isSelected() ? User.Role.ADMIN : User.Role.PERSONEL));
                    Helper.showMsg("done");
                    dispose();

                } else{ // Kullanıcı güncelleme
                    user.setFirst_name(fld_firstname.getText());
                    user.setLast_name(fld_lastname.getText());
                    user.setEmail(fld_email.getText());
                    user.setMobile_phone(fld_mobilephone.getText());
                    user.setPassword(fld_password.getText());
                    user.setUpdated_at(LocalDate.parse(currentDate));

                    if (this.radio_admin.isSelected()) {
                        user.setRole(User.Role.ADMIN);
                    }else{
                        user.setRole(User.Role.PERSONEL);
                    }

                    userManager.update(user);

                    Helper.showMsg("done");
                    dispose();

                }


            }
        });
    }

}
