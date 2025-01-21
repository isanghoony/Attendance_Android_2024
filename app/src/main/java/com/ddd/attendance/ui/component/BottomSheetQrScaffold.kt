package com.ddd.attendance.ui.component

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.ddd.attendance.R
import com.google.common.util.concurrent.ListenableFuture
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.NotFoundException
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetQrScaffold(
    scaffoldState: BottomSheetScaffoldState,
    onCloseImageClicked: () -> Unit,
    onQrCodeScanned: (String) -> Unit,
    bodyContent: @Composable () -> Unit
) {
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        sheetDragHandle = {
            Box(modifier = Modifier.fillMaxWidth().height(0.dp))
        },
        sheetContent = {
            BottomSheetContent(
                onCloseImageClicked = onCloseImageClicked,
                onQrCodeScanned = onQrCodeScanned,
                stopState = if (scaffoldState.bottomSheetState.isVisible) false else true,
            )
        },
    ) {
        bodyContent()
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun BottomSheetContent(
    onCloseImageClicked: () -> Unit,
    onQrCodeScanned: (String) -> Unit,
    stopState: Boolean,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val preview: Preview = Preview.Builder().build()
    val imageAnalyzer = ImageAnalysis.Builder()
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build()

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .fillMaxHeight(0.95f)
    ) {
        DisposableEffect(lifecycleOwner.lifecycle, stopState) {
            if (!stopState) {
                val observer = LifecycleEventObserver { _, event ->
                    when (event) {
                        Lifecycle.Event.ON_RESUME -> {
                            cameraProviderFuture.addListener({
                                val cameraProvider = cameraProviderFuture.get()
                                cameraProvider.unbindAll()

                                val qrCodeReader = MultiFormatReader().apply {
                                    setHints(
                                        mapOf(
                                            DecodeHintType.POSSIBLE_FORMATS to listOf(BarcodeFormat.QR_CODE)
                                        )
                                    )
                                }

                                imageAnalyzer.setAnalyzer(
                                    ContextCompat.getMainExecutor(context)
                                ) { imageProxy ->
                                    processImageProxy(imageProxy, qrCodeReader, onQrCodeScanned, stopState)
                                }

                                cameraProvider.bindToLifecycle(
                                    lifecycleOwner,
                                    CameraSelector.DEFAULT_BACK_CAMERA,
                                    preview,
                                    imageAnalyzer
                                )
                            }, ContextCompat.getMainExecutor(context))
                        }

                        Lifecycle.Event.ON_PAUSE -> {
                            // Pause analysis
                            cameraProviderFuture.get().unbindAll()
                        }

                        else -> {}
                    }
                }

                lifecycleOwner.lifecycle.addObserver(observer)

                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                    cameraProviderFuture.get().unbindAll()
                }
            } else {
                onDispose {
                    cameraProviderFuture.get().unbindAll()
                }
            }
        }

        LaunchedEffect(cameraProviderFuture, stopState) {
            if (!stopState) {
                cameraProviderFuture.addListener({
                    try {
                        val cameraProvider = cameraProviderFuture.get()
                        cameraProvider.unbindAll()

                        val qrCodeReader = MultiFormatReader().apply {
                            setHints(
                                mapOf(
                                    DecodeHintType.POSSIBLE_FORMATS to listOf(BarcodeFormat.QR_CODE)
                                )
                            )
                        }

                        imageAnalyzer.setAnalyzer(
                            ContextCompat.getMainExecutor(context)
                        ) { imageProxy ->
                            processImageProxy(imageProxy, qrCodeReader, onQrCodeScanned, stopState)
                        }

                        cameraProvider.bindToLifecycle(
                            lifecycleOwner = lifecycleOwner,
                            cameraSelector = cameraSelector,
                            preview,
                            imageAnalyzer
                        )
                    } catch (exc: Exception) {
                        Log.e("cameraFail", exc.toString())
                    }
                }, ContextCompat.getMainExecutor(context))
            } else {
                cameraProviderFuture.get().unbindAll()
            }
        }

        if (!stopState) {
            AndroidView(
                modifier = Modifier.clipToBounds(),
                factory = { ctx ->
                    PreviewView(ctx).apply {
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    }
                },
                update = { previewView ->
                    if (previewView.surfaceProvider != preview.surfaceProvider) {
                        preview.surfaceProvider = previewView.surfaceProvider
                    }
                }
            )
        }

        Image(
            modifier = Modifier
                .padding(start = 24.dp, top = 24.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        onCloseImageClicked()
                    }
                ),
            painter = painterResource(R.drawable.ic_36_qr_clear),
            contentDescription = "QR Finish"
        )
    }
}

@androidx.annotation.OptIn(ExperimentalGetImage::class)
private fun processImageProxy(
    imageProxy: ImageProxy,
    qrCodeReader: MultiFormatReader,
    onQrCodeScanned: (String) -> Unit,
    stopState: Boolean
) {
    if (stopState) {
        imageProxy.close()
        return
    }

    val image = imageProxy.image
    if (image != null) {
        val buffer = image.planes[0].buffer
        val data = ByteArray(buffer.remaining())
        buffer.get(data)

        val width = image.width
        val height = image.height
        val rotationDegrees = imageProxy.imageInfo.rotationDegrees

        // 중앙 네모 영역 계산 (네모 크기: 200x200)
        val centerX = width / 2
        val centerY = height / 2
        val boxSize = 240 // 네모 크기
        val left = maxOf(0, centerX - boxSize / 2)
        val top = maxOf(0, centerY - boxSize / 2)

        // 중앙 네모 영역에 맞는 Source 생성
        val source = PlanarYUVLuminanceSource(
            data,
            width,
            height,
            left,
            top,
            boxSize,
            boxSize,
            false
        )

        val binaryBitmap = BinaryBitmap(HybridBinarizer(source))

        try {
            val result = qrCodeReader.decode(binaryBitmap)
            Log.d("QRCodeScanner", "QR 코드 스캔 성공: ${result.text}")
            onQrCodeScanned(result.text)
        } catch (e: NotFoundException) {
            Log.d("QRCodeScanner", "QR 코드가 중앙 영역에 없음")
        } catch (e: Exception) {
            Log.e("QRCodeScanner", "QR 코드 처리 중 오류: ${e.message}")
        } finally {
            imageProxy.close()
        }
    } else {
        imageProxy.close()
    }
}