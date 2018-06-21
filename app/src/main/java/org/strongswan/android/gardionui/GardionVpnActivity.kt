package org.strongswan.android.gardionui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_gardion_vpn.*
import org.strongswan.android.R
import org.strongswan.android.data.UserDataSharedPrefs
import org.strongswan.android.utils.KeyStoreManager


class GardionVpnActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gardion_vpn)
        val vpnServer = "vpna.gardion.net"
        val vpnUsername = "joe"
        val vpnPassword = "nlkbl_kZGI8iuzfi7"
        KeyStoreManager.generateKey()
        info_screen_encrypt.setOnClickListener { encryptAndSaveUserData(vpnServer, vpnUsername, vpnPassword) }
        info_screen_log_button.setOnClickListener { startVPN() }
    }

    private fun encryptAndSaveUserData(serverName: String, username: String, password: String) {
        info_screen_text.text = "Please wait while we encrypting your credentials"
        info_screen_progress_bar.visibility = View.VISIBLE
        val encryptedServerName: String = KeyStoreManager.encryptData(serverName)
        val encryptedUsername: String = KeyStoreManager.encryptData(username)
        val encryptedPassword: String = KeyStoreManager.encryptData(password)
        val userData: UserDataSharedPrefs = UserDataSharedPrefs.getInstance(this)
        userData.saveVpnServer(encryptedServerName)
        userData.saveVpnUsername(encryptedUsername)
        userData.saveVpnPassword(encryptedPassword)
        info_screen_progress_bar.visibility = View.VISIBLE
        info_screen_text.text = "Data encrypted. Please proceed"
    }

    private fun startVPN() {

    }
}
