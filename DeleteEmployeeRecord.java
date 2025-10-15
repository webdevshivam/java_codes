import java.sql.*;
import java.util.Scanner;

public class DeleteEmployeeRecord {
  public static void main(String[] args) throws Exception {

    String url = "jdbc:mysql://localhost:3306/your_database_name";
    String user = "root";
    String password = "root";

    Scanner sc = new Scanner(System.in);
    System.out.print("Enter Employee ID to delete: ");
    int empId = sc.nextInt();

    // Load the MySQL driver
    Class.forName("com.mysql.cj.jdbc.Driver");

    // Establish connection
    Connection con = DriverManager.getConnection(url, user, password);

    // Delete query
    String deleteQuery = "DELETE FROM employee WHERE emp_id = ?";
    PreparedStatement pstmt = con.prepareStatement(deleteQuery);
    pstmt.setInt(1, empId);

    int rowsAffected = pstmt.executeUpdate();
    if (rowsAffected > 0) {
      System.out.println("Employee record deleted successfully!");
    } else {
      System.out.println("No record found with Employee ID: " + empId);
    }

    // Display remaining records
    String selectQuery = "SELECT * FROM employee";
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(selectQuery);

    System.out.println("\nRemaining Employee Records:");
    System.out.println("----------------------------------------");
    while (rs.next()) {
      int id = rs.getInt("emp_id");
      String name = rs.getString("emp_name");
      String dept = rs.getString("department");
      double salary = rs.getDouble("salary");

      System.out.println("ID: " + id + ", Name: " + name + ", Dept: " + dept + ", Salary: " + salary);
    }

    // Close resources
    rs.close();
    stmt.close();
    pstmt.close();
    con.close();
  }
}
