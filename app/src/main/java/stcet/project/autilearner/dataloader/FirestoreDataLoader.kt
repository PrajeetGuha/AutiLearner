package stcet.project.autilearner.dataloader

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import stcet.project.autilearner.model.Category

class FirestoreDataLoader {

    private val db = FirebaseFirestore.getInstance()
    fun loadCategories() : List<Category>{
        val listCategory = mutableListOf<Category>()
        db.collection("categories").get().addOnSuccessListener {

            val docsnaps = it.documents
            for (d in docsnaps){
                val category = Category(d.data?.get("name").toString(), d.data?.get("image") as FirebaseStorage)
                listCategory.add(category)
            }
        }
        return listCategory
    }
}