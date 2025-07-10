package com.cbmedia.boardgame.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cbmedia.boardgame.data.models.BoardSpace
import com.cbmedia.boardgame.data.models.Category
import com.cbmedia.boardgame.data.models.Task
import com.cbmedia.boardgame.ui.components.DropdownMenuBox

@Composable
fun BoardEditor(
    boardSpaces: List<BoardSpace>,
    categories: List<Category>,
    tasks: List<Task>,
    onAssignCategory: (Int, Int) -> Unit,
    onAssignTask: (Int, Int) -> Unit
) {
    Text("Board Spaces", fontWeight = FontWeight.Bold)

    LazyColumn {
        items(boardSpaces) { space ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Space ${space.position}", modifier = Modifier.width(100.dp))

                DropdownMenuBox(
                    items = categories,
                    selectedId = space.categoryId,
                    onSelected = { onAssignCategory(space.position, it) },
                    label = "Category",
                    getName = { it.name },
                    getId = { it.id }
                )

                DropdownMenuBox(
                    items = tasks,
                    selectedId = space.specificTaskId,
                    onSelected = { onAssignTask(space.position, it) },
                    label = "Task",
                    getName = { it.description },
                    getId = { it.id }
                )
            }
        }
    }
}
