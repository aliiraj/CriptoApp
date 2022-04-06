package com.example.criptoapp.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinDetailDto(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("development_status")
    val developmentStatus: String = "",
    @SerializedName("first_data_at")
    val firstDataAt: String = "",
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean? = null,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("is_active")
    val isActive: Boolean? = null,
    @SerializedName("is_new")
    val isNew: Boolean? = null,
    @SerializedName("last_data_at")
    val lastDataAt: String = "",
    @SerializedName("message")
    val message: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("open_source")
    val openSource: Boolean? = null,
    @SerializedName("org_structure")
    val orgStructure: String = "",
    @SerializedName("proof_type")
    val proofType: String = "",
    @SerializedName("rank")
    val rank: Int? = null,
    @SerializedName("started_at")
    val startedAt: String = "",
    @SerializedName("symbol")
    val symbol: String = "",
    @SerializedName("tags")
    val tags: List<TagDto>? = emptyList(),
    @SerializedName("team")
    val team: List<TeamMemberDto>? = emptyList(),
    @SerializedName("type")
    val type: String = "",
) : Parcelable