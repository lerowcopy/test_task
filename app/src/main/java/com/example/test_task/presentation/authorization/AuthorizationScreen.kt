package com.example.test_task.presentation.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test_task.R

@Composable
fun AuthorizationScreen(
    modifier: Modifier = Modifier,
    onMainScreen: () -> Unit = {},
) {

    var emailTextField by remember { mutableStateOf("") }
    var passwordTextField by remember { mutableStateOf("") }

    val uriHandler = LocalUriHandler.current

    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
    val isEmailValid = emailTextField.isEmpty() || emailTextField.matches(emailRegex)
    val authCondition =
        emailTextField.isNotEmpty() && passwordTextField.isNotEmpty() && isEmailValid

    //общий контейнер
    Column(
        modifier = modifier
            .padding(top = 100.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Вход",
            fontSize = 28.sp,
            letterSpacing = 0.sp,
            lineHeight = 36.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.roboto_regular))
        )
        Spacer(modifier = Modifier.height(28.dp))

        // контейнер для ввода данных пользователя
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // контейнер для ввода почты
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(
                    text = "Email",
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    letterSpacing = 0.15.sp,
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = emailTextField,
                    label = { Text("example@gmail.com") },
                    onValueChange = { newValue ->
                        emailTextField = newValue.replace(Regex("[^A-Za-z0-9@._%+-]"), "")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color(50, 51, 58),
                        unfocusedContainerColor = Color(50, 51, 58),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    )
                )

                if (!isEmailValid) {
                    Text(
                        text = "Введите корректный email",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // контейнер для ввода пароля
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(
                    text = "Пароль",
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    letterSpacing = 0.15.sp,
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = passwordTextField,
                    label = { Text("Введите пароль") },
                    onValueChange = { passwordTextField = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color(50, 51, 58),
                        unfocusedContainerColor = Color(50, 51, 58),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    )
                )
            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        // кнопка входа
        Button(
            onClick = {
                onMainScreen()
            },
            enabled = authCondition,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(18, 185, 81, 255),
                disabledContainerColor = Color.LightGray
            ),
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text("Вход")
        }

        // контейнер для Регистрации и забыл пароль
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row() {
                Text(
                    text = "Нету аккаунта?",
                    fontFamily = FontFamily(Font(R.font.roboto_semibold)),
                    fontSize = 12.sp,
                    lineHeight = 15.sp,
                    letterSpacing = 0.4.sp,
                    color = Color.White,
                )
                Text(
                    text = " Регистрация",
                    fontFamily = FontFamily(Font(R.font.roboto_semibold)),
                    fontSize = 12.sp,
                    lineHeight = 15.sp,
                    letterSpacing = 0.4.sp,
                    color = Color(18, 185, 81)
                )

            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Забыл пароль",
                fontFamily = FontFamily(Font(R.font.roboto_semibold)),
                fontSize = 12.sp,
                lineHeight = 15.sp,
                letterSpacing = 0.4.sp,
                color = Color(18, 185, 81)
            )
        }
        Spacer(
            modifier = Modifier
                .padding(vertical = 32.dp)
                .height(1.dp)
                .fillMaxWidth()
                .background(Color(77, 85, 94, 255))
        )

        // контейнер для вк и одноклассников
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(38, 131, 237))
                    .clickable { uriHandler.openUri("https://vk.com/") },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_vk),
                    contentDescription = "VK",
                    tint = Color.White,
                    modifier = Modifier
                        .width(50.dp)
                        .height(40.dp)
                )
            }
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(30.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(249, 133, 9), Color(249, 93, 0))
                        )
                    )
                    .clickable { uriHandler.openUri("https://ok.ru/") },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ok),
                    contentDescription = "OK",
                    tint = Color.White,
                    modifier = Modifier
                        .width(50.dp)
                        .height(40.dp)
                )
            }
        }

    }
}
