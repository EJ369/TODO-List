package com.example.mynotes.utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    @Query("SELECT * FROM NotesTable")
    fun getAllNotes(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM NotesTable WHERE status = 'Pending'")
    fun getAllPendingNotes(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM NotesTable WHERE status = 'Pending'")
    fun getNotes(): List<NotesEntity>

    @Insert
    fun insertNote(note: NotesEntity): Long

    @Update
    fun updateNote(note: NotesEntity): Int

    @Delete
    fun deleteNote(note: NotesEntity)

    @Query("UPDATE NotesTable SET status = :status WHERE id = :id")
    fun updateStatus(id: Int, status: String)

    @Query("SELECT * FROM NotesTable WHERE title LIKE '%' || :searchQuery || '%' OR message LIKE '%' || :searchQuery || '%'")
    fun searchNotes(searchQuery: String): List<NotesEntity>

    @Query("SELECT * FROM NotesTable WHERE status = :status")
    fun notesByStatus(status: String): List<NotesEntity>
}