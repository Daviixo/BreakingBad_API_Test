package com.daviixo.breakingbad_api_test

import android.os.Bundle
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_character_details.*
import kotlinx.android.synthetic.main.item_character.view.*


class CharacterDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        val currentCharacter: CharacterInfo =
            intent.getSerializableExtra(getString(R.string.key_character)) as CharacterInfo

        views(currentCharacter)

    }

    private fun views(cc: CharacterInfo) {
        tvCharacterNameN.text = cc.name
        tvNickNameN.text = cc.nickname
        tvOccupationN.text = cc.occupation.toString().replace("[","").replace("]","")
        tvStatusN.text = cc.status
        tvPortrayedN.text = cc.portrayed

        Glide.with(context)
            .load(cc.img)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .circleCrop()
            .into(ivImgN)
    }
}