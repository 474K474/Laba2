package main

import models.Client
import services.CreditService
import utils.DataLoader

fun main() {
    val banks = DataLoader.loadBanks("C:\\Users\\Kozak\\IdeaProjects\\Laba2\\src\\banks.txt")
    val creditService = CreditService(banks)

    println("Добро пожаловать в систему подбора кредитов!")
    while (true) {
        println("Выберите опцию:")
        println("1. Найти кредит по условиям")
        println("2. Найти кредиты с возможностью досрочного погашения")
        println("3. Найти кредиты с возможностью увеличения кредитной линии")
        println("4. Выйти")

        when (readLine()) {
            "1" -> {
                println("Введите максимальную процентную ставку:")
                val maxInterestRate = readLine()?.toDoubleOrNull() ?: continue
                println("Введите максимальный срок (в годах):")
                val maxTerm = readLine()?.toIntOrNull() ?: continue
                println("Введите минимальную сумму кредита:")
                val minAmount = readLine()?.toDoubleOrNull() ?: continue

                val client = Client("User", maxInterestRate, maxTerm, minAmount)
                val credits = creditService.findCredits(client)
                println("Подходящие кредиты:")
                credits.forEach { println(it) }
            }
            "2" -> {
                val credits = creditService.findCreditsWithEarlyRepayment()
                println("Кредиты с досрочным погашением:")
                credits.forEach { println(it) }
            }
            "3" -> {
                val credits = creditService.findCreditsWithCreditLineIncrease()
                println("Кредиты с увеличением кредитной линии:")
                credits.forEach { println(it) }
            }
            "4" -> {
                println("Выход из программы.")
                return
            }
            else -> println("Некорректный выбор. Попробуйте снова.")
        }
    }
}
