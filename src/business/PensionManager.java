package business;

import core.Helper;
import dao.PensionDao;
import entity.Features;
import entity.Pension;

import java.util.ArrayList;

public class PensionManager {
    private PensionDao pensionDao;

    public PensionManager() {
        this.pensionDao = new PensionDao();
    }

    public Pension getById(int id) {
        return pensionDao.getById(id);
    }

    public ArrayList<Pension> getAllPension() {
        return this.pensionDao.getAllPension();
    }


    public boolean save(Pension pension){
        if (pension.getPension_id() == 0){
            return pensionDao.save(pension);
        }
        return false;
    }

    public boolean update(Pension pension){
        if(getById(pension.getPension_id()) == null){
            Helper.showMsg("notFound");
        }
        return pensionDao.update(pension);
    }

    public boolean delete(int id) {
        if(getById(id) == null){
            Helper.showMsg(id + "numaralı özellik bulunamadı !");
            return false;
        }

        return pensionDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> list) {
        ArrayList<Object[]> rowList = new ArrayList<>();

        for(Pension pension : list) {
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = pension.getPension_id();
            row[i++] = pension.getPensiyon_name();
            row[i++] = pension.getCreated_at().toString();
            row[i++] = pension.getUpdated_at().toString();
            rowList.add(row);
        }
        return rowList;
    }

    // Otel - Pansiyon İşlemleri

    public ArrayList<Pension> getAllOtelPension(int otel_id) {
        return this.pensionDao.getAllOtelPension(otel_id);
    }

    public ArrayList<Object[]> getForTableOtelPension(int size, ArrayList<Pension> list) {
        ArrayList<Object[]> rowList = new ArrayList<>();

        for(Pension pension : list) {
            Object[] row = new Object[size];
            int i = 0;
            row[i++] = pension.getPension_id();
            row[i++] = pension.getPensiyon_name();
            rowList.add(row);
        }
        return rowList;
    }


}
