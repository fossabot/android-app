package org.strongswan.android.data

import android.app.Activity

import android.content.Context
import android.content.SharedPreferences

class UserDataSharedPrefs(private var preferences: SharedPreferences) : UserData {

    companion object {
        private val PREFS_FILE_KEY: String = "GARDION_PREFS_FILE_KEY"
        private val VPN_USERNAME_KEY: String = "VPN_USERNAME_KEY"
        private val VPN_PASSWORD_KEY: String = "VPN_PASSWORD_KEY"
        private val VPN_SERVER_KEY: String = "VPN_SERVER_KEY"

        fun getInstance(activity: Activity): UserDataSharedPrefs {
            return UserDataSharedPrefs(activity.getSharedPreferences(PREFS_FILE_KEY, Context.MODE_PRIVATE))
        }
    }

    override fun saveVpnUsername(vpnUsername: String) {
        preferences.edit().putString(VPN_USERNAME_KEY, vpnUsername).apply()
    }

    override fun getVpnUsername(): String {
        return preferences.getString(VPN_USERNAME_KEY, "")
    }

    override fun saveVpnPassword(vpnPassword: String) {
        preferences.edit().putString(VPN_PASSWORD_KEY, vpnPassword).apply()
    }

    override fun getVpnPassword(): String {
        return preferences.getString(VPN_PASSWORD_KEY, "")
    }

    override fun saveVpnServer(vpnServer: String) {
        preferences.edit().putString(VPN_SERVER_KEY, vpnServer).apply()
    }

    override fun getVpnServer(): String {
        return preferences.getString(VPN_SERVER_KEY, "")
    }
}