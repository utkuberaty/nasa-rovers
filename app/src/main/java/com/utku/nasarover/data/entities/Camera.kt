package com.utku.nasarover.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "camera")
data class Camera (
	@PrimaryKey(autoGenerate = true)
	val id : Int,
	val name : String,
	val rover_id : Int,
	val full_name : String
)