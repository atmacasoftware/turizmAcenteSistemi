package dao;

import core.Database;
import core.Helper;
import entity.Features;
import entity.Pension;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class PensionDao {
    private final Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private Statement statement;
    private Pension pension;

    public PensionDao() {
        this.con = Database.getInstance();
    }

    public Pension getById(int id) {
        Pension pension = null;
        String query = "SELECT * FROM public.pension where pension_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                pension = match(rs);
            }
            return pension;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Pension> getAllPension() {
        ArrayList<Pension> pensionList = new ArrayList<>();
        String query = "SELECT * FROM public.pension";
        try {
            statement = this.con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                pensionList.add(match(rs));
            }
            return pensionList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean save(Pension pension) {
        String query = "INSERT INTO public.pension (pension_name, created_at, updated_at) VALUES (?, ?, ?)";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, pension.getPensiyon_name());
            preparedStatement.setDate(2, Date.valueOf(pension.getCreated_at()));
            preparedStatement.setDate(3, Date.valueOf(pension.getUpdated_at()));
            return preparedStatement.executeUpdate() != 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Pension pension) {
        String query = "UPDATE public.pension SET pension_name = ?, updated_at = ? WHERE pension_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, pension.getPensiyon_name());
            preparedStatement.setDate(2, Date.valueOf(pension.getUpdated_at()));
            preparedStatement.setInt(3, pension.getPension_id());
            return preparedStatement.executeUpdate() != 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.pension WHERE pension_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pension match(ResultSet rs) throws SQLException {
        Pension pension = new Pension();
        pension.setPension_id(rs.getInt("pension_id"));
        pension.setPensiyon_name(rs.getString("pension_name"));
        pension.setCreated_at(rs.getDate("created_at").toLocalDate());
        pension.setUpdated_at(rs.getDate("updated_at").toLocalDate());
        return pension;
    }

    // Otel - Pansiyon işlemleri

    public ArrayList<Pension> getAllOtelPension(int otel_id) {
        ArrayList<Pension> pensionList = new ArrayList<>();
        String query = "SELECT * FROM public.pension as p INNER JOIN public.pension_type as p_t ON p.pension_id = p_t.pension_id WHERE p_t.otel_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, otel_id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                pensionList.add(match(rs));
            }
            return pensionList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
