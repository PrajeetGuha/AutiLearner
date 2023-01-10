package stcet.project.autilearner.model

import com.google.firebase.storage.FirebaseStorage


data class Category(
    private val _name : String,
    private val _image : FirebaseStorage
){
    val name
    get() = _name

    val image
    get() = _image
}
