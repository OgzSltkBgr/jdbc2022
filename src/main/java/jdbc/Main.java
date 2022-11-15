package jdbc;

import java.sql.Connection;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        //DBWork objesini oluştur.
        DBWork db = new DBWork();

        //Connection methodunu çağır.
        Connection con = db.connec_to_db("techproed","postgres","Saltuk40");

        //Yeni table oluşturma methodunu çağır
        db.createTable(con,"employees");






    }

}
