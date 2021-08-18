package com.utku.nasarover.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "rover")
data class Rover (
	@PrimaryKey(autoGenerate = true)
	val id : Int,
	val name : String,
	val landing_date : String,
	val launch_date : String,
	val status : String
)