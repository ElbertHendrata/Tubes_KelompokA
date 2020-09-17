package com.elberthendrata.persistentunguided9744.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.elberthendrata.persistentunguided9744.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}



