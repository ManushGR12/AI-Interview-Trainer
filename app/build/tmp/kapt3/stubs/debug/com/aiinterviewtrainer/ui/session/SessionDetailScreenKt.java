package com.aiinterviewtrainer.ui.session;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.navigation.NavController;
import com.aiinterviewtrainer.data.model.QuestionAttempt;
import com.aiinterviewtrainer.data.model.Session;
import com.aiinterviewtrainer.ui.components.ScoreChip;
import com.aiinterviewtrainer.ui.components.SectionHeader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0003\u001a\u0018\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0003\u001a \u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0003\u001a\u0018\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0003\u001a\u0010\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u000bH\u0003\u001a\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002\u00a8\u0006\u001a"}, d2 = {"SessionDetailScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/aiinterviewtrainer/ui/session/SessionDetailViewModel;", "SessionDetailContent", "session", "Lcom/aiinterviewtrainer/data/model/Session;", "DetailMiniCard", "label", "", "value", "AnalyticsRow", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "QuestionAttemptCard", "attempt", "Lcom/aiinterviewtrainer/data/model/QuestionAttempt;", "index", "", "SmallTag", "text", "formatDate", "timestamp", "", "app_debug"})
public final class SessionDetailScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void SessionDetailScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.ui.session.SessionDetailViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SessionDetailContent(com.aiinterviewtrainer.data.model.Session session, androidx.navigation.NavController navController) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DetailMiniCard(java.lang.String label, java.lang.String value) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AnalyticsRow(androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String label, java.lang.String value) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void QuestionAttemptCard(com.aiinterviewtrainer.data.model.QuestionAttempt attempt, int index) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SmallTag(java.lang.String text) {
    }
    
    private static final java.lang.String formatDate(long timestamp) {
        return null;
    }
}