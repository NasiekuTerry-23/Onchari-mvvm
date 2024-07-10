package net.ezra.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.ezra.ui.SplashScreen
import net.ezra.ui.about.AboutScreen
import net.ezra.ui.auth.LoginScreen
import net.ezra.ui.auth.SignUpScreen
import net.ezra.ui.dashboard.DashboardScreen
import net.ezra.ui.events.Calender
import net.ezra.ui.events.DetailsScreen
import net.ezra.ui.events.LiveEventScreen
import net.ezra.ui.home.HomeScreen
import net.ezra.ui.newevents.AddEventScreen
import net.ezra.ui.newevents.ProductListScreen
import net.ezra.ui.newevents.ProductDetailScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_HOME


) {


    BackHandler {
        navController.popBackStack()

        }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {


        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }


        composable(ROUTE_ABOUT) {
            AboutScreen(navController)
        }

        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }



//        composable(ROUTE_SEARCH) {
//            Search(navController)
//        }

        composable(ROUTE_DASHBOARD) {
            DashboardScreen(navController)
        }

        composable(ROUTE_REGISTER) {
           SignUpScreen(navController = navController) {

           }
        }

        composable(ROUTE_LOGIN) {
            LoginScreen(navController = navController){}
        }


        composable(ROUTE_EVENTS) {
            Calender(navController = navController)
        }

        composable(ROUTE_EVEDETAIL) {
            DetailsScreen(navController = navController)
        }

        composable(ROUTE_EVELIVE) {
            LiveEventScreen(navController = navController)
        }


        composable(ROUTE_NEWEVENTS) {
            AddEventScreen(navController = navController) {}
        }

        composable(ROUTE_VIEWEVE) {
            ProductListScreen(navController = navController, products = listOf())
        }

        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(navController, productId)
        }




















































    }
}