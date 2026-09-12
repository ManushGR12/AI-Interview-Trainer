package com.aiinterviewtrainer.data.local

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileStorageManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val profilePhotosDir get() = File(context.filesDir, "profile_photos").also { it.mkdirs() }
    private val resumesDir get() = File(context.filesDir, "resumes").also { it.mkdirs() }

    fun saveProfilePhoto(bitmap: Bitmap, userId: String): String {
        val file = File(profilePhotosDir, "photo_$userId.jpg")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        }
        return file.absolutePath
    }

    fun saveResume(uri: Uri, userId: String): String {
        val file = File(resumesDir, "resume_$userId.pdf")
        context.contentResolver.openInputStream(uri)?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }
        return file.absolutePath
    }

    fun getProfilePhotoFile(userId: String): File? {
        val file = File(profilePhotosDir, "photo_$userId.jpg")
        return if (file.exists()) file else null
    }

    fun getResumeFile(userId: String): File? {
        val file = File(resumesDir, "resume_$userId.pdf")
        return if (file.exists()) file else null
    }

    fun getResumeBitmap(userId: String): Bitmap? {
        val file = getResumeFile(userId) ?: return null
        return try {
            val pfd = android.os.ParcelFileDescriptor.open(
                file,
                android.os.ParcelFileDescriptor.MODE_READ_ONLY
            )
            val renderer = android.graphics.pdf.PdfRenderer(pfd)
            val page = renderer.openPage(0)
            val bitmap = Bitmap.createBitmap(page.width * 2, page.height * 2, Bitmap.Config.ARGB_8888)
            val canvas = android.graphics.Canvas(bitmap)
            canvas.drawColor(android.graphics.Color.WHITE)
            page.render(bitmap, null, null, android.graphics.pdf.PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            page.close()
            renderer.close()
            pfd.close()
            bitmap
        } catch (e: Exception) { null }
    }
}