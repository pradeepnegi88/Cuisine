package com.example.cuisine.presenter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cuisine.R
import com.example.cuisine.databinding.ActivityMainBinding
import com.example.cuisine.di.DaggerApplicationComponent
import com.example.cuisine.di.RepositoryModule
import com.example.cuisine.di.UseCaseModule
import com.example.cuisine.domain.Cuisine
import com.example.cuisine.presenter.adapter.CuisineAdapter
import com.example.cuisine.presenter.util.Constants
import com.example.cuisine.presenter.util.EspressoIdlingResource
import com.example.cuisine.presenter.viewmodel.CuisineViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }
    private lateinit var cuisineAdapter: CuisineAdapter
    private var photoList = emptyList<Cuisine>()
    val espressoTestIdlingResource = EspressoIdlingResource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDaggerDependencies()
        val viewModel = setDataBinding()

        progressBar.visibility = View.VISIBLE
        cuisineAdapter = CuisineAdapter(this@MainActivity, photoList, object : CuisineAdapter.OnItemClickListener {
            override fun onItemClick(item: Cuisine) {
                redirectToPhotoDescription(item)
            }
        })
        binding.root.gridview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,  RecyclerView.VERTICAL, false)
            adapter = cuisineAdapter
        }
        viewModel.cuisineDataList.observe(this, Observer<List<Cuisine>> { photoList ->
            binding.root.progressBar.visibility = View.GONE
            espressoTestIdlingResource.decrement()
            progressBar.visibility = View.GONE
            if (photoList.isNotEmpty())
                photoList?.let { cuisineAdapter.setData(it) }
            else
                Toast.makeText(this@MainActivity, R.string.no_data_available, Toast.LENGTH_LONG).show()

        })
        espressoTestIdlingResource.increment()

    }

    private fun redirectToPhotoDescription(item: Cuisine) {
        val intent = Intent(this@MainActivity, CuisineDescriptionActivity::class.java)
        intent.putExtra(Constants.DATA, item)
        startActivity(intent)
    }

    private fun setDataBinding(): CuisineViewModel {
        val viewModel = ViewModelProviders.of(this, viewModelFactory)[CuisineViewModel::class.java]
        binding.viewmodel = viewModel
        binding.executePendingBindings()
        binding.setLifecycleOwner(this)
        setContentView(binding.root)
        return viewModel
    }

    private fun setDaggerDependencies() {
        DaggerApplicationComponent.builder()
            .repositoryModule(RepositoryModule())
            .useCaseModule(UseCaseModule())
            .build()
            .inject(this)
    }

    fun getEspressoIdlingResourceForMainActivity() = espressoTestIdlingResource

}









