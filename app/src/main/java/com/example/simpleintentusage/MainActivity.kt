package com.example.simpleintentusage

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult: TextView
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null){
            val selectedValue = result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
            tvResult.text = "Hasil kembalian: $selectedValue"
        }
    }

    companion object{
        private const val STATE_RESULT = "state result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        val btnMoveWithData: Button = findViewById(R.id.btn_move_with_data)
        val btnMoveWithObject: Button = findViewById(R.id.btn_move_with_object)
        val btnDialNumber: Button = findViewById(R.id.btn_dial_number)
        val btnMoveForResult: Button = findViewById(R.id.btn_move_for_result)
        val btnMoveToDetail: Button = findViewById(R.id.btn_move_to_detail)
        tvResult = findViewById(R.id.tv_result)

        btnMoveActivity.setOnClickListener(this)
        btnMoveWithData.setOnClickListener(this)
        btnMoveWithObject.setOnClickListener(this)
        btnDialNumber.setOnClickListener(this)
        btnMoveForResult.setOnClickListener(this)
        btnMoveToDetail.setOnClickListener(this)

        if (savedInstanceState != null){
            tvResult.text = savedInstanceState.getString(STATE_RESULT)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onClick(view: View?) {
        when (view?.id){
            R.id.btn_move_activity -> {
                val moveSecondIntent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(moveSecondIntent)
            }
            R.id.btn_move_with_data -> {
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Kim Sa Wo")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, "5")
                startActivity(moveWithDataIntent)
            }
            R.id.btn_move_with_object -> {
                val person = Person(
                    "Markonah Lee",
                    5,
                    "markonah12@gmail.com",
                    "Bandung"
                )

                val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }
            R.id.btn_dial_number -> {
                val phoneNumber = "087898985646"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_move_for_result -> {
                val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                resultLauncher.launch(moveForResultIntent)
            }
            R.id.btn_move_to_detail -> {
                val moveToDetailIntent = Intent(this@MainActivity, ViewDetailActivity::class.java)
                startActivity(moveToDetailIntent)
            }
        }
    }
}