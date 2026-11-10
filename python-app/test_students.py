def test_moyenne(conn):
 add_student(conn, "Amel", 10) # 1. Préparer : deux étudiants
 add_student(conn, "Karim", 14)
 assert average_grade(conn) == 12.0 # 2. Agir et 3. Vérifier : (10 + 14) / 2 = 12