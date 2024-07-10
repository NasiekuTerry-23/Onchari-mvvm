package net.ezra.ui.events

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.telecom.Call.Details
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import net.ezra.R
import net.ezra.navigation.ROUTE_ABOUT

import net.ezra.navigation.ROUTE_EVENTS
import net.ezra.navigation.ROUTE_DASHBOARD
import net.ezra.navigation.ROUTE_VIEWEVE

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavHostController){

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    androidx.compose.material.Text(
                        text = "Detail Event",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 30.sp,
                        color = Color.White

                    )

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF677572),
                    titleContentColor = Color.White,
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_VIEWEVE)
                    }) {
                        androidx.compose.material.Icon(
                            Icons.Filled.KeyboardArrowLeft, "homeIcon",
                            tint = Color.White,
                            modifier = Modifier
                                .size(45.dp)

                        )
                    }
                },

                )
        }, content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF677572)),


                ) {
                item {

                    Spacer(
                        modifier = Modifier
                            .height(100.dp)
                    )

                    Card(
                        colors = CardDefaults.cardColors(Color(color = 0xff2D2D2A)),
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier
                            .height(850.dp)
                            .fillMaxWidth()


                        // elevation = CardDefaults.cardElevation(10.dp),

                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .clip(RoundedCornerShape(16.dp))
                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxSize(),
                                painter = painterResource(id = R.drawable.eventa),
                                contentDescription = "Event Image",
                                contentScale = ContentScale.Crop,

                                )
                            Row(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(8.dp)
                            ) {
                                IconButton(
                                    onClick = { /* Handle share */ },
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(Color.Transparent.copy(alpha = 0.5f))
                                ) {
                                    androidx.compose.material.Icon(
                                        painter = painterResource(id = R.drawable.favo),
                                        contentDescription = "Share",
                                        tint = Color.White
                                    )
                                }
                                Spacer(
                                    modifier = Modifier
                                        .width(8.dp)
                                )
                                IconButton(
                                    onClick = { /* Handle favorite */ },
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(Color.Transparent.copy(alpha = 0.5f))
                                ) {
                                    androidx.compose.material.Icon(
                                        painter = painterResource(id = R.drawable.share),
                                        contentDescription = "Favorite",
                                        tint = Color.White
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(start = 10.dp)
                            ) {

                                Button(

                                    onClick = {


                                    },
                                    colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                                    shape = RoundedCornerShape(30.dp),

                                    ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.cale),
                                        contentDescription = "Calender Image",
                                        modifier = Modifier
                                            .size(20.dp)

                                    )

                                    Text(
                                        text = "13-14 Jul",
                                        fontSize = 15.sp,
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
                                    colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                                    shape = RoundedCornerShape(30.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.loc),
                                        contentDescription = "Location Image",
                                        modifier = Modifier
                                            .size(20.dp)

                                    )

                                    Text(
                                        text = "Canivore Grounds",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.Black,
                                    )
                                }
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .height(30.dp)
                        )

                        Row {
                            Column {
                                Text(
                                    text = " Amapiano Mix",
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier

                                )
                                Text(
                                    text = " Night",
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier

                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .weight(1f)
                            )
                            Column {
                                Text(
                                    text = "Starts From",
                                    fontSize = 25.sp,
                                    color = Color.White
                                )

                                Spacer(
                                    modifier = Modifier
                                        .height(15.dp)
                                )
                                Text(
                                    text = "Ksh. 600",
                                    fontSize = 35.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Spacer(
                            modifier = Modifier
                                .height(30.dp)
                        )
                        Text(
                            text = "Get ready to dance, connect, and create unforgettable memories. The night is young, the dance floor is calling, and the Amapiano vibes are here to welcome you on  13-14 July, at Canivore Grounds.",
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(start = 10.dp),
                            color = Color.White
                        )

                        Spacer(
                            modifier = Modifier
                                .height(30.dp)
                        )

                        Row {
                            Text(
                                text = " 3k+ people already booked",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.White,
                                modifier = Modifier

                            )
                            Spacer(
                                modifier = Modifier
                                    .width(40.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.pers),
                                contentDescription = "Profile Image",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.White, CircleShape)
                                    .padding(2.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(-20.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.pers1),
                                contentDescription = "Profile Image",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.White, CircleShape)
                                    .padding(2.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(-20.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.pers2),
                                contentDescription = "Profile Image",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.White, CircleShape)
                                    .padding(2.dp)
                            )

                        }

                        Spacer(
                            modifier = Modifier
                                .height(30.dp)
                        )
                        Button(
                            onClick = {
                                navController.navigate(ROUTE_VIEWEVE) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffe56a08)),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            //.padding(16.dp),
                            shape = RoundedCornerShape(30.dp)
                        ) {
                            Text(
                                text = "BOOK TICKET",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }


                }
            }

        })
}





