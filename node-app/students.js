const { Client } = require("pg");

async function addStudent(client, name, grade) {
    await client.query(
        "INSERT INTO students (name, grade) VALUES ($1, $2)",
        [name, grade]
    );
}

async function getStudent(client, name) {
    const result = await client.query(
        "SELECT id, name, grade FROM students WHERE name = $1",
        [name]
    );
    return result.rows[0] || null;
}

async function averageGrade(client) {
    const result = await client.query(
        "SELECT AVG(grade) AS average FROM students"
    );
    return result.rows[0].average;
}

module.exports = { addStudent, getStudent, averageGrade };