package com.BFCAI.encryptionapp.android.Utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import com.BFCAI.encryptionapp.Domain.Model.SharedFilesItemModel
import com.BFCAI.encryptionapp.Domain.Model.UserFilesItemModel
import com.BFCAI.encryptionapp.Presentation.SharedFilesScreen.SharedFilesEvent
import com.BFCAI.encryptionapp.Presentation.UserFilesScreen.UserFilesEvent
import java.io.*
import java.net.URL


class DownloadFiles {
    companion object {
        fun DownloadFiles(itemModel: UserFilesItemModel, context: Context, event: (UserFilesEvent) -> Unit,
        ): File? {
            try {
                val cacheFile = File(context.cacheDir, itemModel.file.name)
                cacheFile.delete()
            } catch (e: Exception) {
            }
            try {
                val u = URL(itemModel.file.url)
                val conn = u.openConnection()
                val contentLength = conn.contentLength
                val stream = DataInputStream(u.openStream())
                val buffer = ByteArray(contentLength)
                stream.readFully(buffer)
                stream.close()
                //val PATH = (Environment.getExternalStorageDirectory().toString() + "/${itemModel.file.name}")
                Log.v("LOG_TAG", "PATH: ${context.cacheDir}")
                val file = File.createTempFile(itemModel.file.name, null, context.cacheDir)
                val fos = DataOutputStream(FileOutputStream(file))
                fos.write(buffer)
                fos.flush()
                fos.close()

                val path: Uri = FileProvider.getUriForFile(context!!,"com.BFCAI.encryptionapp.android.encoProvider", file)
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.setDataAndType(path, itemModel.file_type)
                event(UserFilesEvent.cardIsClicked(false))
                startActivity(context,intent,null)

                return File(context.cacheDir, itemModel.file.name)
            } catch (e: FileNotFoundException) {
                Log.v("LOG_TAGERR", e.message.toString())
                return null
            } catch (e: IOException) {
                Log.v("LOG_TAGERR", e.message.toString())
                return null
            }
        }
        fun DownloadSentFiles(itemModel: SharedFilesItemModel, context: Context, event: (SharedFilesEvent) -> Unit,
        ): File? {
            try {
                val cacheFile = File(context.cacheDir, itemModel.file.name)
                cacheFile.delete()
            } catch (e: Exception) {
            }
            try {
                val u = URL(itemModel.file.url)
                val conn = u.openConnection()
                val contentLength = conn.contentLength
                val stream = DataInputStream(u.openStream())
                val buffer = ByteArray(contentLength)
                stream.readFully(buffer)
                stream.close()
                //val PATH = (Environment.getExternalStorageDirectory().toString() + "/${itemModel.file.name}")
                Log.v("LOG_TAG", "PATH: ${context.cacheDir}")
                val file = File.createTempFile(itemModel.file.name, null, context.cacheDir)
                val fos = DataOutputStream(FileOutputStream(file))
                fos.write(buffer)
                fos.flush()
                fos.close()

                val path: Uri = FileProvider.getUriForFile(context!!,"com.BFCAI.encryptionapp.android.encoProvider", file)
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.setDataAndType(path, itemModel.file_type)
                event(SharedFilesEvent.cardIsClicked(false))
                startActivity(context,intent,null)

                return File(context.cacheDir, itemModel.file.name)
            } catch (e: FileNotFoundException) {
                Log.v("LOG_TAGERR", e.message.toString())
                return null
            } catch (e: IOException) {
                Log.v("LOG_TAGERR", e.message.toString())
                return null
            }
        }
    }
}