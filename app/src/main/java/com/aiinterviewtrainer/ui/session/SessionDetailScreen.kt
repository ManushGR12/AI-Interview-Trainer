package com.aiinterviewtrainer.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aiinterviewtrainer.data.model.QuestionAttempt
import com.aiinterviewtrainer.data.model.Session
import com.aiinterviewtrainer.ui.components.ScoreChip
import com.aiinterviewtrainer.ui.components.SectionHeader
import com.aiinterviewtrainer.ui.theme.AppBg
import com.aiinterviewtrainer.ui.theme.AppBorder
import com.aiinterviewtrainer.ui.theme.AppGreenLight
import com.aiinterviewtrainer.ui.theme.AppOrange
import com.aiinterviewtrainer.ui.theme.AppRed
import com.aiinterviewtrainer.ui.theme.AppSub
import com.aiinterviewtrainer.ui.theme.AppText
import com.aiinterviewtrainer.ui.theme.Blue800
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SessionDetailScreen(
    navController: NavController,
    viewModel: SessionDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBg)
    ) {
        Surface(color = Blue800, shadowElevation = 4.dp) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 4.dp)
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                Text(
                    text = "Session Details",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Blue800)
                }
            }

            uiState.error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = uiState.error ?: "Failed to load session",
                                color = AppText,
                                fontSize = 14.sp
                            )
                            Button(
                                onClick = { viewModel.loadSession() },
                                colors = ButtonDefaults.buttonColors(containerColor = Blue800)
                            ) {
                                Text("Retry")
                            }
                        }
                    }
                }
            }

            uiState.session != null -> {
                SessionDetailContent(
                    session = uiState.session!!,
                    navController = navController
                )
            }
        }
    }
}

@Composable
private fun SessionDetailContent(
    session: Session,
    navController: NavController
) {
    val averageQuestionScore = if (session.questionDetails.isNotEmpty()) {
        session.questionDetails.map { it.score }.average().toInt()
    } else {
        session.overallScore
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(3.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = session.role,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppText
                        )
                        Text(
                            text = "${formatDate(session.date)} • ${session.type.name.lowercase().replaceFirstChar { it.uppercase() }}",
                            fontSize = 12.sp,
                            color = AppSub
                        )
                    }

                    ScoreChip(session.overallScore)
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    DetailMiniCard(
                        label = "Category",
                        value = session.interviewCategory.name.lowercase().replaceFirstChar { it.uppercase() }
                    )
                    DetailMiniCard(
                        label = "Questions",
                        value = "${session.questionCount}"
                    )
                    DetailMiniCard(
                        label = "Timer",
                        value = "${session.timePerQuestionSec}s"
                    )
                }

                if (session.feedbackSummary.isNotBlank()) {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7FAFF))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "Summary",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Blue800
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = session.feedbackSummary,
                                fontSize = 13.sp,
                                color = AppText
                            )
                        }
                    }
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
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                SectionHeader("SESSION ANALYTICS")

                AnalyticsRow(
                    icon = Icons.Default.Star,
                    label = "Overall Score",
                    value = "${session.overallScore}%"
                )

                AnalyticsRow(
                    icon = Icons.Default.MenuBook,
                    label = "Answered Questions",
                    value = "${session.questionDetails.size}"
                )

                AnalyticsRow(
                    icon = Icons.Default.Schedule,
                    label = "Avg Question Score",
                    value = "${averageQuestionScore}%"
                )
            }
        }

        if (session.questionDetails.isNotEmpty()) {
            SectionHeader("QUESTIONS & ANSWERS")

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column {
                    session.questionDetails.forEachIndexed { index, attempt ->
                        QuestionAttemptCard(
                            attempt = attempt,
                            index = index + 1
                        )
                        if (index < session.questionDetails.lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                color = AppBorder
                            )
                        }
                    }
                }
            }
        } else {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "No saved question attempts",
                        fontWeight = FontWeight.SemiBold,
                        color = AppText
                    )
                    Text(
                        text = "This session was saved without per-question details.",
                        fontSize = 13.sp,
                        color = AppSub
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailMiniCard(
    label: String,
    value: String
) {
    Card(
        modifier = Modifier.width(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7FAFF))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = label,
                fontSize = 11.sp,
                color = AppSub
            )
            Text(
                text = value,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = AppText
            )
        }
    }
}

@Composable
private fun AnalyticsRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Blue800,
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
            color = Blue800,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun QuestionAttemptCard(
    attempt: QuestionAttempt,
    index: Int
) {
    val scoreColor = when {
        attempt.score >= 75 -> AppGreenLight
        attempt.score >= 50 -> AppOrange
        else -> AppRed
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Question $index",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Blue800
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = attempt.questionText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppText
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            ScoreChip(attempt.score)
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SmallTag(text = attempt.category.ifBlank { "General" })
            SmallTag(text = attempt.difficulty.ifBlank { "Unknown" })
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "Your Answer",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppSub
            )
            Text(
                text = if (attempt.answerText.isNotBlank()) attempt.answerText else "No answer saved",
                fontSize = 13.sp,
                color = AppText
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = "Feedback",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppSub
            )
            Text(
                text = if (attempt.feedback.isNotBlank()) attempt.feedback else "No feedback available",
                fontSize = 13.sp,
                color = AppText
            )

            LinearProgressIndicator(
                progress = { attempt.score / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(999.dp)),
                color = scoreColor,
                trackColor = AppBorder
            )
        }
    }
}

@Composable
private fun SmallTag(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(Color(0xFFF3F6FB))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            color = AppSub
        )
    }
}

private fun formatDate(timestamp: Long): String {
    val fmt = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
    return fmt.format(Date(timestamp))
}