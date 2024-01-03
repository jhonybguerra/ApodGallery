package com.example.apodgallery.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apodgallery.R
import com.example.apodgallery.apod.presenter.ApodContract
import com.example.apodgallery.apod.presenter.ApodPresenterImpl
import com.example.apodgallery.databinding.FragmentApodBinding
import com.example.apodgallery.model.network.ApodResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ApodFragment : Fragment(), ApodContract.View {

    private var _binding: FragmentApodBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var presenter: ApodPresenterImpl

    private val apodAdapter by lazy {
        ApodAdapter { apodResponse ->
            showAddToFavoritesDialog(apodResponse)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentApodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        showLoading(true)
        presenter.attachView(this)
        presenter.loadApodList(20)
    }

    override fun onDestroy() {
        presenter.detachView()
        _binding = null
        super.onDestroy()

    }

    override fun showApods(apods: List<ApodResponse>) {
        showLoading(false)
        apodAdapter.submitList(apods)
    }

    override fun showSuccess(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        showLoading(false)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    private fun setupRecyclerView() {
        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = apodAdapter
        }
    }

    private fun showAddToFavoritesDialog(apodResponse: ApodResponse) {
        AlertDialog.Builder(requireContext(), R.style.SpaceThemeDialog)
            .setTitle(getString(R.string.details))
            .setMessage(apodResponse.explanation)
            .setPositiveButton(getString(R.string.save)) { _, _ ->
                presenter.addFavorite(apodResponse)
            }
            .setNegativeButton(getString(R.string.close), null)
            .show()
    }
}