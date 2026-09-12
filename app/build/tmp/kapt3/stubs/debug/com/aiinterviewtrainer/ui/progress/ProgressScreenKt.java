package com.aiinterviewtrainer.ui.progress;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.CardDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.text.font.FontWeight;
import androidx.navigation.NavController;
import com.aiinterviewtrainer.data.model.InterviewCategory;
import com.aiinterviewtrainer.data.model.Session;
import com.aiinterviewtrainer.ui.components.ScoreChip;
import com.aiinterviewtrainer.ui.components.SectionHeader;
import com.aiinterviewtrainer.ui.navigation.Routes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a*\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0003\u001a \u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0003\u001a\u0018\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0011H\u0003\u001a\u001e\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u0019H\u0003\u001a\u0016\u0010\u001a\u001a\u00020\u00012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00110\u001cH\u0003\u001a\u0010\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0011H\u0002\u001a\u001e\u0010\u001e\u001a\u00020\u00112\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00170\u001c2\u0006\u0010 \u001a\u00020!H\u0002\u001a\u0010\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020$H\u0002\u00a8\u0006%"}, d2 = {"ProgressScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/aiinterviewtrainer/ui/progress/ProgressViewModel;", "StatCard", "label", "", "value", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "modifier", "Landroidx/compose/ui/Modifier;", "RoleRow", "role", "avg", "", "count", "CategoryScoreRow", "score", "SessionRow", "session", "Lcom/aiinterviewtrainer/data/model/Session;", "onClick", "Lkotlin/Function0;", "TrendChart", "scores", "", "buildStars", "averageForCategory", "sessions", "category", "Lcom/aiinterviewtrainer/data/model/InterviewCategory;", "formatDate", "timestamp", "", "app_debug"})
public final class ProgressScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void ProgressScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.ui.progress.ProgressViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StatCard(java.lang.String label, java.lang.String value, androidx.compose.ui.graphics.vector.ImageVector icon, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RoleRow(java.lang.String role, int avg, int count) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CategoryScoreRow(java.lang.String label, int score) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SessionRow(com.aiinterviewtrainer.data.model.Session session, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TrendChart(java.util.List<java.lang.Integer> scores) {
    }
    
    private static final java.lang.String buildStars(int score) {
        return null;
    }
    
    private static final int averageForCategory(java.util.List<com.aiinterviewtrainer.data.model.Session> sessions, com.aiinterviewtrainer.data.model.InterviewCategory category) {
        return 0;
    }
    
    private static final java.lang.String formatDate(long timestamp) {
        return null;
    }
}