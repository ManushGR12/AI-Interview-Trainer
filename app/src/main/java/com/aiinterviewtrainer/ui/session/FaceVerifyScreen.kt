package com.aiinterviewtrainer.ui.session

import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aiinterviewtrainer.ui.navigation.Routes
import com.aiinterviewtrainer.ui.theme.*
import com.aiinterviewtrainer.util.FaceAnalyzer
import com.aiinterviewtrainer.util.FaceStatus
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.delay
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FaceVerifyScreen(
    navController: NavController,
    viewModel: SessionViewModel = hiltViewModel()
) {
    val cameraPermission = rememberPermissionState(android.Manifest.permission.CAMERA)
    var faceStatus by remember { mutableStateOf(FaceStatus.NO_FACE) }
    var isVerified by remember { mutableStateOf(false) }
    var verifyCountdown by remember { mutableStateOf(3) }

    LaunchedEffect(faceStatus) {
        if (faceStatus == FaceStatus.FACE_DETECTED && !isVerified) {
            for (i in 3 downTo 1) {
                verifyCountdown = i
                delay(1000)
                if (faceStatus != FaceStatus.FACE_DETECTED) break
            }
            if (faceStatus == FaceStatus.FACE_DETECTED) {
                isVerified = true
            }
        }
    }

    LaunchedEffect(isVerified) {
        if (isVerified) {
            delay(800)
            viewModel.generateQuestions()
            navController.navigate(Routes.MOCK_INTERVIEW) {
                popUpTo(Routes.FACE_VERIFY) { inclusive = true }
            }
        }
    }

    LaunchedEffect(Unit) {
        if (!cameraPermission.status.isGranted) {
            cameraPermission.launchPermissionRequest()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(40.dp))

        Text(
            "Face Verification",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            "Mock Interview Security Check",
            fontSize = 13.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(30.dp))

        // Camera circle
        Box(
            modifier = Modifier
                .size(260.dp)
                .clip(CircleShape)
                .border(
                    4.dp,
                    when (faceStatus) {
                        FaceStatus.FACE_DETECTED -> AppGreenLight
                        FaceStatus.MULTIPLE_FACES -> AppRed
                        else -> Color.Gray
                    },
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (cameraPermission.status.isGranted) {
                CameraPreviewForVerify { faceStatus = it }
            } else {
                Icon(Icons.Default.NoPhotography, null, tint = Color.White)
            }
        }

        Spacer(Modifier.height(24.dp))

        val statusText = when {
            isVerified -> "Identity Verified"
            faceStatus == FaceStatus.FACE_DETECTED -> "Hold still... $verifyCountdown"
            faceStatus == FaceStatus.MULTIPLE_FACES -> "Multiple faces detected"
            else -> "Position your face inside the circle"
        }

        val statusColor = when {
            isVerified -> AppGreenLight
            faceStatus == FaceStatus.FACE_DETECTED -> AppGold
            faceStatus == FaceStatus.MULTIPLE_FACES -> AppRed
            else -> Color.White
        }

        Text(statusText, color = statusColor, fontWeight = FontWeight.SemiBold)

        Spacer(Modifier.height(30.dp))

        // Instructions Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f))
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                VerifyInstruction(Icons.Default.LightMode, "Ensure good lighting")
                VerifyInstruction(Icons.Default.Face, "Look directly at the camera")
                VerifyInstruction(Icons.Default.Shield, "Face monitoring during mock interview")
                VerifyInstruction(Icons.Default.Warning, "Face change will trigger alerts")
            }
        }
    }
}

@Composable
private fun VerifyInstruction(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = Blue100, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(10.dp))
        Text(text, fontSize = 13.sp, color = Color(0xCCFFFFFF))
    }
}

@Composable
private fun CameraPreviewForVerify(onFaceStatus: (FaceStatus) -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val executor = remember { Executors.newSingleThreadExecutor() }

    AndroidView(
        factory = { ctx ->
            PreviewView(ctx).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
            }
        }
    ) { previewView ->
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            val provider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            val analyzer = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        FaceAnalyzer { status, _ -> onFaceStatus(status) }
                    )
                }

            try {
                provider.unbindAll()
                provider.bindToLifecycle(
                    lifecycleOwner,
                    CameraSelector.DEFAULT_FRONT_CAMERA,
                    preview,
                    analyzer
                )
            } catch (_: Exception) {}
        }, ContextCompat.getMainExecutor(context))
    }
}