package dev.anmatolay.template.xml.core.authentication

import com.google.firebase.auth.FirebaseAuthException

class UnknownAuthErrorException(isTaskSuccessful: Boolean, isCurrentUserNull: Boolean) :
    FirebaseAuthException(
        "unknown-error",
        "Unknown error occurred during authentication. " +
            "Auth Task successful: $isTaskSuccessful. Is user null: $isCurrentUserNull.",
    )