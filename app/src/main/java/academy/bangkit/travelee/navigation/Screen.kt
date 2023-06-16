package academy.bangkit.travelee.navigation

import academy.bangkit.travelee.sources.Constants.FORGOT_PASSWORD_SCREEN
import academy.bangkit.travelee.sources.Constants.PROFILE_SCREEN
import academy.bangkit.travelee.sources.Constants.SIGN_IN_SCREEN
import academy.bangkit.travelee.sources.Constants.SIGN_UP_SCREEN
import academy.bangkit.travelee.sources.Constants.SPLASH_SCREEN
import academy.bangkit.travelee.sources.Constants.VERIFY_EMAIL_SCREEN


sealed class Screen(val route: String) {
    object SplashScreen: Screen(SPLASH_SCREEN)
    object SignInScreen: Screen(SIGN_IN_SCREEN)
    object ForgotPasswordScreen: Screen(FORGOT_PASSWORD_SCREEN)
    object SignUpScreen: Screen(SIGN_UP_SCREEN)
    object VerifyEmailScreen: Screen(VERIFY_EMAIL_SCREEN)
    object ProfileScreen: Screen(PROFILE_SCREEN)
}