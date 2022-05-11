package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class AddDataPrepareStatement
{
    public static void main(String[] args)
    {
        int TOTAL_COLUMN = 3;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDatabase", "root", "Mind@123");
// checkme
            String sql = "insert into employee_info(firstName, lastName) VALUES (?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            BufferedReader enter = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter First Name:");

            stmt.setString(1, enter.readLine());

            System.out.print("Enter Last Name:");

            stmt.setString(2, enter.readLine());

            int row = stmt.executeUpdate();

            System.out.println("Rows affected: " + row);

            Statement displayStmt = con.createStatement();

            ResultSet rs = displayStmt.executeQuery("select * from employee_info");

            while (rs.next())
            {
                for (int i = 1; i <= TOTAL_COLUMN; i++)
                {
                    System.out.print(rs.getString(i) + "  ");
                }
                System.out.println();
            }

// To delete last 4 entries from the table
// DELETE FROM employee_info ORDER BY id DESC LIMIT 4;

            ResultSet resultSetId = stmt.getGeneratedKeys();


            while (resultSetId.next())
            {
                System.out.println(resultSetId.getLong(1));
            }

            con.close();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
