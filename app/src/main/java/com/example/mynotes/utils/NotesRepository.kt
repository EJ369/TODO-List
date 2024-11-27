package com.example.mynotes.utils

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotesRepository(private val notesDao: NotesDao) {
    val allNotes: LiveData<List<NotesEntity>> = notesDao.getAllNotes()

    fun insertNote(note: NotesEntity): Long {
        return notesDao.insertNote(note)
    }

    fun updateNote(note: NotesEntity): Int {
        return notesDao.updateNote(note)
    }

    suspend fun updateStatus(id: Int, status: String) {
        withContext(Dispatchers.IO) {
            notesDao.updateStatus(id, status)
        }
    }

    suspend fun deleteNote(note: NotesEntity) {
        withContext(Dispatchers.IO) {
            notesDao.deleteNote(note)
        }
    }

    fun searchNotes(searchQuery: String): List<NotesEntity> {
        return notesDao.searchNotes(searchQuery)
    }

    fun getNotesList(): List<NotesEntity> {
        return notesDao.getNotes()
    }
    fun notesByStatus(status: String): List<NotesEntity> {
        return notesDao.notesByStatus(status)
    }
}