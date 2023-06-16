package academy.bangkit.travelee.navigation

import academy.bangkit.travelee.components.AnimatedSplashScreen
import academy.bangkit.travelee.navigation.Screen.ForgotPasswordScreen
import academy.bangkit.travelee.navigation.Screen.ProfileScreen
import academy.bangkit.travelee.navigation.Screen.SignInScreen
import academy.bangkit.travelee.navigation.Screen.SignUpScreen
import academy.bangkit.travelee.navigation.Screen.SplashScreen
import academy.bangkit.travelee.navigation.Screen.VerifyEmailScreen
import academy.bangkit.travelee.presentation.forgot_password.ForgotPasswordScreen
import academy.bangkit.travelee.presentation.profile.ProfileScreen
import academy.bangkit.travelee.presentation.sign_in.SignInScreen
import academy.bangkit.travelee.presentation.sign_up.SignUpScreen
import academy.bangkit.travelee.presentation.verify_email.VerifyEmailScreen
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


@Composable
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
fun NavGraph(
    navController: NavHostController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = SplashScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(
            route = SplashScreen.route
        ) {
            AnimatedSplashScreen(
                navigateToSigninScreen = {
                    navController.navigate(SignInScreen.route)
                }
            )
        }
        composable(
            route = SignInScreen.route
        ) {
            SignInScreen(
                navigateToForgotPasswordScreen = {
                    navController.navigate(ForgotPasswordScreen.route)
                },
                navigateToSignUpScreen = {
                    navController.navigate(SignUpScreen.route)
                }
            )
        }
        composable(
            route = ForgotPasswordScreen.route
        ) {
            ForgotPasswordScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = SignUpScreen.route
        ) {
            SignUpScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = VerifyEmailScreen.route
        ) {
            VerifyEmailScreen(
                navigateToProfileScreen = {
                    navController.navigate(ProfileScreen.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            route = ProfileScreen.route
        ) {
            ProfileScreen()
        }
    }
}