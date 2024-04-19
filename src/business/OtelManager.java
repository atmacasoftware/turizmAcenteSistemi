package business;

import core.Helper;
import dao.OtelDao;
import entity.Features;
import entity.Otel;

import java.util.ArrayList;

public class OtelManager {
    private OtelDao otelDao;

    public OtelManager() {
        this.otelDao = new OtelDao();
    }

    public Otel getById(int id) {
        return otelDao.getById(id);
    }

    public ArrayList<Otel> getAllOtel() {
        return this.otelDao.getAllOtel();
    }

    public boolean save(Otel otel){
        if (otel.getOtel_id() == 0){
            return otelDao.save(otel);
        }
        return false;
    }

    public boolean update(Otel otel){
        if(getById(otel.getOtel_id()) == null){
            Helper.showMsg("notFound");
        }
        return otelDao.update(otel);
    }

    public boolean delete(int id) {
        if(getById(id) == null){
            Helper.showMsg(id + "numaralı otel bulunamadı !");
            return false;
        }

        return otelDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Otel> list) {
        ArrayList<Object[]> rowList = new ArrayList<>();

        for(Otel otel : list) {
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = otel.getOtel_id();
            row[i++] = otel.getName();
            row[i++] = otel.getEmail();
            row[i++] = otel.getPhone();
            row[i++] = otel.getStar();
            row[i++] = otel.getCity();
            row[i++] = otel.getRegion();
            row[i++] = otel.getAddress();
            row[i++] = otel.getCreated_at().toString();
            row[i++] = otel.getUpdated_at().toString();
            rowList.add(row);
        }
        return rowList;
    }

    //Otel Özellik İşlemleri
    public boolean saveFeature(int feature_id, int otel_id){
        return otelDao.saveFeature(feature_id, otel_id);
    }

    public boolean deleteFeature(int feature_id, int otel_id){
        return otelDao.deleteFeature(feature_id, otel_id);
    }

    public ArrayList<Otel> getOtelFeatureById(int id) {
        return this.otelDao.getOtelFeaturesById(id);
    }

    //Otel - Pansiyon İşlemleri
    public boolean savePension(int pension_id, int otel_id){
        return otelDao.savePension(pension_id, otel_id);
    }

    public boolean deletePension(int pension_id, int otel_id){
        return otelDao.deletePension(pension_id, otel_id);
    }

    public ArrayList<Otel> getOtelPensionById(int id) {
        return this.otelDao.getOtelPensionById(id);
    }

}
