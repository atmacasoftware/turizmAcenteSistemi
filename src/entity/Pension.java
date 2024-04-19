package entity;

import core.ComboItem;

import java.time.LocalDate;

public class Pension {
    private int pension_id;
    private String pensiyon_name;
    private LocalDate created_at;
    private LocalDate updated_at;

    public Pension(){}

    public Pension(int pension_id, String pensiyon_name, LocalDate created_at, LocalDate updated_at) {
        this.pension_id = pension_id;
        this.pensiyon_name = pensiyon_name;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Pension(String pensiyon_name, LocalDate created_at, LocalDate updated_at) {
        this.pensiyon_name = pensiyon_name;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }

    public String getPensiyon_name() {
        return pensiyon_name;
    }

    public void setPensiyon_name(String pensiyon_name) {
        this.pensiyon_name = pensiyon_name;
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
        return new ComboItem(getPension_id(), getPensiyon_name());
    }
}
