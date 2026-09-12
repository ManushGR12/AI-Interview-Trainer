package com.aiinterviewtrainer.ui.profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.tooling.preview.Preview;
import androidx.navigation.NavController;
import com.aiinterviewtrainer.data.model.User;
import com.aiinterviewtrainer.ui.navigation.Routes;
import com.aiinterviewtrainer.ui.theme.*;
import com.google.accompanist.permissions.ExperimentalPermissionsApi;
import java.io.File;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001ap\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2&\u0010\u000b\u001a\"\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020\u00010\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007\u001a\u0018\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\rH\u0003\u001a\b\u0010\u0015\u001a\u00020\u0001H\u0007\u00a8\u0006\u0016"}, d2 = {"ProfileScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/aiinterviewtrainer/ui/profile/ProfileViewModel;", "ProfileScreenContent", "uiState", "Lcom/aiinterviewtrainer/ui/profile/ProfileUiState;", "onBack", "Lkotlin/Function0;", "onSaveProfile", "Lkotlin/Function4;", "", "Landroid/graphics/Bitmap;", "onNavigateToResume", "onNavigateToRoles", "onSignOut", "ProfileInfoRow", "label", "value", "ProfileScreenPreview", "app_debug"})
public final class ProfileScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void ProfileScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.ui.profile.ProfileViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {com.google.accompanist.permissions.ExperimentalPermissionsApi.class})
    @androidx.compose.runtime.Composable()
    public static final void ProfileScreenContent(@org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.ui.profile.ProfileUiState uiState, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function4<? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super android.graphics.Bitmap, kotlin.Unit> onSaveProfile, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToResume, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToRoles, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSignOut) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ProfileInfoRow(java.lang.String label, java.lang.String value) {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    @androidx.compose.runtime.Composable()
    public static final void ProfileScreenPreview() {
    }
}