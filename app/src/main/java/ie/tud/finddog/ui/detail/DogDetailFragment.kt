package ie.tud.finddog.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ie.tud.finddog.R
import ie.tud.finddog.databinding.FragmentDogDetailBinding
import ie.tud.finddog.ui.report.ReportViewModel
import timber.log.Timber

class DogDetailFragment : Fragment() {

    private lateinit var detailViewModel: DogDetailViewModel
    private val args by navArgs<DogDetailFragmentArgs>()
    private var _fragBinding: FragmentDogDetailBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val reportViewModel : ReportViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentDogDetailBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        detailViewModel = ViewModelProvider(this).get(DogDetailViewModel::class.java)
        detailViewModel.observableDog.observe(viewLifecycleOwner, Observer { render() })

//        fragBinding.deleteDonationButton.setOnClickListener {
//            reportViewModel.delete(loggedInViewModel.liveFirebaseUser.value?.email!!,
//                detailViewModel.observableDog.value?.id!!)
//            findNavController().navigateUp()
//        }

        return root
    }

    private fun render() {
        fragBinding.editEmail.setText("A Message")

        fragBinding.name = detailViewModel
        fragBinding.area = detailViewModel
        fragBinding.date = detailViewModel
        fragBinding.breed = detailViewModel
        fragBinding.gender = detailViewModel



//        fragBinding. = detailViewModel
        Timber.i("Retrofit fragBinding.donationvm == $fragBinding.donationvm")
    }

    override fun onResume() {
        super.onResume()
        detailViewModel.getDog(args.dogid)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}