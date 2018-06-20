package org.strongswan.android.gardionui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_gardion_main.*
import org.strongswan.android.R

class GardionMainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gardion_main)
        var adapter = ArrayAdapter.createFromResource(this, R.array.authentication_mode, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        login_spinner.adapter = adapter
        login_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //do nothing
            }

            override fun onItemSelected(p0: AdapterView<*>?, item: View?, p2: Int, p3: Long) {
                when(item?.id) {
                    1 -> {
                        login_input_box.visibility = View.VISIBLE
                        login_auth_method_unavailable_text.visibility = View.INVISIBLE
                    }
                    2 -> {
                        login_input_box.visibility = View.INVISIBLE
                        login_auth_method_unavailable_text.visibility = View.VISIBLE
                    }
                }
            }

        }
    }
}
