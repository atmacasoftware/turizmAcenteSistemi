package entity;

import core.ComboItem;

import java.time.LocalDate;
public class Features {
    private int feature_id;
    private String feature;
    private LocalDate created_at;
    private LocalDate updated_at;

    public Features(){}

    public Features(int feature_id, String feature, LocalDate created_at, LocalDate updated_at) {
        this.feature_id = feature_id;
        this.feature = feature;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Features(String feature, LocalDate created_at, LocalDate updated_at) {
        this.feature = feature;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(int feature_id) {
        this.feature_id = feature_id;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public ComboItem getComboItem(){
        return new ComboItem(getFeature_id(), getFeature());
    }

}
