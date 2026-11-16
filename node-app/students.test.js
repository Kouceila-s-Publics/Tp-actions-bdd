const { Client } = require("pg");
const {
    addStudent,
    getStudent,
    averageGrade
} = require("./students");

let client;

beforeAll(async () => {
    client = new Client({
        host: "localhost",
        port: 5432,
        database: "tp",
        user: "postgres",
        password: "postgres"
    });

    await client.connect();
});

beforeEach(async () => {
    await client.query("TRUNCATE TABLE students RESTART IDENTITY");
});

afterAll(async () => {
    await client.end();
});

test("ajouter et lire un étudiant", async () => {
    await addStudent(client, "Amel", 15);

    const student = await getStudent(client, "Amel");

    expect(student.name).toBe("Amel");
    expect(Number(student.grade)).toBe(15);
});

test("un étudiant inexistant retourne null", async () => {
    const student = await getStudent(client, "Inconnu");

    expect(student).toBeNull();
});

test("calculer la moyenne des notes", async () => {
    await addStudent(client, "Amel", 10);
    await addStudent(client, "Karim", 14);

    expect(Number(await averageGrade(client))).toBe(12);
});