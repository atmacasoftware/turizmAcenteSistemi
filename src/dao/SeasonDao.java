package dao;

import core.Database;
import entity.Features;
import entity.Otel;
import entity.Season;

import java.sql.*;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private Statement statement;
    private Season season;

    public SeasonDao() {
        this.con = Database.getInstance();
    }

    public Season getById(int id) {
        Season season = null;
        String query = "SELECT * FROM public.season WHERE season_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                season = match(rs);
            }
            return season;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Season> getAllSeason() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM public.season ORDER BY season_id ASC";
        try {
            statement = this.con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                seasonList.add(match(rs));
            }
            return seasonList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Season> getByOtelAllSeason(int otel_id) {
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM public.season WHERE otel_id = ? ORDER BY season_id ASC";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, otel_id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                seasonList.add(match(rs));
            }
            return seasonList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Season> getAllSeasonAndOtel() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM public.season as s INNER JOIN public.otel as o ON s.otel_id = o.otel_id";
        try {
            statement = this.con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                seasonList.add(match(rs));
            }
            return seasonList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean save(Season season, Otel otel) {
        String query = "INSERT INTO public.season (start_season, end_season, otel_id) VALUES (?, ?, ?)";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(season.getStart_season()));
            preparedStatement.setDate(2, Date.valueOf(season.getEnd_season()));
            preparedStatement.setInt(3, otel.getOtel_id());
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Season season) {
        String query = "UPDATE public.season SET start_season = ?, end_season = ? WHERE season_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(season.getStart_season()));
            preparedStatement.setDate(2, Date.valueOf(season.getEnd_season()));
            preparedStatement.setInt(3, season.getSeason_id());
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.season WHERE season_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Season> selectByQuery(String query){
        ArrayList<Season> models = new ArrayList<>();
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

    public ArrayList<Season> getAllOtelSeason(int otel_id) {
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM public.season as s INNER JOIN public.otel as o ON s.otel_id = o.otel_id WHERE o.otel_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, otel_id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                seasonList.add(match(rs));
            }
            return seasonList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Season match(ResultSet rs) throws SQLException {
        Season season = new Season();
        season.setStart_season(rs.getDate("start_season").toLocalDate());
        season.setEnd_season(rs.getDate("end_season").toLocalDate());
        season.setSeason_id(rs.getInt("season_id"));
        season.setOtel_id(rs.getInt("otel_id"));
        return season;
    }

}
