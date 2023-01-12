package stcet.project.autilearner.model

import com.google.firebase.firestore.DocumentReference


data class Category(
    private val _name : String,
    private val _image : DocumentReference
){
    val name
    get() = _name

    val image
    get() = _image
}
