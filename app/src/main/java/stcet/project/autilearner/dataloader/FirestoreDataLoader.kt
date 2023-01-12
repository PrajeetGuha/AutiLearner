package stcet.project.autilearner.dataloader

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import stcet.project.autilearner.model.Category

class FirestoreDataLoader {

    private val db = Firebase.firestore
    fun loadCategories(){
        db.collection("categories").get().addOnSuccessListener { result ->

                val categories = mutableListOf<Category>()
                for (document in result){
                    val category = Category(
                        document.data["name"].toString(),
                        document.data["image"] as DocumentReference)
                    categories.add(category)
            }
            Log.d("STATUS","Successfully loaded data")
        }.addOnFailureListener{
            Log.d("STATUS","Failed to load data")
        }
    }
}