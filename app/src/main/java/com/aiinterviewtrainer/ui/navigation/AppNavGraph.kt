package com.aiinterviewtrainer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aiinterviewtrainer.ui.auth.LoginScreen
import com.aiinterviewtrainer.ui.auth.RegisterScreen
import com.aiinterviewtrainer.ui.dashboard.DashboardScreen
import com.aiinterviewtrainer.ui.profile.ProfileScreen
import com.aiinterviewtrainer.ui.profile.ProfileSetupScreen
import com.aiinterviewtrainer.ui.progress.ProgressScreen
import com.aiinterviewtrainer.ui.resume.AIAnalysisScreen
import com.aiinterviewtrainer.ui.resume.ResumeUploadScreen
import com.aiinterviewtrainer.ui.roles.RoleSelectionScreen
import com.aiinterviewtrainer.ui.session.AIFeedbackScreen
import com.aiinterviewtrainer.ui.session.FaceVerifyScreen
import com.aiinterviewtrainer.ui.session.MockInterviewScreen
import com.aiinterviewtrainer.ui.session.QuestionScreen
import com.aiinterviewtrainer.ui.session.SessionDetailScreen
import com.aiinterviewtrainer.ui.session.SessionSetupScreen
import com.aiinterviewtrainer.ui.session.SessionSummaryScreen
import com.aiinterviewtrainer.ui.session.SessionViewModel
import com.aiinterviewtrainer.ui.session.VoiceAnswerScreen
import com.aiinterviewtrainer.ui.splash.SplashScreen

object Routes {
    const val SPLASH = "splash"
    const val REGISTER = "register"
    const val PHOTO_CAPTURE = "photo_capture"
    const val LOGIN = "login"
    const val PROFILE = "profile"
    const val RESUME_UPLOAD = "resume_upload"
    const val AI_ANALYSIS = "ai_analysis"
    const val ROLE_SELECTION = "role_selection"
    const val DASHBOARD = "dashboard"
    const val SESSION_SETUP = "session_setup"
    const val FACE_VERIFY = "face_verify"
    const val QUESTION = "question"
    const val VOICE_ANSWER = "voice_answer"
    const val AI_FEEDBACK = "ai_feedback"
    const val MOCK_INTERVIEW = "mock_interview"
    const val SESSION_SUMMARY = "session_summary"
    const val PROGRESS = "progress"
    const val SESSION_DETAIL = "session_detail/{sessionId}"

    fun sessionDetail(sessionId: String): String = "session_detail/$sessionId"
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(navController = navController)
        }

        composable(Routes.REGISTER) {
            RegisterScreen(navController = navController)
        }

        composable(Routes.PHOTO_CAPTURE) {
            ProfileSetupScreen(navController = navController)
        }

        composable(Routes.LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(Routes.PROFILE) {
            ProfileScreen(navController = navController)
        }

        composable(Routes.RESUME_UPLOAD) {
            ResumeUploadScreen(navController = navController)
        }

        composable(Routes.AI_ANALYSIS) {
            AIAnalysisScreen(navController = navController)
        }

        composable(Routes.ROLE_SELECTION) {
            RoleSelectionScreen(navController = navController)
        }

        composable(Routes.DASHBOARD) {
            DashboardScreen(navController = navController)
        }

        composable(Routes.PROGRESS) {
            ProgressScreen(navController = navController)
        }

        composable(
            route = Routes.SESSION_DETAIL,
            arguments = listOf(
                navArgument("sessionId") {
                    type = NavType.StringType
                }
            )
        ) {
            SessionDetailScreen(navController = navController)
        }

        composable(Routes.SESSION_SETUP) { backStackEntry ->
            val sessionViewModel: SessionViewModel = hiltViewModel(backStackEntry)
            SessionSetupScreen(
                navController = navController,
                viewModel = sessionViewModel
            )
        }

        composable(Routes.FACE_VERIFY) { currentEntry ->
            val parentEntry = rememberEntry(
                current = currentEntry,
                navController = navController,
                route = Routes.SESSION_SETUP
            )
            FaceVerifyScreen(
                navController = navController,
                viewModel = hiltViewModel(parentEntry)
            )
        }

        composable(Routes.QUESTION) { currentEntry ->
            val parentEntry = rememberEntry(
                current = currentEntry,
                navController = navController,
                route = Routes.SESSION_SETUP
            )
            QuestionScreen(
                navController = navController,
                viewModel = hiltViewModel(parentEntry)
            )
        }

        composable(Routes.VOICE_ANSWER) { currentEntry ->
            val parentEntry = rememberEntry(
                current = currentEntry,
                navController = navController,
                route = Routes.SESSION_SETUP
            )
            VoiceAnswerScreen(
                navController = navController,
                viewModel = hiltViewModel(parentEntry)
            )
        }

        composable(Routes.AI_FEEDBACK) { currentEntry ->
            val parentEntry = rememberEntry(
                current = currentEntry,
                navController = navController,
                route = Routes.SESSION_SETUP
            )
            AIFeedbackScreen(
                navController = navController,
                viewModel = hiltViewModel(parentEntry)
            )
        }

        composable(Routes.MOCK_INTERVIEW) { currentEntry ->
            val parentEntry = rememberEntry(
                current = currentEntry,
                navController = navController,
                route = Routes.SESSION_SETUP
            )
            MockInterviewScreen(
                navController = navController,
                viewModel = hiltViewModel(parentEntry)
            )
        }

        composable(Routes.SESSION_SUMMARY) { currentEntry ->
            val parentEntry = rememberEntry(
                current = currentEntry,
                navController = navController,
                route = Routes.SESSION_SETUP
            )
            SessionSummaryScreen(
                navController = navController,
                viewModel = hiltViewModel(parentEntry)
            )
        }
    }
}

@Composable
private fun rememberEntry(
    current: NavBackStackEntry,
    navController: NavHostController,
    route: String
): NavBackStackEntry {
    return remember(current) {
        navController.getBackStackEntry(route)
    }
}