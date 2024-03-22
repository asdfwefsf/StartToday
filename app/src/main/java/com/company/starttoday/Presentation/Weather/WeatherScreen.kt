package com.company.starttoday.Presentation.Weather

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.company.starttoday.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Locale

@Composable
fun WeatherScreen() {
    lateinit var fusedLocationClient: FusedLocationProviderClient

    val viewModel : WeatherViewModel = hiltViewModel()

    val rainProbabilities = viewModel.rainPossible.collectAsState()



    CoroutineScope(Dispatchers.IO).launch {
        viewModel.getWeather(61 , 121)
    }



    val image: Painter = painterResource(id = R.drawable.rain_screen)

    val context = LocalContext.current
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    if (ContextCompat.checkSelfPermission(LocalContext.current, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let { loc ->
                    val geocoder = Geocoder(context , Locale.getDefault())
                    try {
                        val addresses = geocoder.getFromLocation(loc.latitude, loc.longitude, 1)
                        if (addresses!!.isNotEmpty()) {
                            val address = addresses[0].getAddressLine(0)
                            val addressPart = address.substring(5)
                            val realAddress = addressPart.replace(Regex("\\s"), "")


                            Log.d("LocationInfo", "Address: $realAddress")
                            Log.d("LocationInfo", "Address: ${loc.latitude}")
                            Log.d("LocationInfo", "Address: ${loc.longitude}")
                        } else {
                            Log.d("LocationInfo", "No Address Found")
                        }
                    } catch (e: IOException) {
                        Log.e("LocationInfo", "Geocoder I/O Exception", e)
                    }
                } ?: Log.d("LocationInfo", "Location is null")
            }
    }















    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (backgroundImage, text) = createRefs()

        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(backgroundImage) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = rainProbabilities.value.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(parent.top, margin = 20.dp)
                },
            color = MaterialTheme.colorScheme.onSurface
        )

    }
}
