package com.nadia.ucpakhir.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nadia.ucpakhir.ui.tanaman.view.DestinasiDetailTanaman
import com.nadia.ucpakhir.ui.tanaman.view.DestinasiEntryTanaman
import com.nadia.ucpakhir.ui.tanaman.view.DestinasiHomeTanaman
import com.nadia.ucpakhir.ui.tanaman.view.DestinasiTanamanUpdate
import com.nadia.ucpakhir.ui.tanaman.view.DetailScreenTanaman
import com.nadia.ucpakhir.ui.tanaman.view.EntryTnmnScreen
import com.nadia.ucpakhir.ui.tanaman.view.HomeTanamanScreen
import com.nadia.ucpakhir.ui.tanaman.view.UpdateScreenTanaman

@Composable
fun PengelolaHalamanPertanian(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeTanaman.route,
        modifier = Modifier,
    ){
        composable(DestinasiHomeTanaman.route){
            HomeTanamanScreen(
                navigateToltemEntry = {
                    navController.navigate(DestinasiEntryTanaman.route)
                },
                onDetailClick = { idtnmn ->
                    navController.navigate("${DestinasiDetailTanaman.route}/$idtnmn")
                    println("PengelolaHalaman: idTanaman = $idtnmn")
                },
                onEditClick = { idtnmn ->
                    navController.navigate("${DestinasiTanamanUpdate.route}/$idtnmn")
                    println("PengelolaHalaman: idTanaman = $idtnmn")
                }
            )
        }

        composable(DestinasiEntryTanaman.route) {
            EntryTnmnScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable (
            DestinasiDetailTanaman.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailTanaman.IDtnmn) {
                    type = NavType.IntType
                }
            )
        ) {
            val idtanaman = it.arguments?.getInt(DestinasiDetailTanaman.IDtnmn)

            idtanaman?.let { idtanaman ->
                DetailScreenTanaman(
                    navigateBack = {
                        navController.navigate(DestinasiHomeTanaman.route) {
                            popUpTo(DestinasiHomeTanaman.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }

        composable(
            DestinasiTanamanUpdate.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailTanaman.IDtnmn){
                    type = NavType.IntType
                }
            )
        ){
            val idtanaman = it.arguments?.getInt(DestinasiTanamanUpdate.IDtnmn)
            idtanaman?.let { idtanaman ->
                UpdateScreenTanaman(
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavigate = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
