package com.aiinterviewtrainer.ui.resume

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aiinterviewtrainer.ui.components.GradientButton
import com.aiinterviewtrainer.ui.navigation.Routes
import com.aiinterviewtrainer.ui.theme.*

@Composable
fun AIAnalysisScreen(
    navController: NavController,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        if (!uiState.analysisComplete && !uiState.isLoading) {
            viewModel.analyzeResume()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

            // Header
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
                    Icon(Icons.Default.AutoAwesome, null, tint = AppGold, modifier = Modifier.size(40.dp))
                    Text("AI Analysis", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Gemini is analyzing your resume", fontSize = 13.sp, color = Color(0xBBBBDEFB))
                }
            }

            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

                // Loading state
                if (uiState.isLoading) {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp).fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            CircularProgressIndicator(color = Blue800)
                            Text("Analyzing your resume with Gemini AI...", fontSize = 14.sp, color = AppSub)
                            Text("This may take a few seconds", fontSize = 12.sp, color = AppSub)
                        }
                    }
                }

                // Analysis results
                AnimatedVisibility(visible = uiState.analysisComplete) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                        // Summary
                        if (uiState.summary.isNotEmpty()) {
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(2.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.Person, null, tint = Blue800, modifier = Modifier.size(18.dp))
                                        Spacer(Modifier.width(8.dp))
                                        Text("Profile Summary", fontWeight = FontWeight.Bold, color = AppText)
                                    }
                                    Spacer(Modifier.height(8.dp))
                                    Text(uiState.summary, color = AppSub, fontSize = 13.sp, lineHeight = 20.sp)
                                    if (uiState.experienceLevel.isNotEmpty()) {
                                        Spacer(Modifier.height(8.dp))
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(Blue50)
                                                .padding(horizontal = 10.dp, vertical = 4.dp)
                                        ) {
                                            Text(
                                                "Level: ${uiState.experienceLevel.replaceFirstChar { it.uppercase() }}",
                                                color = Blue800,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Medium
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // Skills
                        if (uiState.extractedSkills.isNotEmpty()) {
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(2.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        "DETECTED SKILLS",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AppSub,
                                        letterSpacing = 0.8.sp
                                    )
                                    Spacer(Modifier.height(12.dp))
                                    // Skills chips in rows
                                    val chunked = uiState.extractedSkills.chunked(3)
                                    chunked.forEach { row ->
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            modifier = Modifier.padding(bottom = 8.dp)
                                        ) {
                                            row.forEach { skill ->
                                                Box(
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(20.dp))
                                                        .background(Blue100)
                                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                                ) {
                                                    Text(skill, color = Blue800, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // Suggested roles
                        if (uiState.suggestedRoles.isNotEmpty()) {
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(2.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        "AI-SUGGESTED ROLES",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AppSub,
                                        letterSpacing = 0.8.sp
                                    )
                                    Spacer(Modifier.height(12.dp))
                                    uiState.suggestedRoles.forEachIndexed { index, role ->
                                        Row(
                                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(28.dp)
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .background(Blue100),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    "${index + 1}",
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Blue800
                                                )
                                            }
                                            Text(
                                                role,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = AppText,
                                                modifier = Modifier.weight(1f)
                                            )
                                            Icon(Icons.Default.Check, null, tint = AppGreenLight, modifier = Modifier.size(16.dp))
                                        }
                                        if (index < uiState.suggestedRoles.size - 1) {
                                            HorizontalDivider(color = AppBorder)
                                        }
                                    }
                                }
                            }
                        }

                        GradientButton(
                            text = "Continue to Role Selection",
                            onClick = {
                                navController.navigate(Routes.ROLE_SELECTION) {
                                    popUpTo(Routes.AI_ANALYSIS) { inclusive = true }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            icon = Icons.AutoMirrored.Filled.ArrowForward
                        )
                    }
                }

                // Error state
                uiState.error?.let { errorMsg ->
                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3F3))) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Analysis failed: $errorMsg", color = AppRed, fontSize = 13.sp)
                            GradientButton(
                                text = "Try Again",
                                onClick = { viewModel.analyzeResume() },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    TextButton(
                        onClick = { navController.navigate(Routes.ROLE_SELECTION) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Skip to Role Selection", color = AppSub)
                    }
                }
            }
        }
    }
}