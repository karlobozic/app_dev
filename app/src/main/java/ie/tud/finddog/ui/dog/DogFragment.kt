package ie.tud.finddog.ui.dog

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import ie.tud.finddog.R
import ie.tud.finddog.databinding.FragmentDogBinding
import ie.tud.finddog.databinding.FragmentReportBinding
import ie.tud.finddog.firebase.FirebaseImageManager
import ie.tud.finddog.main.DogApp
import ie.tud.finddog.models.DogModel
import ie.tud.finddog.ui.auth.LoggedInViewModel
import ie.tud.finddog.ui.map.MapsViewModel
import ie.tud.finddog.ui.report.ReportViewModel
import ie.tud.finddog.utils.readImageUri
import ie.tud.finddog.utils.showImagePicker
import timber.log.Timber

class DogFragment : Fragment() {

    lateinit var app: DogApp
    private var _fragBinding: FragmentDogBinding? = null
    private val fragBinding get() = _fragBinding!!
    //lateinit var navController: NavController
    private lateinit var dogViewModel: DogViewModel
    private val reportViewModel: ReportViewModel by activityViewModels()
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()
    private val mapsViewModel: MapsViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as DogApp
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _fragBinding = FragmentDogBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_dog)

        dogViewModel =
            ViewModelProvider(this).get(DogViewModel::class.java)
        //val textView: TextView = root.findViewById(R.id.text_home)
        dogViewModel.observableStatus.observe(viewLifecycleOwner, Observer {
                status -> status?.let { render(status) }
        })


        setButtonListener(fragBinding)
        return root;
    }
    private fun render(status: Boolean) {
        when (status) {
            true -> {
                view?.let {
                    //Uncomment this if you want to immediately return to Report
                    //findNavController().popBackStack()
                }
            }
            false -> Toast.makeText(context,getString(R.string.dogError),Toast.LENGTH_LONG).show()
        }
    }


    fun setButtonListener(layout: FragmentDogBinding) {
        layout.submitButton.setOnClickListener {
        val name = layout.editTextName.text.toString()
        val date = layout.editTextDate.text.toString()
        val breed = layout.editTextBreed.text.toString()
        val gender = layout.editTextGender.text.toString()

            dogViewModel.addDog(
                loggedInViewModel.liveFirebaseUser,
                DogModel(
                    name = name,
                    date = date,
                    breed = breed,
                    gender = gender,
                    email = loggedInViewModel.liveFirebaseUser.value?.email!!,
                    latitude = mapsViewModel.currentLocation.value!!.latitude,
                    longitude = mapsViewModel.currentLocation.value!!.longitude,
                )
            )
        }
}

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_dog, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

}