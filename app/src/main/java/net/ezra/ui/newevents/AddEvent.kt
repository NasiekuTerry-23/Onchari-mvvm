package net.ezra.ui.newevents

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Space
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import net.ezra.navigation.ROUTE_ADD_PRODUCT
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_VIEW_PROD
import net.ezra.navigation.ROUTE_EVENTS



import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventScreen(navController: NavController, onEventAdded: () -> Unit) {
    var contentHeadline by remember { mutableStateOf("") }
    var contentDescription by remember { mutableStateOf("") }
    var eventImageUri by remember { mutableStateOf<Uri?>(null) }

    // Track if fields are empty
    var contentHeadlineError by remember { mutableStateOf(false) }
    var contentDescriptionError by remember { mutableStateOf(false) }
    var eventImageError by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            eventImageUri = it
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Craft Event",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_VIEW_PROD)
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
                    androidx.compose.material3.Card(
                        colors = CardDefaults.cardColors(Color(color = 0xff2D2D2A)),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(1500.dp)
                            .fillMaxWidth()


                    ) {

                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(40.dp),
                        ) {
                            Spacer(
                                modifier = Modifier
                                    .height(50.dp)
                            )

                            Text(
                                text = "Detail Event",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(start = 100.dp)
                                //textAlign = Alignment.Center
                            )

                        }
                        Text(
                            text = "1. Define Your Event",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 40.dp)
                            //textAlign = Alignment.Center
                        )
                        if (eventImageUri != null) {
                            // Display selected image
                            Image(
                                painter = rememberImagePainter(eventImageUri), // Using rememberImagePainter with Uri
                                contentDescription = null,
                                modifier = Modifier
                                    .width(300.dp)
                                    .height(250.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                        } else {
                            // Display placeholder if no image selected
                            Box(
                                modifier = Modifier
                                    .width(300.dp)
                                    .height(250.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("No Image Selected",
                                    modifier = Modifier
                                        .padding(8.dp),
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold

                                )
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                        androidx.compose.material3.Button(onClick = {
                            launcher.launch("image/*")
                        },
                            colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                //.padding(start = 100.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                "Select Image",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                        TextField(
                            value = contentHeadline,
                            onValueChange = { contentHeadline = it },

                            label = { Text(
                                "Content Headline",
                                color = Color.LightGray
                            ) },
                            modifier = Modifier.fillMaxWidth(),

                            )
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                        TextField(
                            value = contentDescription,
                            onValueChange = { contentDescription = it },
                            label = { Text(
                                "Content Description",
                                color = Color.LightGray
                            ) },
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )


                        if (contentHeadlineError) {
                            Text("Content Headline is required", color = Color.Red)
                        }
                        if (contentDescriptionError) {
                            Text("Content Description is required", color = Color.Red)
                        }
                        if (eventImageError) {
                            Text("Event Image is required", color = Color.Red)
                        }

                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )

                        Text(
                            text = "Event Keywords",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 40.dp)

                        )
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )

                        // Button to add product
                        Row {

                            androidx.compose.material3.Button(
                                onClick = {
                                    // Reset error flags
                                    contentHeadlineError = contentHeadline.isBlank()
                                    contentDescriptionError = contentDescription.isBlank()
                                    eventImageError = eventImageUri == null

                                    // Add product if all fields are filled
                                    if (!contentHeadlineError && !contentDescriptionError && !eventImageError) {
                                        addEventToFirestore(
                                            navController,
                                            onEventAdded,
                                            contentHeadline,
                                            contentDescription,
                                            eventImageUri
                                        )
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    "+",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )

                            }
                            Spacer(
                                modifier = Modifier
                                    .width(15.dp)
                            )

                            androidx.compose.material3.Button(onClick = {
                                launcher.launch("image/*")
                            },
                                colors = ButtonDefaults.buttonColors(Color.Gray),

                                ) {
                                Text(
                                    "View",
                                    color = Color.Black,
                                    fontSize = 20.sp

                                )
                            }

                            Spacer(
                                modifier = Modifier
                                    .width(15.dp)
                            )

                            androidx.compose.material3.Button(onClick = {
                                launcher.launch("image/*")
                            },
                                colors = ButtonDefaults.buttonColors(Color.Gray),
                            ) {
                                Text(
                                    "Update",
                                    color = Color.Black,
                                    fontSize = 20.sp

                                    )
                            }

                            Spacer(
                                modifier = Modifier
                                    .width(15.dp)
                            )

                            androidx.compose.material3.Button(onClick = {
                                navController.navigate(ROUTE_EVENTS) {
                                    popUpTo(ROUTE_EVENTS) { inclusive = true }
                                }
                            },
                                colors = ButtonDefaults.buttonColors(Color.Gray),
                            ) {
                                Text(
                                    "Live",
                                    color = Color.Black,
                                    fontSize = 20.sp

                                    )
                            }
                        }
                    }
                }
            }
        }
    )
}

private fun addEventToFirestore(navController: NavController, onEventAdded: () -> Unit, contentHeadline: String, contentDescription: String, eventImageUri: Uri?) {
    if (contentHeadline.isEmpty() || contentDescription.isEmpty() || eventImageUri == null) {
        // Validate input fields
        return
    }

    val eventId = UUID.randomUUID().toString()

    val firestore = Firebase.firestore
    val eventData = hashMapOf(
        "name" to contentHeadline,
        "description" to contentDescription,
        "imageUrl" to ""
    )

    firestore.collection("events").document(eventId)
        .set(eventData)
        .addOnSuccessListener {
            uploadImageToStorage(eventId, eventImageUri) { imageUrl ->
                firestore.collection("events").document(eventId)
                    .update("imageUrl", imageUrl)
                    .addOnSuccessListener {
                        // Display toast message
                        Toast.makeText(
                            navController.context,
                            "Events added successfully!",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Navigate to another screen
                        navController.navigate(ROUTE_HOME)

                        // Invoke the onProductAdded callback
                        onEventAdded()
                    }
                    .addOnFailureListener { e ->
                        // Handle error updating product document
                    }
            }
        }
        .addOnFailureListener { e ->
            // Handle error adding product to Firestore
        }
}

private fun uploadImageToStorage(eventId: String, imageUri: Uri?, onSuccess: (String) -> Unit) {
    if (imageUri == null) {
        onSuccess("")
        return
    }

    val storageRef = Firebase.storage.reference
    val imagesRef = storageRef.child("events/$eventId.jpg")

    imagesRef.putFile(imageUri)
        .addOnSuccessListener { taskSnapshot ->
            imagesRef.downloadUrl
                .addOnSuccessListener { uri ->
                    onSuccess(uri.toString())
                }
                .addOnFailureListener {
                    // Handle failure to get download URL
                }
        }
        .addOnFailureListener {
            // Handle failure to upload image
        }
}
