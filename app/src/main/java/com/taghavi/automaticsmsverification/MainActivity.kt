package com.taghavi.automaticsmsverification

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.HintRequest
import com.google.android.gms.common.api.GoogleApiClient


class MainActivity : AppCompatActivity() {
    val RESOLVE_HINT = 1234
   lateinit var apiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun requestHint() {
        val hintRequest = HintRequest.Builder()
            .setPhoneNumberIdentifierSupported(true)
            .build()
        val intent = Auth.CredentialsApi.getHintPickerIntent(
            apiClient, hintRequest
        )
        startIntentSenderForResult(
            intent.intentSender,
            RESOLVE_HINT, null, 0, 0, 0
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == RESULT_OK) {
                val credential: Credential = data.getParcelableExtra(Credential.EXTRA_KEY)!!
            }
        }
    }
}