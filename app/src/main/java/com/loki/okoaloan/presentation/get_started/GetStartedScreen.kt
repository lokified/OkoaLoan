package com.loki.okoaloan.presentation.get_started

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.okoaloan.R
import com.loki.okoaloan.presentation.navigation.Screens

@Composable
fun GetStartedScreen(
    openAndPopUp: (String, String) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        GetStartedTopSection()

        GetStartedBottomSection(openAndPopUp = openAndPopUp)
    }

}

@Composable
fun GetStartedTopSection( ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.loan),
                contentDescription = null,
                modifier = Modifier.padding(top = 30.dp)
                    .size(40.dp)
            )

            Text(
                text = "Okoa Loan",
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "SMART LOANS",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "FOR SMART PEOPLE",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

}

@Composable
fun GetStartedBottomSection(
    openAndPopUp: (String, String) -> Unit
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {
                openAndPopUp(Screens.AuthScreen.route, Screens.GetStartedScreen.route)
            },
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .width(200.dp)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
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
            fontSize = 11.sp,
            modifier = Modifier.padding(bottom = 8.dp, start = 16.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
