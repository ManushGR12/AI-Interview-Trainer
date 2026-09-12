package com.aiinterviewtrainer.ui.dashboard;

import androidx.lifecycle.ViewModel;
import com.aiinterviewtrainer.data.model.Session;
import com.aiinterviewtrainer.data.model.User;
import com.aiinterviewtrainer.data.repository.AuthRepository;
import com.aiinterviewtrainer.data.repository.SessionRepository;
import com.aiinterviewtrainer.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u00a2\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001c\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\nH\u00c6\u0003JM\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020\u00032\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\nH\u00d6\u0001J\t\u0010\"\u001a\u00020#H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015\u00a8\u0006$"}, d2 = {"Lcom/aiinterviewtrainer/ui/dashboard/DashboardUiState;", "", "isLoading", "", "user", "Lcom/aiinterviewtrainer/data/model/User;", "recentSessions", "", "Lcom/aiinterviewtrainer/data/model/Session;", "averageScore", "", "totalSessions", "bestScore", "<init>", "(ZLcom/aiinterviewtrainer/data/model/User;Ljava/util/List;III)V", "()Z", "getUser", "()Lcom/aiinterviewtrainer/data/model/User;", "getRecentSessions", "()Ljava/util/List;", "getAverageScore", "()I", "getTotalSessions", "getBestScore", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "", "app_debug"})
public final class DashboardUiState {
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final com.aiinterviewtrainer.data.model.User user = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.aiinterviewtrainer.data.model.Session> recentSessions = null;
    private final int averageScore = 0;
    private final int totalSessions = 0;
    private final int bestScore = 0;
    
    public DashboardUiState(boolean isLoading, @org.jetbrains.annotations.Nullable()
    com.aiinterviewtrainer.data.model.User user, @org.jetbrains.annotations.NotNull()
    java.util.List<com.aiinterviewtrainer.data.model.Session> recentSessions, int averageScore, int totalSessions, int bestScore) {
        super();
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.aiinterviewtrainer.data.model.User getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aiinterviewtrainer.data.model.Session> getRecentSessions() {
        return null;
    }
    
    public final int getAverageScore() {
        return 0;
    }
    
    public final int getTotalSessions() {
        return 0;
    }
    
    public final int getBestScore() {
        return 0;
    }
    
    public DashboardUiState() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.aiinterviewtrainer.data.model.User component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aiinterviewtrainer.data.model.Session> component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aiinterviewtrainer.ui.dashboard.DashboardUiState copy(boolean isLoading, @org.jetbrains.annotations.Nullable()
    com.aiinterviewtrainer.data.model.User user, @org.jetbrains.annotations.NotNull()
    java.util.List<com.aiinterviewtrainer.data.model.Session> recentSessions, int averageScore, int totalSessions, int bestScore) {
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