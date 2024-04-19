package dao;

import core.Database;
import entity.Reservation;
import entity.Room;

import java.sql.*;
import java.util.ArrayList;

public class ReservationDao {
    private final Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private Statement statement;
    private Reservation reservation;

    public ReservationDao() {
        this.con = Database.getInstance();
    }

    public Reservation getById(int id) {
        Reservation reservation = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                reservation = match(rs);
            }
            return reservation;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Reservation getByRoomId(int id, int room_id) {
        Reservation reservation = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ? AND room_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, room_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                reservation = match(rs);
            }
            return reservation;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Reservation> getAllreservation() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM public.reservation ORDER BY reservation_id ASC";
        try {
            statement = this.con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                reservationList.add(match(rs));
            }
            return reservationList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean save(Reservation reservation) {
        String query = "INSERT INTO public.reservation (room_id, customer_fullname, customer_email, customer_phone, customer_identity, total_adultcount, total_childcount, total_price, status, created_at, updated_at, in_date, out_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, reservation.getRoom_id());
            preparedStatement.setString(2, reservation.getCustomerFullName());
            preparedStatement.setString(3, reservation.getCustomerEmail());
            preparedStatement.setString(4, reservation.getCustomerPhone());
            preparedStatement.setString(5, reservation.getCustomerIdendity());
            preparedStatement.setInt(6, reservation.getAdulCount());
            preparedStatement.setInt(7, reservation.getChildCount());
            preparedStatement.setDouble(8, reservation.getTotalPrice());
            preparedStatement.setString(9, reservation.getStatus());
            preparedStatement.setDate(10, Date.valueOf(reservation.getCreated_at()));
            preparedStatement.setDate(11, Date.valueOf(reservation.getUpdated_at()));
            preparedStatement.setDate(12, Date.valueOf(reservation.getIn_date()));
            preparedStatement.setDate(13, Date.valueOf(reservation.getOut_date()));
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET customer_fullname = ?, customer_email = ?, customer_phone = ?, customer_identity = ?, total_adultcount = ?, total_childcount = ?, total_price = ?, status = ?, updated_at = ?, in_date = ?, out_date = ? WHERE reservation_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, reservation.getCustomerFullName());
            preparedStatement.setString(2, reservation.getCustomerEmail());
            preparedStatement.setString(3, reservation.getCustomerPhone());
            preparedStatement.setString(4, reservation.getCustomerIdendity());
            preparedStatement.setInt(5, reservation.getAdulCount());
            preparedStatement.setInt(6, reservation.getChildCount());
            preparedStatement.setDouble(7, reservation.getTotalPrice());
            preparedStatement.setString(8, reservation.getStatus());
            preparedStatement.setDate(9, Date.valueOf(reservation.getUpdated_at()));
            preparedStatement.setDate(10, Date.valueOf(reservation.getIn_date()));
            preparedStatement.setDate(11, Date.valueOf(reservation.getOut_date()));
            preparedStatement.setInt(12, reservation.getReservationID());
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Reservation> selectByQuery(String query){
        ArrayList<Reservation> models = new ArrayList<>();
        try {
            rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                models.add(match(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return models;
    }

    public Reservation match(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setReservationID(rs.getInt("reservation_id"));
        reservation.setRoom_id(rs.getInt("room_id"));
        reservation.setCustomerFullName(rs.getString("customer_fullname"));
        reservation.setCustomerEmail(rs.getString("customer_email"));
        reservation.setCustomerPhone(rs.getString("customer_phone"));
        reservation.setCustomerIdendity(rs.getString("customer_identity"));
        reservation.setAdulCount(rs.getInt("total_adultcount"));
        reservation.setChildCount(rs.getInt("total_childcount"));
        reservation.setTotalPrice(rs.getDouble("total_price"));
        reservation.setStatus(rs.getString("status"));
        reservation.setCreated_at(rs.getDate("created_at").toLocalDate());
        reservation.setUpdated_at(rs.getDate("updated_at").toLocalDate());
        reservation.setIn_date(rs.getDate("in_date").toLocalDate());
        reservation.setOut_date(rs.getDate("out_date").toLocalDate());
        return reservation;
    }

}
