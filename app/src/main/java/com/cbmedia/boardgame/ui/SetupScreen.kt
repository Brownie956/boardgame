package com.cbmedia.boardgame.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cbmedia.boardgame.data.models.BoardSpace
import com.cbmedia.boardgame.data.models.Category
import com.cbmedia.boardgame.data.models.Task
import com.cbmedia.boardgame.viewmodel.SetupViewModel

@Composable
fun SetupScreen(viewModel: SetupViewModel = hiltViewModel()) {
    val categories by viewModel.categories.observeAsState(emptyList<Category>())
    val tasks by viewModel.tasks.observeAsState(emptyList<Task>())
    val boardSpaces by viewModel.boardSpaces.observeAsState(emptyList<BoardSpace>())

    var boardSize by remember { mutableStateOf("20") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Board Setup", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        // Board size input
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = boardSize,
                onValueChange = { boardSize = it },
                label = { Text("Number of Spaces") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                viewModel.setBoardSize(boardSize.toIntOrNull() ?: 20)
            }, modifier = Modifier.padding(start = 8.dp)) {
                Text("Set")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Category Editor
        CategoryEditor(categories = categories, onAdd = viewModel::addCategory)

        Spacer(Modifier.height(16.dp))

        // Task Editor
        TaskEditor(categories = categories, tasks = tasks, onAdd = viewModel::addTask)

        Spacer(Modifier.height(16.dp))

        // BoardSpace Editor
        BoardEditor(
            boardSpaces = boardSpaces,
            categories = categories,
            tasks = tasks,
            onAssignCategory = viewModel::assignCategoryToSpace,
            onAssignTask = viewModel::assignTaskToSpace
        )
    }
}
