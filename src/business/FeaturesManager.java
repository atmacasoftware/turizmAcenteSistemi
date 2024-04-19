package business;

import core.Helper;
import dao.FeaturesDao;
import dao.UserDao;
import entity.Features;
import entity.User;

import java.util.ArrayList;

public class FeaturesManager {
    private FeaturesDao featuresDao;

    public FeaturesManager() {
        this.featuresDao = new FeaturesDao();
    }

    public Features getById(int id) {
        return featuresDao.getById(id);
    }

    public ArrayList<Features> getAllFeature() {
        return this.featuresDao.getAllFeature();
    }


    public boolean save(Features features){
        if (features.getFeature_id() == 0){
            return featuresDao.save(features);
        }
        return false;
    }

    public boolean update(Features features){
        if(getById(features.getFeature_id()) == null){
            Helper.showMsg("notFound");
        }
        return featuresDao.update(features);
    }

    public boolean delete(int id) {
        if(getById(id) == null){
            Helper.showMsg(id + "numaralı özellik bulunamadı !");
            return false;
        }

        return featuresDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Features> list) {
        ArrayList<Object[]> rowList = new ArrayList<>();

        for(Features features : list) {
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = features.getFeature_id();
            row[i++] = features.getFeature();
            row[i++] = features.getCreated_at().toString();
            row[i++] = features.getUpdated_at().toString();
            rowList.add(row);
        }
        return rowList;
    }

    // Otel - Özellik İşlemleri

    public ArrayList<Features> getAllOtelFeature(int otel_id) {
        return this.featuresDao.getAllOtelFeature(otel_id);
    }

    public ArrayList<Object[]> getForTableOtelFeature(int size, ArrayList<Features> list) {
        ArrayList<Object[]> rowList = new ArrayList<>();

        for(Features features : list) {
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = features.getFeature_id();
            row[i++] = features.getFeature();
            rowList.add(row);
        }
        return rowList;
    }


}
