package co.edu.unicauca.aplimovil.tienda.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao
    companion object {
        @Volatile
        private var Instance : AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase{
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, AppDatabase::class.java, "user_database")
                    .build()
                    .also{ Instance = it}
            }
        }
    }
}