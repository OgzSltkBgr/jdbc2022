package jdbc;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","Saltuk40");
        Statement st =con.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        // 1. Adım Prepared statement Query'sini oluştur
        String sql1 = "update companies set number_of_employees = ? where company = ?";

        // 2. Adım PreparedStatement objesini oluştur
        PreparedStatement pst1= con.prepareStatement(sql1);

        // 3. Adım set...() methodları ile '?' için deger gireceğiz
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");

        //4. Adım Query i execute et --> Execute Query
        int updateRowSayisi = pst1.executeUpdate();
        System.out.println(updateRowSayisi+ " satır güncellendi.");

        String sql2 = "SELECT * FROM companies";
        ResultSet rs1 = st.executeQuery(sql2);

        while(rs1.next()) {
            System.out.println(rs1.getInt(1)+ "-->"+ rs1.getString(2)+"-->"+rs1.getInt(3));
        }

        // 3. Adım set...() methodları ile '?' için deger gireceğiz
        pst1.setInt(1,15000);
        pst1.setString(2,"GOOGLE");

        //4. Adım Query i execute et --> Execute Query
        int updateRowSayisi2 = pst1.executeUpdate();
        System.out.println(updateRowSayisi2+ " satır güncellendi.");

        String sql3 = "SELECT * FROM companies";
        ResultSet rs2 = st.executeQuery(sql3);

        while(rs2.next()) {
            System.out.println(rs2.getInt(1)+ "-->"+ rs2.getString(2)+"-->"+rs2.getInt(3));
        }

        //2. Örnek: "SELECT * FROM <table name>" query'sini prepared statement ile kullanın.
        System.out.println("==================================");
        read_date(con,"companies");
     //   String sql4 = "SELECT * FROM ?";
//
     //   PreparedStatement pst2 = con.prepareStatement(sql4);
//
     //   pst2.setString(1,"companies");
//
     //   ResultSet rs3 =st.executeQuery(sql4);
//
     //   while (rs3.next()){
     //       System.out.println(rs3.getInt(1)+ "-->"+ rs3.getString(2)+"-->"+rs3.getInt(3));
     //   }
    }

    //Bir tablonun istenilen datasını prepared statement ile çağırmak için kullanılan method.
    public static void read_date(Connection con,String tableName){

        try {

            String query = String.format("SELECT * FROM %s",tableName);// Format methodu dinamik String oluşturmak için kullanılır.

            Statement statement =con.createStatement();
            //SQL query i çalıştır.
            ResultSet rs=statement.executeQuery(query);// Datayı çağırıp ResultSet Konteynırına koyuyoruz

            while(rs.next()){
                System.out.println(rs.getInt(1)+ "-->"+ rs.getString(2)+"-->"+rs.getInt(3));
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
