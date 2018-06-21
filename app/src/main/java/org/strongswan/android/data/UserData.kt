package org.strongswan.android.data

interface UserData {
    fun saveVpnUsername(vpnUsername: String)
    fun getVpnUsername(): String
    fun saveVpnPassword(vpnPassword: String)
    fun getVpnPassword(): String
    fun saveVpnServer(vpnServer: String)
    fun getVpnServer(): String
}