package tekrar;

import java.sql.*;

public class C01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Dr iver a kaydolmak için org.postgresql.Driver ekledik
        Class.forName("org.postgresql.Driver");

        // Burdada driver manager üzerinden databas e bağlandık
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","Saltuk40");
        // connection bağlantısıyla statement oluşturcaz
        Statement st = con.createStatement();

        // Bağlantıyı oluşturduk artık burdaki komutlarla sql de işlem yapacağız

        //  Query çalıştır.

        //1.Örnek:"workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
       String sql1 = "create table workers (worker_id varchar(20), worker_name varchar(50), worker_salary int)";
       boolean rs1 =st.execute(sql1);

        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        String sql2="Alter table workers add worker_adress varchar(60)";
        st.execute(sql2);

        //3.Example: Drop workers table
       String sql3 = "Drop table workers";
        st.execute(sql3);
        con.close();
        st.close();
    }
}
