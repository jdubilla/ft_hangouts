package com.example.fthangouts.helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.fthangouts.model.User

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private var database: SQLiteDatabase? = null

    init {
        database = writableDatabase
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createUserTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_FIRST_NAME TEXT, " +
                "$COLUMN_LAST_NAME TEXT," +
                "$COLUMN_PHONE_NUMBER TEXT," +
                "$COLUMN_NOTE TEXT," +
                "$COLUMN_PHOTO TEXT," +
                "$COLUMN_BIRTH_DATE INTEGER)"

        db?.execSQL(createUserTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "ftHangouts"
        private const val DATABASE_VERSION = 12
        private const val TABLE_NAME = "user"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRST_NAME = "first_name"
        private const val COLUMN_LAST_NAME = "last_name"
        private const val COLUMN_PHONE_NUMBER = "phone_number"
        private const val COLUMN_NOTE = "note"
        private const val COLUMN_PHOTO = "photo"
        private const val COLUMN_BIRTH_DATE = "birth_date"
    }

    fun addUser(user: User) {
        val values = ContentValues().apply {
            put(COLUMN_FIRST_NAME, user.firstName)
            put(COLUMN_LAST_NAME, user.lastName)
            put(COLUMN_PHONE_NUMBER, user.phoneNumber)
            put(COLUMN_NOTE, user.note)
            put(COLUMN_PHOTO, user.photo)
            put(COLUMN_BIRTH_DATE, user.birthDate)
        }
        database?.insert(TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun getAllUsers(): List<User> {
        val usersList = mutableListOf<User>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = database?.rawQuery(selectQuery, null)

        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val user = User(
                        it.getString(it.getColumnIndex(COLUMN_FIRST_NAME)),
                        it.getString(it.getColumnIndex(COLUMN_LAST_NAME)),
                        it.getString(it.getColumnIndex(COLUMN_PHONE_NUMBER)),
                        it.getString(it.getColumnIndex(COLUMN_NOTE)),
                        it.getString(it.getColumnIndex(COLUMN_PHOTO)),
                        it.getLong(it.getColumnIndex(COLUMN_BIRTH_DATE)),
                        it.getInt(it.getColumnIndex(COLUMN_ID)),
                    )
                    usersList.add(user)
                } while (it.moveToNext())
            }
        }
        return usersList
    }

    @SuppressLint("Range", "Recycle")
    fun getUserById(id: Int): User? {
        var user: User? = null
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE id = ?"
        val cursor = database?.rawQuery(selectQuery, arrayOf(id.toString()))

        if (cursor != null && cursor.moveToFirst()) {
            user = User(
                cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)),
                cursor.getString(cursor.getColumnIndex(COLUMN_NOTE)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PHOTO)),
                cursor.getLong(cursor.getColumnIndex(COLUMN_BIRTH_DATE)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
            )
        }
        return user
    }

    fun updateUser(user: User) {
//        println(user)
        val values = ContentValues().apply {
            put(COLUMN_FIRST_NAME, user.firstName)
            put(COLUMN_LAST_NAME, user.lastName)
            put(COLUMN_PHONE_NUMBER, user.phoneNumber)
            put(COLUMN_NOTE, user.note)
            put(COLUMN_PHOTO, user.photo)
            put(COLUMN_BIRTH_DATE, user.birthDate)
        }

        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(user.id.toString())

        database?.update(TABLE_NAME, values, whereClause, whereArgs)
    }

    fun deleteUser(id: Int) {
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(id.toString())

        database?.delete(TABLE_NAME, whereClause, whereArgs)
    }

    fun phoneNumberIsRegister(phoneNumber: String): Boolean {
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE phone_number = ?"
        val cursor = database?.rawQuery(selectQuery, arrayOf(phoneNumber))

        if (cursor != null && cursor.moveToFirst()) {
            return true
        }
        return false
    }
}
