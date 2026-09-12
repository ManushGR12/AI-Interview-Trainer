package com.aiinterviewtrainer.ui.session;

import androidx.lifecycle.ViewModel;
import com.aiinterviewtrainer.data.model.Difficulty;
import com.aiinterviewtrainer.data.model.FeedbackResult;
import com.aiinterviewtrainer.data.model.InterviewCategory;
import com.aiinterviewtrainer.data.model.Question;
import com.aiinterviewtrainer.data.model.QuestionAttempt;
import com.aiinterviewtrainer.data.model.Session;
import com.aiinterviewtrainer.data.model.SessionType;
import com.aiinterviewtrainer.data.repository.AuthRepository;
import com.aiinterviewtrainer.data.repository.GeminiRepository;
import com.aiinterviewtrainer.data.repository.SessionRepository;
import com.aiinterviewtrainer.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0017J\u0006\u0010 \u001a\u00020\u001dJ\u000e\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u000eJ\u0006\u0010#\u001a\u00020\u001dJ\u000e\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u000eJ\u0006\u0010&\u001a\u00020\u001dJ\u0016\u0010\'\u001a\u00020\u001d2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)H\u0002J,\u0010+\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020*2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010-\u001a\u00020\u000eH\u0082@\u00a2\u0006\u0002\u0010.J.\u00106\u001a\b\u0012\u0004\u0012\u0002070)2\u0006\u00108\u001a\u00020\u000e2\u0006\u00109\u001a\u00020*2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010/\u001a\u00020*8F\u00a2\u0006\u0006\u001a\u0004\b0\u00101R\u0011\u00102\u001a\u0002038F\u00a2\u0006\u0006\u001a\u0004\b2\u00104R\u0011\u00105\u001a\u0002038F\u00a2\u0006\u0006\u001a\u0004\b5\u00104\u00a8\u0006>"}, d2 = {"Lcom/aiinterviewtrainer/ui/session/SessionViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/aiinterviewtrainer/data/repository/AuthRepository;", "userRepository", "Lcom/aiinterviewtrainer/data/repository/UserRepository;", "sessionRepository", "Lcom/aiinterviewtrainer/data/repository/SessionRepository;", "geminiRepository", "Lcom/aiinterviewtrainer/data/repository/GeminiRepository;", "<init>", "(Lcom/aiinterviewtrainer/data/repository/AuthRepository;Lcom/aiinterviewtrainer/data/repository/UserRepository;Lcom/aiinterviewtrainer/data/repository/SessionRepository;Lcom/aiinterviewtrainer/data/repository/GeminiRepository;)V", "askedQuestions", "", "", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/aiinterviewtrainer/ui/session/SessionUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "sessionConfig", "Lcom/aiinterviewtrainer/ui/session/SessionConfig;", "_speechState", "Lcom/aiinterviewtrainer/ui/session/SpeechState;", "speechState", "getSpeechState", "loadUserRoles", "", "updateConfig", "config", "generateQuestions", "submitAnswer", "answer", "nextQuestion", "reportFaceWarning", "message", "clearFaceWarning", "generateSummary", "scores", "", "", "saveSession", "avgScore", "summary", "(ILjava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "overallScore", "getOverallScore", "()I", "isMockMode", "", "()Z", "isLastQuestion", "getFallbackQuestions", "Lcom/aiinterviewtrainer/data/model/Question;", "role", "count", "difficulty", "Lcom/aiinterviewtrainer/data/model/Difficulty;", "category", "Lcom/aiinterviewtrainer/data/model/InterviewCategory;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SessionViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.repository.SessionRepository sessionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.repository.GeminiRepository geminiRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> askedQuestions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.aiinterviewtrainer.ui.session.SessionUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.aiinterviewtrainer.ui.session.SessionUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private com.aiinterviewtrainer.ui.session.SessionConfig sessionConfig;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.aiinterviewtrainer.ui.session.SpeechState> _speechState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.aiinterviewtrainer.ui.session.SpeechState> speechState = null;
    
    @javax.inject.Inject()
    public SessionViewModel(@org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.repository.SessionRepository sessionRepository, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.repository.GeminiRepository geminiRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.aiinterviewtrainer.ui.session.SessionUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.aiinterviewtrainer.ui.session.SpeechState> getSpeechState() {
        return null;
    }
    
    private final void loadUserRoles() {
    }
    
    public final void updateConfig(@org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.ui.session.SessionConfig config) {
    }
    
    public final void generateQuestions() {
    }
    
    public final void submitAnswer(@org.jetbrains.annotations.NotNull()
    java.lang.String answer) {
    }
    
    public final void nextQuestion() {
    }
    
    public final void reportFaceWarning(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public final void clearFaceWarning() {
    }
    
    private final void generateSummary(java.util.List<java.lang.Integer> scores) {
    }
    
    private final java.lang.Object saveSession(int avgScore, java.util.List<java.lang.Integer> scores, java.lang.String summary, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final int getOverallScore() {
        return 0;
    }
    
    public final boolean isMockMode() {
        return false;
    }
    
    public final boolean isLastQuestion() {
        return false;
    }
    
    private final java.util.List<com.aiinterviewtrainer.data.model.Question> getFallbackQuestions(java.lang.String role, int count, com.aiinterviewtrainer.data.model.Difficulty difficulty, com.aiinterviewtrainer.data.model.InterviewCategory category) {
        return null;
    }
}