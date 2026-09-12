package com.aiinterviewtrainer.util;

import android.graphics.Bitmap;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetectorOptions;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0016R \u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/aiinterviewtrainer/util/FaceAnalyzer;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", "onFaceStatusChanged", "Lkotlin/Function2;", "Lcom/aiinterviewtrainer/util/FaceStatus;", "", "", "<init>", "(Lkotlin/jvm/functions/Function2;)V", "detector", "Lcom/google/mlkit/vision/face/FaceDetector;", "analyze", "imageProxy", "Landroidx/camera/core/ImageProxy;", "app_debug"})
public final class FaceAnalyzer implements androidx.camera.core.ImageAnalysis.Analyzer {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function2<com.aiinterviewtrainer.util.FaceStatus, java.lang.Integer, kotlin.Unit> onFaceStatusChanged = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.mlkit.vision.face.FaceDetector detector = null;
    
    public FaceAnalyzer(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super com.aiinterviewtrainer.util.FaceStatus, ? super java.lang.Integer, kotlin.Unit> onFaceStatusChanged) {
        super();
    }
    
    @java.lang.Override()
    public void analyze(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.ImageProxy imageProxy) {
    }
}