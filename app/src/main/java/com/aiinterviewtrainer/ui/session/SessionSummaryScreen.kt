package com.aiinterviewtrainer.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aiinterviewtrainer.ui.navigation.Routes
import com.aiinterviewtrainer.ui.theme.AppBg
import com.aiinterviewtrainer.ui.theme.AppBorder
import com.aiinterviewtrainer.ui.theme.AppGold
import com.aiinterviewtrainer.ui.theme.AppGreenLight
import com.aiinterviewtrainer.ui.theme.AppOrange
import com.aiinterviewtrainer.ui.theme.AppRed
import com.aiinterviewtrainer.ui.theme.AppSub
import com.aiinterviewtrainer.ui.theme.AppText
import com.aiinterviewtrainer.ui.theme.Blue800
import com.aiinterviewtrainer.ui.theme.Blue900

@Composable
fun SessionSummaryScreen(
    navController: NavController,
    viewModel: SessionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val overallScore = viewModel.overallScore

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBg)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(Blue900, Blue800)))
                .statusBarsPadding()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(92.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$overallScore%",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Session Complete!",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Text(
                    text = uiState.selectedRole.ifBlank { "Interview Session" },
                    color = Color.White.copy(alpha = 0.75f),
                    fontSize = 14.sp
                )
            }
        }

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = "SESSION OVERVIEW",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppSub,
                        letterSpacing = 0.8.sp
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        SummaryMiniCard(
                            modifier = Modifier.weight(1f),
                            label = "Overall",
                            value = "$overallScore%"
                        )
                        SummaryMiniCard(
                            modifier = Modifier.weight(1f),
                            label = "Questions",
                            value = "${uiState.scores.size}"
                        )
                        SummaryMiniCard(
                            modifier = Modifier.weight(1f),
                            label = "Saved",
                            value = if (uiState.lastSavedSessionId.isNullOrBlank()) "No" else "Yes"
                        )
                    }

                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7FBFF))
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = AppGreenLight
                            )
                            Column {
                                Text(
                                    text = "Session data saved",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp,
                                    color = AppText
                                )
                                Text(
                                    text = "Questions, answers, scores, and feedback are available in session details.",
                                    fontSize = 12.sp,
                                    color = AppSub
                                )
                            }
                        }
                    }
                }
            }

            if (uiState.summaryText.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = AppGold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "AI Feedback Summary",
                                fontWeight = FontWeight.Bold,
                                color = AppText
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = uiState.summaryText,
                            color = AppSub,
                            fontSize = 14.sp,
                            lineHeight = 21.sp
                        )
                    }
                }
            }

            if (uiState.scores.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "QUESTION SCORES",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppSub,
                            letterSpacing = 0.8.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            uiState.scores.forEachIndexed { index, score ->
                                val barColor = scoreColorFor(score)

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Bottom,
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                ) {
                                    Text(
                                        text = "$score",
                                        color = barColor,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.height(2.dp))

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(0.7f)
                                            .height((score * 0.8f).dp)
                                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                            .background(barColor)
                                    )

                                    Spacer(modifier = Modifier.height(2.dp))

                                    Text(
                                        text = "${index + 1}",
                                        color = AppSub,
                                        fontSize = 9.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (uiState.questionAttempts.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "QUESTION SNAPSHOT",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppSub,
                            letterSpacing = 0.8.sp
                        )

                        uiState.questionAttempts.take(3).forEachIndexed { index, attempt ->
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "Q${index + 1}. ${attempt.questionText}",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = AppText
                                )
                                Text(
                                    text = "Score: ${attempt.score}%",
                                    fontSize = 12.sp,
                                    color = scoreColorFor(attempt.score)
                                )
                                Text(
                                    text = if (attempt.feedback.isNotBlank()) attempt.feedback else "No feedback available",
                                    fontSize = 12.sp,
                                    color = AppSub
                                )
                            }

                            if (index < uiState.questionAttempts.take(3).lastIndex) {
                                HorizontalDivider(color = AppBorder)
                            }
                        }
                    }
                }
            }

            if (uiState.scores.isNotEmpty()) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    listOf(
                        "Highest" to "${uiState.scores.max()}%",
                        "Lowest" to "${uiState.scores.min()}%",
                        "Questions" to "${uiState.scores.size}"
                    ).forEach { (label, value) ->
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Text(
                                    text = value,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = AppText
                                )
                                Text(
                                    text = label,
                                    color = AppSub,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
            }

            if (!uiState.lastSavedSessionId.isNullOrBlank()) {
                Button(
                    onClick = {
                        navController.navigate(Routes.sessionDetail(uiState.lastSavedSessionId!!))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue800)
                ) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "View Questions & Answers",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            OutlinedButton(
                onClick = {
                    navController.navigate(Routes.PROGRESS)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.BarChart,
                    contentDescription = null,
                    tint = AppText
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "View Full Progress",
                    color = AppText
                )
            }

            OutlinedButton(
                onClick = {
                    navController.navigate(Routes.DASHBOARD) {
                        popUpTo(Routes.SPLASH) { inclusive = false }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Dashboard,
                    contentDescription = null,
                    tint = AppText
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Back to Dashboard",
                    color = AppText
                )
            }
        }
    }
}

@Composable
private fun SummaryMiniCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7FAFF))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = AppText
            )
            Text(
                text = label,
                fontSize = 11.sp,
                color = AppSub
            )
        }
    }
}

private fun scoreColorFor(score: Int): Color {
    return when {
        score >= 80 -> AppGreenLight
        score >= 65 -> AppOrange
        else -> AppRed
    }
}