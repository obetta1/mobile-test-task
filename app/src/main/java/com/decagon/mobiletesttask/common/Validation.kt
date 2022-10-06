package com.decagon.mobiletesttask.common

val UPPERCASE = Regex("[A-Z]")
val LOWERCASE = Regex("[a-z]")
val DIGITCHARACTER = Regex("[0-9]")
val SPECAILCHARAACTERS = Regex("[@#\$%^&+=*_-]")

var EMAIL_PATTERN = Regex(
    "[a-zA-Z0-9+._%\\-]{1,256}" +
            "@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

fun validateEmailInput(email: String): Boolean {
    if (email.isEmpty() || !email.matches(EMAIL_PATTERN)) {
        return false
    }
    return true
}
// passwordInputField validation
fun validatePassword(
    password: String,
): List<String> {
    val result = mutableListOf<String>()

    if (password.length <= 5) {
        result.add("* Minimum of 6 characters")
    }
    if (!password.contains(UPPERCASE) || !password.contains(LOWERCASE)
    ) {
        result.add("* Uppercase and lowercase")
    }
    if (!password.contains(DIGITCHARACTER)) {
        result.add("* Numbers")
    }
    if (!password.contains(SPECAILCHARAACTERS)) {
        result.add("* Special characters")
    }
    return result
}