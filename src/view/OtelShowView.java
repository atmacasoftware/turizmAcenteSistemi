package view;

import business.FeaturesManager;
import business.OtelManager;
import business.PensionManager;
import business.RoomManager;
import entity.Features;
import entity.Otel;
import entity.Pension;
import entity.Room;

import javax.swing.*;
import java.util.ArrayList;

public class OtelShowView extends Layout{
    private JPanel container;
    private JLabel lbl_otelName;
    private JLabel lbl_city;
    private JLabel lbl_region;
    private JLabel lbl_star;
    private JLabel lbl_email;
    private JLabel lbl_phone;
    private JLabel lbl_address;
    private JList list_feature;
    private JList list_pension;
    private Otel otel;
    private OtelManager otelManager;
    private FeaturesManager featuresManager;
    private PensionManager pensionManager;

    public OtelShowView(Otel otel) {
        this.otel = otel;
        this.otelManager = new OtelManager();
        this.featuresManager = new FeaturesManager();
        this.pensionManager = new PensionManager();
        this.add(container);
        this.guiInitilaze(850, 550, String.valueOf(otel.getName()));

        this.lbl_otelName.setText(otel.getName());
        this.lbl_star.setText(String.valueOf(otel.getStar()));
        this.lbl_email.setText(otel.getEmail());
        this.lbl_phone.setText(otel.getPhone());
        this.lbl_address.setText(otel.getAddress());
        this.lbl_city.setText(otel.getCity());
        this.lbl_region.setText(otel.getRegion());

        DefaultListModel featureList = new DefaultListModel();
        ArrayList<Features> items = featuresManager.getAllOtelFeature(otel.getOtel_id());
        for (Features f : items) {
            featureList.addElement(f.getFeature());
        }
        this.list_feature.setModel(featureList);

        DefaultListModel pensionList = new DefaultListModel();
        ArrayList<Pension> pensionItems = pensionManager.getAllOtelPension(otel.getOtel_id());
        for (Pension p : pensionItems) {
            pensionList.addElement(p.getPensiyon_name());
        }
        this.list_pension.setModel(pensionList);


    }

}
