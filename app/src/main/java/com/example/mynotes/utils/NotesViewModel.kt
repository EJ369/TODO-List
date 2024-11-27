package com.example.mynotes.utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotesRepository
    val allNotes: LiveData<List<NotesEntity>>

    init {
        val notesDao = NotesDatabase.getInstance(application).notesDao()
        repository = NotesRepository(notesDao)
        allNotes = repository.allNotes
    }

    fun insertNote(note: NotesEntity): Long {
        return repository.insertNote(note)
    }

    fun updateNote(note: NotesEntity): Int {
        return repository.updateNote(note)
    }

    fun updateStatus(id: Int, status: String) {
        viewModelScope.launch { repository.updateStatus(id, status) }
    }

    fun deleteNote(note: NotesEntity) {
        viewModelScope.launch { repository.deleteNote(note) }
    }

    fun searchNotes(searchQuery: String): List<NotesEntity> {
        return repository.searchNotes(searchQuery)
    }

    fun getNotesList(): List<NotesEntity> {
        return repository.getNotesList()
    }

    fun notesByStatus(status: String): List<NotesEntity> {
        return repository.notesByStatus(status)
    }

}