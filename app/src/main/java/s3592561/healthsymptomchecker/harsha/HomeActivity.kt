package s3592561.healthsymptomchecker.harsha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HealthWiseHomeScreen()
        }
    }
}




// COLORS

val HealthGreen = Color(0xFF299D78)
val HealthBackground = Color(0xFFF5F9F7)
val LightGreen = Color(0xFFE5F4ED)
val DarkText = Color(0xFF263832)
val GrayText = Color(0xFF858F8A)

// MODEL

data class Symptom(
    val name: String,
    val date: String
)

// SAMPLE DATA

val sampleSymptoms = listOf(
    Symptom("Headache", "21 Nov 2026"),
    Symptom("Cold and Cough", "19 Nov 2026"),
    Symptom("Stomach Pain", "15 Nov 2026")
)

// MAIN HOME SCREEN

@Composable
fun HealthWiseHomeScreen(
    symptoms: List<Symptom> = sampleSymptoms,
    onCheckSymptoms: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onSymptomsClick: () -> Unit = {},
    onHealthInfoClick: () -> Unit = {}
) {

    Scaffold(
        containerColor = HealthBackground,

        bottomBar = {

            NavigationBar(
                containerColor = Color.White
            ) {

                NavigationBarItem(
                    selected = true,
                    onClick = onHomeClick,
                    icon = {
                        Icon(Icons.Default.Home, null)
                    },
                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onSymptomsClick,
                    icon = {
                        Icon(Icons.Default.MedicalServices, null)
                    },
                    label = {
                        Text("Symptoms")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onHealthInfoClick,
                    icon = {
                        Icon(Icons.Default.MenuBook, null)
                    },
                    label = {
                        Text("Health Info")
                    }
                )
            }
        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(18.dp),

            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // HEADER

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            "Health Wise",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = HealthGreen
                        )

                        Spacer(Modifier.height(5.dp))

                        Text(
                            "Hello, Welcome Back!",
                            fontSize = 15.sp,
                            color = DarkText
                        )

                        Text(
                            "Take care of your health today.",
                            fontSize = 12.sp,
                            color = GrayText
                        )
                    }

                    IconButton(
                        onClick = onProfileClick,
                        modifier = Modifier
                            .size(45.dp)
                            .background(
                                LightGreen,
                                CircleShape
                            )
                    ) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = HealthGreen
                        )
                    }
                }
            }

            // CHECK SYMPTOMS BUTTON

            item {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = HealthGreen
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Icon(
                            Icons.Default.HealthAndSafety,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(35.dp)
                        )

                        Spacer(Modifier.height(12.dp))

                        Text(
                            "How are you feeling today?",
                            color = Color.White,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(6.dp))

                        Text(
                            "Select your symptoms and get general health guidance.",
                            color = Color.White,
                            fontSize = 13.sp
                        )

                        Spacer(Modifier.height(16.dp))

                        Button(
                            onClick = onCheckSymptoms,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = HealthGreen
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {

                            Text(
                                "Check Symptoms",
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(Modifier.width(8.dp))

                            Icon(
                                Icons.Default.ArrowForward,
                                contentDescription = null
                            )
                        }
                    }
                }
            }

            // RECENT SYMPTOMS TITLE

            item {

                Text(
                    "Recent Symptoms",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )
            }

            // RECENT SYMPTOMS LIST

            if (symptoms.isEmpty()) {

                item {
                    EmptySymptoms()
                }

            } else {

                items(symptoms) { symptom ->

                    SymptomItem(symptom)
                }
            }

            // DAILY HEALTH TIP

            item {

                Text(
                    "Daily Health Tip",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )

                HealthTipCard()
            }
        }
    }
}

// SYMPTOM ITEM

@Composable
fun SymptomItem(symptom: Symptom) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(14.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                Icons.Default.MedicalInformation,
                contentDescription = null,
                tint = HealthGreen,
                modifier = Modifier.size(30.dp)
            )

            Spacer(Modifier.width(12.dp))

            Column {

                Text(
                    symptom.name,
                    fontWeight = FontWeight.SemiBold,
                    color = DarkText
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    symptom.date,
                    fontSize = 12.sp,
                    color = GrayText
                )
            }
        }
    }
}

// EMPTY STATE

@Composable
fun EmptySymptoms() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(14.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = HealthGreen,
                modifier = Modifier.size(35.dp)
            )

            Spacer(Modifier.height(10.dp))

            Text(
                "No symptoms recorded",
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Text(
                "Your recent symptom records will appear here.",
                fontSize = 12.sp,
                color = GrayText
            )
        }
    }
}

// HEALTH TIP CARD

@Composable
fun HealthTipCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = LightGreen
        ),
        shape = RoundedCornerShape(15.dp)
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                Icons.Default.Lightbulb,
                contentDescription = null,
                tint = HealthGreen,
                modifier = Modifier.size(35.dp)
            )

            Spacer(Modifier.width(12.dp))

            Column {

                Text(
                    "Stay Hydrated!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = DarkText
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    "Drink enough water throughout the day to support your overall health.",
                    fontSize = 12.sp,
                    color = DarkText
                )
            }
        }
    }
}

// PREVIEW

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HealthWiseHomePreview() {

    MaterialTheme {

        HealthWiseHomeScreen()
    }
}

// EMPTY DASHBOARD PREVIEW

@Preview(showBackground = true)
@Composable
fun HealthWiseEmptyPreview() {

    MaterialTheme {

        HealthWiseHomeScreen(
            symptoms = emptyList()
        )
    }
}
