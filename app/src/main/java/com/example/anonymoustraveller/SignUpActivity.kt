package com.example.anonymoustraveller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.example.anonymoustraveller.databinding.SignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: SignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.SignUpbtn.setOnClickListener {
            val Email = binding.RegEdemail.text.toString()
            val Password = binding.RegEdpassword1.text.toString()
            val Confirmpsw= binding.RegEdpassword2.text.toString()
            val Name = binding.RegEdname.text.toString()
            val City= binding.RegEdcity.text.toString()



            if(Name.isNotEmpty() && Email.isNotEmpty() && Password.isNotEmpty() && Confirmpsw.isNotEmpty()
                && City.isNotEmpty()){
                if(Password == Confirmpsw){
                    firebaseAuth.createUserWithEmailAndPassword(Email,Password)
                        .addOnCompleteListener(this){task->
                            if(task.isSuccessful){
                                Toast.makeText(this,"Sign Up Successfully",Toast.LENGTH_SHORT).show()

                                startActivity(Intent(this,SignInActivity::class.java))

                            }else{
                                Toast.makeText(this,"something went wrong",Toast.LENGTH_SHORT).show()
                            }
                        }
                }else{
                    Toast.makeText(this, "Password is Not Matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Please Fill all the details", Toast.LENGTH_SHORT).show()
                }

        }
    }
}