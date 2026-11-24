package hu.petrik.filmgyujtemeny

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Movie(
    val id: Int,
    val title: String,
    val genre: String,
    val rating: Int
)

class StudentDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "movies.db", null, 1) {

    //adatbázistábla létrehozása
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
                CREATE TABLE movies (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                genre TEXT NOT NULL,
                rating INTEGER NOT NULL
                )
            """.trimIndent()
        )
    }

    //adatbázis verzió váltás történik
    override fun onUpgrade(
        db: SQLiteDatabase, oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS movies")
        onCreate(db)
    }


    //CREATE
    fun addFilm(title: String,genre: String ,rating: Int) {
        val values = ContentValues().apply {
            put("title", title)
            put("genre", genre)
            put("rating", rating)
        }
        writableDatabase.insert(
            "movies", null,
            values
        )
    }

    //READ
    fun getAllFilms(): List<Movie> {

        val films = mutableListOf<Movie>()
        val cursor = readableDatabase.rawQuery(
            "SELECT id, title, genre, rating FROM movies ORDER by ID DESC", null
        )
        cursor.use {
            while (it.moveToNext()) {
                val film = Movie(
                    id = it.getInt(it.getColumnIndexOrThrow("id")),
                    title = it.getString(it.getColumnIndexOrThrow("title")),
                    genre = it.getString(it.getColumnIndexOrThrow("genre")),
                    rating = it.getInt(it.getColumnIndexOrThrow("rating"))
                )
                films.add(film)
            }
        }
        return films
    }

    //UPDATE
    fun updateFilm(id: Int, title: String,genre: String ,rating: Int){
        val values = ContentValues().apply {
            put("title", title)
            put("genre", genre)
            put("rating", rating)
        }
        writableDatabase.update(
            "movies",
            values, "id = ?",arrayOf(id.toString())
        )
    }

    //DELETE
    fun deleteFilm(id: Int){
        writableDatabase.delete(
            "movies", "id = ?",
            arrayOf(id.toString())
        )
    }
}