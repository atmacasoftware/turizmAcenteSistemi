package view;

import business.FeaturesManager;
import core.Helper;
import entity.Features;

import javax.swing.*;
import java.time.LocalDate;

public class FeatureSaveView extends Layout {
    private JPanel container;
    private JLabel lbl_title;
    private JLabel lbl_name;
    private JTextField fld_name;
    private JButton btn_save;
    private JButton btn_cancel;
    private Features features;
    private FeaturesManager featuresManager;

    public FeatureSaveView(Features features, String currentDate) {
        if(features != null){
            fld_name.setText(features.getFeature());
        }
        this.features = features;
        this.featuresManager = new FeaturesManager();
        this.add(container);
        guiInitilaze(400, 400, "Özellik Ekle / Güncelle");

        btn_cancel.addActionListener(e -> {
            dispose();
        });

        btn_save.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_name)){
                Helper.showMsg("notNull");
            }else{
                boolean isSuccess;

                if(features == null){
                    isSuccess = featuresManager.save(new Features(fld_name.getText(), LocalDate.parse(currentDate), LocalDate.parse(currentDate)));
                    Helper.showMsg("done");
                    fld_name.setText("");
                } else{
                    features.setFeature(fld_name.getText());
                    features.setUpdated_at(LocalDate.parse(currentDate));
                    Helper.showMsg("done");
                    isSuccess = featuresManager.update(features);
                }
            }
        });
    }

}
