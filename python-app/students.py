def average_grade(conn):
    cursor = conn.cursor()
    cursor.execute("SELECT AVG(grade) FROM students")
    result = cursor.fetchone()
    cursor.close()
    return result[0]