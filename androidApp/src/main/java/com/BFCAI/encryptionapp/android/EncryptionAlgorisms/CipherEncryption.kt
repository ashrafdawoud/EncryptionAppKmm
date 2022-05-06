package com.BFCAI.encryptionapp.android.EncryptionAlgorisms

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

fun ByteArray.DetectEncryptionAlgorism(EncType:String):ByteArray{
    when(EncType){
        "AES (Advanced Encryption System)"->{
           return this.AEScipherEncrypt()
        }
        "DES (Data Encryption System)"->{
            return this.DEScipherEncrypt()
        }
        "BLOWFISH"->{
            return  this.BLOWFISHcipherEncrypt()
        }
        "DESede"->{
            return this.DESedecipherEncrypt()
        }else ->{
            return this.AEScipherEncrypt()
        }
    }
}
fun ByteArray.DetectDecryptionAlgorism(EncType:String):ByteArray{
    when(EncType){
        "AES (Advanced Encryption System)"->{
            return this.AEScipherDencrypt()
        }
        "DES (Data Encryption System)"->{
            return this.DEScipherDencrypt()
        }
        "BLOWFISH"->{
            return this.BLOWFISHcipherDencrypt()
        }
        "DESede"->{
            return this.DESedecipherDencrypt()
        }else ->{
        return this.AEScipherDencrypt()
    }
    }
}
@RequiresApi(Build.VERSION_CODES.M)
fun ByteArray.AEScipherEncrypt(): ByteArray {
    val cipher = Cipher.getInstance("AES/CTR/NoPadding")
    cipher.init(
        Cipher.ENCRYPT_MODE,
        SecretKeySpec("1234567891234567".toByteArray(), "AES"),
        IvParameterSpec(
            ByteArray(8)
        )
    )
    val ciphertext: ByteArray = cipher.doFinal(this)
    return ciphertext

}

fun ByteArray.AEScipherDencrypt(): ByteArray {
    val cipher = Cipher.getInstance("AES/CTR/NoPadding")
    cipher.init(
        Cipher.ENCRYPT_MODE,
        SecretKeySpec("1234567891234567".toByteArray(), "AES"),
        IvParameterSpec(
            ByteArray(8)
        )
    )

    val decrypted = cipher.doFinal(this)
    return decrypted
}@RequiresApi(Build.VERSION_CODES.M)
fun ByteArray.DEScipherEncrypt(): ByteArray {
    val cipher = Cipher.getInstance("DES/CTR/NoPadding","BC")
    cipher.init(
        Cipher.ENCRYPT_MODE,
        SecretKeySpec("12345679".toByteArray(), "DES"),
        IvParameterSpec(
            ByteArray(8)
        )
    )
    val ciphertext: ByteArray = cipher.doFinal(this)
    return ciphertext

}

fun ByteArray.DEScipherDencrypt(): ByteArray {
    val cipher = Cipher.getInstance("DES/CTR/NoPadding","BC")
    cipher.init(
        Cipher.ENCRYPT_MODE,
        SecretKeySpec("12345679".toByteArray(), "DES"),
        IvParameterSpec(
            ByteArray(8)
        )
    )

    val decrypted = cipher.doFinal(this)
    return decrypted
}
@RequiresApi(Build.VERSION_CODES.M)
fun ByteArray.BLOWFISHcipherEncrypt(): ByteArray {
    val cipher = Cipher.getInstance("BLOWFISH/CTR/NoPadding")
    cipher.init(
        Cipher.ENCRYPT_MODE,
        SecretKeySpec("12345679".toByteArray(), "BLOWFISH"),
        IvParameterSpec(
            ByteArray(8)
        )
    )
    val ciphertext: ByteArray = cipher.doFinal(this)
    return ciphertext

}

fun ByteArray.BLOWFISHcipherDencrypt(): ByteArray {
    val cipher = Cipher.getInstance("BLOWFISH/CTR/NoPadding")
    cipher.init(
        Cipher.ENCRYPT_MODE,
        SecretKeySpec("12345679".toByteArray(), "BLOWFISH"),
        IvParameterSpec(
            ByteArray(8)
        )
    )

    val decrypted = cipher.doFinal(this)
    return decrypted
}
@RequiresApi(Build.VERSION_CODES.M)
fun ByteArray.DESedecipherEncrypt(): ByteArray {
    val cipher = Cipher.getInstance("DESede/CTR/NoPadding")
    cipher.init(
        Cipher.ENCRYPT_MODE,
        SecretKeySpec("1234567896321456".toByteArray(), "DESede"),
        IvParameterSpec(
            ByteArray(8)
        )
    )
    val ciphertext: ByteArray = cipher.doFinal(this)
    return ciphertext

}

fun ByteArray.DESedecipherDencrypt(): ByteArray {
    val cipher = Cipher.getInstance("DESede/CTR/NoPadding")
    cipher.init(
        Cipher.ENCRYPT_MODE,
        SecretKeySpec("1234567896321456".toByteArray(), "DESede"),
        IvParameterSpec(
            ByteArray(8)
        )
    )

    val decrypted = cipher.doFinal(this)
    return decrypted
}
/**
 * AES (Advanced Encryption Standard) is a strong symmetric encryption algorithm. A secret key is used for the both encryption and decryption of data.
 * Only someone who has access to the same secret key can decrypt data. AES encryption provides strong protection to your data.
 * */

/**
 * Blowfish is an encryption technique designed by Bruce Schneier in 1993 as an alternative to DES Encryption Technique.
 * It is significantly faster than DES and provides a good encryption rate with no effective cryptanalysis technique found to date.
 * It is one of the first,
 * secure block cyphers not subject to any patents and hence freely available for anyone to use.

blockSize: 64-bits
keySize: 32-bits to 448-bits variable size
number of subkeys: 18 [P-array]
number of rounds: 16
number of substitution boxes: 4 [each having 512 entries of 32-bits each]

https://www.geeksforgeeks.org/blowfish-algorithm-with-examples/
 */

/**
 * Data encryption standard (DES) has been found vulnerable against very powerful attacks and therefore,
 * the popularity of DES has been found slightly on the decline.
DES is a block cipher and encrypts data in blocks of size of 64 bits each,
which means 64 bits of plain text goes as the input to DES, which produces 64 bits of ciphertext.
The same algorithm and key are used for encryption and decryption, with minor differences.
The key length is 56 bits. The basic idea is shown in the figure.

https://www.geeksforgeeks.org/data-encryption-standard-des-set-1/
 */
