package com.teratail.q362992

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MenuThanksFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_menu_thanks, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val mvm = ViewModelProvider(requireActivity()).get(MenuViewModel::class.java)

    val nameText = view.findViewById<TextView>(R.id.menuName)
    val priceText = view.findViewById<TextView>(R.id.menuPrice)

    mvm.getSelectedMenu().observe(viewLifecycleOwner, Observer { menuData: MenuData? ->
      if (menuData == null) {
        nameText.text = "--"
        priceText.text = "--"
      } else {
        nameText.text = menuData.name
        priceText.text = menuData.price.toString() + "円"
      }
    })
  }
}