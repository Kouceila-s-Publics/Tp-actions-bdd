
def add_student(conn, name, grade):
    cursor = conn.cursor()
    cursor.execute(
        "INSERT INTO students (name, grade) VALUES (%s, %s)",
        (name, grade)
    )
    conn.commit()
    cursor.close()


def get_student(conn, name):
    cursor = conn.cursor()
    cursor.execute(
        "SELECT id, name, grade FROM students WHERE name = %s",
        (name,)
    )
    student = cursor.fetchone()
    cursor.close()
    return student


def average_grade(conn):
    cursor = conn.cursor()
    cursor.execute("SELECT AVG(grade) FROM students")
    result = cursor.fetchone()
    cursor.close()
    return result[0]
