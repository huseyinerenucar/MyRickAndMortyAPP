package com.example.rickandmorty.util.adapters

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.airbnb.epoxy.*
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.rickandmorty.CharActivity
import com.example.rickandmorty.CharActivity.character
import com.example.rickandmorty.R
import com.example.rickandmorty.util.GlideImageLoader
import com.example.rickandmorty.util.StaticFunctions.textFixer


class EpoxyCharController : PagedListEpoxyController<com.rickandmortyapi.Character>() {

    override fun buildItemModel(currentPosition: Int, item: com.rickandmortyapi.Character?): EpoxyModel<*> {
        return EpoxyCharModel_()
            .id(item!!.id)
            .name(item.name)
            .charUrl(item.image.toString())
            .gender(item.gender.toString())
            .specie(item.species)
            .status(item.status.toString())
            .clickListener { view ->
                character = item
                val bundle = Bundle()
                val intent = Intent(view.context, CharActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(view.context, intent, bundle)
            }

    }


}

@EpoxyModelClass(layout = R.layout.item_character_layout)
abstract class EpoxyCharModel : EpoxyModelWithHolder<EpoxyCharModel.CharHolder>() {

    @EpoxyAttribute
    var name: String? = null

    @EpoxyAttribute
    var charUrl: String? = null

    @EpoxyAttribute
    var gender: String? = null

    @EpoxyAttribute
    var specie: String? = null

    @EpoxyAttribute
    var status: String? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null

    override fun bind(holder: CharHolder) {
        holder.charName!!.text = name
        GlideImageLoader.setImage(holder.charName!!.context, charUrl, holder.charImage, null)

        if(gender.equals("MALE")){
            holder.charLayout!!.background = ContextCompat.getDrawable(holder.charName!!.context, R.drawable.dw_char_background_male)
            holder.genderImage!!.setImageResource(R.drawable.ic_male)
        }
        else if(gender.equals("FEMALE")){
            holder.charLayout!!.background = ContextCompat.getDrawable(holder.charName!!.context, R.drawable.dw_char_background_female)
            holder.genderImage!!.setImageResource(R.drawable.ic_female)
        }
        else {
            holder.charLayout!!.background = ContextCompat.getDrawable(holder.charName!!.context, R.drawable.dw_char_background)
            holder.genderImage!!.setImageResource(R.drawable.ic_unknown)
        }

        holder.specie!!.text = specie
        holder.status!!.text = textFixer(status)

        holder.charLayout!!.setOnClickListener(clickListener)
    }

    class CharHolder : EpoxyHolder() {
        var charLayout: LinearLayout? = null
        var charImage: ImageView? = null
        var genderImage: ImageView? = null
        var charName: TextView? = null
        var specie: TextView? = null
        var status: TextView? = null

        override fun bindView(itemView: View) {
            charLayout = itemView.findViewById(R.id.charLayout)
            charImage = itemView.findViewById(R.id.charImage)
            genderImage = itemView.findViewById(R.id.genderImage)
            charName = itemView.findViewById(R.id.charName)
            specie = itemView.findViewById(R.id.specie)
            status = itemView.findViewById(R.id.status)

        }
    }
}