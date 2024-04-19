package entity;

import business.OtelManager;
import core.ComboItem;

import java.time.LocalDate;

public class Season {
    private int season_id;
    private int otel_id;
    private LocalDate start_season;
    private LocalDate end_season;

    public Season(){}

    public Season(int season_id, int otel_id, LocalDate start_season, LocalDate end_season) {
        this.season_id = season_id;
        this.otel_id = otel_id;
        this.start_season = start_season;
        this.end_season = end_season;
    }

    public Season(int otel_id, LocalDate start_season, LocalDate end_season) {
        this.season_id = season_id;
        this.otel_id = otel_id;
        this.start_season = start_season;
        this.end_season = end_season;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getOtel_id() {
        return otel_id;
    }

    public void setOtel_id(int otel_id) {
        this.otel_id = otel_id;
    }

    public LocalDate getStart_season() {
        return start_season;
    }

    public void setStart_season(LocalDate start_season) {
        this.start_season = start_season;
    }

    public LocalDate getEnd_season() {
        return end_season;
    }

    public void setEnd_season(LocalDate end_season) {
        this.end_season = end_season;
    }

    public int getOtelID(){
        OtelManager otelManager = new OtelManager();
        Otel otel = otelManager.getById(this.otel_id);
        return otel.getOtel_id();
    }

    public String getOtelName(){
        OtelManager otelManager = new OtelManager();
        Otel otel = otelManager.getById(this.otel_id);
        return otel.getName();
    }

    public String getOtelCity(){
        OtelManager otelManager = new OtelManager();
        Otel otel = otelManager.getById(this.otel_id);
        return otel.getCity();
    }

    public String getOtelRegion(){
        OtelManager otelManager = new OtelManager();
        Otel otel = otelManager.getById(this.otel_id);
        return otel.getRegion();
    }

    public ComboItem getComboItem(){
        return new ComboItem(getSeason_id(), getStart_season().toString() + " - " + getEnd_season().toString());
    }
}
