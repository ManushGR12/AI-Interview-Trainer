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

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\'\b\u0086\b\u0018\u00002\u00020\u0001B\u00ad\u0001\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\n\u0012\b\b\u0002\u0010\u0012\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0006H\u00c6\u0003J\t\u0010+\u001a\u00020\bH\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\t\u0010-\u001a\u00020\bH\u00c6\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H\u00c6\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003J\u000f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\nH\u00c6\u0003J\t\u00103\u001a\u00020\nH\u00c6\u0003J\u000f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00140\u0003H\u00c6\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u00af\u0001\u00106\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0002\u0010\u0011\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\n2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\nH\u00c6\u0001J\u0013\u00107\u001a\u00020\b2\b\u00108\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00109\u001a\u00020\u0006H\u00d6\u0001J\t\u0010:\u001a\u00020\nH\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u001cR\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010\u0011\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0011\u0010\u0012\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0019R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001e\u00a8\u0006;"}, d2 = {"Lcom/aiinterviewtrainer/ui/session/SessionUiState;", "", "questions", "", "Lcom/aiinterviewtrainer/data/model/Question;", "currentQuestionIndex", "", "isLoading", "", "faceWarning", "", "sessionComplete", "availableRoles", "userSkills", "feedback", "Lcom/aiinterviewtrainer/data/model/FeedbackResult;", "scores", "summaryText", "selectedRole", "questionAttempts", "Lcom/aiinterviewtrainer/data/model/QuestionAttempt;", "lastSavedSessionId", "<init>", "(Ljava/util/List;IZLjava/lang/String;ZLjava/util/List;Ljava/util/List;Lcom/aiinterviewtrainer/data/model/FeedbackResult;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getQuestions", "()Ljava/util/List;", "getCurrentQuestionIndex", "()I", "()Z", "getFaceWarning", "()Ljava/lang/String;", "getSessionComplete", "getAvailableRoles", "getUserSkills", "getFeedback", "()Lcom/aiinterviewtrainer/data/model/FeedbackResult;", "getScores", "getSummaryText", "getSelectedRole", "getQuestionAttempts", "getLastSavedSessionId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class SessionUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.aiinterviewtrainer.data.model.Question> questions = null;
    private final int currentQuestionIndex = 0;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String faceWarning = null;
    private final boolean sessionComplete = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> availableRoles = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> userSkills = null;
    @org.jetbrains.annotations.Nullable()
    private final com.aiinterviewtrainer.data.model.FeedbackResult feedback = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Integer> scores = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String summaryText = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String selectedRole = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.aiinterviewtrainer.data.model.QuestionAttempt> questionAttempts = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lastSavedSessionId = null;
    
    public SessionUiState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.aiinterviewtrainer.data.model.Question> questions, int currentQuestionIndex, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String faceWarning, boolean sessionComplete, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availableRoles, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> userSkills, @org.jetbrains.annotations.Nullable()
    com.aiinterviewtrainer.data.model.FeedbackResult feedback, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> scores, @org.jetbrains.annotations.NotNull()
    java.lang.String summaryText, @org.jetbrains.annotations.NotNull()
    java.lang.String selectedRole, @org.jetbrains.annotations.NotNull()
    java.util.List<com.aiinterviewtrainer.data.model.QuestionAttempt> questionAttempts, @org.jetbrains.annotations.Nullable()
    java.lang.String lastSavedSessionId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aiinterviewtrainer.data.model.Question> getQuestions() {
        return null;
    }
    
    public final int getCurrentQuestionIndex() {
        return 0;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFaceWarning() {
        return null;
    }
    
    public final boolean getSessionComplete() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getAvailableRoles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getUserSkills() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.aiinterviewtrainer.data.model.FeedbackResult getFeedback() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Integer> getScores() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSummaryText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSelectedRole() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aiinterviewtrainer.data.model.QuestionAttempt> getQuestionAttempts() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastSavedSessionId() {
        return null;
    }
    
    public SessionUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aiinterviewtrainer.data.model.Question> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aiinterviewtrainer.data.model.QuestionAttempt> component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component13() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.aiinterviewtrainer.data.model.FeedbackResult component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Integer> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aiinterviewtrainer.ui.session.SessionUiState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.aiinterviewtrainer.data.model.Question> questions, int currentQuestionIndex, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String faceWarning, boolean sessionComplete, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availableRoles, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> userSkills, @org.jetbrains.annotations.Nullable()
    com.aiinterviewtrainer.data.model.FeedbackResult feedback, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> scores, @org.jetbrains.annotations.NotNull()
    java.lang.String summaryText, @org.jetbrains.annotations.NotNull()
    java.lang.String selectedRole, @org.jetbrains.annotations.NotNull()
    java.util.List<com.aiinterviewtrainer.data.model.QuestionAttempt> questionAttempts, @org.jetbrains.annotations.Nullable()
    java.lang.String lastSavedSessionId) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}