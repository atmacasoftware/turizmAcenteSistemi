package entity;

import business.OtelManager;
import business.PensionManager;
import business.SeasonManager;

public class Room {
    private int roomID;
    private int otelID;
    private int pensionID;
    private int seasonID;
    private String type;
    private int stock;
    private double adultPrice;
    private double childPrice;
    private int bedCapacity;
    private int squareMeter;
    private boolean television;
    private boolean minibar;
    private boolean gameConsole;
    private boolean cashBox;
    private boolean projection;

    public Room() {}

    public Room(int roomID, int otelID, int pensionID, int seasonID, String type, int stock, double adultPrice, double childPrice, int bedCapacity, int squareMeter, boolean television, boolean minibar, boolean gameConsole, boolean cashBox, boolean projection) {
        this.roomID = roomID;
        this.otelID = otelID;
        this.pensionID = pensionID;
        this.seasonID = seasonID;
        this.type = type;
        this.stock = stock;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.bedCapacity = bedCapacity;
        this.squareMeter = squareMeter;
        this.television = television;
        this.minibar = minibar;
        this.gameConsole = gameConsole;
        this.cashBox = cashBox;
        this.projection = projection;
    }

    public Room(int otelID, int pensionID, int seasonID, String type, int stock, double adultPrice, double childPrice, int bedCapacity, int squareMeter, boolean television, boolean minibar, boolean gameConsole, boolean cashBox, boolean projection) {
        this.otelID = otelID;
        this.pensionID = pensionID;
        this.seasonID = seasonID;
        this.type = type;
        this.stock = stock;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.bedCapacity = bedCapacity;
        this.squareMeter = squareMeter;
        this.television = television;
        this.minibar = minibar;
        this.gameConsole = gameConsole;
        this.cashBox = cashBox;
        this.projection = projection;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getOtelID() {
        return otelID;
    }

    public void setOtelID(int otelID) {
        this.otelID = otelID;
    }

    public int getPensionID() {
        return pensionID;
    }

    public void setPensionID(int pensionID) {
        this.pensionID = pensionID;
    }

    public int getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    public int getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(int bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    public int getSquareMeter() {
        return squareMeter;
    }

    public void setSquareMeter(int squareMeter) {
        this.squareMeter = squareMeter;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(boolean gameConsole) {
        this.gameConsole = gameConsole;
    }

    public boolean isCashBox() {
        return cashBox;
    }

    public void setCashBox(boolean cashBox) {
        this.cashBox = cashBox;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }

    public Otel getOtelById(){
        OtelManager otelManager = new OtelManager();
        Otel otel = otelManager.getById(this.getOtelID());
        return otel;
    }

    public Pension getPensionById(){
        PensionManager pensionManager = new PensionManager();
        Pension pension = pensionManager.getById(this.getPensionID());
        return pension;
    }

    public Season getSeasonById(){
        SeasonManager seasonManager = new SeasonManager();
        Season season = seasonManager.getById(this.getSeasonID());
        return season;
    }

}
