package org.strongswan.android.utils

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.nio.charset.Charset
import java.security.Key
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec

class KeyStoreManager {
    companion object {
        private val GARDION_KEY_ALIAS: String = "Gardion_key"
        private val KEY_STORE_TYPE: String = "AndroidKeyStore"
        val keyStore: KeyStore = KeyStore.getInstance(KEY_STORE_TYPE).apply { load(null) }
        val cipher: Cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7)

        fun getSecterKey(): Key? {
            return keyStore.getKey(GARDION_KEY_ALIAS, null)
        }

        fun generateKey() {
            if (!keyStore.containsAlias(GARDION_KEY_ALIAS)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val keyGenerator: KeyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEY_STORE_TYPE)
                    keyGenerator
                            .init(KeyGenParameterSpec.Builder(GARDION_KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                                    .setRandomizedEncryptionRequired(false)
                                    .build())
                } else {

                }
            }
        }

        fun encryptData(data: String): String {
            cipher.init(Cipher.ENCRYPT_MODE, getSecterKey(), GCMParameterSpec(128, cipher.iv))
            val encodedBytes: ByteArray? = cipher.doFinal(data.toByteArray(Charset.defaultCharset()))
            return Base64.encodeToString(encodedBytes, Base64.DEFAULT)
        }

        fun decryptData(encrypted: String): String {
            cipher.init(Cipher.DECRYPT_MODE, getSecterKey(), GCMParameterSpec(128, cipher.iv))
            return cipher.doFinal(encrypted.toByteArray(Charset.defaultCharset())).toString()
        }
    }
}