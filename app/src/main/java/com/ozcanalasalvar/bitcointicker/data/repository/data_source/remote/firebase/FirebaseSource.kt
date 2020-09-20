package com.ozcanalasalvar.bitcointicker.data.repository.data_source.remote.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ozcanalasalvar.bitcointicker.data.model.Favourite
import io.reactivex.Completable
import javax.inject.Inject

class FirebaseSource @Inject constructor() {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val fireStore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }


    fun login(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun saveFavourite(userId: String, data: Favourite) =
        Completable.create { emitter ->
            fireStore.collection("favourites").document(userId)
                .set(data).addOnCompleteListener {
                    if (!emitter.isDisposed) {
                        if (it.isSuccessful)
                            emitter.onComplete()
                        else
                            emitter.onError(it.exception!!)
                    }
                }
        }

    fun readFavourites(userId: String): Task<Favourite> {
        val documentSnapshot = fireStore.collection("favourites").document(userId).get()
        return documentSnapshot.continueWith {
            if (it.isSuccessful) {
                return@continueWith documentSnapshot.result?.toObject(Favourite::class.java)
            } else
                return@continueWith null
        }
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser

}