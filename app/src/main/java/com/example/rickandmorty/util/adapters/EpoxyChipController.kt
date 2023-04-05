package com.example.rickandmorty.util.adapters

import android.view.View
import com.airbnb.epoxy.*
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.rickandmorty.CallBack
import com.example.rickandmorty.R
import com.example.rickandmorty.util.adapters.EpoxyChipModel.ChipHolder
import com.example.rickandmorty.util.paging.LCharactersDataSource
import com.google.android.material.chip.Chip
import com.rickandmortyapi.Location


class EpoxyChipController(private val callback: CallBack) : PagedListEpoxyController<Location>() {

    override fun buildItemModel(currentPosition: Int, item: Location?): EpoxyModel<*> {
        return EpoxyChipModel_()
            .id(item!!.name)
            .location(item.name)
            .clickListener { view ->
                LCharactersDataSource.location = item
                callback.onCallback()
            }
    }
}

@EpoxyModelClass(layout = R.layout.item_location_chip)
abstract class EpoxyChipModel : EpoxyModelWithHolder<ChipHolder>() {

    @EpoxyAttribute
    var location: String? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null

    override fun bind(holder: ChipHolder) {
        holder.chip!!.text = location
        holder.chip!!.setOnClickListener(clickListener)
    }

    class ChipHolder : EpoxyHolder() {
        var chip: Chip? = null
        override fun bindView(itemView: View) {
            chip = itemView.findViewById(R.id.chip)
        }
    }
}