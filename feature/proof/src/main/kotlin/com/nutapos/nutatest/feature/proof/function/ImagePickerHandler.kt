package com.nutapos.nutatest.feature.proof.function

import android.Manifest
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class ImagePickerAction {
    object TakePhoto : ImagePickerAction()
    object PickFromGallery : ImagePickerAction()
}

@Composable
fun ImagePickerHandler(
    action: ImagePickerAction?,
    onImagePicked: (Uri) -> Unit,
    onActionCompleted: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var tempCameraUri: Uri? = null

    fun getUriForFile(file: File): Uri {
        val authority = "${context.packageName}.provider"
        return FileProvider.getUriForFile(context, authority, file)
    }

    fun createPrivateImageFile(): File {
        val imageDir = File(context.filesDir, "images")
        if (!imageDir.exists()) imageDir.mkdirs()
        return File.createTempFile("camera_photo_", ".jpg", imageDir)
    }

    fun copyUriToPrivateStorage(uri: Uri): Uri {
        val privateFile = createPrivateImageFile()
        context.contentResolver.openInputStream(uri)?.use { input ->
            privateFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return getUriForFile(privateFile)
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            it?.let {
                coroutineScope.launch(Dispatchers.IO) {
                    val privateUri = copyUriToPrivateStorage(it)
                    withContext(Dispatchers.Main) {
                        onImagePicked(privateUri)
                    }
                }
            }
            onActionCompleted()
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            if (it) {
                tempCameraUri?.let(onImagePicked)
            }
            onActionCompleted()
        }
    )

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if (it) {
                val newUri = getUriForFile(createPrivateImageFile())
                tempCameraUri = newUri
                cameraLauncher.launch(newUri)
            } else {
                onActionCompleted()
            }
        }
    )

    LaunchedEffect(action) {
        when (action) {
            ImagePickerAction.TakePhoto -> cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            ImagePickerAction.PickFromGallery -> galleryLauncher.launch("image/*")
            null -> { /* Do nothing */ }
        }
    }
}
