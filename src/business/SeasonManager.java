package business;

import core.Helper;
import dao.SeasonDao;
import entity.Features;
import entity.Otel;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private SeasonDao seasonDao;

    public SeasonManager() {
        this.seasonDao = new SeasonDao();
    }

    public Season getById(int id) {
        return seasonDao.getById(id);
    }

    public ArrayList<Season> getAllSeason() {
        return this.seasonDao.getAllSeason();
    }

    public ArrayList<Season> getAllSeasonAndOtel() {
        return this.seasonDao.getAllSeasonAndOtel();
    }

    public ArrayList<Season> getByOtelAllSeason(int otel_id) {
        return this.seasonDao.getByOtelAllSeason(otel_id);
    }

    public boolean save(Season season, Otel otel) {
        if (season.getSeason_id() == 0) {
            return seasonDao.save(season, otel);
        }
        return false;
    }

    public boolean update(Season season) {
        if (getById(season.getSeason_id()) == null) {
            Helper.showMsg("notFound");
        }
        return seasonDao.update(season);
    }

    public boolean delete(int id) {
        if (getById(id) == null) {
            Helper.showMsg(id + "numaralı dönem bulunamadı !");
            return false;
        }

        return seasonDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> list) {
        ArrayList<Object[]> rowList = new ArrayList<>();

        for (Season season : list) {
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = season.getSeason_id();
            row[i++] = season.getOtel_id();
            row[i++] = season.getOtelName();
            row[i++] = season.getOtelCity();
            row[i++] = season.getOtelRegion();
            row[i++] = season.getStart_season();
            row[i++] = season.getEnd_season();
            rowList.add(row);
        }
        return rowList;
    }

    public ArrayList<Object[]> getForTableOtelSeason(int size, ArrayList<Season> list) {
        ArrayList<Object[]> rowList = new ArrayList<>();

        for (Season season : list) {
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = season.getSeason_id();
            row[i++] = season.getStart_season().toString();
            row[i++] = season.getEnd_season().toString();
            rowList.add(row);
        }
        return rowList;
    }

    public ArrayList<Season> filterTable(String otel_name, String city, String region) {
        ArrayList<String> whereList = new ArrayList<>();
        String select = "SELECT * FROM public.season as s INNER JOIN public.otel as o ON s.otel_id = o.otel_id";
        String query;
        if (otel_name.isEmpty() && city.isEmpty() && region.isEmpty()) {
            select = "SELECT * FROM public.season as s INNER JOIN public.otel as o ON s.otel_id = o.otel_id";
            query = select;
        } else {
            if (otel_name != null) {
                whereList.add("o.name = " + "'" + otel_name + "'");
            }
            if (city != null) {
                whereList.add("o.city = " + "'" + city + "'");
            }
            if (region != null) {
                whereList.add("o.region = " + "'" + region + "'");
            }
            String whereStr = String.join(" OR ", whereList);
            query = select;
            if (whereStr.length() > 0) {
                query += " WHERE " + whereStr;
            }
        }

        return seasonDao.selectByQuery(query);

    }

    public ArrayList<Season> getAllOtelSeason(int otel_id) {
        return this.seasonDao.getAllOtelSeason(otel_id);
    }


}
