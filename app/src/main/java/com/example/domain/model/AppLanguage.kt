package com.example.domain.model

enum class AppLanguage(val code: String, val displayName: String) {
    BANGLA("bn", "বাংলা"),
    ENGLISH("en", "English")
}

enum class AppThemeMode {
    SYSTEM,
    LIGHT,
    DARK
}
