package ie.tud.finddog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import ie.tud.finddog.R
import ie.tud.finddog.databinding.CardDogBinding
import ie.tud.finddog.models.DogModel
import ie.tud.finddog.utils.customTransformation

interface DogClickListener {
    fun onDogClick(dog: DogModel)
}

class DogAdapter constructor(private var dogs: ArrayList<DogModel>,
                                  private val listener: DogClickListener,
                                  private val readOnly: Boolean)
    : RecyclerView.Adapter<DogAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardDogBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding,readOnly)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val dog = dogs[holder.adapterPosition]
        holder.bind(dog,listener)
    }

    fun removeAt(position: Int) {
        dogs.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = dogs.size

    inner class MainHolder(val binding : CardDogBinding, private val readOnly : Boolean) :
        RecyclerView.ViewHolder(binding.root) {

        val readOnlyRow = readOnly

        fun bind(dog: DogModel, listener: DogClickListener) {
            binding.root.tag = dog.uid
            binding.name.text = dog.name
            binding.date.text = dog.date
            binding.breed.text = dog.breed
            binding.email.text = dog.email


            Picasso.get().load(dog.profilepic.toUri())
                .resize(200, 200)
                .transform(customTransformation())
                .centerCrop()
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(binding.imageIcon)

            binding.root.setOnClickListener { listener.onDogClick(dog) }
//            binding.executePendingBindings()
        }
    }
}