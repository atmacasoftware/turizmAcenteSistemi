package entity;

import business.OtelManager;
import business.RoomManager;

import java.time.LocalDate;

public class Reservation {
    private int reservationID;
    private int room_id;
    private String customerFullName;
    private String customerEmail;
    private String customerPhone;
    private String customerIdendity;
    private int adulCount;
    private int childCount;
    private double totalPrice;
    private String status;
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate in_date;
    private LocalDate out_date;

    public Reservation() {}

    public Reservation(int reservationID, int room_id, String customerFullName, String customerEmail, String customerPhone, String customerIdendity, int adulCount, int childCount, double totalPrice, String status, LocalDate created_at, LocalDate updated_at, LocalDate in_date, LocalDate out_date) {
        this.reservationID = reservationID;
        this.room_id = room_id;
        this.customerFullName = customerFullName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerIdendity = customerIdendity;
        this.adulCount = adulCount;
        this.childCount = childCount;
        this.totalPrice = totalPrice;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.in_date = in_date;
        this.out_date = out_date;
    }

    public Reservation(int room_id, String customerFullName, String customerEmail, String customerPhone, String customerIdendity, int adulCount, int childCount, double totalPrice, String status, LocalDate created_at, LocalDate updated_at, LocalDate in_date, LocalDate out_date) {
        this.reservationID = reservationID;
        this.room_id = room_id;
        this.customerFullName = customerFullName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerIdendity = customerIdendity;
        this.adulCount = adulCount;
        this.childCount = childCount;
        this.totalPrice = totalPrice;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.in_date = in_date;
        this.out_date = out_date;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerIdendity() {
        return customerIdendity;
    }

    public void setCustomerIdendity(String customerIdendity) {
        this.customerIdendity = customerIdendity;
    }

    public int getAdulCount() {
        return adulCount;
    }

    public void setAdulCount(int adulCount) {
        this.adulCount = adulCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LocalDate getIn_date() {
        return in_date;
    }

    public void setIn_date(LocalDate in_date) {
        this.in_date = in_date;
    }

    public LocalDate getOut_date() {
        return out_date;
    }

    public void setOut_date(LocalDate out_date) {
        this.out_date = out_date;
    }

    public Room getRoom(){
        RoomManager roomManager = new RoomManager();
        Room room = roomManager.getById(getRoom_id());
        return room;
    }

    public Otel getOtel(){
        RoomManager roomManager = new RoomManager();
        Room room = roomManager.getById(getRoom_id());
        OtelManager otelManager = new OtelManager();
        Otel otel = otelManager.getById(room.getOtelID());
        return otel;
    }

}
