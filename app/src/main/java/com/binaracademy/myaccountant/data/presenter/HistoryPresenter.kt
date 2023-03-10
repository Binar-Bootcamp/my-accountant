package com.binaracademy.myaccountant.data.presenter

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.data.dao.SummaryRepository
import com.binaracademy.myaccountant.data.room.Summary
import com.binaracademy.myaccountant.data.room.SummaryRepositoryImpl

class HistoryPresenter(
    private val summaryRepository: SummaryRepository = SummaryRepositoryImpl()
): HistoryContract.Presenter, BasePresenter<HistoryContract.View>() {
    override fun getHistoryTransaction(): LiveData<List<Summary>> {
        return summaryRepository.findAllLiveDataSummary()
    }
}