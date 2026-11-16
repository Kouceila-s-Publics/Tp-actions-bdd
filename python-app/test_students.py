def test_moyenne(conn):
 add_student(conn, "Amel", 10) 
 add_student(conn, "Karim", 14)
 assert average_grade(conn) == 12.0 