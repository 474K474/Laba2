package models

data class Client(
    val name: String,
    val maxInterestRate: Double,
    val maxTerm: Int,
    val minAmount: Double
)
