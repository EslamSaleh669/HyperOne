package com.HyperOne.data.model

import com.google.gson.annotations.SerializedName

data class DataResponse(

	@field:SerializedName("DataResponse")
	val dataResponse: ArrayList<DataResponseItem?>? = null
)

data class DataResponseItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
