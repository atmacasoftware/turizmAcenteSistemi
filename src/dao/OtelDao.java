package dao;

import core.Database;
import core.Helper;
import entity.Otel;
import entity.Pension;

import java.sql.*;
import java.util.ArrayList;

public class OtelDao {
    private final Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private Statement statement;
    private Otel otel;

    public OtelDao() {
        this.con = Database.getInstance();
    }

    public Otel getById(int id) {
        Otel otel = null;
        String query = "SELECT * FROM public.otel WHERE otel_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                otel = match(rs);
            }
            return otel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Otel> getAllOtel() {
        ArrayList<Otel> otelList = new ArrayList<>();
        String query = "SELECT * FROM public.otel ORDER BY otel_id ASC";
        try {
            statement = this.con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                otelList.add(match(rs));
            }
            return otelList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean save(Otel otel) {
        String query = "INSERT INTO public.otel (name, address, email, phone, star, city, region, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, otel.getName());
            preparedStatement.setString(2, otel.getAddress());
            preparedStatement.setString(3, otel.getEmail());
            preparedStatement.setString(4, otel.getPhone());
            preparedStatement.setString(5, otel.getStar());
            preparedStatement.setString(6, otel.getCity());
            preparedStatement.setString(7, otel.getRegion());
            preparedStatement.setDate(8, Date.valueOf(otel.getCreated_at()));
            preparedStatement.setDate(9, Date.valueOf(otel.getUpdated_at()));
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Otel otel) {
        String query = "UPDATE public.otel SET name = ?, address = ?, email = ?, phone = ?, star = ?, city = ?, region = ?, updated_at = ? WHERE otel_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, otel.getName());
            preparedStatement.setString(2, otel.getAddress());
            preparedStatement.setString(3, otel.getEmail());
            preparedStatement.setString(4, otel.getPhone());
            preparedStatement.setString(5, otel.getStar());
            preparedStatement.setString(6, otel.getCity());
            preparedStatement.setString(7, otel.getRegion());
            preparedStatement.setDate(8, Date.valueOf(otel.getUpdated_at()));
            preparedStatement.setInt(9, otel.getOtel_id());
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.otel WHERE otel_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Otel match(ResultSet rs) throws SQLException {
        Otel otel = new Otel();
        otel.setOtel_id(rs.getInt("otel_id"));
        otel.setName(rs.getString("name"));
        otel.setEmail(rs.getString("email"));
        otel.setPhone(rs.getString("phone"));
        otel.setStar(rs.getString("star"));
        otel.setCity(rs.getString("city"));
        otel.setRegion(rs.getString("region"));
        otel.setAddress(rs.getString("address"));
        otel.setCreated_at(rs.getDate("created_at").toLocalDate());
        otel.setUpdated_at(rs.getDate("updated_at").toLocalDate());
        return otel;
    }

    // Otel Özellik İşlemleri

    public boolean saveFeature(int feature_id, int otel_id) {
        String query = "INSERT INTO public.otel_feature (feature_id, otel_id) VALUES (?, ?)";
        try {
            System.out.println(feature_id);
            System.out.println(otel_id);
            System.out.println(isCheckOtelFeature(feature_id, otel_id));
            if (isCheckOtelFeature(feature_id, otel_id)) {
                Helper.showMsg("exists");
                return false;
            } else {
                preparedStatement = this.con.prepareStatement(query);
                preparedStatement.setInt(1, feature_id);
                preparedStatement.setInt(2, otel_id);
                return preparedStatement.executeUpdate() == 1;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteFeature(int feature_id, int otel_id) {
        String query = "DELETE FROM public.otel_feature WHERE otel_id = ? AND feature_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, otel_id);
            preparedStatement.setInt(2, feature_id);
            return preparedStatement.executeUpdate() != 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Otel> getOtelFeaturesById(int id) {
        ArrayList<Otel> otelList = new ArrayList<>();
        String query = "SELECT * FROM public.otel as otel INNER JOIN public.otel_feature as feature ON otel.otel_id = feature.otel_id WHERE otel.otel_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                otelList.add(match(rs));
            }
            return otelList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isCheckOtelFeature(int feature_id, int otel_id) {
        String query = "SELECT * FROM public.otel_feature WHERE otel_id = ? AND feature_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, otel_id);
            preparedStatement.setInt(2, feature_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Otel - Pansiyon Tipi İşlemleri

    public boolean savePension(int pension_id, int otel_id) {
        String query = "INSERT INTO public.pension_type (pension_id, otel_id) VALUES (?, ?)";
        try {
            if (isCheckOtelPension(pension_id, otel_id)) {
                Helper.showMsg("exists");
                return false;
            } else {
                preparedStatement = this.con.prepareStatement(query);
                preparedStatement.setInt(1, pension_id);
                preparedStatement.setInt(2, otel_id);
                return preparedStatement.executeUpdate() == 1;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deletePension(int pension_id, int otel_id) {
        String query = "DELETE FROM public.pension_type WHERE otel_id = ? AND pension_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, otel_id);
            preparedStatement.setInt(2, pension_id);
            return preparedStatement.executeUpdate() != 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Otel> getOtelPensionById(int id) {
        ArrayList<Otel> otelList = new ArrayList<>();
        String query = "SELECT * FROM public.otel as otel INNER JOIN public.pension_type as p_t ON otel.otel_id = p_t.otel_id WHERE otel.otel_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                otelList.add(match(rs));
            }
            return otelList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isCheckOtelPension(int pension_id, int otel_id) {
        String query = "SELECT * FROM public.pension_type WHERE otel_id = ? AND pension_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, otel_id);
            preparedStatement.setInt(2, pension_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
