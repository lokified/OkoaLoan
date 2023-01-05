package com.loki.okoaloan.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.loki.okoaloan.presentation.common.ButtonSection
import com.loki.okoaloan.presentation.common.TopBar
import com.loki.okoaloan.presentation.navigation.Screens

@Composable
fun HomeScreen(
    navController: NavController
) {

    Scaffold(topBar = {
        TopBar(title = "Home")
    }) {

        Box(modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxSize()) {

            Column {
                HomeTopSection(
                    navController = navController,
                    modifier = Modifier.padding(16.dp)
                )

                HomeMenuSection(
                    navController = navController,
                    modifier = Modifier.padding(16.dp)
                )
            }

        }

    }
}


@Composable
fun HomeTopSection(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Available Loan Amount (KES)",
                color = Color.LightGray,
                fontSize = 14.sp
            )

            Text(
                text = "50,000",
                color = Color.Black,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 24.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Timer,
                    contentDescription = null
                )

                Text(
                    text = "Loan Period up to 60 days",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }

            ButtonSection(label = "Get My Loan") {
                navController.navigate(Screens.LoanApplicationScreen.route)
            }
        }
    }
}

@Composable
fun HomeMenuSection(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {

        Column {
            HomeMenuRow(
                colOneTitle = "Loan History",
                colTwoTitle = "Change Password",
                colOneIcon = Icons.Default.History,
                colTwoIcon = Icons.Default.Password,
                onColOneClick = {
                    navController.navigate(Screens.LoanHistoryScreen.route)
                },
                onColTwoClick = {
                    navController.navigate(Screens.ChangePasswordScreen.route)
                }
            )

            HomeMenuRow(
                colOneTitle = "FAQ",
                colTwoTitle = "Log Out",
                colOneIcon = Icons.Default.Newspaper,
                colTwoIcon = Icons.Default.Logout,
                onColOneClick = {

                },
                onColTwoClick = {

                }
            )
        }
    }
}

@Composable
fun HomeMenuRow(
    colOneTitle: String,
    colTwoTitle: String,
    colOneIcon: ImageVector,
    colTwoIcon: ImageVector,
    onColOneClick: () -> Unit,
    onColTwoClick: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {

        MenuItem(title = colOneTitle, icon = colOneIcon, onItemClick = onColOneClick)
        Spacer(modifier = Modifier.width(16.dp))
        MenuItem(title = colTwoTitle, icon = colTwoIcon, onItemClick = onColTwoClick)

    }
}


@Composable
fun MenuItem(
    title: String,
    icon: ImageVector,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(10.dp)
            )
            .height(100.dp)
            .width(100.dp)
            .clickable {
                onItemClick()
            }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
            Text(text = title, textAlign = TextAlign.Center)
        }
    }
}