package com.aiinterviewtrainer.ui.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aiinterviewtrainer.data.model.InterviewCategory
import com.aiinterviewtrainer.data.model.Session
import com.aiinterviewtrainer.ui.components.ScoreChip
import com.aiinterviewtrainer.ui.components.SectionHeader
import com.aiinterviewtrainer.ui.navigation.Routes
import com.aiinterviewtrainer.ui.theme.AppBg
import com.aiinterviewtrainer.ui.theme.AppBorder
import com.aiinterviewtrainer.ui.theme.AppGreenLight
import com.aiinterviewtrainer.ui.theme.AppGrey
import com.aiinterviewtrainer.ui.theme.AppOrange
import com.aiinterviewtrainer.ui.theme.AppRed
import com.aiinterviewtrainer.ui.theme.AppSub
import com.aiinterviewtrainer.ui.theme.AppText
import com.aiinterviewtrainer.ui.theme.Blue800
import com.aiinterviewtrainer.ui.theme.Blue900
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.layout.fillMaxHeight

@Composable
fun ProgressScreen(
    navController: NavController,
    viewModel: ProgressViewModel = hiltViewModel()
) {
    val sessions by viewModel.sessions.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val stats by viewModel.stats.collectAsState()

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
                .padding(bottom = 24.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 4.dp),
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
                        text = "Progress & Analytics",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Track recent performance, categories, and saved interview sessions",
                    color = Color(0xFFDBEAFE),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StatCard(
                        label = "Sessions",
                        value = "${stats.totalSessions}",
                        icon = Icons.Default.PlayCircle,
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        label = "Avg Score",
                        value = "${stats.avgScore}%",
                        icon = Icons.Default.TrendingUp,
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        label = "Streak",
                        value = "${stats.streak}d",
                        icon = Icons.Default.Whatshot,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Blue800)
                }
            }

            if (sessions.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        SectionHeader("TIME PERIOD")
                        Spacer(modifier = Modifier.height(8.dp))
                        val latestDate = sessions.maxOfOrNull { it.date } ?: 0L
                        val earliestDate = sessions.minOfOrNull { it.date } ?: 0L
                        Text(
                            text = "${formatDate(earliestDate)} - ${formatDate(latestDate)}",
                            fontSize = 13.sp,
                            color = AppSub
                        )
                    }
                }
            }

            val allScores = sessions.map { it.overallScore }
            if (allScores.size >= 2) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        SectionHeader("TREND CHART")
                        Spacer(modifier = Modifier.height(8.dp))
                        TrendChart(allScores.takeLast(12).reversed())
                    }
                }
            }

            if (sessions.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        SectionHeader("CATEGORY STAR-SCORE")
                        CategoryScoreRow(
                            label = "Technical",
                            score = averageForCategory(sessions, InterviewCategory.TECHNICAL)
                        )
                        CategoryScoreRow(
                            label = "Behavioral",
                            score = averageForCategory(sessions, InterviewCategory.BEHAVIORAL)
                        )
                        CategoryScoreRow(
                            label = "Mixed",
                            score = averageForCategory(sessions, InterviewCategory.MIXED)
                        )
                    }
                }
            }

            if (stats.roleBreakdown.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SectionHeader("PERFORMANCE BY ROLE")
                        stats.roleBreakdown.entries.forEach { (role, avg) ->
                            val count = sessions.count { it.role == role }
                            RoleRow(role = role, avg = avg, count = count)
                        }
                    }
                }
            }

            if (sessions.isNotEmpty()) {
                SectionHeader("SESSION HISTORY")
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    sessions.take(20).forEachIndexed { index, session ->
                        SessionRow(
                            session = session,
                            onClick = {
                                navController.navigate(Routes.sessionDetail(session.sessionId))
                            }
                        )
                        if (index < sessions.take(20).lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                color = AppBorder
                            )
                        }
                    }
                }
            } else if (!isLoading) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(32.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.BarChart,
                            contentDescription = null,
                            tint = AppGrey,
                            modifier = Modifier.size(48.dp)
                        )
                        Text(
                            text = "No sessions yet",
                            fontWeight = FontWeight.SemiBold,
                            color = AppText
                        )
                        Text(
                            text = "Complete sessions to see your progress",
                            fontSize = 13.sp,
                            color = AppSub
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatCard(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.15f))
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = label,
                fontSize = 9.sp,
                color = Color.White.copy(alpha = 0.75f)
            )
        }
    }
}

@Composable
private fun RoleRow(role: String, avg: Int, count: Int) {
    val color = when {
        avg >= 75 -> AppGreenLight
        avg >= 50 -> AppOrange
        else -> AppRed
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = role,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = AppText
            )
            Text(
                text = "$count sessions",
                fontSize = 11.sp,
                color = AppSub
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        LinearProgressIndicator(
            progress = { avg / 100f },
            modifier = Modifier
                .width(80.dp)
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = color,
            trackColor = AppBorder
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "$avg%",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}

@Composable
private fun CategoryScoreRow(label: String, score: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            color = AppText,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = buildStars(score),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$score%",
            color = Blue800,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun SessionRow(
    session: Session,
    onClick: () -> Unit
) {
    val color = when {
        session.overallScore >= 75 -> AppGreenLight
        session.overallScore >= 50 -> AppOrange
        else -> AppRed
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (session.type.name == "MOCK") {
                    Icons.Default.Videocam
                } else {
                    Icons.Default.MenuBook
                },
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(18.dp)
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = session.role,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppText
            )
            Text(
                text = "${formatDate(session.date)} • ${session.interviewCategory.name.lowercase().replaceFirstChar { it.uppercase() }} • ${session.timePerQuestionSec}s",
                fontSize = 11.sp,
                color = AppSub
            )
        }

        ScoreChip(session.overallScore)
    }
}

@Composable
private fun TrendChart(scores: List<Int>) {
    if (scores.isEmpty()) return

    val maxScore = scores.maxOrNull() ?: 100
    val minScore = maxOf(0, (scores.minOrNull() ?: 0) - 10)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        scores.forEach { score ->
            val normalized = if (maxScore > minScore) {
                (score - minScore).toFloat() / (maxScore - minScore)
            } else {
                1f
            }

            val color = when {
                score >= 75 -> AppGreenLight
                score >= 50 -> AppOrange
                else -> AppRed
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 2.dp)
                    .fillMaxHeight(maxOf(0.1f, normalized))
                    .clip(RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp))
                    .background(color.copy(alpha = 0.85f))
            )
        }
    }
}

private fun buildStars(score: Int): String {
    val filled = when {
        score >= 80 -> 5
        score >= 65 -> 4
        score >= 50 -> 3
        score >= 35 -> 2
        score >= 20 -> 1
        else -> 0
    }
    return "★".repeat(filled) + "☆".repeat(5 - filled)
}

private fun averageForCategory(
    sessions: List<Session>,
    category: InterviewCategory
): Int {
    val filtered = sessions.filter { it.interviewCategory == category }
    return if (filtered.isNotEmpty()) {
        filtered.map { it.overallScore }.average().toInt()
    } else {
        0
    }
}

private fun formatDate(timestamp: Long): String {
    val fmt = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return fmt.format(Date(timestamp))
}