package business;

import core.Helper;
import dao.RoomDao;
import entity.Otel;
import entity.Room;
import entity.Season;

import java.sql.Date;
import java.util.ArrayList;

public class RoomManager {
    private RoomDao roomDao;

    public RoomManager(){
        this.roomDao = new RoomDao();
    }

    public Room getById(int id){
        return roomDao.getById(id);
    }

    public Room getByOtelId(int id, int otel_id){
        return roomDao.getByOtelId(id, otel_id);
    }

    public ArrayList<Room> getAllRoom() {
        return this.roomDao.getAllRoom();
    }

    public ArrayList<Room> getAllOtelRoom(int otel_id) {
        return this.roomDao.getAllOtelRoom(otel_id);
    }

    public boolean save(Room room){
        if (room.getRoomID() == 0){
            return roomDao.save(room);
        }
        return false;
    }

    public boolean update(Room room){
        if(getById(room.getRoomID()) == null){
            Helper.showMsg("notFound");
        }
        return roomDao.update(room);
    }

    public boolean delete(int id) {
        if(getById(id) == null){
            Helper.showMsg(id + "numaralı oda bulunamadı !");
            return false;
        }

        return roomDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> list) {
        ArrayList<Object[]> rowList = new ArrayList<>();

        for(Room room : list) {
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = room.getRoomID();
            row[i++] = room.getOtelID();
            row[i++] = room.getPensionID();
            row[i++] = room.getSeasonID();
            row[i++] = room.getType();
            row[i++] = room.getStock();
            row[i++] = room.getAdultPrice();
            row[i++] = room.getChildPrice();
            row[i++] = room.getBedCapacity();
            row[i++] = room.getSquareMeter();
            row[i++] = room.isTelevision();
            row[i++] = room.isMinibar();
            row[i++] = room.isGameConsole();
            row[i++] = room.isCashBox();
            row[i++] = room.isProjection();
            rowList.add(row);
        }
        return rowList;
    }

    public ArrayList<Object[]> getForSortTable(int size, ArrayList<Room> list) {
        ArrayList<Object[]> rowList = new ArrayList<>();

        for(Room room : list) {
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = room.getRoomID();
            row[i++] = room.getOtelById().getName();
            row[i++] = room.getPensionById().getPensiyon_name();
            row[i++] = room.getSeasonById().getStart_season() + " - " + room.getSeasonById().getEnd_season();
            row[i++] = room.getType();
            row[i++] = room.getOtelById().getCity();
            row[i++] = room.getOtelById().getRegion();
            row[i++] = room.getStock();
            row[i++] = room.getAdultPrice();
            row[i++] = room.getChildPrice();
            rowList.add(row);
        }
        return rowList;
    }

    public Boolean isBetweenValue(int room_id, String startDate, String endDate){
        if(startDate.equals("    -  -  ") || endDate.equals("    -  -  ")){
            return false;
        }
        return roomDao.isBetweenValue(room_id, startDate, endDate);
    }

    public ArrayList<Room> filterTable(String otel_name, String city, String region, String startDate, String endDate) {
        ArrayList<String> whereList = new ArrayList<>();
        String select = "SELECT * FROM public.room as r INNER JOIN otel as o ON r.otel_id = o.otel_id INNER JOIN season as s ON r.season_id = s.season_id";
        String query;
        if (otel_name.isEmpty() && city.isEmpty() && region.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            query = select;
        } else {
            if (!otel_name.isEmpty()) {
                whereList.add("o.name = " + "'" + otel_name + "'");
            }
            if (!city.isEmpty()) {
                whereList.add("o.city = " + "'" + city + "'");
            }
            if (!region.isEmpty()) {
                whereList.add("o.region = " + "'" + region + "'");
            }

            if (!startDate.isEmpty() && !startDate.equals("    -  -  ")) {
                whereList.add("s.start_season <= " + "'" + Date.valueOf(startDate) + "'");
            }

            if (!endDate.isEmpty() && !endDate.equals("    -  -  ")) {
                whereList.add("s.end_season >= " + "'" + Date.valueOf(endDate) + "'");
            }

            whereList.add("r.stock >" + 0);

            String whereStr = String.join(" AND ", whereList);
            query = select;

            if (whereStr.length() > 0) {
                query += " WHERE " + whereStr;
            }
        }

        return roomDao.selectByQuery(query);

    }


}
