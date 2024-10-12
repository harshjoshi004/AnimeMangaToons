package com.harshjoshi.animemangatoons.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.harshjoshi.animemangatoons.roomDatabase.Webtoon
import com.harshjoshi.animemangatoons.roomDatabase.dummyWebtoon

class HomeViewModel:ViewModel() {

    private val _listState = mutableStateListOf<Webtoon>()
    val listState: List<Webtoon> = _listState

    private val ref = FirebaseDatabase
        .getInstance()
        .getReference("webtoons")

    fun getWebtoonsFromServer(){
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Firebase/getWebtoons", "DataChanged")
                _listState.clear()
                snapshot.children.forEach { it->
                    it.getValue(Webtoon::class.java)?.let { webtoon->
                        _listState.add(webtoon)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase/getWebtoons", "Failed to add webtoon" + error.message)
            }
        })
    }

    fun addWebtoonToServer(webtoon: Webtoon){
        ref.child(webtoon.id.toString()).setValue(webtoon)
            .addOnSuccessListener { Log.d("Firebase/addWebtoon", "Webtoon added successfully") }
            .addOnFailureListener { exception-> Log.e("FirebaseError/addWebtoon", "Failed to add webtoon" + exception.message) }
    }

    fun giveRating(webtoon: Webtoon, rating: Float, context: Context){
        val newRating = String.format("%.2f", (webtoon.rating + rating) / (webtoon.ratingCount + 1)).toFloat()

        ref.child(webtoon.id.toString()).setValue(webtoon.copy(rating = newRating, ratingCount = webtoon.ratingCount+1))
            .addOnSuccessListener {
                Log.d("Firebase/addWebtoon", "Webtoon added successfully")
                Toast.makeText(context, "Rating Given Successfully!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                exception-> Log.e("FirebaseError/addWebtoon", "Failed to add webtoon: " + exception.message)
                Toast.makeText(context, "Failed to add webtoon: " + exception.message, Toast.LENGTH_SHORT).show()
            }
    }

    fun updateSave(webtoon: Webtoon, context: Context){
        ref.child(webtoon.id.toString()).setValue(webtoon.copy(savedByCount = webtoon.savedByCount+1))
            .addOnSuccessListener {
                Log.d("Firebase/addWebtoon", "Webtoon added successfully")
                Toast.makeText(context, "Saved Successfully!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                    exception-> Log.e("FirebaseError/addWebtoon", "Failed to add webtoon: " + exception.message)
                Toast.makeText(context, "Failed to save webtoon: " + exception.message, Toast.LENGTH_SHORT).show()
            }
    }
}