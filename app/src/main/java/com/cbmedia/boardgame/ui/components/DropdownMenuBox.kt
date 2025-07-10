package com.cbmedia.boardgame.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun <T> DropdownMenuBox(
    items: List<T>,
    selectedId: Int?,
    onSelected: (Int) -> Unit,
    label: String = "Select",
    getName: (T) -> String = { it.toString() },
    getId: (T) -> Int
) {
    var expanded by remember { mutableStateOf(false) }

    val selectedText = items.find { getId(it) == selectedId }?.let(getName) ?: label

    Box {
        TextButton(onClick = { expanded = true }) {
            Text(
                selectedText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach {
                DropdownMenuItem(
                    text = { Text(getName(it)) },
                    onClick = {
                        onSelected(getId(it))
                        expanded = false
                    }
                )
            }
        }
    }
}
