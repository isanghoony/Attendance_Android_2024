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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.ddd.attendance.R
import com.ddd.attendance.ui.theme.DDD_NEUTRAL_BLUE_40
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
    val preview = remember { Preview.Builder().build() }
    val imageAnalyzer = remember {
        ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
    }

    var borderColor by remember { mutableStateOf(Color.Transparent) }
    var borderWidth by remember { mutableStateOf(0.dp) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.95f)
    ) {
        LaunchedEffect(stopState) {
            val cameraProvider = cameraProviderFuture.get()

            if (!stopState) {
                try {
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
                        processImageProxy(
                            imageProxy = imageProxy,
                            qrCodeReader = qrCodeReader,
                            stopState = stopState,
                            onQrCodeScanned = { data ->
                                //data.contains("Hello world!") 재현용 qr code 값 Hello world!
                                if (data.isNotBlank()) {
                                    borderWidth = 4.dp
                                    borderColor = DDD_NEUTRAL_BLUE_40
                                    onQrCodeScanned(data)
                                } else {
                                    borderWidth = 0.dp
                                    borderColor = Color.Transparent
                                }
                            }
                        )
                    }

                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalyzer
                    )
                } catch (e: Exception) {
                    Log.e("CameraSetup", "Failed to set up camera: $e")
                }
            } else {
                cameraProvider.unbindAll()
            }
        }

        if (!stopState) {
            AndroidView(
                modifier = Modifier
                    .clipToBounds(),
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

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(
                    width = 240.dp,
                    height = 240.dp
                )
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = RoundedCornerShape(20.dp)
                )
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

        val centerX = width / 2
        val centerY = height / 2
        val boxSize = 240 // 네모 크기
        val left = maxOf(0, centerX - boxSize / 2)
        val top = maxOf(0, centerY - boxSize / 2)

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
            Log.d("QRCodeScanner", result.text)
            onQrCodeScanned(result.text)
        } catch (e: NotFoundException) {
            onQrCodeScanned("")
        } catch (e: Exception) {
            onQrCodeScanned("")
        } finally {
            imageProxy.close()
        }
    } else {
        imageProxy.close()
    }
}