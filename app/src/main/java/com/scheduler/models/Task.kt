package com.scheduler.models

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val datetime: String,
    val status: String
) 