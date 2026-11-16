
<?php

class Students
{
    public static function addStudent(PDO $conn, string $name, float $grade): void
    {
        $sql = "INSERT INTO students (name, grade) VALUES (:name, :grade)";

        $stmt = $conn->prepare($sql);
        $stmt->execute([
            ':name' => $name,
            ':grade' => $grade
        ]);
    }

    public static function getStudent(PDO $conn, string $name): ?array
    {
        $sql = "SELECT id, name, grade FROM students WHERE name = :name";

        $stmt = $conn->prepare($sql);
        $stmt->execute([':name' => $name]);

        $student = $stmt->fetch(PDO::FETCH_ASSOC);

        return $student ?: null;
    }

    public static function averageGrade(PDO $conn): float
    {
        $sql = "SELECT AVG(grade) FROM students";

        $result = $conn->query($sql)->fetchColumn();

        return (float) $result;
    }
}
