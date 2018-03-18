package br.unifor.ads.estudointent

import android.app.AlarmManager
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var despetadorButton: Button
    private lateinit var telefoneButton: Button
    private lateinit var navegadorButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        despetadorButton = findViewById(R.id.despertador)
        despetadorButton.setOnClickListener(this)

        telefoneButton = findViewById(R.id.telefone)
        telefoneButton.setOnClickListener(this)

        navegadorButton = findViewById(R.id.navegador)
        navegadorButton.setOnClickListener(this)

    }


    override fun onClick(v: View) {

        when(v.id){

            R.id.despertador -> {
                var hour = Calendar.HOUR
                createAlarm("Deu certo!", hour, 5)
            }

            R.id.telefone -> {
                var phoneNumber = "99999999"
                dialPhoneNumber(phoneNumber)
            }

            R.id.navegador -> {
                var url = "http://unifor.br"
                openWebPage(url)
            }
        }

    }

    fun createAlarm(message: String, hour: Int, minutes: Int){
        var int = Intent(AlarmClock.ACTION_SET_ALARM)
        int.putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes)

        if (int.resolveActivity(getPackageManager()) != null){
            startActivity(int)
        }
    }

    fun dialPhoneNumber(phoneNumber: String){
        var int = Intent(Intent.ACTION_DIAL)
        int.setData(Uri.parse("tel: ${phoneNumber}"))
        if (int.resolveActivity(getPackageManager()) != null){
            startActivity(int)
        }
    }

    fun openWebPage(url: String){
        var uri = Uri.parse(url)
        var int = Intent(Intent.ACTION_VIEW, uri)
        if (int.resolveActivity(getPackageManager()) != null) {
            startActivity(int)
        }

    }

}
