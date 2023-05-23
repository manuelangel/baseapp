package com.example.reviewapp.data.impl

import com.example.reviewapp.data.datasource.entities.response.PhotoResponse
import com.example.reviewapp.domain.Photo

fun PhotoResponse.toPhoto():Photo{
    return Photo(this.id?:-1,this.title?:"",this.url?:"")
}