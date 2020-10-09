package com.elberthendrata.Tubes.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.elberthendrata.Tubes.model.Guru;
import com.elberthendrata.Tubes.model.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class AppDatabase2 extends RoomDatabase {
    public abstract StudentDao studentDao();
}



