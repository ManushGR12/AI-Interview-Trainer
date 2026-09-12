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

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0014"}, d2 = {"Lcom/aiinterviewtrainer/ui/dashboard/DashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/aiinterviewtrainer/data/repository/AuthRepository;", "userRepository", "Lcom/aiinterviewtrainer/data/repository/UserRepository;", "sessionRepository", "Lcom/aiinterviewtrainer/data/repository/SessionRepository;", "<init>", "(Lcom/aiinterviewtrainer/data/repository/AuthRepository;Lcom/aiinterviewtrainer/data/repository/UserRepository;Lcom/aiinterviewtrainer/data/repository/SessionRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/aiinterviewtrainer/ui/dashboard/DashboardUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadDashboard", "", "signOut", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.aiinterviewtrainer.data.repository.SessionRepository sessionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.aiinterviewtrainer.ui.dashboard.DashboardUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.aiinterviewtrainer.ui.dashboard.DashboardUiState> uiState = null;
    
    @javax.inject.Inject()
    public DashboardViewModel(@org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.repository.SessionRepository sessionRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.aiinterviewtrainer.ui.dashboard.DashboardUiState> getUiState() {
        return null;
    }
    
    public final void loadDashboard() {
    }
    
    public final void signOut() {
    }
}