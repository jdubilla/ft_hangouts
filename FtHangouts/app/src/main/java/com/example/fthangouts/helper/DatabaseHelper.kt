package com.example.fthangouts.helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.fthangouts.model.User

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

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
                "$COLUMN_BIRTH_DATE INTEGER)"

        db?.execSQL(createUserTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "ftHangouts"
        private const val DATABASE_VERSION = 4
        private const val TABLE_NAME = "user"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRST_NAME = "first_name"
        private const val COLUMN_LAST_NAME = "last_name"
        private const val COLUMN_PHONE_NUMBER = "phone_number"
        private const val COLUMN_NOTE = "note"
        private const val COLUMN_BIRTH_DATE = "birth_date"
    }

    fun addUser(user: User) {
        val values = ContentValues().apply {
            put(COLUMN_FIRST_NAME, user.firstName)
            put(COLUMN_LAST_NAME, user.lastName)
            put(COLUMN_PHONE_NUMBER, user.phoneNumber)
            put(COLUMN_NOTE, user.note)
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
                        it.getLong(it.getColumnIndex(COLUMN_BIRTH_DATE)),
                    )
                    usersList.add(user)
                } while (it.moveToNext())
            }
        }
        return usersList
    }
}