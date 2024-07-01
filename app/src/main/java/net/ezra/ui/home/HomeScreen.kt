package net.ezra.ui.home






import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import net.ezra.R
import net.ezra.navigation.ROUTE_ABOUT
import net.ezra.navigation.ROUTE_ADD_PRODUCT
import net.ezra.navigation.ROUTE_ADD_STUDENTS
import net.ezra.navigation.ROUTE_DASHBOARD
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_SEARCH
import net.ezra.navigation.ROUTE_VIEW_PROD
import net.ezra.navigation.ROUTE_VIEW_STUDENTS


data class Screen(val title: String, val icon: Int)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceAsColor")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    var isDrawerOpen by remember { mutableStateOf(false) }

    val callLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { _ ->

        }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE67E22)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Spacer(
            modifier = Modifier
                .height(100.dp)
        )
        androidx.compose.material3.Card(
            colors = CardDefaults.cardColors(Color.Black),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .height(900.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(10.dp)
        )
        {
            Spacer(
                modifier = Modifier
                    .height(35.dp))
            Row {

                androidx.compose.material3.Icon(
                    painter = painterResource(id = R.drawable.connect),
                    contentDescription = "connect Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(55.dp)
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
            Column {
                androidx.compose.material3.Text(
                    text = "  Connect, Engage,",
                    color = Color(0xFFFFA500),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()

                )
                androidx.compose.material3.Text(
                    text = "  Make Memories",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()

                )
                androidx.compose.material3.Text(
                    text = "  Together",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()

                )
            }

            Spacer(
                modifier = Modifier
                    .height(60.dp)
            )

            LazyRow {
                item{
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly

                    ){
                        val imageModifier = Modifier
                            .width(250.dp)
                            .height(300.dp)
                            .clip(RoundedCornerShape(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.event1),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = imageModifier
                        )
                        Spacer(
                            modifier = Modifier
                                .width(15.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.event2),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = imageModifier
                        )
                        Spacer(
                            modifier = Modifier
                                .width(15.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.event3),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = imageModifier
                        )
                    }

                }
            }
            Spacer(
                modifier = Modifier
                    .height(50.dp)
                    .width(40.dp)
            )
            androidx.compose.material3.Button(
                onClick = {
                    navController.navigate(ROUTE_LOGIN) { popUpTo(ROUTE_HOME) { inclusive = true }
                     }
                },
                colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                androidx.compose.material3.Text(
                    text = "Get Started",
                    color = Color.White,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.SemiBold,

                    )
            }


        }

    }
}



