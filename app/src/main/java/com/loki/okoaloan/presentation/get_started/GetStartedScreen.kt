package com.loki.okoaloan.presentation.get_started

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.loki.okoaloan.R
import com.loki.okoaloan.presentation.Screens

@Composable
fun GetStartedScreen(
    navController: NavController
) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        GetStartedTopSection()

        GetStartedBottomSection(navController = navController)
    }

}

@Composable
fun GetStartedTopSection( ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.loan),
                contentDescription = null,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "Okoa Loan",
                fontSize = 40.sp,
                color = Color.Black
            )

            Text(
                text = "SMART LOANS",
                fontSize = 20.sp,
                color = Color.Black
            )

            Text(
                text = "FOR SMART PEOPLE",
                fontSize = 20.sp,
                color = Color.Black
            )
        }

}

@Composable
fun GetStartedBottomSection(
    navController: NavController
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {
                navController.navigate(Screens.WelcomeScreen.route)
            },
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .width(200.dp)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.Black
            )
        )  {
            Text(
                text = "GET STARTED",
                fontSize = 15.sp
            )
        }

        Text(
            text = "By signing up you are agreeing to our Terms & Conditions, Terms of Use and Privacy Policy",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
        )
    }
}