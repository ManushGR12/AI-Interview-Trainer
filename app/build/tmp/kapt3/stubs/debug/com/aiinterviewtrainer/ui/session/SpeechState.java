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

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/aiinterviewtrainer/ui/session/SpeechState;", "", "isListening", "", "finalText", "", "partialText", "<init>", "(ZLjava/lang/String;Ljava/lang/String;)V", "()Z", "getFinalText", "()Ljava/lang/String;", "getPartialText", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class SpeechState {
    private final boolean isListening = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String finalText = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String partialText = null;
    
    public SpeechState(boolean isListening, @org.jetbrains.annotations.NotNull()
    java.lang.String finalText, @org.jetbrains.annotations.NotNull()
    java.lang.String partialText) {
        super();
    }
    
    public final boolean isListening() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFinalText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPartialText() {
        return null;
    }
    
    public SpeechState() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aiinterviewtrainer.ui.session.SpeechState copy(boolean isListening, @org.jetbrains.annotations.NotNull()
    java.lang.String finalText, @org.jetbrains.annotations.NotNull()
    java.lang.String partialText) {
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