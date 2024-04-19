package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout{

    private JPanel container;
    private JTextField fld_email;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JLabel fld_user_icon;
    private JLabel fld_login_bg;
    private JLabel fld_email_icon;
    private JLabel fld_password_icon;
    private UserManager userManager;


    public LoginView() {
        userManager = new UserManager();
        this.add(container);
        guiInitilaze(900,500, "Giriş Yap");

        //Giriş yap
        btn_login.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_email) || Helper.isFieldEmpty(fld_password)){
                Helper.showMsg("fill");
            }else{
                User login = userManager.login(fld_email.getText(), fld_password.getText());
                if(login != null){
                    Helper.showMsg("done");
                    isAdmin(login);
                }else{
                    Helper.showMsg("notFound");
                }
            }
        });
    }

    // Admin veya Personel kontrolü
    public void isAdmin(User user){
        if (user.getRole().toString().equals("ADMIN")){
            AdminView adminView = new AdminView(user);
            dispose();
        }else{
            PersonelView personelView = new PersonelView(user);
            dispose();
        }
    }

}
