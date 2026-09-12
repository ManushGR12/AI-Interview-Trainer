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

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u000bH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u000bH\u00c6\u0003JE\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\u000bH\u00d6\u0001J\t\u0010%\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018\u00a8\u0006&"}, d2 = {"Lcom/aiinterviewtrainer/ui/session/SessionConfig;", "", "role", "", "type", "Lcom/aiinterviewtrainer/data/model/SessionType;", "difficulty", "Lcom/aiinterviewtrainer/data/model/Difficulty;", "interviewCategory", "Lcom/aiinterviewtrainer/data/model/InterviewCategory;", "questionCount", "", "timePerQuestionSec", "<init>", "(Ljava/lang/String;Lcom/aiinterviewtrainer/data/model/SessionType;Lcom/aiinterviewtrainer/data/model/Difficulty;Lcom/aiinterviewtrainer/data/model/InterviewCategory;II)V", "getRole", "()Ljava/lang/String;", "getType", "()Lcom/aiinterviewtrainer/data/model/SessionType;", "getDifficulty", "()Lcom/aiinterviewtrainer/data/model/Difficulty;", "getInterviewCategory", "()Lcom/aiinterviewtrainer/data/model/InterviewCategory;", "getQuestionCount", "()I", "getTimePerQuestionSec", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class SessionConfig {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String role = null;
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.model.SessionType type = null;
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.model.Difficulty difficulty = null;
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.model.InterviewCategory interviewCategory = null;
    private final int questionCount = 0;
    private final int timePerQuestionSec = 0;
    
    public SessionConfig(@org.jetbrains.annotations.NotNull()
    java.lang.String role, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.model.SessionType type, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.model.Difficulty difficulty, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.model.InterviewCategory interviewCategory, int questionCount, int timePerQuestionSec) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRole() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aiinterviewtrainer.data.model.SessionType getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aiinterviewtrainer.data.model.Difficulty getDifficulty() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aiinterviewtrainer.data.model.InterviewCategory getInterviewCategory() {
        return null;
    }
    
    public final int getQuestionCount() {
        return 0;
    }
    
    public final int getTimePerQuestionSec() {
        return 0;
    }
    
    public SessionConfig() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aiinterviewtrainer.data.model.SessionType component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aiinterviewtrainer.data.model.Difficulty component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aiinterviewtrainer.data.model.InterviewCategory component4() {
        return null;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aiinterviewtrainer.ui.session.SessionConfig copy(@org.jetbrains.annotations.NotNull()
    java.lang.String role, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.model.SessionType type, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.model.Difficulty difficulty, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.model.InterviewCategory interviewCategory, int questionCount, int timePerQuestionSec) {
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