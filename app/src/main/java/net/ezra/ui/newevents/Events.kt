package net.ezra.ui.newevents

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_NEWEVENTS
import net.ezra.navigation.ROUTE_EVEDETAIL

data class Product(
    var id: String = "",
    val name: String = "",
    val description: String = "",
    val date: String = "",
    val location: String = "",
    val price: String = "",
    var imageUrl: String = ""
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navController: NavController, products: List<Product>) {
    var isLoading by remember { mutableStateOf(true) }
    var productList by remember { mutableStateOf(emptyList<Product>()) }
    var displayedProductCount by remember { mutableStateOf(10) }
    var progress by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        fetchProducts(
            onSuccess = { fetchedProducts ->
                productList = fetchedProducts
                isLoading = false
            },
            onError = {
                // Handle error
                isLoading = false
            },
            onProgress = { value ->
                progress = value
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    androidx.compose.material3.Text(
                            text = "Events",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_NEWEVENTS)
                    }) {
                        androidx.compose.material3.Icon(
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
                    )
            ) {
                if (isLoading) {
                    // Progress indicator
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.CircularProgressIndicator(progress = progress / 100f)
                        androidx.compose.material3.Text(
                            text = "Loading Events... $progress%",
                            fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(start= 20.dp),
                                    color = Color.White
                        )
                    }
                } else {
                    if (productList.isEmpty()) {
                        // No products found
                        androidx.compose.material3.Text(
                            text = "No products found",
                            fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(start = 100.dp)
                                        .padding(top = 50.dp)
                        )
                    } else {
                        // Products list
                        LazyVerticalGrid(columns = GridCells.Fixed(1)) {
                            items(productList.take(displayedProductCount)) { product ->
                                ProductListItem(product) {
                                    navController.navigate("productDetail/${product.id}")
                                }
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .height(16.dp))

                        // Load More Button
                        if (displayedProductCount < productList.size) {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(
                                                 0xffe56a08
                                            )
                                ),
                                onClick = { displayedProductCount += 5 },
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                        shape = RoundedCornerShape(30.dp),
                            ) {
                                androidx.compose.material3.Text(
                                    text = "Load More",
                                    color = Color.White,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun ProductListItem(product: Product, onItemClick: (String) -> Unit) {
    androidx.compose.material3.Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
            .clickable { onItemClick(product.id) }
    ) {
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            // Product Image
            Image(
                painter = rememberImagePainter(product.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(
                modifier = Modifier
                    .width(20.dp)
            )

            // Product Details
            Column {
                androidx.compose.material3.Text(text = product.name)
                androidx.compose.material3.Text(text = product.description)
                androidx.compose.material3.Text(text = product.date)
                androidx.compose.material3.Text(text = product.location)
                androidx.compose.material3.Text(text = "Price: ${product.price}")
            }
        }
    }
}

private suspend fun fetchProducts(
    onSuccess: (List<Product>) -> Unit,
    onError: (Exception) -> Unit,
    onProgress: (Int) -> Unit
) {
    val firestore = Firebase.firestore
    try {
        val snapshot = firestore.collection("events").get().await()
        val productList = snapshot.documents.mapNotNull { doc ->
            val product = doc.toObject<Product>()
            product?.id = doc.id
            product
        }
        onSuccess(productList)
    } catch (e: Exception) {
        onError(e)
    }
}

suspend fun fetchProduct(productId: String, onSuccess: (Product?) -> Unit, onError: (Exception) -> Unit) {
    val firestore = Firebase.firestore
    try {
        val docRef = firestore.collection("events").document(productId)
        val snapshot = docRef.get().await()
        val product = snapshot.toObject<Product>()
        onSuccess(product)
    } catch (e: Exception) {
        onError(e)
    }
}
