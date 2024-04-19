package view;

import business.PensionManager;
import core.Helper;
import entity.Pension;

import javax.swing.*;
import java.time.LocalDate;

public class PensionSaveView extends Layout{
    private JPanel container;
    private JLabel lbl_title;
    private JLabel lbl_name;
    private JTextField fld_name;
    private JButton btn_save;
    private JButton btn_cancel;
    private Pension pension;
    private PensionManager pensionManager;

    public PensionSaveView(Pension pension, String currentDate){
        if(pension != null){
            fld_name.setText(pension.getPensiyon_name());
        }
        this.pension = pension;
        this.pensionManager = new PensionManager();
        this.add(container);
        guiInitilaze(400, 400, "Pansiyon Tipi Ekle / Güncelle");


        btn_cancel.addActionListener(e -> {
            dispose();
        });

        btn_save.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_name)){
                Helper.showMsg("notNull");
            }else{
                boolean isSuccess;

                if(pension == null){
                    isSuccess = pensionManager.save(new Pension(fld_name.getText(), LocalDate.parse(currentDate), LocalDate.parse(currentDate)));
                    Helper.showMsg("done");
                    fld_name.setText("");
                } else{
                    pension.setPensiyon_name(fld_name.getText());
                    pension.setUpdated_at(LocalDate.parse(currentDate));
                    Helper.showMsg("done");
                    isSuccess = pensionManager.update(pension);
                }
            }
        });
    }

}
