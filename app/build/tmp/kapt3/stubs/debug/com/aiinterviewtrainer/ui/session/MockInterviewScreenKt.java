package com.aiinterviewtrainer.ui.session;

import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.CardDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import com.aiinterviewtrainer.ui.navigation.Routes;
import com.aiinterviewtrainer.util.FaceAnalyzer;
import com.aiinterviewtrainer.util.FaceStatus;
import com.aiinterviewtrainer.util.SpeechManager;
import com.google.accompanist.permissions.ExperimentalPermissionsApi;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\'\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0003\u00a2\u0006\u0004\b\f\u0010\r\u001a/\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\nH\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a\b\u0010\u0016\u001a\u00020\u0001H\u0007\u00a8\u0006\u0017"}, d2 = {"MockInterviewScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/aiinterviewtrainer/ui/session/SessionViewModel;", "SmallBadge", "text", "", "background", "Landroidx/compose/ui/graphics/Color;", "contentColor", "SmallBadge-WkMS-hQ", "(Ljava/lang/String;JJ)V", "MonitoringRow", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "value", "valueColor", "MonitoringRow-g2O1Hgs", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Ljava/lang/String;J)V", "VoiceWaveform", "app_debug"})
public final class MockInterviewScreenKt {
    
    @kotlin.OptIn(markerClass = {com.google.accompanist.permissions.ExperimentalPermissionsApi.class})
    @androidx.compose.runtime.Composable()
    public static final void MockInterviewScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.ui.session.SessionViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void VoiceWaveform() {
    }
}