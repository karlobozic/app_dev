package ie.tud.finddog.ui.report

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ie.tud.finddog.R
import ie.tud.finddog.adapters.DogAdapter
import ie.tud.finddog.adapters.DogClickListener
import ie.tud.finddog.databinding.FragmentReportBinding
import ie.tud.finddog.main.DogApp
import ie.tud.finddog.models.DogModel

class ReportFragment : Fragment(), DogClickListener {

    lateinit var app: DogApp
    private var _fragBinding: FragmentReportBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var reportViewModel: ReportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentReportBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        fragBinding.recyclerView.layoutManager = LinearLayoutManager(activity)

        reportViewModel = ViewModelProvider(this).get(ReportViewModel::class.java)
        reportViewModel.observableDogsList.observe(viewLifecycleOwner, Observer {
                dogs ->
            dogs?.let { render(dogs) }
        })

        val fab: FloatingActionButton = fragBinding.fab
        fab.setOnClickListener {
            val action = ReportFragmentDirections.actionReportFragmentToDogFragment()
            findNavController().navigate(action)
        }
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_report, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun render(dogsList: List<DogModel>) {
        fragBinding.recyclerView.adapter = DogAdapter(dogsList, this)
        if (dogsList.isEmpty()) {
            fragBinding.recyclerView.visibility = View.GONE
            fragBinding.dogsNotFound.visibility = View.VISIBLE
        } else {
            fragBinding.recyclerView.visibility = View.VISIBLE
            fragBinding.dogsNotFound.visibility = View.GONE
        }
    }

    override fun onDogClick(dog: DogModel) {
        val action = ReportFragmentDirections.actionReportFragmentToDogDetailFragment(dog.id)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        reportViewModel.load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}