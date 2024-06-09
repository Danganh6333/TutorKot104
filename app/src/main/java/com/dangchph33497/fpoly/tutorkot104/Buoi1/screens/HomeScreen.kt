package com.dangchph33497.fpoly.tutorkot104.Buoi1.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.dangchph33497.fpoly.tutorkot104.Buoi1.Entity.SanPham
import com.dangchph33497.fpoly.tutorkot104.Buoi1.Entity.getListSanPham

@Composable
fun HomeScreen() {
    var id by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var list by remember {
        mutableStateOf(getListSanPham())
    }
    val showDetailDialog = remember {
        mutableStateOf(false)
    }
    val showAddDialog = remember {
        mutableStateOf(false)
    }

    val showDeleteDialog = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Quản Lý Sản Phẩm", style = MaterialTheme.typography.titleLarge)
        Button(onClick = {
//            list = list.toMutableList().apply {
//                add(SanPham(Math.random().toString(), 9f, "Sản Phẩm 5", "mô tả 5", true))
//            }
            showAddDialog.value = true
        }) {
            Text(text = "Thêm Sản Phẩm")
        }
        LazyColumn {
            items(list) {
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clickable {
                            id = it.id
                            price = it.price.toString()
                            name = it.name
                            description = it.description.toString()
                            status = it.status.toString()
                            Toast
                                .makeText(context, it.toString(), Toast.LENGTH_SHORT)
                                .show()

                            showDetailDialog.value = true
                        },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {
                        ItemText(content = it.name)
                        ItemText(content = it.price.toString())
                    }
                    Column {
                        ItemText(content = it.description.toString())
                        ItemText(content = it.status.toString())
                    }
                    Column(
                        Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(imageVector = Icons.Outlined.Edit, contentDescription = "",
                            Modifier
                                .width(15.dp)
                                .clickable {

                                })
                        Icon(imageVector = Icons.Outlined.Delete, contentDescription = "",
                            Modifier
                                .width(15.dp)
                                .clickable {
                                    showDeleteDialog.value = true
                                })
                    }
                }
                Divider()
                if (showDetailDialog.value) {
                    Dialog(onDismissRequest = { showDetailDialog.value = false }) {
                        Card(
                            shape = MaterialTheme.shapes.medium,
                            elevation = CardDefaults.cardElevation(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                contentColor = MaterialTheme.colorScheme.onSurface
                            ),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "Chi Tiết Sản Phẩm",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Divider()
                                ItemText(content = "Tên Sản Phẩm: " + name)
                                ItemText(content = "Giá Sản Phẩm: " + price)
                                ItemText(content = "Mô Tả Sản Phẩm :" + description)
                                ItemText(content = "Trạng thái Sản Phẩm :" + status)
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Button(onClick = {
                                        showDetailDialog.value = false
                                        id = ""
                                        name = ""
                                        description = ""
                                        status = ""
                                    }) {
                                        Text("Đóng")
                                    }
                                }
                            }
                        }
                    }

                }
                if (showDeleteDialog.value) {
                    AlertDialog(
                        onDismissRequest = { showDeleteDialog.value = false },
                        dismissButton = {
                            Button(
                                onClick = {
                                    showDeleteDialog.value = false
                                }) {
                                Text(text = "Không")
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    val temps = list.toMutableList()
                                    temps.remove(it)
                                    list = temps
                                    showDeleteDialog.value = false
                                }) {
                                Text(text = "Có")
                            }
                        },

                        title = {
                            ItemText(content = "Bạn Có muốn xóa không?")
                        }
                    )
                }
                if (showAddDialog.value) {
                    AddProductDialog(
                        onDismiss = { showAddDialog.value = false },
                        onAdd = { newProduct ->
                            list = list.toMutableList().apply {
                                add(newProduct)
                            }
                            showAddDialog.value = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AddProductDialog(onDismiss: () -> Unit, onAdd: (SanPham) -> Unit) {
    var id by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var status by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Thêm Sản Phẩm", style = MaterialTheme.typography.titleLarge)
                Divider()
                ItemTextField(label = "Tên Sản Phẩm", value = name, onValueChange = { name = it })
                ItemTextField(label = "Giá Sản Phẩm", value = price, onValueChange = { price = it })
                ItemTextField(label = "Mô Tả Sản Phẩm", value = description, onValueChange = { description = it })
                ItemSwitch(label = "Trạng thái Sản Phẩm", value = status, onValueChange = { status = it })
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = onDismiss) {
                        Text("Hủy")
                    }
                    Button(onClick = {
                        when {
                            name.isEmpty() -> errorMessage = "Tên Sản Phẩm không được để trống"
                            price.isEmpty() -> errorMessage = "Giá Sản Phẩm không được để trống"
                            description.isEmpty() -> errorMessage = "Mô Tả Sản Phẩm không được để trống"
                            else -> {
                                val newProduct = SanPham(
                                    id = Math.random().toString(),
                                    price = price.toFloatOrNull() ?: 0f,
                                    name = name,
                                    description = description,
                                    status = status
                                )
                                onAdd(newProduct)
                            }
                        }
                    }) {
                        Text("Thêm")
                    }
                }
            }
        }
    }
}
@Composable
fun ItemTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) }
    )
}

@Composable
fun ItemSwitch(label: String, value: Boolean, onValueChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Switch(
            checked = value,
            onCheckedChange = onValueChange
        )
    }
}
@Composable
fun ItemText(content: String) {
    Text(text = content, style = MaterialTheme.typography.bodyLarge)
}