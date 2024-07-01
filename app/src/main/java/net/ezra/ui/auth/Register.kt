package net.ezra.ui.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import net.ezra.R
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_REGISTER


@Composable
fun SignUpScreen(navController: NavController, onSignUpSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current



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
    ) {
        androidx.compose.material3.Icon(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(2.dp)),
            painter = painterResource(id = R.drawable.logoi),
            contentDescription = null
        )
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        androidx.compose.material3.Text(
            text = "Connectify Events",
            color = Color.Black,
            fontSize = 40.sp,
            fontFamily = FontFamily.Cursive,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
        )
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        Text(
            text = "Register",
            fontSize = 40.sp,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        Text(
            text = "Please Register Your Details",
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(
            modifier = Modifier
                .height(15.dp)
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
                .height(15.dp)
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
                .height(15.dp)
        )

        androidx.compose.material3.OutlinedTextField(
            value = confirmPassword,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedLabelColor = Color.Black,
            ),
            onValueChange = { confirmPassword = it },
            label = { androidx.compose.material3.Text(text = "Confirm Password") },
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
                .height(15.dp)
        )

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp)
            )
        } else {
            Button(
                colors = ButtonDefaults.buttonColors(Color(color = 0xffe56a08)),
                onClick = {
                    if (email.isBlank()){
                            error = "Email is required"
                        } else if (password.isBlank()){
                        error = "Password is required"
                        } else if(confirmPassword.isBlank()) {
                        error = "Password Confirmation required"
                    } else if (password != confirmPassword) {
                        error = "Passwords do not match"
                    } else {
                        isLoading = true
                        signUp(email, password, {
                            isLoading = false
                            Toast.makeText(context, "Sign-up successful!", Toast.LENGTH_SHORT).show()
                            onSignUpSuccess()
                        }) { errorMessage ->
                            isLoading = false
                            error = errorMessage
                        }
                    }
                },
                shape = RoundedCornerShape(30.dp)

            ) {
                Text(
                    text = "Register",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            }
            Spacer(
                modifier = Modifier
                    .height(15.dp)
            )

            Text(
                modifier = Modifier

                    .clickable {
                        navController.navigate(ROUTE_LOGIN) {
                            popUpTo(ROUTE_REGISTER) { inclusive = true }
                        }
                    },
                text = "Go to Login Page",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
            )

        }

        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
private fun signUp(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
    FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val signInMethods = task.result?.signInMethods ?: emptyList()
                if (signInMethods.isNotEmpty()) {
                    onError("Email is already registered")
                } else {
                    // Email is not registered, proceed with sign-up
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { signUpTask ->
                            if (signUpTask.isSuccessful) {
                                onSuccess()
                            } else {
                                onError(signUpTask.exception?.message ?: "Sign-up failed")
                            }
                        }
                }
            } else {
                onError(task.exception?.message ?: "Failed to check email availability")
            }
        }
}


