package com.elberthendrata.persistentguided9744.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.elberthendrata.persistentguided9744.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}



