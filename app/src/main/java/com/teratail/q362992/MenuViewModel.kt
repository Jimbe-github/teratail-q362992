package com.teratail.q362992

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.TextView
import androidx.lifecycle.*

class MenuData(val name: String, val price: Int)

class MenuViewModel : ViewModel() {
  private val selectedMenu = MutableLiveData<MenuData?>(null)
  fun getSelectedMenu(): LiveData<MenuData?> {
    return selectedMenu
  }

  fun setSelectedMenu(value: MenuData?) {
    selectedMenu.value = value
  }

  private val menuList = MutableLiveData<List<MenuData>?>(null)
  fun setMenuList(value: List<MenuData>?) {
    menuList.value = value
  }

  fun createMenuAdapter(owner:LifecycleOwner, context: Context): ListAdapter {
    var adapter = MenuAdapter(context);
    menuList.observe(owner, adapter)
    return adapter
  }
}

//メニューアダプタ
class MenuAdapter internal constructor(context: Context) : BaseAdapter(), Observer<List<MenuData>?> {
  private inner class ViewHolder(val text1: TextView, val text2: TextView)

  private val layoutInflater: LayoutInflater
  private var menuList: List<MenuData> = emptyList()

  init {
    layoutInflater = LayoutInflater.from(context)
  }

  override fun getCount(): Int {
    return menuList.size
  }

  override fun getItem(position: Int): MenuData {
    return menuList[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
    var view = convertView
    val vh: ViewHolder
    if (view == null) {
      view = layoutInflater.inflate(android.R.layout.simple_list_item_2, parent, false)
      vh = ViewHolder(
        view.findViewById(android.R.id.text1),
        view.findViewById(android.R.id.text2))
      view.tag = vh
    } else {
      vh = view.tag as ViewHolder
    }

    val menuData = getItem(position)
    vh.text1.text = menuData.name
    vh.text2.text = menuData.price.toString() + "円"

    return view
  }

  override fun onChanged(newMenuList: List<MenuData>?) {
    menuList = if(newMenuList == null) emptyList() else newMenuList
    notifyDataSetChanged()
  }
}