package view;

import business.UserManager;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonelView extends Layout{
    private JPanel container;
    private JButton btn_otelManagement;
    private JButton btn_sessionManagement;
    private JButton btn_rezervasyon;
    private JButton btn_roomManagement;
    private JButton btn_logout;
    private JLabel lbl_welcome;
    private User user;
    private UserManager userManager;

    public PersonelView(User user){
        this.add(container);
        this.userManager = new UserManager();
        this.user = user;
        guiInitilaze(1200, 750, "Turizm Acente Sistemi");

        //this.lbl_welcome.setText(user.getFirst_name() + " " + user.getLast_name());

        btn_logout.addActionListener(e -> { // Giriş ekranına yönlendirme
            LoginView loginView = new LoginView();
        });


        btn_otelManagement.addActionListener(e -> {
            OtelView otelView = new OtelView(user);
            dispose();
        });

        btn_sessionManagement.addActionListener(e -> {
            SeasonView seasonView = new SeasonView(user);
            dispose();
        });

        btn_roomManagement.addActionListener(e -> {
            RoomView roomView = new RoomView(user);
            dispose();
        });

        btn_rezervasyon.addActionListener(e -> {
            ReservationView reservationView = new ReservationView(user);
            dispose();
        });
    }

}
