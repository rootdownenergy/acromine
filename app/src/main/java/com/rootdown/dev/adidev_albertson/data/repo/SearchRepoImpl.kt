package com.rootdown.dev.adidev_albertson.data.repo

import android.util.Log
import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.data.net.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SearchRepoImpl @Inject constructor(
    private val api: ApiService,
) : SearchRepo {
    override suspend fun getAcro(q: String): AcromineFull.AcromineFullItem {
        var result: AcromineFull.AcromineFullItem? = null
        withContext(Dispatchers.IO){
            val acroLs = api.getAcromine(q)
            if(acroLs.isEmpty()){
                result = AcromineFull.AcromineFullItem(
                    lfs = listOf(AcromineFull.AcromineFullItem.Lf(
                        lf = "ERROR",
                        freq = 1,
                        since = 1985,
                        vars = listOf(AcromineFull.AcromineFullItem.Lf.Var(freq = 1, lf = "", since = 1985))
                    )), sf = "")
            } else {
                result = acroLs.get(0)
            }
            Log.w("!!!", "Response: $acroLs")
        }
        return result!!
    }
}