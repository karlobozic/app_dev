package ie.tud.finddog.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ie.tud.finddog.R
import ie.tud.finddog.ui.about.AboutViewModel

    class MessageFragment : Fragment() {


        private lateinit var messageViewModel : MessageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messageViewModel =
            ViewModelProvider(this).get(MessageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_message, container, false)
        //val textView: TextView = root.findViewById(R.id.text_slideshow)
        messageViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }
}