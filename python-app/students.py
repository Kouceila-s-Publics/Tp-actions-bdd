def average_grade():
    cursor = conn.cursor()
    cursor.execute("SELECT AVG(grade) FROM students")
    result = cursor.fetchone()
    return result[0]