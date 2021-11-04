package org.android.daggerhilt.network.response.get_movies

import com.google.gson.annotations.SerializedName

data class Movie (

	@SerializedName("wrapperType") val wrapperType : String? = null,
	@SerializedName("artistId") val artistId : Int? = null,
	@SerializedName("collectionId") val collectionId : Int? = null,
	@SerializedName("artistName") val artistName : String? = null,
	@SerializedName("trackName") val trackName : String? = null,
	@SerializedName("collectionName") val collectionName : String? = null,
	@SerializedName("collectionCensoredName") val collectionCensoredName : String? = null,
	@SerializedName("artistViewUrl") val artistViewUrl : String? = null,
	@SerializedName("collectionViewUrl") val collectionViewUrl : String? = null,
	@SerializedName("artworkUrl60") val artworkUrl60 : String? = null,
	@SerializedName("artworkUrl100") val artworkUrl100 : String? = null,
	@SerializedName("collectionPrice") val collectionPrice : Double? = null,
	@SerializedName("collectionExplicitness") val collectionExplicitness : String? = null,
	@SerializedName("trackCount") val trackCount : Int? = null,
	@SerializedName("copyright") val copyright : String? = null,
	@SerializedName("country") val country : String? = null,
	@SerializedName("currency") val currency : String? = null,
	@SerializedName("releaseDate") val releaseDate : String? = null,
	@SerializedName("primaryGenreName") val primaryGenreName : String? = null,
	@SerializedName("previewUrl") val previewUrl : String? = null,
	@SerializedName("longDescription") val description : String? = null
)