package net.ezra.ui.auth



import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import net.ezra.R
import net.ezra.navigation.ROUTE_DASHBOARD
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_REGISTER

@Composable
fun LoginScreen(navController: NavController, onLoginSuccess: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    BackHandler {
        navController.popBackStack()

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFF677572), Color(0xFFff9448)), // Black to Orange
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

            Icon(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(2.dp)),
                painter = painterResource(id = R.drawable.logoi),
                contentDescription = null,

                )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Text(
                text = "Connectify Events",
                color = Color.Black,
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Text(
                text = "Login",
                fontSize = 40.sp,
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Text(
                text = "Come with your UserID",
                fontSize = 25.sp,
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Text(
                text = "For easy, efficient and secure login",
                fontSize = 18.sp,
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            androidx.compose.material3.OutlinedTextField(
                value = email,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Black,
                ),
                onValueChange = { email = it },
                label = { androidx.compose.material3.Text(text = "Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                leadingIcon = {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Email,
                        tint = Color.Black,
                        contentDescription = "emailIcon"
                    )
                }
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            androidx.compose.material3.OutlinedTextField(
                value = password,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Black,
                ),
                onValueChange = { password = it },
                label = { androidx.compose.material3.Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                leadingIcon = {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Lock,
                        tint = Color.Black,
                        contentDescription = "lockIcon"
                    )
                }
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                )
            } else {
                Button(
                    colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                    onClick = {
                        if (email.isBlank() || password.isBlank()) {
                            error = "Please fill in all the fields"
                        } else {
                            isLoading = true
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    isLoading = false
                                    if (task.isSuccessful) {
                                        navController.navigate(ROUTE_DASHBOARD)
                                    } else {
                                        error = task.exception?.message ?: "Login failed"
                                    }
                                }
                        }
                    },
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Text(
                        text = "Login",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                )
                Text(
                    text = "Don't Have An Account?",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                )
                Text(
                    text = "Register",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ROUTE_REGISTER) {
                                popUpTo(ROUTE_LOGIN) { inclusive = true }
                            }
                        },
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
