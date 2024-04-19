package view;

import business.OtelManager;
import core.Helper;
import entity.Otel;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;

public class OtelSaveView extends Layout{
    private JPanel container;
    private JLabel lbl_title;
    private JTextField fld_name;
    private JLabel btn_name;
    private JLabel lbl_email;
    private JTextField fld_email;
    private JTextField fld_phone;
    private JLabel lbl_phone;
    private JTextField fld_city;
    private JLabel lbl_city;
    private JLabel lbl_region;
    private JTextField fld_region;
    private JTextField fld_adres;
    private JLabel lbl_adres;
    private JButton btn_save;
    private JButton btn_cancel;
    private JLabel lbl_star;
    private JTextField fld_star;
    private Otel otel;
    private OtelManager otelManager;

    public OtelSaveView(Otel otel, String currentDate){
        if(otel != null){
            this.fld_name.setText(otel.getName());
            this.fld_email.setText(otel.getEmail());
            this.fld_phone.setText(otel.getPhone());
            this.fld_city.setText(otel.getCity());
            this.fld_region.setText(otel.getRegion());
            this.fld_adres.setText(otel.getAddress());
            this.fld_star.setText(otel.getStar());
        }

        this.otel = otel;
        this.otelManager = new OtelManager();
        this.add(container);
        this.guiInitilaze(500, 600, "Otel Ekle / Güncelle");


        btn_cancel.addActionListener(e -> {
            dispose();
        });

        btn_save.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_email) || Helper.isFieldEmpty(fld_phone) || Helper.isFieldEmpty(fld_city) || Helper.isFieldEmpty(fld_region) || Helper.isFieldEmpty(fld_adres) || Helper.isFieldEmpty(fld_star)){
                Helper.showMsg("notNull");
            }else{
                boolean isSuccess;

                if(otel == null){
                    isSuccess = otelManager.save(new Otel(fld_name.getText(), fld_adres.getText(), fld_email.getText(), fld_phone.getText(), fld_star.getText(), fld_city.getText(), fld_region.getText(), LocalDate.parse(currentDate), LocalDate.parse(currentDate)));
                    if(isSuccess){
                        Helper.showMsg("done");
                        dispose();
                    }else{
                        Helper.showMsg("error");
                    }

                } else{
                    otel.setName(fld_name.getText());
                    otel.setEmail(fld_email.getText());
                    otel.setPhone(fld_phone.getText());
                    otel.setCity(fld_city.getText());
                    otel.setRegion(fld_region.getText());
                    otel.setAddress(fld_adres.getText());
                    otel.setStar(fld_star.getText());
                    otel.setUpdated_at(LocalDate.parse(currentDate));
                    isSuccess = otelManager.update(otel);
                    if(isSuccess){
                        Helper.showMsg("done");
                        dispose();
                    }else{
                        Helper.showMsg("error");
                    }
                }

            }
        });
    }

    private void createUIComponents() {
        try{
            this.fld_phone = new JFormattedTextField(new MaskFormatter("0(###) ### ## ##"));
        }catch (ParseException e){
            e.printStackTrace();;
        }
    }
}
