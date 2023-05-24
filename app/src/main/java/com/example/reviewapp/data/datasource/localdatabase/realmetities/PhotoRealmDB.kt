package com.example.reviewapp.data.datasource.localdatabase.realmetities

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class PhotoRealmDB :RealmObject {
    @PrimaryKey
    var id: Int = -1
    var title: String = ""
    var imageUrl: String = ""
}