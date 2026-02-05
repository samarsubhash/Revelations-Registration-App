package com.example.revelations.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.revelations.showConfirmationNotification
import com.example.revelations.viewmodel.RegistrationViewModel

@Composable
fun ConfirmationScreen(viewModel: RegistrationViewModel) {
    val context = LocalContext.current

    // Collecting State
    val name by viewModel.name.collectAsState()
    val mobile by viewModel.mobile.collectAsState()
    val email by viewModel.email.collectAsState()
    val college by viewModel.college.collectAsState()
    val group by viewModel.group.collectAsState()
    val events by viewModel.events.collectAsState()

    var confirmed by remember { mutableStateOf(false) }
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
                .size(400.dp)
                .align(Alignment.Center)
                .background(accentRed.copy(alpha = 0.08f), CircleShape)
                .blur(120.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // --- HEADER ---
            Text(
                text = "SUMMARY",
                color = accentRed,
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 3.sp
            )
            Text(
                text = "VERIFY DETAILS",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraLight,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- FROSTED GLASS CARD ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color.White.copy(alpha = 0.03f))
                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(28.dp))
                    .padding(24.dp)
            ) {
                Column {
                    DetailRow("ATTENDEE", name)
                    DetailRow("CONTACT", mobile)
                    DetailRow("INSTITUTION", college)
                    DetailRow("GROUP", group)

                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = Color.White.copy(alpha = 0.1f), thickness = 0.5.dp)
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "SELECTIONS",
                        color = accentRed,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    events.forEach { event ->
                        Text(
                            text = event,
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // --- CONFIRMATION ACTION ---
            if (!confirmed) {
                Surface(
                    onClick = {
                        confirmed = true
                        showConfirmationNotification(context)
                    },
                    color = Color.Transparent,
                    shape = CircleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .border(1.5.dp, accentRed, CircleShape)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.horizontalGradient(
                                    listOf(accentRed.copy(alpha = 0.25f), Color.Transparent)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "FINALIZE REGISTRATION",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )
                    }
                }
            }

            // --- SUCCESS STATE ---
            AnimatedVisibility(visible = confirmed, enter = fadeIn()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Success",
                        modifier = Modifier.size(50.dp),
                        tint = accentRed
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "ACCESS GRANTED",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 4.sp
                    )
                    Text(
                        "Thank you for registering for Revelations 2026",
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.4f),
            fontSize = 9.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraLight
        )
    }
}