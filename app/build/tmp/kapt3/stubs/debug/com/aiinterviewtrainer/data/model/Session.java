package com.aiinterviewtrainer.data.model;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u009f\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0011\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0018\u0010\u0019J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0007H\u00c6\u0003J\t\u00103\u001a\u00020\tH\u00c6\u0003J\t\u00104\u001a\u00020\u000bH\u00c6\u0003J\t\u00105\u001a\u00020\rH\u00c6\u0003J\t\u00106\u001a\u00020\rH\u00c6\u0003J\t\u00107\u001a\u00020\rH\u00c6\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\r0\u0011H\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\u0014H\u00c6\u0003J\u000f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00160\u0011H\u00c6\u0003J\t\u0010<\u001a\u00020\u0014H\u00c6\u0003J\u00a1\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u0014H\u00c6\u0001J\u0013\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010A\u001a\u00020\rH\u00d6\u0001J\t\u0010B\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u000e\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u0011\u0010\u000f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010%R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001bR\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010)R\u0011\u0010\u0017\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010,\u00a8\u0006C"}, d2 = {"Lcom/aiinterviewtrainer/data/model/Session;", "", "sessionId", "", "userId", "role", "type", "Lcom/aiinterviewtrainer/data/model/SessionType;", "difficulty", "Lcom/aiinterviewtrainer/data/model/Difficulty;", "interviewCategory", "Lcom/aiinterviewtrainer/data/model/InterviewCategory;", "questionCount", "", "timePerQuestionSec", "overallScore", "questionScores", "", "feedbackSummary", "durationSeconds", "", "questionDetails", "Lcom/aiinterviewtrainer/data/model/QuestionAttempt;", "date", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/aiinterviewtrainer/data/model/SessionType;Lcom/aiinterviewtrainer/data/model/Difficulty;Lcom/aiinterviewtrainer/data/model/InterviewCategory;IIILjava/util/List;Ljava/lang/String;JLjava/util/List;J)V", "getSessionId", "()Ljava/lang/String;", "getUserId", "getRole", "getType", "()Lcom/aiinterviewtrainer/data/model/SessionType;", "getDifficulty", "()Lcom/aiinterviewtrainer/data/model/Difficulty;", "getInterviewCategory", "()Lcom/aiinterviewtrainer/data/model/InterviewCategory;", "getQuestionCount", "()I", "getTimePerQuestionSec", "getOverallScore", "getQuestionScores", "()Ljava/util/List;", "getFeedbackSummary", "getDurationSeconds", "()J", "getQuestionDetails", "getDate", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class Session {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String sessionId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userId = null;
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
    private final int overallScore = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Integer> questionScores = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String feedbackSummary = null;
    private final long durationSeconds = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.aiinterviewtrainer.data.model.QuestionAttempt> questionDetails = null;
    private final long date = 0L;
    
    public Session(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String role, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.model.SessionType type, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.model.Difficulty difficulty, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.model.InterviewCategory interviewCategory, int questionCount, int timePerQuestionSec, int overallScore, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> questionScores, @org.jetbrains.annotations.NotNull()
    java.lang.String feedbackSummary, long durationSeconds, @org.jetbrains.annotations.NotNull()
    java.util.List<com.aiinterviewtrainer.data.model.QuestionAttempt> questionDetails, long date) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSessionId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserId() {
        return null;
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
    
    public final int getOverallScore() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Integer> getQuestionScores() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFeedbackSummary() {
        return null;
    }
    
    public final long getDurationSeconds() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aiinterviewtrainer.data.model.QuestionAttempt> getQuestionDetails() {
        return null;
    }
    
    public final long getDate() {
        return 0L;
    }
    
    public Session() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Integer> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    public final long component12() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aiinterviewtrainer.data.model.QuestionAttempt> component13() {
        return null;
    }
    
    public final long component14() {
        return 0L;
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
    public final com.aiinterviewtrainer.data.model.SessionType component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aiinterviewtrainer.data.model.Difficulty component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aiinterviewtrainer.data.model.InterviewCategory component6() {
        return null;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aiinterviewtrainer.data.model.Session copy(@org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String role, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.model.SessionType type, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.model.Difficulty difficulty, @org.jetbrains.annotations.NotNull()
    com.aiinterviewtrainer.data.model.InterviewCategory interviewCategory, int questionCount, int timePerQuestionSec, int overallScore, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> questionScores, @org.jetbrains.annotations.NotNull()
    java.lang.String feedbackSummary, long durationSeconds, @org.jetbrains.annotations.NotNull()
    java.util.List<com.aiinterviewtrainer.data.model.QuestionAttempt> questionDetails, long date) {
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