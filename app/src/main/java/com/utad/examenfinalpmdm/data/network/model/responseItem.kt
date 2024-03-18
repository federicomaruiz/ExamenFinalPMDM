package com.utad.examenfinalpmdm.data.network.model


import com.google.gson.annotations.SerializedName

data class responseItem(
    @SerializedName("dead_line")
    val deadLine: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("details")
    val details: List<String>,
    @SerializedName("employee")
    val employee: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("project")
    val project: Project,
    @SerializedName("task_title")
    val taskTitle: String
)