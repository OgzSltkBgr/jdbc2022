package tekrar;

import java.sql.*;

public class C02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","Saltuk40");
        Statement st =con.createStatement();

        //1. Example:  region id'si 1 olan "country name" değerlerini çağırın.
       String sql1="SELECT country_name FROM countries where region_id = 1";
       st.execute(sql1);
        ResultSet result1=st.executeQuery(sql1);

        while (result1.next()) {

            System.out.println(result1.getString(1));

        }


        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
      String sql2="select country_id, country_name from countries where region_id >2";
      ResultSet rs2 = st.executeQuery(sql2);
      while (rs2.next()) {
          System.out.println(rs2.getString(1)+ " --> " + rs2.getString(2));
      }

        //3.Example: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.

        String sql3="select * from companies where number_of_employees =(select min(number_of_employees) from companies)";
      ResultSet rs3 =st.executeQuery(sql3);

      while(rs3.next()){
          System.out.println(rs3.getInt(1)+" -->"+rs3.getString(2)+" -->"+rs3.getInt(3));
      }
        con.close();
        st.close();
    }
}
