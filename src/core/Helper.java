package core;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void setTheme(){

        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            try{
                FlatGradiantoDeepOceanIJTheme.setup();
            }catch (Exception e){
                e.printStackTrace();
            }
            //if(info.getName().equals("FlatLaf")){
            //    try {
            //        UIManager.setLookAndFeel(info.getClassName());
            //    } catch (ClassNotFoundException e) {
            //        throw new RuntimeException(e);
            //    } catch (InstantiationException e) {
            //        throw new RuntimeException(e);
            //    } catch (IllegalAccessException e) {
            //        throw new RuntimeException(e);
            //    } catch (UnsupportedLookAndFeelException e) {
            //        throw new RuntimeException(e);
            //    }
            //}
        }
    }

    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (int) ((Toolkit.getDefaultToolkit().getScreenSize().width - size.getWidth()) / 2);
            case "y" -> (int) ((Toolkit.getDefaultToolkit().getScreenSize().height - size.getHeight()) / 2);
            default -> 0;
        };
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static void showMsg(String str){
        optionPaneTR();
        String msg;
        String title;

        switch (str){
            case "fill" -> {
                msg = "Lütfen tüm alanlarını doldurunuz !";
                title = "Hata !";
            }
            case "done" -> {
                msg = "İşlem Başarılı !";
                title = "Başarılı !";
            }
            case "notFound" -> {
                msg = "Kullanıcı bulunamadı !";
                title = "Bulunamadı !";
            }
            case "error" -> {
                msg = "Bir hata meydana geldi !";
                title = "Hata !";
            }
            case "cancel" -> {
                msg = "İptal edildi !";
                title = "Sonuç !";
            }
            case "uniqError" -> {
                msg = "Bu email adresine kayıtlı kullanıcı bulunmaktadır !";
                title = "Hata !";
            }
            case "exists" -> {
                msg = "Bu kayıt daha önce yapılmış.";
                title = "Mevcut !";
            }
            case "input" -> {
                msg = "Fiyat alanları (double), stok, oda alanı ve yatak sayısı integar formatta olmalıdır.";
                title = "Girdi Hatası !";
            }
            case "dateError" ->{
                msg = "Giriş tarihi ile çıkış tarihi odanın sezon tarihleri arasında değildir.";
                title = "Hatalı Tarih !";
            }
            case "inputError" -> {
                msg = "Yetişkin sayısı ve çocuk sayısı girilmelidir.";
                title = "Girdi Hatası !";
            }
            case "dateMinusError" -> {
                msg = "Giriş tarihi çıkış tarihinden sonra olamaz !";
                title = "Hatalı Tarih !";
            }
            default -> {
                msg = str;
                title = "Mesaj";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void optionPaneTR() {
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
    }

}
