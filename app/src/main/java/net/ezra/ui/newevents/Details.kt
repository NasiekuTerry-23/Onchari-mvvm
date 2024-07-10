package net.ezra.ui.newevents

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.ezra.navigation.ROUTE_VIEWEVE

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(navController: NavController, productId: String) {

    var product by remember { mutableStateOf<Product?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(productId) {
        fetchProduct(
            productId,
            onSuccess = { fetchedProduct ->
                product = fetchedProduct
                isLoading = false
            },
            onError = { error ->
                errorMessage = error.message
                isLoading = false
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    // Display the product name if available
                    Text(
                        text = product?.name ?: "Event Details",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_VIEWEVE)
                    }) {
                        Icon(
                            Icons.Filled.KeyboardArrowLeft, "homeIcon",
                            tint = Color.White,
                            modifier = Modifier
                                .size(45.dp)

                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF677572),
                    titleContentColor = Color.White,
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFF677572),
                                Color(0xFFff9448)
                            ), // Black to Orange
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    ),
            ) {
                product?.let {
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(it.imageUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .size(400.dp)
                                .padding(start = 35.dp)
                                .clip(RoundedCornerShape(15.dp))
                              //  .align(Alignment.CenterHorizontally)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.h5
                        )
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.body1
                        )
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                        Text(
                            text = it.date,
                            style = MaterialTheme.typography.h6
                        )
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                        Text(
                            text = it.location,
                            style = MaterialTheme.typography.subtitle1
                        )
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                        Text(
                            text = "Price: ${it.price}",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Spacer(
                            modifier = Modifier
                                .height(25.dp)
                        )

                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(
                                    0xffe56a08
                                )
                            ),
                            onClick = {
                                navController.navigate(ROUTE_VIEWEVE)
                            },
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            shape = RoundedCornerShape(30.dp),
                        ) {
                            androidx.compose.material3.Text(
                                text = "Book Ticket",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                    }
                    //functionalities to update the details of the event
                    Row {

                    }
                }
            }
        }
    )
}


suspend fun fetchProduct(productId: String): Product? {
    val db = FirebaseFirestore.getInstance()
    val eventsCollection = db.collection("events")

    return try {
        val documentSnapshot = eventsCollection.document(productId).get().await()
        if (documentSnapshot.exists()) {
            val productData = documentSnapshot.data ?: return null
            Product(
                id = productId,
                name = productData["name"] as String,
                description = productData["description"] as String,
                date = productData["date"] as String,
                location = productData["location"] as String,
                price = productData["price"] as String,

                // Add other product properties here
            )
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}


