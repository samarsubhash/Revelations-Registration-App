package com.example.revelations.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.revelations.R

@Composable
fun WelcomeScreen(navController: NavController) {
    // Cinematic background: Deep charcoal to Pitch Black
    val bgGradient = Brush.radialGradient(
        colors = listOf(Color(0xFF220000), Color(0xFF000000)),
        radius = 1500f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgGradient),
        contentAlignment = Alignment.Center
    ) {
        // --- AMBIENT NEON GLOW ---
        // Adds a soft red light leak from the bottom right
        Box(
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 100.dp, y = 100.dp)
                .background(Color.Red.copy(alpha = 0.1f), CircleShape)
                .blur(100.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 40.dp)
        ) {
            // --- THE HERO LOGO ---
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                // Outer subtle glow ring
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(1.dp, Color.Red.copy(alpha = 0.3f), CircleShape)
                        .blur(2.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .fillMaxSize(0.85f)
                        .clip(CircleShape)
                        .border(2.dp, Color.Red, CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            // --- MINIMALIST TYPOGRAPHY ---
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "REVELATIONS",
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.displayMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Light, // Thin and elegant
                        letterSpacing = 12.sp
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "2026",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 10.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(100.dp))

            // --- MODERN CAPSULE BUTTON ---
            Surface(
                onClick = { navController.navigate("register") },
                color = Color.Transparent,
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .border(1.dp, Color.Red, CircleShape)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color.Red.copy(alpha = 0.15f), Color.Transparent)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "ENTER EXPERIENCE",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 3.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}