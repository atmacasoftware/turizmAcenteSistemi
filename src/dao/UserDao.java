package dao;

import core.Database;
import core.Helper;
import entity.User;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserDao {
    private final Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private Statement statement;
    private User user;

    public UserDao() {
        this.con = Database.getInstance();
    }

    public User getById(int id) {

        User user = null;
        String query = "SELECT * FROM public.user where id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = match(rs);
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM public.user";
        try {
            statement = this.con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                userList.add(match(rs));
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User login(String email, String password) {
        String query = "SELECT * FROM public.user WHERE email = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                this.user = match(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return this.user;
    }


    public boolean save(User user) {
        String query = "INSERT INTO public.user (first_name, last_name, email, mobile_phone, password, role, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            if (isCheckEmail(user.getEmail())) {
                Helper.showMsg("uniqError");
                return false;
            } else {
                preparedStatement = this.con.prepareStatement(query);
                preparedStatement.setString(1, user.getFirst_name());
                preparedStatement.setString(2, user.getLast_name());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getMobile_phone());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.setString(6, user.getRole().toString());
                preparedStatement.setDate(7, Date.valueOf(user.getCreated_at()));
                preparedStatement.setDate(8, Date.valueOf(user.getUpdated_at()));
                return preparedStatement.executeUpdate() != 1;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(User user) {
        String query = "UPDATE public.user SET first_name = ?, last_name = ?, email = ?, mobile_phone = ?, password = ?, role = ?, updated_at = ? WHERE id = ?";
        try {
            if (isCheckEmail(user.getEmail())) {
                Helper.showMsg("uniqError");
                return false;
            } else {
                preparedStatement = this.con.prepareStatement(query);
                preparedStatement.setString(1, user.getFirst_name());
                preparedStatement.setString(2, user.getLast_name());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getMobile_phone());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.setString(6, user.getRole().toString());
                preparedStatement.setDate(7, Date.valueOf(user.getUpdated_at()));
                preparedStatement.setInt(8, user.getUser_id());
                return preparedStatement.executeUpdate() != 1;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.user WHERE id = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> selectByQuery(String query) {
        ArrayList<User> userList = new ArrayList<>();
        try{
            rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                userList.add(match(rs));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return userList;
    }

    public User match(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUser_id(rs.getInt("id"));
        user.setFirst_name(rs.getString("first_name"));
        user.setLast_name(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setMobile_phone(rs.getString("mobile_phone"));
        user.setPassword(rs.getString("password"));
        user.setRole(User.Role.valueOf(rs.getString("role")));
        user.setCreated_at(rs.getDate("created_at").toLocalDate());
        user.setUpdated_at(rs.getDate("updated_at").toLocalDate());
        user.setRole(User.Role.valueOf(rs.getString("role")));
        return user;
    }

    public Boolean isCheckEmail(String email) {
        String query = "SELECT * FROM public.user WHERE email = ?";
        try {
            preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, email);
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
