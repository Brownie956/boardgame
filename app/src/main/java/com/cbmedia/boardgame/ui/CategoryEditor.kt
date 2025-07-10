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
import androidx.compose.ui.text.font.FontWeight
import com.cbmedia.boardgame.data.models.Category

@Composable
fun CategoryEditor(categories: List<Category>, onAdd: (String) -> Unit) {
    var newCategory by remember { mutableStateOf("") }

    Column {
        Text("Categories", fontWeight = FontWeight.Bold)
        Row {
            OutlinedTextField(
                value = newCategory,
                onValueChange = { newCategory = it },
                label = { Text("New Category") }
            )
            Button(onClick = {
                if (newCategory.isNotBlank()) {
                    onAdd(newCategory)
                    newCategory = ""
                }
            }) { Text("Add") }
        }

        categories.forEach { Text("- ${it.name}") }
    }
}
