package com.daviixo.breakingbad_api_test

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_character.view.*

private lateinit var itemList: List<CharacterInfo>
lateinit var context: Context

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (::itemList.isInitialized) itemList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = itemList.get(position)
        holder.itemView.tvCharacterName.text = item.name
        holder.itemView.tvNickName.text = item.nickname
        Glide.with(context)
            .load(item.img)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .circleCrop()
            .into(holder.itemView.ivImg)

        //This is the heart animation for our characters
        val lottieAnim = holder.itemView.laHeart

        if (item.isSelected) {
            lottieAnim.speed = 1f
            lottieAnim.playAnimation()
        } else {
            lottieAnim.speed = 0f
            lottieAnim.playAnimation()
        }

        // This is used when a user clicks the heart
        lottieAnim.setOnClickListener {
            var isCheckt = item.isSelected
            val statusC: Boolean
            if (isCheckt) {
                lottieAnim.speed = 0f
                lottieAnim.playAnimation()
                isCheckt = false
                statusC = isCheckt
            } else {
                lottieAnim.speed = 1f
                lottieAnim.playAnimation()
                isCheckt = true
                statusC = isCheckt
            }

            itemList[position].isSelected = statusC

        }

    }

    fun updateData(list: List<CharacterInfo>) {
        itemList = list;
        notifyDataSetChanged()
    }


     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var view: View = itemView

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {

            Toast.makeText(view?.context, "Opening...", Toast.LENGTH_LONG).show()

            val item: CharacterInfo = itemList.get(adapterPosition)
            var intent = Intent(view?.context, CharacterDetails::class.java)
            intent.putExtra(view?.context?.getString(R.string.key_character),item)
            view?.context?.startActivity(intent)

        }

    }

}
