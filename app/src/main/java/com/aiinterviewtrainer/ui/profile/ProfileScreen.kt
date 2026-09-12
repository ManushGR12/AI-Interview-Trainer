package com.aiinterviewtrainer.ui.profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aiinterviewtrainer.data.model.User
import com.aiinterviewtrainer.ui.navigation.Routes
import com.aiinterviewtrainer.ui.theme.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ProfileScreenContent(
        uiState = uiState,
        onBack = { navController.popBackStack() },
        onSaveProfile = { name, college, gradYear, bitmap ->
            viewModel.saveProfile(name, college, gradYear, "", bitmap)
        },
        onNavigateToResume = { navController.navigate(Routes.RESUME_UPLOAD) },
        onNavigateToRoles = { navController.navigate(Routes.ROLE_SELECTION) },
        onSignOut = {
            navController.navigate(Routes.LOGIN) {
                popUpTo(0) { inclusive = true }
            }
        }
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProfileScreenContent(
    uiState: ProfileUiState,
    onBack: () -> Unit,
    onSaveProfile: (String, String, String, Bitmap?) -> Unit,
    onNavigateToResume: () -> Unit,
    onNavigateToRoles: () -> Unit,
    onSignOut: () -> Unit
) {
    val user = uiState.user

    var editMode by remember { mutableStateOf(false) }
    var nameField by remember { mutableStateOf("") }
    var collegeField by remember { mutableStateOf("") }
    var graduationYearField by remember { mutableStateOf("") }
    var capturedBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val cameraPermission = rememberPermissionState(android.Manifest.permission.CAMERA)
    val takePictureLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap -> if (bitmap != null) capturedBitmap = bitmap }

    LaunchedEffect(user) {
        user?.let {
            if (nameField.isEmpty()) nameField = it.name
            if (collegeField.isEmpty()) collegeField = it.college
            if (graduationYearField.isEmpty()) graduationYearField = it.graduationYear
        }
    }

    LaunchedEffect(uiState.isSaved) {
        if (uiState.isSaved) editMode = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBg)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(Blue900, Blue800)))
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                    Text(
                        "My Profile",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = { editMode = !editMode }) {
                        Icon(
                            if (editMode) Icons.Default.Close else Icons.Default.Edit,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(contentAlignment = Alignment.BottomEnd) {
                        Box(
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                                .background(Blue700)
                                .clickable {
                                    if (editMode) {
                                        if (cameraPermission.status.isGranted)
                                            takePictureLauncher.launch(null)
                                        else
                                            cameraPermission.launchPermissionRequest()
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            val photoPath = user?.profilePhotoPath
                            val displayBitmap: Bitmap? = when {
                                capturedBitmap != null -> capturedBitmap
                                !photoPath.isNullOrEmpty() -> {
                                    val f = File(photoPath)
                                    if (f.exists()) BitmapFactory.decodeFile(f.absolutePath) else null
                                }
                                else -> null
                            }

                            if (displayBitmap != null) {
                                Image(
                                    bitmap = displayBitmap.asImageBitmap(),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize().clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Icon(
                                    Icons.Default.Person, contentDescription = null,
                                    tint = Color.White, modifier = Modifier.size(48.dp)
                                )
                            }

                            if (editMode) {
                                Box(
                                    modifier = Modifier.fillMaxSize()
                                        .background(Color.Black.copy(alpha = 0.4f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.CameraAlt, null, tint = Color.White,
                                        modifier = Modifier.size(28.dp))
                                }
                            }
                        }

                        if (editMode) {
                            Box(
                                modifier = Modifier.size(26.dp).clip(CircleShape)
                                    .background(Blue700).border(2.dp, Color.White, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.CameraAlt, null, tint = Color.White,
                                    modifier = Modifier.size(14.dp))
                            }
                        }
                    }

                    Spacer(Modifier.height(10.dp))
                    Text(
                        user?.name?.ifEmpty { "Your Name" } ?: "Your Name",
                        color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp
                    )
                    Text(user?.email ?: "", color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)
                }
            }
        }

        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {

            val errorMsg = uiState.error
            if (!errorMsg.isNullOrEmpty()) {
                Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3F3)),
                    shape = RoundedCornerShape(10.dp)) {
                    Text(errorMsg, modifier = Modifier.padding(12.dp), color = AppRed, fontSize = 13.sp)
                }
            }

            // Personal info card
            Card(shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Person, null, tint = Blue800, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Personal Information", fontWeight = FontWeight.Bold, color = AppText)
                    }
                    Divider(color = AppBorder)

                    if (editMode) {
                        OutlinedTextField(
                            value = nameField, onValueChange = { nameField = it },
                            label = { Text("Full Name") },
                            modifier = Modifier.fillMaxWidth(), singleLine = true,
                            shape = RoundedCornerShape(10.dp)
                        )
                        OutlinedTextField(
                            value = collegeField, onValueChange = { collegeField = it },
                            label = { Text("College / University") },
                            modifier = Modifier.fillMaxWidth(), singleLine = true,
                            shape = RoundedCornerShape(10.dp)
                        )
                        OutlinedTextField(
                            value = graduationYearField, onValueChange = { graduationYearField = it },
                            label = { Text("Graduation Year") },
                            modifier = Modifier.fillMaxWidth(), singleLine = true,
                            shape = RoundedCornerShape(10.dp)
                        )
                    } else {
                        ProfileInfoRow("Name", user?.name?.ifEmpty { "—" } ?: "—")
                        ProfileInfoRow("College", user?.college?.ifEmpty { "—" } ?: "—")
                        ProfileInfoRow("Graduation Year", user?.graduationYear?.ifEmpty { "—" } ?: "—")
                    }
                }
            }

            // Resume card
            Card(shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)) {
                Row(modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Box(modifier = Modifier.size(44.dp).clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFFFF0F0)), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Description, null, tint = AppRed, modifier = Modifier.size(24.dp))
                        }
                        Column {
                            Text("Resume", fontWeight = FontWeight.SemiBold, color = AppText)
                            val hasResume = user?.resumePath?.isNotEmpty() == true
                            Text(if (hasResume) "PDF uploaded ✓" else "Not uploaded",
                                fontSize = 12.sp, color = if (hasResume) AppGreenLight else AppSub)
                        }
                    }
                    TextButton(onClick = onNavigateToResume) {
                        val hasResume = user?.resumePath?.isNotEmpty() == true
                        Text(if (hasResume) "Change" else "Upload", color = Blue800)
                    }
                }
            }

            // Target roles card
            val roles = user?.selectedRoles
            if (!roles.isNullOrEmpty()) {
                Card(shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Work, null, tint = Blue800, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Target Roles", fontWeight = FontWeight.Bold, color = AppText,
                                modifier = Modifier.weight(1f))
                            TextButton(onClick = onNavigateToRoles) {
                                Text("Edit", color = Blue800, fontSize = 12.sp)
                            }
                        }
                        Divider(color = AppBorder)
                        Spacer(Modifier.height(8.dp))
                        roles.forEach { roleItem ->
                            Row(modifier = Modifier.padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Blue800))
                                Spacer(Modifier.width(10.dp))
                                Text(roleItem, color = AppText, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }

            // Save button
            if (editMode) {
                Button(
                    onClick = {
                        onSaveProfile(nameField, collegeField, graduationYearField, capturedBitmap)
                    },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue800),
                    enabled = !uiState.isLoading
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                    } else {
                        Icon(Icons.Default.Save, null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Save Changes", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            // Sign out
            OutlinedButton(
                onClick = onSignOut,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, AppRed)
            ) {
                Icon(Icons.Default.Logout, null, tint = AppRed, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(8.dp))
                Text("Sign Out", color = AppRed)
            }
        }
    }
}

@Composable
private fun ProfileInfoRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = AppSub, fontSize = 13.sp)
        Text(value, color = AppText, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    AIInterviewTheme {
        ProfileScreenContent(
            uiState = ProfileUiState(
                user = User(
                    name = "John Doe",
                    email = "john.doe@example.com",
                    college = "Tech University",
                    graduationYear = "2024",
                    selectedRoles = listOf("Android Developer", "Software Engineer")
                )
            ),
            onBack = {},
            onSaveProfile = { _, _, _, _ -> },
            onNavigateToResume = {},
            onNavigateToRoles = {},
            onSignOut = {}
        )
    }
}
