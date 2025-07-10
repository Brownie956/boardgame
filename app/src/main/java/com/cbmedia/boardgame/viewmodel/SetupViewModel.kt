package com.cbmedia.boardgame.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cbmedia.boardgame.data.dao.BoardSpaceDao
import com.cbmedia.boardgame.data.dao.CategoryDao
import com.cbmedia.boardgame.data.dao.TaskDao
import com.cbmedia.boardgame.data.models.BoardSpace
import com.cbmedia.boardgame.data.models.Category
import com.cbmedia.boardgame.data.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val categoryDao: CategoryDao,
    private val taskDao: TaskDao,
    private val boardSpaceDao: BoardSpaceDao
) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>(emptyList())
    val categories: LiveData<List<Category>> = _categories

    private val _tasks = MutableLiveData<List<Task>>(emptyList())
    val tasks: LiveData<List<Task>> = _tasks

    private val _boardSpaces = MutableLiveData<List<BoardSpace>>(emptyList())
    val boardSpaces: LiveData<List<BoardSpace>> = _boardSpaces

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _categories.value = categoryDao.getAll()
            _tasks.value = taskDao.getAll()
            _boardSpaces.value = boardSpaceDao.getAll()
        }
    }

    fun addCategory(name: String) {
        viewModelScope.launch {
            val category = Category(name = name)
            categoryDao.insert(category)
            _categories.value = categoryDao.getAll()
        }
    }

    fun addTask(description: String, categoryId: Int) {
        viewModelScope.launch {
            val task = Task(description = description, categoryId = categoryId)
            taskDao.insert(task)
            _tasks.value = taskDao.getAll()
        }
    }

    fun setBoardSize(size: Int) {
        viewModelScope.launch {
            val current = boardSpaceDao.getAll()
            if (current.size != size) {
                boardSpaceDao.clearAll()
                val newSpaces = List(size) { BoardSpace(position = it) }
                boardSpaceDao.insertAll(newSpaces)
                _boardSpaces.value = boardSpaceDao.getAll()
            }
        }
    }

    fun assignCategoryToSpace(spacePosition: Int, categoryId: Int?) {
        viewModelScope.launch {
            val existing = boardSpaceDao.getByPosition(spacePosition)
            if (existing != null) {
                boardSpaceDao.insert(existing.copy(categoryId = categoryId))
                _boardSpaces.value = boardSpaceDao.getAll()
            }
        }
    }

    fun assignTaskToSpace(spacePosition: Int, taskId: Int?) {
        viewModelScope.launch {
            val existing = boardSpaceDao.getByPosition(spacePosition)
            if (existing != null) {
                boardSpaceDao.insert(existing.copy(specificTaskId = taskId))
                _boardSpaces.value = boardSpaceDao.getAll()
            }
        }
    }
}
