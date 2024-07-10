package net.ezra.ui.newevents

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import net.ezra.navigation.ROUTE_DASHBOARD
import net.ezra.navigation.ROUTE_EVELIVE
import net.ezra.navigation.ROUTE_HOME
//import net.ezra.navigation.ROUTE_VIEW_PROD
import net.ezra.navigation.ROUTE_NEWEVENTS
import net.ezra.navigation.ROUTE_VIEWEVE



import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventScreen(navController: NavController, onEventAdded: () -> Unit) {
    var contentHeadline by remember { mutableStateOf("") }
    var contentDescription by remember { mutableStateOf("") }
    var eventDate by remember { mutableStateOf("") }
    var eventPrice by remember { mutableStateOf("") }
    var eventLocation by remember { mutableStateOf("") }
    var eventImageUri by remember { mutableStateOf<Uri?>(null) }

    // Track if fields are empty
    var contentHeadlineError by remember { mutableStateOf(false) }
    var contentDescriptionError by remember { mutableStateOf(false) }
    var eventDateError by remember { mutableStateOf(false) }
    var eventPriceError by remember { mutableStateOf(false) }
    var eventLocationError by remember { mutableStateOf(false) }
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
                item {

                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(start = 40.dp),
                        ) {
                            Spacer(
                                modifier = Modifier
                                    .height(30.dp)
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
                            Spacer(
                                modifier = Modifier
                                    .height(90.dp)
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
                                Text("No Posters Selected",
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
                                .padding(start = 100.dp)
                                //.align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                "Select Poster",
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                        OutlinedTextField(
                            value = contentHeadline,
                            onValueChange = { contentHeadline = it },

                            label = { Text(
                                "Content Headline",
                                color = Color.White
                            ) },
                            modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp),

                            )
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                        OutlinedTextField(
                            value = contentDescription,
                            onValueChange = { contentDescription = it },
                            label = { Text(
                                "Content Description",
//                                focusedContainerColor = Color.LightGray,
//                                unfocusedContainerColor = Color.LightGray,
                                color = Color.White
                            ) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                        )
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                    OutlinedTextField(
                        value = eventDate,
                        onValueChange = { eventDate = it },

                        label = { Text(
                            "Event Date",
                            color = Color.White
                        ) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),

                        )
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )
                    OutlinedTextField(
                        value = eventLocation,
                        onValueChange = { eventLocation = it },

                        label = { Text(
                            "Event Location",
                            color = Color.White
                        ) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),

                        )
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )
                    OutlinedTextField(
                        value = eventPrice,
                        onValueChange = { eventPrice = it },

                        label = { Text(
                            "Event Price",
                            color = Color.White
                        ) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),

                        )

                        if (contentHeadlineError) {
                            Text("Content Headline is required", color = Color.Red)
                        }
                        if (contentDescriptionError) {
                            Text("Content Description is required", color = Color.Red)
                        }
                    if (eventLocationError) {
                        Text("Event Location is required", color = Color.Red)
                    }
                    if (eventDateError) {
                        Text("Date of the Event is required", color = Color.Red)
                    }
                    if (eventPriceError) {
                        Text("Event Price is required", color = Color.Red)
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
                                    eventLocationError = eventLocation.isBlank()
                                    eventDateError = eventLocation.isBlank()
                                    eventPriceError = eventLocation.isBlank()
                                    eventImageError = eventImageUri == null

                                    // Add product if all fields are filled
                                    if (!contentHeadlineError && !contentDescriptionError && !eventLocationError && !eventDateError && !eventPriceError && !eventImageError) {
                                        addEventToFirestore(
                                            navController,
                                            onEventAdded,
                                            contentHeadline,
                                            contentDescription,
                                            eventDate,
                                            eventLocation,
                                            eventPrice,
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
                                navController.navigate(ROUTE_VIEWEVE)
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
                                navController.navigate(ROUTE_EVELIVE) {
                                    popUpTo(ROUTE_NEWEVENTS) { inclusive = true }
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
    )
}

private fun addEventToFirestore(
    navController: NavController,
    onEventAdded: () -> Unit,
    contentHeadline: String,
    contentDescription: String,
    eventLocation: String,
    eventDate: String,
    eventPrice: String,
    eventImageUri: Uri?
) {
    if (contentHeadline.isEmpty() || contentDescription.isEmpty() || eventLocation.isEmpty() || eventDate.isEmpty() || eventPrice.isEmpty()|| eventImageUri == null) {
        // Validate input fields
        return
    }

    val eventId = UUID.randomUUID().toString()

    val firestore = Firebase.firestore
    val eventData = hashMapOf(
        "name" to contentHeadline,
        "description" to contentDescription,
        "location" to eventLocation,
        "date" to eventDate,
        "price" to eventPrice,
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
                        navController.navigate(ROUTE_VIEWEVE)

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
