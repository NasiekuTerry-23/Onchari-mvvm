package net.ezra.ui.events

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.R
import net.ezra.navigation.ROUTE_ABOUT
import net.ezra.navigation.ROUTE_DASHBOARD
import net.ezra.navigation.ROUTE_EVENTS

import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_VIEW_PROD

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Calender(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    androidx.compose.material.Text(
                        text = "Calender",
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
                        navController.navigate(ROUTE_DASHBOARD)

                    }) {
                        Icon(
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
                            .height(50.dp)
                    )

                    Card(
                        colors = CardDefaults.cardColors(Color(color = 0xff2D2D2A)),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(1500.dp)
                            .fillMaxWidth()


                        // elevation = CardDefaults.cardElevation(10.dp),

                    ) {


                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(20.dp)
                        ) {
                            Spacer(
                                modifier = Modifier
                                    .height(40.dp)
                            )


                            androidx.compose.material.Text(
                                text = "SEP",
                                color = Color.LightGray,
                                fontSize = 45.sp,
                                fontWeight = FontWeight.Normal,

                                )
                            Spacer(
                                modifier = Modifier
                                    .width(10.dp)
                            )

                            Icon(
                                Icons.Filled.KeyboardArrowLeft, "homeIcon",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(45.dp),

                                )
                            Spacer(
                                modifier = Modifier
                                    .width(10.dp)
                            )

                            androidx.compose.material.Text(
                                text = "OCT",
                                color = Color.White,
                                fontSize = 45.sp,
                                fontWeight = FontWeight.Normal,

                                )

                            Spacer(
                                modifier = Modifier
                                    .width(10.dp)
                            )

                            Icon(
                                Icons.Filled.KeyboardArrowRight, "homeIcon",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(45.dp)


                            )

                            Spacer(
                                modifier = Modifier
                                    .width(10.dp)
                            )

                            Text(
                                text = "NOV",
                                color = Color.LightGray,
                                fontSize = 45.sp,
                                fontWeight = FontWeight.Normal,

                                )

                        }
                        Spacer(
                            modifier = Modifier
                                .height(30.dp)
                        )


                        Card(
                            colors = CardDefaults.cardColors(Color.Black),
                            shape = RoundedCornerShape(20.dp),

                            modifier = Modifier
                                .height(250.dp)
                                .padding(start = 14.dp)
                                .width(420.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Box(modifier = Modifier)

                            {

                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxSize(),
                                    painter = painterResource(id = R.drawable.eve2),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,

                                    )

                                Column {
                                    Row {
                                        androidx.compose.material3.Icon(
                                            painter = painterResource(id = R.drawable.ticket),
                                            contentDescription = "ticket Icon",
                                            tint = Color.White,
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .size(40.dp)
                                                .clickable { }
                                        )
                                        Text(
                                            text = " Tickets Available",
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            fontFamily = FontFamily.SansSerif,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.SemiBold,
                                            modifier = Modifier
                                                .clickable { }
                                                .padding(8.dp)

                                        )
                                        Spacer(
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        Column {
                                            Text(
                                                text = "       80%",
                                                fontSize = 30.sp,
                                                color = Color.White,
                                                fontFamily = FontFamily.SansSerif,
                                                fontStyle = FontStyle.Normal,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier
                                                    .clickable { }
                                                    .padding(8.dp)

                                            )
                                            Text(
                                                text = " Tickets Books",
                                                fontSize = 20.sp,
                                                color = Color.White,
                                                fontFamily = FontFamily.SansSerif,
                                                fontStyle = FontStyle.Normal,
                                                fontWeight = FontWeight.SemiBold,
                                                modifier = Modifier
                                                    .clickable { }

                                            )
                                        }

                                    }
                                    Text(
                                        text = "Amapiano Mix",
                                        fontSize = 30.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        modifier = Modifier
                                            .padding(5.dp)
                                    )
                                    Row {

                                        Text(
                                            text = " Night",
                                            fontSize = 30.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            // modifier = Modifier
                                            // .padding(5.dp),
                                        )

                                        Spacer(
                                            modifier = Modifier
                                                .width(100.dp)
                                        )

                                        var search by remember {
                                            mutableStateOf(TextFieldValue(""))
                                        }
                                        androidx.compose.material3.OutlinedTextField(
                                            value = search,

                                            leadingIcon = {
                                                androidx.compose.material3.Icon(
                                                    imageVector = Icons.Default.ArrowDropDown,
                                                    contentDescription = "Arrow Icon",
                                                    modifier = Modifier
                                                        .size(30.dp)
                                                )
                                            },
                                            colors = OutlinedTextFieldDefaults.colors(
                                                focusedContainerColor = Color.LightGray,
                                                unfocusedContainerColor = Color.LightGray,
                                            ),
                                            onValueChange = { search = it },
                                            label = { Text("Canivore Grounds", color = Color.Black) },
                                            modifier = Modifier
                                                .padding(2.dp)
                                                .height(60.dp)
                                                .width(230.dp),
                                            shape = RoundedCornerShape(30.dp),
                                        )

                                    }

                                    Spacer(
                                        modifier = Modifier
                                        //.padding(10.dp)
                                    )

                                    Row {

                                        androidx.compose.material3.Button(
                                            onClick = {


                                            },
                                            colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                                            shape = RoundedCornerShape(30.dp)
                                        ) {


                                            Text(
                                                text = "Buy Ticket",
                                                color = Color.White,
                                                fontSize = 15.sp,
                                                fontFamily = FontFamily.SansSerif,
                                                fontStyle = FontStyle.Normal,
                                                fontWeight = FontWeight.Bold,


                                                )
                                        }

                                        Spacer(
                                            modifier = Modifier
                                                .width(100.dp)
                                        )
                                        Text(
                                            text = "15 July, 2024",
                                            color = Color.White,
                                            fontSize = 25.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .padding(10.dp)


                                        )

                                    }

                                }

                            }
                        }

                        Spacer(
                            modifier = Modifier
                                .height(35.dp)
                        )

                        androidx.compose.material3.Card(
                            colors = CardDefaults.cardColors(Color.Black),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .height(250.dp)
                                .padding(start = 14.dp)
                                .width(420.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Box(modifier = Modifier)

                            {

                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxSize(),
                                    painter = painterResource(id = R.drawable.eve1),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,

                                    )

                                Column {
                                    Row {
                                        androidx.compose.material3.Icon(
                                            painter = painterResource(id = R.drawable.ticket),
                                            contentDescription = "ticket Icon",
                                            tint = Color.White,
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .size(40.dp)
                                                .clickable { }
                                        )
                                        Text(
                                            text = " Tickets Available",
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            fontFamily = FontFamily.SansSerif,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.SemiBold,
                                            modifier = Modifier
                                                .clickable { }
                                                .padding(8.dp)

                                        )
                                        Spacer(
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        Column {
                                            Text(
                                                text = "       60%",
                                                fontSize = 30.sp,
                                                color = Color.White,
                                                fontFamily = FontFamily.SansSerif,
                                                fontStyle = FontStyle.Normal,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier
                                                    .clickable { }
                                                    .padding(8.dp)

                                            )
                                            Text(
                                                text = " Tickets Books",
                                                fontSize = 20.sp,
                                                color = Color.White,
                                                fontFamily = FontFamily.SansSerif,
                                                fontStyle = FontStyle.Normal,
                                                fontWeight = FontWeight.SemiBold,
                                                modifier = Modifier
                                                    .clickable { }

                                            )
                                        }

                                    }
                                    Text(
                                        text = "Worship Kesha",
                                        fontSize = 30.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        modifier = Modifier
                                            .padding(5.dp)
                                    )
                                    Row {

                                        Text(
                                            text = " Night",
                                            fontSize = 30.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            // modifier = Modifier
                                            // .padding(5.dp),
                                        )

                                        Spacer(
                                            modifier = Modifier
                                                .width(100.dp)
                                        )

                                        var search by remember {
                                            mutableStateOf(TextFieldValue(""))
                                        }
                                        androidx.compose.material3.OutlinedTextField(
                                            value = search,

                                            leadingIcon = {
                                                androidx.compose.material3.Icon(
                                                    imageVector = Icons.Default.ArrowDropDown,
                                                    contentDescription = "Arrow Icon",
                                                    modifier = Modifier
                                                        .size(30.dp)
                                                )
                                            },
                                            colors = OutlinedTextFieldDefaults.colors(
                                                focusedContainerColor = Color.LightGray,
                                                unfocusedContainerColor = Color.LightGray,
                                            ),
                                            onValueChange = { search = it },
                                            label = {
                                                Text(
                                                    "KICC Grounds",
                                                    color = Color.Black
                                                )
                                            },
                                            modifier = Modifier
                                                .padding(2.dp)
                                                .height(60.dp)
                                                .width(230.dp),
                                            shape = RoundedCornerShape(30.dp),
                                        )

                                    }

                                    Spacer(
                                        modifier = Modifier
                                        //.padding(10.dp)
                                    )

                                    Row {

                                        androidx.compose.material3.Button(
                                            onClick = {
                                                // home.startActivity(Intent(home,CaleMenuActivity::class.java))

                                            },
                                            colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                                            shape = RoundedCornerShape(30.dp)
                                        ) {


                                            Text(
                                                text = "Buy Ticket",
                                                color = Color.White,
                                                fontSize = 15.sp,
                                                fontFamily = FontFamily.SansSerif,
                                                fontStyle = FontStyle.Normal,
                                                fontWeight = FontWeight.Bold,


                                                )
                                        }

                                        Spacer(
                                            modifier = Modifier
                                                .width(100.dp)
                                        )
                                        Text(
                                            text = "30 July, 2024",
                                            color = Color.White,
                                            fontSize = 25.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .padding(10.dp)


                                        )

                                    }

                                }
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .height(35.dp)
                        )

                        androidx.compose.material3.Card(
                            colors = CardDefaults.cardColors(Color.Black),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .height(250.dp)
                                .padding(start = 14.dp)
                                .width(420.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Box(modifier = Modifier)

                            {

                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxSize(),
                                    painter = painterResource(id = R.drawable.eve3),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,

                                    )

                                Column {
                                    Row {
                                        androidx.compose.material3.Icon(
                                            painter = painterResource(id = R.drawable.ticket),
                                            contentDescription = "ticket Icon",
                                            tint = Color.White,
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .size(40.dp)
                                                .clickable { }
                                        )
                                        Text(
                                            text = " Tickets Available",
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            fontFamily = FontFamily.SansSerif,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.SemiBold,
                                            modifier = Modifier
                                                .clickable { }
                                                .padding(8.dp)

                                        )
                                        Spacer(
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        Column {
                                            Text(
                                                text = "       75%",
                                                fontSize = 30.sp,
                                                color = Color.White,
                                                fontFamily = FontFamily.SansSerif,
                                                fontStyle = FontStyle.Normal,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier
                                                    .clickable { }
                                                    .padding(8.dp)

                                            )
                                            Text(
                                                text = " Tickets Books",
                                                fontSize = 20.sp,
                                                color = Color.White,
                                                fontFamily = FontFamily.SansSerif,
                                                fontStyle = FontStyle.Normal,
                                                fontWeight = FontWeight.SemiBold,
                                                modifier = Modifier
                                                    .clickable { }

                                            )
                                        }

                                    }
                                    Text(
                                        text = "Ballet Montery",
                                        fontSize = 30.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        modifier = Modifier
                                            .padding(5.dp)
                                    )
                                    Row {

                                        Text(
                                            text = " Performance",
                                            fontSize = 30.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            // modifier = Modifier
                                            // .padding(5.dp),
                                        )

                                        Spacer(
                                            modifier = Modifier
                                                .width(20.dp)
                                        )

                                        var search by remember {
                                            mutableStateOf(TextFieldValue(""))
                                        }
                                        androidx.compose.material3.OutlinedTextField(
                                            value = search,

                                            leadingIcon = {
                                                androidx.compose.material3.Icon(
                                                    imageVector = Icons.Default.ArrowDropDown,
                                                    contentDescription = "Arrow Icon",
                                                    modifier = Modifier
                                                        .size(30.dp)
                                                )
                                            },
                                            colors = OutlinedTextFieldDefaults.colors(
                                                focusedContainerColor = Color.LightGray,
                                                unfocusedContainerColor = Color.LightGray,
                                            ),
                                            onValueChange = { search = it },
                                            label = {
                                                Text(text = "Cinema Hall",
                                                    color = Color.Black
                                                )
                                            },
                                            modifier = Modifier
                                                .padding(2.dp)
                                                .height(60.dp)
                                                .width(230.dp),
                                            shape = RoundedCornerShape(30.dp),
                                        )

                                    }

                                    Spacer(
                                        modifier = Modifier
                                        //.padding(10.dp)
                                    )

                                    Row {

                                        androidx.compose.material3.Button(
                                            onClick = {
                                                // home.startActivity(Intent(home,CaleMenuActivity::class.java))

                                            },
                                            colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                                            shape = RoundedCornerShape(30.dp)
                                        ) {


                                            Text(
                                                text = "Buy Ticket",
                                                color = Color.White,
                                                fontSize = 15.sp,
                                                fontFamily = FontFamily.SansSerif,
                                                fontStyle = FontStyle.Normal,
                                                fontWeight = FontWeight.Bold,


                                                )
                                        }

                                        Spacer(
                                            modifier = Modifier
                                                .width(100.dp)
                                        )
                                        Text(
                                            text = "04 Aug., 2024",
                                            color = Color.White,
                                            fontSize = 25.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .padding(10.dp)


                                        )

                                    }

                                }
                            }
                        }

                        Spacer(
                            modifier = Modifier
                                .height(35.dp)
                        )

                        androidx.compose.material3.Card(
                            colors = CardDefaults.cardColors(Color.Black),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .height(250.dp)
                                .padding(start = 14.dp)
                                .width(420.dp),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Box(modifier = Modifier)

                            {

                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxSize(),
                                    painter = painterResource(id = R.drawable.eve4),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,

                                    )

                                Column {
                                    Row {
                                        androidx.compose.material3.Icon(
                                            painter = painterResource(id = R.drawable.ticket),
                                            contentDescription = "ticket Icon",
                                            tint = Color.White,
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .size(40.dp)
                                                .clickable { }
                                        )
                                        Text(
                                            text = " Tickets Available",
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            fontFamily = FontFamily.SansSerif,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.SemiBold,
                                            modifier = Modifier
                                                .clickable { }
                                                .padding(8.dp)

                                        )
                                        Spacer(
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        Column {
                                            Text(
                                                text = "       55%",
                                                fontSize = 30.sp,
                                                color = Color.White,
                                                fontFamily = FontFamily.SansSerif,
                                                fontStyle = FontStyle.Normal,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier
                                                    .clickable { }
                                                    .padding(8.dp)

                                            )
                                            Text(
                                                text = " Tickets Books",
                                                fontSize = 20.sp,
                                                color = Color.White,
                                                fontFamily = FontFamily.SansSerif,
                                                fontStyle = FontStyle.Normal,
                                                fontWeight = FontWeight.SemiBold,
                                                modifier = Modifier
                                                    .clickable { }

                                            )
                                        }

                                    }
                                    Text(
                                        text = "Kareoke",
                                        fontSize = 30.sp,
                                        fontFamily = FontFamily.SansSerif,
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        modifier = Modifier
                                            .padding(5.dp)
                                    )
                                    Row {

                                        Text(
                                            text = " Night",
                                            fontSize = 30.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            // modifier = Modifier
                                            // .padding(5.dp),
                                        )

                                        Spacer(
                                            modifier = Modifier
                                                .width(100.dp)
                                        )

                                        var search by remember {
                                            mutableStateOf(TextFieldValue(""))
                                        }
                                        androidx.compose.material3.OutlinedTextField(
                                            value = search,

                                            leadingIcon = {
                                                androidx.compose.material3.Icon(
                                                    imageVector = Icons.Default.ArrowDropDown,
                                                    contentDescription = "Arrow Icon",
                                                    modifier = Modifier
                                                        .size(30.dp)
                                                )
                                            },
                                            colors = OutlinedTextFieldDefaults.colors(
                                                focusedContainerColor = Color.LightGray,
                                                unfocusedContainerColor = Color.LightGray,
                                            ),
                                            onValueChange = { search = it },
                                            label = {
                                                Text(
                                                    "Salsa Club",
                                                    color = Color.Black
                                                )
                                            },
                                            modifier = Modifier
                                                .padding(2.dp)
                                                .height(60.dp)
                                                .width(230.dp),
                                            shape = RoundedCornerShape(30.dp),
                                        )

                                    }

                                    Spacer(
                                        modifier = Modifier
                                        //.padding(10.dp)
                                    )

                                    Row {

                                        androidx.compose.material3.Button(
                                            onClick = {
                                                // home.startActivity(Intent(home,CaleMenuActivity::class.java))

                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                Color(
                                                    color = 0xffe56a08
                                                )
                                            ),
                                            shape = RoundedCornerShape(30.dp)
                                        ) {


                                            Text(
                                                text = "Buy Ticket",
                                                color = Color.White,
                                                fontSize = 15.sp,
                                                fontFamily = FontFamily.SansSerif,
                                                fontStyle = FontStyle.Normal,
                                                fontWeight = FontWeight.Bold,


                                                )
                                        }

                                        Spacer(
                                            modifier = Modifier
                                                .width(100.dp)
                                        )
                                        Text(
                                            text = "10 Aug., 2024",
                                            color = Color.White,
                                            fontSize = 25.sp,
                                            fontFamily = FontFamily.SansSerif,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .padding(10.dp)


                                        )

                                    }

                                }


                            }


                        }


                    }

                }
            }

        })
}

