package com.binaracademy.myaccountant.data.presenter

import com.binaracademy.myaccountant.data.dao.SummaryRepository
import com.binaracademy.myaccountant.data.enums.UserType
import com.binaracademy.myaccountant.data.room.SummaryRepositoryImpl

class ProfilePresenter(
    private val summaryRepository: SummaryRepository = SummaryRepositoryImpl()
): ProfileContract.Presenter, BasePresenter<ProfileContract.View>() {
    override suspend fun getTotalSavingUser() {
        val summaries = summaryRepository.findAllSummary()
        // ini nanti di follow up apakah tipe yang di dapatkan
        // dari yang terakhir
        // val last = summaries.first()

        val sumOfTotal = summaries.sumOf { s -> s.total }
        val sumOfSaving = summaries.sumOf {
            when (it.type) {
                UserType.LIFE_BALANCE -> it.income * 5 / 10
                UserType.PALING_HEMAT -> it.income * 6 / 10
                UserType.TUKANG_SHOPPING -> it.income * 3 / 10
            }
        }

        getView()?.onSuccessFetchSaving(sumOfSaving + sumOfTotal)
    }
}