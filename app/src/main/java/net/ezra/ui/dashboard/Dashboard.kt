package net.ezra.ui.dashboard



import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import net.ezra.navigation.ROUTE_LOGIN

import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import net.ezra.R
import net.ezra.navigation.ROUTE_ADD_STUDENTS
import net.ezra.navigation.ROUTE_DASHBOARD
import net.ezra.navigation.ROUTE_EVENTS
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_NEWEVENTS


private var progressDialog: ProgressDialog? = null
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(navController: NavHostController)  {
    var school by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    val currentUser = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()
    var user: User? by remember { mutableStateOf(null) }
    var isLoading by remember { mutableStateOf(true) }
    var studentCount by remember { mutableIntStateOf(0) }
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }

    val firestores = Firebase.firestore


    val context = LocalContext.current

    BackHandler {
        navController.popBackStack()

    }


    // Fetch user details from Firestore
    LaunchedEffect(key1 = currentUser?.uid) {
        if (currentUser != null) {
            val userDocRef = firestore.collection("users").document(currentUser.uid)
            userDocRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        user = document.toObject<User>()
                    }
                    isLoading = false
                }
                .addOnFailureListener { e ->
                    // Handle failure
                    isLoading = false
                }
        }
    }

    LaunchedEffect(Unit) {
        firestores.collection("Students")
            .get()
            .addOnSuccessListener { result ->
                studentCount = result.size()
            }
            .addOnFailureListener { exception ->
                // Handle failures
            }
    }

    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFF677572), Color(0xFFff9448)), // Black to Orange
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
            .padding(20.dp)

    ) {

        item {
            Column {

                Row(
                    modifier = Modifier
                        .padding(16.dp),

                    ) {
                    androidx.compose.material3.Icon(
                        painter = painterResource(id = R.drawable.connect),
                        contentDescription = "connect Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(4.dp)
                            .size(40.dp)
                            .clickable { }
                    )

                    androidx.compose.material3.Text(
                        text = "Connectify",
                        color = Color.White,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(10.dp)

                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(30.dp)
                )
                Row {
                    Column {
                        androidx.compose.material3.Text(
                            text = " Tuesday.",
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier

                        )
                        androidx.compose.material3.Text(
                            text = " 12 Jul",
                            fontSize = 50.sp,
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
                        Image(
                            painter = painterResource(id = R.drawable.pers),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .width(70.dp)
                                .height(70.dp)
                                .clip(CircleShape)
                        )

                        Spacer(
                            modifier = Modifier
                                .height(25.dp)
                        )
                        androidx.compose.material3.Text(
                            text = "Kimani, NKR",
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
                Row {
                    androidx.compose.material3.Button(

                        onClick = {


                        },
                        colors = ButtonDefaults.buttonColors(Color.Black),
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier
                            .padding(10.dp)
                    ) {

                        androidx.compose.material3.Text(
                            text = "Today",
                            fontSize = 15.sp,
                            color = Color.White,

                            )

                    }
                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                    )

                    androidx.compose.material3.OutlinedButton(

                        onClick = {

                            navController.navigate(ROUTE_EVENTS) {
                                popUpTo(ROUTE_EVENTS) { inclusive = true }
                            }


                        },
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier
                            .padding(10.dp)
                    ) {

                        androidx.compose.material3.Text(
                            text = "Calender",
                            fontSize = 15.sp,
                            color = Color.White,

                            )

                    }
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                    androidx.compose.material3.Button(
                        onClick = {
                            navController.navigate(ROUTE_NEWEVENTS) {
                                popUpTo(ROUTE_NEWEVENTS) { inclusive = true }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                        shape = RoundedCornerShape(70.dp),
                    ) {
                        androidx.compose.material3.Text(
                            text = "+",
                            color = Color.White,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                Row {
                    var search by remember {
                        mutableStateOf(TextFieldValue(""))
                    }
                    androidx.compose.material3.OutlinedTextField(
                        value = search,

                        leadingIcon = {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "searchIcon",
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(30.dp)
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.LightGray,
                            unfocusedContainerColor = Color.LightGray,
                        ),
                        onValueChange = { search = it },
                        label = {
                            androidx.compose.material3.Text(
                                "Search for Event",
                                color = Color.Black
                            )
                        },
                        modifier = Modifier
                            .padding(10.dp)
                            .height(60.dp)
                            .width(300.dp),
                        shape = RoundedCornerShape(30.dp),
                    )
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                    androidx.compose.material3.Icon(
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = "Filter",
                        tint = Color.Black,
                        modifier = Modifier
                            .border(1.dp, Color.LightGray, RoundedCornerShape(30.dp))
                            .size(60.dp)
                            //.background(Color.LightGray)
                            .align(Alignment.CenterVertically)
                            .clickable { }
                            .padding(10.dp),
                    )
                }

                androidx.compose.material3.Text(
                    text = "  Visit the events ",
                    fontSize = 40.sp,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,

                    )
                androidx.compose.material3.Text(
                    text = "  of your interest",
                    fontSize = 40.sp,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal,

                    )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                Row {
                    androidx.compose.material3.Text(
                        text = "  All ",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .clickable { }

                    )
                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                    )
                    androidx.compose.material3.Text(
                        text = " Music",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .clickable { }

                    )
                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                    )
                    androidx.compose.material3.Text(
                        text = " Sports",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .clickable { }
                    )
                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                    )
                    androidx.compose.material3.Text(
                        text = " Hobbies",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .clickable { }

                    )
                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                    )
                    androidx.compose.material3.Text(
                        text = " Games",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .clickable { }

                    )

                }
                Spacer(
                    modifier = Modifier
                        .height(15.dp)
                )

                androidx.compose.material3.Card(
                    colors = CardDefaults.cardColors(Color.Black),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
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
                                androidx.compose.material3.Text(
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
                                    androidx.compose.material3.Text(
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
                                    androidx.compose.material3.Text(
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
                            androidx.compose.material3.Text(
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

                                androidx.compose.material3.Text(
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
                                        androidx.compose.material3.Text(
                                            "Canivore Grounds",
                                            color = Color.Black
                                        )
                                    },
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .height(60.dp)
                                        .width(214.dp),
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
                                        navController.navigate(ROUTE_EVENTS) {
                                            popUpTo(ROUTE_EVENTS) { inclusive = true }
                                        }


                                    },
                                    colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                                    shape = RoundedCornerShape(25.dp),
                                    modifier = Modifier
                                        .padding(7.dp)


                                ) {


                                    androidx.compose.material3.Text(
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
                                androidx.compose.material3.Text(
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
                        .height(15.dp)
                )

                androidx.compose.material3.Card(
                    colors = CardDefaults.cardColors(Color.Black),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
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
                                androidx.compose.material3.Text(
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
                                    androidx.compose.material3.Text(
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
                                    androidx.compose.material3.Text(
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
                            androidx.compose.material3.Text(
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

                                androidx.compose.material3.Text(
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
                                        androidx.compose.material3.Text(
                                            "KICC Grounds",
                                            color = Color.Black
                                        )
                                    },
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .height(60.dp)
                                        .width(214.dp),
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
                                        // home.startActivity(Intent(home,CalenderActivity::class.java))

                                    },
                                    colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                                    shape = RoundedCornerShape(30.dp)
                                ) {


                                    androidx.compose.material3.Text(
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
                                androidx.compose.material3.Text(
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

            }


        }
    }
}






//            LazyVerticalGrid(
//                columns = GridCells.Fixed(2),
//                modifier = Modifier.padding(16.dp)
//            ) {
//                items(dashboardItems) { item ->
//                    DashboardItem(item)
//                }
//            }
//
//
////                            Dashboard ends here
//
//
//        }
//
//        })
//
//}











@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardItem(item: DashboardItemData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        backgroundColor = Color.White,
        onClick = item.onClick
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = "Dashboard Icon",
                tint = Color.Black,
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black
            )
            // Add a badge if the badge count is greater than 0
            if (item.badgeCount > 0) {
                Badge(count = item.badgeCount)
            }
        }
    }
}
@Composable
fun Badge(count: Int) {
    Box(
        modifier = Modifier
            .padding(start = 8.dp)
            .size(20.dp)
            .clip(CircleShape)
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.caption,
            color = Color.White
        )
    }
}
data class DashboardItemData(
    val title: String,
    val icon: ImageVector,
    val badgeCount: Int,
    val onClick: () -> Unit
)
data class User(
    val userId: String = "",
    val school: String = "",
    val name: String = ""
)

fun saveUserDetails(user: User, param: (Any) -> Unit) {
    val firestore = FirebaseFirestore.getInstance()
    firestore.collection("users").document(user.userId)
        .set(user, SetOptions.merge())
        .addOnSuccessListener {

            progressDialog?.dismiss()
            // Success message or navigation
        }
        .addOnFailureListener {

            progressDialog?.dismiss()
            // Handle failure
        }
}
