package com.teratail.q362992

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.util.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val mvm = ViewModelProvider(this).get(MenuViewModel::class.java)

    val menuList: MutableList<MenuData> = ArrayList()
    menuList.add(MenuData("から揚げ定食", 800))
    menuList.add(MenuData("ハンバーグ定食", 850))
    // 以下データ登録の繰り返しのため省略。
    mvm.setMenuList(menuList)

    val singleMode = findViewById<View?>(R.id.fragmentContainer) != null
    if (singleMode) {
      mvm.getSelectedMenu().observe(this, { value: MenuData? ->
        val transaction = supportFragmentManager.beginTransaction()
        if (value == null) {
          transaction.replace(R.id.fragmentContainer, MenuListFragment())
        } else {
          transaction.replace(R.id.fragmentContainer, MenuThanksFragment()).addToBackStack(null)
        }
        transaction.commit()
      })
    }
  }
}