package com.aiinterviewtrainer.util

import android.graphics.Bitmap
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

enum class FaceStatus {
    NO_FACE,
    FACE_DETECTED,
    MULTIPLE_FACES,
    FACE_TOO_SMALL
}

class FaceAnalyzer(
    private val onFaceStatusChanged: (FaceStatus, Int) -> Unit
) : ImageAnalysis.Analyzer {

    private val detector = FaceDetection.getClient(
        FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setMinFaceSize(0.15f)
            .build()
    )

    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: run { imageProxy.close(); return }
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        detector.process(image)
            .addOnSuccessListener { faces ->
                val status = when {
                    faces.isEmpty() -> FaceStatus.NO_FACE
                    faces.size > 1 -> FaceStatus.MULTIPLE_FACES
                    else -> FaceStatus.FACE_DETECTED
                }
                onFaceStatusChanged(status, faces.size)
            }
            .addOnFailureListener {
                onFaceStatusChanged(FaceStatus.NO_FACE, 0)
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }
}
