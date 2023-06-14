package com.travelee.signing.navigation

import com.travelee.signing.utilities.Constants.FORGOT_PASSWORD_SCREEN
import com.travelee.signing.utilities.Constants.PROFILE_SCREEN
import com.travelee.signing.utilities.Constants.SIGN_IN_SCREEN
import com.travelee.signing.utilities.Constants.SIGN_UP_SCREEN
import com.travelee.signing.utilities.Constants.VERIFY_EMAIL_SCREEN

sealed class Screen(val route: String) {
    object SignInScreen: Screen(SIGN_IN_SCREEN)
    object ForgotPasswordScreen: Screen(FORGOT_PASSWORD_SCREEN)
    object SignUpScreen: Screen(SIGN_UP_SCREEN)
    object VerifyEmailScreen: Screen(VERIFY_EMAIL_SCREEN)
    object ProfileScreen: Screen(PROFILE_SCREEN)
}