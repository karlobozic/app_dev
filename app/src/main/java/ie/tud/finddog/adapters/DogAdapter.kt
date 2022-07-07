package ie.tud.finddog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.tud.finddog.R
import ie.tud.finddog.databinding.CardDogBinding
import ie.tud.finddog.models.DogModel

interface DogClickListener {
    fun onDogClick(dog: DogModel)
}

class DogAdapter constructor(private var dogs: ArrayList<DogModel>,
                             private val listener: DogClickListener)
    : RecyclerView.Adapter<DogAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardDogBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val dog = dogs[holder.adapterPosition]
        holder.bind(dog, listener)
    }

    fun removeAt(position: Int) {
        dogs.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = dogs.size

    inner class MainHolder(val binding : CardDogBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dog: DogModel, listener: DogClickListener) {
            binding.root.tag = dog.uid
            binding.name.text = dog.name
            binding.date.text = dog.date
            binding.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
            binding.root.setOnClickListener { listener.onDogClick(dog) }
//            binding.executePendingBindings()
        }
    }
}