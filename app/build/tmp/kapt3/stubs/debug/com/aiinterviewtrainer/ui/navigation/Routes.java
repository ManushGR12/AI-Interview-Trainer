package com.aiinterviewtrainer.ui.navigation;

import androidx.compose.runtime.Composable;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavHostController;
import androidx.navigation.NavType;
import com.aiinterviewtrainer.ui.auth.LoginScreen;
import com.aiinterviewtrainer.ui.session.SessionViewModel;
import com.aiinterviewtrainer.ui.splash.SplashScreen;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0014\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/aiinterviewtrainer/ui/navigation/Routes;", "", "<init>", "()V", "SPLASH", "", "REGISTER", "PHOTO_CAPTURE", "LOGIN", "PROFILE", "RESUME_UPLOAD", "AI_ANALYSIS", "ROLE_SELECTION", "DASHBOARD", "SESSION_SETUP", "FACE_VERIFY", "QUESTION", "VOICE_ANSWER", "AI_FEEDBACK", "MOCK_INTERVIEW", "SESSION_SUMMARY", "PROGRESS", "SESSION_DETAIL", "sessionDetail", "sessionId", "app_debug"})
public final class Routes {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SPLASH = "splash";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REGISTER = "register";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PHOTO_CAPTURE = "photo_capture";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN = "login";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PROFILE = "profile";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String RESUME_UPLOAD = "resume_upload";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String AI_ANALYSIS = "ai_analysis";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ROLE_SELECTION = "role_selection";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DASHBOARD = "dashboard";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SESSION_SETUP = "session_setup";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FACE_VERIFY = "face_verify";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String QUESTION = "question";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VOICE_ANSWER = "voice_answer";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String AI_FEEDBACK = "ai_feedback";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MOCK_INTERVIEW = "mock_interview";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SESSION_SUMMARY = "session_summary";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PROGRESS = "progress";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SESSION_DETAIL = "session_detail/{sessionId}";
    @org.jetbrains.annotations.NotNull()
    public static final com.aiinterviewtrainer.ui.navigation.Routes INSTANCE = null;
    
    private Routes() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String sessionDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId) {
        return null;
    }
}