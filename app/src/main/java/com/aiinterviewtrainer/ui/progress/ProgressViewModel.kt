package com.aiinterviewtrainer.ui.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiinterviewtrainer.data.model.Session
import com.aiinterviewtrainer.data.repository.AuthRepository
import com.aiinterviewtrainer.data.repository.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    private val _sessions = MutableStateFlow<List<Session>>(emptyList())
    val sessions = _sessions.asStateFlow()

    private val _stats = MutableStateFlow(ProgressStats())
    val stats = _stats.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true

            val uid = authRepository.currentUser?.uid ?: run {
                _sessions.value = emptyList()
                _stats.value = ProgressStats()
                _isLoading.value = false
                return@launch
            }

            sessionRepository.getSessionsForUser(uid)
                .onSuccess { list ->
                    _sessions.value = list

                    if (list.isNotEmpty()) {
                        val avg = list.map { it.overallScore }.average().toInt()
                        val best = list.maxOf { it.overallScore }
                        val streak = calculateStreak(list)
                        val roleBreakdown = list
                            .groupBy { it.role }
                            .mapValues { (_, sessions) ->
                                sessions.map { it.overallScore }.average().toInt()
                            }

                        _stats.value = ProgressStats(
                            totalSessions = list.size,
                            avgScore = avg,
                            bestScore = best,
                            streak = streak,
                            roleBreakdown = roleBreakdown
                        )
                    } else {
                        _stats.value = ProgressStats()
                    }
                }
                .onFailure {
                    _sessions.value = emptyList()
                    _stats.value = ProgressStats()
                }

            _isLoading.value = false
        }
    }

    private fun calculateStreak(sessions: List<Session>): Int {
        if (sessions.isEmpty()) return 0

        val uniqueDays = sessions
            .map { startOfDay(it.date) }
            .distinct()
            .sortedDescending()

        if (uniqueDays.isEmpty()) return 0

        val today = startOfDay(System.currentTimeMillis())
        val yesterday = today - DAY_MS

        if (uniqueDays.first() != today && uniqueDays.first() != yesterday) {
            return 0
        }

        var streak = 1
        var expectedDay = uniqueDays.first() - DAY_MS

        for (i in 1 until uniqueDays.size) {
            if (uniqueDays[i] == expectedDay) {
                streak++
                expectedDay -= DAY_MS
            } else if (uniqueDays[i] < expectedDay) {
                break
            }
        }

        return streak
    }

    private fun startOfDay(timestamp: Long): Long {
        val cal = Calendar.getInstance().apply {
            timeInMillis = timestamp
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return cal.timeInMillis
    }

    companion object {
        private const val DAY_MS = 86_400_000L
    }
}

data class ProgressStats(
    val totalSessions: Int = 0,
    val avgScore: Int = 0,
    val bestScore: Int = 0,
    val streak: Int = 0,
    val roleBreakdown: Map<String, Int> = emptyMap()
)