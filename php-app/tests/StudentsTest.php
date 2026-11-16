
<?php

use PHPUnit\Framework\TestCase;

require_once __DIR__ . '/Students.php';

class StudentsTest extends TestCase
{
    private PDO $conn;

    protected function setUp(): void
    {
        $this->conn = new PDO(
            'pgsql:host=localhost;port=5432;dbname=tp',
            'postgres',
            'postgres'
        );

        $this->conn->exec("TRUNCATE TABLE students RESTART IDENTITY");
    }

    public function testAddAndReadStudent(): void
    {
        Students::addStudent($this->conn, 'Amel', 15);

        $student = Students::getStudent($this->conn, 'Amel');

        $this->assertNotNull($student);
        $this->assertSame('Amel', $student['name']);
        $this->assertEquals(15.0, (float) $student['grade']);
    }

    public function testMissingStudent(): void
    {
        $student = Students::getStudent($this->conn, 'Inconnu');

        $this->assertNull($student);
    }

    public function testAverageGrade(): void
    {
        Students::addStudent($this->conn, 'Amel', 10);
        Students::addStudent($this->conn, 'Karim', 14);

        $this->assertEquals(12.0, Students::averageGrade($this->conn));
    }
}
