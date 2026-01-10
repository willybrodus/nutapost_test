package com.nutapos.nutatest.feature.cash_out.form

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.nutapos.nutatest.core.ui.component.NutaTestButton
import com.nutapos.nutatest.core.ui.component.NutaTestCurrencyTextField
import com.nutapos.nutatest.core.ui.component.NutaTestTextField
import com.nutapos.nutatest.core.ui.component.NutaTestTopAppBar
import com.nutapos.nutatest.core.ui.theme.GreenMain
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.feature.cash_out.R
import com.nutapos.nutatest.feature.cash_out.dialog.OutcomeType
import com.nutapos.nutatest.feature.cash_out.dialog.OutcomeTypeSelectionBottomSheet
import com.nutapos.nutatest.feature.proof.ImagePickerBottomSheet
import com.nutapos.nutatest.feature.proof.function.ImagePickerAction
import com.nutapos.nutatest.feature.proof.function.ImagePickerHandler
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CashOutFormScreen(
  onBackClick: () -> Unit,
  onNavigateToPaidToSelection: () -> Unit,
) {
  var imageUri by remember { mutableStateOf<Uri?>(null) }
  var imagePickerAction by remember { mutableStateOf<ImagePickerAction?>(null) }

  var outcomeType by remember { mutableStateOf<OutcomeType?>(null) }
  var amount by remember { mutableStateOf(TextFieldValue("0")) }
  var description by remember { mutableStateOf("") }

  val outcomeTypeSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  var showOutcomeTypeBottomSheet by remember { mutableStateOf(false) }

  val imagePickerSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  var showImagePickerBottomSheet by remember { mutableStateOf(false) }

  val coroutineScope = rememberCoroutineScope()

  ImagePickerHandler(
    action = imagePickerAction,
    onImagePicked = { imageUri = it },
    onActionCompleted = { imagePickerAction = null }
  )

  Scaffold(
    topBar = {
      NutaTestTopAppBar(
        title = stringResource(R.string.title_create_cash_out_transaction),
        onNavigationClick = onBackClick
      )
    },
    bottomBar = {
      NutaTestButton(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        text = stringResource(R.string.action_save),
        onClick = { /* Handle save */ }
      )
    }
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(paddingValues)
        .padding(16.dp)
    ) {
      NutaTestReadOnlyTextField(
        label = stringResource(R.string.label_cash_out_from),
        value = "Kasir Perangkat ke-49"
      )
      Spacer(modifier = Modifier.height(16.dp))
      NutaTestTextField(
        modifier = Modifier.fillMaxWidth(),
        value = description,
        onValueChange = { description = it },
        label = stringResource(R.string.label_description),
        placeholder = stringResource(R.string.hint_description_cash_out)
      )
      Spacer(modifier = Modifier.height(16.dp))
      NutaTestCurrencyTextField(
        modifier = Modifier.fillMaxWidth(),
        label = stringResource(R.string.label_amount),
        value = amount.text,
        onValueChange = { amount = TextFieldValue(it) }
      )
      Spacer(modifier = Modifier.height(16.dp))
      NutaTestReadOnlyTextField(
        label = stringResource(R.string.label_outcome_type),
        value = outcomeType?.title ?: "",
        placeholder = stringResource(R.string.hint_select_outcome_type),
        onClick = { showOutcomeTypeBottomSheet = true },
        trailingContent = {
          Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null
          )
        }
      )
      Spacer(modifier = Modifier.height(24.dp))
      Text(
        text = stringResource(R.string.title_upload_proof),
        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
      )
      Spacer(modifier = Modifier.height(16.dp))

      if (imageUri == null) {
        ImagePlaceholder(onClick = { showImagePickerBottomSheet = true })
      } else {
        ImagePreview(
          imageUri = imageUri!!,
          onDeleteClick = { imageUri = null },
          onEditClick = { showImagePickerBottomSheet = true }
        )
      }
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = stringResource(R.string.text_image_format_info),
        modifier = Modifier.align(Alignment.CenterHorizontally),
        style = TextStyle(color = Color.Gray, fontSize = 12.sp)
      )
    }
  }

  if (showOutcomeTypeBottomSheet) {
    OutcomeTypeSelectionBottomSheet(
      outcomeTypeSheetState,
      onDismiss = { showOutcomeTypeBottomSheet = false },
      initialSelected = outcomeType,
      onSelected = { selected ->
        outcomeType = selected
        coroutineScope.launch {
          outcomeTypeSheetState.hide()
        }.invokeOnCompletion {
          if (!outcomeTypeSheetState.isVisible) {
            showOutcomeTypeBottomSheet = false
          }
        }
      }
    )
  }

  if (showImagePickerBottomSheet) {
    ImagePickerBottomSheet(
      sheetState = imagePickerSheetState,
      onDismiss = { showImagePickerBottomSheet = false },
      onGalleryClick = {
        coroutineScope.launch { imagePickerSheetState.hide() }.invokeOnCompletion {
          showImagePickerBottomSheet = false
          imagePickerAction = ImagePickerAction.PickFromGallery
        }
      },
      onCameraClick = {
        coroutineScope.launch { imagePickerSheetState.hide() }.invokeOnCompletion {
          showImagePickerBottomSheet = false
          imagePickerAction = ImagePickerAction.TakePhoto
        }
      }
    )
  }
}

@Composable
private fun NutaTestReadOnlyTextField(
  label: String,
  value: String,
  placeholder: String = "",
  onClick: (() -> Unit)? = null,
  trailingContent: @Composable (() -> Unit)? = null
) {
  NutaTestTextField(
    value = value,
    onValueChange = {},
    label = label,
    placeholder = placeholder,
    readOnly = true,
    onTextFieldClick = onClick,
    trailingContent = trailingContent
  )
}

@Composable
private fun ImagePlaceholder(onClick: () -> Unit) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(150.dp)
      .clip(RoundedCornerShape(16.dp))
      .drawBehind {
        val stroke = Stroke(
          width = 1.5.dp.toPx(),
          pathEffect = PathEffect.dashPathEffect(floatArrayOf(25f, 15f), 0f)
        )
        drawRoundRect(
          color = GreenMain,
          cornerRadius = CornerRadius(16.dp.toPx()),
          style = stroke
        )
      }
      .clickable(onClick = onClick),
    contentAlignment = Alignment.Center
  ) {
    Box(
      modifier = Modifier
        .size(48.dp)
        .background(color = GreenMain, shape = CircleShape),
      contentAlignment = Alignment.Center
    ) {
      Icon(Icons.Default.Add, contentDescription = "Add Photo", tint = Color.White)
    }
  }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ImagePreview(
  imageUri: Uri,
  onDeleteClick: () -> Unit,
  onEditClick: () -> Unit
) {
  Box(modifier = Modifier
    .fillMaxWidth()
    .height(150.dp)) {
    GlideImage(
      model = imageUri,
      contentDescription = "Selected Image",
      modifier = Modifier
        .matchParentSize()
        .clip(RoundedCornerShape(8.dp)),
      contentScale = ContentScale.Crop
    )
    IconButton(
      onClick = onEditClick,
      modifier = Modifier.align(Alignment.TopEnd)
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_edit),
        contentDescription = "Edit",
        tint = Color.White
      )
    }
    IconButton(
      onClick = onDeleteClick,
      modifier = Modifier.align(Alignment.BottomEnd)
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_delete),
        contentDescription = "Delete",
        tint = Color.Red
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun CashOutFormScreenPreview() {
  NutaTestTheme {
    CashOutFormScreen(
      onBackClick = {},
      onNavigateToPaidToSelection = {}
    )
  }
}
