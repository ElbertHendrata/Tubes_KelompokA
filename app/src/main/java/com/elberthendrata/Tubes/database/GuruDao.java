package com.elberthendrata.Tubes.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.elberthendrata.Tubes.model.Guru;

import java.util.List;

@Dao
public interface GuruDao {

    @Query("SELECT * FROM Guru")
    List<Guru> getAll();

    @Insert
    void insert(Guru guru);

    @Update
    void update(Guru guru);

    @Delete
    void delete(Guru guru);

}


