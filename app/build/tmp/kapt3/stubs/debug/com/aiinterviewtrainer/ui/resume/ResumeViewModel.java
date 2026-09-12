package com.aiinterviewtrainer.ui.resume;

import android.net.Uri;
import androidx.lifecycle.ViewModel;
import com.aiinterviewtrainer.data.local.FileStorageManager;
import com.aiinterviewtrainer.data.repository.AuthRepository;
import com.aiinterviewtrainer.data.repository.GeminiRepository;
import com.aiinterviewtrainer.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0014J\u0016\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u0018H\u0082@\u00a2\u0006\u0002\u0010\u001cJ\u0006\u0010\u001d\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001e"}, d2 = {"Lcom/aiinterviewtrainer/ui/resume/ResumeViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/aiinterviewtrainer/data/repository/AuthRepository;", "userRepository", "Lcom/aiinterviewtrainer/data/repository/UserRepository;", "fileStorageManager", "Lcom/aiinterviewtrainer/data/local/FileStorageManager;", "geminiRepository", "Lcom/aiinterviewtrainer/data/repository/GeminiRepository;", "<init>", "(Lcom/aiinterviewtrainer/data/repository/AuthRepository;Lcom/aiinterviewtrainer/data/repository/UserRepository;Lcom/aiinterviewtrainer/data/local/FileStorageManager;Lcom/aiinterviewtrainer/data/repository/GeminiRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/aiinterviewtrainer/ui/resume/ResumeState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "saveResume", "", "uri", "Landroid/net/Uri;", "extractedText", "", "analyzeResume", "runResumeAnalysis", "uid", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetState", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ResumeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.local.FileStorageManager fileStorageManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.repository.GeminiRepository geminiRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.aiinterviewtrainer.ui.resume.ResumeState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.aiinterviewtrainer.ui.resume.ResumeState> uiState = null;
    
    @javax.inject.Inject()
    public ResumeViewModel(@org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.local.FileStorageManager fileStorageManager, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.repository.GeminiRepository geminiRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.aiinterviewtrainer.ui.resume.ResumeState> getUiState() {
        return null;
    }
    
    public final void saveResume(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    java.lang.String extractedText) {
    }
    
    public final void analyzeResume() {
    }
    
    private final java.lang.Object runResumeAnalysis(java.lang.String uid, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void resetState() {
    }
}