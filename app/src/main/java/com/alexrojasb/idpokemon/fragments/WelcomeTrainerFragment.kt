package com.alexrojasb.idpokemon.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alexrojasb.idpokemon.R
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class WelcomeTrainerFragment : Fragment(R.layout.fragment_trainer_welcome) {
    private lateinit var button: Button
    private lateinit var trainerName: TextInputEditText

    private val disposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view.findViewById(R.id.button)
        trainerName = view.findViewById(R.id.trainerName)

        disposable.add(
            trainerName.textChanges()
                .subscribe{
                    button.isEnabled = it.toString().isNotEmpty()
                }
        )

        disposable.add(
            button.clicks()
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe{
                    val action =
                        WelcomeTrainerFragmentDirections.actionWelcomeTrainerFragmentToMainFragment()

                    findNavController().navigate(action)
                }
        )
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}