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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.revelations.R
import com.example.revelations.viewmodel.RegistrationViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventSelectionScreen(navController: NavController, viewModel: RegistrationViewModel) {

    val groups = listOf("The Signal Seekers", "The Demogorgons", "The Hawkins Lab")

    var expanded by remember { mutableStateOf(false) }
    var selectedGroup by remember { mutableStateOf(groups[0]) }
    var eventSliderValue by remember { mutableFloatStateOf(1f) }

    val accentRed = Color(0xFFD32F2F)
    val darkBackground = Color(0xFF000000)

    Box(modifier = Modifier.fillMaxSize().background(darkBackground)) {

        // --- AMBIENT NEON GLOW ---
        Box(
            modifier = Modifier
                .size(350.dp)
                .align(Alignment.BottomStart)
                .offset(x = (-150).dp, y = (150).dp)
                .background(accentRed.copy(alpha = 0.1f), CircleShape)
                .blur(100.dp)
        )

        // --- TOP RIGHT LOGO ---
        Box(
            modifier = Modifier.fillMaxWidth().statusBarsPadding().padding(top = 20.dp, end = 24.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // --- HEADER ---
            Text(
                text = "STEP 02",
                color = accentRed,
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 3.sp
            )
            Text(
                text = "CHOOSE YOUR GROUP",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraLight,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- DROPDOWN ---
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedGroup,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Assign Group", fontSize = 11.sp) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = accentRed,
                        unfocusedBorderColor = Color.White.copy(alpha = 0.15f),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = accentRed
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color(0xFF111111))
                ) {
                    groups.forEach { group ->
                        DropdownMenuItem(
                            text = { Text(group, color = Color.White, fontSize = 14.sp) },
                            onClick = {
                                selectedGroup = group
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            // --- SLIDER SECTION ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "EVENT COUNT",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = eventSliderValue.roundToInt().toString(),
                    color = accentRed,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Light
                )
            }

            Slider(
                value = eventSliderValue,
                onValueChange = { eventSliderValue = it },
                valueRange = 1f..5f,
                steps = 3,
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = accentRed,
                    inactiveTrackColor = Color.White.copy(alpha = 0.1f)
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            // --- CAPSULE BUTTON ---
            Surface(
                onClick = {
                    val count = eventSliderValue.roundToInt()
                    viewModel.group.value = selectedGroup
                    viewModel.events.value = listOf("$count Events Selected")
                    navController.navigate("confirm")
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
                        "PROCEED TO SUMMARY",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 3.sp
                    )
                }
            }
        }
    }
}