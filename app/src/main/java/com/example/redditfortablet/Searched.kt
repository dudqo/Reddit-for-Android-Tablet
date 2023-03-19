package com.example.redditfortablet

import android.content.Context
import androidx.core.location.LocationRequestCompat
import androidx.room.*

@Entity(tableName = "searched_table")
data class Searched(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "searched")
    val searched: String

)

@Dao
interface SearchedDao {

    @Query("SELECT * FROM searched_table")
    fun getSearched(): List<Searched>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(searched: Searched)

    @Query("DELETE FROM searched_table")
    suspend fun deleteAll()
}

@Database(entities = arrayOf(Searched::class), version = 1, exportSchema = false)
public abstract class SearchedRoomDatabase : RoomDatabase() {

    abstract fun SearchedDao(): SearchedDao

    companion object {

        @Volatile
        private var INSTANCE: SearchedRoomDatabase? = null

        fun getDatabase(context: Context): SearchedRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SearchedRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

