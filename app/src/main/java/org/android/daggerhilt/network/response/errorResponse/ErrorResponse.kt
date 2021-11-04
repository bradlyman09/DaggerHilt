package org.android.daggerhilt.network.response.errorResponse

import com.google.gson.annotations.SerializedName


data class ErrorResponse(
    val errorMessage : String? = null,
    @SerializedName("message")
    val causes: Map<String, List<String>> = emptyMap()
)