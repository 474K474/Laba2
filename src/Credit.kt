package models

data class Credit(
    val bankName: String,
    val creditName: String,
    val interestRate: Double,
    val maxAmount: Double,
    val termInYears: Int,
    val hasEarlyRepayment: Boolean,
    val hasCreditLineIncrease: Boolean
)
