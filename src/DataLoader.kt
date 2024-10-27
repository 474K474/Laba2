package utils

import models.Bank
import models.Credit
import java.io.File

object DataLoader {

    fun loadBanks(filename: String): List<Bank> {
        val lines = File(filename).readLines()
        val banks = mutableListOf<Bank>()

        var bankName = ""
        val credits = mutableListOf<Credit>()

        lines.forEach { line ->
            if (line.startsWith("Bank:")) {
                if (bankName.isNotEmpty()) {
                    banks.add(Bank(bankName, credits.toList()))
                    credits.clear()
                }
                bankName = line.removePrefix("Bank:").trim()
            } else {
                val parts = line.split(",")

                // Проверяем, что у нас есть все необходимые поля для создания объекта Credit
                if (parts.size >= 6) {
                    try {
                        val credit = Credit(
                            bankName = bankName,
                            creditName = parts[0].trim(),
                            interestRate = parts[1].trim().toDouble(),
                            maxAmount = parts[2].trim().toDouble(),
                            termInYears = parts[3].trim().toInt(),
                            hasEarlyRepayment = parts[4].trim().toBoolean(),
                            hasCreditLineIncrease = parts[5].trim().toBoolean()
                        )
                        credits.add(credit)
                    } catch (e: Exception) {
                        println("Ошибка при обработке строки: $line. Проверьте формат данных.")
                    }
                } else {
                    println("Некорректный формат строки: $line. Ожидается 6 значений, разделенных запятыми.")
                }
            }
        }

        if (bankName.isNotEmpty()) {
            banks.add(Bank(bankName, credits))
        }

        return banks
    }
}
