package dao;

import core.Database;
import core.Helper;
import entity.Features;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class FeaturesDao {
    private final Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private Statement statement;
    private Features features;

    public FeaturesDao() {
        this.con = Database.getInstance();
    }

    public Features getById(int id) {
        Features features = null;
        String query = "SELECT * FROM public.features where feature_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                features = match(rs);
            }
            return features;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Features> getAllFeature() {
        ArrayList<Features> featureList = new ArrayList<>();
        String query = "SELECT * FROM public.features";
        try {
            statement = this.con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                featureList.add(match(rs));
            }
            return featureList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean save(Features features) {
        String query = "INSERT INTO public.features (feature, created_at, updated_at) VALUES (?, ?, ?)";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, features.getFeature());
            preparedStatement.setDate(2, Date.valueOf(features.getCreated_at()));
            preparedStatement.setDate(3, Date.valueOf(features.getUpdated_at()));
            return preparedStatement.executeUpdate() != 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Features features) {
        String query = "UPDATE public.features SET feature = ?, updated_at = ? WHERE feature_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, features.getFeature());
            preparedStatement.setDate(2, Date.valueOf(features.getUpdated_at()));
            preparedStatement.setInt(3, features.getFeature_id());
            return preparedStatement.executeUpdate() != 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.features WHERE feature_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Features match(ResultSet rs) throws SQLException {
        Features features = new Features();
        features.setFeature_id(rs.getInt("feature_id"));
        features.setFeature(rs.getString("feature"));
        features.setCreated_at(rs.getDate("created_at").toLocalDate());
        features.setUpdated_at(rs.getDate("updated_at").toLocalDate());
        return features;
    }

    // Otel - Özellik işlemleri

    public ArrayList<Features> getAllOtelFeature(int otel_id) {
        ArrayList<Features> featureList = new ArrayList<>();
        String query = "SELECT * FROM public.features as f INNER JOIN public.otel_feature as o_f ON f.feature_id = o_f.feature_id WHERE o_f.otel_id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, otel_id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                featureList.add(match(rs));
            }
            return featureList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
