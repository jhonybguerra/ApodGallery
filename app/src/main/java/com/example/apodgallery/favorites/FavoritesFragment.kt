package com.example.apodgallery.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apodgallery.R
import com.example.apodgallery.databinding.FragmentFavoritesBinding
import com.example.apodgallery.favorites.presenter.FavoritesContract
import com.example.apodgallery.favorites.presenter.FavoritesPresenterImpl
import com.example.apodgallery.model.db.FavoriteApod
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment(), FavoritesContract.View {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var presenter: FavoritesPresenterImpl

    private val favoritesAdapter by lazy {
        FavoritesAdapter { favoriteApod ->
            showRemoveFavoriteDialog(favoriteApod)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        presenter.attachView(this)
        presenter.loadFavoritesList()
    }

    override fun onDestroy() {
        presenter.detachView()
        _binding = null
        super.onDestroy()
    }

    override fun showFavorites(apods: List<FavoriteApod>) {
        favoritesAdapter.submitList(apods)
    }

    override fun showSuccess(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoritesAdapter
        }
    }

    private fun showRemoveFavoriteDialog(favoriteApod: FavoriteApod) {
        AlertDialog.Builder(requireContext(), R.style.SpaceThemeDialog)
            .setTitle(favoriteApod.title)
            .setMessage(favoriteApod.explanation)
            .setPositiveButton(getString(R.string.remove)) { _, _ ->
                presenter.removeFavorites(favoriteApod.id)
            }
            .setNegativeButton(getString(R.string.close), null)
            .show()
    }



}