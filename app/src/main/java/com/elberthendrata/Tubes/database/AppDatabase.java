package com.elberthendrata.Tubes.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.elberthendrata.Tubes.model.Guru;

@Database(entities = {Guru.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GuruDao guruDao();
}



