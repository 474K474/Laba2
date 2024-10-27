package services

import models.Bank
import models.Credit
import models.Client

class CreditService(private val banks: List<Bank>) {

    // Поиск кредитов по условиям клиента
    fun findCredits(client: Client): List<Credit> {
        return banks.flatMap { bank ->
            bank.credits.filter { credit ->
                credit.interestRate <= client.maxInterestRate &&
                        credit.termInYears <= client.maxTerm &&
                        credit.maxAmount >= client.minAmount
            }
        }
    }

    // Поиск кредитов с возможностью досрочного погашения
    fun findCreditsWithEarlyRepayment(): List<Credit> {
        return banks.flatMap { bank -> bank.credits.filter { it.hasEarlyRepayment } }
    }

    // Поиск кредитов с возможностью увеличения кредитной линии
    fun findCreditsWithCreditLineIncrease(): List<Credit> {
        return banks.flatMap { bank -> bank.credits.filter { it.hasCreditLineIncrease } }
    }
}
