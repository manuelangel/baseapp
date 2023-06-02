package com.example.reviewapp.data.impl

import com.example.reviewapp.data.datasource.api.entities.response.PhotoResponse
import com.example.reviewapp.data.datasource.localdatabase.entities.PhotoDB
import com.example.reviewapp.data.datasource.localdatabase.realmetities.PhotoRealmDB
import com.example.reviewapp.domain.Photo

fun PhotoResponse.toPhoto():Photo{
    return Photo(this.id?:-1,this.title?:"",this.url?:"")
}

fun PhotoRealmDB.toPhoto():Photo{
    return Photo(this.id,this.title,this.imageUrl)
}