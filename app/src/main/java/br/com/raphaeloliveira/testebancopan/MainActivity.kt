package br.com.raphaeloliveira.testebancopan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.view.View
import br.com.raphaeloliveira.testebancopan.adapter.TopGamesAdapter
import br.com.raphaeloliveira.testebancopan.model.GameItem
import br.com.raphaeloliveira.testebancopan.model.TopGames
import br.com.raphaeloliveira.testebancopan.network.TwitchService
import br.com.raphaeloliveira.testebancopan.shared.InfiniteScrollListener
import br.com.raphaeloliveira.testebancopan.shared.Utils
import br.com.raphaeloliveira.testebancopan.ui.DetailsFragment
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_scrolling.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private var gameItemList: MutableList<GameItem> =  ArrayList()
    private var snackbar: Snackbar? = null
    private var adapter: TopGamesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Paper.init(this)

        setupViews()

        loadGames(10, 0)
    }

    private fun setupViews(){

        content_scrolling_swipe_refresh.setOnRefreshListener(this)
        activity_main_toolbar.title = resources.getString(R.string.app_name)

        adapter = TopGamesAdapter(gameItemList as ArrayList<GameItem>, this)
        content_scrolling_recycler_view.adapter = adapter

        content_scrolling_recycler_view.apply {
            setHasFixedSize(true)
            val gridLayoutManager = GridLayoutManager(context, 2)
            layoutManager = gridLayoutManager
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ loadGames(adapter!!.itemCount + 10, 0) }, gridLayoutManager))
        }
    }

    private fun loadGames(limit: Int, offset: Int) {
        if (Utils.isInternetAvailable(this)) {
            if (snackbar != null) snackbar!!.dismiss()
            TwitchService.getGames(limit, offset).enqueue(object : Callback<TopGames> {
                override fun onResponse(call: Call<TopGames>, response: Response<TopGames>) {
                    if (response.isSuccessful)
                        updateList(response.body()?.gameItemList)
                }

                override fun onFailure(call: Call<TopGames>, t: Throwable) {
                    stopLoadings()
                    initSnackbar(R.string.error)
                    snackbar?.show()
                }
            })
        } else {
            initSnackbar(R.string.error_no_internet)

            updateList(Paper.book().read("games"))

            snackbar?.show()
            stopLoadings()
        }
    }

    private fun updateList(list: List<GameItem>?) {
        if(list != null){
            gameItemList.clear()
            gameItemList.addAll(list)
            Paper.book().write("games", list)
        }
        adapter?.notifyDataSetChanged()
        stopLoadings()
    }

    override fun onRefresh() {
        loadGames(10, 0)
    }

    private fun stopLoadings() {
        activity_main_progress_bar.visibility = View.GONE
        content_scrolling_swipe_refresh.isRefreshing = false
    }

    private fun initSnackbar(messageId: Int) {
        snackbar = Snackbar.make(content_scrolling_recycler_view, messageId, Snackbar.LENGTH_INDEFINITE)
    }

    fun loadDetails(gameItem: GameItem) {
        val detailsFragment = DetailsFragment.newInstance(gameItem, this)
        detailsFragment.show(supportFragmentManager, DetailsFragment.FRAGMENT_TAG)
    }
}
