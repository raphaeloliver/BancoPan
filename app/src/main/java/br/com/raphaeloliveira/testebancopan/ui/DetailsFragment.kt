package br.com.raphaeloliveira.testebancopan.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.raphaeloliveira.testebancopan.R
import br.com.raphaeloliveira.testebancopan.model.GameItem
import br.com.raphaeloliveira.testebancopan.shared.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*

@SuppressLint("ValidFragment")
class DetailsFragment : AppCompatDialogFragment(){

    private lateinit var gameItem: GameItem

    companion object {
        @JvmField
        val FRAGMENT_TAG: String = "details_fragment"

        fun newInstance(gameItem: GameItem, context: Context): DetailsFragment {
            var frag = DetailsFragment()
            frag.gameItem = gameItem
            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_details, container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadBox()
        loadLogo()
        loadTitle()
        loadDetails()
    }

    private fun loadBox() {
        val url = gameItem.game?.box!!.template
        Picasso.with(fragment_details_img_box.context)
            .load(Utils.getBoxUrl(url, Utils.getDensity(this!!.context!!)))
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(fragment_details_img_box)
    }

    private fun loadLogo() {
        val url = gameItem.game?.logo!!.template
        Picasso.with(fragment_details_img_logo.context)
            .load(Utils.getLogoUrl(url, Utils.getDensity(this!!.context!!)))
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(fragment_details_img_logo)
    }

    private fun loadTitle() {
        fragment_details_title.text = gameItem.game?.name
    }

    private fun loadDetails() {
        val details = buildDetails(gameItem.channels.toString(), gameItem.viewers.toString())
        fragment_details_txt_info.text = details
    }

    private fun buildDetails(channels: String, views: String): String {
        return String.format("%s %s\n%s %s", getString(R.string.channel), channels, getString(R.string.viewers), views)
    }
}