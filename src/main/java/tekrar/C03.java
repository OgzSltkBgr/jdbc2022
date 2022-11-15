package tekrar;

import java.sql.*;

public class C03 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","Saltuk40");
        Statement st =con.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan
        // company ve number_of_employees değerlerini çağırın.

        String sql1 ="Select company, number_of_employees" +
                " from companies" +
                " where number_of_employees = (select max(number_of_employees)" +
                " from companies where number_of_employees <(select max(number_of_employees) from companies))";
        ResultSet rs1=st.executeQuery(sql1);

        while(rs1.next()){
            System.out.println(rs1.getString("company")+"--> "+ rs1.getInt("number_of_employees"));
        }

        String sql2 ="Select company,number_of_employees \n" +
                "from companies\n" +
                "order by number_of_employees desc\n" +
                "offset 1 row\n" +
                "fetch next 1 row only";

        ResultSet result2=st.executeQuery(sql2);

        while(result2.next()) {
            System.out.println(result2.getString("company") + "-->" + result2.getInt("number_of_employees"));
        }
        con.close();
        st.close();
        rs1.close();
        result2.close();
    }
}