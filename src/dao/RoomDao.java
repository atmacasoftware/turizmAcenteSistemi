package dao;

import core.Database;
import entity.Otel;
import entity.Room;
import entity.Season;

import java.sql.*;
import java.util.ArrayList;

public class RoomDao {
    private final Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private Statement statement;
    private Room room;

    public RoomDao() {
        this.con = Database.getInstance();
    }

    public Room getById(int id) {
        Room room = null;
        String query = "SELECT * FROM public.room WHERE room_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                room = match(rs);
            }
            return room;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Room getByOtelId(int id, int otel_id) {
        Room room = null;
        String query = "SELECT * FROM public.room WHERE room_id = ? AND otel_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, otel_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                room = match(rs);
            }
            return room;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Room> getAllRoom() {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM public.room WHERE stock > 0 ORDER BY room_id ASC";
        try {
            statement = this.con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                roomList.add(match(rs));
            }
            return roomList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Room> getAllOtelRoom(int otel_id) {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM public.room WHERE otel_id = ? ORDER BY room_id ASC";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, otel_id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                roomList.add(match(rs));
            }
            return roomList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean save(Room room) {
        String query = "INSERT INTO public.room (otel_id, pension_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, cash_box, projection) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, room.getOtelID());
            preparedStatement.setInt(2, room.getPensionID());
            preparedStatement.setInt(3, room.getSeasonID());
            preparedStatement.setString(4, room.getType());
            preparedStatement.setInt(5, room.getStock());
            preparedStatement.setDouble(6, room.getAdultPrice());
            preparedStatement.setDouble(7, room.getChildPrice());
            preparedStatement.setInt(8, room.getBedCapacity());
            preparedStatement.setInt(9, room.getSquareMeter());
            preparedStatement.setBoolean(10, room.isTelevision());
            preparedStatement.setBoolean(11, room.isMinibar());
            preparedStatement.setBoolean(12, room.isGameConsole());
            preparedStatement.setBoolean(13, room.isCashBox());
            preparedStatement.setBoolean(14, room.isProjection());
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Room room) {
        String query = "UPDATE public.room SET pension_id = ?, season_id = ?, type = ?, stock = ?, adult_price = ?, child_price = ?, bed_capacity = ?, square_meter = ?, television = ?, minibar = ?, game_console = ?, cash_box = ?, projection = ? WHERE room_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, room.getPensionID());
            preparedStatement.setInt(2, room.getSeasonID());
            preparedStatement.setString(3, room.getType());
            preparedStatement.setInt(4, room.getStock());
            preparedStatement.setDouble(5, room.getAdultPrice());
            preparedStatement.setDouble(6, room.getChildPrice());
            preparedStatement.setInt(7, room.getBedCapacity());
            preparedStatement.setInt(8, room.getSquareMeter());
            preparedStatement.setBoolean(9, room.isTelevision());
            preparedStatement.setBoolean(10, room.isMinibar());
            preparedStatement.setBoolean(11, room.isGameConsole());
            preparedStatement.setBoolean(12, room.isCashBox());
            preparedStatement.setBoolean(13, room.isProjection());
            preparedStatement.setInt(14, room.getRoomID());
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.room WHERE roon_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isBetweenValue(int room_id, String startDate, String endDate) {
        String query = "SELECT * FROM public.room as r INNER JOIN season as s ON r.season_id = s.season_id WHERE r.room_id = ? AND s.start_season <= ? AND s.end_season >= ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, room_id);
            preparedStatement.setDate(2, Date.valueOf(startDate));
            preparedStatement.setDate(3, Date.valueOf(endDate));
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Room> selectByQuery(String query){
        ArrayList<Room> models = new ArrayList<>();
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

    public Room match(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setRoomID(rs.getInt("room_id"));
        room.setOtelID(rs.getInt("otel_id"));
        room.setPensionID(rs.getInt("pension_id"));
        room.setSeasonID(rs.getInt("season_id"));
        room.setType(rs.getString("type"));
        room.setStock(rs.getInt("stock"));
        room.setAdultPrice(rs.getDouble("adult_price"));
        room.setChildPrice(rs.getDouble("child_price"));
        room.setBedCapacity(rs.getInt("bed_capacity"));
        room.setSquareMeter(rs.getInt("square_meter"));
        room.setTelevision(rs.getBoolean("television"));
        room.setMinibar(rs.getBoolean("minibar"));
        room.setGameConsole(rs.getBoolean("game_console"));
        room.setCashBox(rs.getBoolean("cash_box"));
        room.setProjection(rs.getBoolean("projection"));
        return room;
    }

}
