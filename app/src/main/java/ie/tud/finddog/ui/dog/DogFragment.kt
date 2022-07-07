package ie.tud.finddog.ui.dog

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import ie.tud.finddog.R
import ie.tud.finddog.databinding.FragmentDogBinding
import ie.tud.finddog.main.DogApp
import ie.tud.finddog.models.DogModel

class DogFragment : Fragment() {

    lateinit var app: DogApp
    private var _fragBinding: FragmentDogBinding? = null
    private val fragBinding get() = _fragBinding!!
    //lateinit var navController: NavController
    private lateinit var dogViewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as DogApp
        setHasOptionsMenu(true)
        //navController = Navigation.findNavController(activity!!, R.id.nav_host_fragment)
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


//

    fun setButtonListener(layout: FragmentDogBinding) {
        layout.submitButton.setOnClickListener {
            val name = layout.editTextName.text.toString()
            val area = layout.editTextArea.text.toString()
            val date = layout.editTextDate.text.toString()
            val breed = layout.editTextBreed.text.toString()
            val gender = layout.editTextGender.text.toString()

            dogViewModel.addDog(
                DogModel( name = name,
                    area = area,
                    date = date,
                    breed = breed,
                    gender = gender))

//            Timber.i("Total Donated so far")
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

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

}