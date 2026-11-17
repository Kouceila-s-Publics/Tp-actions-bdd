
import pytest
import psycopg2
from students import add_student, get_student, average_grade


@pytest.fixture
def conn():
    connection = psycopg2.connect(
        host="localhost",
        database="tp",
        user="postgres",
        password="postgres"
    )

    cursor = connection.cursor()
    cursor.execute("TRUNCATE TABLE students RESTART IDENTITY")
    connection.commit()
    cursor.close()

    yield connection

    connection.close()


# Test 1 : Ajouter un étudiant puis le relire
def test_ajouter_et_lire(conn):
    add_student(conn, "Amel", 15)

    student = get_student(conn, "Amel")

    assert student is not None
    assert student[1] == "Amel"
    assert float(student[2]) == 15.0


# Test 2 : Lire un étudiant inexistant
def test_etudiant_inexistant(conn):
    student = get_student(conn, "Inconnu")

    assert student is None


# Test 3 : Calculer la moyenne de deux étudiants
def test_moyenne(conn):
    add_student(conn, "Amel", 10)
    add_student(conn, "Karim", 14)

    assert float(average_grade(conn)) == 12.0
