package com.example.projectuts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import com.example.projectuts.databinding.ActivityLoginBinding

import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        val createNowTextView: TextView = findViewById(R.id.txt_register)


// Menambahkan onClickListener ke TextView
        createNowTextView.setOnClickListener {


            // Kode yang akan dijalankan saat TextView diklik
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.LBtn1.setOnClickListener {

            val email = binding.edtEmail.text.toString()
            val password = binding.edtConfPassword.text.toString()

            //Validasi email
            if (email.isEmpty()) {
                binding.edtEmail.error = "Email Harus Diisi"
                binding.edtEmail.requestFocus()
                return@setOnClickListener
            }
            //Validasi email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmail.error = "Email Tidak Valid"
                binding.edtEmail.requestFocus()
                return@setOnClickListener
            }

            //Validasi password
            if (password.isEmpty()) {
                binding.edtConfPassword.error = "Password Harus Diisi"
                binding.edtConfPassword.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6) {
                binding.edtConfPassword.error = "Password Minimal 6"
                binding.edtConfPassword.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email, password)





        }
    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Selamat Datang $email", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,NavigatorActivity::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }


    }

}