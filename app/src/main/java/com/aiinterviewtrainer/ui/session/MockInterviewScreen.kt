package com.aiinterviewtrainer.ui.session

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.aiinterviewtrainer.ui.navigation.Routes
import com.aiinterviewtrainer.ui.theme.AppBg
import com.aiinterviewtrainer.ui.theme.AppBorder
import com.aiinterviewtrainer.ui.theme.AppGreenLight
import com.aiinterviewtrainer.ui.theme.AppOrange
import com.aiinterviewtrainer.ui.theme.AppRed
import com.aiinterviewtrainer.ui.theme.AppSub
import com.aiinterviewtrainer.ui.theme.AppText
import com.aiinterviewtrainer.ui.theme.Blue100
import com.aiinterviewtrainer.ui.theme.Blue700
import com.aiinterviewtrainer.ui.theme.Blue800
import com.aiinterviewtrainer.util.FaceAnalyzer
import com.aiinterviewtrainer.util.FaceStatus
import com.aiinterviewtrainer.util.SpeechManager
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MockInterviewScreen(
    navController: NavController,
    viewModel: SessionViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val speechManager = remember { SpeechManager(context) }
    val speechState by speechManager.state.collectAsState()

    val permissionsState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO
        )
    )

    var faceStatus by remember { mutableStateOf(FaceStatus.FACE_DETECTED) }
    var noFaceCount by remember { mutableIntStateOf(0) }
    var malpracticeCount by remember { mutableIntStateOf(0) }
    var showMalpracticeDialog by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableIntStateOf(90) }
    var autoSubmitted by remember { mutableStateOf(false) }

    val currentQuestion = uiState.questions.getOrNull(uiState.currentQuestionIndex)
    val displayAnswer = (speechState.finalText + " " + speechState.partialText).trim()

    LaunchedEffect(uiState.currentQuestionIndex) {
        timeLeft = 90
        autoSubmitted = false
        viewModel.clearFaceWarning()
    }

    LaunchedEffect(timeLeft, uiState.currentQuestionIndex) {
        if (timeLeft > 0) {
            delay(1000)
            timeLeft--
        } else if (!autoSubmitted && !uiState.isLoading) {
            autoSubmitted = true
            val answer = displayAnswer.ifEmpty { "(No answer provided)" }
            speechManager.stopListening()
            speechManager.clearText()
            viewModel.submitAnswer(answer)
            navController.navigate(Routes.AI_FEEDBACK)
        }
    }

    LaunchedEffect(faceStatus) {
        when (faceStatus) {
            FaceStatus.NO_FACE -> {
                noFaceCount++
                if (noFaceCount >= 3) {
                    viewModel.reportFaceWarning("⚠️ Face not visible!")
                }
            }

            FaceStatus.MULTIPLE_FACES -> {
                malpracticeCount++
                viewModel.reportFaceWarning("🚨 Multiple faces detected! ($malpracticeCount)")
                if (malpracticeCount >= 3) {
                    showMalpracticeDialog = true
                }
            }

            FaceStatus.FACE_DETECTED -> {
                noFaceCount = 0
                viewModel.clearFaceWarning()
            }

            else -> Unit
        }
    }

    LaunchedEffect(uiState.sessionComplete) {
        if (uiState.sessionComplete) {
            navController.navigate(Routes.SESSION_SUMMARY) {
                popUpTo(Routes.SESSION_SETUP) { inclusive = false }
            }
        }
    }

    DisposableEffect(Unit) {
        permissionsState.launchMultiplePermissionRequest()
        onDispose { speechManager.destroy() }
    }

    if (showMalpracticeDialog) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(
                    text = "Interview Terminated",
                    fontWeight = FontWeight.Bold,
                    color = AppRed
                )
            },
            text = {
                Text(
                    text = "3 malpractice violations were detected. This mock interview has been flagged and terminated."
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showMalpracticeDialog = false
                        navController.navigate(Routes.DASHBOARD) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AppRed)
                ) {
                    Text("End Interview")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBg)
    ) {
        Surface(
            color = Blue800,
            shadowElevation = 4.dp
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .height(56.dp)
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Videocam,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Mock Interview",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                if (timeLeft < 30) AppRed.copy(alpha = 0.30f)
                                else Color.White.copy(alpha = 0.20f)
                            )
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = "${timeLeft / 60}:${(timeLeft % 60).toString().padStart(2, '0')}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    if (malpracticeCount > 0) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(AppRed)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "⚠ $malpracticeCount",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                LinearProgressIndicator(
                    progress = {
                        if (uiState.questions.isEmpty()) 0f
                        else (uiState.currentQuestionIndex + 1f) / uiState.questions.size
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp),
                    color = Blue100,
                    trackColor = Color.White.copy(alpha = 0.20f)
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Question ${uiState.currentQuestionIndex + 1} of ${uiState.questions.size}",
                    color = AppSub,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )

                if (uiState.faceWarning != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(AppRed.copy(alpha = 0.10f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = uiState.faceWarning ?: "",
                            color = AppRed,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            if (permissionsState.allPermissionsGranted) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(3.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        AndroidView(
                            factory = { ctx ->
                                PreviewView(ctx).apply {
                                    scaleType = PreviewView.ScaleType.FILL_CENTER
                                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        ) { previewView ->
                            val executor = ContextCompat.getMainExecutor(context)
                            ProcessCameraProvider.getInstance(context).also { future ->
                                future.addListener({
                                    val provider = future.get()
                                    val preview = Preview.Builder().build().also {
                                        it.setSurfaceProvider(previewView.surfaceProvider)
                                    }
                                    val analysis = ImageAnalysis.Builder()
                                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                        .build()
                                        .also {
                                            it.setAnalyzer(
                                                executor,
                                                FaceAnalyzer { status, _ -> faceStatus = status }
                                            )
                                        }
                                    try {
                                        provider.unbindAll()
                                        provider.bindToLifecycle(
                                            lifecycleOwner,
                                            CameraSelector.DEFAULT_FRONT_CAMERA,
                                            preview,
                                            analysis
                                        )
                                    } catch (_: Exception) {
                                    }
                                }, executor)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(10.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(AppRed)
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "● LIVE",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(10.dp)
                                .clip(RoundedCornerShape(999.dp))
                                .background(Color.Black.copy(alpha = 0.35f))
                                .padding(horizontal = 8.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (faceStatus == FaceStatus.FACE_DETECTED) AppGreenLight else AppRed
                                    )
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = when (faceStatus) {
                                    FaceStatus.FACE_DETECTED -> "Face OK"
                                    FaceStatus.MULTIPLE_FACES -> "Multiple Faces"
                                    FaceStatus.NO_FACE -> "No Face"
                                    else -> "Checking"
                                },
                                color = Color.White,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (currentQuestion != null) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            SmallBadge(
                                text = currentQuestion.category,
                                background = Blue100,
                                contentColor = Blue800
                            )
                            SmallBadge(
                                text = currentQuestion.difficulty,
                                background = AppBorder,
                                contentColor = AppSub
                            )
                        }

                        Text(
                            text = currentQuestion.text,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AppText,
                            lineHeight = 25.sp
                        )

                        if (!currentQuestion.tips.isNullOrEmpty()) {
                            HorizontalDivider(color = AppBorder)
                            Row(
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = "💡 ",
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = currentQuestion.tips,
                                    fontSize = 12.sp,
                                    color = AppSub,
                                    lineHeight = 18.sp
                                )
                            }
                        }
                    } else {
                        Text(
                            text = if (uiState.isLoading) "Generating question..." else "No question available",
                            fontSize = 15.sp,
                            color = AppSub
                        )
                    }
                }
            }

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = null,
                            tint = if (speechState.isListening) AppRed else Blue800,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (speechState.isListening) "Recording in progress" else "Voice Response",
                            fontWeight = FontWeight.SemiBold,
                            color = AppText,
                            fontSize = 14.sp
                        )
                    }

                    if (speechState.isListening) {
                        VoiceWaveform()
                    }

                    if (displayAnswer.isNotEmpty()) {
                        Text(
                            text = displayAnswer,
                            color = AppText,
                            fontSize = 14.sp,
                            lineHeight = 20.sp
                        )
                    } else {
                        Text(
                            text = "Tap the microphone below and answer the question clearly.",
                            color = AppSub,
                            fontSize = 13.sp,
                            lineHeight = 19.sp
                        )
                    }
                }
            }

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MonitoringRow(
                        icon = Icons.Default.Face,
                        label = "Face Monitoring",
                        value = when (faceStatus) {
                            FaceStatus.FACE_DETECTED -> "Stable"
                            FaceStatus.NO_FACE -> "Not visible"
                            FaceStatus.MULTIPLE_FACES -> "Suspicious"
                            else -> "Checking"
                        },
                        valueColor = when (faceStatus) {
                            FaceStatus.FACE_DETECTED -> AppGreenLight
                            FaceStatus.NO_FACE -> AppOrange
                            FaceStatus.MULTIPLE_FACES -> AppRed
                            else -> AppSub
                        }
                    )

                    MonitoringRow(
                        icon = Icons.Default.Warning,
                        label = "Malpractice Count",
                        value = malpracticeCount.toString(),
                        valueColor = if (malpracticeCount == 0) AppGreenLight else AppRed
                    )

                    MonitoringRow(
                        icon = Icons.Default.Schedule,
                        label = "Time Remaining",
                        value = "${timeLeft}s",
                        valueColor = if (timeLeft < 30) AppRed else Blue800
                    )
                }
            }
        }

        Surface(
            color = Color.White,
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        if (speechState.isListening) {
                            speechManager.stopListening()
                        } else {
                            speechManager.startListening()
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(
                        1.5.dp,
                        if (speechState.isListening) AppRed else Blue800
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (speechState.isListening) {
                            AppRed.copy(alpha = 0.08f)
                        } else {
                            Color.Transparent
                        }
                    )
                ) {
                    Icon(
                        imageVector = if (speechState.isListening) Icons.Default.Stop else Icons.Default.Mic,
                        contentDescription = null,
                        tint = if (speechState.isListening) AppRed else Blue800,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (speechState.isListening) "Stop" else "Voice",
                        color = if (speechState.isListening) AppRed else Blue800,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Button(
                    onClick = {
                        speechManager.stopListening()
                        val answer = displayAnswer.ifEmpty { "(No answer provided)" }
                        speechManager.clearText()
                        viewModel.submitAnswer(answer)
                        navController.navigate(Routes.AI_FEEDBACK)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue800),
                    enabled = !uiState.isLoading && !autoSubmitted
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Submit",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun SmallBadge(
    text: String,
    background: Color,
    contentColor: Color
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(background)
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            color = contentColor,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun MonitoringRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    valueColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Blue700,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            color = AppText,
            fontSize = 13.sp
        )
        Text(
            text = value,
            color = valueColor,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun VoiceWaveform() {
    val infiniteTransition = rememberInfiniteTransition(label = "wave")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(22) { i ->
            val height by infiniteTransition.animateFloat(
                initialValue = 6f,
                targetValue = (12 + (i % 6) * 7).toFloat(),
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 250 + i * 40,
                        easing = FastOutSlowInEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "bar$i"
            )

            Box(
                modifier = Modifier
                    .width(8.dp)
                    .height(height.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Blue700.copy(alpha = 0.8f))
            )
        }
    }
}