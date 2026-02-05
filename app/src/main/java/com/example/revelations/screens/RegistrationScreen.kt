package com.example.revelations.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.revelations.R
import com.example.revelations.viewmodel.RegistrationViewModel

@Composable
fun RegistrationScreen(navController: NavController, viewModel: RegistrationViewModel) {

    var name by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var college by remember { mutableStateOf("") }

    val accentRed = Color(0xFFD32F2F)
    val darkBackground = Color(0xFF000000)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBackground)
    ) {
        // --- AMBIENT NEON GLOW ---
        Box(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.TopStart)
                .offset(x = (-120).dp, y = (-120).dp)
                .background(accentRed.copy(alpha = 0.15f), CircleShape)
                .blur(90.dp)
        )

        // --- TOP RIGHT PROFILE IMAGE ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 20.dp, end = 24.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .border(1.dp, accentRed.copy(alpha = 0.6f), CircleShape)
                    .padding(3.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // --- STEP INDICATOR & HEADER ---
            Text(
                text = "STEP 01",
                color = accentRed,
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 3.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "REGISTRATION",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraLight,
                letterSpacing = 1.sp
            )

            // Minimalist Accent Line
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .width(40.dp)
                    .height(2.dp)
                    .background(accentRed)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // --- INPUT FIELDS ---
            AestheticField("Full Name", name) { name = it }
            AestheticField("Mobile Number", mobile) { mobile = it }
            AestheticField("Email ID", email) { email = it }
            AestheticField("College Name", college) { college = it }

            Spacer(modifier = Modifier.height(50.dp))

            // --- CAPSULE PROCEED BUTTON ---
            Surface(
                onClick = {
                    if (name.isNotBlank() && mobile.length >= 10) {
                        viewModel.name.value = name
                        viewModel.mobile.value = mobile
                        viewModel.email.value = email
                        viewModel.college.value = college
                        navController.navigate("events")
                    }
                },
                color = Color.Transparent,
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .border(1.5.dp, accentRed, CircleShape)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                listOf(accentRed.copy(alpha = 0.2f), Color.Transparent)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "CONTINUE",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 4.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AestheticField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 11.sp, letterSpacing = 1.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFD32F2F),
            unfocusedBorderColor = Color.White.copy(alpha = 0.15f),
            focusedLabelColor = Color(0xFFD32F2F),
            unfocusedLabelColor = Color.Gray,
            cursorColor = Color(0xFFD32F2F)
        )
    )
}