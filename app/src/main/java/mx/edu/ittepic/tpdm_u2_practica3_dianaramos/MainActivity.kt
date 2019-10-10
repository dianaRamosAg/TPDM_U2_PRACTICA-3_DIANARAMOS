package mx.edu.ittepic.tpdm_u2_practica3_dianaramos

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    var numN : EditText?=null
    var numM : EditText?=null
    var btnGenerar : Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numN = findViewById(R.id.numN)
        numM = findViewById(R.id.numM)
        btnGenerar = findViewById(R.id.btnGenerar)

        btnGenerar?.setOnClickListener{
            var num_n=numN?.text.toString().toInt()
            var num_m=numM?.text.toString().toInt()
            generarNumAleatorios(num_n,num_m,applicationContext).execute()
        }
    }

    class generarNumAleatorios(num_n: Int, num_m: Int, context: Context) : AsyncTask<Unit, Unit, List<Int>>(){
        var context = context
        var n1 = num_n
        var n2 = num_m

        override fun doInBackground(vararg params: Unit?): List<Int>? {
            var listNum:List<Int> = List(2000){(n1 until n2).random()}
            return listNum
        }
        override fun onPostExecute(result: List<Int>?) {
            super.onPostExecute(result)
            var n=0
            var numeros=" "
            (0..1999).forEach {i->
                var cont = 0

                (1..result!!.get(i)).forEach {
                    if (result!!.get(i) % it == 0) {
                        cont++
                    }

                }
                if(cont==2)numeros=numeros+result!!.get(i)+" "
            }
            guardarNA(numeros)

        }

        fun guardarNA(numeros:String){
            val guardarArchivo = OutputStreamWriter(context.openFileOutput("primos.txt", Activity.MODE_PRIVATE))
            guardarArchivo.write(numeros)
            guardarArchivo.flush()
            Toast.makeText(context, "Se guardaron los numeros primos en archivo primos.txt", Toast.LENGTH_LONG).show()
            Toast.makeText(context, numeros, Toast.LENGTH_LONG).show()

        }
    }


}
