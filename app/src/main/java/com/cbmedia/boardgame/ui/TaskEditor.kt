package com.cbmedia.boardgame.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.cbmedia.boardgame.data.models.Category
import com.cbmedia.boardgame.data.models.Task
import com.cbmedia.boardgame.ui.components.DropdownMenuBox

@Composable
fun TaskEditor(
    categories: List<Category>,
    tasks: List<Task>,
    onAdd: (String, Int) -> Unit
) {
    var newTask by remember { mutableStateOf("") }
    var selectedCategoryId by remember { mutableStateOf<Int?>(null) }

    Column {
        Text("Tasks", fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newTask,
                onValueChange = { newTask = it },
                label = { Text("New Task") },
                modifier = Modifier.weight(1f)
            )

            DropdownMenuBox(
                items = categories,
                selectedId = selectedCategoryId,
                onSelected = { selectedCategoryId = it },
                label = "Select Category",
                getName = { it.name },
                getId = { it.id }
            )

            Button(onClick = {
                if (newTask.isNotBlank() && selectedCategoryId != null) {
                    onAdd(newTask, selectedCategoryId!!)
                    newTask = ""
                }
            }) {
                Text("Add")
            }
        }

        tasks.forEach { task ->
            val categoryName = categories.find { it.id == task.categoryId }?.name ?: "Unknown"
            Text("- ${task.description} [${categoryName}]")
        }
    }
}
