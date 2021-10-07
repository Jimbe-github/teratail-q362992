package com.teratail.q362992

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class MenuListFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_menu_list, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val mvm = ViewModelProvider(requireActivity()).get(MenuViewModel::class.java)

    val lvMenu = view.findViewById<ListView>(R.id.lvMenu)
    lvMenu.adapter = mvm.createMenuAdapter(viewLifecycleOwner, requireContext());

    // リスナの登録。
    lvMenu.onItemClickListener = OnItemClickListener { parent: AdapterView<*>, view1: View?, position: Int, id: Long ->
      val item = parent.getItemAtPosition(position) as MenuData
      mvm.setSelectedMenu(item)
    }
  }
}