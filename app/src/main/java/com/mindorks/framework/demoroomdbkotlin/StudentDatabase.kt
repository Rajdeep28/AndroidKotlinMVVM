package com.mindorks.framework.demoroomdbkotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mindorks.framework.demoroomdbkotlin.db.Student
import com.mindorks.framework.demoroomdbkotlin.db.StudentDao

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase: RoomDatabase() {

    abstract val studentDao: StudentDao

    companion object{
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_data_databse"
                    ).build()
                }
                return instance
            }
        }
    }
}