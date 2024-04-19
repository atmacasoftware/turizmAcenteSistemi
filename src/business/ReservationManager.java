package business;

import core.Helper;
import dao.ReservationDao;
import entity.Reservation;
import entity.Room;

import java.sql.Date;
import java.util.ArrayList;

public class ReservationManager {
    private ReservationDao reservationDao;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
    }

    public Reservation getById(int id) {
        return reservationDao.getById(id);
    }

    public Reservation getByRoomId(int id, int room_Id) {
        return reservationDao.getByRoomId(id, room_Id);
    }

    public ArrayList<Reservation> getAllreservation() {
        return this.reservationDao.getAllreservation();
    }

    public boolean save(Reservation reservation){
        if (reservation.getReservationID() == 0){
            return reservationDao.save(reservation);
        }
        return false;
    }

    public boolean update(Reservation reservation){
        if(getById(reservation.getReservationID()) == null){
            Helper.showMsg("notFound");
        }
        return reservationDao.update(reservation);
    }

    public boolean delete(int id) {
        if(getById(id) == null){
            Helper.showMsg(id + "numaralı rezervasyon bulunamadı !");
            return false;
        }

        return reservationDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> list) {
        ArrayList<Object[]> rowList = new ArrayList<>();

        for(Reservation reservation : list) {
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = reservation.getReservationID();
            row[i++] = reservation.getOtel().getName();
            row[i++] = reservation.getRoom().getType();
            row[i++] = reservation.getCustomerFullName();
            row[i++] = reservation.getCustomerEmail();
            row[i++] = reservation.getCustomerPhone();
            row[i++] = reservation.getCustomerIdendity();
            row[i++] = reservation.getAdulCount() + " + " + reservation.getChildCount();
            row[i++] = reservation.getTotalPrice();
            row[i++] = reservation.getIn_date().toString() + " / " + reservation.getOut_date().toString();
            row[i++] = reservation.getStatus();
            row[i++] = reservation.getCreated_at().toString();
            row[i++] = reservation.getUpdated_at().toString();
            rowList.add(row);
        }
        return rowList;
    }

    public ArrayList<Reservation> filterTable(String customer_name, String customer_email, String customer_identity, String status) {
        ArrayList<String> whereList = new ArrayList<>();
        String select = "SELECT * FROM public.reservation";
        String query;
        if (customer_name.isEmpty() && customer_email.isEmpty() && customer_identity.isEmpty() && status.isEmpty()) {
            query = select;
        } else {
            if (!customer_name.isEmpty()) {
                whereList.add("customer_fullname = " + "'" + customer_name + "'");
            }
            if (!customer_email.isEmpty()) {
                whereList.add("customer_email = " + "'" + customer_email + "'");
            }
            if (!customer_identity.isEmpty()) {
                whereList.add("customer_identity = " + "'" + customer_identity + "'");
            }

            if (!status.isEmpty()) {
                whereList.add("status = " + "'" + status + "'");
            }

            String whereStr = String.join(" AND ", whereList);
            query = select;

            if (whereStr.length() > 0) {
                query += " WHERE " + whereStr;
            }
        }

        return reservationDao.selectByQuery(query);

    }


}
