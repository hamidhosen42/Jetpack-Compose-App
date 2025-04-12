package com.hamidhosen.easyshop.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamidhosen.easyshop.model.UserModel

class AuthViewModel : ViewModel() {

    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    fun login(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, "Login successful")
                } else {
                    onResult(false, task.exception?.localizedMessage ?: "Login failed")
                }
            }
    }

    fun signup(name: String, email: String, password: String, onResult: (Boolean, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: return@addOnCompleteListener

                    val userModel = UserModel(name,email,userId)

                    firestore.collection("users")
                        .document(userId)
                        .set(userModel)
                        .addOnSuccessListener {
                            onResult(true, "Signup successful")
                        }
                        .addOnFailureListener {
                            onResult(false, it.localizedMessage ?: "Failed to save user data")
                        }
                } else {
                    onResult(false, task.exception?.localizedMessage ?: "Signup failed")
                }
            }
    }
}
