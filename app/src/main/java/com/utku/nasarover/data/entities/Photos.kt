package com.utku.nasarover.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "photos")
data class Photos (
	@PrimaryKey(autoGenerate = true)
	val id : Int,
	val sol : Int,
	val camera : Camera,
	val img_src : String,
	val earth_date : String,
	val rover : Rover
)