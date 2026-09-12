package com.aiinterviewtrainer.data.repository;

import com.aiinterviewtrainer.data.model.Difficulty;
import com.aiinterviewtrainer.data.model.InterviewCategory;
import com.aiinterviewtrainer.data.model.QuestionAttempt;
import com.aiinterviewtrainer.data.model.Session;
import com.aiinterviewtrainer.data.model.SessionType;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0004\b\r\u0010\u000eJ$\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\t2\u0006\u0010\u0011\u001a\u00020\nH\u0086@\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\t2\u0006\u0010\u0015\u001a\u00020\nH\u0086@\u00a2\u0006\u0004\b\u0016\u0010\u0013J\u0018\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0018*\u00020\fH\u0002J\u000e\u0010\u0019\u001a\u0004\u0018\u00010\f*\u00020\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/aiinterviewtrainer/data/repository/SessionRepository;", "", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "<init>", "(Lcom/google/firebase/firestore/FirebaseFirestore;)V", "sessionsCollection", "Lcom/google/firebase/firestore/CollectionReference;", "saveSession", "Lkotlin/Result;", "", "session", "Lcom/aiinterviewtrainer/data/model/Session;", "saveSession-gIAlu-s", "(Lcom/aiinterviewtrainer/data/model/Session;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSessionsForUser", "", "userId", "getSessionsForUser-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSessionById", "sessionId", "getSessionById-gIAlu-s", "toMap", "", "toSession", "Lcom/google/firebase/firestore/DocumentSnapshot;", "app_debug"})
public final class SessionRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference sessionsCollection = null;
    
    @javax.inject.Inject()
    public SessionRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> toMap(com.aiinterviewtrainer.data.model.Session $this$toMap) {
        return null;
    }
    
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    private final com.aiinterviewtrainer.data.model.Session toSession(com.google.firebase.firestore.DocumentSnapshot $this$toSession) {
        return null;
    }
}