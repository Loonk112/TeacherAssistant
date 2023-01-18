package com.example.teacherassist

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {

        val coursesQuery = ("CREATE TABLE $COURSES_TABLE_NAME ( $COURSES_ID_COL INTEGER PRIMARY KEY, $COURSES_NAME_COl TEXT, $COURSES_YEAR_COL  INTEGER)")
        db.execSQL(coursesQuery)
        val studentsQuery = ("CREATE TABLE $STUDENTS_TABLE_NAME ( $STUDENTS_ID_COL INTEGER PRIMARY KEY, $STUDENTS_NAME_COl TEXT, $STUDENTS_SURNAME_COl TEXT, $STUDENTS_GRADE_COL  INTEGER)")
        db.execSQL(studentsQuery)
        val connectionsQuery = ("CREATE TABLE $CONNECTIONS_TABLE_NAME ( $CONNECTIONS_ID_COL INTEGER PRIMARY KEY, $CONNECTIONS_COURSE_ID_COL  INTEGER, $CONNECTIONS_STUDENT_ID_COL  INTEGER, FOREIGN KEY ($CONNECTIONS_COURSE_ID_COL) REFERENCES ${COURSES_TABLE_NAME}($COURSES_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY ($CONNECTIONS_STUDENT_ID_COL) REFERENCES ${STUDENTS_TABLE_NAME}($STUDENTS_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE )")
        db.execSQL(connectionsQuery)
        val gradesQuery = ("CREATE TABLE $GRADES_TABLE_NAME ( $GRADES_ID_COL INTEGER PRIMARY KEY, $GRADES_CONNECTION_ID_COL  INTEGER, $GRADES_COMMENT_COL TEXT, $GRADES_GRADE_COL  INTEGER, FOREIGN KEY ($GRADES_CONNECTION_ID_COL) REFERENCES ${CONNECTIONS_TABLE_NAME}($CONNECTIONS_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE)")
        db.execSQL(gradesQuery)

    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        db?.execSQL("PRAGMA foreign_keys = ON;");
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $COURSES_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $STUDENTS_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $CONNECTIONS_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $GRADES_TABLE_NAME")
        onCreate(db)
    }

////////////////////////////////////////////////////////////////////////////////////////////////////COURSES
    fun addCourse(name : String, year : Int ){

        val values = ContentValues()

        values.put(COURSES_NAME_COl, name)
        values.put(COURSES_YEAR_COL, year)

        val db = this.writableDatabase

        db.insert(COURSES_TABLE_NAME, null, values)

        db.close()
    }
    fun deleteCourse(id: Int) {
        val db = this.writableDatabase
        db.delete(
            COURSES_TABLE_NAME,
            "$COURSES_ID_COL = ?",
            arrayOf(id.toString())
        )
    }
    fun getCourses(): ArrayList<CoursesData> {

        val db = this.readableDatabase
        val sql = "SELECT * FROM $COURSES_TABLE_NAME"
        val storeCourses = ArrayList<CoursesData>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(0).toInt()
                val name = cursor.getString(1)
                val year = cursor.getString(2).toInt()
                storeCourses.add(CoursesData(id, name, year))
            }
            while (cursor.moveToNext())
        }
        cursor.close()

        return storeCourses
    }
////////////////////////////////////////////////////////////////////////////////////////////////////STUDENTS
    fun addStudent(name : String, surname : String, grade : Int ){

        val values = ContentValues()

        values.put(STUDENTS_NAME_COl, name)
        values.put(STUDENTS_SURNAME_COl, surname)
        values.put(STUDENTS_GRADE_COL, grade)

        val db = this.writableDatabase

        db.insert(STUDENTS_TABLE_NAME, null, values)

        db.close()
    }
    fun deleteStudent(id: Int) {
        val db = this.writableDatabase
        db.delete(
            STUDENTS_TABLE_NAME,
            "$STUDENTS_ID_COL = ?",
            arrayOf(id.toString())
        )
    }
    fun getStudents(): ArrayList<StudentsData> {

        val db = this.readableDatabase
        val sql = "SELECT * FROM $STUDENTS_TABLE_NAME"
        val storeStudents = ArrayList<StudentsData>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(0).toInt()
                val name = cursor.getString(1)
                val surname = cursor.getString(2)
                val grade = cursor.getString(3).toInt()
                storeStudents.add(StudentsData(id, name, surname, grade))
            }
            while (cursor.moveToNext())
        }
        cursor.close()

        return storeStudents
    }
////////////////////////////////////////////////////////////////////////////////////////////////////CONNECTIONS
    fun addConnection(course_id : Int, student_id: Int ){

        val values = ContentValues()

        values.put(CONNECTIONS_COURSE_ID_COL, course_id)
        values.put(CONNECTIONS_STUDENT_ID_COL, student_id)

        val db = this.writableDatabase

        db.insert(CONNECTIONS_TABLE_NAME, null, values)

        db.close()
    }
    fun deleteConnection(id: Int) {
        val db = this.writableDatabase
        db.delete(
            CONNECTIONS_TABLE_NAME,
            "$CONNECTIONS_ID_COL = ?",
            arrayOf(id.toString())
        )
    }
    fun getConnectionsCourses(studentId: Int): ArrayList<CoursesData> {

        val db = this.readableDatabase
        val sql = "SELECT  ${CONNECTIONS_TABLE_NAME}.$CONNECTIONS_ID_COL, $COURSES_NAME_COl, $COURSES_YEAR_COL FROM $COURSES_TABLE_NAME INNER JOIN $CONNECTIONS_TABLE_NAME ON ${CONNECTIONS_TABLE_NAME}.$CONNECTIONS_COURSE_ID_COL = ${COURSES_TABLE_NAME}.$COURSES_ID_COL WHERE ${CONNECTIONS_TABLE_NAME}.$CONNECTIONS_STUDENT_ID_COL = $studentId"
        val storeConnections = ArrayList<CoursesData>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val connectionId = cursor.getString(0).toInt()
                val courseName = cursor.getString(1)
                val courseYear = cursor.getString(2).toInt()
                storeConnections.add(CoursesData(connectionId, courseName, courseYear))
            }
            while (cursor.moveToNext())
        }
        cursor.close()
        return storeConnections
    }
    fun getConnectionsStudents(courseId: Int): ArrayList<StudentsData> {

        val db = this.readableDatabase
        val sql = "SELECT ${CONNECTIONS_TABLE_NAME}.$CONNECTIONS_ID_COL, $STUDENTS_NAME_COl, $STUDENTS_SURNAME_COl, $STUDENTS_GRADE_COL FROM $STUDENTS_TABLE_NAME INNER JOIN $CONNECTIONS_TABLE_NAME ON ${CONNECTIONS_TABLE_NAME}.$CONNECTIONS_STUDENT_ID_COL = ${STUDENTS_TABLE_NAME}.$STUDENTS_ID_COL WHERE ${CONNECTIONS_TABLE_NAME}.$CONNECTIONS_COURSE_ID_COL = $courseId"
        val storeConnections = ArrayList<StudentsData>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val connectionId = cursor.getString(0).toInt()
                val studentName = cursor.getString(1)
                val studentSurname = cursor.getString(2)
                val studentGrade = cursor.getString(3).toInt()
                storeConnections.add(StudentsData(connectionId, studentName, studentSurname, studentGrade))
            }
            while (cursor.moveToNext())
        }
        cursor.close()
        return storeConnections
    }
    fun getUnconnectedCourses(studentId: Int): ArrayList<CoursesData> {

        val db = this.readableDatabase
        val sql = "SELECT ${COURSES_TABLE_NAME}.$COURSES_ID_COL, $COURSES_NAME_COl, $COURSES_YEAR_COL FROM $COURSES_TABLE_NAME EXCEPT SELECT ${COURSES_TABLE_NAME}.$COURSES_ID_COL, $COURSES_NAME_COl, $COURSES_YEAR_COL FROM $COURSES_TABLE_NAME INNER JOIN $CONNECTIONS_TABLE_NAME ON ${CONNECTIONS_TABLE_NAME}.$CONNECTIONS_COURSE_ID_COL = ${COURSES_TABLE_NAME}.$COURSES_ID_COL WHERE ${CONNECTIONS_TABLE_NAME}.$CONNECTIONS_STUDENT_ID_COL IS $studentId"
        val storeConnections = ArrayList<CoursesData>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val connectionId = cursor.getString(0).toInt()
                val courseName = cursor.getString(1)
                val courseYear = cursor.getString(2).toInt()
                storeConnections.add(CoursesData(connectionId, courseName, courseYear))
            }
            while (cursor.moveToNext())
        }
        cursor.close()
        return storeConnections
    }
    fun getUnconnectedStudents(courseId: Int): ArrayList<StudentsData> {

        val db = this.readableDatabase
        val sql = "SELECT ${STUDENTS_TABLE_NAME}.$STUDENTS_ID_COL, $STUDENTS_NAME_COl, $STUDENTS_SURNAME_COl, $STUDENTS_GRADE_COL FROM $STUDENTS_TABLE_NAME EXCEPT SELECT ${STUDENTS_TABLE_NAME}.$STUDENTS_ID_COL, $STUDENTS_NAME_COl, $STUDENTS_SURNAME_COl, $STUDENTS_GRADE_COL FROM $STUDENTS_TABLE_NAME INNER JOIN $CONNECTIONS_TABLE_NAME ON ${CONNECTIONS_TABLE_NAME}.$CONNECTIONS_STUDENT_ID_COL = ${STUDENTS_TABLE_NAME}.$STUDENTS_ID_COL WHERE ${CONNECTIONS_TABLE_NAME}.$CONNECTIONS_COURSE_ID_COL IS $courseId"
        val storeConnections = ArrayList<StudentsData>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val connectionId = cursor.getString(0).toInt()
                val studentName = cursor.getString(1)
                val studentSurname = cursor.getString(2)
                val studentGrade = cursor.getString(3).toInt()
                storeConnections.add(StudentsData(connectionId, studentName, studentSurname, studentGrade))
            }
            while (cursor.moveToNext())
        }
        cursor.close()
        return storeConnections
    }
////////////////////////////////////////////////////////////////////////////////////////////////////GRADES
fun addGrade(connection : Int, comment : String, grade : Int ){

    val values = ContentValues()

    values.put(GRADES_CONNECTION_ID_COL, connection)
    values.put(GRADES_COMMENT_COL, comment)
    values.put(GRADES_GRADE_COL, grade)

    val db = this.writableDatabase

    db.insert(GRADES_TABLE_NAME, null, values)

    db.close()
}
    fun deleteGrade(id: Int) {
        val db = this.writableDatabase
        db.delete(
            GRADES_TABLE_NAME,
            "$GRADES_ID_COL = ?",
            arrayOf(id.toString())
        )
    }
    fun getGrades(connection: Int): ArrayList<GradesData> {
        val db = this.readableDatabase
        val sql = "SELECT * FROM $GRADES_TABLE_NAME WHERE $GRADES_CONNECTION_ID_COL = $connection"
        val storeGrades = ArrayList<GradesData>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(0).toInt()
                val connection = cursor.getString(1).toInt()
                val comment = cursor.getString(2)
                val grade = cursor.getString(3).toInt()
                storeGrades.add(GradesData(id, connection, comment, grade))
            }
            while (cursor.moveToNext())
        }
        cursor.close()
        return storeGrades
    }


    companion object{

        private val DATABASE_NAME = "MAIN_DATABASE"

        private val DATABASE_VERSION = 6

        val COURSES_TABLE_NAME = "courses_table"
        val COURSES_ID_COL = "id"
        val COURSES_NAME_COl = "name"
        val COURSES_YEAR_COL = "year"

        val STUDENTS_TABLE_NAME = "students_table"
        val STUDENTS_ID_COL = "id"
        val STUDENTS_NAME_COl = "name"
        val STUDENTS_SURNAME_COl = "surname"
        val STUDENTS_GRADE_COL = "grade"

        val CONNECTIONS_TABLE_NAME = "connection_table"
        val CONNECTIONS_ID_COL = "id"
        val CONNECTIONS_COURSE_ID_COL = "course"
        val CONNECTIONS_STUDENT_ID_COL = "student"

        val GRADES_TABLE_NAME = "grades_table"
        val GRADES_ID_COL = "id"
        val GRADES_CONNECTION_ID_COL = "connection"
        val GRADES_COMMENT_COL = "comment"
        val GRADES_GRADE_COL = "grade"
    }
}