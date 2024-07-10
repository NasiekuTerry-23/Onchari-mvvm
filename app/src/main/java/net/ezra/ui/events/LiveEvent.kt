package net.ezra.ui.events


import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.R
import net.ezra.navigation.ROUTE_ABOUT
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_EVENTS
import net.ezra.navigation.ROUTE_VIEWEVE

@Composable
fun LiveEventScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.mix),
            contentDescription = null,
            contentScale = ContentScale.Crop,

            )
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {


            Row(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {

                Button(

                    onClick = {


                    },
                    colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                    shape = RoundedCornerShape(30.dp),

                    ) {

                    Text(
                        text = "Live",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                    )
                }
                Spacer(
                    modifier = Modifier
                        .width(30.dp)
                )

                Button(

                    onClick = {


                    },
                    colors = ButtonDefaults.buttonColors(Color(color = 0xff2D2D2A)),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.eye),
                        contentDescription = "Location Image",
                        tint = Color.White,
                        modifier = Modifier
                            .size(25.dp),
                    )
                    Spacer(
                        modifier = Modifier
                            .width(2.dp)
                    )

                    Text(
                        text = "10k",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(200.dp)
            )

            Text(
                text = "The Dj",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = " is Now Rocking",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = " The Stage",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
            Text(
                text = "Watch to know more",
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = " about the event",
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(
                modifier = Modifier
                    .height(350.dp)
            )

            Row {

                IconButton(
                    onClick = { /* Handle play button click */ },
                    modifier = Modifier
                        .padding(start = 35.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.7f))
                        .size(65.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.favo),
                        contentDescription = "Share",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(40.dp)

                    )
                }

                Spacer(
                    modifier = Modifier
                        .width(30.dp)
                )

                IconButton(
                    onClick = { /* Handle play button click */ },
                    modifier = Modifier
                        .padding(start = 35.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.7f))
                        .size(100.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.play),
                        contentDescription = "Play",
                        tint = Color.Black,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(ROUTE_VIEWEVE)
                            }
                    )
                }

                Spacer(
                    modifier = Modifier
                        .width(30.dp)
                )

                IconButton(
                    onClick = { /* Handle play button click */ },
                    modifier = Modifier
                        .padding(start = 35.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.7f))
                        .size(65.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.share),
                        contentDescription = "Share",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(40.dp)

                    )
                }

            }
        }
    }

}