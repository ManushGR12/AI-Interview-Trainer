package com.aiinterviewtrainer.util;

import android.graphics.Bitmap;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetectorOptions;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/aiinterviewtrainer/util/FaceStatus;", "", "<init>", "(Ljava/lang/String;I)V", "NO_FACE", "FACE_DETECTED", "MULTIPLE_FACES", "FACE_TOO_SMALL", "app_debug"})
public enum FaceStatus {
    /*public static final*/ NO_FACE /* = new NO_FACE() */,
    /*public static final*/ FACE_DETECTED /* = new FACE_DETECTED() */,
    /*public static final*/ MULTIPLE_FACES /* = new MULTIPLE_FACES() */,
    /*public static final*/ FACE_TOO_SMALL /* = new FACE_TOO_SMALL() */;
    
    FaceStatus() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.aiinterviewtrainer.util.FaceStatus> getEntries() {
        return null;
    }
}