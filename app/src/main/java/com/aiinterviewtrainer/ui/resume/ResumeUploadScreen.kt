package com.aiinterviewtrainer.ui.resume

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aiinterviewtrainer.ui.components.GradientButton
import com.aiinterviewtrainer.ui.components.LoadingOverlay
import com.aiinterviewtrainer.ui.navigation.Routes
import com.aiinterviewtrainer.ui.theme.AppBg
import com.aiinterviewtrainer.ui.theme.AppBorder
import com.aiinterviewtrainer.ui.theme.AppGreen
import com.aiinterviewtrainer.ui.theme.AppGreenLight
import com.aiinterviewtrainer.ui.theme.AppRed
import com.aiinterviewtrainer.ui.theme.AppSub
import com.aiinterviewtrainer.ui.theme.Blue700
import com.aiinterviewtrainer.ui.theme.Blue800
import com.aiinterviewtrainer.ui.theme.Blue900

@Composable
fun ResumeUploadScreen(
    navController: NavController,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    var selectedUri by remember { mutableStateOf<Uri?>(null) }
    var fileName by remember { mutableStateOf("") }

    val fileLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            selectedUri = it
            fileName = getFileName(context, it)
        }
    }

    LaunchedEffect(uiState.resumeSaved) {
        if (uiState.resumeSaved) {
            navController.navigate(Routes.AI_ANALYSIS) {
                popUpTo(Routes.RESUME_UPLOAD) { inclusive = true }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.linearGradient(listOf(Blue900, Blue800)))
                    .padding(top = 48.dp, bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )

                    Text(
                        text = "Upload Resume",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "PDF format • AI analysis continues automatically",
                        fontSize = 13.sp,
                        color = Color(0xFFBBDEFB)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            width = 2.dp,
                            color = if (selectedUri != null) AppGreenLight else AppBorder,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(if (selectedUri != null) Color(0xFFF1FFF1) else AppBg)
                        .clickable { fileLauncher.launch("application/pdf") }
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = if (selectedUri != null) {
                                Icons.Default.CheckCircle
                            } else {
                                Icons.Default.UploadFile
                            },
                            contentDescription = null,
                            tint = if (selectedUri != null) AppGreenLight else Blue700,
                            modifier = Modifier.size(48.dp)
                        )

                        if (selectedUri != null) {
                            Text(
                                text = fileName,
                                fontWeight = FontWeight.SemiBold,
                                color = AppGreen,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "Tap to change",
                                fontSize = 12.sp,
                                color = AppSub
                            )
                        } else {
                            Text(
                                text = "Tap to select PDF",
                                fontWeight = FontWeight.SemiBold,
                                color = Blue800,
                                fontSize = 15.sp
                            )
                            Text(
                                text = "Your resume will be uploaded and analyzed before role selection.",
                                fontSize = 12.sp,
                                color = AppSub,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = null,
                            tint = Blue700,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Your resume is securely processed for AI-based role suggestions and interview personalization.",
                            fontSize = 12.sp,
                            color = AppSub
                        )
                    }
                }

                uiState.error?.let { error ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3F3)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = error,
                            modifier = Modifier.padding(12.dp),
                            color = AppRed,
                            fontSize = 13.sp
                        )
                    }
                }

                GradientButton(
                    text = "Analyse Resume with AI",
                    onClick = {
                        selectedUri?.let { uri ->
                            val extractedText = extractPdfText(context, uri)
                            viewModel.saveResume(uri, extractedText)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = selectedUri != null && !uiState.isLoading,
                    icon = Icons.Default.AutoAwesome
                )

                TextButton(
                    onClick = {
                        navController.navigate(Routes.ROLE_SELECTION) {
                            popUpTo(Routes.RESUME_UPLOAD) { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Skip — Choose role manually",
                        color = AppSub
                    )
                }
            }
        }

        if (uiState.isLoading) {
            LoadingOverlay("Uploading and analysing resume...")
        }
    }
}

private fun getFileName(context: Context, uri: Uri): String {
    var name = "resume.pdf"
    context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
        if (cursor.moveToFirst() && nameIndex >= 0) {
            name = cursor.getString(nameIndex)
        }
    }
    return name
}

private fun extractPdfText(context: Context, uri: Uri): String {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bytes = inputStream?.readBytes() ?: return ""
        inputStream.close()
        "Resume uploaded for AI analysis. File size: ${bytes.size} bytes. Extract candidate strengths, probable role fit, technical areas, and interview focus areas."
    } catch (e: Exception) {
        "Resume uploaded for AI analysis. Please infer probable role fit and interview focus areas."
    }
}