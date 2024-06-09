package com.dangchph33497.fpoly.tutorkot104.Buoi1.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dangchph33497.fpoly.tutorkot104.Buoi1.Entity.SanPham
import com.dangchph33497.fpoly.tutorkot104.Buoi1.Entity.getListSanPham

@Composable
fun HomeScreen(){
    var list by remember {
        mutableStateOf(getListSanPham())
    }
    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        Text(text = "Quản Lý Sản Phẩm", style = MaterialTheme.typography.titleLarge)
        Button(onClick = {list = list.toMutableList().apply {
            add(SanPham(Math.random().toString(),9f,"Sản Phẩm 5","mô tả 5",true))
        }
        }) {
            Text(text = "Thêm Sản Phẩm")
        }
        LazyColumn {
            items(list){
                Row (
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clickable {
                            Toast
                                .makeText(context, it.toString(), Toast.LENGTH_SHORT)
                                .show()
                        },
                    horizontalArrangement = Arrangement.SpaceBetween){

                    Column {
                        ItemText(content = it.name)
                        ItemText(content = it.price.toString())
                    }
                    Column {
                        ItemText(content = it.description.toString())
                        ItemText(content = it.status.toString())
                    }
                    Column {
                        Icon(imageVector = Icons.Outlined.Edit, contentDescription = "",Modifier.clickable {

                        })

                        Icon(imageVector = Icons.Outlined.Delete, contentDescription = "",Modifier.clickable {
                            val temps = list.toMutableList()
                            temps.remove(it)
                            list = temps
                        })
                    }
                }
                Divider()
            }
        }
    }
}
@Composable
fun ItemText(content : String) {
    Text(text = content, style = MaterialTheme.typography.bodyLarge)
}