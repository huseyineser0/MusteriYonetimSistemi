package core;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class Helper {
    public static void setTheme() {
        // BU TEMA KODUNU İNTERNETTE ÇOK RAAHAT BULABİLİRSİN
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (info.getName().equals("Nimbus")) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException expetion) {
                    expetion.printStackTrace();
                }
                break;
            }

        }
    }
    public static boolean isFieldEmpty(JTextField field){
        // trim boşlukku kontrol ediyor
        return field.getText().trim().isEmpty();
    }
    public static boolean isFieldListEmpty(JTextField[] fields){
        for (JTextField field: fields){
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }
    // bu metod uyarı verdiğinde ok yerine tamam yazıyor
    public static void optionPanelDialogTR(){
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");
    }

    //mail kontrol
    // hsyesr@eser.com
    //@ olacak @.2ten önce bir değer @'tan sonra bir nokta ve bir değer olacak

    public static boolean isEmailValid(String mail){

        if (mail==null || mail.trim().isEmpty()) return false;

        if (!mail.contains("@")) return false;

        String[] parts=mail.split("@");

        if (parts.length!=2) return false;

        if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) return false;

        if (!parts[1].contains(".")) return false;

        return true;

    }
    public static void showMsg(String message){
        String msg;
        String title;
        optionPanelDialogTR();
        switch (message){

            case "fiil":
                msg="Lütfen Tüm Alanları Doldurunuz";
                title="HATA";
                break;

            case "done":
                msg="işlem Başarılı";
                title="HATA";
                break;

            case "error":
                msg="Bir Hata Oluştu";
                title="HATA";
                break;

            default:
                msg=message;
                title="HATA";

        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);

    }
    public static boolean confirm(String str){
        optionPanelDialogTR();
        String msg;
        if (str.equals("sure")){
            msg="Bu işlemi gerçekleştirmek istediğinize emin misinz ";
        }else {
            msg=str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Emin misin ?",JOptionPane.YES_NO_OPTION)==0;
    }

}
