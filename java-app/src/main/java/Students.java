
import java.sql.*;

public class Students {

    public static void addStudent(Connection conn, String name, double grade)
            throws SQLException {

        String sql = "INSERT INTO students (name, grade) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, grade);
            stmt.executeUpdate();
        }
    }

    public static Object[] getStudent(Connection conn, String name)
            throws SQLException {

        String sql = "SELECT id, name, grade FROM students WHERE name = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Object[] {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("grade")
                    };
                }
            }
        }

        return null;
    }

    public static double averageGrade(Connection conn) throws SQLException {

        String sql = "SELECT AVG(grade) FROM students";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            rs.next();
            return rs.getDouble(1);
        }
    }
}
