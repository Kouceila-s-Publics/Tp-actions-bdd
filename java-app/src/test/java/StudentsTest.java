
import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class StudentsTest {

    private Connection conn;

    @BeforeEach
    void setUp() throws SQLException {
        conn = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/tp",
            "postgres",
            "postgres"
        );

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("TRUNCATE TABLE students RESTART IDENTITY");
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Test
    void testAddAndReadStudent() throws SQLException {
        Students.addStudent(conn, "Amel", 15);

        Object[] student = Students.getStudent(conn, "Amel");

        assertNotNull(student);
        assertEquals("Amel", student[1]);
        assertEquals(15.0, ((Number) student[2]).doubleValue());
    }

    @Test
    void testMissingStudent() throws SQLException {
        Object[] student = Students.getStudent(conn, "Inconnu");

        assertNull(student);
    }

    @Test
    void testAverageGrade() throws SQLException {
        Students.addStudent(conn, "Amel", 10);
        Students.addStudent(conn, "Karim", 14);

        assertEquals(12.0, Students.averageGrade(conn), 0.01);
    }
}
