package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;
import java.util.Objects;

public class UserManager {
    private UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public ArrayList<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }


    public User login(String email, String password) {
        return this.userDao.login(email, password);
    }


    public boolean save(User user){
        if (user.getUser_id() == 0){
            return userDao.save(user);
        }
        return false;
    }

    public boolean update(User user){
        if(getById(user.getUser_id()) == null){
            Helper.showMsg("notFound");
        }
        return userDao.update(user);
    }

    public boolean delete(int id) {
        if(getById(id) == null){
            Helper.showMsg(id + "numaralı kullanıcı bulunamadı !");
            return false;
        }

        return userDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> list) {
        ArrayList<Object[]> rowList = new ArrayList<>();

        for(User user : list) {
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = user.getUser_id();
            row[i++] = user.getFirst_name();
            row[i++] = user.getLast_name();
            row[i++] = user.getEmail();
            row[i++] = user.getMobile_phone();
            row[i++] = user.getRole().toString();
            row[i++] = user.getCreated_at().toString();
            row[i++] = user.getUpdated_at().toString();
            rowList.add(row);
        }
        return rowList;
    }

    public ArrayList<User> filterTable(User.Role role){
        ArrayList<String> whereList = new ArrayList<>();
        String select = "SELECT * FROM public.user";
        String query = select;

        if(role != null && role != User.Role.HEPSI){
            whereList.add("role = '" + role.toString() + "'");
            String whereStr = String.join(" AND ", whereList);
            if(whereStr.length() > 0){
                query += " WHERE " + whereStr;
            }
        }


        return userDao.selectByQuery(query);
    }

}
