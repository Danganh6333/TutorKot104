package com.dangchph33497.fpoly.tutorkot104.Buoi1.Entity

data class SanPham(var id:String , var price : Float, var name : String ,var description : String?,var status : Boolean ) {

}
fun getListSanPham() : MutableList<SanPham>{
    val list = mutableListOf<SanPham>()
    list.add(SanPham("1",10f,"Sản Phẩm 1","",true))
    list.add(SanPham("2",15f,"Sản Phẩm 2","",false))
    list.add(SanPham("3",20f,"Sản Phẩm 3","",true))
    list.add(SanPham("4",23f,"Sản Phẩm 4","",true))
    return list
}